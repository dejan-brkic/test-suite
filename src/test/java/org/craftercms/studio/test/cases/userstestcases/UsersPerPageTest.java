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
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

import org.craftercms.studio.test.cases.StudioBaseTest;


/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class UsersPerPageTest extends StudioBaseTest{

	private String userName;
	private String password;
	private String usersPerPageInputXpath;
	private String usersRowsXpath;
	private String lastNumberOfPaginationXpath;

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
		usersRowsXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.usersrows");
		lastNumberOfPaginationXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.pagination.lastnumberelement");
	}

	public void filters() {

		// Show 1 user
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", usersPerPageInputXpath);
		getWebDriverManager().sendText("xpath",usersPerPageInputXpath,"1");

		// Asser only 1 users displayed
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",lastNumberOfPaginationXpath);
		Assert.assertTrue(this.getWebDriverManager().elementHasChildsByXPath(usersRowsXpath));

		List<WebElement> usersList1item = this.getWebDriverManager().getDriver().findElements(By.xpath(usersRowsXpath));
		Assert.assertTrue(usersList1item.size() == 1);
		
		// Show 3 users
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",usersPerPageInputXpath);
		getWebDriverManager().sendText("xpath",usersPerPageInputXpath,"3");

		// Assert 3 users displayed
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",lastNumberOfPaginationXpath);
		Assert.assertTrue(this.getWebDriverManager().elementHasChildsByXPath(usersRowsXpath));

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",usersPerPageInputXpath);
		getWebDriverManager().sendText("xpath",usersPerPageInputXpath,"10");
		this.getWebDriverManager().waitForAnimation();
	}

	@Test(priority = 0)
	public void verifyThatTheShowUsersPerPageWorksProperlyTest() {
		// login to application
		loginPage.loginToCrafter(userName, password);
		
		//Wait for login page to close
		getWebDriverManager().waitUntilLoginCloses();

		createSitePage.clickOnUsersOption();

		// filters
		filters();	
		
		this.getWebDriverManager().waitForAnimation();
		// Delete all users
	}

	@Parameters({"testUser"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testUser) {
		apiTestHelper.deleteUser(testUser + "01");
		apiTestHelper.deleteUser(testUser + "02");
		apiTestHelper.deleteUser(testUser + "03");
	}
}
