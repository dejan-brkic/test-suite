package org.craftercms.studio.test.cases.contextualnavigationtestcases;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.craftercms.studio.test.cases.StudioBaseTest;

/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class EnableDisableEditingInContextTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String previewToolsInContextualEditingButton;


	@BeforeMethod
	public void beforeTest() {
		
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		previewToolsInContextualEditingButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.previewtools.incontextualeditingbutton");
		
	}

	@Test(priority = 0)
	public void verifyThatTheInContextEditingIsEnabledOrDisabledTest() {

		// login to application
		loginPage.loginToCrafter(userName, password);
		
		//Wait for login page to close
		driverManager.waitUntilLoginCloses();

		// go to dashboard page
		homePage.goToPreviewPage();

		// Click on Preview Tools icon
		previewPage.clickOnPreviewTools();

		// Expand context editing section
		previewPage.clickToExpandInContextEditing();

		// Enable/disable In Context edit
		previewPage.clickToEnableDisableInContextEditing();

		// Assert
		String editIconActive = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", previewToolsInContextualEditingButton).getText();
		Assert.assertEquals(editIconActive, "In-Context Edit Off");

	}

}