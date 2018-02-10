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

public class GetUserPerSiteAPITest {
	
	private SecurityAPI securityAPI;
	private UserManagementAPI userManagementAPI;
	private SiteManagementAPI siteManagementAPI;
	private GroupManagementAPI groupManagementAPI;
	private String siteId="siteGetUserPerSiteAPITest";

	public GetUserPerSiteAPITest() {
		APIConnectionManager apiConnectionManager = new APIConnectionManager();
		JsonTester api = new JsonTester(apiConnectionManager.getProtocol(), apiConnectionManager.getHost(),
				apiConnectionManager.getPort());
		securityAPI = new SecurityAPI(api,apiConnectionManager);
		userManagementAPI = new UserManagementAPI(api,apiConnectionManager);
		siteManagementAPI= new SiteManagementAPI(api,apiConnectionManager);
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
	
	@Test(priority=1,groups={"getUsersPerSite"})
	public void testGetUsersPerSite() {
		userManagementAPI.testGetUsersPerSite(siteManagementAPI.getSiteId());
	}	
	
	@Test(priority=2,groups={"getUsersPerSite"})
	public void testGetUsersPerSiteInvalidParameters() {
		userManagementAPI.testGetUsersPerSiteInvalidParameters();
	}	
	
	@Test(priority=3,groups={"getUsersPerSite"})
	public void testGetUsersPerSiteNotFound() {
		userManagementAPI.testGetUsersPerSiteNotFound();
	}	
	
	@AfterGroups(groups={"getUsersPerSite"})
	public void afterTest() {
		userManagementAPI.testDeleteUser();
		siteManagementAPI.testDeleteSite(siteId);
		securityAPI.logOutFromStudioUsingAPICall();
	}
	
	@Test(dependsOnGroups={"getUsersPerSite"})
	public void testGetUsersPerSiteUnauthorized(){
		userManagementAPI.testGetUsersPerSiteUnauthorized();
	}
}
