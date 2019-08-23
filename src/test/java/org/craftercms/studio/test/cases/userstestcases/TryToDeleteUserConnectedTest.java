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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.cases.StudioBaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class TryToDeleteUserConnectedTest extends StudioBaseTest {

	private static final Logger logger = LogManager.getLogger(TryToDeleteUserConnectedTest.class);

	private String userName;
	private String password;
	private String deleteYesButtonXpath;
	private String deleteNotAllowedMessageXpath;
	private String errorMessageXpath;

	@BeforeClass
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		deleteYesButtonXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.deleteyesbutton");
		deleteNotAllowedMessageXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.deletenotallowedparagraph");
		errorMessageXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.deletenotallowederrorparagraph");

	}

	@Test(priority = 0)
	public void tryToDeleteTheAdminUser() {
		logger.info("Starting test case");
		// login to application
		loginPage.loginToCrafter(userName, password);	
	
		//Wait for login page to close
		getWebDriverManager().waitUntilLoginCloses();
		
		// click On Users option
		createSitePage.clickOnUsersOption();

		// Try to delete the user current
		usersPage.clickOnDeleteUser();

		// Confirmation to delete user connected
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", deleteYesButtonXpath).click();

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
			deleteNotAllowedMessageXpath);

		// Verify
		Assert.assertTrue(this.getWebDriverManager().isElementPresentByXpath(deleteNotAllowedMessageXpath));
		Assert.assertTrue(this.getWebDriverManager().isElementPresentByXpath(errorMessageXpath));
		Assert.assertTrue(this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
				deleteNotAllowedMessageXpath).getText().contains("Unable to delete user"));

	}

}
