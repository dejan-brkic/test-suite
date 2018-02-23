package org.craftercms.studio.test.cases.contextualnavigationtestcases;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.craftercms.studio.test.cases.StudioBaseTest;


/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class DependenciesOptionTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String siteDropdownXpath;
	private String homeXpath;
	private String dependeciesDialogTitle;
	private String siteDropdownListElementXPath;

	@BeforeMethod
	public void beforeTest() {

		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		siteDropdownXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sitedropdown");
		homeXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.home");
		dependeciesDialogTitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.dependeciesdialogtitle");
		siteDropdownListElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdownlielement");
	}

	@Test(priority = 0)
	public void dependenciesOptionTest() {

		// login to application
		loginPage.loginToCrafter(userName, password);
		
		//Wait for login page to closes
		driverManager.waitUntilLoginCloses();

		// go to preview page
		homePage.goToPreviewPage();

		// Show site content panel
		if (!(this.driverManager.waitUntilElementIsPresent("xpath", siteDropdownListElementXPath)
				.getAttribute("class").contains("site-dropdown-open")))
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed( "xpath",
				siteDropdownXpath).click();

		// expand pages folder
		previewPage.expandPagesTree();
		
		driverManager.getDriver().navigate().refresh();
		
		// expand home content
		previewPage.expandHomeTree();

		// Select the content to view the history.
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", homeXpath).click();
		
		// click on history option
		previewPage.clickOnDependenciesOption();

		// Assert
		String historyPage = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", dependeciesDialogTitle).getText();
		Assert.assertEquals(historyPage, "Dependencies");

	}

}
