package org.craftercms.studio.test.cases.sitestestcases;

import org.apache.commons.lang3.RandomStringUtils;
import org.craftercms.studio.test.cases.StudioBaseTest;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

/**
 * 
 * 
 * @author luishernandez
 *
 */

// related to ticket on windows:
// https://github.com/craftercms/craftercms/issues/1905
public class CreateSiteWithWebSiteEditorialBluePrintTestForDeliveryCheck extends StudioBaseTest {

	private String userName;
	private String password;
	private String siteDropdownElementXPath;
	private String siteId;

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		siteId="testsite" + RandomStringUtils.randomAlphabetic(5).toLowerCase();
	}

	@Test(priority = 0)
	public void createSiteWithWebSiteEditorialBluePrintTestForDeliveryCheck() {

		// login to application
		loginPage.loginToCrafter(userName, password);

		driverManager.waitUntilLoginCloses();

		// Click on the create site button
		homePage.clickOnCreateSiteButton();

		// Filling the name of site
		createSitePage.fillSiteName(siteId);

		// Filling the description of the site

		createSitePage.fillDescription("Description");

		// Open blueprint combo
		// Select blueprint

		createSitePage.selectWebSiteEditorialBluePrintOption();

		// Click on Create button

		createSitePage.clickOnCreateSiteButton();

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", siteDropdownElementXPath);

		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", siteDropdownElementXPath)
				.isDisplayed());

		// go to delivery folder and init site for test
		int exitCode = this.driverManager
				.goToFolderAndExecuteInitSiteScriptThroughCommandLine(siteId);
			
		Assert.assertTrue(exitCode == 0, "Init site process failed");
		
		//saving the siteId for the dependent test cases to this test case.
		constantsPropertiesManager.setProperty("general.currentsiteid", siteId);
	}
}
