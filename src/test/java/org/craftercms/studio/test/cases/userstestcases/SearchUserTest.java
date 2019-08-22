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

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import org.craftercms.studio.test.cases.StudioBaseTest;


/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class SearchUserTest extends StudioBaseTest {


	private String userName;
	private String password;
	private String newUserFirstNameId;
	private String newUserLastNameId;
	private String newUserEmailId;
	private String newUserUserNameId;
	private String newUserPasswordId;
	private String newUserPasswordVerificationId;
	private String userSearchXpath;
	private String newUserUserNameXpath;
	private String searchResultUserNameXpath;
	private String deleteYesButtonXpath;
	private String usersRowsXpath;

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
		userSearchXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.userssearchinput");
		searchResultUserNameXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.newuserusernamecell");
		newUserUserNameXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.usernamecreated");
		deleteYesButtonXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.deleteyesbutton");
		usersRowsXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.usersrows");
	}

	public void createUser() {

		// click On Users option
		createSitePage.clickOnUsersOption();

		// click on new user button
		usersPage.clickOnNewUser();

		// Follow the form
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", newUserFirstNameId).sendKeys("Name");

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", newUserLastNameId).sendKeys("Last Name");

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", newUserEmailId)
				.sendKeys("email@email.com");

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", newUserUserNameId).sendKeys("username");

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", newUserPasswordId).sendKeys("password");

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", newUserPasswordVerificationId)
				.sendKeys("password");

		// Save Button
		usersPage.clickOnSaveNewUser();
	}

	public void searchUsers() {

		// Search user recently created
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", userSearchXpath).sendKeys("username");

		// Assert to search is properly
		String searchUsername = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", searchResultUserNameXpath).getText();
		Assert.assertEquals(searchUsername, "username", "ERROR: searched username is not displayed");

		// Search user admin

		// Cleaning search field
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", userSearchXpath).clear();

		// Search admin
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", userSearchXpath).sendKeys("admin");

		// Assert to search is properly

		String searchAdminUser = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", searchResultUserNameXpath).getText();

		Assert.assertEquals(searchAdminUser, "admin", "ERROR: admin user is not displayed");

	}

	@Test(priority = 0)

	public void searchUser() {

		// login to applicatioN
		loginPage.loginToCrafter(userName, password);
		
		//Wait for login page to close
		getWebDriverManager().waitUntilLoginCloses();

		// create new user
		createUser();

		// Assert new users created is present
		WebElement newUserCreated = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
				newUserUserNameXpath);

		Assert.assertTrue(newUserCreated.isDisplayed());

		// search users
		searchUsers();
		
		// Cleaning search field
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", userSearchXpath).clear();

		// Click on delete user
		getWebDriverManager().getDriver().navigate().refresh();
		usersPage.clickOnDeleteUserCreated();

		// Confirmation to delete user connected
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", deleteYesButtonXpath).click();

		// Assert new users created is deleted

		Assert.assertTrue(this.getWebDriverManager().elementHasChildsByXPath(usersRowsXpath));

		List<WebElement> usersList = this.getWebDriverManager().getDriver().findElements(By.xpath(usersRowsXpath));

		Assert.assertTrue(usersList.size() == 1);

	}
}
