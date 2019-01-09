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
import org.craftercms.studio.test.utils.ConstantsPropertiesManager;
import org.craftercms.studio.test.utils.FilesLocations;
import org.craftercms.studio.test.utils.JsonTester;

/**
 * @author luishernandez
 *
 */
public class SecurityAPI extends BaseAPI {

	private String password;
	private String userName;

	public SecurityAPI(JsonTester api, APIConnectionManager apiConnectionManager) {
		super(api, apiConnectionManager);
		ConstantsPropertiesManager constantsPropertiesManager = new ConstantsPropertiesManager(
				FilesLocations.CONSTANTSPROPERTIESFILEPATH);
		userName = constantsPropertiesManager.getSharedExecutionConstants()
				.getProperty("crafter.api.username");
		password = constantsPropertiesManager.getSharedExecutionConstants()
				.getProperty("crafter.api.password");
	}

	public void logInIntoStudioUsingAPICall() {
		Map<String, Object> json = new HashMap<>();
		json.put("username", userName);
		json.put("password", password);
		api.post("/studio/api/1/services/api/1/security/login.json").json(json).execute().status(HttpStatus.SC_OK);
	}

	public void testLogInUnauthorized() {
		Map<String, Object> json = new HashMap<>();
		json.put("username", userName + "nonvalid");
		json.put("password", password + "nonvalid");
		api.post("/studio/api/1/services/api/1/security/login.json").json(json).execute().status(HttpStatus.SC_UNAUTHORIZED);

	}

	public void logOutFromStudioUsingAPICall() {
		api.post("/studio/api/1/services/api/1/security/logout.json").execute().status(HttpStatus.SC_OK);
	}

	public void loginWithOtherUser(String username, String password) {
		Map<String, Object> json = new HashMap<>();
		json.put("username", username);
		json.put("password", password);
		api.post("/studio/api/1/services/api/1/security/login.json").json(json).execute().status(HttpStatus.SC_OK);
	}

	public void testValidateSession() {
		api.get("/studio/api/1/services/api/1/security/validate-session.json").execute().status(HttpStatus.SC_OK)
				.json("$.message", is("OK")).debug();
	}

	public void testValidateSessionUnauthorized() {
		api.get("/studio/api/1/services/api/1/security/validate-session.json").execute().status(HttpStatus.SC_UNAUTHORIZED).debug();
	}

	public void testGetUserPermissions(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("site", siteId);
		json.put("user", userName);

		api.get("/studio/api/1/services/api/1/security/get-user-permissions.json").json(json).execute()
				.status(HttpStatus.SC_OK).debug();
	}

	public void testGetUserRoles(String siteId) {
		api.get("/studio/api/1/services/api/1/security/get-user-roles.json").urlParam("site_id", siteId)
		.urlParam("user", userName).execute().status(HttpStatus.SC_OK).debug();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
