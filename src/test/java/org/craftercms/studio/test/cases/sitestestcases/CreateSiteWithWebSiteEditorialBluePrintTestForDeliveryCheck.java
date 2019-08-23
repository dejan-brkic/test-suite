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

import org.apache.commons.lang3.RandomStringUtils;
import org.craftercms.studio.test.cases.StudioBaseTest;
import org.craftercms.studio.test.utils.FilesLocations;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

/**
 * 
 * 
 * @author luishernandez
 *
 */

// related to ticket on windows:
// https://github.com/craftercms/craftercms/issues/1905
public class CreateSiteWithWebSiteEditorialBluePrintTestForDeliveryCheck extends StudioBaseTest {

	private String userName;
	private String password;
	private String siteDropdownElementXPath;
	private String siteId;

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		siteId = "testsite" + RandomStringUtils.randomAlphabetic(5).toLowerCase();
	}

	@Test(priority = 0)
	public void createSiteWithWebSiteEditorialBluePrintTestForDeliveryCheck() {

		// login to application
		loginPage.loginToCrafter(userName, password);

		getWebDriverManager().waitUntilLoginCloses();

		// Click on the create site button
		homePage.clickOnCreateSiteButton();
		//select blueprint, set site name, set description, click review and create site
		createSitePage.selectWebSiteEditorialBluePrintOption()
				.setSiteName(siteId)	// Filling the name of site
				.setDescription("Description")		// Filling the description of the site
				.clickReviewAndCreate()
				.clickOnCreateButton();

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", siteDropdownElementXPath);

		Assert.assertTrue(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", siteDropdownElementXPath)
				.isDisplayed());

		// go to delivery folder and init site for test
		int exitCode = this.getWebDriverManager().goToDeliveryFolderAndExecuteSiteScriptThroughCommandLine(siteId, "init");

		Assert.assertTrue(exitCode == 0, "Init site process failed");

		// saving the siteId for the dependent test cases to this test case.
		deliveryExecutionValuesManager.setProperty("general.currentsiteid", siteId, FilesLocations.DELIVERYEXECUTIONVALUESPROPERTIESFILEPATH);
	}
}
