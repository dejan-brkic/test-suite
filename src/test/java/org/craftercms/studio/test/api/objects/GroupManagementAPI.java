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

package org.craftercms.studio.test.api.objects;

import static org.hamcrest.Matchers.is;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.craftercms.studio.test.utils.APIConnectionManager;
import org.craftercms.studio.test.utils.JsonTester;

/**
 * @author luishernandez
 *
 */
public class GroupManagementAPI extends BaseAPI {

	private String groupName1 = "contributors01";
	private String groupName2 = "contributors02";
	private String description = "description";

	public GroupManagementAPI(JsonTester api, APIConnectionManager apiConnectionManager) {
		super(api, apiConnectionManager);
	}

	public void testCreateStudioGroup01(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("group_name", groupName1);
		json.put("site_id", siteId);
		json.put("description", description);

		api.post("/studio/api/1/services/api/1/group/create.json").json(json).execute().status(HttpStatus.SC_CREATED)
				.header("Location", is(headerLocationBase
						+ "/studio/api/1/services/api/1/group/get.json?group_name=" + groupName1))
				.json("$.message", is("OK")).debug();
	}

	public void testCreateStudioGroup02(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("group_name", groupName2);
		json.put("site_id", siteId);
		json.put("description", description);

		api.post("/studio/api/1/services/api/1/group/create.json").json(json).execute().status(HttpStatus.SC_CREATED)
				.header("Location", is(headerLocationBase
						+ "/studio/api/1/services/api/1/group/get.json?group_name=" + groupName2))
				.json("$.message", is("OK")).debug();
	}

	public void testAddUserToGroup01(String newUserName, String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("username", newUserName);
		json.put("group_name", groupName1);
		json.put("site_id", siteId);

		api.post("/studio/api/1/services/api/1/group/add-user.json").json(json).execute().status(HttpStatus.SC_OK)
				.header("Location", is(headerLocationBase
						+ "/studio/api/1/services/api/1/group/get.json?group_name=" + groupName1))
				.json("$.message", is("OK")).debug();
	}

	public void testAddUserToGroup02(String newUserName, String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("username", newUserName);
		json.put("group_name", groupName2);
		json.put("site_id", siteId);

		api.post("/studio/api/1/services/api/1/group/add-user.json").json(json).execute().status(HttpStatus.SC_OK)
				.header("Location", is(headerLocationBase
						+ "/studio/api/1/services/api/1/group/get.json?group_name=" + groupName2))
				.json("$.message", is("OK")).debug();
	}

	public void testCreateStudioGroupInvalidParameters(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("group_nameInvalidParameter", groupName1);
		json.put("site_id", siteId);
		json.put("description", description);

		api.post("/studio/api/1/services/api/1/group/create.json").json(json).execute().status(HttpStatus.SC_BAD_REQUEST)
				.json("$.message", is("Invalid parameter(s): [group_name]")).debug();

	}

	public void testCreateStudioGroupAlreadyExists(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("group_name", groupName1);
		json.put("site_id", siteId);
		json.put("description", description);

		api.post("/studio/api/1/services/api/1/group/create.json").json(json).execute().status(HttpStatus.SC_CONFLICT)
				.header("Location", is(headerLocationBase
						+ "/studio/api/1/services/api/1/group/get.json?group_name=" + groupName1))
				.json("$.message", is("Group already exists")).debug();

	}

	public void testCreateStudioGroupSiteNotFound(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("group_name", groupName1);
		json.put("site_id", siteId + "nonvalid");
		json.put("description", description);

		api.post("/studio/api/1/services/api/1/group/create.json").json(json).execute().status(HttpStatus.SC_NOT_FOUND)
				.header("Location", is(headerLocationBase
						+ "/studio/api/1/services/api/1/group/get.json?group_name=" + groupName1))
				.json("$.message", is("Site not found")).debug();

	}

	public void testCreateStudioGroupUnauthorized(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("group_name", groupName1);
		json.put("site_id", siteId);
		json.put("description", description);

		api.post("/studio/api/1/services/api/1/group/create.json").json(json).execute().status(HttpStatus.SC_UNAUTHORIZED)
				.header("Location", is(headerLocationBase
						+ "/studio/api/1/services/api/1/group/get.json?group_name=" + groupName1))
				.debug();
	}

