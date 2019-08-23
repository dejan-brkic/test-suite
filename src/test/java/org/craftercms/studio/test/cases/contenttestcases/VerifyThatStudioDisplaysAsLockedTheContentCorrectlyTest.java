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

// Test Case Studio- Site Content ID:21
public class VerifyThatStudioDisplaysAsLockedTheContentCorrectlyTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String siteDropdownElementXPath;
	private String selectAllSegmentsCheckBox;
	private String selectAllCategoriesCheckBox;
	private String articlesFolder;
	private String articles2016Folder;
	private String testingArticleXpath;
	private String unlockOptionXpath;
	private String articles201612Folder;
	private String testingArticleCompleteXPath;

	private static final Logger logger = LogManager.getLogger(VerifyThatStudioDisplaysAsLockedTheContentCorrectlyTest.class);

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String siteId, String blueprint) {
		apiTestHelper.createSite(siteId, "", blueprint);
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		articlesFolder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.articlesfolder");
		articles2016Folder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.articles.2016folder");
		selectAllSegmentsCheckBox = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame2.article_select_all_segments_checkbox");
		selectAllCategoriesCheckBox = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame2.select_All_Categories_CheckBox");
		testingArticleXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.page.testpage");
		unlockOptionXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.unlock.option");
		articles201612Folder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.articles.201612childfolder");
		testingArticleCompleteXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.articles.2016.testingarticle");
	}

	public void loginAndGoToPreview(String siteId) {
		loginPage.loginToCrafter(userName, password);

		getWebDriverManager().waitUntilLoginCloses();

		// go to preview page
		homePage.goToPreviewPage(siteId);
		getWebDriverManager().clickElement("xpath", siteDropdownElementXPath);
	}

	public void changeBodyToNotRequiredOnPageArticleContent() {
		previewPage.changeBodyOfArticlePageToNotRequired();
	}

	public void checkUnlockOptionOnContextClick() {
		logger.info("Editing testing article created previously");
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				testingArticleCompleteXPath);
		
		Assert.assertTrue(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
						testingArticleCompleteXPath
								+ "//span[@class='fa studio-fa-stack-1x fa-lock locked']")
				.isDisplayed());
		
		this.getWebDriverManager().contextClick("xpath", testingArticleXpath, false);
		getWebDriverManager().usingContextMenu(() -> {
			Assert.assertTrue(this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
					unlockOptionXpath).isDisplayed());
			this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
					unlockOptionXpath).click();
		}, "Pages");
		
		

	}

	public void checkLockedIcon() {
		logger.info("Checking if testing article is locked");
		this.getWebDriverManager().waitUntilSidebarOpens();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				testingArticleCompleteXPath);
		Assert.assertTrue(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", testingArticleCompleteXPath)
				.isDisplayed());
		Assert.assertTrue(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
						testingArticleCompleteXPath
								+ "//span[@class='fa studio-fa-stack-1x fa-lock locked']")
				.isDisplayed());
	}
	
	public void openSidebarAndGotoArticlesChildFolderAndCreatNewArticle() {

		logger.info("Change Article Page body content to not required");
		this.changeBodyToNotRequiredOnPageArticleContent();

		this.getWebDriverManager().waitUntilSidebarOpens();

		// Expand Home Tree
		dashboardPage.expandHomeTree();

		// expand Articles folder
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articlesFolder);
		dashboardPage.expandParentFolder(articlesFolder);

		// expand Articles/2016/12 folder
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				articles2016Folder);
		dashboardPage.expandParentFolder(articles2016Folder);

		dashboardPage.expandParentFolder(articles201612Folder);
		this.createNewPageArticleAsDraf(articles201612Folder);
	}
	
	public void createNewPageArticleAsDraf(String folderLocation) {
		logger.info("Create Article Content");
		this.getWebDriverManager().waitForAnimation();
		previewPage.createPageArticleContentAsDraft("test", "Testing1", "test", folderLocation,
				selectAllCategoriesCheckBox, selectAllSegmentsCheckBox, "ArticleSubject", "ArticleAuthor",
				"ArticleSummary");
	}

	@Parameters({"testId"})
	@Test()
	public void verifyThatStudioDisplaysAsLockedTheContentWhenTheContentIsBeingEditedTest(String testId) {

		this.loginAndGoToPreview(testId);
		this.openSidebarAndGotoArticlesChildFolderAndCreatNewArticle();
		//Steps 1, 2 and 3
		this.checkLockedIcon();
		
		// Step 4
		this.checkUnlockOptionOnContextClick();
	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
