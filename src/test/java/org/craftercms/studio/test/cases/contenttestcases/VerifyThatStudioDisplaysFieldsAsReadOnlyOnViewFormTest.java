/*
 * Copyright (C) 2007-2018 Crafter Software Corporation. All Rights Reserved.
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

import java.util.List;
import java.util.NoSuchElementException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.cases.StudioBaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * 
 * @author Luis Hernandez
 *
 */

// Test Case Studio- Site Content ID:10
public class VerifyThatStudioDisplaysFieldsAsReadOnlyOnViewFormTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String siteDropdownElementXPath;
	private String siteDropdownListElementXPath;
	private String selectAllSegmentsCheckBox;
	private String selectAllCategoriesCheckBox;
	private String articlesFolder;
	private String articles2016Folder;
	private String testingArticleXpath;
	private String viewOptionXpath;
	private String createFormFrameElementCss;
	private String articles201612Folder;
	private String readOnlyBanner;
	private String viewFormButtons;
	private String closeButton;
	private static final Logger logger = LogManager
			.getLogger(VerifyThatStudioDisplaysFieldsAsReadOnlyOnViewFormTest.class);

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
		viewOptionXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.view.option");
		createFormFrameElementCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformframe");
		articles201612Folder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.articles.201612childfolder");
		readOnlyBanner = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.view.article.readonlybanner");
		viewFormButtons = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.view.article.buttons");
		closeButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame2.view.closebutton");
		
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

	public void reviewAllReadOnlyFieldsOnViewForm() {
		logger.info("Editing testing article created previously");	
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				testingArticleXpath);
		this.driverManager.contextClick("xpath", testingArticleXpath, false);
		driverManager.usingContextMenu(() -> {
			WebElement viewOption = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
					viewOptionXpath);
			viewOption.click();
		}, "Pages");

		this.driverManager.waitForAnimation();
		logger.info("Opening view form");
		driverManager.usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {
			// edit internal name
			Assert.assertTrue(this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", readOnlyBanner).isDisplayed());
			Assert.assertTrue("READ ONLY".equalsIgnoreCase(this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", readOnlyBanner).getText()));
			
			List<WebElement> viewFormDefaulButtons = this.driverManager.getDriver().findElements(By.xpath(viewFormButtons));
			Assert.assertTrue((viewFormDefaulButtons.size() == 1),
					"There are more than the Close button displayed on the View form, expected only the Close button");
			
			this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", closeButton)
					.click();
		});
	}

	public void setup() {
		this.loginAndGoToPreview();
		this.openSidebarAndGotoArticlesChildFolderAndCreatNewArticle();
	}

	@Test(
			priority = 0)
	public void verifyThatStudioDisplaysFieldsAsReadOnlyOnViewFormTest() {
		this.setup();

		// Steps 1 and 2
		this.reviewAllReadOnlyFieldsOnViewForm();
	}

}
