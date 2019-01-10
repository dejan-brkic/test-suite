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

import org.craftercms.studio.test.api.objects.SecurityAPI;
import org.craftercms.studio.test.api.objects.UserManagementAPI;
import org.craftercms.studio.test.utils.APIConnectionManager;
import org.craftercms.studio.test.utils.FakeSMTPServerManager;
import org.craftercms.studio.test.utils.JsonTester;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Gustavo Ortiz Alfaro.
 */

public class ForgotPasswordAndValidateTokenAndSetPasswordAPITest {

	private SecurityAPI securityAPI;
	private UserManagementAPI userManagementAPI;
	private FakeSMTPServerManager fakeSMTPServerManager;
    private boolean isTheLastPassed=false;
	private boolean isTheFirstTest=true;
    
	public ForgotPasswordAndValidateTokenAndSetPasswordAPITest() {
		APIConnectionManager apiConnectionManager = new APIConnectionManager();
		JsonTester api = new JsonTester(apiConnectionManager.getProtocol(), apiConnectionManager.getHost(),
				apiConnectionManager.getPort());
		securityAPI = new SecurityAPI(api,apiConnectionManager);
		userManagementAPI = new UserManagementAPI(api,apiConnectionManager);
		fakeSMTPServerManager = new FakeSMTPServerManager();
	}

	@BeforeMethod
	public void beforeTest() {
		securityAPI.logInIntoStudioUsingAPICall();
		if(isTheFirstTest) {
		userManagementAPI.testCreateUser();
		fakeSMTPServerManager.startFakeSMTPServer();}
		
	}
	
	@Test(priority=1)
	public void testForgotPassword() {
		
		userManagementAPI.testForgotPassword();
		
	}
	
	@Test(priority=2)
	public void testForgotPasswordInvalidParameters() {
		userManagementAPI.testForgotPasswordInvalidParameters();
	}
	
	@Test(priority=3)
	public void testForgotPasswordUserNotFound() {
		userManagementAPI.testForgotPasswordUserNotFound();
	}
	
	@Test(priority=4)
	public void testForgotPasswordInternalServerError() {
		userManagementAPI.testForgotPasswordInternalServerError();
	}

	@Test(priority = 5)
	public void testValidateToken() {
		userManagementAPI.testValidateToken();
	}

	@Test(priority = 6)
	public void testValidateTokenInvalidParameters() {
		userManagementAPI.testValidateTokenInvalidParameters();
	}
	
	@Test(priority = 7)
	public void testSetPassword() {
		userManagementAPI.testSetPassword();
	}

	@Test(priority = 8)
	public void testSetPasswordInvalidParameters() {
		userManagementAPI.testSetPasswordInvalidParameters();
		isTheLastPassed=true;
	}
	@AfterMethod
	public void afterTest() {	
		if(isTheFirstTest)
		{
		userManagementAPI.setToken(fakeSMTPServerManager.getRecentlyGeneratedToken());
		fakeSMTPServerManager.stopFakeSMTPServer();
		isTheFirstTest=false;
		}
		
		if(isTheLastPassed) {
		userManagementAPI.testDeleteUser();
		securityAPI.logOutFromStudioUsingAPICall();
		}
	}
}
