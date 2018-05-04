package org.craftercms.studio.test.cases.sitestestcases;

import org.craftercms.studio.test.cases.StudioBaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author luishernandez
 *
 */

public class VerifySitesPageWithSitesAndWithoutSitesTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String siteDropdownElementXPath;

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
	}

	public void createSite() {
		// Click on the create site button
		homePage.clickOnCreateSiteButton();

		// Filling the name of site

		createSitePage.fillSiteName();

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

		dashboardPage.clickOnSitesOption();
	}

	public void step3() {
		this.homePage.checkElementsOnSitePageWithoutSites();
	}

	public void step6() {
		this.homePage.checkElementsOnSitePageWithSites();
	}

	public void step7() {
		this.homePage.deleteAllSites();
		this.homePage.checkElementsOnSitePageWithoutSites();
	}

	@Test(priority = 0)
	public void verifySitesPageWithSitesAndWithoutSitesTest() {

		// login to application
		loginPage.loginToCrafter(userName, password);

		driverManager.waitUntilLoginCloses();

		step3();

		// Steps 4 y 5
		createSite();

		step6();

		step7();
	}

}
