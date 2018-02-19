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
	public void testInvalidParameters() {
		groupManagementAPI.testRemoveUserFromGroupInvalidParameters(userManagementAPI.getNewusername(),siteManagementAPI.getSiteId());
	}
	
	
	@Test(priority=2,groups={"removeUserFromGroup"})
	public void testGroupNotFound() {
		groupManagementAPI.testRemoveUserFromGroupGroupNotFound(userManagementAPI.getNewusername(),siteManagementAPI.getSiteId());
	}
	
	@Test(priority=3,groups={"removeUserFromGroup"})
	public void testUserNotFound() {
		groupManagementAPI.testRemoveUserFromGroupUserNotFound(userManagementAPI.getNewusername(),siteManagementAPI.getSiteId());
	}
	
	@Test(priority=4,groups={"removeUserFromGroup"})
	public void testSiteNotFound() {
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
