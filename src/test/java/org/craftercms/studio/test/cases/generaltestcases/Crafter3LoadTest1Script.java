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

import org.craftercms.studio.test.cases.StudioBaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * @author luishernandez
 *
 */
public class Crafter3LoadTest1Script extends StudioBaseTest {

	private String userName;
	private String password;
	private String parentFolderName;
	private String harnessFolderName;
	private String emptyFolderName;
	private String bigTree1FolderName;
	private String bigTree2FolderName;
	private String myTestFolderName;
	private String anotherTestFolderName;
	private String parentFolderLocator;
	private String harnessFolderLocator;
	private String emptyFolderLocator;
	private String bigTree1FolderLocator;
	private String bigTree2FolderLocator;
	private String parentFolderDivOnTreeSelectorLocator;
	private String mytestFolderLocator;
	private String anotherTestFolderLocator;
	private String bigTree1FolderDivOnSelectorXPath;
	private String bigTree2BigTree1ChildFolderLocator;
	private String myTestBigTreeChildFolderLocator;
	private String anotherTestBigTreeChildFolderLocator;
	private String styleLocator;
	private String entertainmentLocator;
	private String healthLocator;
	private String technologyLocator;
	private String siteDropdownElementXPath;
	private String createFormArticleMainTitleElementXPath;
	private String createFormSaveAndCloseElement;
	private String historyFirstItemCheckbBox;
	private String historySecondItemCheckbBox;
	private String differencesDialogId;
	private String differencesDialogRemovedMarkXpath;
	private String differencesDialogAddedMarkXpath;
	private String historyInitialCommitRevertButton;
	private String studioLogo;
	private String createFormFrameElementCss;
	private String createFormTitleElementXPath;
	private String actionsHeaderXpath;
	private String siteDropdownListElementXPath;
	private String categoryDrowpdownXpath;
	private static Logger logger = LogManager.getLogger(Crafter3LoadTest1Script.class);

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String testId, String blueprint) {
		apiTestHelper.createSite(testId, "", blueprint);
		this.parentFolderName = "tester-" + RandomStringUtils.randomAlphabetic(5).toLowerCase();
		this.harnessFolderName = "harness";
		this.emptyFolderName = "empty-folder";
		this.bigTree1FolderName = "big-tree1";
		this.bigTree2FolderName = "big-tree2";
		this.myTestFolderName = "mytest";
		this.anotherTestFolderName = "anothertest";

		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");

		this.parentFolderLocator = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty(
				"complexscenarios.crafter3loadtest.parentfolder") + this.parentFolderName + "')]";
		harnessFolderLocator = this.parentFolderLocator + uiElementsPropertiesManager
				.getSharedUIElementsLocators().getProperty("complexscenarios.crafter3loadtest.childfolder")
				+ this.harnessFolderName + "')]";
		emptyFolderLocator = harnessFolderLocator + uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.crafter3loadtest.childfolder") + this.emptyFolderName + "')]";
		bigTree1FolderLocator = harnessFolderLocator + uiElementsPropertiesManager
				.getSharedUIElementsLocators().getProperty("complexscenarios.crafter3loadtest.childfolder")
				+ this.bigTree1FolderName + "')]";
		bigTree2FolderLocator = harnessFolderLocator + uiElementsPropertiesManager
				.getSharedUIElementsLocators().getProperty("complexscenarios.crafter3loadtest.childfolder")
				+ this.bigTree2FolderName + "')]";
		mytestFolderLocator = harnessFolderLocator + uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.crafter3loadtest.childfolder") + this.myTestFolderName + "')]";
		anotherTestFolderLocator = harnessFolderLocator + uiElementsPropertiesManager
				.getSharedUIElementsLocators().getProperty("complexscenarios.crafter3loadtest.childfolder")
				+ this.anotherTestFolderName + "')]";
		parentFolderDivOnTreeSelectorLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.parentfolderdivontreeselector");
		styleLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.crafter3loadtest.stylecontentpage");
		entertainmentLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.crafter3loadtest.entertaimentcontentpage");
		healthLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.crafter3loadtest.healthcontentpage");
		technologyLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.crafter3loadtest.technologycontentpage");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		createFormFrameElementCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformframe");
		createFormArticleMainTitleElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformMainTitle");
		createFormTitleElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformtitle");
		createFormSaveAndCloseElement = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.saveandclosebutton");
		historyFirstItemCheckbBox = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.crafter3loadtest.historydialog.firstitemcheckbox");
		historySecondItemCheckbBox = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.crafter3loadtest.historydialog.seconditemcheckbox");
		differencesDialogId = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.differencedialogid");
		differencesDialogRemovedMarkXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.crafter3loadtest.differencedialog_removedmark");
		differencesDialogAddedMarkXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.crafter3loadtest.differencedialog_addedmark");
		historyInitialCommitRevertButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.crafter3loadtest.historydialog.initialcommittrevertbutton");
		studioLogo = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.studiologo");
		actionsHeaderXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.historydialogactionsheader");
		siteDropdownListElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdownlielement");
		categoryDrowpdownXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformcategorydropdown");

	}

	public void createFolderOnAPresentFolder(String folderName, String parentLocator) {
		// Right click and click on New Folder option
		dashboardPage.rightClickNewFolderOnAPresentFolder(parentLocator);
		// Set the name of the folder
		dashboardPage.setFolderName(folderName);
	}

	public void createFolderOnHome(String folderName) {
		// right click to see the the menu
		dashboardPage.rightClickToFolderOnHome();
		// Set the name of the folder
		dashboardPage.setFolderName(folderName);
	}

	public void loginAndGoToSiteContentPagesStructure(String siteId) {
		// login to application
		loginPage.loginToCrafter(userName, password);

		// Wait for login page to close
		getWebDriverManager().waitUntilLoginCloses();
		this.getWebDriverManager().waitForAnimation();
		// go to preview page
		homePage.goToPreviewPage(siteId);
		if (this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", siteDropdownElementXPath)
				.isDisplayed())
			if (!(this.getWebDriverManager().waitUntilElementIsPresent("xpath", siteDropdownListElementXPath)
					.getAttribute("class").contains("site-dropdown-open")))
				this.getWebDriverManager()
						.driverWaitUntilElementIsPresentAndDisplayed("xpath", siteDropdownElementXPath)
						.click();
			else
				throw new NoSuchElementException(
						"Site creation process is taking too long time and the element was not found");
	}

	public void prepareTestArea() {
		// Create the parent folder on Home Step1 of Test Case
		this.createFolderOnHome(parentFolderName);

		// Checking if parent folder is present
		getWebDriverManager().waitUntilElementIsDisplayed("xpath", parentFolderLocator);
		Assert.assertTrue(getWebDriverManager().isElementPresentByXpath(parentFolderLocator));

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				parentFolderLocator);
		// creating a new folder on a given parentFolder
		this.createFolderOnAPresentFolder(harnessFolderName, parentFolderLocator);

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				harnessFolderLocator);
		// creating a new folder on a given parentFolder
		this.createFolderOnAPresentFolder(emptyFolderName, harnessFolderLocator);

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				emptyFolderLocator);
		// creating a new folder on a given parentFolder
		this.createFolderOnAPresentFolder(bigTree1FolderName, harnessFolderLocator);

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				bigTree1FolderLocator);
		// creating a new folder on a given parentFolder
		this.createFolderOnAPresentFolder(bigTree2FolderName, harnessFolderLocator);

		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", styleLocator);
		dashboardPage.rightClickCopyContentPage(styleLocator);

		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				bigTree1FolderLocator);
		this.getWebDriverManager().waitForAnimation();
		dashboardPage.rightClickPasteOnAFolder(bigTree1FolderLocator);

		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				entertainmentLocator);
		dashboardPage.rightClickCopyContentPage(entertainmentLocator);

		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				bigTree1FolderLocator);
		this.getWebDriverManager().waitForAnimation();
		dashboardPage.rightClickPasteOnAFolder(bigTree1FolderLocator);

		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", healthLocator);
		dashboardPage.rightClickCopyContentPage(healthLocator);

		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				bigTree1FolderLocator);
		this.getWebDriverManager().waitForAnimation();
		dashboardPage.rightClickPasteOnAFolder(bigTree1FolderLocator);

		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				technologyLocator);
		dashboardPage.rightClickCopyContentPage(technologyLocator);

		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				bigTree1FolderLocator);
		this.getWebDriverManager().waitForAnimation();
		dashboardPage.rightClickPasteOnAFolder(bigTree1FolderLocator);

	}

	public void createNewPageArticleContent() {

		getWebDriverManager().usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {
			// creating random values for URL field and InternalName field
			String randomURL = "newPageURL" + RandomStringUtils.randomAlphabetic(5).toLowerCase();
			String randomInternalName = "newPageInternalName"
					+ RandomStringUtils.randomAlphabetic(5).toLowerCase();

			// Set basics fields of the new content created
			dashboardPage.setBasicFieldsOfNewPageArticleContent(randomURL, randomInternalName,
					"newPageArticlesTitle");

			// Set the title of main content
			this.getWebDriverManager().scrollDown();
			getWebDriverManager().sendText("xpath", createFormArticleMainTitleElementXPath, "MainTitle");

			WebElement categoryDropdown = this.getWebDriverManager()
					.driverWaitUntilElementIsPresentAndDisplayed("xpath", categoryDrowpdownXpath);
			Select select = new Select(categoryDropdown);
			select.selectByValue("style");

			// save and close
			this.getWebDriverManager()
					.driverWaitUntilElementIsPresentAndDisplayed("xpath", createFormSaveAndCloseElement)
					.click();
		});

		this.getWebDriverManager().waitUntilSidebarOpens();

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

	public void editSelectedContent() {

		getWebDriverManager().usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {
			// Typing new text on title text field
			getWebDriverManager().sendText("xpath", createFormTitleElementXPath,
					RandomStringUtils.randomAlphabetic(5).toLowerCase());

			// Save and close button.
			dashboardPage.clickSaveClose();
		});
	}

	public void compareTwoVersionsOfAContentPage() {

		// Switch to the iframe
		getWebDriverManager().getDriver().switchTo().defaultContent();
		getWebDriverManager().getDriver().switchTo().activeElement();

		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().waitUntilPageLoad();

		try {
			this.getWebDriverManager().waitUntilElementIsDisplayed("xpath", actionsHeaderXpath);
		} catch (TimeoutException e) {
			this.getWebDriverManager().takeScreenshot("HistoryDialogNotCompletedRendered");
			logger.warn("History dialog is not completely rendered, and the buttons can't be clicked");
		}

		// Checking the first row version
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", historyFirstItemCheckbBox)
				.click();

		// Checking the second row version
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", historySecondItemCheckbBox)
				.click();

		// click on Compare button
		this.getWebDriverManager().waitForAnimation();
		dashboardPage.clickCompareButton();

		// switching to the compare frame
		this.getWebDriverManager().waitForAnimation();
		getWebDriverManager().usingCrafterDialog("cssSelector", differencesDialogId, () -> {
			this.getWebDriverManager().waitForAnimation();
			// checkin if is present the removed-red-highlight text
			Assert.assertTrue(getWebDriverManager().isElementPresentByXpath(differencesDialogRemovedMarkXpath));

			// checkin if is present the added-green-highlight text
			Assert.assertTrue(getWebDriverManager().isElementPresentByXpath(differencesDialogAddedMarkXpath));

			// click on close button
			dashboardPage.clickCloseButton();
		});

	}

	public void revertLastVersionChanges() {

		// Switch to the iframe
		getWebDriverManager().getDriver().switchTo().activeElement();

		this.getWebDriverManager().scrollDown();

		// Clickin the revert changes option for the initial version
		this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", historyInitialCommitRevertButton)
				.click();

		// Comparing first two versions of the content
		compareTwoVersionsOfAContentPage();

		// Click on close button
		dashboardPage.clickHistoryCloseButton();

		getWebDriverManager().waitUntilSidebarOpens();

		// switch to default content
		getWebDriverManager().getDriver().switchTo().defaultContent();
	}

	public void confirmPublishAction() {
		// Switch to the form
		getWebDriverManager().usingYuiContainer(() -> {

			// Click on Publish button
			dashboardPage.clickApproveAndPublishSubmitButton();
		});

		// switch to default content
		this.getWebDriverManager().waitUntilSidebarOpens();
		this.getWebDriverManager().waitForAnimation();
	}

	public void publishAllPagesOnAFolder(String folderLocator) {
		// getting the entire list of content pages on a folder
		// The xpath bellow is a dynamic xpath according with folder name of the
		// folderLocator
		getWebDriverManager()
				.elementHasChildsByXPath(folderLocator + "/../../../../../div/div/table/tbody/tr/td/span");

		// Switch to the form

		this.getWebDriverManager().waitForAnimation();
		getWebDriverManager().getDriver().switchTo().defaultContent();

		// The xpath bellow is a dynamic xpath according with folder name of the
		// folderLocator
		this.getWebDriverManager().waitForAnimation();
		String firstChild = folderLocator + "/../../../../../div/div[1]/table/tbody/tr/td/span";
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", firstChild);
		dashboardPage.rightClickOnAContentPageByJavascript(firstChild);
		// selecting the Publish option
		getWebDriverManager().usingContextMenu(() -> {
			dashboardPage.clickOnPublishOption();
		}, "Pages");
		// moving to the publish dialog, clicking on Submit and confirm action
		this.confirmPublishAction();
		this.getWebDriverManager().waitUntilPublishMaskedModalCloses();
		this.getWebDriverManager().waitUntilElementHasPublishedIcon(firstChild + "//span/span[2]");
		getWebDriverManager().getDriver().switchTo().defaultContent();

		// The xpath bellow is a dynamic xpath according with folder name of the
		// folderLocator
		this.getWebDriverManager().waitForAnimation();
		String secondChild = folderLocator + "/../../../../../div/div[2]/table/tbody/tr/td/span";
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", secondChild);
		dashboardPage.rightClickOnAContentPageByJavascript(secondChild);
		// selecting the Publish option
		getWebDriverManager().usingContextMenu(() -> {
			dashboardPage.clickOnPublishOption();
		}, "Pages");
		// moving to the publish dialog, clicking on Submit and confirm action
		this.confirmPublishAction();
		this.getWebDriverManager().waitUntilPublishMaskedModalCloses();
		this.getWebDriverManager().waitUntilElementHasPublishedIcon(secondChild + "//span/span[2]");
		getWebDriverManager().getDriver().switchTo().defaultContent();

		// The xpath bellow is a dynamic xpath according with folder name of the
		// folderLocator
		this.getWebDriverManager().waitForAnimation();
		String thirdChild = folderLocator + "/../../../../../div/div[3]/table/tbody/tr/td/span";
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", thirdChild);
		dashboardPage.rightClickOnAContentPageByJavascript(thirdChild);
		// selecting the Publish option
		getWebDriverManager().usingContextMenu(() -> {
			dashboardPage.clickOnPublishOption();
		}, "Pages");
		// moving to the publish dialog, clicking on Submit and confirm action
		this.confirmPublishAction();
		this.getWebDriverManager().waitUntilPublishMaskedModalCloses();
		this.getWebDriverManager().waitUntilElementHasPublishedIcon(thirdChild + "//span/span[2]");
		getWebDriverManager().getDriver().switchTo().defaultContent();

	}

	public void confirmDeleteAction() {
		// Switch to the form
		getWebDriverManager().getDriver().switchTo().activeElement();
		// Click on delete button
		dashboardPage.clickDeleteDeleteSubmitButton();
		getWebDriverManager().getDriver().switchTo().defaultContent();
	}

	public void createMultipleContentPagesOnFolder() {

		// creating multiple content pages
		for (int count = 0; count < 1; count++) {
			// reload page
			this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
					bigTree1FolderLocator);
			this.createPageCategoryLandingPage(bigTree1FolderLocator);
		}
	}

	public void step1() {
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				emptyFolderLocator);
		// Step 1
		this.createPageCategoryLandingPage(emptyFolderLocator);

		// creating multiple content pages on bigtree1
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				bigTree1FolderLocator);
		this.createMultipleContentPagesOnFolder();
	}

	public void step2() {
		// Step2 a)
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				bigTree1FolderLocator);
		dashboardPage.rightClickCopyFolder(bigTree1FolderLocator);

		// Step2 b)
		bigTree1FolderDivOnSelectorXPath = this.parentFolderDivOnTreeSelectorLocator + "/site/website/"
				+ parentFolderName + "/" + harnessFolderName + "/" + bigTree1FolderName + "']";
		dashboardPage.selectAllTreeOnSelector(bigTree1FolderDivOnSelectorXPath);
		dashboardPage.clickCopyButtonOnTreeSelector();

		// Step2 c)
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				bigTree2FolderLocator);
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().waitForAnimation();
		dashboardPage.rightClickPasteOnAFolder(bigTree2FolderLocator);

		bigTree2BigTree1ChildFolderLocator = bigTree2FolderLocator + uiElementsPropertiesManager
				.getSharedUIElementsLocators().getProperty("complexscenarios.crafter3loadtest.childfolder")
				+ this.bigTree1FolderName + "')]";
	}

	public void step3() {
		this.getWebDriverManager().waitForFullExpansionOfTree();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				harnessFolderLocator);
		this.createFolderOnAPresentFolder(myTestFolderName, harnessFolderLocator);

	}

	public void step4() {
		// Step4
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				bigTree1FolderLocator);
		dashboardPage.rightClickCopyFolder(bigTree1FolderLocator);

		dashboardPage.selectAllTreeOnSelector(bigTree1FolderDivOnSelectorXPath);
		dashboardPage.clickCopyButtonOnTreeSelector();

		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				mytestFolderLocator);
		this.getWebDriverManager().waitForAnimation();
		dashboardPage.rightClickPasteOnAFolder(mytestFolderLocator);

		myTestBigTreeChildFolderLocator = mytestFolderLocator + uiElementsPropertiesManager
				.getSharedUIElementsLocators().getProperty("complexscenarios.crafter3loadtest.childfolder")
				+ this.bigTree1FolderName + "')]";
	}

	public void step5() {
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				harnessFolderLocator);
		this.createFolderOnAPresentFolder(anotherTestFolderName, harnessFolderLocator);
	}

	public void step6() {

		// Step6
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				mytestFolderLocator);
		dashboardPage.expandParentFolder(mytestFolderLocator);

		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				myTestBigTreeChildFolderLocator);
		dashboardPage.rightClickCutAFolder(myTestBigTreeChildFolderLocator);

		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				anotherTestFolderLocator);
		this.getWebDriverManager().waitForAnimation();
		dashboardPage.rightClickPasteOnAFolder(anotherTestFolderLocator);

		anotherTestBigTreeChildFolderLocator = anotherTestFolderLocator + uiElementsPropertiesManager
				.getSharedUIElementsLocators().getProperty("complexscenarios.crafter3loadtest.childfolder")
				+ this.bigTree1FolderName + "')]";
	}

	public void step7() {
		// Step7 a)
		this.getWebDriverManager().waitForAnimation();

		dashboardPage.clickHomeTree();

		dashboardPage.clickOnContextualNavigationEditOption();

		// Step7 b)
		this.editSelectedContent();

	}

	public void step8() {
		// Step8
		getWebDriverManager().waitUntilSidebarOpens();

		dashboardPage.clickHomeTree();

		dashboardPage.clickOnContextualNavigationHistoryOption();

		this.getWebDriverManager().waitForAnimation();
	}

	public void step9() {
		// Step9
		this.compareTwoVersionsOfAContentPage();
	}

	public void step10() {
		// Step10
		this.revertLastVersionChanges();
	}

	public void step11() {
		// Step11
		getWebDriverManager().waitUntilSidebarOpens();
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				anotherTestFolderLocator);
		dashboardPage.expandParentFolder(anotherTestFolderLocator);

		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				anotherTestBigTreeChildFolderLocator);
		dashboardPage.expandParentFolder(anotherTestBigTreeChildFolderLocator);

		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				anotherTestBigTreeChildFolderLocator + "/../../../../../div/div[5]/table/tbody/tr/td/span");
		this.publishAllPagesOnAFolder(anotherTestBigTreeChildFolderLocator);

		getWebDriverManager().waitUntilSidebarOpens();
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				bigTree2FolderLocator);
		dashboardPage.expandParentFolder(bigTree2FolderLocator);

		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				bigTree2BigTree1ChildFolderLocator);
		dashboardPage.expandParentFolder(bigTree2BigTree1ChildFolderLocator);

		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				bigTree2BigTree1ChildFolderLocator + "/../../../../../div/div[5]/table/tbody/tr/td/span");
		this.publishAllPagesOnAFolder(bigTree2BigTree1ChildFolderLocator);

	}

	public void step12() {
		// Step12
		// Wait for a while until harness folder is opened after publish actions
		try {
			getWebDriverManager().waitUntilSidebarOpens();
			this.getWebDriverManager().waitForAnimation();
			this.getWebDriverManager().waitUntilFolderOpens("xpath", harnessFolderLocator);
		} catch (TimeoutException e) {
			this.getWebDriverManager().takeScreenshot("FolderIconMissed");
			logger.warn("Something went wrong, the folder is not opened or the icon is missing");
		}
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				mytestFolderLocator);
		dashboardPage.rightClickDeleteAFolder(mytestFolderLocator);
		this.confirmDeleteAction();

		// Wait for a while until harness folder is opened after publish actions
		try {
			getWebDriverManager().waitUntilSidebarOpens();
			this.getWebDriverManager().waitForAnimation();
			this.getWebDriverManager().waitUntilFolderOpens("xpath", harnessFolderLocator);
		} catch (TimeoutException e) {
			this.getWebDriverManager().takeScreenshot("FolderIconMissed");
			logger.warn("Something went wrong, the folder is not opened or the icon is missing");
		}
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				anotherTestFolderLocator);
		dashboardPage.rightClickDeleteAFolder(anotherTestFolderLocator);
		this.confirmDeleteAction();

		// Wait for a while until harness folder is opened after publish actions
		try {
			getWebDriverManager().waitUntilSidebarOpens();
			this.getWebDriverManager().waitForAnimation();
			this.getWebDriverManager().waitUntilFolderOpens("xpath", harnessFolderLocator);
		} catch (TimeoutException e) {
			this.getWebDriverManager().takeScreenshot("FolderIconMissed");
			logger.warn("Something went wrong, the folder is not opened or the icon is missing");
		}
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				bigTree2BigTree1ChildFolderLocator);
		dashboardPage.rightClickDeleteAFolder(bigTree2BigTree1ChildFolderLocator);
		this.confirmDeleteAction();

	}

	public void crafter3LoadTest(String siteId) {
		// login and go to dashboard page, later open the content site (site
		// dropdown panel)
		this.loginAndGoToSiteContentPagesStructure(siteId);

		// create the folders structure according with script
		this.prepareTestArea();

		// reload page
		getWebDriverManager().getDriver().navigate().refresh();

		// Step1
		this.step1();

		// Step2
		this.step2();

		// Step3
		this.step3();

		// Step4
		this.step4();

		// Step5
		this.step5();

		// Step6
		this.step6();

		// go to dashboard
		this.getWebDriverManager().getDriver().navigate().refresh();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", studioLogo).click();

		// Step7
		this.step7();
		this.getWebDriverManager().getDriver().navigate().refresh();

		// Step8
		this.step8();

		// Step9
		this.step9();

		// Step10
		this.step10();

		// Step11
		getWebDriverManager().getDriver().navigate().refresh();
		this.step11();

		// Step12
		getWebDriverManager().getDriver().navigate().refresh();
		this.step12();
	}

	@Parameters({"testId"})
	@Test()
	public void crafter3LoadTestTestUser1(String testId) {
		this.crafter3LoadTest(testId);
	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
