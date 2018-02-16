package org.craftercms.studio.test.cases.sitestestcases;


import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.craftercms.studio.test.cases.StudioBaseTest;

/**
 * 
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class CancelCreateSiteProcessTest extends StudioBaseTest{

	private String userName;
	private String password;
	private String sitesPageTitleLocator;
	
	
	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		sitesPageTitleLocator = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("sites.pagetitle");

	}

	@Test(priority = 0)
	public void verifyTheCancellationOfTheCreateSiteProcessTest() {

		// login to application
		loginPage.loginToCrafter(
				userName,password);

		//Wait for login page to close
		driverManager.waitUntilLoginCloses();

		// Click on the create site button
		homePage.clickOnCreateSiteButton();

		// Filling the name of site
		createSitePage.fillSiteName();

		// Filling the Id of the site
		createSitePage.fillIdSite("");

		// Filling the description of the site
		createSitePage.fillDescription("Description");

		// Open blueprint combo

		createSitePage.openBlueprintCombo();

		// Click on Cancel button
		createSitePage.clickOnCancelButtonOfTheCreateSiteProcess();

		// Assert
		WebElement siteName = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed( "xpath",
				sitesPageTitleLocator);

		Assert.assertTrue(siteName.isDisplayed());

	}

}
