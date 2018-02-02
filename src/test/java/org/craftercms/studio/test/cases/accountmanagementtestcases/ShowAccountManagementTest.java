package org.craftercms.studio.test.cases.accountmanagementtestcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.cases.BaseTest;
import org.craftercms.studio.test.utils.APIConnectionManager;

public class ShowAccountManagementTest extends BaseTest {

	private String userName;
	private String password;
	private APIConnectionManager apiConnectionManager;
	private String accountManagementTitle;
	private static Logger logger = LogManager.getLogger(ShowAccountManagementTest.class);

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		apiConnectionManager = new APIConnectionManager();
		accountManagementTitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("accountManagement.pageTitle");
	}

	@Test(priority = 0)
	public void verifyThatApplicationShowsAccountManagementPageWhenUserClicksSettingsContextualNavigationOption() {

		// login to application
		logger.info("Login into Crafter");
		loginPage.loginToCrafter(userName, password);

		// wait for login page to close
		driverManager.waitUntilLoginCloses();

		// wait for element is clickable
		createSitePage.clickAdmin();

		// click on settings
		createSitePage.clickOnSettingsOption();

		this.driverManager.waitForAnimation();
		this.driverManager.waitUntilElementIsDisplayed("xpath", accountManagementTitle);
		
		// Checking if the Sites page was Loaded
		Assert.assertTrue(accountManagementPage.getDriverManager().getDriver().getCurrentUrl()
				.equals(apiConnectionManager.getHeaderLocationBase() + "/studio/#/settings"));

		// Checking if the Users title is displayed on the current page
		Assert.assertTrue(accountManagementPage.isAccountManagementTitlePresent());

	}
}
