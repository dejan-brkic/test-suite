/*
 * Copyright (C) 2007-2018 Crafter Software Corporation. All Rights Reserved.
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

/**
 * @author luishernandez
 *
 */
public class UsersManagementAPI2 extends BaseAPI {

	public UsersManagementAPI2(JsonTester api, APIConnectionManager apiConnectionManager) {
		super(api, apiConnectionManager);
	}

	public void testGetAllUsersNonSiteId() {
		api.get("/studio/api/2/users").urlParam("offset", "0").urlParam("limit", "10").urlParam("sort", "asc")
				.execute().status(200);
	}

	public void testGetAllUsersNonSiteIdNotFound() {
		api.get("/studio/api/2/users" + "nonvalid").urlParam("offset", "0").urlParam("limit", "10")
				.urlParam("sort", "asc").execute().status(404);
	}

	public void testGetAllUsersNonSiteIdBadRequest() {
		api.get("/studio/api/2/users").urlParam("offset", "noninteger").urlParam("limit", "10")
				.urlParam("sort", "asc").execute().status(400);
	}

	public void testGetAllUsersNonSiteIdNonAuthorized() {
		api.get("/studio/api/2/users").urlParam("offset", "0").urlParam("limit", "10").urlParam("sort", "asc")
				.execute().status(401);
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

		api.post("/studio/api/2/users").json(json).execute().status(200);
	}

	public void testCreateUserAlreadyExists(String id, String userName) {
		Map<String, Object> json = new HashMap<>();
		json.put("id", id);
		json.put("username", userName);
		json.put("password", userName);
		json.put("firstName", "Test");
		json.put("lastName", "Test");
		json.put("email", "test@test.com");
		json.put("enabled", true);
		json.put("externallyManaged", true);

		api.post("/studio/api/2/users").json(json).execute().status(409);
	}

