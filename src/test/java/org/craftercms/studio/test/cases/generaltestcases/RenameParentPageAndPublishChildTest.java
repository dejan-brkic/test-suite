/**
 * 
 */
package org.craftercms.studio.test.cases.generaltestcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.craftercms.studio.test.cases.BaseTest;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

/**
 * @author luishernandez
 *
 */
public class RenameParentPageAndPublishChildTest extends BaseTest{

	private String userName;
	private String password;
	private String parentPageName;
	private String childPage1Name;
	private String childPage2Name;
	private String parentPageNewName;
	private String parentPageLocator;
	private String childPage1Locator;
	private String childPage2Locator;
	private String parentPageNewLocator;
	private String homeContent;
	private String siteDropdownElementXPath;
	private String approveSubmitId;
	private String unselectAllCheckBox;
	private String createFormFrameElementCss;
	private String createFormSaveAndCloseElement;
	private String homeExpansorXpath;
	private String createFormArticleMainTitleElementXPath;

	@BeforeMethod
	public void beforeTest() {
		this.parentPageName = "1";
		this.childPage1Name = "2";
		this.childPage2Name = "3";
		this.parentPageNewName = "11";
		
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");

		homeContent = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.home_Content_Page");
		this.parentPageLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.crafter3loadtest.parentfolder") + this.parentPageName + "')]";
		this.childPage1Locator = this.parentPageLocator + uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.crafter3loadtest.childfolder") + this.childPage1Name + "')]";
		this.childPage2Locator = this.childPage1Locator + uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.crafter3loadtest.childfolder") + this.childPage2Name + "')]";
		this.parentPageNewLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.crafter3loadtest.parentfolder") + this.parentPageNewName + "')]";
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		approveSubmitId = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.renameparentpageandpublishchildtest.approvesubmitid");
		unselectAllCheckBox = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.renameparentpageandpublishchildtest.unselectallcheckbox");
		createFormFrameElementCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformframe");
		createFormArticleMainTitleElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformMainTitle");
		createFormSaveAndCloseElement = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.saveandclosebutton");
		homeExpansorXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.homeexpansor");
	}

	public void loginAndGoToSiteContentPagesStructure() {
		// login to application
		loginPage.loginToCrafter(userName, password);

		// Wait for login page to close
		driverManager.waitUntilLoginCloses();

		// go to preview page
		homePage.goToPreviewPage();

		if (this.driverManager.isElementPresentAndClickableByXpath(siteDropdownElementXPath))
			this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", siteDropdownElementXPath).click();
		else
			throw new NoSuchElementException(
					"Site creation process is taking too long time and the element was not found");
	}

	public void publishElement(String elementLocator, String pageName) {

		dashboardPage.rightClickOnAContentPage(elementLocator);
		// selecting the Publish option
		driverManager.usingContextMenu(() -> {
			dashboardPage.clickOnPublishOption();
		});
		// moving to the publish dialog, clicking on Submit and confirm action
		this.selectOnlyOnePageToPublish(pageName);
		this.confirmPublishAction();
		this.driverManager.waitUntilSidebarOpens();
	}

	private void selectOnlyOnePageToPublish(String pageName) {
		// Switch to the form
		driverManager.getDriver().switchTo().activeElement();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("id", approveSubmitId);
		WebElement unSelectAllCheck = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				unselectAllCheckBox);
		unSelectAllCheck.click();

