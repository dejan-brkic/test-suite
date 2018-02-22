/**
 * 
 */
package org.craftercms.studio.test.cases.userstestcases;


import org.craftercms.studio.test.cases.StudioBaseTest;
import org.craftercms.studio.test.utils.APIConnectionManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Luis Alonso Hernandez Monge
 *
 */

public class ShowUsersPageTest extends StudioBaseTest {
	

	private APIConnectionManager apiConnectionManager;
	private String userName;
	private String password;
	private String newUserXpath;
	
	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		newUserXpath = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("users.new_user");
		apiConnectionManager = new APIConnectionManager();
	
	}

	@Test(priority = 0)
	public void verifyThatStudioAllowsToAccessTheUsersPageWhenUserClicksOnUsersOptionTopNavigationBar() {

		// login to application
		loginPage.loginToCrafter(userName, password);
		
		//Wait for login page to close
		driverManager.waitUntilLoginCloses();

		// Click on the Users Contextual Navigation Option
		homePage.clickUsersContextualNavigationOption();

	    this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",newUserXpath);
		
	    // Checking if the UsersPage was Loaded
		Assert.assertTrue(usersPage.getDriverManager().getDriver().getCurrentUrl()
				.equals(apiConnectionManager.getHeaderLocationBase()+"/studio/#/users"));

		// Checking if the Users title is displayed on the current page
		Assert.assertTrue(usersPage.isUsersPageTitlePresent());

		// go back to Sites Page
		usersPage.clickOnCrafterLogo();

		// select the about us option
		homePage.goToDashboardPage();

		this.driverManager.waitForAnimation();
		dashboardPage.clickUsersContextualNavigationOption();

		// Checking if the UsersPage was Loaded
		this.driverManager.waitForAnimation();
		Assert.assertTrue(usersPage.getDriverManager().getDriver().getCurrentUrl()
				.equals(apiConnectionManager.getHeaderLocationBase()+"/studio/#/users"));

		// Checking if the Users title is displayed on the current page
		this.driverManager.waitForAnimation();
		Assert.assertTrue(usersPage.isUsersPageTitlePresent());

		// go back to Sites Page
		usersPage.clickOnCrafterLogo();

	}
}
