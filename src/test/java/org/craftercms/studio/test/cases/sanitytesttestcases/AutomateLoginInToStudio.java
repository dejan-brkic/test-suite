package org.craftercms.studio.test.cases.sanitytesttestcases;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.craftercms.studio.test.cases.StudioBaseTest;

/**
 * 
 * @author Juan Camacho
 */

//Test to cover ticket https://github.com/craftercms/craftercms/issues/1435
public class AutomateLoginInToStudio extends StudioBaseTest{

	private String userName;
	private String password;
	private String createSiteButtonXpath;
	private String sitesPageTitle;
	private String sitesPageURL;
	
	@BeforeMethod 
	public void beforeTest() {
		
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		createSiteButtonXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.createsitebutton");
		sitesPageTitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.sitespagetitle"); 
		sitesPageURL = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.sitepageurl");
	}

	@Test(priority = 0)
	public void automateLoginInToStudio() {

		// login to application
		loginPage.loginToCrafter(userName, password);
		
		//Wait for login page to close
		driverManager.waitUntilLoginCloses();

		// Assert create button is present.
		WebElement createButton = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed( "xpath",
				createSiteButtonXpath);

		//Assert is the Create Button Present
		Assert.assertTrue(createButton.isDisplayed());
		
		//Assert Is the Site Page title Displayed
		Assert.assertTrue(driverManager.isElementPresentAndClickableByXpath(sitesPageTitle));
		
		//Assert URL of the page is the correct
		String siteURL = driverManager.getDriver().getCurrentUrl();
		Assert.assertTrue(siteURL.contains(sitesPageURL));
	
	}

}