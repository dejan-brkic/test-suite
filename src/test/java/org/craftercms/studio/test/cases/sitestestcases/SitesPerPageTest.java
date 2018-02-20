package org.craftercms.studio.test.cases.sitestestcases;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.craftercms.studio.test.cases.StudioBaseTest;

/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class SitesPerPageTest extends StudioBaseTest{

	private String userName;
	private String password;
	private String sitesPerPageInputXpath;
	private String firstSiteXpath;
	private String secondSiteXpath;
	private String thirdSiteXpath;
	private String createSiteButton;
	private String siteDropdownElementXPath;
	private String topNavDeleteOption;
	private String topNavEditOption;
	private String topNavSitesOption;

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		
		sitesPerPageInputXpath= uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.sitesperpageinput");
		firstSiteXpath= uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.firstSiteNameOnList");
		secondSiteXpath= uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.secondSiteNameOnList");
		thirdSiteXpath= uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.thirdSiteNameOnList");
		createSiteButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.createsitebutton");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		topNavDeleteOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.deletetopnavoption");
		topNavEditOption= uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.edittopnavoption");
		topNavSitesOption= uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.preview.sitesoption");
		
		// login to application
		loginPage.loginToCrafter(userName, password);
		
		//Wait for login page to close
		driverManager.waitUntilLoginCloses();
		
		// Create site 1
		createSitesRandom();
		// Create site 2
		createSitesRandom();
		// Create site 3
		createSitesRandom();

	}

	@AfterMethod
	public void afterTest() {
		// Delete all sites
		deleteSites();
	}

	public void createSitesRandom() {

		// Click on the create site button
		homePage.clickOnCreateSiteButton();

		// Filling the name of site
		createSitePage.fillSiteName();

		// Filling the description of the site
		createSitePage.fillDescription("Description");

		// Open blueprint combo
		createSitePage.openBlueprintCombo();

		// Select empty blueprint
		createSitePage.selectEmptyBlueprint();

		// Click on Create button
		createSitePage.clickOnCreateSiteButton();
		
		this.driverManager.waitWhileElementIsDisplayedAndClickableByXpath(siteDropdownElementXPath);

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", topNavDeleteOption);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", topNavEditOption);

		Assert.assertTrue(this.driverManager.isElementPresentAndClickableByXpath(siteDropdownElementXPath));

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", topNavSitesOption).click();
	}

	public void filters() {

		// Show 1 sites
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				sitesPerPageInputXpath).clear();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				sitesPerPageInputXpath).sendKeys("1");

		// Assert only 1 sites displayed
		WebElement page1 = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				firstSiteXpath);
		Assert.assertTrue(page1.isDisplayed());

		// Show 2 sites
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				sitesPerPageInputXpath).clear();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				sitesPerPageInputXpath).sendKeys("2");

		// Asser only 2 sites displayed
		WebElement page2 = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				secondSiteXpath);
		Assert.assertTrue(page2.isDisplayed());

		// Show 3 site
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				sitesPerPageInputXpath).clear();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				sitesPerPageInputXpath).sendKeys("3");

		// Asser only 3 site displayed
		WebElement page3 = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				thirdSiteXpath);
		Assert.assertTrue(page3.isDisplayed());

		// Show 10 sites
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				sitesPerPageInputXpath).clear();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				sitesPerPageInputXpath).sendKeys("10");
		
		driverManager.getDriver().navigate().refresh();
	}

	public void deleteSites() {

		//delete all the sites present
		this.driverManager.isElementPresentAndClickableByXpath(createSiteButton);
		homePage.deleteAllSites();
	}

	@Test(priority = 0)
	public void verifyThatTheShowSitesPerPageWorksProperlyTest() {
		// filters
		filters();
	}
}
