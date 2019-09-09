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

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
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
	private String janeDoeAuthor;
	private String editRecentlyContentCreated;
	private String createFormFrameElementCss;
	private String cropImageDialogTitle;
	private String cropImageDialogButton;
	private String photoImageInfo;
	private String zoomInButtonCss;
	private String zoomOutButtonCss;
	private String resetButtonCss;

	private static final Logger logger = LogManager.getLogger(VerifyThatImageCropWorksProperlyTest.class);

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String siteId, String blueprint) {
		apiTestHelper.createSite(siteId, "", blueprint);
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
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
		zoomInButtonCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame2.crop.image.zoom.in.css");
		zoomOutButtonCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame2.crop.image.zoom.out.css");
		resetButtonCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame2.crop.image.reset.css");
	}

	public void login() {
		loginPage.loginToCrafter(userName, password);

		getWebDriverManager().waitUntilLoginCloses();
	}

	public void goToPreview(String siteId) {
		logger.info("Going to preview page of site: {}", siteId);
		// go to preview page
		homePage.goToPreviewPage(siteId);
		getWebDriverManager().clickElement("xpath", siteDropdownElementXPath);

	}

	public void step2() {
		this.previewPage.expandItemsTree();
		this.previewPage.expandItemsSubtree();
		this.previewPage.expandAuthorsTree();
	}

	public void rightClickAndSelectEditOption() {
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", janeDoeAuthor);
		this.getWebDriverManager().contextClick("xpath", janeDoeAuthor, false);
		getWebDriverManager().usingContextMenu(() -> {
			WebElement editOption = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
					editRecentlyContentCreated);
			editOption.click();
		}, "Pages");
	}

	@Parameters({"testId"})
	@Test()
	public void verifyThatImageCropWorksProperlyTest(String testId) {

		this.login();

		// Step1
		this.goToPreview(testId);

		// Step2
		this.step2();

		// Step3 and 4
		this.rightClickAndSelectEditOption();

		// Step 5, 6, 7 and 8
		this.getWebDriverManager().waitForAnimation();

		getWebDriverManager().usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {
			this.getWebDriverManager().waitForAnimation();
			dashboardPage.addAnImageToAnAuthorUsingUploadOption();

			// checking that the crop dialog is displayed and crop the image
			getWebDriverManager().getDriver().switchTo().activeElement();
			Assert.assertTrue(getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",cropImageDialogTitle).isDisplayed());
			Assert.assertTrue(getWebDriverManager().waitUntilElementIsClickable("cssselector", zoomInButtonCss).isDisplayed());
			Assert.assertTrue(getWebDriverManager().waitUntilElementIsClickable("cssselector", zoomOutButtonCss).isDisplayed());
			Assert.assertTrue(getWebDriverManager().waitUntilElementIsClickable("cssselector", resetButtonCss).isDisplayed());
			getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", cropImageDialogButton).click();
			
			// Switch to the iframe
			getWebDriverManager().getDriver().switchTo().defaultContent();
			getWebDriverManager().getDriver().switchTo()
					.frame(this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("cssSelector",
							".studio-ice-dialog > .bd iframe"));
			this.getWebDriverManager().isElementPresentAndClickableBycssSelector(".studio-ice-dialog > .bd iframe");
			this.getWebDriverManager().waitForFullExpansionOfTree();
			
			//check if the image was replaced by new one (just cropped image)
			Assert.assertTrue(this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",photoImageInfo).isDisplayed());
			Assert.assertTrue(
					this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",photoImageInfo).getText().contains("testimage.jpg")
					);
			
			// save and closing the edit dialog
			this.getWebDriverManager().waitForAnimation();
			
			this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("id", "cstudioSaveAndClose")
					.click();
		});
		this.getWebDriverManager().waitUntilSidebarOpens();
	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
