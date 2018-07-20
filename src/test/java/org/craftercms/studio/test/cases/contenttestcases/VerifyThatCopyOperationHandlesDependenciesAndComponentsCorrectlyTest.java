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

// Test Case Studio- Site Content ID:6
public class VerifyThatCopyOperationHandlesDependenciesAndComponentsCorrectlyTest extends StudioBaseTest {

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
	private String copyContent;
	private String pasteContent;
	private String articles20167Folder;
	private String articles20171Folder;
	private String articles2017Folder;
	private String testing2ArticleXpath;
	private String articles20172Folder;
	private String homeTree;
	private String expandPagesTree;
	private String staticAssetsButton;
	private String staticAssetsChildFolder;
	private String staticAssetsItemsChildFolder;
	private String staticAssetsItemsImagesChildFolder;
	private String staticAssetsItemImagesTestImagesChilds;
	private String staticAssetsimagesFolder;
	private String winterWomanImage;
	private String dependeciesOption;

	private static final Logger logger = LogManager
			.getLogger(VerifyThatCopyOperationHandlesDependenciesAndComponentsCorrectlyTest.class);

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
		testing2ArticleXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.page.testpage2");
		articles201612Folder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.articles.201612childfolder");
		articles20167Folder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.articles.20167childfolder");
		articlesFolder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.articlesfolder");
		copyContent = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.copy.option");
		pasteContent = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.paste.option");
		articles20171Folder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.articles.20171childfolder");
		articles20172Folder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.articles.20172childfolder");
		articles2017Folder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.articles.2017folder");
		homeTree = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.expand_GlobalEntry_Tree");
		expandPagesTree = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.expand_Pages_Tree");
		staticAssetsButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("preview.static_assets_button");
		staticAssetsChildFolder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("preview.static_assets_child_folder");
		staticAssetsItemsChildFolder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("preview.static_assets_itemschild_folder");
		staticAssetsItemsImagesChildFolder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("preview.static_assets_itemsimages_child_folder");
		staticAssetsItemImagesTestImagesChilds = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("preview.static_assets_itemsimages_childsitems");
		staticAssetsimagesFolder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.staticassets.imagesfolder");
		winterWomanImage = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.staticassets.winterwomanimage");
		dependeciesOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.dependenciesoption");
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
		previewPage.createPageArticleContentUsingUploadedImage("test", "Testing1", "test", folderLocation,
				selectAllCategoriesCheckBox, selectAllSegmentsCheckBox, "ArticleSubject", "ArticleAuthor",
				"ArticleSummary");
	}

	public void createSecondPageArticle(String folderLocation) {
		logger.info("Create Article Content");
		this.driverManager.waitForAnimation();
		previewPage.createPageArticleContentUsingWinterWomanPicture("test2", "Testing2", "test2",
				folderLocation, selectAllCategoriesCheckBox, selectAllSegmentsCheckBox, "ArticleSubject",
				"ArticleAuthor", "ArticleSummary");
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

	public void createNewArticleOn2017Folder() {
		this.driverManager.waitUntilSidebarOpens();
		// expand pages folder
		dashboardPage.expandPagesTree();

		// Expand Home Tree
		dashboardPage.expandHomeTree();

		// expand Articles/2017/1 folder
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				articles2017Folder);
		dashboardPage.expandParentFolder(articles2017Folder);

		dashboardPage.expandParentFolder(articles20171Folder);
		this.createSecondPageArticle(articles20171Folder);

		this.driverManager.waitForAnimation();
	}

	public void copyArticleToNewFolder(String pageXpath, String folderXpath) {
		this.driverManager.waitUntilSidebarOpens();
		logger.info("Editing testing article created previously");
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", pageXpath);
		this.driverManager.contextClick("xpath", pageXpath, false);
		driverManager.usingContextMenu(() -> {
			WebElement copyContent = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
					this.copyContent);
			copyContent.click();
		}, "Pages");

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", folderXpath);
		this.driverManager.contextClick("xpath", folderXpath, false);
		driverManager.usingContextMenu(() -> {
			WebElement pasteContentElement = this.driverManager
					.driverWaitUntilElementIsPresentAndDisplayed("xpath", pasteContent);
			pasteContentElement.click();
		}, "Pages");

	}

	public void setup() {
		this.loginAndGoToPreview();
	}

	private void checkDependenciesForFirstCopiedArticleAttachedImage() {
		this.assertContentImagesOnStaticAssets();
	}

	public void assertContentImagesOnStaticAssets() {
		// Collapse Home tree
		logger.info("Collapse Home tree");
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", homeTree);
		this.driverManager.waitUntilFolderOpens("xpath", expandPagesTree);
		this.dashboardPage.collapseParentFolder(homeTree);
		this.dashboardPage.collapseParentFolder(expandPagesTree);

		logger.info("Click the Static Assets Tree");
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				staticAssetsButton);
		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", staticAssetsButton).click();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				staticAssetsChildFolder);
		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", staticAssetsChildFolder)
				.click();
		this.driverManager.waitForAnimation();
		this.driverManager.scrollDownIntoSideBar();

		logger.info("Click the Static Assets/Page Tree");
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				staticAssetsItemsChildFolder);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				staticAssetsItemsChildFolder).click();

		logger.info("Click the Static Assets/item/images Tree");
		this.driverManager.waitUntilContentTooltipIsHidden();
		this.driverManager.waitForFullExpansionOfTree();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				staticAssetsItemsImagesChildFolder);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				staticAssetsItemsImagesChildFolder).click();

		// Expanding Year folder
		this.driverManager.scrollDownIntoSideBar();
		String yearFolderXpath = ".//span[text()='" + this.driverManager.getCurrentYear() + "']";
		this.driverManager.waitUntilContentTooltipIsHidden();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", yearFolderXpath);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", yearFolderXpath)
				.click();

		// Expanding Month folder
		String monthFolderXpath = yearFolderXpath
				+ "/../../../../../div[@class='ygtvchildren']//span[text()='"
				+ this.driverManager.getCurrentMonth() + "']";
		this.driverManager.waitUntilContentTooltipIsHidden();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", monthFolderXpath);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", monthFolderXpath)
				.click();

		// Expanding Day folder
		String dayFolderXpath = monthFolderXpath + "/../../../../../div[@class='ygtvchildren']//span[text()='"
				+ this.driverManager.getCurrentDay() + "']";
		this.driverManager.waitUntilContentTooltipIsHidden();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dayFolderXpath);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dayFolderXpath)
				.click();

		this.driverManager.waitForAnimation();
		this.driverManager.waitForFullExpansionOfTree();
		this.driverManager.scrollDownIntoSideBar();
		this.driverManager.waitForAnimation();

		String ImageItemsXpath = dayFolderXpath + staticAssetsItemImagesTestImagesChilds;
		List<WebElement> testimagesitems = this.driverManager.getDriver()
				.findElements(By.xpath(ImageItemsXpath));
		Assert.assertTrue((testimagesitems.size() == 2),
				"There are not the correct amount of items for static asset/page/images/testimage.jpg , expected 2 items");
	}

	private void checkDependenciesForSecondCopiedArticleAttachedImage() {
		logger.info("Click the Static Assets/Item Tree to expand it");
		this.driverManager.scrollDownIntoSideBar();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				staticAssetsItemsChildFolder);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				staticAssetsItemsChildFolder).click();

		this.driverManager.waitForAnimation();
		dashboardPage.expandParentFolder(staticAssetsimagesFolder);

		this.driverManager.waitForAnimation();
		this.driverManager.scrollDownIntoSideBar();
		this.rightClickAndClickOnDependencies(winterWomanImage, "Static Assets");

		// check dependencies are listed
		logger.info("Check Listed Dependencies");
		previewPage.checkDependenciesForStaticAssetItem("winter-woman-pic.jpg", true, false);

	}

	public void rightClickAndClickOnDependencies(String itemLocator, String menuLocation) {
		this.driverManager.waitForAnimation();
		WebElement element = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				itemLocator);

		this.driverManager.contextClick(this.driverManager.getDriver(), element, false);
		driverManager.usingContextMenu(() -> {
			this.driverManager.waitUntilContentTooltipIsHidden();
			WebElement dependenciesOption = this.driverManager
					.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dependeciesOption);
			dependenciesOption.click();
		}, menuLocation);

		this.driverManager.waitForAnimation();
	}

	@Test(
			priority = 0)
	public void verifyThatCopyOperationHandlesDependenciesAndComponentsCorrectlyTest() {
		this.setup();

		this.openSidebarAndGotoArticlesChildFolderAndCreatNewArticle();

		// Copy just created article to 2016/7 folder
		this.copyArticleToNewFolder(testingArticleXpath, articles20167Folder);

		this.checkDependenciesForFirstCopiedArticleAttachedImage();

		this.createNewArticleOn2017Folder();

		// Copy just created article (using static asset) to 2017/2
		this.copyArticleToNewFolder(testing2ArticleXpath, articles20172Folder);

		this.checkDependenciesForSecondCopiedArticleAttachedImage();

	}
}
