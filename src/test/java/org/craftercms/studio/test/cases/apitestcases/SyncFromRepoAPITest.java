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

import org.craftercms.studio.test.api.objects.RepoManagementAPI;
import org.craftercms.studio.test.api.objects.SecurityAPI;
import org.craftercms.studio.test.api.objects.SiteManagementAPI;
import org.craftercms.studio.test.utils.APIConnectionManager;
import org.craftercms.studio.test.utils.JsonTester;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by Gustavo Ortiz Alfaro
 */

public class SyncFromRepoAPITest {

	private SecurityAPI securityAPI;
	private SiteManagementAPI siteManagementAPI;
	private RepoManagementAPI repoManagementAPI;
	private String siteId="sitesyncfomrepoapitest";
	
	public SyncFromRepoAPITest() {
		APIConnectionManager apiConnectionManager = new APIConnectionManager();
		JsonTester api = new JsonTester(apiConnectionManager.getProtocol(), apiConnectionManager.getHost(),
				apiConnectionManager.getPort());
		securityAPI = new SecurityAPI(api, apiConnectionManager);
		siteManagementAPI = new SiteManagementAPI(api, apiConnectionManager);
		repoManagementAPI = new RepoManagementAPI(api, apiConnectionManager);
	}

	@BeforeTest
	public void beforeTest() {
		securityAPI.logInIntoStudioUsingAPICall();
		siteManagementAPI.testCreateSite(siteId);
	}

    @Test(priority=1,groups={"syncFromRepo"})
   	public void testSyncFromRepo() {
    	repoManagementAPI.testSyncFromRepo(siteManagementAPI.getSiteId());
   	}
    
    
    @Test(priority=2,groups={"syncFromRepo"})
   	public void testInvalidParameter() {
     	repoManagementAPI.testSyncFromRepoInvalidParameter(siteManagementAPI.getSiteId());
   	}
    
    @Test(priority=3,groups={"syncFromRepo"})
   	public void testSiteNotFound() {
    	repoManagementAPI.testSyncFromRepoSiteNotFound(siteManagementAPI.getSiteId());
   	}
    
    @AfterGroups(groups={"syncFromRepo"})
	public void afterTest() {
		siteManagementAPI.testDeleteSite(siteId);
		securityAPI.logOutFromStudioUsingAPICall();
	}

    @Test(dependsOnGroups={"syncFromRepo"})
   	public void testSyncFromRepoUnauthorized() {
    	repoManagementAPI.testSyncFromRepoUnauthorized(siteManagementAPI.getSiteId());
   	}
}
