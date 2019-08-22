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

public class ShowSitesPageTest extends StudioBaseTest {
	

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
	public void verifyThatApplicationShowsSitesPageWhenUserClicksSitesContextualNavigationOption() {

		// login to application
		loginPage.loginToCrafter(userName, password);
		
		//Wait for login page to close
		getWebDriverManager().waitUntilLoginCloses();

		// Click on the Users Contextual Navigation Option
		homePage.clickUsersContextualNavigationOption();

	    this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",newUserXpath);
		
		// go back to Sites Page
		usersPage.clickOnSitesOption();

		// Checking if the Sites page was Loaded
		this.getWebDriverManager().waitForAnimation();
		Assert.assertTrue(usersPage.getDriverManager().getDriver().getCurrentUrl()
				.equals(apiConnectionManager.getHeaderLocationBase()+"/studio/#/sites"));
		
		// Checking if the Users title is displayed on the current page
		Assert.assertTrue(homePage.isSitePageTitlePresent());
				
		// select the about us option
		homePage.goToDashboardPage();
		this.getWebDriverManager().waitForAnimation();
		dashboardPage.clickOnSitesOption();

		// Checking if the Sites page was Loaded
		this.getWebDriverManager().waitForAnimation();
		Assert.assertTrue(dashboardPage.getDriverManager().getDriver().getCurrentUrl()
				.equals(apiConnectionManager.getHeaderLocationBase()+"/studio/#/sites"));

		// Checking if the Users title is displayed on the current page
		this.getWebDriverManager().waitForAnimation();
		Assert.assertTrue(homePage.isSitePageTitlePresent());

	}
}
