package org.craftercms.studio.test.cases.contenttestcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.cases.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class CutPasteContentTest extends BaseTest {

	private String userName;
	private String password;
	private String siteDropdownElementXPath;
	private String adminConsoleXpath;
	private String entryContentTypeBodyXpath;
	private String entryContentTypeBodyCheckCss;
	private String studioLogo;
	private String createFormFrameElementCss;
	private String createFormSaveAndCloseElement;
	private String createFormMainTitleElementXPath;
	private String newFolderCreated;
	private String newFolderSpanXpath;
	private String testingItemURLXpath;
	private static Logger logger = LogManager.getLogger(CutPasteContentTest.class);

	@BeforeMethod
	public void beforeTest() {

		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		adminConsoleXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.adminconsole");
		entryContentTypeBodyXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.entrycontenttype.body");
		entryContentTypeBodyCheckCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.entrycontenttype.bodyrequiredcheck");
		studioLogo = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("general.studiologo");
		createFormFrameElementCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformframe");
		createFormSaveAndCloseElement = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.saveandclosebutton");
		createFormMainTitleElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.createformTitle");
		testingItemURLXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.cutpaste.pastedelement");
		newFolderCreated = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.add_new_folder");
		newFolderSpanXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sitecontent.newfolder");

	}

	@Test(priority = 0)
	public void cutAndPasteContentUsingContextualClickOptionsTest() {

		// login to application
		loginPage.loginToCrafter(userName, password);

		// Wait for loging page to close
		driverManager.waitUntilLoginCloses();

		// go to dashboard page
		homePage.goToDashboardPage();

		driverManager.getDriver().navigate().refresh();

		// Show site content panel
		logger.debug("Click on Site Dropdown");
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", siteDropdownElementXPath).click();

		// go to admin console page
		logger.debug("Click on Admin Console");
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", adminConsoleXpath).click();

		// select content types
		siteConfigPage.selectContentTypeOption();

		// open content types
		siteConfigPage.clickExistingTypeOption();

		// Confirm the content type selected
		siteConfigPage.confirmContentTypeSelected();

		// select main content
		logger.debug("Select Main content");
		this.driverManager.waitUntilSiteConfigMaskedModalCloses();
		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", entryContentTypeBodyXpath).click();

		// Body not required
		logger.debug("Disable RTE for the selected content");
		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", entryContentTypeBodyCheckCss)
				.click();

		// save
		siteConfigPage.saveDragAndDropProcess();

		// Switch to the form
		driverManager.getDriver().navigate().refresh();
		driverManager.getDriver().switchTo().defaultContent();

		logger.debug("Return to preview page.");
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", studioLogo).click();

		// expand pages folder
		dashboardPage.expandPagesTree();

		// right click to see the the menu

		dashboardPage.rightClickToSeeMenu();

		// Select Entry Content Type

		dashboardPage.clickEntryCT();

		// Confirm the Content Type selected
		dashboardPage.clickOKButton();

		// Switch to the iframe
		driverManager.usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {

			// Set basics fields of the new content created
			logger.info("Set the fields of the new content");
			dashboardPage.setBasicFieldsOfNewContent("Test1", "Testing1");

			// Set the title of main content

			this.driverManager.sendText("xpath", createFormMainTitleElementXPath, "MainTitle");

			// save and close
			logger.info("Click on Save and close button");
			this.driverManager

					.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", createFormSaveAndCloseElement)
					.click();

		});

		// reload page
		driverManager.getDriver().navigate().refresh();

		// expand home content
		dashboardPage.expandHomeTree();

		// right click to see the menu
		dashboardPage.rightClickToFolderOnHome();

		// Set the name of the folder
		dashboardPage.setFolderName("addnewfolder");

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", newFolderCreated);

		// Right click and cut content.
		dashboardPage.rightClickToCutOption();

		// Right click and paste content.

		dashboardPage.rightClickToPasteIntoFolder();

		// reload page
		driverManager.getDriver().navigate().refresh();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", newFolderSpanXpath).click();

		// Assert of the content copied
		this.driverManager.waitWhileElementIsDisplayedAndClickableByXpath(testingItemURLXpath);
		String contentCopied = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", testingItemURLXpath).getText();
		Assert.assertEquals(contentCopied, "Testing1", "NNew folder does not contain the cu/paste content");

	}

}
