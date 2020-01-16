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
package org.craftercms.studio.test.cases.contenttestcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.cases.StudioBaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * 
 * @author luishernandez
 *
 */
// Test Case Studio- Site Content ID:27
public class CutPasteToFolderTest extends StudioBaseTest {

	private String randomURL;
	private String randomInternalName;
	private String configurationSetUp;
	private String dashboardLink;
	private String recentActivityContentURL;
	private String recentActivityContentName;
	private String fooContentXpath;
	private String cutContent;
	private String pasteContent;
	private String folderChildContent;
	private String folderContent;
	private static final Logger logger = LogManager.getLogger(CutPasteToFolderTest.class);

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String testId, String blueprint) {
		apiTestHelper.createSite(testId, "", blueprint);
		dashboardLink = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.dashboard.dashboardlink");
		cutContent = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("rightclick.cut.option");
		recentActivityContentURL = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.contenturl");
		recentActivityContentName = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.contentname");
		fooContentXpath = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("general.foocontent");
		pasteContent = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("rightclick.paste.option");
		folderContent = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("general.afolder");
		folderChildContent = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.afolder.childcontent");
		randomURL = "foo";
		randomInternalName = "foo";
		configurationSetUp = "<content-type name=\"/page/entry\" is-wcm-type=\"true\">"
				+ "<label>Entry</label>" + "<form>/page/entry</form>" + "<form-path>simple</form-path>"
				+ "<model-instance-path>NOT-USED-BY-SIMPLE-FORM-ENGINE</model-instance-path>"
				+ "<file-extension>xml</file-extension>" + "<content-as-folder>false</content-as-folder>"
				+ "<previewable>true</previewable>" + "<noThumbnail>true</noThumbnail>"
				+ "<image-thumbnail>image.jpg</image-thumbnail>" + "</content-type>";

	}

	public void modifyPageXMLDefinition() {
		previewPage.modifyPageXMLDefinitionContentAsFolderEntryContentType(configurationSetUp);
	}

	public void setup(String siteId) {
		loginPage.loginToCrafter();
		homePage.goToDashboardPage(siteId);
		previewPage.clickSidebar();
		this.modifyPageXMLDefinition();
		dashboardPage.expandPagesTree();
		logger.info("Creating new folder");
		dashboardPage.rightClickNewFolderOnHome();
		dashboardPage.setFolderName("a-folder");

		previewPage.createEntryContent(randomURL, randomInternalName, "title" + siteId, "body" + siteId);

		// click on dashboard
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dashboardLink);
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dashboardLink).click();

		// check items on My Recent Activity widget
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentName);
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentURL);

		this.getWebDriverManager().waitForAnimation();
		Assert.assertTrue(
				this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentName)
						.getText().contains("foo"));
		this.getWebDriverManager().waitForAnimation();
		Assert.assertTrue(
				this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentURL)
						.getText().contains("/foo.xml"));
	}

	public void step3() {
		// expand pages folder
		this.getWebDriverManager().waitUntilSidebarOpens();
		this.getWebDriverManager().waitForAnimation();
		dashboardPage.expandPagesTree();
	}

	public void step4() {
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", fooContentXpath);
		this.getWebDriverManager().contextClick("xpath", fooContentXpath, false);
		getWebDriverManager().usingContextMenu(() -> {
			WebElement cutContent = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
					this.cutContent);
			cutContent.click();
		}, "Pages");

		this.getWebDriverManager().waitForAnimation();
	}

	public void step6() {
		// click on dashboard
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dashboardLink);
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dashboardLink).click();

		// check items on My Recent Activity widget
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentName);
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentURL);

		this.getWebDriverManager().waitForAnimation();
		Assert.assertTrue(
				this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentName)
						.getText().contains("foo"));
		this.getWebDriverManager().waitForAnimation();
		Assert.assertTrue(
				this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentURL)
						.getText().contains("/a-folder/foo.xml"));
	}

	public void step5() {
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", folderContent);
		this.getWebDriverManager().contextClick("xpath", folderContent, false);
		getWebDriverManager().usingContextMenu(() -> {
			WebElement pasteContentElement = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
					pasteContent);
			pasteContentElement.click();
		}, "Pages");

		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().waitForFullExpansionOfTree();
		Assert.assertTrue(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", folderChildContent).isDisplayed());
	}

	@Parameters({"testId"})
	@Test()
	public void cutPasteToFolderPageXMLMoveToFolderTest(String testId) {
		this.setup(testId);

		this.step3();

		this.step4();

		this.step5();

		this.step6();

	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
