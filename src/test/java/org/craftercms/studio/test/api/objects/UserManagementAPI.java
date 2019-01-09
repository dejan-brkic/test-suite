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

public class UserManagementAPI extends BaseAPI {

	private String newusername = "jane.doe";
	private String newpassword = "SuperSecretPassword123#";
	private String first_name = "Jane";
	private String last_name = "Doe";
	private String email = "jane@example.com";
	private String token = null;

	public UserManagementAPI(JsonTester api, APIConnectionManager apiConnectionManager) {
		super(api, apiConnectionManager);
	}

	public void testCreateUser() {
		Map<String, Object> json = new HashMap<>();
		json.put("username", newusername);
		json.put("password", newpassword);
		json.put("first_name", first_name);
		json.put("last_name", last_name);
		json.put("email", email);

		api.post("/studio/api/1/services/api/1/user/create.json").json(json).execute().status(HttpStatus.SC_CREATED)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/user/get.json?user=" + newusername))
				.json("$.message", is("OK")).debug();
		
	}

	public void testCreateUserUserExist() {
		Map<String, Object> json = new HashMap<>();
		json.put("username", newusername);
		json.put("password", newpassword);
		json.put("first_name", first_name);
		json.put("last_name", last_name);
		json.put("email", email);

		api.post("/studio/api/1/services/api/1/user/create.json").json(json).execute().status(HttpStatus.SC_CONFLICT)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/user/get.json?user=" + newusername))
				.json("$.message", is("User already exists")).debug();

	}

	public void testCreateUserInvalidParameters() {
		Map<String, Object> json = new HashMap<>();
		json.put("usernamenonvalid", newusername);
		json.put("passwordnonvalid", newpassword);
		json.put("first_namenonvalid", first_name);
		json.put("last_namenonvalid", last_name);
		json.put("emailnonvalid", email);

		api.post("/studio/api/1/services/api/1/user/create.json").json(json).execute().status(HttpStatus.SC_BAD_REQUEST)
				.json("$.message", is("Invalid parameter(s): [username, password, firstname, lastname, email]"))
				.debug();

	}

	public void testCreateUserUnauthorized() {
		Map<String, Object> json = new HashMap<>();
		json.put("username", newusername);
		json.put("password", newpassword);
		json.put("first_name", first_name);
		json.put("last_name", last_name);
		json.put("email", email);

		api.post("/studio/api/1/services/api/1/user/create.json").json(json).execute().status(HttpStatus.SC_UNAUTHORIZED)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/user/get.json?user=" + newusername))
				.debug();
		
	}
	
	public void testDeleteUser() {
		Map<String, Object> json = new HashMap<>();
		json.put("username", newusername);
		api.post("/studio/api/1/services/api/1/user/delete.json").json(json).execute().status(HttpStatus.SC_NO_CONTENT);
	}

	public void testDeleteUserInvalidParameters() {
		Map<String, Object> json = new HashMap<>();
		json.put("usernamenonvalid", newusername);
		api.post("/studio/api/1/services/api/1/user/delete.json").json(json).execute().status(HttpStatus.SC_BAD_REQUEST).json("$.message",
				is("Invalid parameter: username"));

	}

	public void testDeleteUserUserNotFound() {
		Map<String, Object> json = new HashMap<>();
		json.put("username", newusername + "nonvalid");

		api.post("/studio/api/1/services/api/1/user/delete.json").json(json).execute().status(HttpStatus.SC_NOT_FOUND).json("$.message",
				is("User not found"));
	}

	public void testDeleteUserUnauthorized() {
		Map<String, Object> json = new HashMap<>();
		json.put("username", newusername);
		api.post("/studio/api/1/services/api/1/user/delete.json").json(json).execute().status(HttpStatus.SC_UNAUTHORIZED);
	}
	
