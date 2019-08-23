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

public class EditUserTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String newUserLastNameId;
	private String newUserEmailId;
	private String newUserFirstNameId;
	private String newUserUserNameId;
	private String newUserPasswordId;
	private String newUserPasswordVerificationId;
	private String newUserButtonXpath;
	private String userLastNameCellXpath;
	private String firstNameCellXpath;
	private String emailCellXpath;

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		newUserFirstNameId = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.firstname");
		newUserLastNameId = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.lastname");
		newUserEmailId = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.email");
		newUserUserNameId = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.username");
		newUserPasswordId = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.password");
		newUserPasswordVerificationId = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.passwordVerification");
		newUserButtonXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.newuserbutton");
		userLastNameCellXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.userlastnamecell");
		firstNameCellXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.userfirstnamecell");
		emailCellXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.useremailnamecell");
	}

	public void createUserToEdit(String value) {
		// click on new user button
		usersPage.clickOnNewUser();

		// Follow the form
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", newUserFirstNameId)
				.sendKeys(value+ "N");

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", newUserLastNameId)
				.sendKeys(value + "LN");

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", newUserEmailId)
				.sendKeys(value + "@" + value);

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", newUserUserNameId)
				.sendKeys(value);

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", newUserPasswordId)
				.sendKeys(value);

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", newUserPasswordVerificationId)
				.sendKeys(value);

		// Save Button
		usersPage.clickOnSaveNewUser();

	}

	public void editingUser(String username) {
		// Click on edit option

		usersPage.clickEditUsername(username);

		// Follow the form
		this.getWebDriverManager().sendText("xpath", newUserFirstNameId, "TestFN");
		this.getWebDriverManager().sendText("xpath", newUserLastNameId, "TestLN");
		this.getWebDriverManager().sendText("xpath", newUserEmailId, "Test@Test");

		// Save Button
		usersPage.clickOnSaveNewUser();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",newUserButtonXpath);

	}

	@Parameters({"testUser"})
	@Test()
	public void verifyThatStudioAllowsToEditAnUser(String testUser) {
		// login to application
		loginPage.loginToCrafter(userName, password);

		// Wait for login page to close
		getWebDriverManager().waitUntilLoginCloses();

		// click On Users option
		createSitePage.clickOnUsersOption();

		// create a new user
		createUserToEdit(testUser);

		// wait for element is clickeable
		this.getWebDriverManager().waitUntilModalCloses();

		// edit user
		editingUser(testUser);

		this.getWebDriverManager().waitForAnimation();

		Assert.assertEquals(
				getWebDriverManager().waitUntilElementIsDisplayed("xpath", String.format(userLastNameCellXpath, testUser)).getText(),
				"TestLN");
		Assert.assertEquals(
				getWebDriverManager().waitUntilElementIsDisplayed("xpath", String.format(firstNameCellXpath, testUser)).getText(),
				"TestFN");
		Assert.assertEquals(
				getWebDriverManager().waitUntilElementIsDisplayed("xpath", String.format(emailCellXpath, testUser)).getText(),
				"Test@Test");

	}

	@Parameters({"testUser"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testUser) {
		apiTestHelper.deleteUser(testUser);
	}
}
