package org.craftercms.studio.test.cases.apitestcases;

import org.craftercms.studio.test.api.objects.GroupManagementAPI;
import org.craftercms.studio.test.api.objects.SecurityAPI;
import org.craftercms.studio.test.api.objects.SiteManagementAPI;
import org.craftercms.studio.test.utils.APIConnectionManager;
import org.craftercms.studio.test.utils.JsonTester;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by Gustavo Ortiz Alfaro.
 */

public class UpdateGroupAPITest {
	private SecurityAPI securityAPI;
	private SiteManagementAPI siteManagementAPI;
	private GroupManagementAPI groupManagementAPI;
	private String siteId="siteUpdateGroupAPITest";

	public UpdateGroupAPITest() {
		APIConnectionManager apiConnectionManager = new APIConnectionManager();
		JsonTester api = new JsonTester(apiConnectionManager.getProtocol(), apiConnectionManager.getHost(),
				apiConnectionManager.getPort());
		securityAPI = new SecurityAPI(api, apiConnectionManager);
		siteManagementAPI = new SiteManagementAPI(api, apiConnectionManager);
		groupManagementAPI = new GroupManagementAPI(api, apiConnectionManager);
	}

	@BeforeTest
	public void beforeTest() {
		securityAPI.logInIntoStudioUsingAPICall();
		siteManagementAPI.testCreateSite(siteId);
		groupManagementAPI.testCreateStudioGroup01(siteManagementAPI.getSiteId());
	}

	@Test(priority = 1,groups={"updateGroup"})
	public void testUpdateGroup() {
		groupManagementAPI.testUpdateGroup(siteManagementAPI.getSiteId());
	}

	@Test(priority = 2,groups={"updateGroup"})
	public void testInvalidParameters() {
		groupManagementAPI.testUpdateGroupInvalidParameters(siteManagementAPI.getSiteId());
	}

	@Test(priority = 3,groups={"updateGroup"})
	public void testGroupNotFound() {
		groupManagementAPI.testUpdateGroupGroupNotFound(siteManagementAPI.getSiteId());
	}
	
	@Test(priority = 4,groups={"updateGroup"})
	public void testSiteNotFound() {
		groupManagementAPI.testUpdateGroupSiteNotFound(siteManagementAPI.getSiteId());
	}
	
	@AfterGroups(groups={"updateGroup"})
	public void afterTest() {
		siteManagementAPI.testDeleteSite(siteId);
		securityAPI.logOutFromStudioUsingAPICall();
	}

	@Test(dependsOnGroups={"updateGroup"})
	public void testUpdateGroupUnauthorized(){
		groupManagementAPI.testUpdateGroupUnauthorized(siteManagementAPI.getSiteId());
	}
}
