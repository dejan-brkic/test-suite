/*
 * Copyright (C) 2007-2019 Crafter Software Corporation. All Rights Reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.craftercms.studio.test.cases.previewtoolstestcases;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
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

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String testId, String blueprint) {
		apiTestHelper.createSite(testId, "", blueprint);
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		previewToolsPanel = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.previewtools.mainpanel");
	}

	@Parameters({"testId"})
	@Test()
	public void verifyTheDesignOfPreviewToolsSectionTest(String testId) {
		// login to application
		loginPage.loginToCrafter(userName, password);

		// Wait for login page to close
		getWebDriverManager().waitUntilLoginCloses();

		// go to dashboard page
		homePage.goToPreviewPage(testId);

		// Click on Preview Tools icon (show)
		previewPage.clickOnPreviewTools();

		// Assert
		WebElement previewToolsShow = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
				previewToolsPanel);
		Assert.assertTrue(previewToolsShow.getAttribute("style").contains("visibility: visible"),
				"ERROR: Preview tools panel is not displayed");
		this.getWebDriverManager().waitForAnimation();
		// Click on Preview Tools icon (hide)
		previewPage.clickOnPreviewTools();

		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().waitUntilAttributeContains("xpath", previewToolsPanel, "style", "visibility: hidden");

		// Assert
		this.getWebDriverManager().waitForAnimation();
		Assert.assertTrue(this.getWebDriverManager().waitUntilElementIsPresent("xpath", previewToolsPanel)
				.getAttribute("style").contains("visibility: hidden"),
				"ERROR: Preview tools panel should not be displayed");
	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}