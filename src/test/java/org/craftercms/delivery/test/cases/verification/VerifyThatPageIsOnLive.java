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

package org.craftercms.delivery.test.cases.verification;

import org.craftercms.studio.test.cases.DeliveryBaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * 
 * @author Luis Hernandez
 *
 */
//Related to ticket: https://github.com/craftercms/craftercms/issues/1869
public class VerifyThatPageIsOnLive extends DeliveryBaseTest {
	private String pageTitleXpath;

	@Parameters({"testId"})
	@BeforeMethod
	public void beforeTest(String testId) {
		String pageURL = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("delivery.verification.pageurl");
		String siteURL = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("delivery.verification.siteurl");
		pageTitleXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("delivery.verification.pagetitle");
		this.getWebDriverManager().getDriver().get(siteURL+testId);
		this.getWebDriverManager().getDriver().get(pageURL);
	}

	@Test()
	public void verifyThatPageIsOnLive() {
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().waitUntilElementIsDisplayed("xpath", pageTitleXpath);
		Assert.assertTrue(this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", pageTitleXpath)
				.getText().equalsIgnoreCase("testingpage"));
	}

}
