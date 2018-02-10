package org.craftercms.studio.test.cases.userstestcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.craftercms.studio.test.cases.BaseTest;

/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class DeleteUserTest extends BaseTest {

	private String userName;
	private String password;
	private String usersRowsXpath;

	@BeforeMethod
	public void beforeTest() {

		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		usersRowsXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.usersrows");
	}

	@Test(priority = 0)
	public void verifyThatStudioAllowsToDeleteAnUserTest() {

		// login to application
		loginPage.loginToCrafter(userName, password);

		// Wait for login page to close
		driverManager.waitUntilLoginCloses();

		// click On Users option
		createSitePage.clickOnUsersOption();

		// Deleting user
		this.driverManager.waitForAnimation();
		this.driverManager.waitUntilPageLoad();

		usersPage.deleteAllUsersExceptAdmin();

		// Assert new users created is deleted
		this.driverManager.waitForAnimation();
		driverManager.getDriver().navigate().refresh();
		this.driverManager.waitForAnimation();
		Assert.assertTrue(this.driverManager.elementHasChildsByXPath(usersRowsXpath));

		this.driverManager.waitForAnimation();
		Assert.assertTrue((this.driverManager.getDriver().findElements(By.xpath(usersRowsXpath))).size() == 1);

	}
}
