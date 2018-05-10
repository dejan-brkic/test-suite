package org.craftercms.studio.test.cases.sitestestcases;

import org.craftercms.studio.test.cases.StudioBaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author luishernandez
 *
 */

// Test Case Studio- Sites ID:2
public class VerifyStudioAllowsToCreateSitesWithEachBlueprintTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String siteDropdownElementXPath;
	private String emptyBPSiteId;
	private String HeadlessBlogBPSiteId;
	private String HeadlessStoreBPSiteId;
	private String editorialBPSiteId;

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		emptyBPSiteId = "emptybpsite";
		HeadlessBlogBPSiteId = "headlessblogbpsite";
		HeadlessStoreBPSiteId = "headlessstorebpsite";
		editorialBPSiteId = "editorialbpsite";
	}

	public void createSiteUsingEmptyBluePrint() {
		// Click on the create site button
		homePage.clickOnCreateSiteButton();

		// Filling the name of site

		createSitePage.fillSiteName(emptyBPSiteId);

		// Filling the description of the site

		createSitePage.fillDescription("Description");

		// Select empty blueprint

		createSitePage.selectEmptyBluePrintOption();

		// Click on Create button
		createSitePage.clickOnCreateSiteButton();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", siteDropdownElementXPath);

		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", siteDropdownElementXPath)
				.isDisplayed());

	}

	public void createSiteUsingWebSiteEditorialBluePrint() {
		// Click on the create site button
		homePage.clickOnCreateSiteButton();

		// Filling the name of site

		createSitePage.fillSiteName(editorialBPSiteId);

		// Filling the description of the site

		createSitePage.fillDescription("Description");

		// Select empty blueprint

		createSitePage.selectWebSiteEditorialBluePrintOption();

		// Click on Create button
		createSitePage.clickOnCreateSiteButton();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", siteDropdownElementXPath);

		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", siteDropdownElementXPath)
				.isDisplayed());

	}

	public void createSiteUsingHeadlessBlogBluePrint() {
		// Click on the create site button
		homePage.clickOnCreateSiteButton();

		// Filling the name of site

		createSitePage.fillSiteName(HeadlessBlogBPSiteId);

		// Filling the description of the site

		createSitePage.fillDescription("Description");

		// Select empty blueprint

		createSitePage.selectHeadlessBlogBluePrintOption();

		// Click on Create button
		createSitePage.clickOnCreateSiteButton();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", siteDropdownElementXPath);

		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", siteDropdownElementXPath)
				.isDisplayed());
	}

	public void createSiteUsingHeadlessStoreBluePrint() {
		// Click on the create site button
		homePage.clickOnCreateSiteButton();

		// Filling the name of site

		createSitePage.fillSiteName(HeadlessStoreBPSiteId);

		// Filling the description of the site

		createSitePage.fillDescription("Description");

		// Select empty blueprint

		createSitePage.selectHeadlessStoreBluePrintOption();

		// Click on Create button
		createSitePage.clickOnCreateSiteButton();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", siteDropdownElementXPath);

		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", siteDropdownElementXPath)
				.isDisplayed());

	}

	public void step5() {
		dashboardPage.clickOnSitesOption();
		this.homePage.checkIfSiteIsListedOnSitesPage(emptyBPSiteId);
	}

	public void step8() {
		dashboardPage.clickOnSitesOption();
		this.homePage.checkIfSiteIsListedOnSitesPage(editorialBPSiteId);
	}

	public void step11() {
		dashboardPage.clickOnSitesOption();
		this.homePage.checkIfSiteIsListedOnSitesPage(HeadlessBlogBPSiteId);
	}

	public void step14() {
		dashboardPage.clickOnSitesOption();
		this.homePage.checkIfSiteIsListedOnSitesPage(HeadlessStoreBPSiteId);
	}

	@Test(priority = 0)
	public void verifyStudioAllowsToCreateSitesWithEachBlueprint() {

		// login to application
		loginPage.loginToCrafter(userName, password);

		driverManager.waitUntilLoginCloses();

		// Steps 3 y 4
		createSiteUsingEmptyBluePrint();

		step5();

		// Steps 6 y 7
		createSiteUsingWebSiteEditorialBluePrint();

		step8();

		// Steps 9 y 10
		createSiteUsingHeadlessBlogBluePrint();

		step11();

		// Steps 12 y 13
		createSiteUsingHeadlessStoreBluePrint();

		step14();

	}

	@AfterMethod
	public void afterTest() {
		this.homePage.deleteAllSites();
	}
}
