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
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

/**
 * 
 * @author Luis Hernandez
 *
 */

// Test Case Studio- Site Content ID:23
public class VerifyThatStudioAllowsToPublishAContentProperlyTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String siteDropdownElementXPath;
	private String selectAllSegmentsCheckBox;
	private String selectAllCategoriesCheckBox;
	private String articlesFolder;
	private String articles2016Folder;
	private String testingArticleXpath;
	private String articles201612Folder;
	private String testingArticleCompleteXPath;
	private String publishOptionLocator;
	private int numberOfAttemptsForElementsDisplayed;

	private static final Logger logger = LogManager
			.getLogger(VerifyThatStudioAllowsToPublishAContentProperlyTest.class);

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
		articles201612Folder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.articles.201612childfolder");
		testingArticleCompleteXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.articles.2016.testingarticle");
		publishOptionLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.approveandpublish.option");
		numberOfAttemptsForElementsDisplayed = Integer.parseInt(constantsPropertiesManager
				.getSharedExecutionConstants().getProperty("crafter.numberofattemptsforelementdisplayed"));
	}

	public void loginAndGoToPreview(String testId) {
		loginPage.loginToCrafter(userName, password);

		getWebDriverManager().waitUntilLoginCloses();

		// go to preview page
		homePage.goToPreviewPage(testId);
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

	public void publishArticle() {
		logger.info("Publish testing article created previously");
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				testingArticleXpath);
		this.getWebDriverManager().contextClick("xpath", testingArticleXpath, false);
		getWebDriverManager().usingContextMenu(() -> {
			WebElement publishOption = this.getWebDriverManager()
					.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", publishOptionLocator);
			publishOption.click();
		}, "Pages");

		this.confirmPublishAction();
		this.getWebDriverManager().waitUntilSidebarOpens();

	}

	public void confirmPublishAction() {
		// Switch to the form
		getWebDriverManager().getDriver().switchTo().activeElement();
		// Click on Publish button
		dashboardPage.clickApproveAndPublishSubmitButton();
		// switch to default content
		getWebDriverManager().getDriver().switchTo().defaultContent();
	}

	public void checkContentAndProperIconAreDisplayedAfterCreateContent() {
		logger.info("Checking if testing article is displayed");
		this.getWebDriverManager().waitUntilSidebarOpens();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				testingArticleCompleteXPath);
		Assert.assertTrue(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", testingArticleCompleteXPath)
				.isDisplayed());
		logger.info("Checking if testing article has new icon");
		Assert.assertTrue(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
						testingArticleCompleteXPath
								+ "//span[@class='fa studio-fa-stack-1x fa-plus never-published']")
				.isDisplayed());

	}

	public void checkContentAndProperIconAreDisplayedAfterPublishContent() {
		logger.info("Checking if testing article is displayed");
		this.getWebDriverManager().waitUntilSidebarOpens();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				testingArticleCompleteXPath);
		Assert.assertTrue(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", testingArticleCompleteXPath)
				.isDisplayed());
		logger.info("Checking if testing article has live icon");
		this.checkPublishedIcon();

	}

	public void checkPublishedIcon() {
		String iconElement = testingArticleCompleteXPath
		+ "//span[@class='fa studio-fa-stack-1x undefined live']";
		
		for (int i = 0; i < numberOfAttemptsForElementsDisplayed; i++) {
			try {
				this.getWebDriverManager()
						.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", testingArticleCompleteXPath).click();
				this.getWebDriverManager().waitUntilAttributeContains("xpath", iconElement, "class", "undefined live");
				break;
			} catch (TimeoutException e) {
				this.getWebDriverManager().takeScreenshot("PageNotPublishedOnTopNavBar");
				logger.warn(
						"Content page is not published yet, checking again if it has published icon on top bar");
				this.getWebDriverManager().waitForAnimation();
				this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
						testingArticleCompleteXPath).click();
				getWebDriverManager().getDriver().navigate().refresh();
			}
		}

		Assert.assertTrue(this.getWebDriverManager().getDriver().findElement(By.xpath(iconElement))
				.getAttribute("class").contains("undefined live"));
	}

	@Parameters({"testId"})
	@Test()
	public void verifyThatStudioAllowsToPublishAContentProperlyTest(String testId) {
		this.loginAndGoToPreview(testId);
		this.openSidebarAndGotoArticlesChildFolderAndCreatNewArticle();
		// Step 3
		this.checkContentAndProperIconAreDisplayedAfterCreateContent();

		// Step 4
		this.publishArticle();
		this.checkContentAndProperIconAreDisplayedAfterPublishContent();
	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
