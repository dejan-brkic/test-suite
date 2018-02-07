package org.craftercms.studio.test.cases.apitestcases;

import org.craftercms.studio.test.api.objects.SecurityAPI;
import org.craftercms.studio.test.api.objects.UserManagementAPI;
import org.craftercms.studio.test.utils.APIConnectionManager;
import org.craftercms.studio.test.utils.JsonTester;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by Gustavo Ortiz Alfaro
 */

public class ChangePasswordAPITest {

	private SecurityAPI securityAPI;
	private UserManagementAPI userManagementAPI;

	public ChangePasswordAPITest() {
		 APIConnectionManager apiConnectionManager = new APIConnectionManager();
		 JsonTester api = new JsonTester(apiConnectionManager.getProtocol(), apiConnectionManager.getHost(),
				apiConnectionManager.getPort());
		securityAPI = new SecurityAPI(api,apiConnectionManager);
		userManagementAPI = new UserManagementAPI(api,apiConnectionManager);
	}

	@BeforeTest
	public void beforeTest() {
		securityAPI.logInIntoStudioUsingAPICall();
		userManagementAPI.testCreateUser();
		securityAPI.logOutFromStudioUsingAPICall();
		securityAPI.loginWithOtherUser(userManagementAPI.getNewusername(), userManagementAPI.getNewpassword());
	}

	@Test(priority = 1)
	public void testChangePasswordInvalidParameters() {
		userManagementAPI.testChangePasswordInvalidParameters();
	}
	
	@Test(priority = 2)
	public void testChangePassword() {
		userManagementAPI.testChangePassword();
		securityAPI.logOutFromStudioUsingAPICall();
		securityAPI.loginWithOtherUser(userManagementAPI.getNewusername(), userManagementAPI.getNewpassword());
		securityAPI.logOutFromStudioUsingAPICall();
	}

	@Test(priority = 3)
	public void testUnauthorized() {
		userManagementAPI.testChangePasswordUnauthorized();
	}
	
	@AfterTest
	public void afterTest() {
		securityAPI.logInIntoStudioUsingAPICall();
		userManagementAPI.testDeleteUser();
		securityAPI.logOutFromStudioUsingAPICall();
	}

}
