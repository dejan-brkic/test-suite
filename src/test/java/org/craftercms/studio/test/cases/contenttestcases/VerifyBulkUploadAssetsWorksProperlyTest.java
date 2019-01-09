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
package org.craftercms.studio.test.cases.contenttestcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.File;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.cases.StudioBaseTest;
import org.craftercms.studio.test.utils.FilesLocations;
import org.craftercms.studio.test.utils.WebDriverManager;

/**
 * 
 * @author Luis Hernandez
 *
 */

// Test Case Studio- Site Content ID:46
public class VerifyBulkUploadAssetsWorksProperlyTest extends StudioBaseTest {

	private String userName;
	private String password;
	private int amountOfLastLinesToReadOnLog;
	private String siteDropdownElementXPath;
	private String siteDropdownListElementXPath;
	private String staticAssetsTreeXpath;
	private String staticAssetsSubTreeXpath;
	private String cssFolder;
	private String editorialBPSiteId;
	private String fontsFolder;
	private String imagesFolder;
	private String jsFolder;
	private String templatesTreeXpath;
	private String templatesSubTreeXpath;
	private String scriptsTreeXpath;
	private String scriptsSubTreeXpath;

	private static final Logger logger = LogManager.getLogger(WebDriverManager.class);

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		amountOfLastLinesToReadOnLog = Integer
				.parseInt(constantsPropertiesManager.getSharedExecutionConstants()
						.getProperty("crafter.bulkupload.amountoflastlinestoreadonlog"));
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		siteDropdownListElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdownlielement");
		cssFolder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.staticassets.cssfolder");
		fontsFolder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.staticassets.fontsfolder");
		imagesFolder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.staticassets.imagesfolder");
		jsFolder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.staticassets.jsfolder");
		staticAssetsTreeXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.staticassets.staticassetstree");
		staticAssetsSubTreeXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.staticassets.staticassetssubtree");
		templatesTreeXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.templates.templatestree");
		templatesSubTreeXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.templates.templatessubtree");
		scriptsTreeXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.scripts.scriptstree");
		scriptsSubTreeXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.scripts.scriptssubtree");
		editorialBPSiteId = "editorialbpsite" + RandomStringUtils.randomAlphabetic(5).toLowerCase();
	}

	public void createFoldersStructureToTest() {
		int exitCode = this.driverManager.createFoldersStructureForTestingCopiedFromSite(editorialBPSiteId);
		Assert.assertTrue(exitCode == 0, "Create folders structure process failed");
	}

	public void setup() {
		loginPage.loginToCrafter(userName, password);

		driverManager.waitUntilLoginCloses();

		this.createSiteUsingWebSiteEditorialBluePrint();

		dashboardPage.clickOnSitesOption();

		createFoldersStructureToTest();
	}

	public void createSiteUsingWebSiteEditorialBluePrint() {
		logger.info("Creating site using WebSite Editorial blueprint with name: {}", editorialBPSiteId);
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

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				siteDropdownElementXPath);

		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", siteDropdownElementXPath)
				.isDisplayed());

	}

	public void goToPreview() {
		logger.info("Going to preview page of site: {}", editorialBPSiteId);
		// go to preview page
		homePage.goToPreviewPage();

		if (this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", siteDropdownElementXPath)
				.isDisplayed())
			if (!(this.driverManager.waitUntilElementIsPresent("xpath", siteDropdownListElementXPath)
					.getAttribute("class").contains("site-dropdown-open")))
				this.driverManager
						.driverWaitUntilElementIsPresentAndDisplayed("xpath", siteDropdownElementXPath)
						.click();
			else
				throw new NoSuchElementException(
						"Site creation process is taking too long time and the element was not found");
	}

	public void expandStaticAssetsTree() {
		logger.info("Expanding Static Assets option on sidebar");
		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				staticAssetsTreeXpath);
		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", staticAssetsTreeXpath)
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
	}

	public void bulkUploadFolderToCssFolder() {
		this.bulkUploadFolderContainingNestedFolders(FilesLocations.BULK_CSSFOLDERFILEPATH,
				"/static-assets/css");
	}

	public void bulkUploadFolderContainingNestedFolders(String folderPath, String targetPath) {
		// Performing bulkUpload process from api calls
		logger.info("Executing bulk upload proccess for folder: {} to path: {}", folderPath, targetPath);
		this.driverManager.waitForAnimation();
		driverManager.getDriver().switchTo().activeElement();

		File directoryFile = new File(folderPath);

		this.driverManager.uploadFilesOnADirectoryUsingAPICalls(directoryFile, editorialBPSiteId, targetPath);
		this.driverManager.getDriver().navigate().refresh();
	}

	public void step7() {

		this.driverManager.checkNoErrorsOnStudioTomcatLogInGivenLastLines(editorialBPSiteId,
				amountOfLastLinesToReadOnLog);
	}

	public void step8() {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", cssFolder);
		dashboardPage.collapseParentFolder(cssFolder);

		this.driverManager.waitForAnimation();
		dashboardPage.expandParentFolder(fontsFolder);

		this.driverManager.waitForAnimation();
		this.driverManager.scrollDownIntoSideBar();
	}

	public void bulkUploadFolderToFontsFolder() {
		this.bulkUploadFolderContainingNestedFolders(FilesLocations.BULK_FONTSFOLDERFILEPATH,
				"/static-assets/fonts");
	}

	public void step11() {
		this.driverManager.checkNoErrorsOnStudioTomcatLogInGivenLastLines(editorialBPSiteId,
				amountOfLastLinesToReadOnLog);
	}

	public void step12() {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", fontsFolder);
		dashboardPage.collapseParentFolder(fontsFolder);

		this.driverManager.waitForAnimation();
		dashboardPage.expandParentFolder(imagesFolder);

		this.driverManager.waitForAnimation();
		this.driverManager.scrollDownIntoSideBar();
	}

	public void bulkUploadFolderToImagesFolder() {
		this.bulkUploadFolderContainingNestedFolders(FilesLocations.BULK_IMAGEFOLDERFILEPATH,
				"/static-assets/images");
	}

	public void step15() {

		this.driverManager.checkNoErrorsOnStudioTomcatLogInGivenLastLines(editorialBPSiteId,
				amountOfLastLinesToReadOnLog);
	}

	public void step16() {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", imagesFolder);
		dashboardPage.collapseParentFolder(imagesFolder);

		this.driverManager.waitForAnimation();
		dashboardPage.expandParentFolder(jsFolder);

		this.driverManager.waitForAnimation();
		this.driverManager.scrollDownIntoSideBar();
	}

	public void bulkUploadFolderToJSFolder() {
		this.bulkUploadFolderContainingNestedFolders(FilesLocations.BULK_JSFOLDERFILEPATH,
				"/static-assets/js");
	}

	public void step19() {

		this.driverManager.checkNoErrorsOnStudioTomcatLogInGivenLastLines(editorialBPSiteId,
				amountOfLastLinesToReadOnLog);
	}

	public void step20() {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", jsFolder);
		dashboardPage.collapseParentFolder(jsFolder);

		this.driverManager.waitForAnimation();
		this.driverManager.scrollDownIntoSideBar();
	}

	public void bulkUploadFolderToStaticAssetsFolder() {
		this.bulkUploadFolderContainingNestedFolders(FilesLocations.BULK_TXTFILEPATH, "/static-assets");
	}

	public void step23() {

		this.driverManager.checkNoErrorsOnStudioTomcatLogInGivenLastLines(editorialBPSiteId,
				amountOfLastLinesToReadOnLog);
	}

	public void step24() {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				staticAssetsTreeXpath);
		dashboardPage.collapseParentFolder(staticAssetsTreeXpath);

		this.driverManager.waitForAnimation();
		dashboardPage.expandParentFolder(templatesTreeXpath);

		this.driverManager.waitForAnimation();
		dashboardPage.expandParentFolder(templatesSubTreeXpath);

		this.driverManager.waitForAnimation();
		this.driverManager.scrollDownIntoSideBar();
	}

	public void bulkUploadFolderToTemplatesFolder() {
		this.bulkUploadFolderContainingNestedFolders(FilesLocations.BULK_TEMPLATESFILEPATH, "/templates");
	}

	public void step28() {

		this.driverManager.checkNoErrorsOnStudioTomcatLogInGivenLastLines(editorialBPSiteId,
				amountOfLastLinesToReadOnLog);
	}

	public void step29() {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				templatesTreeXpath);
		dashboardPage.collapseParentFolder(templatesTreeXpath);

		this.driverManager.waitForAnimation();
		dashboardPage.expandParentFolder(scriptsTreeXpath);

		this.driverManager.waitForAnimation();
		dashboardPage.expandParentFolder(scriptsSubTreeXpath);

		this.driverManager.waitForAnimation();
		this.driverManager.scrollDownIntoSideBar();
	}

	public void bulkUploadFolderToScriptsFolder() {
		this.bulkUploadFolderContainingNestedFolders(FilesLocations.BULK_SCRIPTSFOLDERFILEPATH, "/scripts");
	}

	public void step33() {
		this.driverManager.checkNoErrorsOnStudioTomcatLogInGivenLastLines(editorialBPSiteId,
				amountOfLastLinesToReadOnLog);
	}

	@Test(
			priority = 0)
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

		// Step 7
		this.step7();

		// Step 8
		this.step8();

		// Step 9 and 10
		this.bulkUploadFolderToFontsFolder();

		// Step 11
		this.step11();

		// Step 12
		this.step12();

		// Step 13 and 14
		this.bulkUploadFolderToImagesFolder();

		// Step 15
		this.step15();

		// Step 16
		this.step16();

		// Step 17 and 18
		this.bulkUploadFolderToJSFolder();

		// Step 19
		this.step19();

		// Step 20
		this.step20();

		// Step 21 and 22
		this.bulkUploadFolderToStaticAssetsFolder();

		// Step 23
		this.step23();

		// Step 24 and 25
		this.step24();

		// Step 26 and 27
		this.bulkUploadFolderToTemplatesFolder();

		// Step 28
		this.step28();

		// Step 29 and 30
		this.step29();

		// Step 31 and 32
		this.bulkUploadFolderToScriptsFolder();

		// Step 33
		this.step33();
	}

	public void deleteFoldersStructureToTest() {
		int exitCode = this.driverManager.deleteFoldersStructureForTestingCopiedFromSite();
		Assert.assertTrue(exitCode == 0, "Delete folders structure process failed");
	}

	@AfterMethod
	public void afterTest() {
		this.deleteFoldersStructureToTest();
	}
}
