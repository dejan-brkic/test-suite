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
package org.craftercms.studio.test.cases.contenttestcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.NoSuchElementException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.cases.StudioBaseTest;
import org.openqa.selenium.WebElement;

/**
 * 
 * @author Luis Hernandez
 *
 */

// Test Case Studio- Site Content ID:47
public class VerifyThatImageCropWorksProperlyTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String siteDropdownElementXPath;
	private String siteDropdownListElementXPath;
	private String editorialBPSiteId;
	private String janeDoeAuthor;
	private String editRecentlyContentCreated;
	private String createFormFrameElementCss;
	private String cropImageDialogTitle;
	private String cropImageDialogButton;
	private String photoImageInfo;

	private static final Logger logger = LogManager.getLogger(VerifyThatImageCropWorksProperlyTest.class);

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		siteDropdownListElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdownlielement");
		janeDoeAuthor = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sitecontent.janedoeauthor");
		editRecentlyContentCreated = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.edit.option");
		createFormFrameElementCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformframe");
		cropImageDialogTitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sitecontent.cropimagedialogtitle");
		cropImageDialogButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame2.author.cropbutton");
		photoImageInfo = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame2.author.photoinfo");
	}

	public void login() {
		loginPage.loginToCrafter(userName, password);

		driverManager.waitUntilLoginCloses();
	}

	public void goToPreview() {
		logger.info("Going to preview page of site: {}", editorialBPSiteId);
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

	public void step1() {
		this.goToPreview();
	}

	public void step2() {
		this.previewPage.expandItemsTree();
		this.previewPage.expandItemsSubtree();
		this.previewPage.expandAuthorsTree();
	}

	public void rightClickAndSelectEditOption() {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", janeDoeAuthor);
		this.driverManager.contextClick("xpath", janeDoeAuthor, false);
		driverManager.usingContextMenu(() -> {
			WebElement editOption = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
					editRecentlyContentCreated);
			editOption.click();
		}, "Pages");
	}

	@Test(
			priority = 0)
	public void verifyThatImageCropWorksProperlyTest() {

		this.login();

		// Step1
		this.step1();

		// Step2
		this.step2();

		// Step3 and 4
		this.rightClickAndSelectEditOption();

		// Step 5, 6, 7 and 8
		this.driverManager.waitForAnimation();

		driverManager.usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {
			this.driverManager.waitForAnimation();			
			dashboardPage.addAnImageToAnAuthorUsingUploadOption();

			// checking that the crop dialog is displayed and crop the image
			driverManager.getDriver().switchTo().activeElement();
			Assert.assertTrue(this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",cropImageDialogTitle).isDisplayed());
			this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", cropImageDialogButton).click();
			
			// Switch to the iframe
			driverManager.getDriver().switchTo().defaultContent();
			driverManager.getDriver().switchTo()
					.frame(this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("cssSelector",
							".studio-ice-dialog > .bd iframe"));
			this.driverManager.isElementPresentAndClickableBycssSelector(".studio-ice-dialog > .bd iframe");
			this.driverManager.waitForFullExpansionOfTree();
			
			//check if the image was replaced by new one (just cropped image)
			Assert.assertTrue(this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",photoImageInfo).isDisplayed());
			Assert.assertTrue(
					this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",photoImageInfo).getText().contains("testimage.jpg")
					);
			
			// save and closing the edit dialog
			this.driverManager.waitForAnimation();
			
			this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("id", "cstudioSaveAndClose")
					.click();
		});
		this.driverManager.waitUntilSidebarOpens();

	}

}
