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
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.List;
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

// Test Case Studio- Site Content ID:3
public class CopyPasteContentWithSharedComponentsTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String articlesFolder;
	private String siteDropdownElementXPath;
	private String pasteOptionLocator;
	private String firstChildLocator;
	private String firstDestinationLocator;
	private String childFolder;
	private String topNavStatusIcon;
	private String finalChildFolderLocator;
	private String selectAllSegmentsCheckBox;
	private String selectAllCategoriesCheckBox;
	private String homeTree;
	private String expandPagesTree;
	private String staticAssetsButton;
	private String staticAssetsChildFolder;
	private String staticAssetsItemsImagesChildFolder;
	private String staticAssetsItemImagesTestImagesChilds;
	private String secondDestinationLocator;
	private String thirdDestinationLocator;
	private String fourthDestinationLocator;
	private String fifthDestinationLocator;
	private String seventhDestinationLocator;
	private String sixthDestinationLocator;
	private String eighthDestinationLocator;
	private String ninthDestinationLocator;
	private String tenthDestinationLocator;
	private String staticAssetsItemsChildFolder;
	private static Logger logger = LogManager.getLogger(CopyPasteContentWithSharedComponentsTest.class);

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String testId, String blueprint) {
		apiTestHelper.createSite(testId, "", blueprint);
		int exitCode = this.getWebDriverManager().goToDeliveryFolderAndExecuteSiteScriptThroughCommandLine(testId, "init");
		Assert.assertEquals(exitCode, 0, "Init site process failed");
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		articlesFolder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.articlesfolder");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		pasteOptionLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.paste.option");
		firstChildLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.articles.2017folder");
		firstDestinationLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.articles.2016folder");
		childFolder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.articles.childfolder2017");
		topNavStatusIcon = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.statustopbaricon");
		finalChildFolderLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.articles.2016childfolder");
		selectAllSegmentsCheckBox = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame2.article_select_all_segments_checkbox");
		selectAllCategoriesCheckBox = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame2.select_All_Categories_CheckBox");
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
	}

	public void copyAndPasteLongTreeIntoExistentFolder(String childLocator, String destinationFolderLocator) {
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", childLocator);
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", childLocator);
		this.getWebDriverManager().waitForAnimation();
		dashboardPage.rightClickCopyFolder(childLocator);
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().waitForFullExpansionOfTree();
		dashboardPage.clickCopyButtonOnTreeSelector();

		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", destinationFolderLocator);
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().waitForFullExpansionOfTree();
		this.getWebDriverManager().contextClick("xpath", destinationFolderLocator, false);
		this.getWebDriverManager().waitUntilContentTooltipIsHidden();
		getWebDriverManager().usingContextMenu(() -> {
			this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", pasteOptionLocator)
					.click();
		}, "Pages");

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
		previewPage.createPageArticleContentUsingUploadedImage("test", "Testing1", "test", folderLocation,
				selectAllCategoriesCheckBox, selectAllSegmentsCheckBox, "ArticleSubject", "ArticleAuthor",
				"ArticleSummary");

		this.getWebDriverManager().waitUntilSidebarOpens();
	}

	public void changeBodyToNotRequiredOnPageArticleContent() {
		previewPage.changeBodyOfArticlePageToNotRequired();
	}

	public void step1() {

		logger.info("Change Article Page body content to not required");
		this.changeBodyToNotRequiredOnPageArticleContent();

		this.getWebDriverManager().waitUntilSidebarOpens();

		// Expand Home Tree
		this.getWebDriverManager().waitForAnimation();
		dashboardPage.expandHomeTree();

		// expand Articles folder
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articlesFolder);
		dashboardPage.expandParentFolder(articlesFolder);

		this.getWebDriverManager().waitForAnimation();
		dashboardPage.expandParentFolder(firstChildLocator);

		this.getWebDriverManager().waitForAnimation();
		dashboardPage
				.expandParentFolder(firstChildLocator + "/../../../../../div[@class='ygtvchildren']//span[text()='3']");
		this.createNewPageArticle(firstChildLocator + "/../../../../../div[@class='ygtvchildren']//span[text()='3']");

		this.getWebDriverManager().waitForAnimation();
		dashboardPage.collapseParentFolder(
				firstChildLocator + "/../../../../../div[@class='ygtvchildren']//span[text()='3']");

		this.getWebDriverManager().waitForAnimation();
		dashboardPage.collapseParentFolder(firstChildLocator);

		this.getWebDriverManager().waitForFullExpansionOfTree();
		copyAndPasteLongTreeIntoExistentFolder(firstChildLocator, firstDestinationLocator);
		logger.info("Checking if the element {} was pasted with success", "/articles/2016/2017");
		Assert.assertTrue(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", firstDestinationLocator + childFolder)
				.isDisplayed());
	}

	public void step2() {
		this.getWebDriverManager().waitForFullExpansionOfTree();
		this.getWebDriverManager().waitForFullExpansionOfTree();
		secondDestinationLocator = firstDestinationLocator + childFolder;
		copyAndPasteLongTreeIntoExistentFolder(firstChildLocator, secondDestinationLocator);
		logger.info("Checking if the element {} was pasted with success", "/articles/2016/2017/2017");
		Assert.assertTrue(this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				secondDestinationLocator + childFolder).isDisplayed());

		this.getWebDriverManager().scrollDownIntoSideBar();
		this.getWebDriverManager().waitForFullExpansionOfTree();
		thirdDestinationLocator = secondDestinationLocator + childFolder;
		copyAndPasteLongTreeIntoExistentFolder(firstChildLocator, thirdDestinationLocator);
		logger.info("Checking if the element {} was pasted with success", "/articles/2016/2017/2017/2017");
		Assert.assertTrue(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", thirdDestinationLocator + childFolder)
				.isDisplayed());

		this.getWebDriverManager().waitForFullExpansionOfTree();
		fourthDestinationLocator = thirdDestinationLocator + childFolder;
		copyAndPasteLongTreeIntoExistentFolder(firstChildLocator, fourthDestinationLocator);
		logger.info("Checking if the element {} was pasted with success", "/articles/2016/2017/2017/2017/2017");
		Assert.assertTrue(this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				fourthDestinationLocator + childFolder).isDisplayed());

		this.getWebDriverManager().scrollDownIntoSideBar();
		this.getWebDriverManager().waitForFullExpansionOfTree();
		fifthDestinationLocator = fourthDestinationLocator + childFolder;
		copyAndPasteLongTreeIntoExistentFolder(firstChildLocator, fifthDestinationLocator);
		logger.info("Checking if the element {} was pasted with success", "/articles/2016/2017/2017/2017/2017/2017");
		Assert.assertTrue(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", fifthDestinationLocator + childFolder)
				.isDisplayed());

		this.getWebDriverManager().waitForFullExpansionOfTree();
		sixthDestinationLocator = fifthDestinationLocator + childFolder;
		copyAndPasteLongTreeIntoExistentFolder(firstChildLocator, sixthDestinationLocator);
		logger.info("Checking if the element {} was pasted with success",
				"/articles/2016/2017/2017/2017/2017/2017/2017");
		Assert.assertTrue(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", sixthDestinationLocator + childFolder)
				.isDisplayed());

		this.getWebDriverManager().scrollDownIntoSideBar();
		this.getWebDriverManager().waitForFullExpansionOfTree();
		seventhDestinationLocator = sixthDestinationLocator + childFolder;
		copyAndPasteLongTreeIntoExistentFolder(firstChildLocator, seventhDestinationLocator);
		logger.info("Checking if the element {} was pasted with success",
				"/articles/2016/2017/2017/2017/2017/2017/2017/2017");
		Assert.assertTrue(this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				seventhDestinationLocator + childFolder).isDisplayed());

		this.getWebDriverManager().waitForFullExpansionOfTree();
		eighthDestinationLocator = seventhDestinationLocator + childFolder;
		copyAndPasteLongTreeIntoExistentFolder(firstChildLocator, eighthDestinationLocator);
		logger.info("Checking if the element {} was pasted with success",
				"/articles/2016/2017/2017/2017/2017/2017/2017/2017/2017");
		Assert.assertTrue(this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				eighthDestinationLocator + childFolder).isDisplayed());

		this.getWebDriverManager().scrollDownIntoSideBar();
		this.getWebDriverManager().waitForFullExpansionOfTree();
		ninthDestinationLocator = eighthDestinationLocator + childFolder;
		copyAndPasteLongTreeIntoExistentFolder(firstChildLocator, ninthDestinationLocator);
		logger.info("Checking if the element {} was pasted with success",
				"/articles/2016/2017/2017/2017/2017/2017/2017/2017/2017/2017");
		Assert.assertTrue(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", ninthDestinationLocator + childFolder)
				.isDisplayed());

		this.getWebDriverManager().waitForFullExpansionOfTree();
		tenthDestinationLocator = ninthDestinationLocator + childFolder;
		copyAndPasteLongTreeIntoExistentFolder(firstChildLocator, tenthDestinationLocator);
		logger.info("Checking if the element {} was pasted with success",
				"/articles/2016/2017/2017/2017/2017/2017/2017/2017/2017/2017/2017");
		Assert.assertTrue(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", tenthDestinationLocator + childFolder)
				.isDisplayed());

		this.collapseFolders();

		this.getWebDriverManager().waitForAnimation();
		copyAndPasteLongTreeIntoExistentFolder(firstDestinationLocator, firstChildLocator);

		String elementClassValue = "";
		while (!(elementClassValue.contains("open"))) {
			elementClassValue = this.getWebDriverManager()
					.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", firstChildLocator)
					.getAttribute("class");
		}

		this.assertContentImagesOnStaticAssets();
	}

	public void assertContentImagesOnStaticAssets() {
		// Collapse Home tree
		logger.info("Collapse Home tree");
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", homeTree);
		this.getWebDriverManager().waitUntilFolderOpens("xpath", expandPagesTree);
		this.dashboardPage.expandHomeTree();

		logger.info("Click the Static Assets Tree");
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", staticAssetsButton);
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", staticAssetsButton).click();

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", staticAssetsChildFolder);
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", staticAssetsChildFolder)
				.click();

		logger.info("Click the Static Assets/Page Tree");
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				staticAssetsItemsChildFolder);
		this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", staticAssetsItemsChildFolder).click();

		logger.info("Click the Static Assets/item/images Tree");
		this.getWebDriverManager().waitUntilContentTooltipIsHidden();
		this.getWebDriverManager().waitForFullExpansionOfTree();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				staticAssetsItemsImagesChildFolder);
		this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
						 staticAssetsItemsImagesChildFolder)
				.click();

		// Expanding Year folder
		String yearFolderXpath = ".//span[text()='" + this.getWebDriverManager().getCurrentYear() + "']";
		this.getWebDriverManager().waitUntilContentTooltipIsHidden();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", yearFolderXpath);
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", yearFolderXpath).click();

		// Expanding Month folder
		String monthFolderXpath = yearFolderXpath + "/../../../../../div[@class='ygtvchildren']//span[text()='" + this.getWebDriverManager().getCurrentMonth() + "']";
		this.getWebDriverManager().waitUntilContentTooltipIsHidden();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", monthFolderXpath);
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", monthFolderXpath).click();

		// Expanding Day folder
		String dayFolderXpath = monthFolderXpath + "/../../../../../div[@class='ygtvchildren']//span[text()='"+ this.getWebDriverManager().getCurrentDay()+"']";
		this.getWebDriverManager().waitUntilContentTooltipIsHidden();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dayFolderXpath);
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dayFolderXpath).click();

		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().waitForFullExpansionOfTree();
		this.getWebDriverManager().scrollDownIntoSideBar();
		this.getWebDriverManager().waitForAnimation();

		String ImageItemsXpath = dayFolderXpath + staticAssetsItemImagesTestImagesChilds;
		List<WebElement> testimagesitems = this.getWebDriverManager().getDriver().findElements(By.xpath(ImageItemsXpath));
		Assert.assertTrue((testimagesitems.size() == 21),
				"There are not the correct amount of items for static asset/page/images/testimage.jpg , expected 21 items");
	}

	public void collapseFolders() {
		getWebDriverManager().getDriver().navigate().refresh();

		this.getWebDriverManager().scrollDownIntoSideBar();
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().waitForFullExpansionOfTree();

		this.getWebDriverManager().scrollDownIntoSideBar();
		this.getWebDriverManager().waitForFullExpansionOfTree();
		this.getWebDriverManager().scrollDownIntoSideBar();
		dashboardPage.collapseParentFolder(tenthDestinationLocator);
		this.getWebDriverManager().waitForAnimation();
		dashboardPage.collapseParentFolder(ninthDestinationLocator);
		this.getWebDriverManager().waitForAnimation();
		dashboardPage.collapseParentFolder(eighthDestinationLocator);
		this.getWebDriverManager().waitForAnimation();
		dashboardPage.collapseParentFolder(seventhDestinationLocator);
		this.getWebDriverManager().waitForAnimation();
		dashboardPage.collapseParentFolder(sixthDestinationLocator);
		this.getWebDriverManager().waitForAnimation();
		dashboardPage.collapseParentFolder(fifthDestinationLocator);
		this.getWebDriverManager().waitForAnimation();
		dashboardPage.collapseParentFolder(fourthDestinationLocator);
		this.getWebDriverManager().waitForAnimation();
		dashboardPage.collapseParentFolder(thirdDestinationLocator);
		this.getWebDriverManager().waitForAnimation();
		dashboardPage.collapseParentFolder(secondDestinationLocator);
		this.getWebDriverManager().waitForAnimation();
		dashboardPage.collapseParentFolder(firstDestinationLocator);
	}

	public void expandAllCutTrees() {
		// expand 2016 parent
		this.getWebDriverManager().waitForFullExpansionOfTree();
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				firstChildLocator + "/../../../../../div[@class='ygtvchildren']//span[text()='2016']");
		dashboardPage.expandParentFolder(
				firstChildLocator + "/../../../../../div[@class='ygtvchildren']//span[text()='2016']");
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().waitForFullExpansionOfTree();

		// expand the all folder tree
		for (int i = 0; i < 10; i++) {
			// append the last opened 2017 folder
			finalChildFolderLocator = finalChildFolderLocator + childFolder;

			this.getWebDriverManager().scrollDownIntoSideBar();
			this.getWebDriverManager().waitForAnimation();
			this.getWebDriverManager().waitForFullExpansionOfTree();
			this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
					finalChildFolderLocator);

			// at first time expand the 2016 child on 2017 folder
			dashboardPage.expandParentFolder(finalChildFolderLocator);
		}
	}

	public void step3() {
		logger.info("Executing bulk publish");
		this.getWebDriverManager().waitUntilSidebarOpens();
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().scrollDownIntoSideBar();

		previewPage.bulkPublish("/site/website/articles", 50000);

		getWebDriverManager().getDriver().navigate().refresh();
		this.getWebDriverManager().scrollDownIntoSideBar();
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().waitForFullExpansionOfTree();

		logger.info("Collapse the Static Assets Tree");
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", staticAssetsButton);
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", staticAssetsButton).click();

		logger.info("Expand Home tree");
		this.dashboardPage.expandPagesTree();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", homeTree);
		this.getWebDriverManager().waitUntilFolderOpens("xpath", expandPagesTree);
		this.dashboardPage.expandHomeTree();

		// expand Articles folder
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articlesFolder);
		dashboardPage.expandParentFolder(articlesFolder);

		this.getWebDriverManager().scrollDownIntoSideBar();
		this.getWebDriverManager().waitForAnimation();

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", firstChildLocator);
		this.getWebDriverManager().waitForAnimation();
		dashboardPage.expandParentFolder(firstChildLocator);
		this.expandAllCutTrees();

		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().scrollDownIntoSideBar();
		this.getWebDriverManager().waitForAnimation();
		String articleXpath = finalChildFolderLocator
				+ "/../../../../../div[@class='ygtvchildren']/div//span[contains(text(),'Men Styles For Winter')]";

		logger.info("Verify Article is published");
		for (int i = 0; i < 2; i++) {
			this.getWebDriverManager().waitForAnimation();
			this.getWebDriverManager().waitForFullExpansionOfTree();
			this.getWebDriverManager().scrollDownIntoSideBar();
			this.getWebDriverManager().waitForFullExpansionOfTree();
			this.getWebDriverManager().waitUntilSidebarOpens();
			this.getWebDriverManager().scrollRightIntoSideBar(
					finalChildFolderLocator + "/../../../../../div[@class='ygtvchildren']//span[text()='1']");

			// if the folder is not expanded do a click on it
			if (!(this.getWebDriverManager()
					.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
							finalChildFolderLocator + "/../../../../../div[@class='ygtvchildren']//span[text()='1']")
					.getAttribute("class").contains("open"))) {
				this.getWebDriverManager().waitUntilContentTooltipIsHidden();
				this.getWebDriverManager().waitForAnimation();
				this.getWebDriverManager().waitForFullExpansionOfTree();
				this.getWebDriverManager().waitForAnimation();
				this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
						finalChildFolderLocator + "/../../../../../div[@class='ygtvchildren']//span[text()='1']")
						.click();
			}

			try {
				// Wait for the article and click it.
				this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articleXpath);
				this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articleXpath)
						.click();
				this.getWebDriverManager().waitForAnimation();
				this.getWebDriverManager().waitUntilAttributeContains("xpath", topNavStatusIcon, "class", "undefined live");
				break;

			} catch (TimeoutException e) {
				this.getWebDriverManager().takeScreenshot("PageNotPublishedOnTopNavBar");
				logger.warn("Content page is not published yet, checking again if it has published icon on top bar");
				getWebDriverManager().getDriver().navigate().refresh();
			}
		}

		String elementClassValueTopNav = this.getWebDriverManager().getDriver().findElement(By.xpath(topNavStatusIcon))
				.getAttribute("class");
		Assert.assertTrue(elementClassValueTopNav.contains("undefined live"));

	}

	@Parameters({"testId"})
	@Test()
	public void verifyThatStudioAllowsToCopyPasteContentWithSharedComponents(String testId) {
		loginAndGoToPreview(testId);

		step1();

		step2();

		step3();
	}
}
