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
package org.craftercms.studio.test.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.utils.UIElementsPropertiesManager;
import org.craftercms.studio.test.utils.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class UsersPage {

	private static final Logger logger = LogManager.getLogger(UsersPage.class);

	private WebDriverManager driverManager;
	private WebDriver driver;
	private String deleteUserOption;
	private String newUserOption;
	private String saveNewUserOption;
	private String deleteUserOptionNonAdmin;
	private String editUserOption;
	private String usersPageTitle;
	private String crafterLogo;
	private String deleteYesButtonXpath;
	private String deleteUsersRowsXpath;
	private String deleteNonAdminUserIconXpath;
	private String sitesOptionXpath;
	private String newUserFirstNameId;
	private String newUserLastNameId;
	private String newUserEmailId;
	private String newUserUserNameId;
	private String newUserPasswordId;
	private String newUserPasswordVerificationId;


	public UsersPage(WebDriverManager driverManager, UIElementsPropertiesManager uiElementsPropertiesManager) {
		this.driverManager = driverManager;
		this.driver = this.driverManager.getDriver();

		deleteUserOption = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("users.delete_option");
		newUserOption = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("users.new_user");
		saveNewUserOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("users.save_new_user");
		deleteUserOptionNonAdmin = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("users.delete_option_nonadminuser");
		editUserOption = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("users.edit_option");
		usersPageTitle = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("users.page_title");
		crafterLogo = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("users.crafterlogo");
		deleteYesButtonXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.deleteyesbutton");
		deleteUsersRowsXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.deleteusersrows");
		deleteNonAdminUserIconXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.deletenonadminrow");
		sitesOptionXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.homesites");
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

	}

	public UsersPage(WebDriver driver) {
		this.driver = driver;
	}

	// Try to delete the user connected

	public void clickDeleteOption() {
		logger.info("Deleting user");
		this.driverManager.waitForAnimation();
		WebElement previewLink = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				deleteUserOption);
		previewLink.click();
	}

	// Try to delete the user connected
	public void clickOnDeleteUser() {
		// Try to delete the user connected
		this.clickDeleteOption();
	}

	// Click on New User Button
	public void clickNewUserButton() {
		WebElement newUserButton = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				newUserOption);
		newUserButton.click();
	}

	// Click on New User Button
	public void clickOnNewUser() {
		// Click on New User Button
		this.clickNewUserButton();
	}

	// Click on Save New User Button
	public void clickSaveNewUserButton() {
		WebElement saveNewUser = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				saveNewUserOption);
		saveNewUser.click();
	}

	// Click on Save New User Button
	public void clickOnSaveNewUser() {
		// Click on Save New User Button
		this.clickSaveNewUserButton();
	}

	// Delete User
	public void clickDeleteOptionCreated() {
		WebElement deleteIcon = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				deleteUserOptionNonAdmin);
		deleteIcon.click();
	}

	// Delete User
	public void clickOnDeleteUserCreated() {
		// Delete User
		this.clickDeleteOptionCreated();
	}

	// edit User
	public void clickEditUsername(String username) {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				String.format(editUserOption, username)).click();
	}

	public WebDriverManager getDriverManager() {
		return driverManager;
	}

	public void setDriverManager(WebDriverManager driverManager) {
		this.driverManager = driverManager;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public Boolean isUsersPageTitlePresent() {
		return this.driverManager.isElementPresentByXpath(usersPageTitle);
	}

	public void clickOnCrafterLogo() {
		WebElement crafterLogoWebElement = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				crafterLogo);
		crafterLogoWebElement.click();
	}

	public void deleteUser(String username) {
		// click on the delete button
		this.driverManager.clickElement("xpath", String.format(deleteNonAdminUserIconXpath, username));

		// confirm and wait
		this.driverManager.clickElement("xpath", deleteYesButtonXpath);
		this.driverManager.waitUntilDeleteSiteModalCloses();
	}

	public void clickOnSitesOption() {
		WebElement siteOptionWebElement = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				sitesOptionXpath);
		siteOptionWebElement.click();
	}
	
	public void addNewUser(String userName) {

		// click on new user button
		clickOnNewUser();

		// Follow the form
		this.driverManager.sendText("xpath", newUserFirstNameId, "FirstName");

		this.driverManager.sendText("xpath", newUserLastNameId,"LastName");

		this.driverManager.sendText("xpath", newUserEmailId, "email@email.com");

		this.driverManager.sendText("xpath", newUserUserNameId, userName);

		this.driverManager.sendText("xpath", newUserPasswordId, userName);

		this.driverManager.sendText("xpath", newUserPasswordVerificationId, userName);

		// Save Button
		clickOnSaveNewUser();
		this.driverManager.waitForAnimation();
		this.driverManager.waitUntilAddUserModalCloses();
	}
}