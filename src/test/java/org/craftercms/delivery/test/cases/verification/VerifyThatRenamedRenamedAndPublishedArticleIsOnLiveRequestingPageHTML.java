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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
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

	@Test()
	public void verifyThatRenamedAndPublishedArticleIsOnLiveRequestingPageHTML() {
		//click on entertainment and check the article 
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",".//a[text()='Entertainment']").click();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",".//a[text()='foo']").click();
		
		this.getWebDriverManager().waitUntilElementIsDisplayed("xpath", pageTitleXpath);
		Assert.assertTrue(this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", pageTitleXpath)
				.getText().equalsIgnoreCase("foo"));
		Assert.assertTrue(this.getWebDriverManager().getDriver().getCurrentUrl()
				.equalsIgnoreCase(getWebDriverManager().environmentProperties.getProperty("delivery.base.url") + "articles/2016/12/baz.html"));
	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
		int exitCode = getWebDriverManager().goToDeliveryFolderAndExecuteSiteScriptThroughCommandLine(testId, "remove");
		Assert.assertEquals(exitCode, 0, "Remove site process failed");
	}
}
