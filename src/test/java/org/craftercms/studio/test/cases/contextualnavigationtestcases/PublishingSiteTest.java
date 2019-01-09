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

import org.testng.Assert;

import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;

import org.craftercms.studio.test.cases.StudioBaseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

/**
 * 
 * @author luishernandez
 *
 */

public class PublishingSiteTest extends StudioBaseTest {
	private String userName;
	private String password;
	private String createFormFrameElementCss;
	private String createFormMainTitleElementXPath;
	private String createFormSaveAndCloseElement;
	private String testingContentItem;
	private String topNavStatusIcon;
	private String homeXpath;
	private int numberOfAttemptsForElementsDisplayed;

	private static Logger logger = LogManager.getLogger(PublishingSiteTest.class);

	@BeforeMethod
	public void beforeTest() {

		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		createFormFrameElementCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.createformframe");
		createFormMainTitleElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.createformTitle");
		createFormSaveAndCloseElement = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.saveandclosebutton");
		testingContentItem = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.testingcontentitem");
		topNavStatusIcon = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.statustopbaricon");
		homeXpath = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("general.home");
		this.numberOfAttemptsForElementsDisplayed = Integer.parseInt(constantsPropertiesManager
				.getSharedExecutionConstants().getProperty("crafter.numberofattemptsforelementdisplayed"));

	}

	public void changeBodyToNotRequiredOnEntryContent() {
		previewPage.changeBodyOfEntryContentPageToNotRequired();
	}

	public void createNewContent() {

		// right click to see the the menu

		dashboardPage.rightClickToSeeMenu();

		// Select Entry Content Type

		dashboardPage.clickEntryCT();

		// Confirm the Content Type selected

		dashboardPage.clickOKButton();

		// Switch to the iframe

		driverManager.usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {

			// Set basics fields of the new content created

			logger.info("Set the fields of the new content");

			dashboardPage.setBasicFieldsOfNewContent("Test1", "Testing1");

			// Set the title of main content

			this.driverManager.sendText("xpath", createFormMainTitleElementXPath, "MainTitle");

			// save and close

			logger.info("Click on Save and close button");

			this.driverManager

					.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", createFormSaveAndCloseElement)
					.click();

		});

	}

	public void approveAndPublish() {

		// approve and publish

		previewPage.clickOnApprovePublish();

		// submit

		previewPage.clickOnSubmitButtonOfApprovePublish();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", homeXpath);

	}

	@Test(priority = 0)

	public void publishingSite() {

		// login to application

		loginPage.loginToCrafter(userName, password);

		// Wait for login page to closes

		driverManager.waitUntilLoginCloses();

		// goto preview page

		homePage.goToPreviewPage();

		// select the content type to the test

		changeBodyToNotRequiredOnEntryContent();

		// Switch to the form

		//driverManager.getDriver().navigate().refresh();

		driverManager.getDriver().switchTo().defaultContent();

		// expand pages folder

		dashboardPage.expandPagesTree();

		// expand home content
		this.driverManager.waitUntilPageLoad();
		this.driverManager.waitUntilSidebarOpens();
	

		dashboardPage.expandHomeTree();

		// create a new content

		createNewContent();

		// reload page
		//driverManager.getDriver().navigate().refresh();

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", testingContentItem).click();

		// approve and publish
		approveAndPublish();

		this.driverManager.waitForAnimation();

		// expand pages folder
		dashboardPage.expandPagesTree();

		for (int i = 0; i < numberOfAttemptsForElementsDisplayed; i++) {
			try {
				this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", testingContentItem)
						.click();
				this.driverManager.waitUntilAttributeContains("xpath", topNavStatusIcon, "class", "undefined live");
				break;
			} catch (TimeoutException e) {
				this.driverManager.takeScreenshot("PageNotPublishedOnTopNavBar");
				logger.warn("Content page is not published yet, checking again if it has published icon on top bar");
				driverManager.getDriver().navigate().refresh();
			}
		}

		String elementClassValue = this.driverManager.getDriver().findElement(By.xpath(topNavStatusIcon))
				.getAttribute("class");
		Assert.assertTrue(elementClassValue.contains("undefined live"));

	}

}
