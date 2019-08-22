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
package org.craftercms.studio.test.cases.sitestestcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.craftercms.studio.test.cases.StudioBaseTest;
/**
 * 
 * @author luishernandez
 *
 */

//Test Case Studio- Sites ID:7
public class VerifyThatPaginationOfSiteListWorksProperlyTest extends StudioBaseTest{

	private String userName;
	private String password;
	private String sitesPerPageInputXpath;
	private String lastNumberOfPaginationXpath;
	private String firstNumberOfPaginationXpath;
	private String lastArrowOfPaginationXpath;
	private String firstArrowOfPaginationXpath;

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String testId, String blueprint) {
		apiTestHelper.createSite(testId+"1", "", blueprint);
		apiTestHelper.createSite(testId+"2", "", blueprint);
		apiTestHelper.createSite(testId+"3", "", blueprint);
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		sitesPerPageInputXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.sitesperpageinput");
		lastNumberOfPaginationXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.pagination.lastnumberelement");
		firstNumberOfPaginationXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.pagination.firstnumberelement");
		lastArrowOfPaginationXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.pagination.lastarrowelement");
		firstArrowOfPaginationXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.pagination.firstarrowelement");
	}

	public void navigationOfPage() {

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", sitesPerPageInputXpath).clear();

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", sitesPerPageInputXpath).sendKeys("1");

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", sitesPerPageInputXpath).clear();

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", sitesPerPageInputXpath).sendKeys("2");

		// navigation
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", lastNumberOfPaginationXpath).click();

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", firstNumberOfPaginationXpath).click();

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", lastArrowOfPaginationXpath).click();

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", firstArrowOfPaginationXpath).click();

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", sitesPerPageInputXpath).clear();

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", sitesPerPageInputXpath).sendKeys("10");

		this.getWebDriverManager().waitForAnimation();
	}

	@Test()
	public void verifyThatThePaginationOnSitesListWorkProperly() {
		loginPage.loginToCrafter(userName, password);
		getWebDriverManager().waitUntilLoginCloses();
		navigationOfPage();
	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId+"1");
		apiTestHelper.deleteSite(testId+"2");
		apiTestHelper.deleteSite(testId+"3");
	}
}
