package org.craftercms.studio.test.cases.dashboardtestcases;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Action;

import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.cases.BaseTest;

public class EditContentThroughDashboardEditOptionTest extends BaseTest {

	private String userName;
	private String password;
	private String controlsSectionFromSection;
	private String contentFormName;
	private String controlsSectionInputLocator;
	private String contentTypeContainerFormSectionContainerLocator;
	private String adminConsoleXpath;
	private String entryContentTypeBodyXpath;
	private String entryContentTypeBodyCheckCss;
	private String createFormFrameElementCss;
	private String createFormSaveAndCloseElement;
	private String createFormTitle;
	private String siteDropDownXpath;
	private String crafterLogoId;
	private String createFormMainTitleElementXPath;

	private static Logger logger = LogManager.getLogger(EditContentThroughDashboardEditOptionTest.class);

	@BeforeMethod
	public void beforeTest() {

		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		controlsSectionFromSection = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.controlssection");
		contentFormName = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.contentformname");
		controlsSectionInputLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.controlsinput");
		contentTypeContainerFormSectionContainerLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.contenttypecontainerformsectioncontainer");
		adminConsoleXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.adminconsole");
		entryContentTypeBodyXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.entrycontenttype.body");
		entryContentTypeBodyCheckCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.entrycontenttype.bodyrequiredcheck");
		createFormFrameElementCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformframe");
		createFormSaveAndCloseElement = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.saveandclosebutton");
		createFormTitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.createformfiletitle");
		siteDropDownXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sitedropdown");
		crafterLogoId = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("general.studiologo");
		createFormMainTitleElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.createformTitle");
	}

	public void dragAndDrop() {

		// go to admin console page
		previewPage.goToAdminConsolePage();

		// select content types
		siteConfigPage.selectContentTypeOption();

		// open content types
		siteConfigPage.clickExistingTypeOption();

		// Confirm the content type selected
		siteConfigPage.confirmContentTypeSelected();

		// Drag and drop Form Section
		WebElement From = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				controlsSectionFromSection);

		WebElement To = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", contentFormName);

		Actions builder = new Actions(driverManager.getDriver());

		Action dragAndDrop = builder.clickAndHold(From)

				.moveToElement(To)

				.release(To)

				.build();

		dragAndDrop.perform();

		WebElement FromInput = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				controlsSectionInputLocator);

		WebElement ToDefaultSection = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				contentTypeContainerFormSectionContainerLocator);

		Action dragAndDropInput = builder.clickAndHold(FromInput)

				.moveToElement(ToDefaultSection)

				.release(ToDefaultSection)

				.build();

		dragAndDropInput.perform();

		// Complete the input fields basics

		siteConfigPage.completeControlFieldsBasics("TestTitle", "TestICEGroup", "TestDescription", "TestDefaultValue");

		siteConfigPage.saveDragAndDropProcess();

	}

	public void bodyNotRequiered() {
		// go to admin console page
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", adminConsoleXpath).click();

		// select content types
		siteConfigPage.selectContentTypeOption();

		// open content types
		siteConfigPage.clickExistingTypeOption();

		// Confirm the content type selected

		siteConfigPage.confirmContentTypeSelected();

		// wait for element is clickeable
		driverManager.getDriver().switchTo().defaultContent();

		// select main content
		this.driverManager.waitUntilSiteConfigMaskedModalCloses();
		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", entryContentTypeBodyXpath).click();

		// Mark Body not required
		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", entryContentTypeBodyCheckCss).click();
		// save
		siteConfigPage.saveDragAndDropProcess();
	}

	public void createNewContent() {

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

	}

	public void editFormCreated() {

		// Click on edit option of recent activity section

		dashboardPage.clickEditOptionOfRecentActivitySection();

		this.driverManager.waitForAnimation();

		// Switch to the iframe
		driverManager.usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {

			// Expand default section
			myRecentActivityFramePage1.expandDefaultSection();

			// Clealing title text field

			this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", createFormTitle).clear();

			// Typing new text on title text field

			this.driverManager.sendText("xpath", createFormTitle, "TestQA");

			// save and close
			logger.info("Click on Save and close button");

			this.driverManager
					.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", createFormSaveAndCloseElement)
					.click();

		});

		// Switch back to the dashboard page
		driverManager.getDriver().switchTo().defaultContent();

	}

	public void clickOnEditOptionOfRecentActivitySection() {

		// Click on edit option of recent activity section

		dashboardPage.clickEditOptionOfRecentActivitySection();

		this.driverManager.waitForAnimation();
		// Switch to the iframe

		// Switch to the iframe
		driverManager.usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {

			// Expand default section
			myRecentActivityFramePage1.expandDefaultSection();
			// Assert validation
			this.driverManager.scrollDown();

			String textTitle = this.driverManager

					.driverWaitUntilElementIsPresentAndDisplayed("xpath", createFormTitle)

					.getAttribute("value");

			Assert.assertEquals(textTitle, "TestQA", "Content is not eddited properly");

			// save and close
			logger.info("Click on Save and close button");

			this.driverManager
					.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", createFormSaveAndCloseElement)
					.click();

		});
	}

	public void goToDashboard() {
		// Go to dashboard page
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", crafterLogoId).click();
	}

	@Test(priority = 0)
	public void verifyTheEditionOfAPageUsingEditLinkOfRecentActivityTest() {

		// login to application

		loginPage.loginToCrafter(userName, password);

		// Wait for login page to close
		driverManager.waitUntilLoginCloses();

		// go to preview page
		homePage.goToPreviewPage();

		// Show site content panel

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", siteDropDownXpath).click();

		this.driverManager.waitUntilSidebarOpens();

		// Select the content type and drag and drop
		dragAndDrop();

		driverManager.getDriver().switchTo().defaultContent();
		this.driverManager.getDriver().navigate().refresh();
		this.driverManager.waitForAnimation();
		
		goToDashboard();

		bodyNotRequiered();

		driverManager.getDriver().switchTo().defaultContent();
		this.driverManager.getDriver().navigate().refresh();
		this.driverManager.waitForAnimation();
		// go to dashboard
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", crafterLogoId).click();

		dashboardPage.expandPagesTree();

		this.driverManager.waitUntilPageLoad();
		this.driverManager.waitUntilSidebarOpens();

		// create a new content
		createNewContent();

		driverManager.getDriver().navigate().refresh();

		// edit the form created
		editFormCreated();

		// reload page

		driverManager.getDriver().navigate().refresh();

		// click on edit option of recently activity section

		clickOnEditOptionOfRecentActivitySection();

	}

}