	public void testGetGroup(String siteId) {
		api.get("/studio/api/1/services/api/1/group/get.json").urlParam("group_name", groupName1)
				.urlParam("site_id", siteId).execute().status(HttpStatus.SC_OK).header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/get/get.json?site_id=" + siteId
								+ "&group_name=" + groupName1));

	}

	public void testGetGroupInvalidParameter(String siteId) {
		api.get("/studio/api/1/services/api/1/group/get.json").urlParam("group_name", groupName1)
				.urlParam("site_idnonvalid", siteId).execute().status(HttpStatus.SC_BAD_REQUEST)
				.json("$.message", is("Invalid parameter(s): [site_id]")).debug();
	}

	public void testGetGroupGroupNotFound(String siteId) {
		api.get("/studio/api/1/services/api/1/group/get.json").urlParam("group_name", groupName1 + "nonvalid")
				.urlParam("site_id", siteId).execute().status(HttpStatus.SC_NOT_FOUND).json("$.message", is("Group not found"))
				.debug();

	}

	public void testGetGroupSiteNotFound(String siteId) {
		api.get("/studio/api/1/services/api/1/group/get.json").urlParam("group_name", groupName1)
				.urlParam("site_id", siteId + "nonvalid").execute().status(HttpStatus.SC_NOT_FOUND)
				.json("$.message", is("Site not found")).debug();

	}

	public void testGetGroupUnauthorized(String siteId) {
		api.get("/studio/api/1/services/api/1/group/get.json").urlParam("group_name", groupName1)
				.urlParam("site_id", siteId).execute().status(HttpStatus.SC_UNAUTHORIZED).header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/get/get.json?site_id=" + siteId
								+ "&group_name=" + groupName1));

	}

	public void testGetGroups() {
		Map<String, Object> json = new HashMap<>();
		json.put("start", "0");
		json.put("number", "25");
		api.get("/studio/api/1/services/api/1/group/get-all.json").json(json).execute().status(HttpStatus.SC_OK).header(
				"Location",
				is(headerLocationBase + "/studio/api/1/services/api/1/group/get-all.json?start=0&number=25"));
	}

	public void testGetGroupsUnauthorized() {
		Map<String, Object> json = new HashMap<>();
		json.put("start", "0");
		json.put("number", "25");

		api.get("/studio/api/1/services/api/1/group/get-all.json").json(json).execute().status(HttpStatus.SC_UNAUTHORIZED).header(
				"Location",
				is(headerLocationBase + "/studio/api/1/services/api/1/group/get-all.json?start=0&number=25"));
	}

	public void testGetUsersPerGroup(String siteId) {
		api.get("/studio/api/1/services/api/1/group/users.json").urlParam("group_name", groupName1)
				.urlParam("site_id", siteId).execute().status(HttpStatus.SC_OK).header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/group/users.json?site_id="
								+ siteId + "&group_name=" + this.groupName1 + "&start=0&number=25"));
	}

	public void testGetUsersPerGroupInvalidParameters(String siteId) {
		api.get("/studio/api/1/services/api/1/group/users.json").urlParam("group_name", groupName1)
				.urlParam("site_idnonvalid", siteId).execute().status(HttpStatus.SC_BAD_REQUEST).debug();

	}

	public void testGetUsersPerGroupSiteNotFound(String siteId) {
		api.get("/studio/api/1/services/api/1/group/users.json").urlParam("group_name", groupName1)
				.urlParam("site_id", siteId + "nonvalid").execute().status(HttpStatus.SC_NOT_FOUND).debug();

	}

	public void testGetUsersPerGroupGroupNotFound(String siteId) {
		api.get("/studio/api/1/services/api/1/group/users.json")
				.urlParam("group_name", groupName1 + "nonvalid").urlParam("site_id", siteId).execute()
				.status(HttpStatus.SC_NOT_FOUND).debug();

	}

	public void testGetUsersPerGroupUnauthorized(String siteId) {
		api.get("/studio/api/1/services/api/1/group/users.json").urlParam("group_name", groupName1)
				.urlParam("site_id", siteId).execute().status(HttpStatus.SC_UNAUTHORIZED).header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/group/users.json?site_id="
								+ siteId + "&group_name=" + this.groupName1 + "&start=0&number=25"));
	}

	public void testGetGroupsPerSite(String siteId) {
		api.get("/studio/api/1/services/api/1/group/get-per-site.json").urlParam("site_id", siteId).execute()
				.status(HttpStatus.SC_OK).header("Location",
						is(headerLocationBase
								+ "/studio/api/1/services/api/1/group/get-per-site.json?site_id=" + siteId
								+ "&start=0&number=25"));
	}

	public void testGetGroupsPerSiteInvalidParameters(String siteId) {
		api.get("/studio/api/1/services/api/1/group/get-per-site.json").urlParam("site_idnonvalid", siteId)
				.urlParam("number", "10").urlParam("start", "0").execute().status(HttpStatus.SC_BAD_REQUEST).debug();
	}

	public void testGetGroupsPerSiteNotFound(String siteId) {
		api.get("/studio/api/1/services/api/1/group/get-per-site.json").urlParam("site_id", siteId+"nonvalid")
		.urlParam("number", "10").urlParam("start", "0").execute().status(HttpStatus.SC_NOT_FOUND).debug();
	}

	public void testGetGroupsPerSiteUnauthorized(String siteId) {
		api.get("/studio/api/1/services/api/1/group/get-per-site.json").urlParam("site_id", siteId).execute()
				.status(HttpStatus.SC_UNAUTHORIZED).header("Location",
						is(headerLocationBase
								+ "/studio/api/1/services/api/1/group/get-per-site.json?site_id=" + siteId
								+ "&start=0&number=25"));
	}

	public void testUpdateGroup(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("group_name", groupName1);
		json.put("site_id", siteId);
		json.put("description", description + "updated");

		api.post("/studio/api/1/services/api/1/group/update.json").json(json).execute().status(HttpStatus.SC_OK)
				.header("Location", is(headerLocationBase
						+ "/studio/api/1/services/api/1/group/get.json?group_name=" + groupName1))
				.json("$.message", is("OK")).debug();
	}

	public void testUpdateGroupInvalidParameters(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("group_namenonvalid", groupName1);
		json.put("site_id", siteId);
		json.put("description", description + "updated");

		api.post("/studio/api/1/services/api/1/group/update.json").json(json).execute().status(HttpStatus.SC_BAD_REQUEST)
				.json("$.message", is("Invalid parameter(s): [group_name]")).debug();
	}

	public void testUpdateGroupGroupNotFound(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("group_name", groupName1 + "nonvalid");
		json.put("site_id", siteId);
		json.put("description", description + "updated");

		api.post("/studio/api/1/services/api/1/group/update.json").json(json).execute().status(HttpStatus.SC_NOT_FOUND)
				.json("$.message", is("Group not found")).debug();
	}

	public void testUpdateGroupSiteNotFound(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("group_name", groupName1);
		json.put("site_id", siteId + "nonvalid");
		json.put("description", description + "updated");

		api.post("/studio/api/1/services/api/1/group/update.json").json(json).execute().status(HttpStatus.SC_NOT_FOUND)
				.json("$.message", is("Site not found")).debug();
	}

	public void testUpdateGroupUnauthorized(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("group_name", groupName1);
		json.put("site_id", siteId);
		json.put("description", description + "updated");

		api.post("/studio/api/1/services/api/1/group/update.json").json(json).execute().status(HttpStatus.SC_UNAUTHORIZED)
				.header("Location", is(headerLocationBase
						+ "/studio/api/1/services/api/1/group/get.json?group_name=" + groupName1))
				.debug();
	}

	public void testDeleteGroup(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("group_name", groupName1);
		json.put("site_id", siteId);
		api.post("/studio/api/1/services/api/1/group/delete.json").json(json).execute().status(HttpStatus.SC_NO_CONTENT);
	}

	public void testDeleteGroupInvalidParameters(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("group_namenonvalid", groupName1);
		json.put("site_id", siteId);

		api.post("/studio/api/1/services/api/1/group/delete.json").json(json).execute().status(HttpStatus.SC_BAD_REQUEST)
				.json("$.message", is("Invalid parameter(s): [group_name]")).debug();
	}

	public void testDeleteGroupGroupNotFound(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("group_name", groupName1 + "nonvalid");
		json.put("site_id", siteId);

		api.post("/studio/api/1/services/api/1/group/delete.json").json(json).execute().status(HttpStatus.SC_NOT_FOUND)
				.json("$.message", is("Group not found")).debug();

	}

	public void testDeleteGroupSiteNotFound(String siteId) {
		Map<String, Object> json = new HashMap<>();

		json.put("group_name", groupName1);
		json.put("site_id", siteId + "nonvalid");

		api.post("/studio/api/1/services/api/1/group/delete.json").json(json).execute().status(HttpStatus.SC_NOT_FOUND)
				.json("$.message", is("Site not found")).debug();

	}

	public void testDeleteGroupUnauthorized(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("group_name", groupName1);
		json.put("site_id", siteId);
		api.post("/studio/api/1/services/api/1/group/delete.json").json(json).execute().status(HttpStatus.SC_UNAUTHORIZED);
	}

	public void testAddUserToGroup(String username, String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("username", username);
		json.put("group_name", groupName1);
		json.put("site_id", siteId);
		api.post("/studio/api/1/services/api/1/group/add-user.json").json(json).execute().status(HttpStatus.SC_OK)
				.header("Location", is(headerLocationBase
						+ "/studio/api/1/services/api/1/group/get.json?group_name=" + groupName1))
				.json("$.message", is("OK")).debug();
	}

	public void testAddUserToGroupInvalidParameters(String username, String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("usernameInvalid", username);
		json.put("group_name", groupName1);
		json.put("site_id", siteId);
		api.post("/studio/api/1/services/api/1/group/add-user.json").json(json).execute().status(HttpStatus.SC_BAD_REQUEST)
				.json("$.message", is("Invalid parameter(s): [username]")).debug();

	}

	public void testAddUserToGroupUserNotFound(String username, String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("username", username + "non-valid");
		json.put("group_name", groupName1);
		json.put("site_id", siteId);
		api.post("/studio/api/1/services/api/1/group/add-user.json").json(json).execute().status(HttpStatus.SC_NOT_FOUND)
				.json("$.message", is("User not found")).debug();

	}

	public void testAddUserToGroupGroupNotFound(String username, String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("username", username);
		json.put("group_name", groupName1 + "non-valid");
		json.put("site_id", siteId);
		api.post("/studio/api/1/services/api/1/group/add-user.json").json(json).execute().status(HttpStatus.SC_NOT_FOUND)
				.json("$.message", is("Group not found")).debug();

	}

	public void testAddUserToGroupSiteNotFound(String username, String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("username", username);
		json.put("group_name", groupName1);
		json.put("site_id", siteId + "non-valid");
		api.post("/studio/api/1/services/api/1/group/add-user.json").json(json).execute().status(HttpStatus.SC_NOT_FOUND)
				.json("$.message", is("Site not found")).debug();

	}

	public void testAddUserToGroupAlreadyInGroup(String username, String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("username", username);
		json.put("group_name", groupName1);
		json.put("site_id", siteId);

		api.post("/studio/api/1/services/api/1/group/add-user.json").json(json).execute().status(HttpStatus.SC_CONFLICT)
				.header("Location", is(headerLocationBase
						+ "/studio/api/1/services/api/1/group/get.json?group_name=" + groupName1))
				.json("$.message", is("User already in group")).debug();
	}

	public void testAddUserToGroupUnauthorized(String newUserName, String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("username", newUserName);
		json.put("group_name", groupName1);
		json.put("site_id", siteId);

		api.post("/studio/api/1/services/api/1/group/add-user.json").json(json).execute().status(HttpStatus.SC_UNAUTHORIZED)
				.header("Location", is(headerLocationBase
						+ "/studio/api/1/services/api/1/group/get.json?group_name=" + groupName1))
				.debug();
	}

	public void testRemoveUserFromGroup(String username, String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("username", username);
		json.put("group_name", groupName1);
		json.put("site_id", siteId);

		api.post("/studio/api/1/services/api/1/group/remove-user.json").json(json).execute().status(HttpStatus.SC_NO_CONTENT);
	}

	public void testRemoveUserFromGroupInvalidParameters(String username, String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("usernamenonvalid", username);
		json.put("group_namenonvalid", groupName1);
		json.put("site_idnonvalid", siteId);

		api.post("/studio/api/1/services/api/1/group/remove-user.json").json(json).execute().status(HttpStatus.SC_BAD_REQUEST)
				.json("$.message", is("Invalid parameter(s): [group_name, site_id, username]"));
	}

	public void testRemoveUserFromGroupGroupNotFound(String username, String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("username", username);
		json.put("group_name", groupName1 + "nonvalid");
		json.put("site_id", siteId);

		api.post("/studio/api/1/services/api/1/group/remove-user.json").json(json).execute().status(HttpStatus.SC_NOT_FOUND)
				.json("$.message", is("Group not found"));

	}

	public void testRemoveUserFromGroupUserNotFound(String username, String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("username", username + "nonvalid");
		json.put("group_name", groupName1);
		json.put("site_id", siteId);

		api.post("/studio/api/1/services/api/1/group/remove-user.json").json(json).execute().status(HttpStatus.SC_NOT_FOUND)
				.json("$.message", is("User not found"));

	}

	public void testRemoveUserFromGroupSiteNotFound(String username, String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("username", username);
		json.put("group_name", groupName1);
		json.put("site_id", siteId + "nonvalid");

		api.post("/studio/api/1/services/api/1/group/remove-user.json").json(json).execute().status(HttpStatus.SC_NOT_FOUND)
				.json("$.message", is("Site not found"));

	}

	public void testRemoveUserFromGroupUnauthorized(String username, String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("username", username);
		json.put("group_name", groupName1);
		json.put("site_id", siteId);

		api.post("/studio/api/1/services/api/1/group/remove-user.json").json(json).execute().status(HttpStatus.SC_UNAUTHORIZED);
	}
}
