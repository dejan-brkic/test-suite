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
package org.craftercms.studio.test.cases.userstestcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.craftercms.studio.test.cases.StudioBaseTest;

/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class DeleteUserTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String usersRowsXpath;

	@BeforeMethod
	public void beforeTest() {

		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		usersRowsXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.usersrows");
	}

	@Test(priority = 0)
	public void verifyThatStudioAllowsToDeleteAnUserTest() {

		// login to application
		loginPage.loginToCrafter(userName, password);

		// Wait for login page to close
		driverManager.waitUntilLoginCloses();

		// click On Users option
		createSitePage.clickOnUsersOption();

		// Deleting user
		this.driverManager.waitForAnimation();
		this.driverManager.waitUntilPageLoad();

		usersPage.deleteAllUsersExceptAdmin();

		// Assert new users created is deleted
		this.driverManager.waitForAnimation();
		
		Assert.assertTrue(this.driverManager.elementHasChildsByXPath(usersRowsXpath));

		this.driverManager.waitForAnimation();
		Assert.assertTrue((this.driverManager.getDriver().findElements(By.xpath(usersRowsXpath))).size() == 1);

	}
}
