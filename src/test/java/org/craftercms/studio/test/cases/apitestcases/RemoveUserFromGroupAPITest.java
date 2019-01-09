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

public class RemoveUserFromGroupAPITest {
	private SecurityAPI securityAPI;
	private SiteManagementAPI siteManagementAPI;
	private GroupManagementAPI groupManagementAPI;
	private UserManagementAPI userManagementAPI;
	private String siteId="siteRemoveUserFromGroupAPITest";
	
	public RemoveUserFromGroupAPITest() {
		APIConnectionManager apiConnectionManager = new APIConnectionManager();
		JsonTester api = new JsonTester(apiConnectionManager.getProtocol(), apiConnectionManager.getHost(),
				apiConnectionManager.getPort());
		securityAPI = new SecurityAPI(api, apiConnectionManager);
		siteManagementAPI = new SiteManagementAPI(api, apiConnectionManager);
		groupManagementAPI = new GroupManagementAPI(api, apiConnectionManager);
		userManagementAPI = new UserManagementAPI(api, apiConnectionManager);
	}

	@BeforeTest
	public void beforeTest() {
		securityAPI.logInIntoStudioUsingAPICall();
		siteManagementAPI.testCreateSite(siteId);
		groupManagementAPI.testCreateStudioGroup01(siteManagementAPI.getSiteId());
		userManagementAPI.testCreateUser();
		groupManagementAPI.testAddUserToGroup01(userManagementAPI.getNewusername(), siteManagementAPI.getSiteId());
	}
	
	@Test(priority=1,groups={"removeUserFromGroup"})
	public void testRemoveUserFromGroupInvalidParameters() {
		groupManagementAPI.testRemoveUserFromGroupInvalidParameters(userManagementAPI.getNewusername(),siteManagementAPI.getSiteId());
	}
	
	
	@Test(priority=2,groups={"removeUserFromGroup"})
	public void testRemoveUserFromGroupGroupNotFound() {
		groupManagementAPI.testRemoveUserFromGroupGroupNotFound(userManagementAPI.getNewusername(),siteManagementAPI.getSiteId());
	}
	
	@Test(priority=3,groups={"removeUserFromGroup"})
	public void testRemoveUserFromGroupUserNotFound() {
		groupManagementAPI.testRemoveUserFromGroupUserNotFound(userManagementAPI.getNewusername(),siteManagementAPI.getSiteId());
	}
	
	@Test(priority=4,groups={"removeUserFromGroup"})
	public void testRemoveUserFromGroupSiteNotFound() {
		groupManagementAPI.testRemoveUserFromGroupSiteNotFound(userManagementAPI.getNewusername(),siteManagementAPI.getSiteId());
	}
	
	@Test(priority=5,groups={"removeUserFromGroup"})
	public void testRemoveUserFromGroup() {
		groupManagementAPI.testRemoveUserFromGroup(userManagementAPI.getNewusername(),siteManagementAPI.getSiteId());
	}
	
	@AfterGroups(groups={"removeUserFromGroup"})
	public void afterTest() {
		userManagementAPI.testDeleteUser();
		siteManagementAPI.testDeleteSite(siteId);
		securityAPI.logOutFromStudioUsingAPICall();
	}
	
	@Test(dependsOnGroups={"removeUserFromGroup"})
	public void testRemoveUserFromGroupUnauthorized(){
		groupManagementAPI.testRemoveUserFromGroupUnauthorized(userManagementAPI.getNewusername(), siteManagementAPI.getSiteId());
	}
	
}
