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
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.io.File;

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

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String testId, String blueprint) {
		apiTestHelper.createSite(testId, "", blueprint);
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		amountOfLastLinesToReadOnLog = Integer
				.parseInt(constantsPropertiesManager.getSharedExecutionConstants()
						.getProperty("crafter.bulkupload.amountoflastlinestoreadonlog"));
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
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
		editorialBPSiteId = testId;
	}

	public void createFoldersStructureToTest() {
		int exitCode = this.getWebDriverManager().createFoldersStructureForTestingCopiedFromSite(editorialBPSiteId);
		Assert.assertTrue(exitCode == 0, "Create folders structure process failed");
	}

	public void setup() {
		loginPage.loginToCrafter(userName, password);
		createFoldersStructureToTest();
	}

	public void goToPreview(String siteId) {
		logger.info("Going to preview page of site: {}", siteId);
		// go to preview page
		homePage.goToPreviewPage(siteId);
		getWebDriverManager().clickElement("xpath", siteDropdownElementXPath);
	}

	public void expandStaticAssetsTree() {
		logger.info("Expanding Static Assets option on sidebar");
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				staticAssetsTreeXpath);
		this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", staticAssetsTreeXpath)
				.click();
	}

	public void expandStaticAssetsSubTree() {
		this.getWebDriverManager().waitForAnimation();
		dashboardPage.expandParentFolder(staticAssetsSubTreeXpath);
	}

	public void step4() {
		this.getWebDriverManager().waitForAnimation();
		dashboardPage.expandParentFolder(cssFolder);

		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().scrollDownIntoSideBar();
	}

	public void bulkUploadFolderToCssFolder() {
		this.bulkUploadFolderContainingNestedFolders(FilesLocations.BULK_CSSFOLDERFILEPATH,
				"/static-assets/css");
	}

	public void bulkUploadFolderContainingNestedFolders(String folderPath, String targetPath) {
		// Performing bulkUpload process from api calls
		logger.info("Executing bulk upload proccess for folder: {} to path: {}", folderPath, targetPath);
		this.getWebDriverManager().waitForAnimation();
		getWebDriverManager().getDriver().switchTo().activeElement();

		File directoryFile = new File(folderPath);

		this.getWebDriverManager().uploadFilesOnADirectoryUsingAPICalls(directoryFile, editorialBPSiteId, targetPath);
		this.getWebDriverManager().getDriver().navigate().refresh();
	}

	public void step7() {

		this.getWebDriverManager().checkNoErrorsOnStudioTomcatLogInGivenLastLines(editorialBPSiteId,
				amountOfLastLinesToReadOnLog);
	}

	public void step8() {
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", cssFolder);
		dashboardPage.collapseParentFolder(cssFolder);

		this.getWebDriverManager().waitForAnimation();
		dashboardPage.expandParentFolder(fontsFolder);

		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().scrollDownIntoSideBar();
	}

	public void bulkUploadFolderToFontsFolder() {
		this.bulkUploadFolderContainingNestedFolders(FilesLocations.BULK_FONTSFOLDERFILEPATH,
				"/static-assets/fonts");
	}

	public void step11() {
		this.getWebDriverManager().checkNoErrorsOnStudioTomcatLogInGivenLastLines(editorialBPSiteId,
				amountOfLastLinesToReadOnLog);
	}

	public void step12() {
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", fontsFolder);
		dashboardPage.collapseParentFolder(fontsFolder);

		this.getWebDriverManager().waitForAnimation();
		dashboardPage.expandParentFolder(imagesFolder);

		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().scrollDownIntoSideBar();
	}

	public void bulkUploadFolderToImagesFolder() {
		this.bulkUploadFolderContainingNestedFolders(FilesLocations.BULK_IMAGEFOLDERFILEPATH,
				"/static-assets/images");
	}

	public void step15() {

		this.getWebDriverManager().checkNoErrorsOnStudioTomcatLogInGivenLastLines(editorialBPSiteId,
				amountOfLastLinesToReadOnLog);
	}

	public void step16() {
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", imagesFolder);
		dashboardPage.collapseParentFolder(imagesFolder);

		this.getWebDriverManager().waitForAnimation();
		dashboardPage.expandParentFolder(jsFolder);

		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().scrollDownIntoSideBar();
	}

	public void bulkUploadFolderToJSFolder() {
		this.bulkUploadFolderContainingNestedFolders(FilesLocations.BULK_JSFOLDERFILEPATH,
				"/static-assets/js");
	}

	public void step19() {

		this.getWebDriverManager().checkNoErrorsOnStudioTomcatLogInGivenLastLines(editorialBPSiteId,
				amountOfLastLinesToReadOnLog);
	}

	public void step20() {
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", jsFolder);
		dashboardPage.collapseParentFolder(jsFolder);

		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().scrollDownIntoSideBar();
	}

	public void bulkUploadFolderToStaticAssetsFolder() {
		this.bulkUploadFolderContainingNestedFolders(FilesLocations.BULK_TXTFILEPATH, "/static-assets");
	}

	public void step23() {

		this.getWebDriverManager().checkNoErrorsOnStudioTomcatLogInGivenLastLines(editorialBPSiteId,
				amountOfLastLinesToReadOnLog);
	}

	public void step24() {
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				staticAssetsTreeXpath);
		dashboardPage.collapseParentFolder(staticAssetsTreeXpath);

		this.getWebDriverManager().waitForAnimation();
		dashboardPage.expandParentFolder(templatesTreeXpath);

		this.getWebDriverManager().waitForAnimation();
		dashboardPage.expandParentFolder(templatesSubTreeXpath);

		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().scrollDownIntoSideBar();
	}

	public void bulkUploadFolderToTemplatesFolder() {
		this.bulkUploadFolderContainingNestedFolders(FilesLocations.BULK_TEMPLATESFILEPATH, "/templates");
	}

	public void step28() {

		this.getWebDriverManager().checkNoErrorsOnStudioTomcatLogInGivenLastLines(editorialBPSiteId,
				amountOfLastLinesToReadOnLog);
	}

	public void step29() {
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				templatesTreeXpath);
		dashboardPage.collapseParentFolder(templatesTreeXpath);

		this.getWebDriverManager().waitForAnimation();
		dashboardPage.expandParentFolder(scriptsTreeXpath);

		this.getWebDriverManager().waitForAnimation();
		dashboardPage.expandParentFolder(scriptsSubTreeXpath);

		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().scrollDownIntoSideBar();
	}

	public void bulkUploadFolderToScriptsFolder() {
		this.bulkUploadFolderContainingNestedFolders(FilesLocations.BULK_SCRIPTSFOLDERFILEPATH, "/scripts");
	}

	public void step33() {
		this.getWebDriverManager().checkNoErrorsOnStudioTomcatLogInGivenLastLines(editorialBPSiteId,
				amountOfLastLinesToReadOnLog);
	}

	@Parameters({"testId"})
	@Test()
	public void verifyBulkUploadAssetsWorksProperlyTest(String testId) {

		this.setup();

		this.goToPreview(testId);

		this.getWebDriverManager().waitUntilSidebarOpens();

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

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		int exitCode = this.getWebDriverManager().deleteFoldersStructureForTestingCopiedFromSite();
		Assert.assertTrue(exitCode == 0, "Delete folders structure process failed");
		apiTestHelper.deleteSite(testId);
	}
}
