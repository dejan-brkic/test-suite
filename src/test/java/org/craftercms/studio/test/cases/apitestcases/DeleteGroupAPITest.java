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

public class DeleteGroupAPITest {

	private SiteManagementAPI siteManagementAPI;
	private SecurityAPI securityAPI;
	private GroupManagementAPI groupManagementAPI;
	private String siteId="siteTestDeleteGroupAPITest";
	
	public DeleteGroupAPITest() {
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

	@Test(priority = 1,groups={"deleteGroup"})
	public void testInvalidParameters() {
		groupManagementAPI.testDeleteGroupInvalidParameters(siteManagementAPI.getSiteId());
	}

	@Test(priority = 2,groups={"deleteGroup"})
	public void testDeleteGroup() {
		groupManagementAPI.testDeleteGroup(siteManagementAPI.getSiteId());
	}

	@Test(priority = 3,groups={"deleteGroup"})
	public void testDeleteGroupGroupNotFound() {
		groupManagementAPI.testDeleteGroupGroupNotFound(siteManagementAPI.getSiteId());
	}
	
	@Test(priority = 4,groups={"deleteGroup"})
	public void testDeleteGroupSiteNotFound() {
		groupManagementAPI.testDeleteGroupSiteNotFound(siteManagementAPI.getSiteId());
	}

	@AfterGroups(groups={"deleteGroup"})
	public void afterTest() {
		siteManagementAPI.testDeleteSite(siteId);
		securityAPI.logOutFromStudioUsingAPICall();
	}
	
	@Test(dependsOnGroups={"deleteGroup"})
	public void testDeleteGroupUnauthorized(){
		groupManagementAPI.testDeleteGroupUnauthorized(siteManagementAPI.getSiteId());
	}

}
