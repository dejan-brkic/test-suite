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
package org.craftercms.studio.test.cases.userstestcases;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.craftercms.studio.test.cases.StudioBaseTest;

/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class DesignOfUsersPageTest extends StudioBaseTest{

	private String userName;
	private String password;
	private String usersTitleXpath;
	private String crafterLogoXpath;
	private String accountTopNavOptionXpath;
	private String helpTopNavOptionXpath;
	private String usersPerPageInputXpath;
	private String newUserButtonXpath;
	private String userSearchXpath;

	@BeforeMethod
	public void beforeTest() {

		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		usersTitleXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.userstitle");
		crafterLogoXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.crafterlogo");
		helpTopNavOptionXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.homehelp");
		accountTopNavOptionXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.accountdropdown");
		usersPerPageInputXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.usersperpageinput");
		newUserButtonXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.newuserbutton");
		userSearchXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.userssearchinput");
	}


	@Test(priority = 0)
	public void verifyTheDesingOfUsersPageTest() {

		// login to application
		loginPage.loginToCrafter(userName, password);
		
		//Wait for login page to close
		getWebDriverManager().waitUntilLoginCloses();

		// click On Users option
		createSitePage.clickOnUsersOption();

		// Assert header is present.
		WebElement header = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", usersTitleXpath);
		Assert.assertTrue(header.isDisplayed());

		// Assert crafter logo is present.
		WebElement crafterLogo = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
				crafterLogoXpath);
		Assert.assertTrue(crafterLogo.isDisplayed());

		// Assert help menu option is present.
		WebElement helpMenuOption = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
				helpTopNavOptionXpath);
		Assert.assertTrue(helpMenuOption.isDisplayed());

		// Assert admin dropdown option is present.
		WebElement adminOption = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
				accountTopNavOptionXpath);
		Assert.assertTrue(adminOption.isDisplayed());

		// Assert users per page combo option is present.
		WebElement usersPerPage = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
				usersPerPageInputXpath);
		Assert.assertTrue(usersPerPage.isDisplayed());

		// Assert new user option is present.
		WebElement newUser = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
				newUserButtonXpath);
		Assert.assertTrue(newUser.isDisplayed());
		
		// Assert search option is present.
		WebElement search = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
				userSearchXpath);
		Assert.assertTrue(search.isDisplayed());

	}
}
