/*
 * Copyright (C) 2007-2018 Crafter Software Corporation. All Rights Reserved.
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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
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
	private String siteDropdownListElementXPath;
	private String siteDropdownElementXPath;
	private static Logger logger = LogManager.getLogger(VerifyThatCompareHistoryWorksProperly.class);

	@BeforeMethod
	public void beforeTest() {

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
		siteDropdownListElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdownlielement");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");

	}

	public void loginAndGoToSiteContentPagesStructure() {
		// login to application
		loginPage.loginToCrafter(userName, password);

		// Wait for login page to close
		driverManager.waitUntilLoginCloses();
		this.driverManager.waitForAnimation();
		// go to preview page
		homePage.goToPreviewPage();
		if (this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", siteDropdownElementXPath)
				.isDisplayed())
			if (!(this.driverManager.waitUntilElementIsPresent("xpath", siteDropdownListElementXPath)
					.getAttribute("class").contains("site-dropdown-open")))
				this.driverManager
						.driverWaitUntilElementIsPresentAndDisplayed("xpath", siteDropdownElementXPath)
						.click();
			else
				throw new NoSuchElementException(
						"Site creation process is taking too long time and the element was not found");
	}

	public void editSelectedContent() {

		driverManager.usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {
			// Typing new text on title text field
			driverManager.sendText("xpath", createFormTitleElementXPath,
					RandomStringUtils.randomAlphabetic(5).toLowerCase());

			// Save and close button.
			dashboardPage.clickSaveClose();
		});
	}

	public void compareTwoVersionsOfAContentPage() {

		// Switch to the iframe
		driverManager.getDriver().switchTo().defaultContent();
		driverManager.getDriver().switchTo().activeElement();

		this.driverManager.waitForAnimation();
		this.driverManager.waitUntilPageLoad();

		try {
			this.driverManager.waitUntilElementIsDisplayed("xpath", actionsHeaderXpath);
		} catch (TimeoutException e) {
			this.driverManager.takeScreenshot("HistoryDialogNotCompletedRendered");
			logger.warn("History dialog is not completely rendered, and the buttons can't be clicked");
		}

		// Checking the first row version
		this.driverManager.waitForAnimation();
		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", historyFirstItemCheckbBox)
				.click();

		// Checking the second row version
		this.driverManager.waitForAnimation();
		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", historySecondItemCheckbBox)
				.click();

		// click on Compare button
		this.driverManager.waitForAnimation();
		dashboardPage.clickCompareButton();

		// switching to the compare frame
		this.driverManager.waitForAnimation();
		driverManager.usingCrafterDialog("cssSelector", differencesDialogId, () -> {
			this.driverManager.waitForAnimation();
			// checkin if is present the removed-red-highlight text
			Assert.assertTrue(driverManager.isElementPresentByXpath(differencesDialogRemovedMarkXpath));

			// checkin if is present the added-green-highlight text
			Assert.assertTrue(driverManager.isElementPresentByXpath(differencesDialogAddedMarkXpath));

			// click on close button
			dashboardPage.clickCloseButton();
		});

	}

	public void editHome() {
		
		this.driverManager.waitForAnimation();

		dashboardPage.clickHomeTree();

		dashboardPage.clickOnContextualNavigationEditOption();

		this.editSelectedContent();

	}

	public void clickOnHistoryOption() {
		driverManager.waitUntilSidebarOpens();

		dashboardPage.clickHomeTree();

		dashboardPage.clickOnContextualNavigationHistoryOption();

		this.driverManager.waitForAnimation();
	}

	public void compareVersionsOfHome() {
	
		this.compareTwoVersionsOfAContentPage();
	}

	public void testScenario() {
		// login and go to dashboard page, later open the content site (site
		// dropdown panel)
		this.loginAndGoToSiteContentPagesStructure();
		// expand pages folder
		dashboardPage.expandPagesTree();
		this.editHome();
		this.driverManager.getDriver().navigate().refresh();
		this.clickOnHistoryOption();
		this.compareVersionsOfHome();

	}

	@Test(
			priority = 0,
			sequential = true)
	public void verifyThatCompareHistoryWorksProperly() {
		this.testScenario();
	}

}
