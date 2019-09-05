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
package org.craftercms.studio.test.cases.contextualnavigationtestcases;

import org.craftercms.studio.test.cases.StudioBaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;

/**
 * @author luishernandez
 *
 */
public class VerifyThatCompareHistoryWorksProperly extends StudioBaseTest {

	private String userName;
	private String password;
	private String historyFirstItemCheckbBox;
	private String historySecondItemCheckbBox;
	private String differencesDialogId;
	private String differencesDialogRemovedMarkXpath;
	private String differencesDialogAddedMarkXpath;
	private String createFormFrameElementCss;
	private String createFormTitleElementXPath;
	private String actionsHeaderXpath;
	private String siteDropdownElementXPath;
	private String firstItemRevertXpath;
	private String historyRevertButtonXpath;
	private static Logger logger = LogManager.getLogger(VerifyThatCompareHistoryWorksProperly.class);

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String testId, String blueprint) {
		apiTestHelper.createSite(testId, "", blueprint);
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");

		createFormFrameElementCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformframe");
		createFormTitleElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformtitle");
		historyFirstItemCheckbBox = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.crafter3loadtest.historydialog.firstitemcheckbox");
		historySecondItemCheckbBox = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.crafter3loadtest.historydialog.seconditemcheckbox");
		differencesDialogId = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.differencedialogid");
		differencesDialogRemovedMarkXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.crafter3loadtest.differencedialog_removedmark");
		differencesDialogAddedMarkXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.crafter3loadtest.differencedialog_addedmark");
		actionsHeaderXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.historydialogactionsheader");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		firstItemRevertXpath = uiElementsPropertiesManager.getSharedDataOfExecutionLocators()
				.getProperty("complexscenarios.crafter3loadtest.historydialog.initialcommittrevertbutton");
		historyRevertButtonXpath = uiElementsPropertiesManager.getSharedDataOfExecutionLocators()
				.getProperty("general.history.revert.button");
	}

	public void loginAndGoToSiteContentPagesStructure(String testId) {
		// login to application
		loginPage.loginToCrafter(userName, password);

		// Wait for login page to close
		getWebDriverManager().waitUntilLoginCloses();
		this.getWebDriverManager().waitForAnimation();
		// go to preview page
		homePage.goToPreviewPage(testId);
		getWebDriverManager().clickElement("xpath", siteDropdownElementXPath);
	}

	public void editSelectedContent() {

		getWebDriverManager().usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {
			// Typing new text on title text field
			getWebDriverManager().sendText("xpath", createFormTitleElementXPath,
					RandomStringUtils.randomAlphabetic(5).toLowerCase());

			// Save and close button.
			dashboardPage.clickSaveClose();
		});
	}

	public void compareTwoVersionsOfAContentPage() {

		// Switch to the iframe
		getWebDriverManager().getDriver().switchTo().defaultContent();
		getWebDriverManager().getDriver().switchTo().activeElement();

		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager().waitUntilPageLoad();

		try {
			this.getWebDriverManager().waitUntilElementIsDisplayed("xpath", actionsHeaderXpath);
		} catch (TimeoutException e) {
			this.getWebDriverManager().takeScreenshot("HistoryDialogNotCompletedRendered");
			logger.warn("History dialog is not completely rendered, and the buttons can't be clicked");
		}

		// Checking the first row version
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", historyFirstItemCheckbBox)
				.click();

		// Checking the second row version
		this.getWebDriverManager().waitForAnimation();
		this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", historySecondItemCheckbBox)
				.click();

		// click on Compare button
		this.getWebDriverManager().waitForAnimation();
		dashboardPage.clickCompareButton();

		// switching to the compare frame
		this.getWebDriverManager().waitForAnimation();
		getWebDriverManager().usingCrafterDialog("cssSelector", differencesDialogId, () -> {
			this.getWebDriverManager().waitForAnimation();
			// checkin if is present the removed-red-highlight text
			Assert.assertTrue(getWebDriverManager().isElementPresentByXpath(differencesDialogRemovedMarkXpath));

			// checkin if is present the added-green-highlight text
			Assert.assertTrue(getWebDriverManager().isElementPresentByXpath(differencesDialogAddedMarkXpath));

			// click on close button
			dashboardPage.clickCloseButton();
		});

		getWebDriverManager().waitForNumberElementsToBe("xpath", historyRevertButtonXpath, 2);
		getWebDriverManager().clickElement("xpath", firstItemRevertXpath);
		getWebDriverManager().waitForNumberElementsToBe("xpath", historyRevertButtonXpath, 3);
	}

	public void editHome() {
		
		this.getWebDriverManager().waitForAnimation();

		dashboardPage.clickHomeTree();

		dashboardPage.clickOnContextualNavigationEditOption();

		this.editSelectedContent();

	}

	public void clickOnHistoryOption() {
		getWebDriverManager().waitUntilSidebarOpens();

		dashboardPage.clickHomeTree();

		dashboardPage.clickOnContextualNavigationHistoryOption();

		this.getWebDriverManager().waitForAnimation();
	}

	public void compareVersionsOfHome() {
	
		this.compareTwoVersionsOfAContentPage();
	}

	public void testScenario(String testId) {
		// login and go to dashboard page, later open the content site (site
		// dropdown panel)
		this.loginAndGoToSiteContentPagesStructure(testId);
		this.editHome();
		this.getWebDriverManager().getDriver().navigate().refresh();
		this.clickOnHistoryOption();
		this.compareVersionsOfHome();

	}

	@Parameters({"testId"})
	@Test()
	public void verifyThatCompareHistoryWorksProperly(String testId) {
		this.testScenario(testId);
	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
