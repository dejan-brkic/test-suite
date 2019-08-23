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
import org.openqa.selenium.WebElement;

/**
 * 
 * @author Luis Hernandez
 *
 */

// Test Case Studio- Site Content ID:48
public class VerifyThatSaveDraftWorksProperlyTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String siteDropdownElementXPath;
	private String siteDropdownListElementXPath;
	private String selectAllSegmentsCheckBox;
	private String selectAllCategoriesCheckBox;
	private String articlesFolder;
	private String articles2016Folder;
	private String testingArticleXpath;
	private String editRecentlyContentCreated;
	private String createFormFrameElementCss;
	private String articles201612Folder;
	private String testingArticleCompleteXPath;
	private String previewDraftBar;

	private static final Logger logger = LogManager.getLogger(VerifyThatSaveDraftWorksProperlyTest.class);

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String siteId, String blueprint) {
		apiTestHelper.createSite(siteId, "", blueprint);
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		siteDropdownListElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdownlielement");
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
		editRecentlyContentCreated = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.edit.option");
		createFormFrameElementCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformframe");
		articles201612Folder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.articles.201612childfolder");
		testingArticleCompleteXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.articles.2016.testingarticle");
		previewDraftBar = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("preview.savedraftbar");
	}

	public void loginAndGoToPreview(String siteId) {
		loginPage.loginToCrafter(userName, password);

		getWebDriverManager().waitUntilLoginCloses();

		// go to preview page
		homePage.goToPreviewPage(siteId);
		getWebDriverManager().clickElement("xpath", siteDropdownElementXPath);

	}

	public void createNewPageArticleAsDraft(String folderLocation) {
		logger.info("Create Article Content");
		this.getWebDriverManager().waitForAnimation();
		previewPage.createPageArticleContentAsDraft("test", "Testing1", "test", folderLocation,
				selectAllCategoriesCheckBox, selectAllSegmentsCheckBox, "ArticleSubject", "ArticleAuthor",
				"ArticleSummary");
	}

	public void changeBodyToNotRequiredOnPageArticleContent() {
		previewPage.changeBodyOfArticlePageToNotRequired();
	}

	public void openSidebarAndGotoArticlesChildFolderAndCreatNewArticleAsDraft() {

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

		dashboardPage.expandParentFolder(
				articles201612Folder);
		this.createNewPageArticleAsDraft(
				articles201612Folder);
	}

	public void editArticleAndSaveAndClose() {
		logger.info("Editing testing article created previously");
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				testingArticleXpath);
		this.getWebDriverManager().contextClick("xpath", testingArticleXpath, false);
		getWebDriverManager().usingContextMenu(() -> {
			WebElement editOption = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
					editRecentlyContentCreated);
			editOption.click();
		}, "Pages");

		this.getWebDriverManager().waitForAnimation();
		logger.info("Opening edit form");
		getWebDriverManager().usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {
			// edit internal name
			dashboardPage.editInternalName("Testing");
		});

	}

	public void checkDraftPreviewBarIsDisplayed() {
		logger.info("Checking if testing article is displayed");
		this.getWebDriverManager().waitUntilSidebarOpens();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				testingArticleCompleteXPath);
		Assert.assertTrue(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", testingArticleCompleteXPath)
				.isDisplayed());
		logger.info("Checking if testing article has locked icon");
		Assert.assertTrue(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
						testingArticleCompleteXPath + "//span[@class='fa studio-fa-stack-1x fa-lock locked']")
				.isDisplayed());
		this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", testingArticleCompleteXPath)
				.click();

		this.getWebDriverManager().waitForFullExpansionOfTree();
		getWebDriverManager().getDriver().switchTo().defaultContent();
		logger.info("Checking if Draft preview bar is displayed");
		Assert.assertTrue(
				"Content was saved as DRAFT. Some required fields may not be populated. This can cause errors when previewed or deployed."
						.equalsIgnoreCase(this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed(
								"xpath", previewDraftBar).getText()));
	}

	public void checkDraftPreviewBarIsNotDisplayed() {
		logger.info("Checking if testing article is displayed");
		String testingContentXpath = testingArticleCompleteXPath.replace("Testing1", "Testing");

		this.getWebDriverManager().waitUntilSidebarOpens();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				testingContentXpath);
		Assert.assertTrue(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", testingContentXpath)
				.isDisplayed());
		logger.info("Checking if testing article has the never-published icon");
		Assert.assertTrue(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
						testingContentXpath
								+ "//span[@class='fa studio-fa-stack-1x fa-plus never-published']")
				.isDisplayed());
		this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", testingContentXpath)
				.click();

		this.getWebDriverManager().waitForFullExpansionOfTree();
		getWebDriverManager().getDriver().switchTo().defaultContent();
		logger.info("Checking if Draft preview bar is not displayed");
		Assert.assertFalse(
				this.getWebDriverManager().isElementPresentAndClickableByXpath(previewDraftBar));
	}

	@Parameters({"testId"})
	@Test()
	public void verifyThatSaveDraftWorksProperlyTest(String testId) {

		this.loginAndGoToPreview(testId);

		// Step1, 2, 3, 4, 5, 6 and 7
		this.openSidebarAndGotoArticlesChildFolderAndCreatNewArticleAsDraft();

		// Step 8
		this.checkDraftPreviewBarIsDisplayed();

		// Step 9
		this.editArticleAndSaveAndClose();
		this.checkDraftPreviewBarIsNotDisplayed();
	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
