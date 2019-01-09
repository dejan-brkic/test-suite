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

package org.craftercms.studio.test.cases.apitestcases;

import org.craftercms.studio.test.api.objects.GroupManagementAPI;
import org.craftercms.studio.test.api.objects.SecurityAPI;
import org.craftercms.studio.test.api.objects.SiteManagementAPI;
import org.craftercms.studio.test.api.objects.UserManagementAPI;
import org.craftercms.studio.test.utils.APIConnectionManager;
import org.craftercms.studio.test.utils.JsonTester;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by Gustavo Ortiz Alfaro.
 */

public class GetUsersPerGroupAPITest {
	
	private SecurityAPI securityAPI;
	private SiteManagementAPI siteManagementAPI;
	private GroupManagementAPI groupManagementAPI;
	private UserManagementAPI userManagementAPI;
	private String siteId="siteGetUsersPerGroupAPITest";

	public GetUsersPerGroupAPITest() {
		APIConnectionManager apiConnectionManager = new APIConnectionManager();
		JsonTester api = new JsonTester(apiConnectionManager.getProtocol(), apiConnectionManager.getHost(),
				apiConnectionManager.getPort());
		securityAPI = new SecurityAPI(api,apiConnectionManager);
		userManagementAPI = new UserManagementAPI(api,apiConnectionManager);
		siteManagementAPI = new SiteManagementAPI(api,apiConnectionManager);
		groupManagementAPI = new GroupManagementAPI(api,apiConnectionManager);
	}

	@BeforeTest
	public void beforeTest() {
		securityAPI.logInIntoStudioUsingAPICall();
		userManagementAPI.testCreateUser();
		siteManagementAPI.testCreateSite(siteId);
		groupManagementAPI.testCreateStudioGroup01(siteManagementAPI.getSiteId());
		groupManagementAPI.testAddUserToGroup01(userManagementAPI.getNewusername(), siteManagementAPI.getSiteId());
	}

	
	@Test(priority=1,groups={"getUsersPerGroup"})
	public void testGetUsersPerGroup() {
		groupManagementAPI.testGetUsersPerGroup(siteManagementAPI.getSiteId());
	}
	
	@Test(priority=2,groups={"getUsersPerGroup"})
	public void testGetUsersPerGroupInvalidParameters() {
		groupManagementAPI.testGetUsersPerGroupInvalidParameters(siteManagementAPI.getSiteId());
	}
	
	@Test(priority=3,groups={"getUsersPerGroup"})
	public void testGetUsersGroupNotFound() {
		groupManagementAPI.testGetUsersPerGroupGroupNotFound(siteManagementAPI.getSiteId());
	}
	
	@Test(priority=4,groups={"getUsersPerGroup"})
	public void testGetUsersSiteNotFound() {
		groupManagementAPI.testGetUsersPerGroupSiteNotFound(siteManagementAPI.getSiteId());
	}
	
	@AfterGroups(groups={"getUsersPerGroup"})
	public void afterTest() {
		siteManagementAPI.testDeleteSite(siteId);
		userManagementAPI.testDeleteUser();
		securityAPI.logOutFromStudioUsingAPICall();
	}
	
	@Test(dependsOnGroups={"getUsersPerGroup"})
	public void testGetUsersPerGroupUnauthorized(){
		groupManagementAPI.testGetUsersPerGroupUnauthorized(siteManagementAPI.getSiteId());
	}
}
