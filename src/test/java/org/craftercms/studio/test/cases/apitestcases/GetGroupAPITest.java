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

public class GetGroupAPITest {

	private SecurityAPI securityAPI;
	private SiteManagementAPI siteManagementAPI;
	private GroupManagementAPI groupManagementAPI;
	private String siteId="siteGetGroupAPITest";

	public GetGroupAPITest() {
		APIConnectionManager apiConnectionManager = new APIConnectionManager();
		JsonTester api = new JsonTester(apiConnectionManager.getProtocol(), apiConnectionManager.getHost(),
				apiConnectionManager.getPort());
		securityAPI = new SecurityAPI(api,apiConnectionManager);
		siteManagementAPI = new SiteManagementAPI(api,apiConnectionManager);
		groupManagementAPI = new GroupManagementAPI(api,apiConnectionManager);
	}

	@BeforeTest
	public void beforeTest() {
		securityAPI.logInIntoStudioUsingAPICall();
		siteManagementAPI.testCreateSite(siteId);
		groupManagementAPI.testCreateStudioGroup01(siteManagementAPI.getSiteId());
	}

	@Test(priority = 1,groups={"getGroup"})
	public void testGetGroup() {
		groupManagementAPI.testGetGroup(siteManagementAPI.getSiteId());
	}

	@Test(priority = 2,groups={"getGroup"})
	public void testInvalidParameter() {
		groupManagementAPI.testGetGroupInvalidParameter(siteManagementAPI.getSiteId());
	}

	@Test(priority = 3,groups={"getGroup"})
	public void testGroupNotFound() {
		groupManagementAPI.testGetGroupGroupNotFound(siteManagementAPI.getSiteId());
	}
	
	@Test(priority = 4,groups={"getGroup"})
	public void testSiteNotFound() {
		groupManagementAPI.testGetGroupSiteNotFound(siteManagementAPI.getSiteId());
	}

	@AfterGroups(groups={"getGroup"})
	public void afterTest() {
		siteManagementAPI.testDeleteSite(siteId);
		securityAPI.logOutFromStudioUsingAPICall();
	}
	
	@Test(dependsOnGroups={"getGroup"})
	public void testGetGroupUnauthorized(){
		groupManagementAPI.testGetGroupUnauthorized(siteManagementAPI.getSiteId());
	}

}
