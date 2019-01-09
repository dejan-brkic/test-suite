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
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

/**
 * 
 * @author Luis Hernandez
 *
 */

// Test Case Studio- Site Content ID:24
public class VerifyThatStudioDisplaysProperContentStateTest extends StudioBaseTest {

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
	private String publishOptionLocator;
	private int numberOfAttemptsForElementsDisplayed;

	private static final Logger logger = LogManager
			.getLogger(VerifyThatStudioDisplaysProperContentStateTest.class);

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
		publishOptionLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.approveandpublish.option");
		numberOfAttemptsForElementsDisplayed = Integer.parseInt(constantsPropertiesManager
				.getSharedExecutionConstants().getProperty("crafter.numberofattemptsforelementdisplayed"));
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

	public void publishArticle() {
		logger.info("Publish testing article created previously");
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				testingArticleXpath);
		this.driverManager.contextClick("xpath", testingArticleXpath, false);
		driverManager.usingContextMenu(() -> {
			WebElement publishOption = this.driverManager
					.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", publishOptionLocator);
			publishOption.click();
		}, "Pages");

		this.confirmPublishAction();
		this.driverManager.waitUntilSidebarOpens();

	}

	public void confirmPublishAction() {
		// Switch to the form
		driverManager.getDriver().switchTo().activeElement();
		// Click on Publish button
		dashboardPage.clickApproveAndPublishSubmitButton();
		// switch to default content
		driverManager.getDriver().switchTo().defaultContent();
	}

	public void checkContentAndProperIconAreDisplayedAfterCreateContent() {
		logger.info("Checking if testing article is displayed");
		this.driverManager.waitUntilSidebarOpens();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				testingArticleCompleteXPath);
		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", testingArticleCompleteXPath)
				.isDisplayed());
		logger.info("Checking if testing article has new icon");
		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
						testingArticleCompleteXPath
								+ "//span[@class='fa studio-fa-stack-1x fa-plus never-published']")
				.isDisplayed());

	}

	public void checkContentAndProperIconAreDisplayedAfterPublishContent() {
		logger.info("Checking if testing article is displayed");
		this.driverManager.waitUntilSidebarOpens();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				testingArticleCompleteXPath);
		Assert.assertTrue(this.driverManager
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
				this.driverManager
						.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", testingArticleCompleteXPath).click();
				this.driverManager.waitUntilAttributeContains("xpath", iconElement, "class", "undefined live");
				break;
			} catch (TimeoutException e) {
				this.driverManager.takeScreenshot("PageNotPublishedOnTopNavBar");
				logger.warn(
						"Content page is not published yet, checking again if it has published icon on top bar");
				this.driverManager.waitForAnimation();
				this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
						testingArticleCompleteXPath).click();
				driverManager.getDriver().navigate().refresh();
			}
		}

		Assert.assertTrue(this.driverManager.getDriver().findElement(By.xpath(iconElement))
				.getAttribute("class").contains("undefined live"));
	}

	public void checkContentAndProperIconAreDisplayedAfterEditContent() {
		logger.info("Checking if testing article is displayed");
		String testingContentXpath = testingArticleCompleteXPath.replace("Testing1", "Testing");

		this.driverManager.waitUntilSidebarOpens();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				testingContentXpath);
		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", testingContentXpath)
				.isDisplayed());
		logger.info("Checking if testing article has the edited icon");
		Assert.assertTrue(
				this.driverManager
						.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
								testingContentXpath
										+ "//span[@class='fa studio-fa-stack-1x fa-pencil edited']")
						.isDisplayed());

	}
	public void setup() {
		this.loginAndGoToPreview();
		this.openSidebarAndGotoArticlesChildFolderAndCreatNewArticle();
	}

	@Test(
			priority = 0)
	public void verifyThatStudioDisplaysProperContentStateTestWhenContentChangesArePerformed() {
		this.setup();

		// Step 3
		this.checkContentAndProperIconAreDisplayedAfterCreateContent();

		// Step 4
		this.publishArticle();
		this.checkContentAndProperIconAreDisplayedAfterPublishContent();

		// Step 5, 6 and 7
		this.editArticleAndSaveAndClose();
		this.checkContentAndProperIconAreDisplayedAfterEditContent();
	}

}
