/*
 * Copyright (C) 2007-2019 Crafter Software Corporation. All Rights Reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.craftercms.studio.test.api2.objects;

import org.apache.http.HttpStatus;
import org.craftercms.studio.test.api.objects.BaseAPI;
import org.craftercms.studio.test.utils.APIConnectionManager;
import org.craftercms.studio.test.utils.JsonResponse;
import org.craftercms.studio.test.utils.JsonTester;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;

/**
 * @author luishernandez
 *
 */
public class UsersManagementAPI2 extends BaseAPI {

	private String offSet;
	private String limit;
	private String sort;

	public UsersManagementAPI2(JsonTester api, APIConnectionManager apiConnectionManager, String offSet,
			String limit, String sort) {
		super(api, apiConnectionManager);
		this.offSet = offSet;
		this.limit = limit;
		this.sort = sort;
	}

	public void testGetAllUsersNonSiteId() {
		api.get("/studio/api/2/users").urlParam("offset", offSet).urlParam("limit", limit)
				.urlParam("sort", sort).execute().status(HttpStatus.SC_OK);
	}

	public void testGetAllUsersNonSiteIdBadRequest() {
		api.get("/studio/api/2/users").urlParam("offset", offSet + "noninteger").urlParam("limit", limit)
				.urlParam("sort", sort).execute().status(HttpStatus.SC_BAD_REQUEST)
				.json("$.response.code", is(1001))
				.json("$.response.remedialAction", is("Check API and make sure you're sending the correct parameters"))
				.json("$.response.message", is("Invalid parameter(s)"));
	}

	public void testGetAllUsersNonSiteIdNonAuthorized() {
		api.get("/studio/api/2/users").urlParam("offset", offSet).urlParam("limit", limit)
				.urlParam("sort", sort).execute().status(HttpStatus.SC_UNAUTHORIZED);
	}

	public void testCreateUser(String id, String userName) {
		Map<String, Object> json = new HashMap<>();
		json.put("id", id);
		json.put("username", userName);
		json.put("password", userName);
		json.put("firstName", "Test");
		json.put("lastName", "Test");
		json.put("email", "test@test.com");
		json.put("enabled", true);
		json.put("externallyManaged", true);

		api.post("/studio/api/2/users").json(json).execute().status(HttpStatus.SC_CREATED);
	}

	public void testCreateUserResourceAlreadyExists(String id, String userName) {
		Map<String, Object> json = new HashMap<>();
		json.put("id", id);
		json.put("username", userName);
		json.put("password", userName);
		json.put("firstName", "Test");
		json.put("lastName", "Test");
		json.put("email", "test@test.com");
		json.put("enabled", true);
		json.put("externallyManaged", true);

		api.post("/studio/api/2/users").json(json).execute().status(HttpStatus.SC_CONFLICT);
	}

	public void testCreateUserBadRequest(String id, String userName) {
		Map<String, Object> json = new HashMap<>();
		json.put("idnonvalid", id);
		json.put("username", userName);
		json.put("password", userName);
		json.put("firstName", "Test");
		json.put("lastName", "Test");
		json.put("email", "test@test.com");
		json.put("enabled", true);
		json.put("externallyManaged", true);

		api.post("/studio/api/2/users").json(json).execute().status(HttpStatus.SC_BAD_REQUEST)
				.json("$.response.code", is(1001))
				.json("$.response.remedialAction", is("Check API and make sure you're sending the correct parameters"))
				.json("$.response.message", is("Invalid parameter(s)"));
	}

