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

package org.craftercms.studio.test.cases.api2testcases;

import org.apache.commons.lang3.RandomStringUtils;
import org.craftercms.studio.test.api.objects.SecurityAPI;
import org.craftercms.studio.test.api2.objects.UsersManagementAPI2;
import org.craftercms.studio.test.utils.APIConnectionManager;
import org.craftercms.studio.test.utils.JsonTester;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * 
 * @author luishernandez
 *
 */

public class UpdateUserAPI2Test {

	private SecurityAPI securityAPI;
	private UsersManagementAPI2 usersManagementAPI2;
	private int randomID;
	private String userName;
	private String userId;
	private String offSet = "0";
	private String limit = "1000";
	private String sort = "asc";

	public UpdateUserAPI2Test() {
		APIConnectionManager apiConnectionManager = new APIConnectionManager();
		JsonTester api = new JsonTester(apiConnectionManager.getProtocol(), apiConnectionManager.getHost(),
				apiConnectionManager.getPort());
		securityAPI = new SecurityAPI(api, apiConnectionManager);
		usersManagementAPI2 = new UsersManagementAPI2(api, apiConnectionManager,offSet,limit,sort);
		
		randomID = (int) (((Math.random() * 2) * 5) + Math.random());
		userName = "tester"+ RandomStringUtils.randomAlphabetic(5).toLowerCase();
	}

	@BeforeTest
	public void beforeTest() {
		securityAPI.logInIntoStudioUsingAPICall();
		usersManagementAPI2.testCreateUser(String.valueOf(randomID), userName);
		userId = usersManagementAPI2.getUserIDForUserName(userName);
	}

	@Test(
			priority = 1,
			groups = { "updateUserAPI2" })
	public void testUpdateUser() {
		usersManagementAPI2.testUpdateUser(userId, userName);
	}

	@Test(
			priority = 2,
			groups = { "updateUserAPI2" })
	public void testUpdateUserResourceNotFound() {
		usersManagementAPI2.testUpdateUserResourceNotFound("0", "nonvalid");
	}

	@Test(
			priority = 3,
			groups = { "updateUserAPI2" })
	public void testUpdateUserBadRequest() {
		usersManagementAPI2.testUpdateUserBadRequest(userId, userName);
	}

	@AfterGroups(
			groups = { "updateUserAPI2" })
	public void afterTest() {
		usersManagementAPI2.testDeleteUserById(userId);
		securityAPI.logOutFromStudioUsingAPICall();
	}

	@Test(
			dependsOnGroups = { "updateUserAPI2" })
	public void testUpdateUserUnauthorized() {
		usersManagementAPI2.testUpdateUserUnAuthorized(userId, userName);
	}
}
