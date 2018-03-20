package org.craftercms.studio.test.cases.contenttestcases;

import org.craftercms.studio.test.cases.StudioBaseTest;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.BeforeMethod;
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

	@BeforeMethod
	public void beforeTest() {
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
	}

	@Test(priority = 0)
	public void addNewContentAndPublishContentTest() {
		// dropdown panel)
		this.loginAndGoToSiteContentPagesStructure();

		// expand pages folder
		dashboardPage.expandPagesTree();

		// Step2
		this.createContentAndPublishIt();

	}

	public void createContentAndPublishIt() {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", homeContent);
		// Step 1
		this.createPageCategoryLandingPage(homeContent);

		driverManager.getDriver().navigate().refresh();
		this.driverManager.waitForAnimation();
		this.driverManager.waitUntilSidebarOpens();

		dashboardPage.expandHomeTree();

		this.publishElement(createdContentXPath);
		this.driverManager.waitUntilElementHasPublishedIcon(createdContentXPath);
	}

	public void publishElement(String elementLocator) {

		dashboardPage.rightClickOnAContentPage(elementLocator);
		// selecting the Publish option
		driverManager.usingContextMenu(() -> {
			dashboardPage.clickOnPublishOption();
		},"Pages");
		// moving to the publish dialog, clicking on Submit and confirm action
		this.confirmPublishAction();
		this.driverManager.waitUntilSidebarOpens();
	}

	public void confirmPublishAction() {
		// Switch to the form
		driverManager.getDriver().switchTo().activeElement();
		// Click on Publish button
		dashboardPage.clickApproveAndPublishSubmitButton();
		// switch to default content
		driverManager.getDriver().switchTo().defaultContent();
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

	public void createNewPageArticleContent() {

		driverManager.usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {
			// creating random values for URL field and InternalName field
			String randomURL = "testingPage";
			String randomInternalName = "testingPage";

			// Set basics fields of the new content created
			dashboardPage.setBasicFieldsOfNewPageArticleContent(randomURL, randomInternalName, "testingPage");

			// Set the title of main content
			this.driverManager.scrollDown();
			driverManager.sendText("xpath", createFormArticleMainTitleElementXPath, "testingPage");

			// save and close
			this.driverManager.waitForAnimation();
			this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", createFormSaveAndCloseElement)
					.click();
		});

		this.driverManager.waitUntilSidebarOpens();

	}

	public void loginAndGoToSiteContentPagesStructure() {
		// login to application
		loginPage.loginToCrafter(userName, password);

		// Wait for login page to close
		driverManager.waitUntilLoginCloses();

		// go to preview page
		homePage.goToPreviewPage();
		if (this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", siteDropdownElementXPath)
				.isDisplayed())
			if (!(this.driverManager.waitUntilElementIsPresent("xpath", siteDropdownListElementXPath)
					.getAttribute("class").contains("site-dropdown-open")))
			this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", siteDropdownElementXPath).click();
		else
			throw new NoSuchElementException(
					"Site creation process is taking too long time and the element was not found");
	}

}
