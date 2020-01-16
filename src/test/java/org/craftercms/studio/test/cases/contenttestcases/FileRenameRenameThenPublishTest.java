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

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.cases.StudioBaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * 
 * @author luishernandez
 *
 */
// Test Case Studio- Site Content ID:42
public class FileRenameRenameThenPublishTest extends StudioBaseTest {

	private String createFormFrameElementCss;
	private String createFormSaveAndCloseElement;
	private String configurationSetUp;
	private String dashboardLink;
	private String editRecentlyContentCreated;
	private String recentActivityContentURL;
	private String recentActivityContentName;
	private String fooContentXpath;
	private String editURLButton;
	private String warningTitle;
	private String warningOkButton;
	private String filenameInput;
	private String selectAllSegmentsCheckBox;
	private String selectEntertaimentCategoryCheckBox;
	private String articlesFolder;
	private String folder2016Locator;
	private String publishOptionXpath;
	private String topNavStatusIcon;
	private String recentlyActivitySelectAll;
	private String recentlyActivityTable;
	private String recentlyActivityItemName;
	private String recentlyActivityItemIcon;
	private String recentlyActivityItemURL;
	private String recentlyActivityItemConfigurationEditedIcon;
	private String recentlyPublishedContentName;
	private String recentlyPublishedContentURL;
	private String recentlyPublishedSelectAll;
	private int numberOfAttemptsForElementsDisplayed;
	private static Logger logger = LogManager.getLogger(FileRenameRenameThenPublishTest.class);

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String testId, String blueprint) {
		apiTestHelper.createSite(testId, "", blueprint);
		int exitCode = this.getWebDriverManager().goToDeliveryFolderAndExecuteSiteScriptThroughCommandLine(testId, "init");
		Assert.assertEquals(exitCode, 0, "Init site process failed");
		createFormFrameElementCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformframe");
		createFormSaveAndCloseElement = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.saveandclosebutton");
		dashboardLink = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.dashboard.dashboardlink");
		editRecentlyContentCreated = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.edit.option");
		recentActivityContentURL = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.contenturl");
		recentActivityContentName = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.contentname");
		recentlyPublishedContentName = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentlypublished.contentname");
		recentlyPublishedContentURL = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentlypublished.contenturl");
		fooContentXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.foocontent");
		editURLButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame1.editurlbutton");
		warningTitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame1.warning.warningtitle");
		warningOkButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame1.warning.okbutton");
		filenameInput = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame1.filenameinput");
		selectAllSegmentsCheckBox = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame2.article_select_all_segments_checkbox");
		selectEntertaimentCategoryCheckBox = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame2.select_entertaiment_Category_CheckBox");
		articlesFolder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.articlesfolder");
		folder2016Locator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.articles.2016folder");
		fooContentXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.foocontent");
		publishOptionXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.approveandpublish.option");
		topNavStatusIcon = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.statustopbaricon");
		recentlyActivitySelectAll = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.selectall");
		recentlyPublishedSelectAll = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentlypublished.selectall");
		recentlyActivityTable = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.tablebody");
		recentlyActivityItemName = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.itemname");
		recentlyActivityItemIcon = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.itemicon");
		recentlyActivityItemURL = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.itemurl");
		recentlyActivityItemConfigurationEditedIcon = uiElementsPropertiesManager
				.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.itemconfigurationeditedicon");
		this.numberOfAttemptsForElementsDisplayed = Integer.parseInt(constantsPropertiesManager
				.getSharedExecutionConstants().getProperty("crafter.numberofattemptsforelementdisplayed"));
		configurationSetUp = "<content-type name=\"/page/article\" is-wcm-type=\"true\">"
				+ "<label>Page - Article</label>" + "<form>/page/article</form>"
				+ "<form-path>simple</form-path>"
				+ "<model-instance-path>NOT-USED-BY-SIMPLE-FORM-ENGINE</model-instance-path>"
				+ "<file-extension>xml</file-extension>" + "<content-as-folder>false</content-as-folder>"
				+ "<previewable>true</previewable>" + "<noThumbnail>false</noThumbnail>"
				+ "<image-thumbnail>page-article.png</image-thumbnail>" + "<paths>"
				+ "<includes><pattern>^/site/website/articles/.*</pattern></includes>" + "</paths>"
				+ "</content-type>";
	}


	public void modifyPageXMLDefinition() {
		previewPage.modifyPageXMLDefinitionContentAsFolderForPageArticle(configurationSetUp);
	}

	public void createNewPageArticle(String folderLocation) {
		logger.info("Create Article Content");
		this.getWebDriverManager().waitForAnimation();
		previewPage.createPageArticleContentUsingUploadedImage("foo", "foo", "foo", folderLocation,
				selectEntertaimentCategoryCheckBox, selectAllSegmentsCheckBox, "foo", "foo", "foo", "foo");

		this.getWebDriverManager().waitUntilSidebarOpens();
	}

	public void setup(String siteId) {
		loginPage.loginToCrafter();
		homePage.goToPreviewPage(siteId);
		previewPage.clickSidebar();
		// modify page XML definition
		this.modifyPageXMLDefinition();

		this.getWebDriverManager().waitUntilSidebarOpens();

		// Expand Home Tree
		this.getWebDriverManager().waitForAnimation();
		dashboardPage.expandHomeTree();

		// expand Articles folder
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articlesFolder);
		dashboardPage.expandParentFolder(articlesFolder);

		this.getWebDriverManager().waitForAnimation();
		dashboardPage.expandParentFolder(folder2016Locator);

		this.getWebDriverManager().waitForAnimation();
		dashboardPage.expandParentFolder(
				folder2016Locator + "/../../../../../div[@class='ygtvchildren']//span[text()='12']");
		this.createNewPageArticle(
				folder2016Locator + "/../../../../../div[@class='ygtvchildren']//span[text()='12']");

		getWebDriverManager().waitUntilHomeIsOpened();
		Assert.assertTrue(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", fooContentXpath)
				.isDisplayed());

		this.getWebDriverManager().contextClick("xpath", fooContentXpath, false);
		getWebDriverManager().usingContextMenu(() -> {
			WebElement publishOption = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
					publishOptionXpath);
			publishOption.click();
		}, "Pages");

		// submit
		previewPage.clickOnSubmitButtonOfApprovePublish();
		this.getWebDriverManager().waitForAnimation();

		for (int i = 0; i < numberOfAttemptsForElementsDisplayed; i++) {
			try {
				this.getWebDriverManager()
						.clickElement("xpath", fooContentXpath);
				this.getWebDriverManager().waitUntilAttributeContains("xpath", topNavStatusIcon, "class",
						"undefined live");
				break;
			} catch (TimeoutException e) {
				this.getWebDriverManager().takeScreenshot("PageNotPublishedOnTopNavBar");
				logger.warn(
						"Content page is not published yet, checking again if it has published icon on top bar");
				getWebDriverManager().getDriver().navigate().refresh();
			}
		}

		String elementClassValue = this.getWebDriverManager().getDriver().findElement(By.xpath(topNavStatusIcon))
				.getAttribute("class");
		Assert.assertTrue(elementClassValue.contains("undefined live"));

		this.goToDashboardAndCheck();
	}

	public void goToDashboardAndCheck() {
		// click on dashboard
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dashboardLink);
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dashboardLink)
				.click();

		this.getWebDriverManager().waitUntilDashboardWidgetsAreLoaded();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				recentlyActivitySelectAll);
		Select categoriesDropDown = new Select(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentlyActivitySelectAll));

		categoriesDropDown.selectByValue("all");

		// check items on My Recent Activity widget
		this.getWebDriverManager().waitUntilDashboardLoadingAnimationIsNotDisplayedOnRecentActivity();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentName);
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentURL);

		this.getWebDriverManager().waitForFullExpansionOfTree();
		List<WebElement> recentActivityItems = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentlyActivityTable)
				.findElements(By.tagName("tr"));

		for (WebElement webElement : recentActivityItems) {
			this.checkRecentActivityItems(webElement);
		}

	}

	public void checkRecentActivityItems(WebElement element) {
		this.getWebDriverManager().waitForAnimation();
		String itemName = element.findElement(By.xpath(recentlyActivityItemName)).getText();
		String itemIconClass = element.findElement(By.xpath(recentlyActivityItemIcon)).getAttribute("class");
		String itemURL = element.findElement(By.xpath(recentlyActivityItemURL)).getText();

		switch (itemName) {
		case "foo":
			Assert.assertTrue(itemIconClass.contains("fa-file-o"));
			Assert.assertTrue(itemURL.equalsIgnoreCase("/articles/2016/12/foo.xml"));
			break;
		case "images":
			Assert.assertTrue(itemIconClass.contains("fa-folder-o"));
			Assert.assertTrue(itemURL.equalsIgnoreCase("/static-assets/item/images"));
			break;
		case "item":
			Assert.assertTrue(itemIconClass.contains("fa-folder-o"));
			Assert.assertTrue(itemURL.equalsIgnoreCase("/static-assets/item"));
			break;
		case "testimage.jpg":
			Assert.assertTrue(itemIconClass.contains("fa-file-image-o"));
			Assert.assertTrue(itemURL.contains("/static-assets/item/images/"));
			Assert.assertTrue(itemURL.contains("/testimage.jpg"));
			break;
		case "config.xml":
			itemIconClass = element.findElement(By.xpath(recentlyActivityItemConfigurationEditedIcon))
					.getAttribute("class");
			Assert.assertTrue(itemIconClass.contains("fa-pencil"));
			Assert.assertTrue(
					itemURL.equalsIgnoreCase("/config/studio/content-types/page/article/config.xml"));
			break;
		case "form-definition.xml":
			itemIconClass = element.findElement(By.xpath(recentlyActivityItemConfigurationEditedIcon))
					.getAttribute("class");
			Assert.assertTrue(itemIconClass.contains("fa-pencil"));
			Assert.assertTrue(itemURL
					.equalsIgnoreCase("/config/studio/content-types/page/article/form-definition.xml"));
			break;
		default:
			this.validateItemNameForStaticAssetsFolders(itemName, itemIconClass, itemURL);
			break;
		}
	}

	public void validateItemNameForStaticAssetsFolders(String itemName, String itemIconClass,
			String itemURL) {
		String year = this.getWebDriverManager().getCurrentYear();
		String month = this.getWebDriverManager().getCurrentMonth();
		String day = this.getWebDriverManager().getCurrentDay();

		if (itemURL.equalsIgnoreCase("/static-assets/item/images/" + year)) {
			Assert.assertTrue(itemIconClass.contains("fa-folder-o"));
			Assert.assertTrue(itemName.equalsIgnoreCase(year));
		} else if (itemURL.equalsIgnoreCase("/static-assets/item/images/" + year + "/" + month)) {
			Assert.assertTrue(itemIconClass.contains("fa-folder-o"));
			Assert.assertTrue(itemName.equalsIgnoreCase(month));
		} else if (itemURL.equalsIgnoreCase("/static-assets/item/images/" + year + "/" + month + "/" + day)) {
			Assert.assertTrue(itemIconClass.contains("fa-folder-o"));
			Assert.assertTrue(itemName.equalsIgnoreCase(day));
		} else {
			Assert.assertTrue(false, "The Item URL is not the correct for the item: " + itemName);
		}
	}

	public void step3() {
		// expand pages folder
		this.getWebDriverManager().waitUntilSidebarOpens();
		this.getWebDriverManager().waitForAnimation();
		dashboardPage.expandPagesTree();

		// reload page
		getWebDriverManager().getDriver().navigate().refresh();
		this.getWebDriverManager().waitUntilHomeIsOpened();

		dashboardPage.expandParentFolder(folder2016Locator);

		dashboardPage.expandParentFolder(
				folder2016Locator + "/../../../../../div[@class='ygtvchildren']//span[text()='12']");
	}

	public void step10() {
		// click on dashboard
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dashboardLink);
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dashboardLink)
				.click();
		this.getWebDriverManager().waitForAnimation();

		this.dashboardPage.validateItemsOnRecentActivity(false);

	}

	public void step20() {
		// checking if the content was published
		for (int i = 0; i < numberOfAttemptsForElementsDisplayed; i++) {
			try {
				this.getWebDriverManager()
						.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", fooContentXpath)
						.click();
				this.getWebDriverManager().waitUntilAttributeContains("xpath", topNavStatusIcon, "class",
						"undefined live");
				break;
			} catch (TimeoutException e) {
				this.getWebDriverManager().takeScreenshot("PageNotPublishedOnTopNavBar");
				logger.warn(
						"Content page is not published yet, checking again if it has published icon on top bar");
				getWebDriverManager().getDriver().navigate().refresh();
			}
		}

		String elementClassValue = this.getWebDriverManager().getDriver().findElement(By.xpath(topNavStatusIcon))
				.getAttribute("class");
		Assert.assertTrue(elementClassValue.contains("undefined live"));

		// click on dashboard
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dashboardLink);
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dashboardLink)
				.click();

		this.getWebDriverManager().waitUntilDashboardLoadingAnimationIsNotDisplayedOnRecentlyPublished();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				recentlyPublishedSelectAll);
		Select categoriesDropDown = new Select(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentlyPublishedSelectAll));

		categoriesDropDown.selectByValue("all");

		// check items on Recently Published widget
		this.getWebDriverManager().waitUntilDashboardLoadingAnimationIsNotDisplayedOnRecentlyPublished();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentlyPublishedContentName);
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentlyPublishedContentURL);

		Assert.assertTrue(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentlyPublishedContentName).getText()
				.contains("foo"));
		Assert.assertTrue(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentlyPublishedContentURL).getText()
				.contains("/articles/2016/12/baz.xml"));
	}

	public void step18() {
		this.getWebDriverManager().contextClick("xpath", fooContentXpath, false);
		getWebDriverManager().usingContextMenu(() -> {
			WebElement publishOption = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
					publishOptionXpath);
			publishOption.click();
		}, "Pages");
	}

	public void step19() {
		// submit
		previewPage.clickOnSubmitButtonOfApprovePublish();
		this.getWebDriverManager().waitForAnimation();
	}

	public void step13() {
		for (int i = 0; i < numberOfAttemptsForElementsDisplayed; i++) {
			try {
				this.getWebDriverManager()
						.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", fooContentXpath)
						.click();
				this.getWebDriverManager().waitUntilAttributeContains("xpath", topNavStatusIcon, "class",
						"undefined live");
				break;
			} catch (TimeoutException e) {
				this.getWebDriverManager().takeScreenshot("PageNotPublishedOnTopNavBar");
				logger.warn(
						"Content page is not published yet, checking again if it has published icon on top bar");
				getWebDriverManager().getDriver().navigate().refresh();
			}
		}

		String elementClassValue = this.getWebDriverManager().getDriver().findElement(By.xpath(topNavStatusIcon))
				.getAttribute("class");
		Assert.assertTrue(elementClassValue.contains("undefined live"));
	}

	public void step11() {
		// steps 11, , 12, 13 and 14
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", fooContentXpath);
		this.getWebDriverManager().contextClick("xpath", fooContentXpath, false);
		getWebDriverManager().usingContextMenu(() -> {
			WebElement editOption = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
					editRecentlyContentCreated);
			editOption.click();
		}, "Pages");

		this.getWebDriverManager().waitForAnimation();
		getWebDriverManager().usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {
			// check that the edit form was opened
			// step 4
			Assert.assertTrue(
					this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", ".//h1/span")
							.getText().equalsIgnoreCase("foo"));

			// step 5
			this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", editURLButton)
					.click();

			this.getWebDriverManager().getDriver().switchTo().activeElement();
			Assert.assertTrue(
					this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", warningTitle)
							.getText().equalsIgnoreCase("Warning"));

			// step 6
			this.getWebDriverManager()
					.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", warningOkButton)
					.click();

			// step 7
			this.getWebDriverManager().waitForAnimation();
			this.getWebDriverManager().getDriver().switchTo().activeElement();
			this.getWebDriverManager().sendText("xpath", filenameInput, "baz");
			this.getWebDriverManager().waitForAnimation();

			// save and close
			this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
					createFormSaveAndCloseElement).click();

		});
		// Step 8 Expected Output
		Assert.assertTrue(this.getWebDriverManager().getDriver().getCurrentUrl().contains("/studio/site-dashboard"));

	}

	public void step16() {
		this.getWebDriverManager().waitForFullExpansionOfTree();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", fooContentXpath)
				.click();
		this.getWebDriverManager().waitForAnimation();
		Assert.assertTrue(
				this.getWebDriverManager().getDriver().getCurrentUrl().contains("page=/articles/2016/12/baz.html"));
	}

	public void step17() {
		// click on dashboard
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dashboardLink);
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dashboardLink)
				.click();
	}

	@Parameters({"testId"})
	@Test()
	public void fileRenameFileXMLRenameRenameAndPublishTest(String testId) {
		this.setup(testId);

		this.step3();

		// steps 4, 5, 6, 7 and 8
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", fooContentXpath);
		this.getWebDriverManager().contextClick("xpath", fooContentXpath, false);
		getWebDriverManager().usingContextMenu(() -> {
			WebElement editOption = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
					editRecentlyContentCreated);
			editOption.click();
		}, "Pages");

		this.getWebDriverManager().waitForAnimation();
		getWebDriverManager().usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {
			// check that the edit form was opened
			// step 4
			Assert.assertTrue(
					this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", ".//h1/span")
							.getText().equalsIgnoreCase("foo"));

			// step 5
			this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", editURLButton)
					.click();

			this.getWebDriverManager().getDriver().switchTo().activeElement();
			Assert.assertTrue(
					this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", warningTitle)
							.getText().equalsIgnoreCase("Warning"));

			// step 6
			this.getWebDriverManager()
					.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", warningOkButton)
					.click();

			// step 7
			this.getWebDriverManager().waitForAnimation();
			this.getWebDriverManager().getDriver().switchTo().activeElement();
			this.getWebDriverManager().sendText("xpath", filenameInput, "bar");
			this.getWebDriverManager().waitForAnimation();

			// save and close
			this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
					createFormSaveAndCloseElement).click();

		});

		// Step 8 Expected Output
		Assert.assertTrue(this.getWebDriverManager().getDriver().getCurrentUrl().contains("/studio/site-dashboard"));

		// Step 9
		this.getWebDriverManager().waitForFullExpansionOfTree();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", fooContentXpath)
				.click();
		this.getWebDriverManager().waitForAnimation();
		Assert.assertTrue(
				this.getWebDriverManager().getDriver().getCurrentUrl().contains("page=/articles/2016/12/bar.html"));

		// Step 10
		this.step10();
		// Step 11, 12, 13, 14 and 15
		this.step11();
		// Step 16
		this.step16();
		// Step 17
		this.step17();
		// Step 18
		this.step18();
		// Step 19
		this.step19();
		// Step 20
		this.step20();

	}

}
