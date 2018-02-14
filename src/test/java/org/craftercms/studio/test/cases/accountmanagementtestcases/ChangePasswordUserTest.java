package org.craftercms.studio.test.cases.accountmanagementtestcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.cases.StudioBaseTest;
import org.openqa.selenium.WebElement;

/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class ChangePasswordUserTest extends StudioBaseTest {
	
	private String userName;
	private String password;
	private String createSiteButtonXpath;
	final static Logger logger = LogManager.getLogger(ChangePasswordUserTest.class);


	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		createSiteButtonXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.createsitebutton");
		
	}
	
	@Test(priority = 0)
	public void changePasswordUser() {
		
		// login to application
		logger.info("Login into Crafter");
		loginPage.loginToCrafter(userName, password);

		// wait for login page to close
		driverManager.waitUntilLoginCloses();
		
		// wait for element is clickable
		createSitePage.clickAdmin();

		// click on settings
		createSitePage.clickOnSettingsOption();

		// change password
		logger.info("Change actual User Password");
		accountManagementPage.changeUserPassword(userName, "123456", "123456");

		// login to application
		logger.info("Login into Crafter with the new password");
		loginPage.loginToCrafter(userName, "123456");
		
		// wait for login page to close
		driverManager.waitUntilLoginCloses();
		
		// click On admin option
		createSitePage.clickAdmin();

		// click on settings
		createSitePage.clickOnSettingsOption();

		// change password
		logger.info("Restore user admin default password");
		accountManagementPage.changeUserPassword("123456", userName, "admin");

		// login to application
		logger.info("Login into Crafter");
		loginPage.loginToCrafter(userName, password);
		
		// wait for login page to close
		driverManager.waitUntilLoginCloses();

		// Assert create button is present.
		logger.info("Verify login is correct and Create Site page is displayed");
		WebElement createButton = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed(
				"xpath", createSiteButtonXpath);
		Assert.assertTrue(createButton.isDisplayed(),"Create Site Button is not displayed");

	}
}
