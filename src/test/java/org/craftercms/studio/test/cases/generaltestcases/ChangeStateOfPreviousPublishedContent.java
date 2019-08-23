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
package org.craftercms.studio.test.cases.generaltestcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.cases.StudioBaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

/**
 * 
 * @author Juan Camacho
 *
 * 
 * 
 */

public class ChangeStateOfPreviousPublishedContent extends StudioBaseTest {

	private String userName;
	private String password;
	private String selectAllSegmentsCheckBox;
	private String selectAllCategoriesCheckBox;
	private String userOptions;
	private String userOptionsLogout;
	private String requestPublishButton;
	private String publishSubmitButton;
	private String cancelWorkflowContinueButton;
	private String staticAssetsButton;
	private String homeTree;
	private String dependenciesMenuOption;
	private String staticAssetsChildFolder;
	private String staticAssetsImagesChildFolder;
	private String generalSiteDropdown;
	private String pageStatus;
	private String staticAssetsGearImageXpath;
	private String articlesFolder;
	private String createFormFrameElementCss;
	private String generalEditOption;
	private String expandPagesTree;
	private String editedPageArticleName;
	private String articleTitle;
	private String siteDropdownElementXPath;
	private String articleContentCreatedName;
	private String gearImageXpath;
	private int numberOfAttemptsForElementsDisplayed;
	private static Logger logger = LogManager.getLogger(ChangeStateOfPreviousPublishedContent.class);

