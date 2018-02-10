package org.craftercms.studio.test.cases.contextualnavigationtestcases;

import org.testng.Assert;

import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;

import org.craftercms.studio.test.cases.BaseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

/**
 * 
 * 
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 * 
 * 
 */

public class PublishingSiteTest extends BaseTest {
	private String userName;
	private String password;
	private String createFormFrameElementCss;
	private String createFormMainTitleElementXPath;
	private String createFormSaveAndCloseElement;
	private String testingContentItem;
	private String topNavStatusIcon;
	private String homeXpath;

	private static Logger logger = LogManager.getLogger(PublishingSiteTest.class);

	@BeforeMethod
	public void beforeTest() {

		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		createFormFrameElementCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.createformframe");
		createFormMainTitleElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.createformTitle");
		createFormSaveAndCloseElement = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.saveandclosebutton");
		testingContentItem = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.testingcontentitem");
		topNavStatusIcon = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.statustopbaricon");
		homeXpath = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("general.home");

	}

	public void changeBodyToNotRequiredOnEntryContent() {
		previewPage.changeBodyOfEntryContentPageToNotRequired();
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

	public void approveAndPublish() {

		// approve and publish

		previewPage.clickOnApprovePublish();

		// submit

		previewPage.clickOnSubmitButtonOfApprovePublish();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", homeXpath);

	}

	@Test(priority = 0)

	public void publishingSite() {

		// login to application

		loginPage.loginToCrafter(userName, password);

		// Wait for login page to closes

		driverManager.waitUntilLoginCloses();

		// goto preview page

		homePage.goToPreviewPage();

		// select the content type to the test

		changeBodyToNotRequiredOnEntryContent();

		// Switch to the form

		driverManager.getDriver().navigate().refresh();

		driverManager.getDriver().switchTo().defaultContent();

		// expand pages folder

		dashboardPage.expandPagesTree();

		// expand home content
		this.driverManager.waitUntilPageLoad();
		this.driverManager.waitUntilSidebarOpens();
	

		dashboardPage.expandHomeTree();

		// create a new content

		createNewContent();

		// reload page
		driverManager.getDriver().navigate().refresh();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", testingContentItem).click();

		// approve and publish
		approveAndPublish();

		this.driverManager.waitForAnimation();

		// expand pages folder
		dashboardPage.expandPagesTree();

		for (int i = 0; i < 3; i++) {
			try {
				this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", testingContentItem)
						.click();
				this.driverManager.waitUntilAttributeContains("xpath", topNavStatusIcon, "class", "undefined live");
				break;
			} catch (TimeoutException e) {
				this.driverManager.takeScreenshot("PageNotPublishedOnTopNavBar");
				logger.warn("Content page is not published yet, checking again if it has published icon on top bar");
				driverManager.getDriver().navigate().refresh();
			}
		}

		String elementClassValue = this.driverManager.getDriver().findElement(By.xpath(topNavStatusIcon))
				.getAttribute("class");
		Assert.assertTrue(elementClassValue.contains("undefined live"));

	}

}
