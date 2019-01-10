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

/**
 * 
 * @author Luis Hernandez
 *
 */

// Test Case Studio- Site Content ID:8
public class VerifyThatStudioDisplaysTheProperPreviewInfoForAContentTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String siteDropdownElementXPath;
	private String siteDropdownListElementXPath;
	private String articles2016Folder;
	private String selectAllSegmentsCheckBox;
	private String selectAllCategoriesCheckBox;
	private String articles201612Folder;
	private String articlesFolder;
	private String testingArticleXpath;
	private String articleSubject;
	private String articleAuthor;
	private String articleImage;

	private static final Logger logger = LogManager
			.getLogger(VerifyThatStudioDisplaysTheProperPreviewInfoForAContentTest.class);

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		siteDropdownListElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdownlielement");
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
		articleSubject=uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.previewcontent.articlesubject");
		articleAuthor=uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.previewcontent.articleauthor");
		articleImage=uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.previewcontent.articleimage");
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

	public void checkContentPreviewInfoForTestingArticleContent() {
		this.driverManager.waitUntilSidebarOpens();
		this.driverManager.waitUntilContentTooltipIsHidden();

		logger.info("Checking preview info for testing article content");
		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", testingArticleXpath)
				.click();
		this.driverManager.waitForAnimation();

		Assert.assertTrue(
				this.driverManager.getDriver().getCurrentUrl().contains("page=/articles/2016/12/test"));

		this.driverManager.getDriver().switchTo().defaultContent();
		this.driverManager.getDriver().switchTo()
				.frame(this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("id", "engineWindow"));
		// Checking preview article subject of testing article
		Assert.assertTrue("ArticleSubject"
				.equalsIgnoreCase(this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
						articleSubject).getText()));
		// Checking preview author of testing article
		Assert.assertTrue("by ArticleAuthor"
				.equalsIgnoreCase(this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
						articleAuthor).getText()));
		// Checking preview image of testing article
		String sourceValue = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				articleImage).getAttribute("src");

		Assert.assertTrue(sourceValue.contains("/static-assets/images/1-gear.png"));
		this.driverManager.getDriver().switchTo().defaultContent();
	}

	public void setup() {
		this.loginAndGoToPreview();
		this.openSidebarAndGotoArticlesChildFolderAndCreatNewArticle();
	}

	@Test(
			priority = 0)
	public void verifyThatStudioDisplaysTheProperPreviewInfoForAContentTest() {
		this.setup();

		// Checking Testing Article preview info
		this.checkContentPreviewInfoForTestingArticleContent();

	}

}
