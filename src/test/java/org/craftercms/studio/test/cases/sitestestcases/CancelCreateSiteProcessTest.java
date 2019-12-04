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

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.craftercms.studio.test.cases.StudioBaseTest;

/**
 * 
 * @author luishernandez
 *
 */
//Test Case Studio- Sites ID:3
public class CancelCreateSiteProcessTest extends StudioBaseTest{

	private String sitesPageTitleLocator;

	@BeforeMethod
	public void beforeTest() {
		sitesPageTitleLocator = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("sites.pagetitle");
	}

	@Test(priority = 0)
	public void verifyTheCancellationOfTheCreateSiteProcessTest() {

		// login to application
		loginPage.loginToCrafter();

		// Click on the create site button
		homePage.clickOnCreateSiteButton();

		//select blueprint, set site name, set description, click review and cancel the create process
		createSitePage.selectWebSiteEditorialBluePrintOption()
				.setSiteName()
				.setDescription("Description")
				.clickReview()
				.cancelButton()
				.confirmCancelCreateSite();

		// Assert
		WebElement sitePage = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed( "xpath",
				sitesPageTitleLocator);

		Assert.assertTrue(sitePage.isDisplayed());
	}

}