	public void testGetUser() {
		api.get("/studio/api/1/services/api/1/user/get.json").urlParam("username", newusername).execute().status(HttpStatus.SC_OK)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/user/get.json?username=" + newusername));
	}
	
	public void testGetUserUnauthorized() {
		api.get("/studio/api/1/services/api/1/user/get.json").urlParam("username", newusername).execute().status(HttpStatus.SC_UNAUTHORIZED)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/user/get.json?username=" + newusername));
	}

	public void testGetUserInvalidParameters() {
		api.get("/studio/api/1/services/api/1/user/get.json").urlParam("usernamenonvalid", newusername).execute()
				.status(HttpStatus.SC_BAD_REQUEST)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/user/get.json?username=" + newusername))
				.json("$.message", is("Invalid parameter: username"));
	}

	public void testGetUserUserNotFound() {
		api.get("/studio/api/1/services/api/1/user/get.json").urlParam("username", "notfound").execute()
				.status(HttpStatus.SC_NOT_FOUND);
	}

	public void testGetUsers() {
		api.get("/studio/api/1/services/api/1/user/get-all.json").urlParam("start", "0").urlParam("number", "25")
				.execute().status(HttpStatus.SC_OK).header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/user/get-all.json?start=0&number=25"));
	}

	public void testGetUsersUnauthorized() {
		api.get("/studio/api/1/services/api/1/user/get-all.json").urlParam("start", "0").urlParam("number", "25")
				.execute().status(HttpStatus.SC_UNAUTHORIZED).header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/user/get-all.json?start=0&number=25"));
	}
	
