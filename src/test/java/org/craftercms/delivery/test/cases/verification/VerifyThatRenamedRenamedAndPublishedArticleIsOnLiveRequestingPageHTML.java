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
import org.testng.annotations.Test;

/**
 * 
 * @author Luis Hernandez
 *
 */
// Test Case Studio- Site Content ID:42
public class VerifyThatRenamedRenamedAndPublishedArticleIsOnLiveRequestingPageHTML extends DeliveryBaseTest {
	private String pageTitleXpath;

	@BeforeMethod
	public void beforeTest() {
		pageTitleXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("delivery.verification.pagetitle");
	}

	@Test(priority = 0)
	public void verifyThatRenamedAndPublishedArticleIsOnLiveRequestingPageHTML() {
		//click on entertainment and check the article 
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",".//a[text()='Entertainment']").click();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",".//a[text()='foo']").click();
		
		this.driverManager.waitUntilElementIsDisplayed("xpath", pageTitleXpath);
		Assert.assertTrue(this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", pageTitleXpath)
				.getText().equalsIgnoreCase("foo"));
		Assert.assertTrue(this.driverManager.getDriver().getCurrentUrl()
				.equalsIgnoreCase("http://localhost:9080/articles/2016/12/baz.html"));
	}

}
