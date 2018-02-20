package org.craftercms.studio.test.cases.dashboardtestcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.craftercms.studio.test.cases.StudioBaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class ShowHideSiteContentTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String adminConsoleXpath;

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		adminConsoleXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.adminconsole");

	}

	@Test(priority = 0)
	public void verifyThatTheSiteContentIsDisplayedOrHiddenWhenClicksOnSiteContentTest() {

		// login to application

		loginPage.loginToCrafter(userName, password);

		// Wait for login page to close
		driverManager.waitUntilLoginCloses();

		// go to dashboard page

		homePage.goToDashboardPage();

		dashboardPage.clickOnSiteContentOption();

		// Assert that the site content is expanded
		String siteContentExpanded = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", adminConsoleXpath).getText();

		Assert.assertEquals(siteContentExpanded, "Site Config");

		// Collapse the site content panel
		dashboardPage.clickOnSiteContentOption();

		driverManager.waitUntilSidebarCloses();

		// Assertion
		WebElement element = driverManager.getDriver().findElement(By.xpath(adminConsoleXpath));
		Assert.assertFalse(element.isDisplayed());
	}

}