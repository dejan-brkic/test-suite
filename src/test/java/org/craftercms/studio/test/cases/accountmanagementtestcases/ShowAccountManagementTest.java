/*
 * Copyright (C) 2007-2018 Crafter Software Corporation. All Rights Reserved.
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

package org.craftercms.studio.test.cases.accountmanagementtestcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.cases.StudioBaseTest;
import org.craftercms.studio.test.utils.APIConnectionManager;

public class ShowAccountManagementTest extends StudioBaseTest {

	private String userName;
	private String password;
	private APIConnectionManager apiConnectionManager;
	private String accountManagementTitle;
	private static Logger logger = LogManager.getLogger(ShowAccountManagementTest.class);

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		apiConnectionManager = new APIConnectionManager();
		accountManagementTitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("accountManagement.pageTitle");
	}

	@Test(priority = 0)
	public void verifyThatApplicationShowsAccountManagementPageWhenUserClicksSettingsContextualNavigationOption() {

		// login to application
		logger.info("Login into Crafter");
		loginPage.loginToCrafter(userName, password);

		// wait for login page to close
		driverManager.waitUntilLoginCloses();

		// wait for element is clickable
		createSitePage.clickAdmin();

		// click on settings
		createSitePage.clickOnSettingsOption();

		this.driverManager.waitForAnimation();
		this.driverManager.waitUntilElementIsDisplayed("xpath", accountManagementTitle);
		
		// Checking if the Sites page was Loaded
		Assert.assertTrue(accountManagementPage.getDriverManager().getDriver().getCurrentUrl()
				.equals(apiConnectionManager.getHeaderLocationBase() + "/studio/#/settings"));

		// Checking if the Users title is displayed on the current page
		Assert.assertTrue(accountManagementPage.isAccountManagementTitlePresent());

	}
}
