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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.cases.StudioBaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author luishernandez
 *
 */
// Test Case Studio- Site Content ID:31
public class CopyPasteToFolderWithCollisionTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String createFormFrameElementCss;
	private String createFormSaveAndCloseElement;
	private String createFormMainTitleElementXPath;
	private String randomURL;
	private String randomInternalName;
	private String configurationSetUp;
	private String dashboardLink;
	private String recentActivityContentURL;
	private String recentActivityContentName;
	private String fooContentXpath;
	private String pasteContent;
	private String folderChildContent;
	private String folderContent;
	private String copyContent;
	private String fooOriginalContent;
	private String recentActivitySecondContentName;
	private String recentActivitySecondContentURL;
	private String recentActivityThirdContentName;
	private String recentActivityThirdContentURL;
	private String folderSecondChildContent;
	private String secondPasteContentText;
	private static final Logger logger = LogManager.getLogger(CopyPasteToFolderWithCollisionTest.class);

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		createFormFrameElementCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformframe");
		createFormSaveAndCloseElement = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.saveandclosebutton");
		createFormMainTitleElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.createformTitle");
		dashboardLink = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.dashboard.dashboardlink");
		copyContent = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("rightclick.copy.option");
		recentActivityContentURL = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.contenturl");
		recentActivitySecondContentURL = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.contentsecondurl");
		recentActivityThirdContentURL = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.contentthirdurl");
		recentActivityContentName = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.contentname");
		recentActivitySecondContentName = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.contentsecondname");
		recentActivityThirdContentName = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.contentthirdname");
		fooContentXpath = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("general.foocontent");
		pasteContent = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("rightclick.paste.option");
		folderContent = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("general.afolder");
		folderChildContent = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.afolder.childcontentafterpaste");
		folderSecondChildContent = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.afolder.childsecondcontentafterpaste");
		fooOriginalContent = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.foocontent");
		randomURL = "foo";
		randomInternalName = "foo";
		configurationSetUp = "<content-as-folder>false</content-as-folder>";

	}

	public void changeBodyToNotRequiredOnEntryContent() {
		previewPage.changeBodyOfEntryContentPageToNotRequired();
	}

	public void modifyPageXMLDefinition() {
		previewPage.modifyPageXMLDefinitionContentAsFolderEntryContentType(configurationSetUp);
	}

	public void createContent() {
		// right click to see the the menu
		driverManager.waitUntilPageLoad();
		driverManager.waitUntilSidebarOpens();
		dashboardPage.rightClickToSeeMenu();

		// Select Entry Content Type
		dashboardPage.clickEntryCT();

		// Confirm the Content Type selected
		dashboardPage.clickOKButton();

		driverManager.usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {
			// creating random values for URL field and InternalName field

			// Set basics fields of the new content created
			dashboardPage.setBasicFieldsOfNewContent(randomURL, randomInternalName);

			// Set the title of main content
			driverManager.sendText("xpath", createFormMainTitleElementXPath, "MainTitle");

			// save and close

			this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", createFormSaveAndCloseElement)
					.click();
		});

		this.driverManager.waitUntilSidebarOpens();

	}

	public void loginAndChangeContentTypeConfiguration() {
		// login to application
		loginPage.loginToCrafter(userName, password);

		driverManager.waitUntilLoginCloses();

		// go to preview page
		homePage.goToPreviewPage();

		// body not required
		this.changeBodyToNotRequiredOnEntryContent();

		// modify page XML definition
		this.modifyPageXMLDefinition();
	}

	public void createContentAndFolder() {
		// expand pages folder
		dashboardPage.expandPagesTree();

		// right click to see the the menu
		logger.info("Creating new folder");
		dashboardPage.rightClickNewFolderOnHome();

		// Set the name of the folder
		dashboardPage.setFolderName("a-folder");

		// reload page
		driverManager.getDriver().navigate().refresh();

		// create content
		createContent();

		// reload page
		driverManager.getDriver().navigate().refresh();
	}

	public void goToDashboardAndManageContents() {
		// click on dashboard
		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dashboardLink);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dashboardLink).click();

		// check items on My Recent Activity widget
		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentName);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentURL);

		this.driverManager.waitForAnimation();
		Assert.assertTrue(
				this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentName)
						.getText().contains("foo"));
		this.driverManager.waitForAnimation();
		Assert.assertTrue(
				this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentURL)
						.getText().contains("/foo.xml"));

		// expand pages folder
		this.driverManager.waitUntilSidebarOpens();
		this.driverManager.waitForAnimation();
		dashboardPage.expandPagesTree();

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", fooContentXpath);
		this.driverManager.contextClick("xpath", fooContentXpath, false);
		driverManager.usingContextMenu(() -> {
			WebElement copyContent = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
					this.copyContent);
			copyContent.click();
		}, "Pages");
		this.driverManager.waitForAnimation();

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", folderContent);
		this.driverManager.contextClick("xpath", folderContent, false);
		driverManager.usingContextMenu(() -> {
			WebElement pasteContentElement = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
					pasteContent);
			pasteContentElement.click();
		}, "Pages");

		this.driverManager.waitForAnimation();
		this.driverManager.waitForFullExpansionOfTree();
		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", folderChildContent).isDisplayed());
		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", fooOriginalContent).isDisplayed());

		// click on dashboard
		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dashboardLink);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dashboardLink).click();

		// check items on My Recent Activity widget
		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentName);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentURL);

		this.driverManager.waitForAnimation();
		Assert.assertTrue(
				this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentName)
						.getText().contains("foo"));
		this.driverManager.waitForAnimation();
		Assert.assertTrue(
				this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentURL)
						.getText().contains("/a-folder/foo.xml"));

		this.driverManager.waitForAnimation();
		Assert.assertTrue(
				this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivitySecondContentName)
						.getText().contains("foo"));
		this.driverManager.waitForAnimation();
		Assert.assertTrue(
				this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivitySecondContentURL)
						.getText().contains("/foo.xml"));
	}
	
	public void setup() {

		this.loginAndChangeContentTypeConfiguration();
		
		this.createContentAndFolder();
		
		this.goToDashboardAndManageContents();
	
	}

	public void step4() {
		this.driverManager.waitUntilSidebarOpens();
		this.driverManager.waitForAnimation();
		dashboardPage.expandPagesTree();

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", fooContentXpath);
		this.driverManager.contextClick("xpath", fooContentXpath, false);
		driverManager.usingContextMenu(() -> {
			WebElement copyContent = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
					this.copyContent);
			copyContent.click();
		}, "Pages");
		this.driverManager.waitForAnimation();
	}

	public void step5() {
		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", folderContent);
		this.driverManager.contextClick("xpath", folderContent, false);
		driverManager.usingContextMenu(() -> {
			WebElement pasteContentElement = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
					pasteContent);
			pasteContentElement.click();
		}, "Pages");

		this.driverManager.waitForAnimation();
		this.driverManager.waitForFullExpansionOfTree();
		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", folderSecondChildContent)
				.isDisplayed());

		this.secondPasteContentText = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", folderSecondChildContent).getText();
	}

	public void step6() {
		// click on dashboard
		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dashboardLink);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dashboardLink).click();

		// check items on My Recent Activity widget
		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentName);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentURL);

		this.driverManager.waitForAnimation();
		Assert.assertTrue(
				this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentName)
						.getText().contains(secondPasteContentText));
		this.driverManager.waitForAnimation();

		String pageUrl = secondPasteContentText.replace("foo ", "foo-") + ".xml";
		Assert.assertTrue(
				this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentURL)
						.getText().contains("/a-folder/" + pageUrl));

		this.driverManager.waitForAnimation();
		Assert.assertTrue(
				this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivitySecondContentName)
						.getText().contains("foo"));
		this.driverManager.waitForAnimation();
		Assert.assertTrue(
				this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivitySecondContentURL)
						.getText().contains("/a-folder/foo.xml"));

		this.driverManager.waitForAnimation();
		Assert.assertTrue(
				this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityThirdContentName)
						.getText().contains("foo"));
		this.driverManager.waitForAnimation();
		Assert.assertTrue(
				this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityThirdContentURL)
						.getText().contains("/foo.xml"));

	}

	@Test(priority = 0)
	public void copyPasteToFolderWithCollisionAndDuplicateTest() {
		this.setup();

		this.step4();

		this.step5();

		this.step6();
	}

}
