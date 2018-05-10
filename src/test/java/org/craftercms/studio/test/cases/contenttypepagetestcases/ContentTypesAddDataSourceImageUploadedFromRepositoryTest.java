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

public class ContentTypesAddDataSourceImageUploadedFromRepositoryTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String contentTypeContainerLocator;
	private String dataSourceSectionImageUploadedFromRepositoryLocator;
	private String contentTypeContainerImageUploadedFromRepositoryTitleLocator;
	private String siteDropdownXpath;
	private String adminConsoleXpath;
	private String siteDropdownListElementXPath;

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		this.contentTypeContainerLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.contenttypecontainer");
		this.dataSourceSectionImageUploadedFromRepositoryLocator = uiElementsPropertiesManager
				.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.datasourceimageuploadedfromrepository");
		this.contentTypeContainerImageUploadedFromRepositoryTitleLocator = uiElementsPropertiesManager
				.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.contenttypecontainerimageuploadedfromrepositorytitle");
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
		WebElement FromDataSourceImageUploadedFromRepoElement = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed( "xpath",
						dataSourceSectionImageUploadedFromRepositoryLocator);

		// Getting the Content Type Container for drag and drop action
		// (destination)
		WebElement ToContentTypeContainer = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed( "xpath",
				contentTypeContainerLocator);

		driverManager.dragAndDropElement(FromDataSourceImageUploadedFromRepoElement, ToContentTypeContainer);
		
		// Complete the input fields basics
		siteConfigPage.completeDataSourceFieldsBasics("TestTitle");

		// Save the data
		siteConfigPage.saveDragAndDropProcess();
	}

	@Test(priority = 0)
	public void verifyThatStudioAllowsToAddADataSourceImageUploadedFromRepositoryToExistingContentTypeTest() {

		// login to application
		loginPage.loginToCrafter(
				userName,password);

		//Wait for login page to closes
		driverManager.waitUntilLoginCloses();
		
		//Go to Preview Page
		homePage.goToPreviewPage();

		// Show site content panel
		if (!(this.driverManager.waitUntilElementIsPresent("xpath", siteDropdownListElementXPath)
				.getAttribute("class").contains("site-dropdown-open")))
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed( "xpath",
				siteDropdownXpath).click();

		// Show admin console page
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed( "xpath", adminConsoleXpath).click();

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
		siteConfigPage.clickDataSourceImageUploadedFromRepositorySection();

		// Asserts that fields are not empty.
		this.driverManager.isElementPresentAndClickableByXpath(contentTypeContainerLocator);
		this.driverManager.isElementPresentByXpath(contentTypeContainerImageUploadedFromRepositoryTitleLocator);
		
		String titleText = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed( "xpath",
				contentTypeContainerImageUploadedFromRepositoryTitleLocator).getText();
		Assert.assertTrue(titleText.contains("TestTitle"));
		siteConfigPage.cancelChangesOnContentType();

	}

}
