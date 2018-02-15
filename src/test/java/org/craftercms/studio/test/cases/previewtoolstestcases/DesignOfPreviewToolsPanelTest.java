package org.craftercms.studio.test.cases.previewtoolstestcases;

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

public class DesignOfPreviewToolsPanelTest extends StudioBaseTest {

	private String userName;
	private String password;

	private String previewToolsPanel;

	@BeforeMethod
	public void beforeTest() {

		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		previewToolsPanel = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.previewtools.mainpanel");
	}

	@Test(priority = 0)
	public void verifyTheDesignOfPreviewToolsSectionTest() {
		// login to application
		loginPage.loginToCrafter(userName, password);

		// Wait for login page to close
		driverManager.waitUntilLoginCloses();

		// go to dashboard page
		homePage.goToPreviewPage();

		// Click on Preview Tools icon (show)
		previewPage.clickOnPreviewTools();

		// Assert
		WebElement previewToolsShow = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				previewToolsPanel);
		Assert.assertTrue(previewToolsShow.getAttribute("style").contains("visibility: visible"),
				"ERROR: Preview tools panel is not displayed");
		this.driverManager.waitForAnimation();
		// Click on Preview Tools icon (hide)
		previewPage.clickOnPreviewTools();

		this.driverManager.waitForAnimation();
		this.driverManager.waitUntilAttributeContains("xpath", previewToolsPanel, "style", "visibility: hidden");

		// Assert
		this.driverManager.waitForAnimation();
		Assert.assertTrue(this.driverManager.waitUntilElementIsPresent("xpath", previewToolsPanel)
				.getAttribute("style").contains("visibility: hidden"),
				"ERROR: Preview tools panel should not be displayed");

	}

}