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
package org.craftercms.studio.test.cases.sitedropdowntestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.cases.StudioBaseTest;
import org.openqa.selenium.WebElement;

/**
 * 
 * 
 * @author Juan Camacho A
 *
 */
//Test Case Studio- Site Dropdown ID:67
public class VerifyTheApplicationRedirectstoTheSiteConfigPageWhenTheSiteConfigOptionIsClicked extends StudioBaseTest{

	private String userName;
	private String password;
	private String siteDropdownElementXPath;
	private String siteConfigLink;
	private String contentTypeOption;
	private static Logger logger = LogManager
			.getLogger(VerifyTheApplicationRedirectstoTheSiteConfigPageWhenTheSiteConfigOptionIsClicked.class);

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String testId, String blueprint) {
		apiTestHelper.createSite(testId, "", blueprint);
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdownmenuinnerxpath");
		siteConfigLink = uiElementsPropertiesManager.getSharedUIElementsLocators()
		.getProperty("general.adminconsole");
		contentTypeOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.content_type_option");
	}

	@Parameters({"testId"})
	@Test()
	public void verifyTheApplicationRedirectsToTheSiteConfigPageWhenTheSiteConfigOptionIsClicked(String testId) {
		logger.info("Login into Crafter");
		loginPage.loginToCrafter(userName,password);
		//Wait for login page to close
		getWebDriverManager().waitUntilLoginCloses();

		homePage.goToPreviewPage(testId);

		//Expand the site bar
		logger.info("Opening the site bar");
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",siteDropdownElementXPath);
		
		this.getWebDriverManager().clickElement("xpath", siteDropdownElementXPath);

		Assert.assertTrue(this.getWebDriverManager().isElementPresentAndClickableByXpath(siteDropdownElementXPath));
		
		logger.info("Click on the Site ConFig Page");
		WebElement siteConfigLinkElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", siteConfigLink);
		siteConfigLinkElement.click();
		
		logger.info("Verify Site Config Page is displayed");
		this.getWebDriverManager().waitForAnimation();

		WebElement contentTypesOptionElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", contentTypeOption);
		Assert.assertTrue(contentTypesOptionElement.isDisplayed(),
				"ERROR: Content Type Option is not present, verify if Site config Page is displayed");
	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}

