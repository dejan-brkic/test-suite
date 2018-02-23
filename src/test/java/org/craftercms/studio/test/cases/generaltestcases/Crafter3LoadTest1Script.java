/**
 * 
 */
package org.craftercms.studio.test.cases.generaltestcases;

import org.craftercms.studio.test.cases.StudioBaseTest;
import org.craftercms.studio.test.pages.SiteConfigPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

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
	private static Logger logger = LogManager.getLogger(SiteConfigPage.class);

	@BeforeMethod
	public void beforeTest() {
		this.parentFolderName = "tester-" + RandomStringUtils.randomAlphabetic(5).toLowerCase();
		this.harnessFolderName = "harness";
		this.emptyFolderName = "empty-folder";
		this.bigTree1FolderName = "big-tree1";
		this.bigTree2FolderName = "big-tree2";
		this.myTestFolderName = "mytest";
		this.anotherTestFolderName = "anothertest";

		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");

		this.parentFolderLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.crafter3loadtest.parentfolder") + this.parentFolderName + "')]";
		harnessFolderLocator = this.parentFolderLocator + uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.crafter3loadtest.childfolder") + this.harnessFolderName + "')]";
		emptyFolderLocator = harnessFolderLocator + uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.crafter3loadtest.childfolder") + this.emptyFolderName + "')]";
		bigTree1FolderLocator = harnessFolderLocator + uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.crafter3loadtest.childfolder") + this.bigTree1FolderName + "')]";
		bigTree2FolderLocator = harnessFolderLocator + uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.crafter3loadtest.childfolder") + this.bigTree2FolderName + "')]";
		mytestFolderLocator = harnessFolderLocator + uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.crafter3loadtest.childfolder") + this.myTestFolderName + "')]";
		anotherTestFolderLocator = harnessFolderLocator + uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.crafter3loadtest.childfolder") + this.anotherTestFolderName + "')]";
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
		studioLogo = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("general.studiologo");
		actionsHeaderXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.historydialogactionsheader");
		siteDropdownListElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdownlielement");

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

	public void loginAndGoToSiteContentPagesStructure() {
		// login to application
		loginPage.loginToCrafter(userName, password);

		// Wait for login page to close
		driverManager.waitUntilLoginCloses();
		this.driverManager.waitForAnimation();
		// go to preview page
		homePage.goToPreviewPage();
		if (this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", siteDropdownElementXPath)
				.isDisplayed())
			if (!(this.driverManager.waitUntilElementIsPresent("xpath", siteDropdownListElementXPath)
					.getAttribute("class").contains("site-dropdown-open")))
				this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", siteDropdownElementXPath)
						.click();
			else
				throw new NoSuchElementException(
						"Site creation process is taking too long time and the element was not found");
	}

	public void prepareTestArea() {
		// Create the parent folder on Home Step1 of Test Case
		this.createFolderOnHome(parentFolderName);

		// Checking if parent folder is present
		driverManager.waitUntilElementIsDisplayed("xpath", parentFolderLocator);
		Assert.assertTrue(driverManager.isElementPresentByXpath(parentFolderLocator));

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", parentFolderLocator);
		// creating a new folder on a given parentFolder
		this.createFolderOnAPresentFolder(harnessFolderName, parentFolderLocator);

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", harnessFolderLocator);
		// creating a new folder on a given parentFolder
		this.createFolderOnAPresentFolder(emptyFolderName, harnessFolderLocator);

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", emptyFolderLocator);
		// creating a new folder on a given parentFolder
		this.createFolderOnAPresentFolder(bigTree1FolderName, harnessFolderLocator);

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", bigTree1FolderLocator);
		// creating a new folder on a given parentFolder
		this.createFolderOnAPresentFolder(bigTree2FolderName, harnessFolderLocator);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", styleLocator);
		dashboardPage.rightClickCopyContentPage(styleLocator);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", bigTree1FolderLocator);
		dashboardPage.rightClickPasteOnAFolder(bigTree1FolderLocator);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", entertainmentLocator);
		dashboardPage.rightClickCopyContentPage(entertainmentLocator);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", bigTree1FolderLocator);
		dashboardPage.rightClickPasteOnAFolder(bigTree1FolderLocator);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", healthLocator);
		dashboardPage.rightClickCopyContentPage(healthLocator);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", bigTree1FolderLocator);
		dashboardPage.rightClickPasteOnAFolder(bigTree1FolderLocator);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", technologyLocator);
		dashboardPage.rightClickCopyContentPage(technologyLocator);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", bigTree1FolderLocator);
		dashboardPage.rightClickPasteOnAFolder(bigTree1FolderLocator);

	}

	public void createNewPageArticleContent() {

		driverManager.usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {
			// creating random values for URL field and InternalName field
			String randomURL = "newPageURL" + RandomStringUtils.randomAlphabetic(5).toLowerCase();
			String randomInternalName = "newPageInternalName" + RandomStringUtils.randomAlphabetic(5).toLowerCase();

			// Set basics fields of the new content created
			dashboardPage.setBasicFieldsOfNewPageArticleContent(randomURL, randomInternalName, "newPageArticlesTitle");

			// Set the title of main content
			this.driverManager.scrollDown();
			driverManager.sendText("xpath", createFormArticleMainTitleElementXPath, "MainTitle");

			// save and close

			this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", createFormSaveAndCloseElement)
					.click();
		});

		this.driverManager.waitUntilSidebarOpens();

	}

	public void createPageCategoryLandingPage(String folderWebElementLocator) {
		// right clicking and clikc on create New Content option
		dashboardPage.rightClickCreatePageOnAPresentFolder(folderWebElementLocator);
		// selecting Page Category Landing Page
		dashboardPage.selectPageArticleContentType();
		// click on the Ok button to confirm the select content type above
		dashboardPage.clickOKButton();
		// creating new Page Article into the empty folder
		driverManager.getDriver().switchTo().defaultContent();
		this.createNewPageArticleContent();
	}

	public void editSelectedContent() {

		driverManager.usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {
			// Typing new text on title text field
			driverManager.sendText("xpath", createFormTitleElementXPath,
					RandomStringUtils.randomAlphabetic(5).toLowerCase());

			// Save and close button.
			dashboardPage.clickSaveClose();
		});
	}

	public void compareTwoVersionsOfAContentPage() {

		// Switch to the iframe
		driverManager.getDriver().switchTo().defaultContent();
		driverManager.getDriver().switchTo().activeElement();

		this.driverManager.waitForAnimation();
		this.driverManager.waitUntilPageLoad();

		try {
			this.driverManager.waitUntilElementIsDisplayed("xpath", actionsHeaderXpath);
		} catch (TimeoutException e) {
			this.driverManager.takeScreenshot("HistoryDialogNotCompletedRendered");
			logger.warn("History dialog is not completely rendered, and the buttons can't be clicked");
		}

		// Checking the first row version
		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", historyFirstItemCheckbBox)
				.click();

		// Checking the second row version
		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", historySecondItemCheckbBox)
				.click();

		// click on Compare button
		this.driverManager.waitForAnimation();
		dashboardPage.clickCompareButton();

		// switching to the compare frame
		this.driverManager.waitForAnimation();
		driverManager.usingCrafterDialog("cssSelector", differencesDialogId, () -> {
			this.driverManager.waitForAnimation();
			// checkin if is present the removed-red-highlight text
			Assert.assertTrue(driverManager.isElementPresentByXpath(differencesDialogRemovedMarkXpath));

			// checkin if is present the added-green-highlight text
			Assert.assertTrue(driverManager.isElementPresentByXpath(differencesDialogAddedMarkXpath));

			// click on close button
			dashboardPage.clickCloseButton();
		});

	}

	public void revertLastVersionChanges() {

		// Switch to the iframe
		driverManager.getDriver().switchTo().activeElement();

		this.driverManager.scrollDown();

		// Clickin the revert changes option for the initial version
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", historyInitialCommitRevertButton)
				.click();

		// Comparing first two versions of the content
		compareTwoVersionsOfAContentPage();

		// Click on close button
		dashboardPage.clickHistoryCloseButton();

		driverManager.waitUntilSidebarOpens();

		// switch to default content
		driverManager.getDriver().switchTo().defaultContent();
	}

	public void confirmPublishAction() {
		// Switch to the form
		driverManager.usingYuiContainer(() -> {

			// Click on Publish button
			dashboardPage.clickApproveAndPublishSubmitButton();
		});

		// switch to default content
		this.driverManager.waitUntilSidebarOpens();
		this.driverManager.waitForAnimation();
	}

	public void publishAllPagesOnAFolder(String folderLocator) {
		// getting the entire list of content pages on a folder
		// The xpath bellow is a dynamic xpath according with folder name of the
		// folderLocator
		driverManager.elementHasChildsByXPath(folderLocator + "/../../../../../div/div/table/tbody/tr/td/span");

		// Switch to the form
		// driverManager.getDriver().navigate().refresh();
		this.driverManager.waitForAnimation();
		driverManager.getDriver().switchTo().defaultContent();

		// The xpath bellow is a dynamic xpath according with folder name of the
		// folderLocator
		this.driverManager.waitForAnimation();
		String firstChild = folderLocator + "/../../../../../div/div[1]/table/tbody/tr/td/span";
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", firstChild);
		dashboardPage.rightClickOnAContentPageByJavascript(firstChild);
		// selecting the Publish option
		driverManager.usingContextMenu(() -> {
			dashboardPage.clickOnPublishOption();
		});
		// moving to the publish dialog, clicking on Submit and confirm action
		this.confirmPublishAction();
		this.driverManager.waitUntilPublishMaskedModalCloses();
		this.driverManager.waitUntilElementHasPublishedIcon(firstChild + "//span/span[2]");
		driverManager.getDriver().switchTo().defaultContent();

		// The xpath bellow is a dynamic xpath according with folder name of the
		// folderLocator
		this.driverManager.waitForAnimation();
		String secondChild = folderLocator + "/../../../../../div/div[2]/table/tbody/tr/td/span";
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", secondChild);
		dashboardPage.rightClickOnAContentPageByJavascript(secondChild);
		// selecting the Publish option
		driverManager.usingContextMenu(() -> {
			dashboardPage.clickOnPublishOption();
		});
		// moving to the publish dialog, clicking on Submit and confirm action
		this.confirmPublishAction();
		this.driverManager.waitUntilPublishMaskedModalCloses();
		this.driverManager.waitUntilElementHasPublishedIcon(secondChild + "//span/span[2]");
		driverManager.getDriver().switchTo().defaultContent();

		// The xpath bellow is a dynamic xpath according with folder name of the
		// folderLocator
		this.driverManager.waitForAnimation();
		String thirdChild = folderLocator + "/../../../../../div/div[3]/table/tbody/tr/td/span";
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", thirdChild);
		dashboardPage.rightClickOnAContentPageByJavascript(thirdChild);
		// selecting the Publish option
		driverManager.usingContextMenu(() -> {
			dashboardPage.clickOnPublishOption();
		});
		// moving to the publish dialog, clicking on Submit and confirm action
		this.confirmPublishAction();
		this.driverManager.waitUntilPublishMaskedModalCloses();
		this.driverManager.waitUntilElementHasPublishedIcon(thirdChild + "//span/span[2]");
		driverManager.getDriver().switchTo().defaultContent();

	}

	public void confirmDeleteAction() {
		// Switch to the form
		driverManager.getDriver().switchTo().activeElement();
		// Click on delete button
		dashboardPage.clickDeleteDeleteSubmitButton();
		driverManager.getDriver().switchTo().defaultContent();
	}

	public void createMultipleContentPagesOnFolder() {

		// creating multiple content pages
		for (int count = 0; count < 1; count++) {
			// reload page
			driverManager.getDriver().navigate().refresh();
			this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", bigTree1FolderLocator);
			this.createPageCategoryLandingPage(bigTree1FolderLocator);
		}
	}

	public void step1() {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", emptyFolderLocator);
		// Step 1
		this.createPageCategoryLandingPage(emptyFolderLocator);

		// creating multiple content pages on bigtree1
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", bigTree1FolderLocator);
		this.createMultipleContentPagesOnFolder();
	}

	public void step2() {
		// Step2 a)
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", bigTree1FolderLocator);
		dashboardPage.rightClickCopyFolder(bigTree1FolderLocator);

		// Step2 b)
		bigTree1FolderDivOnSelectorXPath = this.parentFolderDivOnTreeSelectorLocator + "/site/website/"
				+ parentFolderName + "/" + harnessFolderName + "/" + bigTree1FolderName + "']";
		dashboardPage.selectAllTreeOnSelector(bigTree1FolderDivOnSelectorXPath);
		dashboardPage.clickCopyButtonOnTreeSelector();

		// Step2 c)
		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", bigTree2FolderLocator);
		this.driverManager.waitForAnimation();
		dashboardPage.rightClickPasteOnAFolder(bigTree2FolderLocator);

		bigTree2BigTree1ChildFolderLocator = bigTree2FolderLocator + uiElementsPropertiesManager
				.getSharedUIElementsLocators().getProperty("complexscenarios.crafter3loadtest.childfolder")
				+ this.bigTree1FolderName + "')]";
	}

	public void step3() {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", harnessFolderLocator);
		this.createFolderOnAPresentFolder(myTestFolderName, harnessFolderLocator);

	}

	public void step4() {
		// Step4
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", bigTree1FolderLocator);
		dashboardPage.rightClickCopyFolder(bigTree1FolderLocator);

		dashboardPage.selectAllTreeOnSelector(bigTree1FolderDivOnSelectorXPath);
		dashboardPage.clickCopyButtonOnTreeSelector();

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", mytestFolderLocator);
		this.driverManager.waitForAnimation();
		dashboardPage.rightClickPasteOnAFolder(mytestFolderLocator);

		myTestBigTreeChildFolderLocator = mytestFolderLocator + uiElementsPropertiesManager
				.getSharedUIElementsLocators().getProperty("complexscenarios.crafter3loadtest.childfolder")
				+ this.bigTree1FolderName + "')]";
	}

	public void step5() {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", harnessFolderLocator);
		this.createFolderOnAPresentFolder(anotherTestFolderName, harnessFolderLocator);
	}

	public void step6() {

		// Step6
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", mytestFolderLocator);
		dashboardPage.expandParentFolder(mytestFolderLocator);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				myTestBigTreeChildFolderLocator);
		dashboardPage.rightClickCutAFolder(myTestBigTreeChildFolderLocator);

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", anotherTestFolderLocator);
		dashboardPage.rightClickPasteOnAFolder(anotherTestFolderLocator);

		anotherTestBigTreeChildFolderLocator = anotherTestFolderLocator + uiElementsPropertiesManager
				.getSharedUIElementsLocators().getProperty("complexscenarios.crafter3loadtest.childfolder")
				+ this.bigTree1FolderName + "')]";
	}

	public void step7() {
		// Step7 a)
		driverManager.waitUntilSidebarOpens();

		dashboardPage.clickHomeTree();

		dashboardPage.clickOnContextualNavigationEditOption();

		// Step7 b)
		this.editSelectedContent();

	}

	public void step8() {
		// Step8
		driverManager.waitUntilSidebarOpens();

		dashboardPage.clickHomeTree();

		dashboardPage.clickOnContextualNavigationHistoryOption();

		this.driverManager.waitForAnimation();
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
		driverManager.waitUntilSidebarOpens();
		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", anotherTestFolderLocator);
		dashboardPage.expandParentFolder(anotherTestFolderLocator);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				anotherTestBigTreeChildFolderLocator);
		dashboardPage.expandParentFolder(anotherTestBigTreeChildFolderLocator);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				anotherTestBigTreeChildFolderLocator + "/../../../../../div/div[5]/table/tbody/tr/td/span");
		this.publishAllPagesOnAFolder(anotherTestBigTreeChildFolderLocator);

		driverManager.waitUntilSidebarOpens();
		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", bigTree2FolderLocator);
		dashboardPage.expandParentFolder(bigTree2FolderLocator);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				bigTree2BigTree1ChildFolderLocator);
		dashboardPage.expandParentFolder(bigTree2BigTree1ChildFolderLocator);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				bigTree2BigTree1ChildFolderLocator + "/../../../../../div/div[5]/table/tbody/tr/td/span");
		this.publishAllPagesOnAFolder(bigTree2BigTree1ChildFolderLocator);

	}

	public void step12() {
		// Step12
		// Wait for a while until harness folder is opened after publish actions
		try {
			driverManager.waitUntilSidebarOpens();
			this.driverManager.waitForAnimation();
			this.driverManager.waitUntilFolderOpens("xpath", harnessFolderLocator);
		} catch (TimeoutException e) {
			this.driverManager.takeScreenshot("FolderIconMissed");
			logger.warn("Something went wrong, the folder is not opened or the icon is missing");
		}
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", mytestFolderLocator);
		dashboardPage.rightClickDeleteAFolder(mytestFolderLocator);
		this.confirmDeleteAction();

		// Wait for a while until harness folder is opened after publish actions
		try {
			driverManager.waitUntilSidebarOpens();
			this.driverManager.waitForAnimation();
			this.driverManager.waitUntilFolderOpens("xpath", harnessFolderLocator);
		} catch (TimeoutException e) {
			this.driverManager.takeScreenshot("FolderIconMissed");
			logger.warn("Something went wrong, the folder is not opened or the icon is missing");
		}
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", anotherTestFolderLocator);
		dashboardPage.rightClickDeleteAFolder(anotherTestFolderLocator);
		this.confirmDeleteAction();

		// Wait for a while until harness folder is opened after publish actions
		try {
			driverManager.waitUntilSidebarOpens();
			this.driverManager.waitForAnimation();
			this.driverManager.waitUntilFolderOpens("xpath", harnessFolderLocator);
		} catch (TimeoutException e) {
			this.driverManager.takeScreenshot("FolderIconMissed");
			logger.warn("Something went wrong, the folder is not opened or the icon is missing");
		}
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				bigTree2BigTree1ChildFolderLocator);
		dashboardPage.rightClickDeleteAFolder(bigTree2BigTree1ChildFolderLocator);
		this.confirmDeleteAction();

	}

	public void crafter3LoadTest() {
		// login and go to dashboard page, later open the content site (site
		// dropdown panel)
		this.loginAndGoToSiteContentPagesStructure();

		// expand pages folder
		dashboardPage.expandPagesTree();

		// create the folders structure according with script
		this.prepareTestArea();

		// reload page
		driverManager.getDriver().navigate().refresh();

		// Step1
		this.step1();

		driverManager.getDriver().navigate().refresh();

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
		this.driverManager.getDriver().navigate().refresh();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", studioLogo).click();

		// Step7
		this.step7();
		this.driverManager.getDriver().navigate().refresh();

		// Step8
		this.step8();

		// Step9
		this.step9();

		// Step10
		this.step10();

		// Step11
		driverManager.getDriver().navigate().refresh();
		this.step11();

		// Step12
		driverManager.getDriver().navigate().refresh();
		this.step12();
	}

	@Test(priority = 0, sequential = true)
	public void crafter3LoadTestTestUser1() {
		this.crafter3LoadTest();
	}

}
