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

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.craftercms.studio.test.cases.StudioBaseTest;

/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class PaginationOfListOfUsersTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String usersPerPageInputXpath;
	private String lastNumberOfPaginationXpath;
	private String firstNumberOfPaginationXpath;
	private String lastArrowOfPaginationXpath;
	private String firstArrowOfPaginationXpath;

	@Parameters({"testUser"})
	@BeforeMethod
	public void beforeTest(String testUser) {
		apiTestHelper.createUser(testUser + "01");
		apiTestHelper.createUser(testUser + "02");
		apiTestHelper.createUser(testUser + "03");
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		usersPerPageInputXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.usersperpageinput");
		lastNumberOfPaginationXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.pagination.lastnumberelement");
		firstNumberOfPaginationXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.pagination.firstnumberelement");
		lastArrowOfPaginationXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.pagination.lastarrowelement");
		firstArrowOfPaginationXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.pagination.firstarrowelement");

	}

	public void navigationOfPage() {

		// Show users
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", usersPerPageInputXpath)
				.clear();

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", usersPerPageInputXpath)
				.sendKeys("1");

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", usersPerPageInputXpath)
				.clear();

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", usersPerPageInputXpath)
				.sendKeys("2");

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", lastNumberOfPaginationXpath)
				.click();

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", firstNumberOfPaginationXpath)
				.click();

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", lastArrowOfPaginationXpath)
				.click();

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", firstArrowOfPaginationXpath)
				.click();

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", usersPerPageInputXpath)
				.clear();

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", usersPerPageInputXpath)
				.sendKeys("10");

		this.getWebDriverManager().waitForAnimation();
	}

	@Test()
	public void verifyThatThePaginationOfTheListOfUsersWorksProperlyTest() {

		// login to application
		loginPage.loginToCrafter(userName, password);

		// Wait for login page to close
		getWebDriverManager().waitUntilLoginCloses();

		createSitePage.clickOnUsersOption();

		// filters
		navigationOfPage();

		// Delete users
		this.getWebDriverManager().waitForAnimation();
	}

	@Parameters({"testUser"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testUser) {
		apiTestHelper.deleteUser(testUser + "01");
		apiTestHelper.deleteUser(testUser + "02");
		apiTestHelper.deleteUser(testUser + "03");
	}
}
