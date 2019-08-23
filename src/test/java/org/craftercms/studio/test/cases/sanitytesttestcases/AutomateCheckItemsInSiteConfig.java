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
package org.craftercms.studio.test.cases.sanitytesttestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.craftercms.studio.test.cases.StudioBaseTest;
import org.openqa.selenium.WebElement;

/**
 * 
 * 
 * @author Juan Camacho A
 *
 */
// Test Case created to cover ticket
// https://github.com/craftercms/craftercms/issues/1448
public class AutomateCheckItemsInSiteConfig extends StudioBaseTest {

	private String userName;
	private String password;
	private String siteDropdownElementXPath;
	private String createSiteErrorNotificationWindow;
	private String adminConsole;
	private String dashboardSiteContent;
	private String siteConfigcontentTypesOption;
	private String siteConfigConfigurationOption;
	private String siteConfigAuditOption;
	private String siteConfigPublishOperationsOption;
	private String siteConfigWorkflowStatesoption;
	private String siteConfigLogConsoleOption;
	private String siteConfigRemoteRepositoriesOption;
	private String siteConfigGraphiqlOption;

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		createSiteErrorNotificationWindow = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.createsite.errowindow");
		adminConsole = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.adminconsole");
		dashboardSiteContent = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.site_content");
		siteConfigcontentTypesOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.content_type_option");
		siteConfigConfigurationOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.configuration_option");
		siteConfigAuditOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.audit_option");
		siteConfigPublishOperationsOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.publish_operations_option");
		siteConfigWorkflowStatesoption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.workflowstates_option");
		siteConfigLogConsoleOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.logconsole_option");
		siteConfigRemoteRepositoriesOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.remoterepositories_option");
		siteConfigGraphiqlOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.graphiql_option");

	}

	@Parameters({"testId"})
	@Test()
	public void automateCheckItemsInSiteConfig(String testId) {

		// login to application
		loginPage.loginToCrafter(userName, password);

		// Wait for login page to close
		getWebDriverManager().waitUntilLoginCloses();

		// Click on the create site button
		homePage.clickOnCreateSiteButton();

		//select blueprint, set site name, set description, click review and create site
		createSitePage.selectWebSiteEditorialBluePrintOption()
				.setSiteName(testId)
				.setDescription("Description")
				.clickReviewAndCreate()
				.clickOnCreateButton();

		// Verify No error messages after clicking on the Create button
		Assert.assertFalse(getWebDriverManager().isElementPresentByXpath(createSiteErrorNotificationWindow));
		this.getWebDriverManager().waitWhileElementIsDisplayedAndClickableByXpath(siteDropdownElementXPath);

		WebElement sidebar = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
				dashboardSiteContent);

		sidebar.click();

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", adminConsole);

		WebElement siteConfigButton = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
				adminConsole);

		siteConfigButton.click();

		this.getWebDriverManager().waitForAnimation();

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				siteConfigPublishOperationsOption);

		// Verify that there are no error messages displayed and that the following
		// items are listed:
		// - Content Types - Configuration - Audit - Bulk Operations - Workflow States
		// - Logging Levels - Log Console

		Assert.assertTrue(getWebDriverManager().isElementPresentByXpath(siteConfigcontentTypesOption),
				"ERROR: Content Types option is not present");

		Assert.assertTrue(getWebDriverManager().isElementPresentByXpath(siteConfigConfigurationOption),
				"ERROR: Configuration option is not present");

		Assert.assertTrue(getWebDriverManager().isElementPresentByXpath(siteConfigAuditOption),
				"ERROR: Audit option is not present");

		Assert.assertTrue(getWebDriverManager().isElementPresentByXpath(siteConfigPublishOperationsOption),
				"ERROR: Publishing Operations option is not present");

		Assert.assertTrue(getWebDriverManager().isElementPresentByXpath(siteConfigWorkflowStatesoption),
				"ERROR: Workflow States option is not present");

		Assert.assertTrue(getWebDriverManager().isElementPresentByXpath(siteConfigRemoteRepositoriesOption),
				"ERROR: Remote Repositories option is not present");

		Assert.assertTrue(getWebDriverManager().isElementPresentByXpath(siteConfigLogConsoleOption),
				"ERROR: Log Console option is not present");

		Assert.assertTrue(getWebDriverManager().isElementPresentByXpath(siteConfigGraphiqlOption),
				"ERROR: GraphiQL option is not present");
	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
