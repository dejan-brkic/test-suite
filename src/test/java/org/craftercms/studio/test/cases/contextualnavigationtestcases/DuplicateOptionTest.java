package org.craftercms.studio.test.cases.contextualnavigationtestcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.cases.StudioBaseTest;

/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class DuplicateOptionTest extends StudioBaseTest {

	private String userName;
	private String password;

	private String createFormFrameElementCss;
	private String createFormSaveAndCloseElementId;
	private String createFormMainTitleElementXPath;
	private String studioLogo;
	private String testItemXpath;
	private String duplicateButtonXpath;

	private String copyTestItemXpath;
	private static Logger logger = LogManager.getLogger(DuplicateOptionTest.class);

	@BeforeMethod
	public void beforeTest() {

		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");

		createFormFrameElementCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformframe");
		createFormSaveAndCloseElementId = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.saveandclosebutton");
		createFormMainTitleElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.createformTitle");
		studioLogo = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("general.studiologo");
		testItemXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.testingcontentitem");
		duplicateButtonXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.duplicatedialog.duplicate");
		copyTestItemXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sitecontent.copytestitem");
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
					.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", createFormSaveAndCloseElementId)
					.click();

		});

	}

	public void duplicateContentCreated() {
		dashboardPage.clickOnDuplicateOption();

		// click on duplicate in the popup
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", duplicateButtonXpath)
				.click();

		this.driverManager.waitForAnimation();

		// Switch to the iframe
		driverManager.usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {
			// edit internal name
			dashboardPage.editInternalName("COPY");
		});

		// Switch back to the dashboard page
		driverManager.getDriver().switchTo().defaultContent();

		// reload page
		driverManager.getDriver().navigate().refresh();
	}

	public void goToPreviewPage() {
		// go to preview page
		homePage.goToPreviewPage();
	}

	@Test(priority = 0)
	public void duplicatePageUsingContextualNavigationDuplicateOptionOption() {

		// login to application
		loginPage.loginToCrafter(userName, password);

		// Wait for login page to closes
		driverManager.waitUntilLoginCloses();

		// goto preview page
		goToPreviewPage();

		// select the content type to the test
		changeBodyToNotRequiredOnEntryContent();

		// expand pages folder
		dashboardPage.expandPagesTree();

		// expand home content
		this.driverManager.waitUntilPageLoad();
		this.driverManager.waitUntilSidebarOpens();
		this.driverManager.waitForAnimation();
		dashboardPage.expandHomeTree();

		// create a new content
		createNewContent();

		// reload page
		driverManager.getDriver().navigate().refresh();

		// Select the content to duplicate.
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", testItemXpath).click();

		// Duplicate content created
		duplicateContentCreated();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", studioLogo).click();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", copyTestItemXpath).click();
		Assert.assertTrue(driverManager.isElementPresentByXpath(copyTestItemXpath),
				"Duplicated Option is not displayed");

	}

}
