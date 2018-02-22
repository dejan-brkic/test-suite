/**
 * 
 */
package org.craftercms.studio.test.cases.contenttypepagetestcases;

import org.craftercms.studio.test.cases.StudioBaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author luishernandez
 *
 */
public class ContentTypesAddDropdownTest extends StudioBaseTest{
	private String userName;
	private String password;
	private String controlsSectionFormSectionLocator;
	private String contentTypeContainerLocator;
	private String controlsSectionDropdownLocator;
	private String contentTypeContainerFormSectionContainerLocator;
	private String contentTypeContainerDropdownTitleLocator;
	private String siteDropdownXpath;
	private String adminConsoleXpath;
	private String siteDropdownListElementXPath;

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		this.controlsSectionFormSectionLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.controlssectionformsection");
		this.contentTypeContainerLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.contenttypecontainer");
		this.controlsSectionDropdownLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.controlsdropdown");
		this.contentTypeContainerFormSectionContainerLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.contenttypecontainerformsectioncontainer");
		this.contentTypeContainerDropdownTitleLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.contenttypecontainerdropdowntitle");
		siteDropdownXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sitedropdown");
		adminConsoleXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.adminconsole");
		siteDropdownListElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdownlielement");
	}

	public void dragAndDrop() {

		// Getting the Form Section control input for drag and drop action
		WebElement FromControlSectionFormSectionElement = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", controlsSectionFormSectionLocator);

		// Getting the Content Type Container for drag and drop action
		// (destination)
		WebElement ToContentTypeContainer = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				contentTypeContainerLocator);

		driverManager.dragAndDropElement(FromControlSectionFormSectionElement, ToContentTypeContainer);

		WebElement FromDropDown = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				controlsSectionDropdownLocator);

		WebElement ToDefaultSection = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				contentTypeContainerFormSectionContainerLocator);

		siteConfigPage.getDriverManager().dragAndDropElement(FromDropDown, ToDefaultSection);

		// Complete the input fields basics
		siteConfigPage.completeControlFieldsBasics("TestTitle", "TestICEGroup", "TestDescription", "TestDefault");

		// Save the data
		siteConfigPage.saveDragAndDropProcess();

	}

	@Test(priority = 0)
	public void verifyThatStudioAllowsToAddADropdownControlToExistingContentTypeTest() {

		// login to application
		loginPage.loginToCrafter(userName, password);
		
		//Wait for login page to closes
		driverManager.waitUntilLoginCloses();

		// go to preview page
		homePage.goToPreviewPage();

		// Show site content panel
		if (!(this.driverManager.waitUntilElementIsPresent("xpath", siteDropdownListElementXPath)
				.getAttribute("class").contains("site-dropdown-open")))
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				siteDropdownXpath).click();

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

		driverManager.waitUntilPopupIsHidden();
		siteConfigPage.clickDropdownSection();

		// Asserts that fields are not empty.
		String titleText = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", contentTypeContainerDropdownTitleLocator)
				.getText();

		Assert.assertTrue(titleText.contains("TestTitle"));
		siteConfigPage.saveDragAndDropProcess();
	}
}
