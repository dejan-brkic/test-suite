package org.craftercms.studio.test.cases.contextualnavigationtestcases;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.craftercms.studio.test.cases.StudioBaseTest;


/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class ShowDocumentationTest extends StudioBaseTest{
	
	private String userName;
	private String password;

	@BeforeMethod
	public void beforeTest() {
		
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
	}

	@Test(priority = 0)
	public void showDocumentationPageTest() {

		// login to application
		loginPage.loginToCrafter(userName, password);
		
		//Wait for login page to close
		driverManager.waitUntilLoginCloses();

		// click On help option
		createSitePage.clickOnHelpOption();

		// select the about us option
		createSitePage.clickOnDocumentationOption();
	}
}
