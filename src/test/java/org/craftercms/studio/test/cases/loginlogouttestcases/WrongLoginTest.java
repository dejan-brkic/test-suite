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

public class WrongLoginTest extends StudioBaseTest{


	private String userName;
	private String password;
	private String alertWrongUserOrPasswordXpath;

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		alertWrongUserOrPasswordXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.loginalertfornonvalidcreds");
	}
	

	@Test(priority = 0)
	public void tryToLoginUsingNonValidCredientialsTest() {

		// login to application

		loginPage.loginToCrafterWithWrongCredentials(userName+"wrong", password);
		
		// Assert No login for invalid user.
		WebElement signInWrongUser = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed( "xpath",
				alertWrongUserOrPasswordXpath);

		Assert.assertTrue(signInWrongUser.isDisplayed());

		// login to application
		loginPage.loginToCrafterWithWrongCredentials(userName, password+"wrong");

		// Assert No login for invalid password.
		WebElement signInWrongPwd = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed( "xpath",
				alertWrongUserOrPasswordXpath);

		Assert.assertTrue(signInWrongPwd.isDisplayed());

	}

}
