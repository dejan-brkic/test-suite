package org.craftercms.studio.test.cases.contenttypepagetestcases;

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

public class ContentTypesAddDataSourceChildContentTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String contentTypeContainerLocator;
	private String dataSourceSectionChildContentLocator;
	private String contentTypeContainerChildContentTitleLocator;
	private String siteDropdownXpath;
	private String adminConsoleXpath;

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		this.contentTypeContainerLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.contenttypecontainer");
		this.dataSourceSectionChildContentLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.datasourcechildcontent");
		this.contentTypeContainerChildContentTitleLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.contenttypecontainerchildcontenttitle");
		siteDropdownXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general" + ".sitedropdown");
		adminConsoleXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general" + ".adminconsole");
	}

	public void dragAndDrop() {
		this.driverManager.scrollDownPx(3000);
		// Getting the ChildContent for drag and drop action
		WebElement FromDataSourceChildContentElement = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", dataSourceSectionChildContentLocator);

		// Getting the Content Type Container for drag and drop action
		// (destination)
		WebElement ToContentTypeContainer = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				contentTypeContainerLocator);

		driverManager.dragAndDropElement(FromDataSourceChildContentElement, ToContentTypeContainer);

		// Complete the input fields basics
		siteConfigPage.completeDataSourceFieldsBasics("TestTitle");

		// Save the data
		siteConfigPage.saveDragAndDropProcess();
	}

	@Test(priority = 0)
	public void verifyThatStudioAllowsToAddADataSourceChildToExistingContentTypeTest() {

		// login to application
		loginPage.loginToCrafter(userName, password);

		// Wait for login page to closes
		driverManager.waitUntilLoginCloses();

		// go to preview page
		homePage.goToPreviewPage();

		// Show site content panel
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", siteDropdownXpath).click();

		// Show admin console page
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", adminConsoleXpath).click();

		// Select the content type to the test
		siteConfigPage.selectEntryContentTypeFromAdminConsole();

		// drag and drop
		this.dragAndDrop();

		// open content types
		siteConfigPage.clickExistingTypeOption();

		// Confirm the content type selected
		siteConfigPage.confirmContentTypeSelected();

		// Click on input section to can view the properties
		driverManager.waitUntilPopupIsHidden();
		siteConfigPage.clickDataSourceChildContentSection();

		// Asserts that fields are not empty.
		this.driverManager.isElementPresentByXpath(contentTypeContainerChildContentTitleLocator);

		String titleText = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", contentTypeContainerChildContentTitleLocator)
				.getText();
		Assert.assertTrue(titleText.contains("TestTitle"));

	}

}
