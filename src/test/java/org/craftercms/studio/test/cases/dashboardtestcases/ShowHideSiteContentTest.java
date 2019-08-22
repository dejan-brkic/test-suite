/*
 * Copyright (C) 2007-2019 Crafter Software Corporation. All Rights Reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.craftercms.studio.test.cases.dashboardtestcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
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
	private String siteDropdownListElementXPath;

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String testId, String blueprint) {
		apiTestHelper.createSite(testId, "", blueprint);
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		adminConsoleXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.adminconsole");
		siteDropdownListElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdownlielement");

	}

	@Parameters({"testId"})
	@Test()
	public void verifyThatTheSiteContentIsDisplayedOrHiddenWhenClicksOnSiteContentTest(String testId) {

		// login to application

		loginPage.loginToCrafter(userName, password);

		// Wait for login page to close
		getWebDriverManager().waitUntilLoginCloses();

		// go to dashboard page

		homePage.goToDashboardPage(testId);

		if (!(this.getWebDriverManager().waitUntilElementIsPresent("xpath", siteDropdownListElementXPath)
				.getAttribute("class").contains("site-dropdown-open"))) 
		dashboardPage.clickOnSiteContentOption();

		// Assert that the site content is expanded
		String siteContentExpanded = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", adminConsoleXpath).getText();

		Assert.assertEquals(siteContentExpanded, "Site Config");

		// Collapse the site content panel
		dashboardPage.clickOnSiteContentOption();

		getWebDriverManager().waitUntilSidebarCloses();

		// Assertion
		WebElement element = getWebDriverManager().getDriver().findElement(By.xpath(adminConsoleXpath));
		Assert.assertFalse(element.isDisplayed());
	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}