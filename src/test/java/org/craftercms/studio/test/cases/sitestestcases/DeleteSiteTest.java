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

import org.craftercms.studio.test.cases.StudioBaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class DeleteSiteTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String deletedSiteButton;
	private String createSiteButton;

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String testId, String blueprint) {
		apiTestHelper.createSite(testId, "", blueprint);
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		deletedSiteButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.deletesite");
		createSiteButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.createsitebutton");
	}

	@Parameters({"testId"})
	@Test()
	public void deleteSiteTest(String testId) {

		// login to application
		loginPage.loginToCrafter(
				userName,password);

		getWebDriverManager().waitUntilLoginCloses();

		// Click on Delete icon
        this.getWebDriverManager().isElementPresentAndClickableByXpath(createSiteButton);
		
        this.homePage.deleteSite(testId);
       	
		// Assert
		Assert.assertFalse(this.getWebDriverManager().isElementPresentAndClickableByXpath(String.format(deletedSiteButton, testId)));
	}
}




