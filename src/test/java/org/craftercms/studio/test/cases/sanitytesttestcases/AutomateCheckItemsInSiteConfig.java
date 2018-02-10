package org.craftercms.studio.test.cases.sanitytesttestcases;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.craftercms.studio.test.cases.BaseTest;
import org.openqa.selenium.WebElement;

/**
 * 
 * 
 * @author Juan Camacho A
 *
 */
//Test Case created to cover ticket https://github.com/craftercms/craftercms/issues/1448
public class AutomateCheckItemsInSiteConfig extends BaseTest{

	private String userName;
	private String password;
	private String siteDropdownElementXPath;
	private String createSiteErrorNotificationWindow;
	private String adminConsole;
	private String menuSitesButton;
	
	private String dashboardSiteContent;

	private String siteConfigcontentTypesOption;
	private String siteConfigConfigurationOption;
	private String siteConfigGroupsOption;
	private String siteConfigAuditOption;
	private String siteConfigPublishOperationsOption;
	private String siteConfigWorkflowStatesoption;
	private String siteConfigLoggingLevelsOption;
	private String siteConfigLogConsoleOption;
	
	
	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		createSiteErrorNotificationWindow = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.createsite.errowindow");
		menuSitesButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("preview.sites.menu.button");
		adminConsole = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.adminconsole");
		dashboardSiteContent = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.site_content");
		siteConfigcontentTypesOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.content_type_option");
		siteConfigConfigurationOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.configuration_option");
		siteConfigGroupsOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.groups_option");
		siteConfigAuditOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.audit_option");
		siteConfigPublishOperationsOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.publish_operations_option");
		siteConfigWorkflowStatesoption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.workflowstates_option");
		siteConfigLoggingLevelsOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.logginglevels_option");
		siteConfigLogConsoleOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.logconsole_option");
		
	}
	
	public void deleteSite() {
		
		this.driverManager.getDriver().switchTo().defaultContent();
		
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable(
				"xpath", menuSitesButton).click();

		// Click on Delete icon
		homePage.clickOnDeleteSiteIcon();

		// Click on YES to confirm the delete.
		homePage.clickOnYesToDeleteSite();
		
		//Refresh the page
		driverManager.getDriver().navigate().refresh();

	}

	@AfterMethod
	public void afterTest() {
		deleteSite();
	}

	@Test(priority = 0)
	public void automateCheckItemsInSiteConfig() {

		// login to application
		loginPage.loginToCrafter(
				userName,password);
		
		//Wait for login page to close
		driverManager.waitUntilLoginCloses();

		// Click on the create site button
		homePage.clickOnCreateSiteButton();

		// Filling the name of site
		createSitePage.fillSiteName();

		// Filling the description of the site
		createSitePage.fillDescription("Description");

		// Open blueprint combo
		// Select blueprint
		createSitePage.selectWebSiteEditorialBluePrintOption();

		// Click on Create button
		createSitePage.clickOnCreateSiteButton();
		
		//Verify No error messages after clicking on the Create button
		Assert.assertFalse(driverManager.isElementPresentByXpath(createSiteErrorNotificationWindow));
		this.driverManager.waitWhileElementIsDisplayedAndClickableByXpath(siteDropdownElementXPath);
		
		WebElement sidebar = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",dashboardSiteContent);
		
		sidebar.click();
		
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", adminConsole);

		WebElement siteConfigButton = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				adminConsole);

		siteConfigButton.click();
		
		this.driverManager.waitForAnimation();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", siteConfigGroupsOption);
		
//		Verify that there are no error messages displayed and that the following items are listed:
//			- Content Types - Configuration - Groups - Audit - Bulk Operations - Workflow States
//			- Logging Levels - Log Console

		Assert.assertTrue(driverManager.isElementPresentByXpath(siteConfigcontentTypesOption),
				"ERROR: Content Types option is not present");
		
		Assert.assertTrue(driverManager.isElementPresentByXpath(siteConfigConfigurationOption),
				"ERROR: Configuration option is not present");
		
		Assert.assertTrue(driverManager.isElementPresentByXpath(siteConfigGroupsOption),
				"ERROR: Groups option is not present");
		
		Assert.assertTrue(driverManager.isElementPresentByXpath(siteConfigAuditOption),
				"ERROR: Audit option is not present");
		
		Assert.assertTrue(driverManager.isElementPresentByXpath(siteConfigPublishOperationsOption),
				"ERROR: Publishing Operations option is not present");
		
		Assert.assertTrue(driverManager.isElementPresentByXpath(siteConfigWorkflowStatesoption),
				"ERROR: Workflow States option is not present");
		
		Assert.assertTrue(driverManager.isElementPresentByXpath(siteConfigLoggingLevelsOption),
				"ERROR: Logging Levels option is not present");
		
		Assert.assertTrue(driverManager.isElementPresentByXpath(siteConfigLogConsoleOption),
				"ERROR: Log Console option is not present");
	
	}

}
