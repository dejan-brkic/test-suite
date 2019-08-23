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
package org.craftercms.studio.test.cases.loginlogouttestcases;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.craftercms.studio.test.cases.StudioBaseTest;

/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class LoginTest extends StudioBaseTest{

	private String userName;
	private String password;
	private String createSiteButtonXpath;
	
	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		createSiteButtonXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.createsitebutton");
	
	}

	@Test(priority = 0)
	public void loginInToStudioTest() {

		// login to application

		loginPage.loginToCrafter(userName, password);
		
		//Wait for login page to close
		getWebDriverManager().waitUntilLoginCloses();

		// Assert create button is present.
		WebElement createButton = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed( "xpath",
				createSiteButtonXpath);

		Assert.assertTrue(createButton.isDisplayed());

	}

}
