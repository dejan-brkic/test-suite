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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.NoSuchElementException;
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

	@BeforeMethod
	public void beforeTest() {
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

	public void loginAndGoToPreview() {
		loginPage.loginToCrafter(userName, password);

		driverManager.waitUntilLoginCloses();

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

	public void createNewPageArticleAsDraft(String folderLocation) {
		logger.info("Create Article Content");
		this.driverManager.waitForAnimation();
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

		dashboardPage.expandParentFolder(
				articles201612Folder);
		this.createNewPageArticleAsDraft(
				articles201612Folder);
	}

	public void editArticleAndSaveAndClose() {
		logger.info("Editing testing article created previously");
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				testingArticleXpath);
		this.driverManager.contextClick("xpath", testingArticleXpath, false);
		driverManager.usingContextMenu(() -> {
			WebElement editOption = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
					editRecentlyContentCreated);
			editOption.click();
		}, "Pages");

		this.driverManager.waitForAnimation();
		logger.info("Opening edit form");
		driverManager.usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {
			// edit internal name
			dashboardPage.editInternalName("Testing");
		});

	}

	public void checkDraftPreviewBarIsDisplayed() {
		logger.info("Checking if testing article is displayed");
		this.driverManager.waitUntilSidebarOpens();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				testingArticleCompleteXPath);
		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", testingArticleCompleteXPath)
				.isDisplayed());
		logger.info("Checking if testing article has locked icon");
		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
						testingArticleCompleteXPath + "//span[@class='fa studio-fa-stack-1x fa-lock locked']")
				.isDisplayed());
		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", testingArticleCompleteXPath)
				.click();

		this.driverManager.waitForFullExpansionOfTree();
		driverManager.getDriver().switchTo().defaultContent();
		logger.info("Checking if Draft preview bar is displayed");
		Assert.assertTrue(
				"Content was saved as DRAFT. Some required fields may not be populated. This can cause errors when previewed or deployed."
						.equalsIgnoreCase(this.driverManager.driverWaitUntilElementIsPresentAndDisplayed(
								"xpath", previewDraftBar).getText()));
	}

	public void checkDraftPreviewBarIsNotDisplayed() {
		logger.info("Checking if testing article is displayed");
		String testingContentXpath = testingArticleCompleteXPath.replace("Testing1", "Testing");

		this.driverManager.waitUntilSidebarOpens();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				testingContentXpath);
		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", testingContentXpath)
				.isDisplayed());
		logger.info("Checking if testing article has the never-published icon");
		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
						testingContentXpath
								+ "//span[@class='fa studio-fa-stack-1x fa-plus never-published']")
				.isDisplayed());
		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", testingContentXpath)
				.click();

		this.driverManager.waitForFullExpansionOfTree();
		driverManager.getDriver().switchTo().defaultContent();
		logger.info("Checking if Draft preview bar is not displayed");
		Assert.assertFalse(
				this.driverManager.isElementPresentAndClickableByXpath(previewDraftBar));
	}

	@Test(
			priority = 0)
	public void verifyThatSaveDraftWorksProperlyTest() {

		this.loginAndGoToPreview();

		// Step1, 2, 3, 4, 5, 6 and 7
		this.openSidebarAndGotoArticlesChildFolderAndCreatNewArticleAsDraft();

		// Step 8
		this.checkDraftPreviewBarIsDisplayed();

		// Step 9
		this.editArticleAndSaveAndClose();
		this.checkDraftPreviewBarIsNotDisplayed();
	}

}
