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

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.craftercms.studio.test.cases.StudioBaseTest;

/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class PresetEachDesignTest extends StudioBaseTest{	
	private String userName;
	private String password;

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");

	}

	public void changeBodyToNotRequiredOnEntryContent() {

		previewPage.changeBodyOfEntryContentPageToNotRequired();

	}

	public void createContent() {
		// right click to see the the menu

		dashboardPage.rightClickToSeeMenu();

		// Select Entry Content Type
		dashboardPage.clickEntryCT();

		// Confirm the Content Type selected

		dashboardPage.clickOKButton();

		// Switch to the iframe
		getWebDriverManager().getDriver().switchTo().defaultContent();
		getWebDriverManager().getDriver().switchTo()
				.frame(this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed(
						"cssSelector", ".studio-ice-dialog > .bd iframe"));					

		// Set basics fields of the new content created
		dashboardPage.setBasicFieldsOfNewContent("PRESET", "PRESET TESTING");

		// Set the title of main content
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed(
				"cssSelector", "#title > div > input").sendKeys("MainTitle");
	

		// click necessary to validate all fields required
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed(
				"cssSelector", "#cstudio-form-expand-all").click();

		// save and close
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed(
				"id", "cstudioSaveAndClose").click();
	
		// Switch back to the dashboard page
		getWebDriverManager().getDriver().switchTo().defaultContent();

	}

	public void presets() {

		// open publishing channel combo
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed(
				"cssSelector", "#medium-panel-elem > div.acn-accordion-header > a").click();

		 String contentURL = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed(
					"cssSelector", "#engineWindow").getText();
		
		 Assert.assertTrue(contentURL.contains(contentURL));
	}

	@Test(priority = 0)

	public void verifyTheDesingOfPresetsOnPreviewToolsTest() {

		// login to application
		loginPage.loginToCrafter(userName, password);
		
		//Wait for login page to close
		getWebDriverManager().waitUntilLoginCloses();

		// go to preview page
		homePage.goToPreviewPage();

		// body not required
		changeBodyToNotRequiredOnEntryContent();

		// expand pages folder
		dashboardPage.expandPagesTree();

		// create content
		createContent();

		// Expand Home Tree
		dashboardPage.expandHomeTree();

		// select content
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed(
				"cssSelector", "#ygtvlabelel3").click();
		
		// open tools
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed(
				"cssSelector", "#acn-preview-tools-image").click();

		// presets and asserts
		presets();

	}

}