		String pageNameCheckLocator = ".//table[@class='item-listing scroll-body']/tbody/tr/td/div/span[contains(text(),'"
				+ pageName + "')]/../../../td/input";
		WebElement pageNameCheck = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				pageNameCheckLocator);
		pageNameCheck.click();
	}

	public void confirmPublishAction() {
		// Switch to the form
		driverManager.getDriver().switchTo().activeElement();
		// Click on Publish button
		dashboardPage.clickApproveAndPublishSubmitButton();
		// switch to default content
		driverManager.getDriver().switchTo().defaultContent();
	}

	public void createNewPageArticleContent(String pageName) {

		driverManager.usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {
			// Set basics fields of the new content created
			dashboardPage.setBasicFieldsOfNewPageArticleContent(pageName, pageName, pageName);

			// Set the title of main content
			driverManager.sendText("xpath", createFormArticleMainTitleElementXPath, pageName);

			// save and close
			this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", createFormSaveAndCloseElement)
					.click();
		});
		this.driverManager.waitUntilSidebarOpens();

	}

	public void editPageArticleContent(String pageName) {
		// Switch to the iframe
		this.driverManager.waitForAnimation();

		driverManager.usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {
			// creating random values for URL field and InternalName field
			String randomInternalName = pageName;
			// Set basics fields of the new content created
			dashboardPage.updateBasicFieldsOfNewPageArticleContent(randomInternalName, pageName);
			// Set the title of main content
			this.driverManager.scrollUp();
			this.driverManager
					.driverWaitUntilElementIsPresentAndDisplayed("xpath", createFormArticleMainTitleElementXPath)
					.sendKeys(pageName);
			// save and close

			this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", createFormSaveAndCloseElement)
					.click();
		});
		this.driverManager.waitUntilSidebarOpens();
	}

	public void createPageCategoryLandingPage(String folderWebElementSelector, String pageName) {
		dashboardPage.rightClickCreatePageOnAPresentPage(folderWebElementSelector);
		// selecting Page Category Landing Page
		dashboardPage.selectPageArticleContentType();
		// click on the Ok button to confirm the select content type above
		dashboardPage.clickOKButton();
		// creating new Page Article into the empty folder
		driverManager.getDriver().switchTo().defaultContent();
		this.createNewPageArticleContent(pageName);
	}

	public void testScenario() {
		WebElement homeParent = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", homeContent);
		this.createPageCategoryLandingPage(homeContent, parentPageName);

		homeParent = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", homeContent);
		if (!homeParent.getAttribute("class").contains("open")) {
			WebElement expansorElementForHome = this.driverManager
					.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", homeExpansorXpath);
			expansorElementForHome.click();
		}

		Assert.assertTrue(driverManager.waitUntilElementIsClickable("xpath", parentPageLocator).isDisplayed());

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", parentPageLocator);

		this.createPageCategoryLandingPage(parentPageLocator, childPage1Name);

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", childPage1Locator);
		this.createPageCategoryLandingPage(childPage1Locator, childPage2Name);
		driverManager.getDriver().navigate().refresh();
		
		this.driverManager.waitForAnimation();
		this.driverManager.waitUntilSidebarOpens();
		this.renamePage(parentPageLocator, parentPageNewName);

		this.childPage1Locator = this.parentPageNewLocator + uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.crafter3loadtest.childfolder") + this.childPage1Name + "')]";


		this.childPage2Locator = this.childPage1Locator + uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.crafter3loadtest.childfolder") + this.childPage2Name + "')]";

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", childPage2Locator).click();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", childPage2Locator);

		this.publishElement(childPage2Locator, childPage2Name);
		this.verifyPublishedItemsOnDashboard();

	}

	private void verifyPublishedItemsOnDashboard() {

		String iconNeverPublishedForParentPage = this.parentPageNewLocator
				+ "/div/span/span[contains(@class,'never-published')]";
		String iconNeverPublishedForChild1Page = this.childPage1Locator
				+ "/div/span/span[contains(@class,'never-published')]";

		this.driverManager.waitUntilPageLoad();
		this.driverManager.waitForAnimation();

		boolean isPresent = this.driverManager.isElementPresentAndClickableByXpath(iconNeverPublishedForChild1Page);

		while (isPresent) {
			this.driverManager.waitUntilPageLoad();
			driverManager.getDriver().navigate().refresh();
			isPresent = this.driverManager.isElementPresentAndClickableByXpath(iconNeverPublishedForChild1Page);
		}

		Assert.assertFalse(this.driverManager.isElementPresentAndClickableByXpath(iconNeverPublishedForParentPage));
		Assert.assertFalse(this.driverManager.isElementPresentAndClickableByXpath(iconNeverPublishedForChild1Page));

	}

	public void confirmDeleteAction() {

		// Switch to the form
		driverManager.getDriver().switchTo().activeElement();
		// Click on delete button
		dashboardPage.clickDeleteDeleteSubmitButton();
		driverManager.getDriver().switchTo().defaultContent();
	}

	private void renamePage(String pageLocator, String newPageName) {
		dashboardPage.rightClickEditOnAPresentPage(pageLocator);
		// creating new Page Article into the empty folder
		this.editPageArticleContent(newPageName);
	}

	@Test
	public void renameParentPageAndPublishChildTest() {
		// Related to the bug:
		// https://github.com/craftercms/craftercms/issues/1248
		this.loginAndGoToSiteContentPagesStructure();

		// expand pages folder
		dashboardPage.expandPagesTree();

		// create the folders structure according with script
		this.testScenario();
	}
}