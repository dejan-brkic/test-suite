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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.craftercms.studio.test.cases.StudioBaseTest;


/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class AddNewUserTest extends StudioBaseTest {

	private String userName;
	private String password;

	private String newUserFirstNameId;
	private String newUserLastNameId;
	private String newUserEmailId;
	private String newUserUserNameId;
	private String newUserPasswordId;
	private String newUserPasswordVerificationId;
	private String newUserUserNameCreatedXpath;

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		newUserFirstNameId = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.firstname");
		newUserLastNameId = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.lastname");
		newUserEmailId = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("general.users.email");
		newUserUserNameId = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.username");
		newUserPasswordId = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.password");
		newUserPasswordVerificationId = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.passwordVerification");
		newUserUserNameCreatedXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.usernamecreated");
	}

	@Parameters({"testUser"})
	@Test()
	public void verifyThatStudioAllowsToCreateANewUser(String testUser) {

		// login to application
		loginPage.loginToCrafter(userName, password);

		//Wait for login page to close
		getWebDriverManager().waitUntilLoginCloses();
		
		// click On Users option
		createSitePage.clickOnUsersOption();

		// click on new user button
		usersPage.clickOnNewUser();

		// Follow the form
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", newUserFirstNameId).sendKeys(testUser + "N");

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", newUserLastNameId).sendKeys(testUser + "LN");

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", newUserEmailId)
				.sendKeys(testUser + "@" + testUser);

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", newUserUserNameId).sendKeys(testUser);

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", newUserPasswordId).sendKeys(testUser);

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", newUserPasswordVerificationId)
				.sendKeys(testUser);

		// Save Button
		usersPage.clickOnSaveNewUser();

		// Assert new users created is present
		WebElement newUserCreated = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
				String.format(newUserUserNameCreatedXpath, testUser));

		Assert.assertTrue(newUserCreated.isDisplayed(),"ERROR: Recently created user is not displayed");

	}

	@Parameters({"testUser"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testUser) {
		apiTestHelper.deleteUser(testUser);
	}
}
