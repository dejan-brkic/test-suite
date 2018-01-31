package org.craftercms.studio.test.cases.sitedropdowntestcases;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.cases.BaseTest;
import org.openqa.selenium.WebElement;

/**
 * 
 * 
 * @author Juan Camacho A
 *
 */
//Test Case Studio- Site Dropdown ID:67
public class VerifyTheApplicationRedirectstoTheSiteConfigPageWhenTheSiteConfigOptionIsClicked extends BaseTest{

	private String userName;
	private String password;
	private String siteDropdownElementXPath;
	private String menuSitesButton;
	private String siteConfigLink;
	private String contentTypeOption;
	private String siteconfigGroupsOption;
	private static Logger logger = LogManager
			.getLogger(VerifyTheApplicationRedirectstoTheSiteConfigPageWhenTheSiteConfigOptionIsClicked.class);
	
	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdownmenuinnerxpath");
		menuSitesButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("preview.sites.menu.button");
		siteConfigLink = uiElementsPropertiesManager.getSharedUIElementsLocators()
		.getProperty("general.adminconsole");
		contentTypeOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.content_type_option");
		siteconfigGroupsOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.groups_option");
			
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
	public void verifyTheApplicationRedirectstoTheSiteConfigPageWhenTheSiteConfigOptionIsClicked() {

		// login to application
		logger.info("Login into Crafter");
		loginPage.loginToCrafter(
				userName,password);
		
		//Wait for login page to close
		driverManager.waitUntilLoginCloses();

		// Click on the create site button
		logger.info("Creating Web Editorial Site");
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
	
		//Expand the site bar
		logger.info("Opening the site bar");
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",siteDropdownElementXPath);
		WebElement sidebar = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",siteDropdownElementXPath);
		sidebar.click();
		Assert.assertTrue(this.driverManager.isElementPresentAndClickableByXpath(siteDropdownElementXPath));
		
		logger.info("Click on the Site ConFig Page");
		WebElement siteConfigLinkElement = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", siteConfigLink);
		siteConfigLinkElement.click();
		
		logger.info("Verify Site Config Page is displayed");
		this.driverManager.waitForAnimation();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", siteconfigGroupsOption);
		
		WebElement groupsOptionElement = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", siteconfigGroupsOption);
		Assert.assertTrue(groupsOptionElement.isDisplayed(),
				"ERROR: Groups Option is not present, verify if Site config Page is displayed");
		
		WebElement contentTypesOptionElement = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", contentTypeOption);
		Assert.assertTrue(contentTypesOptionElement.isDisplayed(),
				"ERROR: Content Type Option is not present, verify if Site config Page is displayed");
		}	
	}

