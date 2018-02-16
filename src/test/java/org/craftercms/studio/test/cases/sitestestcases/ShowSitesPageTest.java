/**
 * 
 */
package org.craftercms.studio.test.cases.sitestestcases;


import org.craftercms.studio.test.cases.StudioBaseTest;
import org.craftercms.studio.test.utils.APIConnectionManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Luis Alonso Hernandez Monge
 *
 */

public class ShowSitesPageTest extends StudioBaseTest {
	

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
	public void verifyThatApplicationShowsSitesPageWhenUserClicksSitesContextualNavigationOption() {

		// login to application
		loginPage.loginToCrafter(userName, password);
		
		//Wait for login page to close
		driverManager.waitUntilLoginCloses();

		// Click on the Users Contextual Navigation Option
		homePage.clickUsersContextualNavigationOption();

	    this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",newUserXpath);
		
		// go back to Sites Page
		usersPage.clickOnSitesOption();

		// Checking if the Sites page was Loaded
		this.driverManager.waitForAnimation();
		Assert.assertTrue(usersPage.getDriverManager().getDriver().getCurrentUrl()
				.equals(apiConnectionManager.getHeaderLocationBase()+"/studio/#/sites"));
		
		// Checking if the Users title is displayed on the current page
		Assert.assertTrue(homePage.isSitePageTitlePresent());
				
		// select the about us option
		homePage.goToDashboardPage();

		dashboardPage.clickOnSitesOption();

		// Checking if the Sites page was Loaded
		Assert.assertTrue(dashboardPage.getDriverManager().getDriver().getCurrentUrl()
				.equals(apiConnectionManager.getHeaderLocationBase()+"/studio/#/sites"));

		// Checking if the Users title is displayed on the current page
		Assert.assertTrue(homePage.isSitePageTitlePresent());

	}
}
