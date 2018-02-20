package org.craftercms.studio.test.cases.contenttestcases;

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

public class AddNewContentSectionDefaultsTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String createFormFrameElementCss;
	private String createFormSaveAndCloseElement;
	private String siteDropDownXpath;
	private String sectionDefaultsXpath;
	private static Logger logger = LogManager.getLogger(AddNewContentSectionDefaultsTest.class);
	

	@BeforeMethod
	public void beforeTest() {
			
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		createFormFrameElementCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformframe");
		createFormSaveAndCloseElement = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.saveandclosebutton");
		siteDropDownXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sitedropdown");
		sectionDefaultsXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sitecontent.sectiondefaults");
	}

	public void createLevelDescriptorContent() {
		
		// right click to see the the menu
		dashboardPage.rightClickToSeeMenu();
		
		// create a content with level descriptor content type
		// right click to see the the menu
		dashboardPage.selectLDCT(); 

		// Select Entry Content Type
		dashboardPage.clickLevelDescriptorCT();

		// Confirm the Content Type selected
		dashboardPage.clickOKButton();

		// Switch to the iframe
		driverManager.getDriver().switchTo().defaultContent();
		driverManager.getDriver().switchTo().frame(this.driverManager.driverWaitUntilElementIsPresentAndDisplayed(
				"cssSelector", createFormFrameElementCss));

		// save and close
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed( "xpath", createFormSaveAndCloseElement).click();

	
		// Switch back to the dashboard page
		driverManager.getDriver().switchTo().defaultContent();

		// reload page
		driverManager.getDriver().navigate().refresh();
	}

	@Test(priority = 0)
	public void addLevelDescriptorItemUsingContextualClickOptionsTest() {

		// login to application
		logger.info("Login into Crafter");
		loginPage.loginToCrafter(userName, password);
		
		//Wait for login page closes
		driverManager.waitUntilLoginCloses();

		// go to preview page
		homePage.goToPreviewPage();

		// Show site content panel
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed( "xpath", siteDropDownXpath)
				.click();

		// expand pages folder
		logger.info("Expanding pages folder");
		dashboardPage.expandPagesTree();
		
		// Expand Home Tree
		logger.info("Expanding Home Tree");
		dashboardPage.expandHomeTree();

		// Create level descriptor content
		logger.info("Creating level Descriptor content");
		createLevelDescriptorContent();

		// Assert of the test case is fine
		String levelDescriptor = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed( "xpath", sectionDefaultsXpath).getText();

		logger.info("Verify Level Descriptor was created");
		Assert.assertEquals(levelDescriptor, "Section Defaults",
				"Level descriptors are not the same, check if the level descriptor was succesfully created");

	}

}