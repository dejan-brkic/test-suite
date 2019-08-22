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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * 
 * @author luishernandez
 *
 */
// Test Case Studio- Site Content ID:44
public class FileRenameAgainAndDeleteTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String createFormFrameElementCss;
	private String createFormSaveAndCloseElement;
	private String dashboardLink;
	private String editRecentlyContentCreated;
	private String fooContentXpath;
	private String editURLButton;
	private String warningTitle;
	private String warningOkButton;
	private String filenameInput;
	private String deleteContent;
	private String deleteContentOK;
	private String recentlyActivityItemName;
	private String recentlyActivityItemIcon;
	private String recentlyActivityItemURL;
	private String recentlyActivitySelectAll;
	private String recentActivityContentName;
	private String recentActivityContentURL;
	private String recentlyActivityTable;
	private String folder2016Locator;
	private String siteContentXpath;
	private String articlesFolder;
	private String submittalCompleteOK;
	private static Logger logger = LogManager.getLogger(FileRenameAgainAndDeleteTest.class);

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		createFormFrameElementCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformframe");
		createFormSaveAndCloseElement = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.saveandclosebutton");
		dashboardLink = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.dashboard.dashboardlink");
		editRecentlyContentCreated = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.edit.option");
		fooContentXpath = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("general.foocontent");
		editURLButton = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("frame1.editurlbutton");
		warningTitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame1.warning.warningtitle");
		warningOkButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame1.warning.okbutton");
		filenameInput = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("frame1.filenameinput");
		fooContentXpath = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("general.foocontent");
		deleteContent = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.delete.option");
		deleteContentOK = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.delete_content_OK");
		recentlyActivityItemName = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.itemname");
		recentlyActivityItemIcon = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.itemicon");
		recentlyActivityItemURL = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.itemurl");
		recentlyActivitySelectAll = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.selectall");
		recentActivityContentName = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.contentname");
		recentActivityContentURL = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.contenturl");
		recentlyActivityTable = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.tablebody");
		folder2016Locator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.articles.2016folder");
		siteContentXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.site_content");
		articlesFolder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.articlesfolder");
		submittalCompleteOK = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.submitall.ok");
	}

	public void step22() {
		goToDashboardAndCheck(true);
	}

	public void step25() {
		// click on dashboard
		goToDashboardAndCheck(false);
	}

	public void goToDashboardAndCheck(boolean checkingBeforeDeleteContent) {
		// click on dashboard
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dashboardLink);
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dashboardLink).click();

		this.getWebDriverManager().waitUntilDashboardWidgetsAreLoaded();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", recentlyActivitySelectAll);
		Select categoriesDropDown = new Select(
				this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentlyActivitySelectAll));

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
			this.checkRecentActivityItems(webElement, checkingBeforeDeleteContent);
		}

	}

	public void checkRecentActivityItems(WebElement element, boolean checkingBeforeDeleteContent) {
		this.getWebDriverManager().waitForAnimation();
		String itemName = element.findElement(By.xpath(recentlyActivityItemName)).getText();
		String itemIconClass = element.findElement(By.xpath(recentlyActivityItemIcon)).getAttribute("class");
		String itemURL = element.findElement(By.xpath(recentlyActivityItemURL)).getText();

		if (checkingBeforeDeleteContent) {
			checkDisplayedItemsBeforeDelete(itemIconClass, itemURL, itemName);
		} else {
			checkDisplayedItemsAfterDelete(itemIconClass, itemURL, itemName);
		}

	}

	public void checkDisplayedItemsBeforeDelete(String itemIconClass, String itemURL, String itemName) {
		if ((itemIconClass.contains("deleted")) || (itemIconClass.contains("fa-file-o"))) {
			switch (itemName) {
			case "bar.xml":
				Assert.assertTrue(itemURL.equalsIgnoreCase("/articles/2016/12/bar.xml"));
				break;
			case "foo.xml":
				Assert.assertTrue(itemURL.equalsIgnoreCase("/articles/2016/12/foo.xml"));
				break;
			default:
				Assert.assertTrue(itemURL.equalsIgnoreCase("/articles/2016/12/baz.xml"));
				break;
			}
		}
	}

	public void checkDisplayedItemsAfterDelete(String itemIconClass, String itemURL, String itemName) {
		if (itemIconClass.contains("deleted")) {
			switch (itemName) {
			case "bar.xml":
				Assert.assertTrue(itemURL.equalsIgnoreCase("/articles/2016/12/bar.xml"));
				break;
			case "foo.xml":
				Assert.assertTrue(itemURL.equalsIgnoreCase("/articles/2016/12/foo.xml"));
				break;
			case "testimage.jpg":
				Assert.assertTrue(itemURL.contains("/static-assets/item/images/"));
				Assert.assertTrue(itemURL.contains("/testimage.jpg"));
				break;
			default:
				Assert.assertTrue(itemURL.equalsIgnoreCase("/articles/2016/12/baz.xml"));
				break;
			}
		}
	}

	public void step16(String testId) {
		// login to application
		loginPage.loginToCrafter(userName, password);

		getWebDriverManager().waitUntilLoginCloses();

		// go to preview page
		homePage.goToPreviewPage(testId);

		getWebDriverManager().clickElement("xpath", siteContentXpath);

		this.getWebDriverManager().waitUntilSidebarOpens();

		this.getWebDriverManager().waitForAnimation();
		dashboardPage.expandHomeTree();

		// reload page
		getWebDriverManager().getDriver().navigate().refresh();
		this.getWebDriverManager().waitUntilHomeIsOpened();

		// expand Articles folder
		logger.info("Expanding articles folder and editing previoulsy created article");
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articlesFolder);
		dashboardPage.expandParentFolder(articlesFolder);

		dashboardPage.expandParentFolder(folder2016Locator);

		dashboardPage.expandParentFolder(
				folder2016Locator + "/../../../../../div[@class='ygtvchildren']//span[text()='12']");
	}

	@Parameters({"testId"})
	@Test()
	public void fileRenameFileXMLRenameAgainAndDeleteTest(String testId) {

		this.step16(testId);
		
		// step 17,18, 19 and 20
		logger.info("Editing previously created article");
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
			// step 17
			Assert.assertTrue(this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", ".//h1/span")
					.getText().equalsIgnoreCase("foo"));

			// step 18
			this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", editURLButton).click();

			this.getWebDriverManager().getDriver().switchTo().activeElement();
			Assert.assertTrue(this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", warningTitle)
					.getText().equalsIgnoreCase("Warning"));

			this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", warningOkButton)
					.click();

			// step 19
			this.getWebDriverManager().waitForAnimation();
			this.getWebDriverManager().getDriver().switchTo().activeElement();
			this.getWebDriverManager().sendText("xpath", filenameInput, "baz");
			this.getWebDriverManager().waitForAnimation();

			// Step20 Click on Save and close
			this.getWebDriverManager()
					.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", createFormSaveAndCloseElement)
					.click();

		});

		// Step 21
		this.getWebDriverManager().waitForFullExpansionOfTree();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", fooContentXpath).click();
		this.getWebDriverManager().waitForAnimation();
		logger.info("Checking article update on the current url");
		Assert.assertTrue(this.getWebDriverManager().getDriver().getCurrentUrl().contains("page=/articles/2016/12/baz.html"));

		// Step 22
		this.step22();

		// Step 23 and 24
		logger.info("Deleting article content");
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", fooContentXpath);
		this.getWebDriverManager().contextClick("xpath", fooContentXpath, false);
		getWebDriverManager().usingContextMenu(() -> {
			WebElement delContent = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
					deleteContent);
			delContent.click();
		}, "Pages");

		WebElement confirmDelete = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
				deleteContentOK);
		confirmDelete.click();

		WebElement submittalComplete = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
				submittalCompleteOK);
		submittalComplete.click();

		logger.info("Checking items on recent activity dashboard widget");
		this.step25();
	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
