package org.craftercms.studio.test.cases.userstestcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.cases.StudioBaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class TryToDeleteUserConnectedTest extends StudioBaseTest {

	private static final Logger logger = LogManager.getLogger(TryToDeleteUserConnectedTest.class);

	private String userName;
	private String password;
	private String deleteYesButtonXpath;
	private String deleteNotAllowedMessageXpath;
	private String errorMessageXpath;

	@BeforeClass
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		deleteYesButtonXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.deleteyesbutton");
		deleteNotAllowedMessageXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.deletenotallowedparagraph");
		errorMessageXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.deletenotallowederrorparagraph");

	}

	@Test(priority = 0)
	public void tryToDeleteTheAdminUser() {
		logger.info("Starting test case");
		// login to application
		loginPage.loginToCrafter(userName, password);	
	
		//Wait for login page to close
		driverManager.waitUntilLoginCloses();
		
		// click On Users option
		createSitePage.clickOnUsersOption();

		// Try to delete the user current
		this.driverManager.waitForAnimation();
		usersPage.clickOnDeleteUser();

		// Confirmation to delete user connected
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", deleteYesButtonXpath).click();

		WebElement validation = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
			deleteNotAllowedMessageXpath);

		// Verify
		Assert.assertTrue(this.driverManager.isElementPresentByXpath(deleteNotAllowedMessageXpath));
		Assert.assertTrue(this.driverManager.isElementPresentByXpath(errorMessageXpath));
		Assert.assertTrue(validation.getText().contains("Unable to delete user"));

	}

}
