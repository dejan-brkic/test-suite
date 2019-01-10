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

public class GetUserSitesAPI2Test {

	private SecurityAPI securityAPI;
	private UsersManagementAPI2 usersManagementAPI2;
	private String userId;
	private String offSet = "0";
	private String limit = "1000";
	private String sort = "asc";

	public GetUserSitesAPI2Test() {
		APIConnectionManager apiConnectionManager = new APIConnectionManager();
		JsonTester api = new JsonTester(apiConnectionManager.getProtocol(), apiConnectionManager.getHost(),
				apiConnectionManager.getPort());
		securityAPI = new SecurityAPI(api, apiConnectionManager);
		usersManagementAPI2 = new UsersManagementAPI2(api, apiConnectionManager,offSet,limit,sort);
		userId = "1";
	}

	@BeforeTest
	public void beforeTest() {
		securityAPI.logInIntoStudioUsingAPICall();
	}

	@Test(
			priority = 1,
			groups = { "getUserSitesAPI2" })
	public void testGetUserSites() {
		usersManagementAPI2.testGetUserSites(userId);
	}

	@Test(
			priority = 2,
			groups = { "getUserSitesAPI2" })
	public void testGetUserSitesResourceNotFound() {
		usersManagementAPI2.testGetUserSitesResourceNotFound("0");
	}

	@AfterGroups(
			groups = { "getUserSitesAPI2" })
	public void afterTest() {
		securityAPI.logOutFromStudioUsingAPICall();
	}

	@Test(
			dependsOnGroups = { "getUserSitesAPI2" })
	public void testGetUserSitesUnauthorized() {
		usersManagementAPI2.testGetUserSitesUnauthorized(userId);
	}
}
