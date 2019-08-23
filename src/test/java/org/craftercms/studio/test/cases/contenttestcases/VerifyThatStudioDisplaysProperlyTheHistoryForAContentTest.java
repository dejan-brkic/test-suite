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
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

/**
 * 
 * @author Luis Hernandez
 *
 */

// Test Case Studio- Site Content ID:16
public class VerifyThatStudioDisplaysProperlyTheHistoryForAContentTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String siteDropdownElementXPath;
	private String selectAllSegmentsCheckBox;
	private String selectAllCategoriesCheckBox;
	private String articlesFolder;
	private String articles2016Folder;
	private String testingArticleXpath;
	private String editRecentlyContentCreated;
	private String createFormFrameElementCss;
	private String articles201612Folder;
	private String historyOptionLocator;
	private String actionsHeaderXpath;
	private String historyFirstItemCheckbBox;
	private String historySecondItemCheckbBox;

	private static final Logger logger = LogManager
			.getLogger(VerifyThatStudioDisplaysProperlyTheHistoryForAContentTest.class);

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
		editRecentlyContentCreated = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.edit.option");
		createFormFrameElementCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformframe");
		articles201612Folder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.articles.201612childfolder");
		historyOptionLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.history.option");
		actionsHeaderXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.historydialogactionsheader");
		historyFirstItemCheckbBox = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.crafter3loadtest.historydialog.firstitemcheckbox");
		historySecondItemCheckbBox = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.crafter3loadtest.historydialog.seconditemcheckbox");
	}

	public void loginAndGoToPreview(String siteId) {
		loginPage.loginToCrafter(userName, password);

		getWebDriverManager().waitUntilLoginCloses();

		// go to preview page
		homePage.goToPreviewPage(siteId);

		getWebDriverManager().clickElement("xpath", siteDropdownElementXPath);
	}

	public void createNewPageArticle(String folderLocation) {
		logger.info("Create Article Content");
		this.getWebDriverManager().waitForAnimation();
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
		this.createNewPageArticle(articles201612Folder);
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

	public void checkHistoryOfArticle() {
		logger.info("Publish testing article created previously");
		String testingArticleXpathAfterEdit = testingArticleXpath.replace("Testing1", "Testing");
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				testingArticleXpathAfterEdit);
		
		this.getWebDriverManager().contextClick("xpath", testingArticleXpathAfterEdit, false);
		getWebDriverManager().usingContextMenu(() -> {
			WebElement historyOption = this.getWebDriverManager()
					.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", historyOptionLocator);
			historyOption.click();
		}, "Pages");

		this.getWebDriverManager().waitForAnimation();
		this.checkHistoryRows();
	}

	public void checkHistoryRows() {

		// Switch to the iframe
		getWebDriverManager().getDriver().switchTo().defaultContent();
		getWebDriverManager().getDriver().switchTo().activeElement();

		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().waitUntilPageLoad();

		try {
			this.getWebDriverManager().waitUntilElementIsDisplayed("xpath", actionsHeaderXpath);
		} catch (TimeoutException e) {
			this.getWebDriverManager().takeScreenshot("HistoryDialogNotCompletedRendered");
			logger.warn("History dialog is not completely rendered, and the buttons can't be clicked");
		}

		// Checking if the first row version is displayed
		Assert.assertTrue(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", historyFirstItemCheckbBox)
				.isDisplayed());
		// Checking if the second row version is displayed
		Assert.assertTrue(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", historySecondItemCheckbBox)
				.isDisplayed());
	}

	@Parameters({"testId"})
	@Test()
	public void verifyThatStudioDisplaysProperlyTheHistoryForAContentTest(String testId) {
		this.loginAndGoToPreview(testId);
		this.openSidebarAndGotoArticlesChildFolderAndCreatNewArticle();

		// Steps 2 and 3
		this.editArticleAndSaveAndClose();

		// Step 4
		this.checkHistoryOfArticle();
	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
