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

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.cases.StudioBaseTest;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

/**
 * 
 * @author luishernandez
 *
 */

public class VerifyThatApplicationDisplaysTheInContextEditIconProperlyTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String siteDropdownXpath;
	private String homeXpath;
	private String siteDropdownListElementXPath;
	private String inContextEditOption;
	private String expandPagesTree;
	private String siteDropdownElementXPath;
	private String newUserUserNameCreatedXpath;
	private String crafterLogo;
	private String createSiteButton;
	private String editReviewerGroupOption;
	private String groupsAddNewMembersInput;
	private String groupsAddNewMembersCheckbox;
	private String groupsAddNewMembersAutocompleteOption1;
	private String groupsAddNewMembersButton;
	private String siteconfigGroupsOption;
	private String userOptions;
	private String userOptionsLogout;
	private String inContextEditEnabledOnHomeTitle;
	private String inContextEditEnabledOnMainTitle;
	private static Logger logger = LogManager
			.getLogger(VerifyThatApplicationDisplaysTheInContextEditIconProperlyTest.class);

	@BeforeMethod
	public void beforeTest() {

		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		siteDropdownXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sitedropdown");
		homeXpath = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("general.home");
		siteDropdownListElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdownlielement");
		expandPagesTree = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.expand_Pages_Tree");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdownmenuinnerxpath");
		newUserUserNameCreatedXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.reviewerusernamecreated");
		crafterLogo = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("users.crafterlogo");
		createSiteButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsitebutton");
		editReviewerGroupOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("groups.edit_reviewer_group_option");
		groupsAddNewMembersCheckbox = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("groups.add_new_members_checkbox");
		groupsAddNewMembersInput = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("groups.add_new_members_input");
		groupsAddNewMembersAutocompleteOption1 = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("groups.add_new_members_autocomplete_option1");
		groupsAddNewMembersButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("groups.add_new_members_button");
		siteconfigGroupsOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.groups_option");
		userOptions = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.user_options");
		userOptionsLogout = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.user_options_logout");
		inContextEditEnabledOnHomeTitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.incontextedit.enabledonhometitle");
		inContextEditEnabledOnMainTitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.incontextedit.enabledonmaintitle");
		inContextEditOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.incontextedittopnavoption");
	}

	@Test(
			priority = 0)
	public void verifyThatApplicationDisplaysTheInContextEditIconProperlyTest() {

		this.setup();

		// Show site content panel
		if (!(this.driverManager.waitUntilElementIsPresent("xpath", siteDropdownListElementXPath)
				.getAttribute("class").contains("site-dropdown-open")))
			this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", siteDropdownXpath)
					.click();

		// expand pages folder
		previewPage.expandPagesTree();

		driverManager.getDriver().navigate().refresh();

		// expand home content
		previewPage.expandHomeTree();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", homeXpath).click();

		// click on history option
		previewPage.clickOnInContextEditOption();
		this.driverManager.waitForAnimation();

		this.driverManager.getDriver().switchTo().defaultContent();
		this.driverManager.getDriver().switchTo()
				.frame(this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("id", "engineWindow"));
		// Assertions
		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", inContextEditEnabledOnHomeTitle)
				.isDisplayed());
		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", inContextEditEnabledOnMainTitle)
				.isDisplayed());

		this.driverManager.getDriver().switchTo().defaultContent();

		// logout from Crafter
		logger.info("logout from Crafter");
		this.logoutFromCrafter();

		// login to application with reviewer user
		logger.info("login to application with reviewer user");
		loginPage.loginToCrafter("reviewer", "reviewer");

		driverManager.waitUntilLoginCloses();

		logger.info("Go to Preview Page");
		this.homePage.goToPreviewPage();

		// Expand the site bar
		this.driverManager.waitForAnimation();
		if (this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", siteDropdownElementXPath)
				.isDisplayed()) {
			if (!(this.driverManager.waitUntilElementIsPresent("xpath", siteDropdownListElementXPath)
					.getAttribute("class").contains("site-dropdown-open")))
				this.driverManager
						.driverWaitUntilElementIsPresentAndDisplayed("xpath", siteDropdownElementXPath)
						.click();
		} else
			throw new NoSuchElementException(
					"Site creation process is taking too long time and the element was not found");

		Assert.assertTrue(this.driverManager.isElementPresentAndClickableByXpath(siteDropdownElementXPath));
		this.driverManager.waitForAnimation();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", homeXpath).click();
		this.driverManager.waitForAnimation();

		// Assertions
		Assert.assertFalse(this.driverManager.isElementPresentAndClickableByXpath(inContextEditOption));

		this.driverManager.getDriver().switchTo().defaultContent();
		this.driverManager.getDriver().switchTo()
				.frame(this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("id", "engineWindow"));

		Assert.assertFalse(
				this.driverManager.isElementPresentAndClickableByXpath(inContextEditEnabledOnHomeTitle));
		Assert.assertFalse(
				this.driverManager.isElementPresentAndClickableByXpath(inContextEditEnabledOnMainTitle));

		this.driverManager.getDriver().switchTo().defaultContent();
	}

	public void setup() {
		// login to application
		loginPage.loginToCrafter(userName, password);

		// Wait for login page to closes
		driverManager.waitUntilLoginCloses();

		logger.info("Adding New User");
		this.addNewUser();

		logger.info("Add previous created user to Reviewer Group");
		this.addUserToReviewerGroup();

		logger.info("Go to Site Preview");
		this.goToSiteContentPagesStructure();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", expandPagesTree);

		this.driverManager.waitUntilSidebarOpens();
	}

	public void addNewUser() {

		// click On Users option
		this.driverManager.waitForAnimation();
		createSitePage.clickOnUsersOption();

		// click on new user button
		usersPage.addNewUser("reviewer");

		// Assert new users created is present
		WebElement newUserCreated = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				newUserUserNameCreatedXpath);

		Assert.assertTrue(newUserCreated.isDisplayed(), "ERROR: Recently created user is not displayed");

		// Switch to the form

		driverManager.getDriver().navigate().refresh();

		driverManager.getDriver().switchTo().defaultContent();

		this.driverManager.waitForAnimation();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",

				crafterLogo);

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",

				crafterLogo).click();

		createSitePage.clickOnSitesOption();
	}

	private void goToSiteContentPagesStructure() {

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", createSiteButton);

		this.driverManager.waitForAnimation();
		homePage.goToPreviewPage();

		this.driverManager.waitForAnimation();
		if (this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", siteDropdownElementXPath)
				.isDisplayed()) {
			if (!(this.driverManager.waitUntilElementIsPresent("xpath", siteDropdownListElementXPath)
					.getAttribute("class").contains("site-dropdown-open")))
				this.driverManager
						.driverWaitUntilElementIsPresentAndDisplayed("xpath", siteDropdownElementXPath)
						.click();
		} else
			throw new NoSuchElementException(
					"Site creation process is taking too long time and the element was not found");
	}

	public void addUserToReviewerGroup() {

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				siteconfigGroupsOption);

		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", siteconfigGroupsOption)

				.click();
		this.driverManager.waitForAnimation();
		driverManager.getDriver().switchTo().defaultContent();
		this.driverManager.getDriver().switchTo().activeElement();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",

				editReviewerGroupOption);

		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", editReviewerGroupOption)
				.click();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", groupsAddNewMembersCheckbox);

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", groupsAddNewMembersCheckbox)
				.click();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", groupsAddNewMembersInput)
				.sendKeys("reviewer");

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				groupsAddNewMembersAutocompleteOption1);
		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", groupsAddNewMembersAutocompleteOption1)
				.click();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				groupsAddNewMembersButton);

		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", groupsAddNewMembersButton)
				.click();

		
		this.driverManager.waitForAnimation();
		driverManager.getDriver().switchTo().defaultContent();
		this.driverManager.getDriver().switchTo().activeElement();
		
		this.driverManager.waitUntilAddUserModalCloses();
		this.driverManager.waitForAnimation();
		
		createSitePage.clickOnSitesOption();
	}

	private void logoutFromCrafter() {

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", userOptions);

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", userOptions).click();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				userOptionsLogout);

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", userOptionsLogout)
				.click();

	}

}
