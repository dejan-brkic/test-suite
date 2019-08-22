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
package org.craftercms.studio.test.cases.contextualnavigationtestcases;


import org.craftercms.studio.test.cases.StudioBaseTest;
import org.craftercms.studio.test.utils.APIConnectionManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Luis Alonso Hernandez Monge
 *
 */

public class ShowUsersPageTest extends StudioBaseTest {
	

	private APIConnectionManager apiConnectionManager;
	private String userName;
	private String password;
	private String newUserXpath;
	
	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		newUserXpath = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("users.new_user");
		apiConnectionManager = new APIConnectionManager();
	
	}

	@Test(priority = 0)
	public void verifyThatStudioAllowsToAccessTheUsersPageWhenUserClicksOnUsersOptionTopNavigationBar() {

		// login to application
		loginPage.loginToCrafter(userName, password);
		
		//Wait for login page to close
		getWebDriverManager().waitUntilLoginCloses();

		// Click on the Users Contextual Navigation Option
		homePage.clickUsersContextualNavigationOption();

	    this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",newUserXpath);
		
	    // Checking if the UsersPage was Loaded
		Assert.assertTrue(usersPage.getDriverManager().getDriver().getCurrentUrl()
				.equals(apiConnectionManager.getHeaderLocationBase()+"/studio/#/users"));

		// Checking if the Users title is displayed on the current page
		Assert.assertTrue(usersPage.isUsersPageTitlePresent());

		// go back to Sites Page
		usersPage.clickOnCrafterLogo();

		// select the about us option
		homePage.goToDashboardPage();

		this.getWebDriverManager().waitForAnimation();
		dashboardPage.clickUsersContextualNavigationOption();

		// Checking if the UsersPage was Loaded
		this.getWebDriverManager().waitForAnimation();
		Assert.assertTrue(usersPage.getDriverManager().getDriver().getCurrentUrl()
				.equals(apiConnectionManager.getHeaderLocationBase()+"/studio/#/users"));

		// Checking if the Users title is displayed on the current page
		this.getWebDriverManager().waitForAnimation();
		Assert.assertTrue(usersPage.isUsersPageTitlePresent());

		// go back to Sites Page
		usersPage.clickOnCrafterLogo();

	}
}