	public void testGetUsersPerSite(String siteId) {
		api.get("/studio/api/1/services/api/1/user/get-per-site.json").urlParam("site_id", siteId).execute().status(HttpStatus.SC_OK)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/user/get-per-site.json?site_id=" + siteId
								+ "&start=0&number=25"));

	}
	
	public void testGetUsersPerSiteInvalidParameters() {
		api.get("/studio/api/1/services/api/1/user/get-per-site.json").urlParam("site_idinvalid", "").execute().status(HttpStatus.SC_BAD_REQUEST)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/user/get-per-site.json?site_id=&start=0&number=25"));

	}
	
	public void testGetUsersPerSiteNotFound() {
	
		api.get("/studio/api/1/services/api/1/user/get-per-site.json").urlParam("site_id", "invalid").execute().status(HttpStatus.SC_NOT_FOUND)
		.header("Location",
				is(headerLocationBase + "/studio/api/1/services/api/1/user/get-per-site.json?site_id=invalid&start=0&number=25"));

	}
	
	public void testGetUsersPerSiteUnauthorized() {
		
		api.get("/studio/api/1/services/api/1/user/get-per-site.json").urlParam("site_id", "").execute().status(HttpStatus.SC_UNAUTHORIZED)
		.header("Location",
				is(headerLocationBase + "/studio/api/1/services/api/1/user/get-per-site.json?site_id=invalid&start=0&number=25"));

	}

	public void testUpdateUser() {
		Map<String, Object> json = new HashMap<>();
		json.put("username", newusername);
		json.put("password", newpassword);
		json.put("first_name", first_name);
		json.put("last_name", last_name);
		json.put("email", email);
		json.put("externally_managed", "false");
		api.post("/studio/api/1/services/api/1/user/update.json").json(json).execute().status(HttpStatus.SC_OK)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/user/get.json?username=" + newusername))
				.json("$.message", is("OK")).debug();
	}
	
	public void testUpdateUserInvalidParameters() {
		Map<String, Object> json = new HashMap<>();
		json.put("usernamenonvalid", newusername);
		json.put("passwordnonvalid", newpassword);
		json.put("first_namenonvalid", first_name);
		json.put("last_namenonvalid", last_name);
		json.put("emailnonvalid", email);
		json.put("externally_managednonvalid", "false");

		api.post("/studio/api/1/services/api/1/user/update.json").json(json).execute().status(HttpStatus.SC_BAD_REQUEST)
				.json("$.message", is("Invalid parameter: username")).debug();

	}
	
	public void testUpdateUserToExternallyManaged() {
		Map<String, Object> json = new HashMap<>();
		json.put("username", newusername);
		json.put("password", newpassword);
		json.put("first_name", first_name);
		json.put("last_name", last_name);
		json.put("email", email);
		json.put("externally_managed", "true");
		api.post("/studio/api/1/services/api/1/user/update.json").json(json).execute().status(HttpStatus.SC_OK)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/user/get.json?username=" + newusername))
				.json("$.message", is("OK")).debug();
	}
	
	public void testUpdateUserUserNotFound() {
		Map<String, Object> json = new HashMap<>();
		json.put("username", newusername + "nonvalid");
		json.put("password", newpassword);
		json.put("first_name", first_name);
		json.put("last_name", last_name);
		json.put("email", email);
		json.put("externally_managed", "false");

		api.post("/studio/api/1/services/api/1/user/update.json").json(json).execute().status(HttpStatus.SC_NOT_FOUND)
				.json("$.message", is("User not found")).debug();

	}
	
	public void testUpdateUserUnauthorized() {
		Map<String, Object> json = new HashMap<>();
		json.put("username", newusername);
		json.put("password", newpassword);
		json.put("first_name", first_name);
		json.put("last_name", last_name);
		json.put("email", email);
		json.put("externally_managed", "false");
		api.post("/studio/api/1/services/api/1/user/update.json").json(json).execute().status(HttpStatus.SC_UNAUTHORIZED)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/user/get.json?username=" + newusername))
				.debug();
	}

	public void testEnableUser() {
		Map<String, Object> json = new HashMap<>();
		json.put("username", newusername);

		api.post("/studio/api/1/services/api/1/user/enable.json").json(json).execute().status(HttpStatus.SC_OK)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/user/get.json?username=" + newusername))
				.json("$.message", is("OK")).debug();
	}

	public void testEnableUserInvalidParameters() {
		Map<String, Object> json = new HashMap<>();
		json.put("usernamenonvalid", newusername);

		api.post("/studio/api/1/services/api/1/user/enable.json").json(json).execute().status(HttpStatus.SC_BAD_REQUEST)
				.json("$.message", is("Invalid parameter: username")).debug();

	}

	public void testEnableUserUserNotFound() {
		Map<String, Object> json = new HashMap<>();
		json.put("username", newusername + "nonvalid");

		api.post("/studio/api/1/services/api/1/user/enable.json").json(json).execute().status(HttpStatus.SC_NOT_FOUND)
				.json("$.message", is("User not found")).debug();

	}
	
	public void testEnableUserUnauthorized() {
		Map<String, Object> json = new HashMap<>();
		json.put("username", newusername);

		api.post("/studio/api/1/services/api/1/user/enable.json").json(json).execute().status(HttpStatus.SC_UNAUTHORIZED)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/user/get.json?username=" + newusername))
				.debug();
	}

	public void testDisableUser() {
		Map<String, Object> json = new HashMap<>();
		json.put("username", newusername);

		api.post("/studio/api/1/services/api/1/user/disable.json").json(json).execute().status(HttpStatus.SC_OK)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/user/get.json?username=" + newusername))
				.json("$.message", is("OK"));

	}

	public void testDisableUserInvalidParameters() {
		Map<String, Object> json = new HashMap<>();
		json.put("usernamenonvalid", newusername);

		api.post("/studio/api/1/services/api/1/user/disable.json").json(json).execute().status(HttpStatus.SC_BAD_REQUEST).json("$.message",
				is("Invalid parameter: username"));

	}

	public void testDisableUserUserNotFound() {
		Map<String, Object> json = new HashMap<>();
		json.put("username", newusername + "nonvalid");

		api.post("/studio/api/1/services/api/1/user/disable.json").json(json).execute().status(HttpStatus.SC_NOT_FOUND).json("$.message",
				is("User not found"));

	}

	public void testDisableUserUnauthorized() {
		Map<String, Object> json = new HashMap<>();
		json.put("username", newusername);

		api.post("/studio/api/1/services/api/1/user/disable.json").json(json).execute().status(HttpStatus.SC_UNAUTHORIZED)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/user/get.json?username=" + newusername))
				.debug();

	}
	
	public void testGetUserStatus() {

		api.get("/studio/api/1/services/api/1/user/status.json").urlParam("username", newusername).execute().status(HttpStatus.SC_OK)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/user/get.json?username=" + newusername));

	}

	public void testGetUserStatusInvalidParameter() {
		Map<String, Object> json = new HashMap<>();
		json.put("usernamenonvalid", newusername);

		api.get("/studio/api/1/services/api/1/user/status.json").json(json).execute().status(HttpStatus.SC_BAD_REQUEST).json("$.message",
				is("Invalid parameter: username"));

	}

	public void testGetUserStatusUserNotFound() {

		api.get("/studio/api/1/services/api/1/user/status.json").urlParam("username", newusername + "nonvalid")
				.execute().status(HttpStatus.SC_NOT_FOUND);

	}
	
	public void testGetUserStatusUnauthorized() {

		api.get("/studio/api/1/services/api/1/user/status.json").urlParam("username", newusername).execute().status(HttpStatus.SC_UNAUTHORIZED)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/user/get.json?username=" + newusername)).debug();

	}

	public void testForgotPassword() {
		api.get("/studio/api/1/services/api/1/user/forgot-password.json").urlParam("username", newusername).execute()
				.status(HttpStatus.SC_OK)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/user/get.json?username=" + newusername))
				.json("$.message", is("OK"));
	}

	public void testForgotPasswordInvalidParameters() {
		api.get("/studio/api/1/services/api/1/user/forgot-password.json").urlParam("usernamenonvalid", newusername).execute().status(HttpStatus.SC_BAD_REQUEST)
				.json("$.message", is("Invalid parameter: username")).debug();
	}
	
	public void testForgotPasswordUserNotFound() {
		api.get("/studio/api/1/services/api/1/user/forgot-password.json").urlParam("username", newusername+"nonvalid").execute()
				.status(HttpStatus.SC_NOT_FOUND).json("$.message", is("User not found"));

	}

	public void testForgotPasswordInternalServerError() {
		api.get("/studio/api/1/services/api/1/user/forgot-password.json").urlParam("username", newusername).execute().status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
				.debug();
	}

	public void testSetPassword() {
		Map<String, Object> json = new HashMap<>();
		json.put("token", token);
		json.put("new", "new");
		api.post("/studio/api/1/services/api/1/user/set-password.json").json(json).execute().status(HttpStatus.SC_OK)
				.json("$.message", is("OK")).debug();
	}

	public void testSetPasswordInvalidParameters() {
		Map<String, Object> json = new HashMap<>();
		json.put("tokennonvalid", token);
		json.put("new", "new");
		api.post("/studio/api/1/services/api/1/user/set-password.json").json(json).execute().status(HttpStatus.SC_BAD_REQUEST)
		.json("$.message",is("Invalid parameter(s): [token]"));
	}

	public void testSetPasswordUnauthorized() {
		Map<String, Object> json = new HashMap<>();
		json.put("token", token);
		json.put("new", "new");
		api.post("/studio/api/1/services/api/1/user/set-password.json").json(json).execute().status(HttpStatus.SC_UNAUTHORIZED);
	}

	public void testValidateToken() {
		api.get("/studio/api/1/services/api/1/user/validate-token.json").urlParam("token", token).execute().status(HttpStatus.SC_OK)
				.json("$.message", is("OK")).debug();
	}

	public void testValidateTokenInvalidParameters() {
		api.get("/studio/api/1/services/api/1/user/validate-token.json").urlParam("tokennonvalid", token).execute().status(HttpStatus.SC_BAD_REQUEST)
				.json("$.message", is("Invalid parameter: token")).debug();
	}

	public void testValidateTokenUnauthorized() {
		api.get("/studio/api/1/services/api/1/user/validate-token.json").urlParam("token", token).execute().status(HttpStatus.SC_UNAUTHORIZED)
				.json("$.message", is("Unauthorized")).debug();
	}

	public void testValidateTokenExternallyManagedUser() {
		api.get("/studio/api/1/services/api/1/user/validate-token.json").urlParam("token", token).execute().status(HttpStatus.SC_FORBIDDEN)
				.json("$.message", is("Externally managed user")).debug();
	}

	public void testValidateTokenInternalServerError() {
		api.get("/studio/api/1/services/api/1/user/validate-token.json").urlParam("token", token).execute().status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
				.json("$.message", is("Internal server error")).debug();
	}

	public void testChangePassword() {
		Map<String, Object> json = new HashMap<>();
		json.put("username", newusername);
		json.put("current", newpassword);
		json.put("new", newpassword + "new");

		api.post("/studio/api/1/services/api/1/user/change-password.json").json(json).execute().status(HttpStatus.SC_OK)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/user/get.json?username=" + newusername))
				.json("$.message", is("OK")).debug();
		
		setNewpassword(newpassword + "new");
	}

	public void testChangePasswordInvalidParameters() {
		Map<String, Object> json = new HashMap<>();
		json.put("username", newusername);
		json.put("currentnon-valid", newpassword);
		json.put("new", newpassword + "#");

		api.post("/studio/api/1/services/api/1/user/change-password.json").json(json).execute().status(HttpStatus.SC_BAD_REQUEST)
				.json("$.message", is("Invalid parameter(s): [current]")).debug();

	}

	public void testChangePasswordUnauthorized() {
		Map<String, Object> json = new HashMap<>();
		json.put("username", newusername);
		json.put("current", newpassword);
		json.put("new", newpassword + "#");

		api.post("/studio/api/1/services/api/1/user/change-password.json").json(json).execute().status(HttpStatus.SC_UNAUTHORIZED);

	}

	public void testResetPassword() {
		Map<String, Object> json = new HashMap<>();
		json.put("username", newusername);
		json.put("new", newpassword + "#");

		api.post("/studio/api/1/services/api/1/user/reset-password.json").json(json).execute().status(HttpStatus.SC_OK)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/user/get.json?username=" + newusername))
				.json("$.message", is("OK")).debug();

	}
	
	public void testResetPasswordInvalidParameters() {
		Map<String, Object> json = new HashMap<>();
		json.put("usernamenonvalid", newusername);
		json.put("newnonvalid", newpassword + "#");

		api.post("/studio/api/1/services/api/1/user/reset-password.json").json(json).execute().status(HttpStatus.SC_BAD_REQUEST)
		.json("$.message", is("Invalid parameter(s): [username, new]")).debug();

	}

	public void testResetPasswordUserNotFound() {
		Map<String, Object> json = new HashMap<>();
		json.put("username", newusername + "nonvalid");
		json.put("new", newpassword + "##");

		api.post("/studio/api/1/services/api/1/user/reset-password.json").json(json).execute().status(HttpStatus.SC_NOT_FOUND)
				.json("$.message", is("User not found")).debug();
	}
	
	public void testResetPasswordUnauthorized() {
		Map<String, Object> json = new HashMap<>();
		json.put("username", newusername);
		json.put("new", newpassword + "#");

		api.post("/studio/api/1/services/api/1/user/reset-password.json").json(json).execute().status(HttpStatus.SC_UNAUTHORIZED)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/user/get.json?username=" + newusername))
				.debug();

	}

	public String getNewusername() {
		return newusername;
	}

	public void setNewusername(String newusername) {
		this.newusername = newusername;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
