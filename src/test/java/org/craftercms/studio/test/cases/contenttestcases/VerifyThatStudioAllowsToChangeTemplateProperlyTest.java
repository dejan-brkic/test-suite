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
import org.openqa.selenium.WebElement;

/**
 * 
 * @author Luis Hernandez
 *
 */

// Test Case Studio- Site Content ID:15
public class VerifyThatStudioAllowsToChangeTemplateProperlyTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String siteDropdownElementXPath;
	private String createFormFrameElementCss;
	private String changeTemplateOptionXpath;
	private String searchResultsContent;
	private String createFormSaveAndCloseElement;
	private String editRecentlyContentCreated;
	private String editPageContentType;
	private String editPageContentPageName;

	private static final Logger logger = LogManager
			.getLogger(VerifyThatStudioAllowsToChangeTemplateProperlyTest.class);

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String siteId, String blueprint) {
		apiTestHelper.createSite(siteId, "", blueprint);
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		searchResultsContent = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.searchresultscontent");
		createFormFrameElementCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformframe");
		changeTemplateOptionXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.change.template.option");
		createFormSaveAndCloseElement = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.saveandclosebutton");
		editRecentlyContentCreated = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.edit.option");
		editPageContentType = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame2.pagelanding.contenttype");
		editPageContentPageName = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame2.pagelanding.pagename");
		
	}

	public void loginAndGoToPreview(String testId) {
		loginPage.loginToCrafter(userName, password);

		getWebDriverManager().waitUntilLoginCloses();

		// go to preview page
		homePage.goToPreviewPage(testId);

		this.getWebDriverManager().clickElement("xpath", siteDropdownElementXPath);

		this.getWebDriverManager().waitUntilSidebarOpens();

		// Expand Home Tree
		dashboardPage.expandHomeTree();
	}

	public void editSearchResultAfterChangeTemplate() {
		logger.info("Editing search results content after change template");
		this.getWebDriverManager().waitForAnimation();
		logger.info("Opening edit form");
		getWebDriverManager().usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {
			// set Article title
			dashboardPage.setArticlesTitleWhenChangeTemplate("SearchResultsArticleTitle");
			// select the Articles category
			dashboardPage.selectFirstCategoryOfPagArticle();

			// save and close
			this.getWebDriverManager()
					.driverWaitUntilElementIsPresentAndDisplayed("xpath", createFormSaveAndCloseElement)
					.click();
		});

		this.getWebDriverManager().getDriver().navigate().refresh();
		this.getWebDriverManager().waitUntilSidebarOpens();
	}

	public void changeTemplateForSearchResultsContent() {
		logger.info("Publish testing article created previously");
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				searchResultsContent);
		this.getWebDriverManager().contextClick("xpath", searchResultsContent, false);
		getWebDriverManager().usingContextMenu(() -> {
			WebElement changeTemplateOption = this.getWebDriverManager()
					.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
							changeTemplateOptionXpath);
			changeTemplateOption.click();
		}, "Pages");

		this.acceptChangeTemplateAction();
		this.confirmChangeTemplateAction();

	}

	public void checkContentInfoForSearchResultsContent() {
		logger.info("Checking content type for Search Results page");
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				searchResultsContent);
		this.getWebDriverManager().contextClick("xpath", searchResultsContent, false);
		getWebDriverManager().usingContextMenu(() -> {
			WebElement editOption = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
					editRecentlyContentCreated);
			editOption.click();
		}, "Pages");

		this.getWebDriverManager().waitForAnimation();
		logger.info("Opening edit form");
		getWebDriverManager().usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {
			// edit internal name
			Assert.assertTrue("Page - Category Landing".equalsIgnoreCase(this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",editPageContentType).getText()));
			Assert.assertTrue("search-results".equalsIgnoreCase(this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",editPageContentPageName).getText()));
		});
	}

	public void confirmChangeTemplateAction() {
		// Switch to the form
		getWebDriverManager().getDriver().switchTo().activeElement();
		// Click on Publish button
		dashboardPage.clickChangeTemplateSubmitButton();
		// switch to default content
		getWebDriverManager().getDriver().switchTo().defaultContent();
	}

	public void acceptChangeTemplateAction() {
		// Switch to the form
		getWebDriverManager().getDriver().switchTo().activeElement();
		// Click on Publish button
		dashboardPage.clickOnChangeTemplateYesButton();
		// switch to default content
		getWebDriverManager().getDriver().switchTo().defaultContent();
	}

	@Parameters({"testId"})
	@Test()
	public void verifyThatStudioAllowsToChangeTemplateProperlyTest(String testId) {
		// Step 1
		this.loginAndGoToPreview(testId);

		// Steps 2, 3, 4 and 5
		this.changeTemplateForSearchResultsContent();

		// Step 6
		this.editSearchResultAfterChangeTemplate();
		this.checkContentInfoForSearchResultsContent();
	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
