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

import org.openqa.selenium.WebElement;
import org.testng.Assert;
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

//Test Case Studio- Sites ID:6
public class VerifyStudioAllowsToFilterNumberOfSitesPerPage extends StudioBaseTest{

	private String userName;
	private String password;
	private String sitesPerPageInputXpath;
	private String firstSiteXpath;
	private String secondSiteXpath;
	private String thirdSiteXpath;
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
		sitesPerPageInputXpath= uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.sitesperpageinput");
		firstSiteXpath= uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.firstSiteNameOnList");
		secondSiteXpath= uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.secondSiteNameOnList");
		thirdSiteXpath= uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.thirdSiteNameOnList");
		lastNumberOfPaginationXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.pagination.lastnumberelement");
		firstNumberOfPaginationXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.pagination.firstnumberelement");
		lastArrowOfPaginationXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.pagination.lastarrowelement");
		firstArrowOfPaginationXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.pagination.firstarrowelement");
	}

	public void filters() {

		// Show 1 sites
		this.getWebDriverManager().waitUntilElementIsDisplayed("xpath",
				sitesPerPageInputXpath).clear();
		this.getWebDriverManager().waitUntilElementIsDisplayed("xpath",
				sitesPerPageInputXpath).sendKeys("1");

		// Assert only 1 sites displayed
		WebElement page1 = this.getWebDriverManager().waitUntilElementIsDisplayed("xpath",
				firstSiteXpath);
		Assert.assertTrue(page1.isDisplayed());

		// Show 2 sites
		this.getWebDriverManager().waitUntilElementIsDisplayed("xpath",
				sitesPerPageInputXpath).clear();
		this.getWebDriverManager().waitUntilElementIsDisplayed("xpath",
				sitesPerPageInputXpath).sendKeys("2");

		// Assert only 2 sites displayed
		WebElement page2 = this.getWebDriverManager().waitUntilElementIsDisplayed("xpath",
				secondSiteXpath);
		Assert.assertTrue(page2.isDisplayed());

		// Show 3 site
		this.getWebDriverManager().waitUntilElementIsDisplayed("xpath",
				sitesPerPageInputXpath).clear();
		this.getWebDriverManager().waitUntilElementIsDisplayed("xpath",
				sitesPerPageInputXpath).sendKeys("3");

		// Assert only 3 site displayed
		WebElement page3 = this.getWebDriverManager().waitUntilElementIsDisplayed("xpath",
				thirdSiteXpath);
		Assert.assertTrue(page3.isDisplayed());

		// Show 10 sites
		this.getWebDriverManager().waitUntilElementIsDisplayed("xpath",
				sitesPerPageInputXpath).clear();
		this.getWebDriverManager().waitUntilElementIsDisplayed("xpath",
				sitesPerPageInputXpath).sendKeys("10");
		
		this.getWebDriverManager().waitForAnimation();
		
		this.navigationOfPage();
	}

	public void navigationOfPage() {

		this.getWebDriverManager().waitUntilElementIsDisplayed("xpath", sitesPerPageInputXpath).clear();

		this.getWebDriverManager().waitUntilElementIsDisplayed("xpath", sitesPerPageInputXpath).sendKeys("1");

		this.getWebDriverManager().waitUntilElementIsDisplayed("xpath", sitesPerPageInputXpath).clear();

		this.getWebDriverManager().waitUntilElementIsDisplayed("xpath", sitesPerPageInputXpath).sendKeys("2");

		// navigation
		this.getWebDriverManager().waitUntilElementIsDisplayed("xpath", lastNumberOfPaginationXpath).click();

		this.getWebDriverManager().waitUntilElementIsDisplayed("xpath", firstNumberOfPaginationXpath).click();

		this.getWebDriverManager().waitUntilElementIsDisplayed("xpath", lastArrowOfPaginationXpath).click();

		this.getWebDriverManager().waitUntilElementIsDisplayed("xpath", firstArrowOfPaginationXpath).click();

		this.getWebDriverManager().waitUntilElementIsDisplayed("xpath", sitesPerPageInputXpath).clear();

		this.getWebDriverManager().waitUntilElementIsDisplayed("xpath", sitesPerPageInputXpath).sendKeys("10");

		this.getWebDriverManager().waitForAnimation();
	}

	@Test()
	public void verifyStudioAllowsToFilterNumberOfSitesPerPage() {
		loginPage.loginToCrafter(userName, password);
		getWebDriverManager().waitUntilLoginCloses();
		filters();
	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId+"1");
		apiTestHelper.deleteSite(testId+"2");
		apiTestHelper.deleteSite(testId+"3");
	}
}
