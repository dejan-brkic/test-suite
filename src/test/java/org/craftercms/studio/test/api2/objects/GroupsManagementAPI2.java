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

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.craftercms.studio.test.api.objects.BaseAPI;
import org.craftercms.studio.test.utils.APIConnectionManager;
import org.craftercms.studio.test.utils.JsonResponse;
import org.craftercms.studio.test.utils.JsonTester;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

/**
 * @author luishernandez
 *
 */
public class GroupsManagementAPI2 extends BaseAPI {

	public GroupsManagementAPI2(JsonTester api, APIConnectionManager apiConnectionManager) {
		super(api, apiConnectionManager);
	}

	public void testGetAllGroups() {
		api.get("/studio/api/2/groups").urlParam("offset", "0").urlParam("limit", "10")
				.urlParam("sort", "asc").execute().status(200);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getGroupIDForGroupName(String groupName) {
		String id = "";
		JsonResponse response = api.get("/studio/api/2/groups").urlParam("offset", "0")
				.urlParam("limit", "1000").urlParam("sort", "asc").execute();

		ObjectMapper mapper = new ObjectMapper();
		try {
			Map responseMap = mapper.readValue(response.getRaw(), Map.class);
			Map resultMap = (Map) responseMap.get("result");
			List<Map> entities = (List<Map>) resultMap.get("entities");

			id = String.valueOf(entities.stream().filter(e -> e.get("name").equals(groupName)).map(e -> e.get("id"))
					.findFirst().get());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return id;
	}

	public void testGetAllGroupsNotFound() {
		api.get("/studio/api/2/groups" + "nonvalid").urlParam("offset", "0").urlParam("limit", "10")
				.urlParam("sort", "asc").execute().status(404);
	}

	public void testGetAllGroupsBadRequest() {
		api.get("/studio/api/2/groups").urlParam("offset", "noninteger").urlParam("limit", "10")
				.urlParam("sort", "asc").execute().status(400);
	}

	public void testGetAllGroupsNonAuthorized() {
		api.get("/studio/api/2/groups").urlParam("offset", "0").urlParam("limit", "10")
				.urlParam("sort", "asc").execute().status(401);
	}

	public void testCreateGroups(String groupName) {
		Map<String, Object> json = new HashMap<>();
		json.put("id", 0);
		json.put("name", groupName);
		json.put("desc", "Testing Group API2");

		api.post("/studio/api/2/groups").json(json).execute().status(200);
	}

	public void testCreateGroupsWithGivenID(String id, String groupName) {
		Map<String, Object> json = new HashMap<>();
		json.put("id", id);
		json.put("name", groupName);
		json.put("desc", "Testing Group API2 with given id");

		api.post("/studio/api/2/groups").json(json).execute().status(201);
	}

	public void testCreateGroupsNotFound() {
		Map<String, Object> json = new HashMap<>();
		json.put("id", 0);
		json.put("name", "Test_Groups");
		json.put("desc", "Testing Group API2");

		api.post("/studio/api/2/groups" + "nonvalid").json(json).execute().status(404);
	}

	public void testCreateGroupsBadRequest() {
		Map<String, Object> json = new HashMap<>();
		json.put("idnonvalid", 0);
		json.put("name", "Test_Groups");
		json.put("desc", "Testing Group API2");

		api.post("/studio/api/2/groups").json(json).execute().status(400);
	}

	public void testCreateGroupsUnauthorized() {
		Map<String, Object> json = new HashMap<>();
		json.put("id", 0);
		json.put("name", "Test_Groups");
		json.put("desc", "Testing Group API2");

		api.post("/studio/api/2/groups").json(json).execute().status(401);
	}

	public void testDeleteGroups(String id) {
		api.delete("/studio/api/2/groups").urlParam("id", id).execute().status(200);
	}

	public void testDeleteGroupsNotFound(String id) {
		api.delete("/studio/api/2/groups" + "nonvalid").urlParam("id", id).execute().status(405);
	}

	public void testDeleteGroupsBadRequest(String id) {
		api.delete("/studio/api/2/groups").urlParam("idnonvalid", id).execute().status(400);
	}

	public void testDeleteGroupsUnauthorized(String id) {
		api.delete("/studio/api/2/groups").urlParam("idnonvalid", id).execute().status(401);
	}

	public void testUpdateGroups(String id, String groupName) {
		Map<String, Object> json = new HashMap<>();
		json.put("id", id);
		json.put("name", groupName);
		json.put("desc", "Testing Group API2 updated");

		api.patch("/studio/api/2/groups").json(json).execute().status(200);
	}

	public void testUpdateGroupsNotAllowed(String id, String groupName) {
		Map<String, Object> json = new HashMap<>();
		json.put("id", id);
		json.put("name", groupName);
		json.put("desc", "Testing Group API2 updated");

		api.patch("/studio/api/2/groups" + "nonvalid").json(json).execute().status(405);
	}

	public void testUpdateGroupsBadRequest(String id, String groupName) {
		Map<String, Object> json = new HashMap<>();
		json.put("idnonvalid", id);
		json.put("name", groupName);
		json.put("desc", "Testing Group API2 updated");

		api.patch("/studio/api/2/groups").json(json).execute().status(400);
	}

	public void testUpdateGroupsUnauthorized(String id, String groupName) {
		Map<String, Object> json = new HashMap<>();
		json.put("id", id);
		json.put("name", groupName);
		json.put("desc", "Testing Group API2 updated");

		api.patch("/studio/api/2/groups").json(json).execute().status(401);
	}

	public void testGetGroupsByID(String id) {
		api.get("/studio/api/2/groups/" + id).execute().status(200);
	}

	public void testGetGroupsByIDNotFound(String id) {
		api.get("/studio/api/2/groupsnonvalid/" + id).execute().status(404);
	}

	public void testGetGroupsByIDBadRequest(String id) {
		api.get("/studio/api/2/groups/" + id + "nonvalid").execute().status(400);
	}

	public void testGetGroupsByIDNonAuthorized(String id) {
		api.get("/studio/api/2/groups/" + id).execute().status(401);
	}

	public void testGetGroupsMembers(String id) {
		api.get("/studio/api/2/groups/" + id + "/members").execute().status(200);
	}

	public void testGetGroupsMembersNotFound(String id) {
		api.get("/studio/api/2/groups/" + id + "/members" + "nonvalid").execute().status(404);
	}

	public void testGetGroupsMembersBadRequest(String id) {
		api.get("/studio/api/2/groups/" + id + "nonvalid" + "/members").execute().status(400);
	}

	public void testGetGroupsMembersNonAuthorized(String id) {
		api.get("/studio/api/2/groups/" + id + "/members").execute().status(401);
	}

	public void testAddMemberToGroupUsingUsername(String groupId, String userName) {

		JSONArray usernames = new JSONArray();
		usernames.add(userName);

		JSONObject json = new JSONObject();
		json.put("usernames", usernames);

		api.post("/studio/api/2/groups/" + groupId + "/members").json(json).execute().status(200);
	}
	
	public void testAddMemberToGroupUsingUsernameNonAuthorized(String groupId, String userName) {

		JSONArray usernames = new JSONArray();
		usernames.add(userName);

		JSONObject json = new JSONObject();
		json.put("usernames", usernames);

		api.post("/studio/api/2/groups/" + groupId + "/members").json(json).execute().status(401);
	}
	
	public void testAddMemberToGroupUsingUsernameBadRequest(String groupId, String userName) {

		JSONArray usernames = new JSONArray();
		usernames.add(userName);

		JSONObject json = new JSONObject();
		json.put("usernamesnonvalid", usernames);

		api.post("/studio/api/2/groups/" + groupId + "/members").json(json).execute().status(400);
	}
	
	public void testAddMemberToGroupUsingUsernameNotFound(String groupId, String userName) {

		JSONArray usernames = new JSONArray();
		usernames.add(userName);

		JSONObject json = new JSONObject();
		json.put("usernamesnonvalid", usernames);

		api.post("/studio/api/2/groups/" + groupId + "/members"+"nonvalid").json(json).execute().status(404);
	}
	
	public void testRemoveMemberFromGroupUsingUsername(String groupId, String userName) {
		api.delete("/studio/api/2/groups/" + groupId + "/members").urlParam("username",userName).execute().status(200);
	}
	
	public void testRemoveMemberFromGroupUsingUsernameNonAuthorized(String groupId, String userName) {
		api.delete("/studio/api/2/groups/" + groupId + "/members").urlParam("username",userName).execute().status(401);
	}
	
	public void testRemoveMemberFromGroupUsingUsernameBadRequest(String groupId, String userName) {
		api.delete("/studio/api/2/groups/" + groupId + "/members").urlParam("username",userName+"nonvalid").execute().status(404);
	}
	
	public void testRemoveMemberFromUsingUsernameNotFound(String groupId, String userName) {
		api.delete("/studio/api/2/groups/" + groupId + "/members"+"nonvalid").urlParam("username",userName).execute().status(405);
	}
	
}
