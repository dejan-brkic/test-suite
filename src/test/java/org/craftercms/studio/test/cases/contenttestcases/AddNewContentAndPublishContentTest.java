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

import org.craftercms.studio.test.cases.StudioBaseTest;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * 
 * @author Luis Hernandez
 *
 */

// Related to ticket: https://github.com/craftercms/craftercms/issues/1869
public class AddNewContentAndPublishContentTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String createFormFrameElementCss;
	private String createFormSaveAndCloseElement;
	private String siteDropdownElementXPath;
	private String createFormArticleMainTitleElementXPath;
	private String homeContent;
	private String createdContentXPath;
	private String siteDropdownListElementXPath;
	private String categoryDrowpdownXpath;
	private String pageTitleXpath;
	private String pageURL;

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String testId, String blueprint) {
		apiTestHelper.createSite(testId, "", blueprint);
		int exitCode = this.getWebDriverManager().goToDeliveryFolderAndExecuteSiteScriptThroughCommandLine(testId, "init");
		Assert.assertEquals(exitCode, 0, "Init site process failed");
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");

		createFormFrameElementCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformframe");
		createFormSaveAndCloseElement = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.saveandclosebutton");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		createFormArticleMainTitleElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformMainTitle");
		homeContent = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.home_Content_Page");
		createdContentXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.studio.deliverypagecontenttodelete");
		siteDropdownListElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdownlielement");
		categoryDrowpdownXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformcategorydropdown");
		pageURL = getWebDriverManager().environmentProperties.getProperty("delivery.base.url") +
				uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("delivery.verification.pageurl");
		pageTitleXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("delivery.verification.pagetitle");
	}

	@Parameters({"testId"})
	@Test()
	public void addNewContentAndPublishContentTest(String testId) {
		// dropdown panel)
		this.loginAndGoToSiteContentPagesStructure(testId);

		// expand pages folder
		dashboardPage.expandPagesTree();

		// Step2
		this.createContentAndPublishIt();

		String studioURL = this.getWebDriverManager().getDriver().getCurrentUrl();
		verifyThatPageIsOnLive(testId);
		deleteDeliveryContentPageTest(studioURL);
		verifyThatPageIsNotOnLive(testId);
	}

	public void createContentAndPublishIt() {
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", homeContent);
		// Step 1
		this.createPageCategoryLandingPage(homeContent);

		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().waitUntilSidebarOpens();

		this.publishElement(createdContentXPath);
		this.getWebDriverManager().waitUntilElementHasPublishedIcon(createdContentXPath);
	}

	public void publishElement(String elementLocator) {

		dashboardPage.rightClickOnAContentPage(elementLocator);
		// selecting the Publish option
		getWebDriverManager().usingContextMenu(() -> {
			dashboardPage.clickOnPublishOption();
		},"Pages");
		// moving to the publish dialog, clicking on Submit and confirm action
		this.confirmPublishAction();
		this.getWebDriverManager().waitUntilSidebarOpens();
	}

	public void confirmPublishAction() {
		// Switch to the form
		getWebDriverManager().getDriver().switchTo().activeElement();
		// Click on Publish button
		dashboardPage.clickApproveAndPublishSubmitButton();
		// switch to default content
		getWebDriverManager().getDriver().switchTo().defaultContent();
	}

	public void createPageCategoryLandingPage(String folderWebElementLocator) {
		// right clicking and clikc on create New Content option
		dashboardPage.rightClickCreatePageOnAPresentFolder(folderWebElementLocator);
		// selecting Page Category Landing Page
		dashboardPage.selectPageArticleContentType();
		// click on the Ok button to confirm the select content type above
		dashboardPage.clickOKButton();
		// creating new Page Article into the empty folder
		getWebDriverManager().getDriver().switchTo().defaultContent();
		this.createNewPageArticleContent();
	}

	public void createNewPageArticleContent() {

		getWebDriverManager().usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {
			// creating random values for URL field and InternalName field
			String randomURL = "testingPage";
			String randomInternalName = "testingPage";

			// Set basics fields of the new content created
			dashboardPage.setBasicFieldsOfNewPageArticleContent(randomURL, randomInternalName, "testingPage");

			// Set the title of main content
			this.getWebDriverManager().scrollDown();
			getWebDriverManager().sendText("xpath", createFormArticleMainTitleElementXPath, "testingPage");

			WebElement categoryDropdown = this.getWebDriverManager()
					.driverWaitUntilElementIsPresentAndDisplayed("xpath", categoryDrowpdownXpath);
			Select select = new Select(categoryDropdown);
			select.selectByValue("style");
			
			// save and close
			this.getWebDriverManager().waitForAnimation();
			this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", createFormSaveAndCloseElement)
					.click();
		});

		this.getWebDriverManager().waitUntilSidebarOpens();

	}

	public void loginAndGoToSiteContentPagesStructure(String siteId) {
		// login to application
		loginPage.loginToCrafter(userName, password);

		// Wait for login page to close
		getWebDriverManager().waitUntilLoginCloses();

		// go to preview page
		homePage.goToPreviewPage(siteId);
		getWebDriverManager().clickElement("xpath", siteDropdownElementXPath);

	}

	public void verifyThatPageIsOnLive(String siteId) {
		getWebDriverManager().goToDeliveryFromStudio(siteId);
		getWebDriverManager().goToUrl(pageURL);
		getWebDriverManager().waitUntilElementIsDisplayed("xpath", pageTitleXpath);
		Assert.assertTrue(getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", pageTitleXpath)
				.getText().equalsIgnoreCase("testingpage"));
	}

	public void deleteDeliveryContentPageTest(String url) {
		getWebDriverManager().goToUrl(url);

		// Step2
		getWebDriverManager().waitUntilSidebarOpens();

		// right click to delete
		dashboardPage.rightClickToDeleteContent(createdContentXPath);

		// confirmation
		dashboardPage.clicktoDeleteContent();

		// submittal complete ok
		dashboardPage.clickOKSubmittalComplete();

		getWebDriverManager().waitForAnimation();
		Assert.assertFalse(getWebDriverManager().isElementPresentByXpath(createdContentXPath));
	}

	public void verifyThatPageIsNotOnLive(String siteId) {
		getWebDriverManager().goToDeliveryFromStudio(siteId);
		getWebDriverManager().goToUrl(pageURL);
		getWebDriverManager().waitUntilElementIsDisplayed("xpath", pageTitleXpath);
		Assert.assertTrue(getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", pageTitleXpath)
				.getText().equalsIgnoreCase("The page you are looking for doesn't exist."));
	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
		int exitCode = getWebDriverManager().goToDeliveryFolderAndExecuteSiteScriptThroughCommandLine(testId, "remove");
		Assert.assertEquals(exitCode, 0, "Remove site process failed");

	}
}
