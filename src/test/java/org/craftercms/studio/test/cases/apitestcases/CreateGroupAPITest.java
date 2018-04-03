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

public class CreateGroupAPITest {

	private SecurityAPI securityAPI;
	private SiteManagementAPI siteManagementAPI;
	private GroupManagementAPI groupManagementAPI;
	private String siteId="siteTestCreateGroupAPITest";

	public CreateGroupAPITest() {
		APIConnectionManager apiConnectionManager = new APIConnectionManager();
		JsonTester api = new JsonTester(apiConnectionManager.getProtocol(), apiConnectionManager.getHost(),
				apiConnectionManager.getPort());
		securityAPI = new SecurityAPI(api,apiConnectionManager);
		siteManagementAPI= new SiteManagementAPI(api,apiConnectionManager);
		groupManagementAPI = new GroupManagementAPI(api,apiConnectionManager);
	}

	@BeforeTest
	public void beforeTest() {
		securityAPI.logInIntoStudioUsingAPICall();
		siteManagementAPI.testCreateSite(siteId);
	}

	@Test(priority = 1,groups={"createGroup"})
	public void testCreateStudioGroup() {
		groupManagementAPI.testCreateStudioGroup01(siteManagementAPI.getSiteId());
	}

	@Test(priority = 2,groups={"createGroup"})
	public void testInvalidParameters() {
		groupManagementAPI.testCreateStudioGroupInvalidParameters(siteManagementAPI.getSiteId());
	}

	@Test(priority = 3,groups={"createGroup"})
	public void testGroupAlreadyExists() {
		groupManagementAPI.testCreateStudioGroupAlreadyExists(siteManagementAPI.getSiteId());
	}
	
	@Test(priority = 4,groups={"createGroup"})
	public void testSiteNotFound() {
		groupManagementAPI.testCreateStudioGroupSiteNotFound(siteManagementAPI.getSiteId());
	}
	
	@AfterGroups(groups={"createGroup"})
	public void afterTest() {
		siteManagementAPI.testDeleteSite(siteId);
		securityAPI.logOutFromStudioUsingAPICall();
	}

	@Test(dependsOnGroups={"createGroup"})
	public void testCreateGroupUnauthorized(){
		groupManagementAPI.testCreateStudioGroupUnauthorized(siteManagementAPI.getSiteId());
	}
}
