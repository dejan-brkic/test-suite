package org.craftercms.studio.test.cases.contenttestcases;

import org.craftercms.studio.test.cases.StudioBaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class AddNewFolderTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String siteDropdownElementXPath;
	private String newFolderXpath;
	private String homeTree;
	private String siteDropdownListElementXPath;

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		newFolderXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sitecontent.newfolder");
		homeTree = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.home_Content_Page");
		siteDropdownListElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdownlielement");
	}

	@Test(priority = 0)
	public void createANewFolderUsingContextualClickOptionTest() {

		// login to application

		loginPage.loginToCrafter(userName, password);

		driverManager.waitUntilLoginCloses();

		// go to dashboard page

		homePage.goToDashboardPage();

		// Show site content panel
		if (!(this.driverManager.waitUntilElementIsPresent("xpath", siteDropdownListElementXPath)
				.getAttribute("class").contains("site-dropdown-open")))
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", siteDropdownElementXPath).click();

		// expand pages folder
		dashboardPage.expandPagesTree();

		
		// right click to see the the menu
		dashboardPage.rightClickToFolderOnHome();

		// Set the name of the folder
		dashboardPage.setFolderName("addnewfolder");

		this.driverManager.waitUntilPageLoad();
		this.driverManager.waitUntilSidebarOpens();
		
		dashboardPage.expandHomeTree();
		
	
		this.driverManager.waitUntilFolderOpens("xpath", homeTree);
		
		driverManager.getDriver().navigate().refresh();
		this.driverManager.waitUntilHomeIsOpened();
		
		// Assert find the new folder created	
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", newFolderXpath);
		
		Assert.assertTrue(this.driverManager.isElementPresentByXpath(newFolderXpath));

	}

}