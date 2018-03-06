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

public class ContentTypesAddDataSourceImageUploadedFromDesktopTest extends StudioBaseTest {
	
	private String userName;
	private String password;
	private String contentTypeContainerLocator;
	private String dataSourceSectionImageUploadedFromDesktopLocator;
	private String contentTypeContainerImageUploadedFromDesktopTitleLocator;
	private String siteDropdownXpath;
	private String adminConsoleXpath;
	private String siteDropdownListElementXPath;

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		this.contentTypeContainerLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.contenttypecontainer");
		this.dataSourceSectionImageUploadedFromDesktopLocator = uiElementsPropertiesManager
			.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.datasourceimageuploadedfromdesktop");
		this.contentTypeContainerImageUploadedFromDesktopTitleLocator = uiElementsPropertiesManager
			.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.contenttypecontainerimageuploadedfromdesktoptitle");
		siteDropdownXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sitedropdown");
		adminConsoleXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.adminconsole");
		siteDropdownListElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdownlielement");
	}

	public void dragAndDrop() {
		this.driverManager.scrollDownPx(3000);
		// Getting the ChildContent for drag and drop action
		WebElement FromDataSourceImageUploadedFromDesktopElement =  this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed( "xpath",
						dataSourceSectionImageUploadedFromDesktopLocator);

		// Getting the Content Type Container for drag and drop action
		// (destination)
		WebElement ToContentTypeContainer = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed( "xpath",
						contentTypeContainerLocator);
		
		driverManager.dragAndDropElement(FromDataSourceImageUploadedFromDesktopElement, ToContentTypeContainer);

		// Complete the input fields basics
		siteConfigPage.completeDataSourceFieldsBasics("TestTitle");

		// Save the data
		siteConfigPage.saveDragAndDropProcess();
	}

	@Test(priority = 0)
	public void verifyThatStudioAllowsToAddADataSourceImageUploadedFromDesktopToExistingContentTypeTest() {

		// login to application
		loginPage.loginToCrafter(
				userName,password);
		
		//Wait for login page to closes
		driverManager.waitUntilLoginCloses();
		
		// go to preview page
		homePage.goToPreviewPage();
		
		// Show site content panel
		if (!(this.driverManager.waitUntilElementIsPresent("xpath", siteDropdownListElementXPath)
				.getAttribute("class").contains("site-dropdown-open")))
		 this.driverManager
			.driverWaitUntilElementIsPresentAndDisplayed( "xpath",
					siteDropdownXpath).click();

		// Show admin console page
		 this.driverManager
			.driverWaitUntilElementIsPresentAndDisplayed( "xpath",
					adminConsoleXpath).click();

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
		siteConfigPage.clickDataSourceImageUploadedFromDesktopSection();

		// Asserts that fields are not empty.
		String titleText =this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed( "xpath",
						contentTypeContainerImageUploadedFromDesktopTitleLocator).getText();
		Assert.assertTrue(titleText.contains("TestTitle"));
		siteConfigPage.saveDragAndDropProcess();

	}

}
