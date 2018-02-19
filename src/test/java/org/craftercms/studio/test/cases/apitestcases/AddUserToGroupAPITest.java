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

public class AddUserToGroupAPITest {

	private SecurityAPI securityAPI;
	private SiteManagementAPI siteManagementAPI;
	private GroupManagementAPI groupManagementAPI;
	private UserManagementAPI userManagementAPI;
	private String siteId="siteTestAddUserToGroupAPITest";

	public AddUserToGroupAPITest() {
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
	}

	@Test(priority = 1,groups={"addUserToGroup"})
	public void testAddUserToGroup() {
		groupManagementAPI.testAddUserToGroup(userManagementAPI.getNewusername(),siteManagementAPI.getSiteId());
	}

	@Test(priority = 2,groups={"addUserToGroup"})
	public void testInvalidParameters() {
		groupManagementAPI.testAddUserToGroupInvalidParameters(userManagementAPI.getNewusername(), siteManagementAPI.getSiteId());
	}

	@Test(priority = 3,groups={"addUserToGroup"})
	public void testUserNotFound() {
		groupManagementAPI.testAddUserToGroupUserNotFound(userManagementAPI.getNewusername(), siteManagementAPI.getSiteId());
	}

	@Test(priority = 4,groups={"addUserToGroup"})
	public void testGroupNotFound() {
		groupManagementAPI.testAddUserToGroupGroupNotFound(userManagementAPI.getNewusername(), siteManagementAPI.getSiteId());
	}
	
	@Test(priority = 5,groups={"addUserToGroup"})
	public void testSiteNotFound() {
		groupManagementAPI.testAddUserToGroupSiteNotFound(userManagementAPI.getNewusername(), siteManagementAPI.getSiteId());
	}

	@Test(priority = 6,groups={"addUserToGroup"})
	public void testUserAlreadyInGroup() {
		groupManagementAPI.testAddUserToGroupAlreadyInGroup(userManagementAPI.getNewusername(), siteManagementAPI.getSiteId());
	}
	
	@AfterGroups(groups={"addUserToGroup"})
	public void afterTest() {
		userManagementAPI.testDeleteUser();
		siteManagementAPI.testDeleteSite(siteId);
		securityAPI.logOutFromStudioUsingAPICall();
	}
	
	@Test(dependsOnGroups={"addUserToGroup"})
	public void testAddUserToGroupUnauthorized(){
		groupManagementAPI.testAddUserToGroupUnauthorized(userManagementAPI.getNewusername(), siteManagementAPI.getSiteId());
	}
}
