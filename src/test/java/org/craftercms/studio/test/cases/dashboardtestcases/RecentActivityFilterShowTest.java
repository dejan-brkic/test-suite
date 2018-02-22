package org.craftercms.studio.test.cases.dashboardtestcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.cases.StudioBaseTest;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class RecentActivityFilterShowTest extends StudioBaseTest {

	private static final Logger logger = LogManager.getLogger(RecentActivityFilterShowTest.class);

	private String userName;
	private String password;

	private String createFormFrameElementCss;
	private String createFormMainTitleElementXPath;
	private String createFormExpandAll;
	private String createFormSaveAndCloseElement;
	private String homeElementXPath;
	private String myRecentActivityShowInputXPath;
	private String myRecentActivityFirstItemURLXPath;
	private String myRecentActivitySecondItemURLXPath;

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		createFormFrameElementCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformframe");
		createFormSaveAndCloseElement = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.saveandclosebutton");
		createFormExpandAll = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformexpandall");
		createFormMainTitleElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.createformMainTitle");
		homeElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("general.home");
		myRecentActivityShowInputXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.myrecentactivity.showinput");
		myRecentActivityFirstItemURLXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.myrecentactivity.firstelementurl");
		myRecentActivitySecondItemURLXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.myrecentactivity.secondelementurl");

	}

	public void changeBodyToNotRequiredOnEntryContent() {

		previewPage.changeBodyOfEntryContentPageToNotRequired();

	}

	public void createContent() {
		logger.info("Creating first content");

		driverManager.waitUntilPageLoad();
		driverManager.waitUntilSidebarOpens();
		// right click to see the the menu
		dashboardPage.rightClickToSeeMenu();

		// Select Entry Content Type
		dashboardPage.clickEntryCT();

		// Confirm the Content Type selected
		dashboardPage.clickOKButton();

		// Switch to the iframe
		driverManager.usingCrafterForm("cssselector", createFormFrameElementCss, () -> {
			// Set basics fields of the new content created
			dashboardPage.setBasicFieldsOfNewContent("AboutUs", "AboutUs");

			// Set the title of main content
			driverManager.sendText("xpath", createFormMainTitleElementXPath, "MainTitle");

			// click necessary to validate all fields required
			this.driverManager.scrollUp();
			this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", createFormExpandAll).click();

			// save and close
			this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", createFormSaveAndCloseElement)
					.click();
		});
	}

	public void createSecondContent() {
		logger.info("Creating second content");

		driverManager.waitUntilPageLoad();
		driverManager.waitUntilSidebarOpens();
		// right click to see the the menu
		dashboardPage.rightClickToSeeMenu();

		// Select Entry Content Type
		dashboardPage.clickEntryCT();

		// Confirm the Content Type selected

		dashboardPage.clickOKButton();

		// Switch to the iframe
		driverManager.usingCrafterForm("cssselector", createFormFrameElementCss, () -> {
			// Set basics fields of the new content created
			dashboardPage.setBasicFieldsOfNewContent("AboutUs1", "AboutUs1");

			// Set the title of main content
			driverManager.sendText("xpath", createFormMainTitleElementXPath, "MainTitle");

			// click necessary to validate all fields required
			this.driverManager.scrollUp();
			this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", createFormExpandAll).click();

			// save and close

			this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", createFormSaveAndCloseElement)
					.click();
		});
	}

	public void filtersAndAsserts() {

		// clean filter
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", myRecentActivityShowInputXPath).clear();

		// Show only 1 item edited
		driverManager.sendText("xpath", myRecentActivityShowInputXPath, "1");
		driverManager.waitUntilElementIsDisplayed("xpath", myRecentActivityShowInputXPath).sendKeys(Keys.ENTER);
		this.driverManager.waitForAnimation();
		driverManager.waitUntilElementIsDisplayed("xpath", myRecentActivityFirstItemURLXPath);

		// Assert filter 1
		this.driverManager.waitForAnimation();
		String edit1 = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", myRecentActivityFirstItemURLXPath).getText();

		Assert.assertEquals(edit1, "/aboutus1");

		// clean filter
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", myRecentActivityShowInputXPath).clear();

		// Show only 1 item edited
		driverManager.sendText("xpath", myRecentActivityShowInputXPath, "2");
		driverManager.waitUntilElementIsDisplayed("xpath", myRecentActivityShowInputXPath).sendKeys(Keys.ENTER);
		this.driverManager.waitForAnimation();
		driverManager.waitUntilElementIsDisplayed("xpath", myRecentActivitySecondItemURLXPath);

		// Assert filter 1
		this.driverManager.waitForAnimation();
		String edit2 = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", myRecentActivitySecondItemURLXPath).getText();
		Assert.assertEquals(edit2, "/aboutus");
	}

	@Test(priority = 0)
	public void verifyThatTheShowInputWorksProperlyOnRecentActivityWidgetTest() {
		logger.info("Starting test case");

		// login to application
		loginPage.loginToCrafter(userName, password);

		// Wait for login page to close
		driverManager.waitUntilLoginCloses();

		// go to preview page
		homePage.goToPreviewPage();

		// body not requiered
		changeBodyToNotRequiredOnEntryContent();

		dashboardPage.expandPagesTree();

		// create content
		createContent();

		// expand home
		dashboardPage.expandHomeTree();

		// reload page
		driverManager.getDriver().navigate().refresh();

		// create a content with level descriptor content type
		// create another content to use a filter
		this.driverManager.isElementPresentAndClickableByXpath(homeElementXPath);
		createSecondContent();

		// reload page
		driverManager.getDriver().navigate().refresh();

		// filters and asserts
		this.filtersAndAsserts();

	}

}
