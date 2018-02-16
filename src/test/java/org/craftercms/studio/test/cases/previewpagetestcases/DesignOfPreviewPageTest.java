package org.craftercms.studio.test.cases.previewpagetestcases;


import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.craftercms.studio.test.cases.StudioBaseTest;

/**
 * 
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class DesignOfPreviewPageTest extends StudioBaseTest {

	private String userName;
	private String password;

	private String crafterLogoId;
	private String siteDropDownXpath;
	private String searchTopBarOptionId;
	private String accountDropdownTopBarOptionId;
	private String topNavDeleteOption;
	private String topNavEditOption;
	private String topNavHistoryOption;
	private String topNavDependenciesOption;
	private String dashboardOptionXpath;
	private String adminConsoleXpath;
	private String topNavUsersOption;
	private String topNavSitesOption;

	@BeforeMethod
	public void beforeTest() {

		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		crafterLogoId = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("general.studiologo");
		siteDropDownXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sitedropdown");
		searchTopBarOptionId = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.preview.searchtopbaroption");
		accountDropdownTopBarOptionId = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.preview.accountdropdowntopbaroption");
		topNavDeleteOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.deletetopnavoption");
		topNavEditOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.edittopnavoption");
		topNavHistoryOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.historytopnavoption");
		topNavDependenciesOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.dependenciestopnavoption");
		dashboardOptionXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sitecontent.dashboard");
		adminConsoleXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.adminconsole");
		topNavUsersOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.userstopnavoption");
		topNavSitesOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sitestopnavoption");

	}

	@Test(priority = 0)
	public void verifyTheDesignOfPreviewPageTest() {

		// login to application

		loginPage.loginToCrafter(userName, password);
		
		//Wait for login page to close
		driverManager.waitUntilLoginCloses();

		// go to preview page
		homePage.goToPreviewPage();

		// reload page
		driverManager.getDriver().navigate().refresh();

		// Assert crafter studio logo is present.
		WebElement logoCrafter = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", crafterLogoId);
		Assert.assertTrue(logoCrafter.isDisplayed(), "ERROR: Crafter logo is not displayed");

		// Assert site content option is present.
		WebElement siteContent = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				siteDropDownXpath);

		Assert.assertTrue(siteContent.isDisplayed(), "ERROR: Site content option is not displayed");

		// Assert search field is present.
		WebElement searchField = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				searchTopBarOptionId);

		Assert.assertTrue(searchField.isDisplayed(), "ERROR: Search Field is not displayed");

		// Assert account option is present.
		WebElement signUp = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				accountDropdownTopBarOptionId);

		Assert.assertTrue(signUp.isDisplayed(), "ERROR: Account option is not displayed");

		// Assert Edit option is present.
		WebElement editOption = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				topNavEditOption);

		Assert.assertTrue(editOption.isDisplayed(), "ERROR: Edit option is not displayed");

		// Assert delete option is present.
		WebElement deleteOption = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				topNavDeleteOption);

		Assert.assertTrue(deleteOption.isDisplayed(), "ERROR: Delete option is not displayed");

		// Assert history option is present.
		WebElement historyOption = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				topNavHistoryOption);
		Assert.assertTrue(historyOption.isDisplayed(), "ERROR: history option is not displayed");

		// Assert history option is present.
		WebElement dependencies = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				topNavDependenciesOption);
		Assert.assertTrue(dependencies.isDisplayed(), "ERROR: Dependencies option is not displayed");

		// Show site content panel
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				siteDropDownXpath).click();

		// Assert all Sites Dropdown option is present.
		WebElement dashboard = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				dashboardOptionXpath);
		Assert.assertTrue(dashboard.isDisplayed(), "ERROR: All sites option is not displayed");

		// Assert Users option is present.
		WebElement usersOption = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				topNavUsersOption);
		Assert.assertTrue(usersOption.isDisplayed(), "ERROR: Users option is not displayed");

		// Assert sites option is present.
		WebElement sitesOption = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				topNavSitesOption);
		Assert.assertTrue(sitesOption.isDisplayed(), "ERROR: All sites option is not displayed");

		// Assert admin console option is present.
		WebElement adminConsoleOption = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				adminConsoleXpath);
		Assert.assertTrue(adminConsoleOption.isDisplayed(), "ERROR: Admin Console option is not displayed");

	}

}
