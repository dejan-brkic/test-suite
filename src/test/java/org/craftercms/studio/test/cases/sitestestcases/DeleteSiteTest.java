package org.craftercms.studio.test.cases.sitestestcases;

import org.craftercms.studio.test.cases.StudioBaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class DeleteSiteTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String deletedSiteRow;
	private String createSiteButton;

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		deletedSiteRow = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.deletedsiterow");
		createSiteButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.createsitebutton");
	}

	@Test(priority = 0)
	public void deleteSiteTest() {

		// login to application
		loginPage.loginToCrafter(
				userName,password);

		driverManager.waitUntilLoginCloses();

		// Click on Delete icon
        this.driverManager.isElementPresentAndClickableByXpath(createSiteButton);
		
        this.homePage.deleteAllSites();
       	
		// Assert
		this.driverManager.waitWhileElementIsNotDisplayedByXpath(deletedSiteRow);
		Assert.assertFalse(this.driverManager.isElementPresentAndClickableByXpath(deletedSiteRow));
	}
}