	public void testCreateUserUnAuthorized(String id, String userName) {
		Map<String, Object> json = new HashMap<>();
		json.put("id", id);
		json.put("username", userName);
		json.put("password", userName);
		json.put("firstName", "Test");
		json.put("lastName", "Test");
		json.put("email", "test@test.com");
		json.put("enabled", true);
		json.put("externallyManaged", true);

		api.post("/studio/api/2/users").json(json).execute().status(HttpStatus.SC_UNAUTHORIZED);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getUserIDForUserName(String userName) {
		String id = "";
		JsonResponse response = api.get("/studio/api/2/users").urlParam("offset", offSet)
				.urlParam("limit", limit).urlParam("sort", sort).execute();

		ObjectMapper mapper = new ObjectMapper();
		try {
			Map responseMap = mapper.readValue(response.getRaw(), Map.class);
			List<Map> users = (List<Map>) responseMap.get("users");
			id = String.valueOf(users.stream().filter(e -> e.get("username").equals(userName))
					.map(e -> e.get("id")).findFirst().get());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return id;
	}

	public void testUpdateUser(String id, String userName) {
		Map<String, Object> json = new HashMap<>();
		json.put("id", id);
		json.put("username", userName);
		json.put("password", userName);
		json.put("firstName", "TestUpdated");
		json.put("lastName", "Test");
		json.put("email", "test@test.com");
		json.put("enabled", true);
		json.put("externallyManaged", true);

		api.patch("/studio/api/2/users").json(json).execute().status(HttpStatus.SC_OK);
	}

	public void testUpdateUserResourceNotFound(String id, String userName) {
		Map<String, Object> json = new HashMap<>();
		json.put("id", id);
		json.put("username", userName);
		json.put("password", userName);
		json.put("firstName", "TestUpdated");
		json.put("lastName", "Test");
		json.put("email", "test@test.com");
		json.put("enabled", true);
		json.put("externallyManaged", true);

		api.patch("/studio/api/2/users").json(json).execute().status(HttpStatus.SC_NOT_FOUND);
	}

	public void testUpdateUserBadRequest(String id, String userName) {
		Map<String, Object> json = new HashMap<>();
		json.put("idnonvalid", id);
		json.put("username", userName);
		json.put("password", userName);
		json.put("firstName", "TestUpdated");
		json.put("lastName", "Test");
		json.put("email", "test@test.com");
		json.put("enabled", true);
		json.put("externallyManaged", true);

		api.post("/studio/api/2/users").json(json).execute().status(HttpStatus.SC_BAD_REQUEST)
				.json("$.response.code", is(1001))
				.json("$.response.remedialAction", is("Check API and make sure you're sending the correct parameters"))
				.json("$.response.message", is("Invalid parameter(s)"));
	}

	public void testUpdateUserUnAuthorized(String id, String userName) {
		Map<String, Object> json = new HashMap<>();
		json.put("id", id);
		json.put("username", userName);
		json.put("password", userName);
		json.put("firstName", "TestUpdated");
		json.put("lastName", "Test");
		json.put("email", "test@test.com");
		json.put("enabled", true);
		json.put("externallyManaged", true);

		api.patch("/studio/api/2/users").json(json).execute().status(HttpStatus.SC_UNAUTHORIZED);
	}

	public void testDeleteUserById(String userId) {
		api.delete("/studio/api/2/users").urlParam("id", userId).execute().status(HttpStatus.SC_OK);
	}

	public void testDeleteUserByIdBadRequest(String userId) {
		api.delete("/studio/api/2/users").urlParam("idnonvalid", userId).execute()
				.status(HttpStatus.SC_BAD_REQUEST)
				.json("$.response.code", is(1001))
				.json("$.response.remedialAction", is("Check API and make sure you're sending the correct parameters"))
				.json("$.response.message", is("Invalid parameter(s) : All parameters are empty or null"));
	}

	public void testDeleteUserByIdUnauthorized(String userId) {
		api.delete("/studio/api/2/users").urlParam("id", userId).execute().status(HttpStatus.SC_UNAUTHORIZED);
	}

	public void testDeleteUserByUserName(String userName) {
		api.delete("/studio/api/2/users").urlParam("username", userName).execute().status(HttpStatus.SC_OK);
	}

	public void testDeleteUserByUserNameBadRequest(String userName) {
		api.delete("/studio/api/2/users").urlParam("usernamenonvalid", userName).execute()
				.status(HttpStatus.SC_BAD_REQUEST)
				.json("$.response.code", is(1001))
				.json("$.response.remedialAction", is("Check API and make sure you're sending the correct parameters"))
				.json("$.response.message", is("Invalid parameter(s) : All parameters are empty or null"));
	}

	public void testDeleteUserByUserNameUnauthorized(String userName) {
		api.delete("/studio/api/2/users").urlParam("username", userName).execute()
				.status(HttpStatus.SC_UNAUTHORIZED);
	}

	public void testGetUserById(String id) {
		api.get("/studio/api/2/users/" + id).execute().status(HttpStatus.SC_OK);
	}

	public void testGetUserByIdResourceNotFound(String id) {
		api.get("/studio/api/2/users/" + id).execute().status(HttpStatus.SC_NOT_FOUND);
	}

	public void testGetUserByIdUnauthorized(String id) {
		api.get("/studio/api/2/users/" + id).execute().status(HttpStatus.SC_UNAUTHORIZED);
	}

	public void testGetUserSites(String id) {
		api.get("/studio/api/2/users/" + id + "/sites").execute().status(HttpStatus.SC_OK);
	}

	public void testGetUserSitesResourceNotFound(String id) {
		api.get("/studio/api/2/users/" + id + "/sites").execute().status(HttpStatus.SC_NOT_FOUND);
	}

	public void testGetUserSitesUnauthorized(String id) {
		api.get("/studio/api/2/users/" + id + "/sites").execute().status(HttpStatus.SC_UNAUTHORIZED);
	}

	public void testGetUserSiteRole(String id, String siteId) {
		api.get("/studio/api/2/users/" + id + "/sites/" + siteId + "/roles").execute()
				.status(HttpStatus.SC_OK);
	}

	public void testGetUserSiteRoleResourceNotFound(String id, String siteId) {
		api.get("/studio/api/2/users/" + id + "/sites/" + siteId + "/roles").execute()
				.status(HttpStatus.SC_NOT_FOUND);
	}


	public void testGetUserSiteRoleUnauthorized(String id, String siteId) {
		api.get("/studio/api/2/users/" + id + "/sites/" + siteId + "/roles").execute()
				.status(HttpStatus.SC_UNAUTHORIZED);
	}

	public void testUpdateUserEnableUserUsingUsername(String userName) {
		JSONArray usernames = new JSONArray();
		usernames.add(userName);

		JSONObject json = new JSONObject();
		json.put("usernames", usernames);

		api.patch("/studio/api/2/users/enable").json(json).execute().status(HttpStatus.SC_OK);
	}

	public void testUpdateUserEnableUserUsingUsernameBadRequest(String userName) {
		JSONArray usernames = new JSONArray();
		usernames.add(userName);

		JSONObject json = new JSONObject();
		json.put("usernamesnonvalid", usernames);

		api.patch("/studio/api/2/users/enable").json(json).execute().status(HttpStatus.SC_BAD_REQUEST)
				.json("$.response.code", is(1001))
				.json("$.response.remedialAction", is("Check API and make sure you're sending the correct parameters"))
				.json("$.response.message", is("Invalid parameter(s)"));
	}

	public void testUpdateUserEnableUserUsingUsernameUnauthorized(String userName) {
		JSONArray usernames = new JSONArray();
		usernames.add(userName);

		JSONObject json = new JSONObject();
		json.put("usernames", usernames);

		api.patch("/studio/api/2/users/enable").json(json).execute().status(HttpStatus.SC_UNAUTHORIZED);
	}

	public void testUpdateUserDisableUserUsingUsername(String userName) {
		JSONArray usernames = new JSONArray();
		usernames.add(userName);

		JSONObject json = new JSONObject();
		json.put("usernames", usernames);

		api.patch("/studio/api/2/users/disable").json(json).execute().status(HttpStatus.SC_OK);
	}

	public void testUpdateUserDisableUserUsingUsernameBadRequest(String userName) {
		JSONArray usernames = new JSONArray();
		usernames.add(userName);

		JSONObject json = new JSONObject();
		json.put("usernamesnonvalid", usernames);

		api.patch("/studio/api/2/users/disable").json(json).execute().status(HttpStatus.SC_BAD_REQUEST)
				.json("$.response.code", is(1001))
				.json("$.response.remedialAction", is("Check API and make sure you're sending the correct parameters"))
				.json("$.response.message", is("Invalid parameter(s)"));
	}

	public void testUpdateUserDisableUserUsingUsernameUnauthorized(String userName) {
		JSONArray usernames = new JSONArray();
		usernames.add(userName);

		JSONObject json = new JSONObject();
		json.put("usernames", usernames);

		api.patch("/studio/api/2/users/disable").json(json).execute().status(HttpStatus.SC_UNAUTHORIZED);
	}
}