	public void testCreateUserNotFound(String id, String userName) {
		Map<String, Object> json = new HashMap<>();
		json.put("id", id);
		json.put("username", userName);
		json.put("password", userName);
		json.put("firstName", "Test");
		json.put("lastName", "Test");
		json.put("email", "test@test.com");
		json.put("enabled", true);
		json.put("externallyManaged", true);

		api.post("/studio/api/2/users" + "nonvalid").json(json).execute().status(404);
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

		api.post("/studio/api/2/users").json(json).execute().status(400);
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

		api.post("/studio/api/2/users").json(json).execute().status(401);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getUserIDForUserName(String userName) {
		String id = "";
		JsonResponse response = api.get("/studio/api/2/users").urlParam("offset", "0")
				.urlParam("limit", "1000").urlParam("sort", "asc").execute();

		ObjectMapper mapper = new ObjectMapper();
		try {
			Map responseMap = mapper.readValue(response.getRaw(), Map.class);
			Map resultMap = (Map) responseMap.get("result");
			List<Map> entities = (List<Map>) resultMap.get("entities");

			id = String.valueOf(entities.stream().filter(e -> e.get("username").equals(userName))
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

		api.patch("/studio/api/2/users").json(json).execute().status(200);
	}

	public void testUpdateUserNotFound(String id, String userName) {
		Map<String, Object> json = new HashMap<>();
		json.put("id", id);
		json.put("username", userName);
		json.put("password", userName);
		json.put("firstName", "TestUpdated");
		json.put("lastName", "Test");
		json.put("email", "test@test.com");
		json.put("enabled", true);
		json.put("externallyManaged", true);

		api.patch("/studio/api/2/users" + "nonvalid").json(json).execute().status(404);

		// it return 405 method not allowed
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

		api.post("/studio/api/2/users").json(json).execute().status(400);
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

		api.patch("/studio/api/2/users").json(json).execute().status(401);
	}

	public void testDeleteUserById(String userId) {
		api.delete("/studio/api/2/users").urlParam("id", userId).execute().status(200);
	}

	public void testDeleteUserByIdNotFound(String userId) {
		api.delete("/studio/api/2/users" + "nonvalid").urlParam("id", userId).execute().status(404);
		// it returns 405
	}

	public void testDeleteUserByIdBadRequest(String userId) {
		api.delete("/studio/api/2/users").urlParam("idnonvalid", userId).execute().status(400);
	}

	public void testDeleteUserByIdUnauthorized(String userId) {
		api.delete("/studio/api/2/users").urlParam("id", userId).execute().status(401);
	}

	public void testDeleteUserByUserName(String userName) {
		api.delete("/studio/api/2/users").urlParam("username", userName).execute().status(200);
	}

	public void testDeleteUserByUserNameNotFound(String userName) {
		api.delete("/studio/api/2/users" + "nonvalid").urlParam("username", userName).execute().status(404);
		// it returns 405
	}

	public void testDeleteUserByUserNameBadRequest(String userName) {
		api.delete("/studio/api/2/users").urlParam("usernamenonvalid", userName).execute().status(400);
	}

	public void testDeleteUserByUserNameUnauthorized(String userName) {
		api.delete("/studio/api/2/users").urlParam("username", userName).execute().status(401);
	}

	public void testGetUserById(String id) {
		api.get("/studio/api/2/users"+"/"+id).execute().status(200);
	}
	
	public void testGetUserByIdNotFound(String id) {
		api.get("/studio/api/2/users"+"nonvalid/"+id).execute().status(404);
	}
	
	public void testGetUserByIdBadRequest(String id) {
		api.get("/studio/api/2/users"+"/"+id+"nonvalid").execute().status(400);
		//it returns 200 everytime when id contains valid id into string
	}
	
	public void testGetUserByIdUnauthorized(String id) {
		api.get("/studio/api/2/users"+"/"+id).execute().status(401);
	}
	
	public void testGetUserSites(String id) {
		api.get("/studio/api/2/users"+"/"+id).execute().status(200);
	}
	
	public void testGetUserSitesNotFound(String id) {
		api.get("/studio/api/2/users"+"nonvalid/"+id).execute().status(404);
	}
	
	public void testGetUserSitesBadRequest(String id) {
		api.get("/studio/api/2/users"+"/"+id+"nonvalid").execute().status(400);
		//it returns 200 everytime when id contains valid id into string
	}
	
	public void testGetUserSitesUnauthorized(String id) {
		api.get("/studio/api/2/users"+"/"+id).execute().status(401);
	}
	
	public void testGetCurrentAuthenticatedUser() {
		api.get("/studio/api/2/user").execute().status(200);
	}
	
	public void testGetCurrentAuthenticatedUserNotFound() {
		api.get("/studio/api/2/user"+"nonvalid").execute().status(404);
	}
	
	public void testGetCurrentAuthenticatedUserUnauthorized() {
		api.get("/studio/api/2/user").execute().status(401);
	}
	
	public void testUpdateUserEnableUserUsingUsername(String userName) {
		JSONArray usernames = new JSONArray();
		usernames.add(userName);

		JSONObject json = new JSONObject();
		json.put("usernames", usernames);
		
		api.patch("/studio/api/2/users/enable").json(json).execute().status(200);
	}
	
	public void testUpdateUserEnableUserUsingUsernameNotFound(String userName) {
		JSONArray usernames = new JSONArray();
		usernames.add(userName);

		JSONObject json = new JSONObject();
		json.put("usernamesnonvalid", usernames);
		
		api.patch("/studio/api/2/users/enable"+"nonvalid").json(json).execute().status(404);
		//it returns 405
	}
	
	public void testUpdateUserEnableUserUsingUsernameBadRequest(String userName) {
		JSONArray usernames = new JSONArray();
		usernames.add(userName);

		JSONObject json = new JSONObject();
		json.put("usernamesnonvalid", usernames);
		
		api.patch("/studio/api/2/users/enable").json(json).execute().status(400);
	}
	public void testUpdateUserEnableUserUsingUsernameUnauthorized(String userName) {
		JSONArray usernames = new JSONArray();
		usernames.add(userName);

		JSONObject json = new JSONObject();
		json.put("usernames", usernames);
		
		api.patch("/studio/api/2/users/enable").json(json).execute().status(401);
	}
	
	public void testUpdateUserDisableUserUsingUsername(String userName) {
		JSONArray usernames = new JSONArray();
		usernames.add(userName);

		JSONObject json = new JSONObject();
		json.put("usernames", usernames);
		
		api.patch("/studio/api/2/users/disable").json(json).execute().status(200);
	}
	
	public void testUpdateUserDisableUserUsingUsernameNotFound(String userName) {
		JSONArray usernames = new JSONArray();
		usernames.add(userName);

		JSONObject json = new JSONObject();
		json.put("usernamesnonvalid", usernames);
		
		api.patch("/studio/api/2/users/disable"+"nonvalid").json(json).execute().status(404);
		//it returns 405
	}
	
	public void testUpdateUserDisableUserUsingUsernameBadRequest(String userName) {
		JSONArray usernames = new JSONArray();
		usernames.add(userName);

		JSONObject json = new JSONObject();
		json.put("usernamesnonvalid", usernames);
		
		api.patch("/studio/api/2/users/disable").json(json).execute().status(400);
	}
	public void testUpdateUserDisableUserUsingUsernameUnauthorized(String userName) {
		JSONArray usernames = new JSONArray();
		usernames.add(userName);

		JSONObject json = new JSONObject();
		json.put("usernames", usernames);
		
		api.patch("/studio/api/2/users/disable").json(json).execute().status(401);
	}
}