	@Parameters({"testId", "blueprint", "testUser", "testGroup"})
	@BeforeMethod
	public void beforeTest(String testId, String blueprint, String testUser, String testGroup) {
		apiTestHelper.createSite(testId, "", blueprint);
		apiTestHelper.createUserAddToGroup(testUser, testGroup);
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		articlesFolder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.articlesfolder");
		selectAllSegmentsCheckBox = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame2.article_select_all_segments_checkbox");
		selectAllCategoriesCheckBox = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame2.select_All_Categories_CheckBox");
		userOptions = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("dashboard.user_options");
		userOptionsLogout = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.user_options_logout");
		requestPublishButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.requestpublishtopnavoption");
		publishSubmitButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.approve&publish.submit");
		cancelWorkflowContinueButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("preview.workflow_cancellation_continue_Button");
		staticAssetsButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("preview.static_assets_button");
		homeTree = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.expand_GlobalEntry_Tree");
		dependenciesMenuOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.dependenciestopnavoption");
		staticAssetsChildFolder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("preview.static_assets_child_folder");
		staticAssetsImagesChildFolder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("preview.static_assets_images_child_folder");
		generalSiteDropdown = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sitedropdown");
		pageStatus = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("general.pageStatus");
		staticAssetsGearImageXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("preview.staticassets.gear.image.xpath");
		createFormFrameElementCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformframe");
		generalEditOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.edittopnavoption");
		expandPagesTree = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.expand_Pages_Tree");
		editedPageArticleName = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.editedarticlename");
		articleTitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.cssarticletitle");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		articleContentCreatedName = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.testingcontentitem");
		gearImageXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.gearimagexpath");
		this.numberOfAttemptsForElementsDisplayed = Integer.parseInt(constantsPropertiesManager
				.getSharedExecutionConstants().getProperty("crafter.numberofattemptsforelementdisplayed"));

	}

	public void login(String user, String loginpassword) {
		// login to application
		loginPage.loginToCrafter(user, loginpassword);
		// Wait for login page to close
		getWebDriverManager().waitUntilLoginCloses();

	}

	public void editPageArticleContent(String pageName) {

		getWebDriverManager().usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {

			// creating random values for URL field and InternalName field

			String randomInternalName = pageName;

			// Set basics fields of the new content created

			dashboardPage.updateFieldsOfPageArticleContent(randomInternalName, pageName);

			// Set the title of main content
			this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", articleTitle).clear();

			this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", articleTitle).sendKeys(pageName);

			// save and close
			this.getWebDriverManager().waitForAnimation();
			this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("id", "cstudioSaveAndClose").click();

		});

		this.getWebDriverManager().waitUntilSidebarOpens();

	}

	public void changeBodyToNotRequiredOnPageArticleContent() {
		previewPage.changeBodyOfArticlePageToNotRequired();

		previewPage.changeDateOfArticlePageToNotRequired();

	}

	private void renamePage(String parentPage, String newPageName) {

		dashboardPage.rightClickEditOnAPresentPage(parentPage);

		// creating new Page Article into the empty folder

		this.editPageArticleContent(newPageName);

	}

	private void renamePageWithWorkflowCancelation(String parentPage, String newPageName) {

		dashboardPage.rightClickEditOnAPresentPage(parentPage);

		// Cancel the Workflow

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", cancelWorkflowContinueButton);

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", cancelWorkflowContinueButton).click();

		// Edit PAge Article

		this.editPageArticleContent(newPageName);

	}

	private void logoutFromCrafter() {

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", userOptions);

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", userOptions).click();

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", userOptionsLogout);

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", userOptionsLogout).click();

	}

	private void requestPublish(String newPageArticleName) {
		this.getWebDriverManager().waitForFullExpansionOfTree();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				".//span[contains(text(),'" + newPageArticleName + "')]");
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				".//span[contains(text(),'" + newPageArticleName + "')]").click();

		this.getWebDriverManager().getDriver().navigate().refresh();

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", requestPublishButton);

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", requestPublishButton)
				.click();

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", publishSubmitButton);

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", publishSubmitButton)
				.click();

	}

	public void testScenario(String siteId, String testUser) {

		// Related to the bug:
		// issue https://github.com/craftercms/craftercms/issues/1557
		this.login(userName, password);

		logger.info("Go to Site Preview {}", siteId);
		homePage.goToPreviewPage(siteId);

		this.getWebDriverManager().clickElement("xpath", siteDropdownElementXPath);

		this.getWebDriverManager().waitUntilFolderOpens("xpath", expandPagesTree);

		this.getWebDriverManager().waitUntilSidebarOpens();


		// body not required Page-Article
		logger.info("Change Article Page body content to not required");

		this.changeBodyToNotRequiredOnPageArticleContent();

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("id",

				"admin-console");

		// expand Home tree
		this.getWebDriverManager().waitUntilFolderOpens("xpath", expandPagesTree);

		this.dashboardPage.expandHomeTree();

		//this.getWebDriverManager().getDriver().navigate().refresh();

		logger.info("Create Article Content");
		this.getWebDriverManager().waitForAnimation();
		previewPage.createPageArticleContent("test", "Testing1", "test", articlesFolder, selectAllCategoriesCheckBox,

				selectAllSegmentsCheckBox, "ArticleSubject", "ArticleAuthor", "ArticleSummary");

		// Switch back to the dashboard page
		this.getWebDriverManager().getDriver().switchTo().activeElement();
		this.getWebDriverManager().getDriver().navigate().refresh();

		// Open dependencies for the previous created element
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articlesFolder);

		this.getWebDriverManager().clickElement("xpath", articlesFolder);

		this.getWebDriverManager().waitUntilSidebarOpens();

		// Bulk Publish
		logger.info("Executing bulk publish");
		previewPage.bulkPublish("/", 30000);

		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().waitForFullExpansionOfTree();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articlesFolder);
		this.getWebDriverManager().clickIfFolderIsNotExpanded(articlesFolder);

		// Verify Article is published
		logger.info("Verify Article is published");
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().waitForFullExpansionOfTree();
		previewPage.verifyPageArticleIsPublished();

		// logout from Crafter
		logger.info("logout from Crafter");
		this.logoutFromCrafter();

		// login to application with author user
		logger.info("login to application with {} user", testUser);
		loginPage.loginToCrafter(testUser, testUser);

		logger.info("Go to Preview Page {}", siteId);
		this.homePage.goToPreviewPage(siteId);
		//getWebDriverManager().clickElement("xpath", siteDropdownElementXPath);

		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().waitUntilFolderOpens("xpath", expandPagesTree);

		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", generalEditOption);
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articlesFolder);

		this.getWebDriverManager().waitUntilContentTooltipIsHidden();
		this.getWebDriverManager().waitForAnimation();
		WebElement articlesFolderElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articlesFolder);

		if (!(articlesFolderElement.getAttribute("class").contains("open"))) {
			articlesFolderElement.click();
		}

		this.getWebDriverManager().waitForAnimation();

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articleContentCreatedName);

		this.getWebDriverManager().waitUntilContentTooltipIsHidden();

		this.getWebDriverManager().clickElement("xpath", articleContentCreatedName);

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", articleContentCreatedName);

		// Edit content Page with the Author User
		logger.info("Edit content Page with the Author User");
		String newPageArticleName = "Testing1Edited";
		this.renamePage(articleContentCreatedName, newPageArticleName);

		// request publish
		logger.info("Request Publish");
		this.getWebDriverManager().waitForAnimation();
		this.requestPublish(newPageArticleName);

		// Open dependencies for the previous created element
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().waitUntilPublishMaskedModalCloses();

		logger.info("Open dependencies for the previous created element");
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", generalEditOption);
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dependenciesMenuOption);

		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().clickElement("xpath", dependenciesMenuOption);

		// check dependencies are listed
		logger.info("Check Listed Dependencies");
		previewPage.checkDependencies();

		// Cancel the Workflow and Edit again the Page Article Content
		newPageArticleName = "Testing1Edited2";
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", editedPageArticleName);

		logger.info("Edit again the Page Article Page");

		this.renamePageWithWorkflowCancelation(editedPageArticleName, newPageArticleName);

		// Collapse Home tree

		logger.info("Collapse Home tree");

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", generalEditOption);

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", homeTree);

		this.getWebDriverManager().waitUntilFolderOpens("xpath", expandPagesTree);

		this.dashboardPage.expandHomeTree();

		logger.info("Click the Static Assets Button");

		this.getWebDriverManager().clickElement("xpath", staticAssetsButton);

		this.getWebDriverManager().clickElement("xpath", staticAssetsChildFolder);

		this.getWebDriverManager().clickElement("xpath", staticAssetsImagesChildFolder);

		this.getWebDriverManager().clickElement("xpath", staticAssetsGearImageXpath);
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dependenciesMenuOption);

		for (int i = 0; i < numberOfAttemptsForElementsDisplayed; i++) {
			try {
				this.getWebDriverManager().clickElement("xpath", pageStatus);
				this.getWebDriverManager().waitUntilAttributeContains("xpath", pageStatus, "class", "undefined live");
				break;
			} catch (TimeoutException e) {
				this.getWebDriverManager().takeScreenshot("PageNotPublishedOnTopNavBar");
				logger.warn("Content page is not published yet, checking again if it has published icon on top bar");
				this.getWebDriverManager().waitForAnimation();
				this.getWebDriverManager().clickElement("xpath", gearImageXpath);
				//getWebDriverManager().getDriver().navigate().refresh();
			}
		}

		Assert.assertTrue(this.getWebDriverManager().getDriver().findElement(By.xpath(pageStatus)).getAttribute("class")
				.contains("undefined live"));
	}

	@Parameters({"testId", "testUser"})
	@Test
	public void changeStateOfPreviousPublishedContent(String testId, String testUser) {
		this.testScenario(testId, testUser);
	}

	@Parameters({"testId", "testUser"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId, String testUser) {
		apiTestHelper.deleteSite(testId);
		apiTestHelper.deleteUser(testUser);
	}

}
