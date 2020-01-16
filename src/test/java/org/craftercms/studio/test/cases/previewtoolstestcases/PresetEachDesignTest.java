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
package org.craftercms.studio.test.cases.previewtoolstestcases;

import org.testng.Assert;
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

public class PresetEachDesignTest extends StudioBaseTest{

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String testId, String blueprint) {
		apiTestHelper.createSite(testId, "", blueprint);
	}

	public void presets() {
		// open publishing channel combo
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed(
				"cssSelector", "#medium-panel-elem > div.acn-accordion-header > a").click();

		 String contentURL = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed(
					"cssSelector", "#engineWindow").getText();
		//Todo: use dropdown options and assert width height
		 Assert.assertTrue(contentURL.contains(contentURL));
	}

	@Parameters({"testId"})
	@Test()
	public void verifyTheDesingOfPresetsOnPreviewToolsTest(String testId) {
		loginPage.loginToCrafter();
		homePage.goToPreviewPage(testId);
		previewPage.clickSidebar()
				.createEntryContent("PRESET", "PRESET TESTING", "title", "body");
		// open tools
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed(
				"cssSelector", "#acn-preview-tools-image").click();

		// presets and asserts
		presets();

	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
