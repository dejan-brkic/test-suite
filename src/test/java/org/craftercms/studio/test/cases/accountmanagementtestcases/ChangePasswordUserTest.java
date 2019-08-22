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

package org.craftercms.studio.test.cases.accountmanagementtestcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.cases.StudioBaseTest;
import org.openqa.selenium.WebElement;

/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class ChangePasswordUserTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String createSiteButtonXpath;
	final static Logger logger = LogManager.getLogger(ChangePasswordUserTest.class);

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		createSiteButtonXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.createsitebutton");

	}

	@Test(priority = 0)
	public void changePasswordUser() {

		// login to application
		logger.info("Login into Crafter");
		loginPage.loginToCrafter(userName, password);

		// wait for login page to close
		getWebDriverManager().waitUntilLoginCloses();

		// wait for element is clickable
		createSitePage.clickAdmin();

		// click on settings
		createSitePage.clickOnSettingsOption();

		// change password
		logger.info("Change actual User Password");
		accountManagementPage.changeUserPassword(userName, "123456", "123456");

		// login to application
		logger.info("Login into Crafter with the new password");
		this.getWebDriverManager().waitForAnimation();
		loginPage.loginToCrafter(userName, "123456");

		// wait for login page to close
		getWebDriverManager().waitUntilLoginCloses();

		this.getWebDriverManager().waitForAnimation();
		
		// click On admin option
		createSitePage.clickAdmin();

		// click on settings
		createSitePage.clickOnSettingsOption();

		// change password
		logger.info("Restore user admin default password");
		accountManagementPage.changeUserPassword("123456", userName, "admin");

		// login to application
		logger.info("Login into Crafter");
		this.getWebDriverManager().waitForAnimation();
		loginPage.loginToCrafter(userName, password);

		// wait for login page to close
		getWebDriverManager().waitUntilLoginCloses();
		this.getWebDriverManager().waitForAnimation();
		// Assert create button is present.
		logger.info("Verify login is correct and Create Site page is displayed");
		WebElement createButton = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
				createSiteButtonXpath);
		Assert.assertTrue(createButton.isDisplayed(), "Create Site Button is not displayed");

	}
}
