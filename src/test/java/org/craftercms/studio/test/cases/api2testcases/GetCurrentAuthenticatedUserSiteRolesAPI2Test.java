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
import org.craftercms.studio.test.api.objects.SiteManagementAPI;
import org.craftercms.studio.test.api2.objects.UserManagementAPI2;
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

public class GetCurrentAuthenticatedUserSiteRolesAPI2Test {

	private SecurityAPI securityAPI;
	private UserManagementAPI2 userManagementAPI2;
	private SiteManagementAPI siteManagementAPI;
	private String siteId="api2testsite";

	
	public GetCurrentAuthenticatedUserSiteRolesAPI2Test() {
		APIConnectionManager apiConnectionManager = new APIConnectionManager();
		JsonTester api = new JsonTester(apiConnectionManager.getProtocol(), apiConnectionManager.getHost(),
				apiConnectionManager.getPort());
		securityAPI = new SecurityAPI(api, apiConnectionManager);
		userManagementAPI2 = new UserManagementAPI2(api, apiConnectionManager);
		siteManagementAPI = new SiteManagementAPI(api, apiConnectionManager);
	}

	@BeforeTest
	public void beforeTest() {
		securityAPI.logInIntoStudioUsingAPICall();
		siteManagementAPI.testCreateSite(siteId);
	}

	@Test(
			priority = 1,
			groups = { "getCurrentAuthenticatedUserSiteRolesAPI2" })
	public void testGetCurrentAuthenticatedUserSiteRoles() {
		userManagementAPI2.testGetCurrentAuthenticatedUserSiteRoles(siteId);
	}


	@AfterGroups(
			groups = { "getCurrentAuthenticatedUserSiteRolesAPI2" })
	public void afterTest() {
		siteManagementAPI.testDeleteSite(siteId);
		securityAPI.logOutFromStudioUsingAPICall();
	}

	@Test(
			dependsOnGroups = { "getCurrentAuthenticatedUserSiteRolesAPI2" })
	public void testGetUserSitesUnauthorized() {
		userManagementAPI2.testGetCurrentAuthenticatedUserSiteRolesUnauthorized(siteId);
	}
}
