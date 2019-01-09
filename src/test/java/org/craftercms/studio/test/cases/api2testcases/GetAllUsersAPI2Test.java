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

public class GetAllUsersAPI2Test {

	
	private SecurityAPI securityAPI;
	private UsersManagementAPI2 usersManagementAPI2;
	private String offSet = "0";
	private String limit = "1000";
	private String sort = "asc";


	public GetAllUsersAPI2Test() {
		APIConnectionManager apiConnectionManager = new APIConnectionManager();
		JsonTester api = new JsonTester(apiConnectionManager.getProtocol(), apiConnectionManager.getHost(),
				apiConnectionManager.getPort());
		securityAPI = new SecurityAPI(api,apiConnectionManager);
		usersManagementAPI2 = new UsersManagementAPI2(api, apiConnectionManager,offSet,limit,sort);
	}

	@BeforeTest
	public void beforeTest() {
		securityAPI.logInIntoStudioUsingAPICall();
	}

	@Test(priority = 1,groups={"getAllUsers"})
	public void testGetAllUsers() {
		usersManagementAPI2.testGetAllUsersNonSiteId();
	}
	
	@Test(priority = 3,groups={"getAllUsers"})
	public void testGetAllUsersBadRequest() {
		usersManagementAPI2.testGetAllUsersNonSiteIdBadRequest();
	}
	
	@AfterGroups(groups={"getAllUsers"})
	public void afterTest() {
		securityAPI.logOutFromStudioUsingAPICall();
	}
	
	@Test(dependsOnGroups={"getAllUsers"})
	public void testGetAllUsersUnauthorized(){
		usersManagementAPI2.testGetAllUsersNonSiteIdNonAuthorized();
	}
}
