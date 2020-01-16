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
// Test Case Studio- Site Content ID:26
public class RenameViaFormTest extends StudioBaseTest {

	private String createFormFrameElementCss;
	private String createFormSaveAndCloseElement;
	private String randomURL;
	private String randomInternalName;
	private String configurationSetUp;
	private String dashboardLink;
	private String editRecentlyContentCreated;
	private String recentActivityContentURL;
	private String recentActivityContentName;
	private String fooContentXpath;
	private String editURLButton;
	private String warningTitle;
	private String warningOkButton;
	private String filenameInput;

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String testId, String blueprint) {
		apiTestHelper.createSite(testId, "", blueprint);
		createFormFrameElementCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformframe");
		createFormSaveAndCloseElement = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.saveandclosebutton");
		dashboardLink = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.dashboard.dashboardlink");
		editRecentlyContentCreated = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.edit.option");
		recentActivityContentURL = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.contenturl");
		recentActivityContentName = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.contentname");
		fooContentXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.foocontent");
		editURLButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame1.editurlbutton");
		warningTitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame1.warning.warningtitle");
		warningOkButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame1.warning.okbutton");
		filenameInput = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame1.filenameinput");
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
		homePage.goToPreviewPage(siteId);
		previewPage.clickSidebar();
		this.modifyPageXMLDefinition();
		previewPage.createEntryContent(randomURL, randomInternalName, "title" + siteId, "body" + siteId);

		// check items on My Recent Activity widget
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentName);
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentURL);

		this.getWebDriverManager().waitForAnimation();
		Assert.assertTrue(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentName).getText()
				.contains("foo"));
		this.getWebDriverManager().waitForAnimation();
		Assert.assertTrue(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentURL).getText()
				.contains("/foo.xml"));
	}

	public void step3() {
		dashboardPage.expandHomeTree();
	}

	public void step10() {
		// click on dashboard
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dashboardLink);
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dashboardLink)
				.click();

		// check items on My Recent Activity widget
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentName);
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentURL);

		this.getWebDriverManager().waitForAnimation();
		Assert.assertTrue(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentName).getText()
				.contains("foo"));
		this.getWebDriverManager().waitForAnimation();
		Assert.assertTrue(this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentURL).getText()
				.contains("/bar.xml"));
	}
	@Parameters({"testId"})
	@Test()
	public void renameViaFormPageXMLMoveNameTest(String testId) {
		this.setup(testId);

		this.step3();

		// steps 4, 5, 6, 7 and 8
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", fooContentXpath);
		this.getWebDriverManager().contextClick("xpath", fooContentXpath, false);
		getWebDriverManager().usingContextMenu(() -> {
			WebElement editOption = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
					editRecentlyContentCreated);
			editOption.click();
		}, "Pages");

		this.getWebDriverManager().waitForAnimation();
		getWebDriverManager().usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {
			// check that the edit form was opened
			// step 4
			this.getWebDriverManager().waitForAnimation();
			Assert.assertTrue(
					this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", ".//h1/span")
							.getText().equalsIgnoreCase("foo"));

			// step 5
			this.getWebDriverManager().waitForAnimation();
			this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", editURLButton).click();

			this.getWebDriverManager().waitForAnimation();
			this.getWebDriverManager().getDriver().switchTo().activeElement();
			Assert.assertTrue(
					this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", warningTitle)
							.getText().equalsIgnoreCase("Warning"));

			// step 6
			this.getWebDriverManager().waitForAnimation();
			this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", warningOkButton).click();

			// step 7
			this.getWebDriverManager().waitForAnimation();
			this.getWebDriverManager().getDriver().switchTo().activeElement();
			this.getWebDriverManager().sendText("xpath", filenameInput, "bar");
			this.getWebDriverManager().waitForAnimation();

			// save and close
			this.getWebDriverManager()
					.driverWaitUntilElementIsPresentAndDisplayed("xpath", createFormSaveAndCloseElement)
					.click();

		});
		// Step 8 Expected Output
		Assert.assertTrue(this.getWebDriverManager().getDriver().getCurrentUrl().contains("/studio/site-dashboard"));

		// Step 9
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", fooContentXpath)
				.click();
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().waitUntilSidebarOpens();
		Assert.assertTrue(this.getWebDriverManager().getDriver().getCurrentUrl().contains("page=/bar.html"));

		// Step 10
		this.step10();
	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
