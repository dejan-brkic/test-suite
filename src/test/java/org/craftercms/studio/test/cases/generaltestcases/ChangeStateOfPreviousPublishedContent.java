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
		driverManager.waitUntilLoginCloses();

	}

	public void editPageArticleContent(String pageName) {

		driverManager.usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {

			// creating random values for URL field and InternalName field

			String randomInternalName = pageName;

			// Set basics fields of the new content created

			dashboardPage.updateFieldsOfPageArticleContent(randomInternalName, pageName);

			// Set the title of main content
			this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", articleTitle).clear();

			this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", articleTitle).sendKeys(pageName);

			// save and close
			this.driverManager.waitForAnimation();
			this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("id", "cstudioSaveAndClose").click();

		});

		this.driverManager.waitUntilSidebarOpens();

	}

	public void changeBodyToNotRequiredOnPageArticleContent() {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", generalSiteDropdown).click();

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

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", cancelWorkflowContinueButton);

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", cancelWorkflowContinueButton).click();

		// Edit PAge Article

		this.editPageArticleContent(newPageName);

	}

	private void logoutFromCrafter() {

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", userOptions);

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", userOptions).click();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", userOptionsLogout);

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", userOptionsLogout).click();

	}

	private void requestPublish(String newPageArticleName) {
		this.driverManager.waitForFullExpansionOfTree();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				".//span[contains(text(),'" + newPageArticleName + "')]");
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				".//span[contains(text(),'" + newPageArticleName + "')]").click();

		this.driverManager.getDriver().navigate().refresh();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", requestPublishButton);

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", requestPublishButton)
				.click();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", publishSubmitButton);

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", publishSubmitButton)
				.click();

	}

	public void testScenario(String siteId, String testUser) {

		// Related to the bug:
		// issue https://github.com/craftercms/craftercms/issues/1557
		this.login(userName, password);

		logger.info("Go to Site Preview {}", siteId);
		homePage.goToPreviewPage(siteId);

		this.driverManager.clickElement("xpath", siteDropdownElementXPath);

		this.driverManager.waitUntilFolderOpens("xpath", expandPagesTree);

		this.driverManager.waitUntilSidebarOpens();


		// body not required Page-Article
		logger.info("Change Article Page body content to not required");

		this.changeBodyToNotRequiredOnPageArticleContent();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("id",

				"admin-console");

		// expand Home tree
		this.driverManager.waitUntilFolderOpens("xpath", expandPagesTree);

		this.dashboardPage.expandHomeTree();

		//this.driverManager.getDriver().navigate().refresh();

		logger.info("Create Article Content");
		this.driverManager.waitForAnimation();
		previewPage.createPageArticleContent("test", "Testing1", "test", articlesFolder, selectAllCategoriesCheckBox,

				selectAllSegmentsCheckBox, "ArticleSubject", "ArticleAuthor", "ArticleSummary");

		// Switch back to the dashboard page
		this.driverManager.getDriver().switchTo().activeElement();
		this.driverManager.getDriver().navigate().refresh();

		// Open dependencies for the previous created element
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articlesFolder);

		this.driverManager.clickElement("xpath", articlesFolder);

		//this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", sidebarMenuOption).click();

		//this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", sidebarMenuOption).click();

		this.driverManager.waitUntilSidebarOpens();

		// Bulk Publish
		logger.info("Executing bulk publish");
		previewPage.bulkPublish("/", 30000);

		this.driverManager.waitForAnimation();
		this.driverManager.waitForFullExpansionOfTree();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articlesFolder);
		this.driverManager.clickIfFolderIsNotExpanded(articlesFolder);

		// Verify Article is published
		logger.info("Verify Article is published");
		this.driverManager.waitForAnimation();
		this.driverManager.waitForFullExpansionOfTree();
		previewPage.verifyPageArticleIsPublished();

		// logout from Crafter
		logger.info("logout from Crafter");
		this.logoutFromCrafter();

		// login to application with author user
		logger.info("login to application with {} user", testUser);
		loginPage.loginToCrafter(testUser, testUser);

		logger.info("Go to Preview Page {}", siteId);
		this.homePage.goToPreviewPage(siteId);

		this.driverManager.waitForAnimation();
		this.driverManager.waitUntilFolderOpens("xpath", expandPagesTree);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", generalEditOption);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articlesFolder);

		this.driverManager.waitUntilContentTooltipIsHidden();
		this.driverManager.waitForAnimation();
		WebElement articlesFolderElement = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articlesFolder);

		if (!(articlesFolderElement.getAttribute("class").contains("open"))) {
			articlesFolderElement.click();
		}

		this.driverManager.waitForAnimation();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articleContentCreatedName);

		this.driverManager.waitUntilContentTooltipIsHidden();

		this.driverManager.clickElement("xpath", articleContentCreatedName);

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", articleContentCreatedName);

		// Edit content Page with the Author User
		logger.info("Edit content Page with the Author User");
		String newPageArticleName = "Testing1Edited";
		this.renamePage(articleContentCreatedName, newPageArticleName);

		// request publish
		logger.info("Request Publish");
		this.driverManager.waitForAnimation();
		this.requestPublish(newPageArticleName);

		// Open dependencies for the previous created element
		this.driverManager.waitForAnimation();
		this.driverManager.waitUntilPublishMaskedModalCloses();

		logger.info("Open dependencies for the previous created element");
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", generalEditOption);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dependenciesMenuOption);

		this.driverManager.waitForAnimation();
		this.driverManager.clickElement("xpath", dependenciesMenuOption);

		// check dependencies are listed
		logger.info("Check Listed Dependencies");
		previewPage.checkDependencies();

		// Cancel the Workflow and Edit again the Page Article Content
		newPageArticleName = "Testing1Edited2";
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", editedPageArticleName);

		logger.info("Edit again the Page Article Page");

		this.renamePageWithWorkflowCancelation(editedPageArticleName, newPageArticleName);

		// Collapse Home tree

		logger.info("Collapse Home tree");

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", generalEditOption);

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", homeTree);

		this.driverManager.waitUntilFolderOpens("xpath", expandPagesTree);

		this.dashboardPage.expandHomeTree();

		logger.info("Click the Static Assets Button");

		this.driverManager.clickElement("xpath", staticAssetsButton);

		this.driverManager.clickElement("xpath", staticAssetsChildFolder);

		this.driverManager.clickElement("xpath", staticAssetsImagesChildFolder);

		this.driverManager.clickElement("xpath", staticAssetsGearImageXpath);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dependenciesMenuOption);

		for (int i = 0; i < numberOfAttemptsForElementsDisplayed; i++) {
			try {
				this.driverManager.clickElement("xpath", pageStatus);
				this.driverManager.waitUntilAttributeContains("xpath", pageStatus, "class", "undefined live");
				break;
			} catch (TimeoutException e) {
				this.driverManager.takeScreenshot("PageNotPublishedOnTopNavBar");
				logger.warn("Content page is not published yet, checking again if it has published icon on top bar");
				this.driverManager.waitForAnimation();
				this.driverManager.clickElement("xpath", gearImageXpath);
				//driverManager.getDriver().navigate().refresh();
			}
		}

		Assert.assertTrue(this.driverManager.getDriver().findElement(By.xpath(pageStatus)).getAttribute("class")
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
