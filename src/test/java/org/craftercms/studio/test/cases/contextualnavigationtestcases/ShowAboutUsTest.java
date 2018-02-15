package org.craftercms.studio.test.cases.contextualnavigationtestcases;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.craftercms.studio.test.cases.StudioBaseTest;

/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class ShowAboutUsTest extends StudioBaseTest{
	private String userName;
	private String password;
	private String aboutUsInfoXpath;

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		aboutUsInfoXpath = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("general.aboutus.studiodatacontainer");
	}

	@Test(priority = 0)
	public void showAboutUsPageTest() {

		// login to application
		loginPage.loginToCrafter(userName, password);
		
		//Wait for login page to close
		driverManager.waitUntilLoginCloses();

		// click On help option
		createSitePage.clickOnHelpOption();

		// select the about us option
		createSitePage.clickOnAboutOption();

		// Assert 
		WebElement aboutUsInfo = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				aboutUsInfoXpath);

		Assert.assertTrue(aboutUsInfo.isDisplayed());

	}
}
