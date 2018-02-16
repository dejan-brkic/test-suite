package org.craftercms.studio.test.cases.sanitytesttestcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.craftercms.studio.test.cases.StudioBaseTest;

/**
 * 
 * @author Juan Camacho
 */

// Test to cover ticket https://github.com/craftercms/craftercms/issues/1434
public class AutomateOpeningStudioInActualBrowser extends StudioBaseTest {

	private String crafterLoginImage;
	private String userNameXpath;
	private String passwordXpath;
	private String loginXpath;
	private String loginLanguageSelector;

	@BeforeMethod
	public void beforeTest() {

		crafterLoginImage = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("login.crafterloginimage");
		userNameXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("login.username");
		passwordXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("login.password");
		loginXpath = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("login.login");
		loginLanguageSelector = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("login.languageselector"); 
	}

	@Test(priority = 0)
	public void automateOpeningStudioInActualBrowser() {

		// Verfy Login page is displayed when URL 'localhost:8080/studio' is load in a browser
		
		//Verify Crafter Logo Image is displayed
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", crafterLoginImage);
		Assert.assertTrue(driverManager.isElementPresentByXpath(crafterLoginImage),
				"ERROR:  Crafter Logo image is not displayed, check localhost:8080/studio open the login page");

		//Verify Fields are displayed
		Assert.assertTrue(driverManager.isElementPresentByXpath(userNameXpath),
				"ERROR: User name field is not displayed");
				
		Assert.assertTrue(driverManager.isElementPresentByXpath(passwordXpath),
				"ERROR: Password field is not displayed");
				
		Assert.assertTrue(driverManager.isElementPresentByXpath(loginXpath),
				"ERROR: Login submit button is not displayed");
		
		Assert.assertTrue(driverManager.isElementPresentByXpath(loginLanguageSelector),
				"ERROR: Login language Selector is not displayed");

	}

}