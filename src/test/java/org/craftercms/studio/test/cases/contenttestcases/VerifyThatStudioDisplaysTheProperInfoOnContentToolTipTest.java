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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.cases.StudioBaseTest;

/**
 * 
 * @author Luis Hernandez
 *
 */

// Test Case Studio- Site Content ID:9
public class VerifyThatStudioDisplaysTheProperInfoOnContentToolTipTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String siteDropdownElementXPath;
	private String articles2016Folder;
	private String selectAllSegmentsCheckBox;
	private String selectAllCategoriesCheckBox;
	private String articles201612Folder;
	private String articlesFolder;
	private String homeContent;
	private String testingArticleXpath;
	private String styleContent;
	private String homeSectionDefaultsContent;

	private static final Logger logger = LogManager
			.getLogger(VerifyThatStudioDisplaysTheProperInfoOnContentToolTipTest.class);

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String siteId, String blueprint) {
		apiTestHelper.createSite(siteId, "", blueprint);
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		articles2016Folder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.articles.2016folder");
		selectAllSegmentsCheckBox = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame2.article_select_all_segments_checkbox");
		selectAllCategoriesCheckBox = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame2.select_All_Categories_CheckBox");
		testingArticleXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.page.testpage");
		articles201612Folder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.articles.201612childfolder");
		articlesFolder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.articlesfolder");
		homeContent = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.home_Content_Page");
		styleContent = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.pagestree.style.landingpage");
		homeSectionDefaultsContent = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.home.sectiondefaults");
	}

	public void loginAndGoToPreview(String siteId) {
		loginPage.loginToCrafter(userName, password);

		driverManager.waitUntilLoginCloses();

		// go to preview page
		homePage.goToPreviewPage(siteId);

		driverManager.clickElement("xpath", siteDropdownElementXPath);

		this.driverManager.waitUntilSidebarOpens();
	}

	public void createNewPageArticle(String folderLocation) {
		logger.info("Create Article Content");
		this.driverManager.waitForAnimation();
		previewPage.createPageArticleContent("test", "Testing1", "test", folderLocation,
				selectAllCategoriesCheckBox, selectAllSegmentsCheckBox, "ArticleSubject", "ArticleAuthor",
				"ArticleSummary");
	}

	public void changeBodyToNotRequiredOnPageArticleContent() {
		previewPage.changeBodyOfArticlePageToNotRequired();
	}

	public void openSidebarAndGotoArticlesChildFolderAndCreatNewArticle() {

		logger.info("Change Article Page body content to not required");
		this.changeBodyToNotRequiredOnPageArticleContent();

		this.driverManager.waitUntilSidebarOpens();
		// expand pages folder
		dashboardPage.expandPagesTree();

		// Expand Home Tree
		dashboardPage.expandHomeTree();

		// expand Articles folder
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articlesFolder);
		dashboardPage.expandParentFolder(articlesFolder);

		// expand Articles/2016/12 folder
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				articles2016Folder);
		dashboardPage.expandParentFolder(articles2016Folder);

		dashboardPage.expandParentFolder(articles201612Folder);
		this.createNewPageArticle(articles201612Folder);
		
		this.driverManager.waitForAnimation();
	}

	public void checkContentInfoForHomeContent() {
		this.driverManager.waitUntilSidebarOpens();
		
		logger.info("Checking content info for Home page");
		this.driverManager.clickElement("xpath", homeContent);
		
		String contentTypeInfo = this.driverManager.getContentTypeTooltipInfo(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", homeContent));
		String contentNameInfo = this.driverManager.getContentNameTooltipInfo(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", homeContent));
		String contentStatusInfo = this.driverManager.getContentStatusTooltipInfo(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", homeContent));
	

		Assert.assertTrue("Home (Page)".equalsIgnoreCase(contentTypeInfo), "Theres is an error, expected Home (PAGE) but found" + contentTypeInfo);
		Assert.assertTrue("Home".equalsIgnoreCase(contentNameInfo), "Expected HOME but found " + contentNameInfo);
		Assert.assertTrue("Live".equalsIgnoreCase(contentStatusInfo), "expected Live but found" + contentStatusInfo);
	}
	
	public void checkContentInfoForStyleContent() {
		this.driverManager.waitUntilSidebarOpens();
		this.driverManager.waitUntilContentTooltipIsHidden();
		
		logger.info("Checking content info for Style page");
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", styleContent)
				.click();
		
		String contentTypeInfo = this.driverManager.getContentTypeTooltipInfo(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", styleContent));
		String contentNameInfo = this.driverManager.getContentNameTooltipInfo(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", styleContent));
		String contentStatusInfo = this.driverManager.getContentStatusTooltipInfo(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", styleContent));
	

		Assert.assertTrue("Category-Landing (Page)".equalsIgnoreCase(contentTypeInfo));
		Assert.assertTrue("Style".equalsIgnoreCase(contentNameInfo));
		Assert.assertTrue("Live".equalsIgnoreCase(contentStatusInfo));
	}
	
	public void checkContentInfoForHomeSectionDefaultsContent() {
		this.driverManager.waitUntilSidebarOpens();
		this.driverManager.waitUntilContentTooltipIsHidden();
		
		logger.info("Checking content info for Section Defaults content of Home");
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", homeSectionDefaultsContent)
				.click();
		
		String contentTypeInfo = this.driverManager.getContentTypeTooltipInfo(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", homeSectionDefaultsContent));
		String contentNameInfo = this.driverManager.getContentNameTooltipInfo(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", homeSectionDefaultsContent));
		String contentStatusInfo = this.driverManager.getContentStatusTooltipInfo(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", homeSectionDefaultsContent));
	

		Assert.assertTrue("Level-Descriptor (Component)".equalsIgnoreCase(contentTypeInfo));
		Assert.assertTrue("Section Defaults".equalsIgnoreCase(contentNameInfo));
		Assert.assertTrue("Live".equalsIgnoreCase(contentStatusInfo));
	}

	public void checkContentInfoForArticlesFolder() {
		this.driverManager.waitUntilSidebarOpens();
		this.driverManager.waitUntilContentTooltipIsHidden();
		
		logger.info("Checking content info for Articles Folder");
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articlesFolder)
				.click();
		
		String contentTypeInfo = this.driverManager.getContentTypeTooltipInfo(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articlesFolder));
		String contentNameInfo = this.driverManager.getContentNameTooltipInfo(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articlesFolder));
		String contentStatusInfo = this.driverManager.getContentStatusTooltipInfo(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articlesFolder));
	

		Assert.assertTrue("Folder".equalsIgnoreCase(contentTypeInfo));
		Assert.assertTrue("articles".equalsIgnoreCase(contentNameInfo));
		Assert.assertTrue("Live".equalsIgnoreCase(contentStatusInfo));
		
	}
	
	
	public void checkContentInfoForTestingArticleContent() {
		this.driverManager.waitUntilSidebarOpens();
		this.driverManager.waitUntilContentTooltipIsHidden();
		
		logger.info("Checking content info for testing article content");
		this.driverManager
		.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", testingArticleXpath).click();
		this.driverManager.waitForAnimation();
		
		String contentTypeInfo = this.driverManager.getContentTypeTooltipInfo(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", testingArticleXpath));
		String contentNameInfo = this.driverManager.getContentNameTooltipInfo(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", testingArticleXpath));
		String contentStatusInfo = this.driverManager.getContentStatusTooltipInfo(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", testingArticleXpath));
		String contentEditedByInfo = this.driverManager.getContentEditedByTooltipInfo(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", testingArticleXpath));
	

		Assert.assertTrue("Article (Page)".equalsIgnoreCase(contentTypeInfo));
		Assert.assertTrue("Testing1".equalsIgnoreCase(contentNameInfo));
		Assert.assertTrue("Edited".equalsIgnoreCase(contentStatusInfo));
		Assert.assertTrue("admin".equalsIgnoreCase(contentEditedByInfo));
		
	}

	@Parameters({"testId"})
	@Test()
	public void verifyThatStudioDisplaysTheProperInfoOnContentToolTipTest(String testId) {
		this.loginAndGoToPreview(testId);
		this.openSidebarAndGotoArticlesChildFolderAndCreatNewArticle();

		//Checking Page contents
		//Checking Home tooltip info
		this.checkContentInfoForHomeContent();
		
		//Checking Style tooltip info
		this.checkContentInfoForStyleContent();
		
		//Checking Home Section Defaults info
		this.checkContentInfoForHomeSectionDefaultsContent();
		
		//Checking Articles folder info
		this.checkContentInfoForArticlesFolder();
		
		//Checking Testing Article info
		this.checkContentInfoForTestingArticleContent();
		
	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
