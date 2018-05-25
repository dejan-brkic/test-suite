/*
 * Copyright (C) 2007-2018 Crafter Software Corporation. All Rights Reserved.
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
package org.craftercms.studio.test.cases.contenttestcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.File;
import java.util.NoSuchElementException;
import org.craftercms.studio.test.cases.StudioBaseTest;
import org.craftercms.studio.test.utils.FilesLocations;
import org.openqa.selenium.WebElement;

/**
 * 
 * @author Luis Hernandez
 *
 */

// Test Case Studio- Site Content ID:46
public class VerifyBulkUploadAssetsWorksProperlyTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String siteDropdownElementXPath;
	private String siteDropdownListElementXPath;
	private String bulkUploadOptionXpath;
	private String staticAssetsTreeXpath;
	private String staticAssetsSubTreeXpath;
	private String cssFolder;
	private String editorialBPSiteId;

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		siteDropdownListElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdownlielement");
		bulkUploadOptionXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.bulkuploadoption");
		cssFolder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.staticassets.cssfolder");
		staticAssetsTreeXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.staticassets.staticassetstree");
		staticAssetsSubTreeXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.staticassets.staticassetssubtree");

		editorialBPSiteId = "editorialbpsite";
	}

	public void setup() {
		loginPage.loginToCrafter(userName, password);

		driverManager.waitUntilLoginCloses();

		this.createSiteUsingWebSiteEditorialBluePrint();

		dashboardPage.clickOnSitesOption();
	}

	public void createSiteUsingWebSiteEditorialBluePrint() {
		// Click on the create site button
		homePage.clickOnCreateSiteButton();

		// Filling the name of site

		createSitePage.fillSiteName(editorialBPSiteId);

		// Filling the description of the site

		createSitePage.fillDescription("Description");

		// Select website blueprint
		createSitePage.selectWebSiteEditorialBluePrintOption();

		// Click on Create button
		createSitePage.clickOnCreateSiteButton();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", siteDropdownElementXPath);

		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", siteDropdownElementXPath)
				.isDisplayed());

	}

	public void goToPreview() {

		// go to preview page
		homePage.goToPreviewPage();

		if (this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", siteDropdownElementXPath)
				.isDisplayed())
			if (!(this.driverManager.waitUntilElementIsPresent("xpath", siteDropdownListElementXPath)
					.getAttribute("class").contains("site-dropdown-open")))
				this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", siteDropdownElementXPath)
						.click();
			else
				throw new NoSuchElementException(
						"Site creation process is taking too long time and the element was not found");
	}

	public void expandStaticAssetsTree() {
		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", staticAssetsTreeXpath);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", staticAssetsTreeXpath)
				.click();
	}

	public void expandStaticAssetsSubTree() {
		this.driverManager.waitForAnimation();
		dashboardPage.expandParentFolder(staticAssetsSubTreeXpath);
	}

	public void step4() {
		this.driverManager.waitForAnimation();
		dashboardPage.expandParentFolder(cssFolder);

		this.driverManager.waitForAnimation();
		this.driverManager.scrollDownIntoSideBar();
		this.rightClickAndClickOnBulkUpload(cssFolder, "Static Assets");
	}

	public void rightClickAndClickOnBulkUpload(String itemLocator, String menuLocation) {
		this.driverManager.waitForAnimation();
		WebElement element = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", itemLocator);

		this.driverManager.contextClick(this.driverManager.getDriver(), element, false);
		driverManager.usingContextMenu(() -> {
			this.driverManager.waitUntilContentTooltipIsHidden();
			WebElement bulkUploadOption = this.driverManager
					.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", bulkUploadOptionXpath);
			bulkUploadOption.click();
		}, menuLocation);

		this.driverManager.waitForAnimation();
	}

	public void bulkUploadFolderToCssFolder() {
		this.bulkUploadFolderContainingNestedFolders(FilesLocations.BULK_CSSFOLDERFILEPATH);
	}

	public void bulkUploadFolderContainingNestedFolders(String folderPath) {
		// Switch to the form
		this.driverManager.waitForAnimation();
		driverManager.getDriver().switchTo().activeElement();

		File directoryFile = new File(folderPath);

		this.driverManager.uploadFilesOnADirectoryUsingAPICalls(directoryFile, editorialBPSiteId, "/static-assets/css");
	}

	@Test(priority = 0)
	public void verifyBulkUploadAssetsWorksProperlyTest() {

		this.setup();

		this.goToPreview();

		this.driverManager.waitUntilSidebarOpens();

		// Step2
		this.expandStaticAssetsTree();

		// Step 3
		this.expandStaticAssetsSubTree();

		// Step 4
		this.step4();

		// Step 5 and 6
		this.bulkUploadFolderToCssFolder();

	}
}
