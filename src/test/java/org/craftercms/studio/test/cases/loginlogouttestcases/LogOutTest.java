package org.craftercms.studio.test.cases.loginlogouttestcases;


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

public class LogOutTest extends StudioBaseTest{

	private String userName;
	private String password;
	private String loginButtonLocator;

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		loginButtonLocator = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("login.login");

	}


	@Test(priority = 0)
	public void logoutFromStudioTest() {

		// login to application

		loginPage.loginToCrafter(userName, password);

		//Wait for login page to close
		driverManager.waitUntilLoginCloses();
		
		// LogOut
		homePage.clickLogoutOutCrafter();

		// Verify login is fine
		WebElement signInButtom = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				loginButtonLocator);

		Assert.assertTrue(signInButtom.isDisplayed());

	}

}
