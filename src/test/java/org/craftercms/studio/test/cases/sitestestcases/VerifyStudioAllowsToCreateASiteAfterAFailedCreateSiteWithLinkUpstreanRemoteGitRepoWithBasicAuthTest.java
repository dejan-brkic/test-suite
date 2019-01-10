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
package org.craftercms.studio.test.cases.sitestestcases;

import org.craftercms.studio.test.cases.StudioBaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author luishernandez
 *
 */

// Test Case Studio- Sites ID:18
public class VerifyStudioAllowsToCreateASiteAfterAFailedCreateSiteWithLinkUpstreanRemoteGitRepoWithBasicAuthTest
		extends StudioBaseTest {

	private String userName;
	private String password;
	private String siteId;
	private String siteDropdownElementXPath;
	private String gitUserName;
	private String gitPassword;
	private String gitRepositoryURL;
	private String pushToBareRepoInput;
	private String notificationTitle;
	private String notificationText;
	private String notificationError;
	private String notificationClose;

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		gitUserName = constantsPropertiesManager.getSharedExecutionConstants()
				.getProperty("crafter.gitrepository.username");
		gitPassword = constantsPropertiesManager.getSharedExecutionConstants()
				.getProperty("crafter.gitrepository.password");
		gitRepositoryURL = constantsPropertiesManager.getSharedExecutionConstants()
				.getProperty("crafter.baregitrepository.url");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		pushToBareRepoInput = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.repositorypushtoremotebare");
		notificationTitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.notificationdialog.title");
		notificationText = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.notificationdialog.text");
		notificationError = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.notificationdialog.error");
		notificationClose = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.notificationdialog.closebutton");
		siteId = "testingtargetsiteforbasicauth";
	}

	public void step2() {
		this.clickOnCreateSiteButton();
	}

	public void step3() {
		// Filling the name of site
		createSitePage.setSiteName(siteId);
	}

	public void step4() {
		this.homePage.clickOnLinkToUpstreamRemoteGitRepository();
	}

	public void step5() {
		createSitePage.setRepositoryName("origin");
	}

	public void step6() {
		createSitePage.setRepositoryURL(gitRepositoryURL + "non-valid");
	}

	public void step7() {
		createSitePage.selectGitRepoBasicAutheticationType();

		createSitePage.setRepositoryUserName(gitUserName);

		createSitePage.setRepositoryUserPassword(gitPassword);
	}

	public void step8() {
		WebElement pushRemoteBareRepoInputElement = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", pushToBareRepoInput);
		pushRemoteBareRepoInputElement.click();
	}

	public void step9() {
		// Select website blueprint
		createSitePage.selectWebSiteEditorialBluePrintOption();
	}

	public void step10() {
		// Click on Create button
		createSitePage.clickOnCreateSiteButton();

		this.driverManager.waitForAnimation();

		String notificationTitleText = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", notificationTitle).getText();

		while (!notificationTitleText.equalsIgnoreCase("Notification")) {
			this.driverManager.waitForAnimation();
			notificationTitleText = this.driverManager
					.driverWaitUntilElementIsPresentAndDisplayed("xpath", notificationTitle).getText();
		}

		notificationTitleText = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", notificationTitle).getText();
		String notificationFirstText = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", notificationText).getText();
		String notificationSecondText = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", notificationError).getText();

		Assert.assertTrue("Notification".equals(notificationTitleText));
		Assert.assertTrue("Unable to create site. Please contact your system administrator."
				.equals(notificationFirstText));
		Assert.assertTrue("Error: Remote repository not found".equals(notificationSecondText));
	}

	public void step11() {
		this.clickOnCloseNotificationButton();
	}
	
	private void clickOnCloseNotificationButton() {
		this.driverManager.isElementPresentAndClickableByXpath(notificationClose);
		WebElement closeButton = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", notificationClose);
		closeButton.click();
	}

	public void step12() {
		this.driverManager.waitForAnimation();
		this.clickOnCreateSiteButton();
	}

	public void step13() {
		// Filling the name of site
		createSitePage.setSiteName(siteId);
	}

	public void step14() {
		this.homePage.clickOnLinkToUpstreamRemoteGitRepository();
	}

	public void step15() {
		createSitePage.setRepositoryName("origin");
	}

	public void step16() {
		createSitePage.setRepositoryURL(gitRepositoryURL);
	}

	public void step17() {
		createSitePage.selectGitRepoBasicAutheticationType();

		createSitePage.setRepositoryUserName(gitUserName);

		createSitePage.setRepositoryUserPassword(gitPassword);
	}

	public void step18() {
		WebElement pushRemoteBareRepoInputElement = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", pushToBareRepoInput);
		pushRemoteBareRepoInputElement.click();
	}

	public void step19() {
		// Select website blueprint
		createSitePage.selectWebSiteEditorialBluePrintOption();
	}
	
	public void step20() {
		// Click on Create button
		createSitePage.clickOnCreateSiteButton();

		this.driverManager.waitForAnimation();
		this.driverManager.waitUntilCreateSiteModalCloses();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				siteDropdownElementXPath);

		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", siteDropdownElementXPath)
				.isDisplayed());
	}

	public void clickOnCreateSiteButton() {
		// Click on the create site button
		homePage.clickOnCreateSiteButton();
	}

	@Test(
			priority = 0)
	public void verifyStudioAllowsToCreateASiteAfterAFailedCreateSiteWithLinkUpstreanRemoteGitRepoWithBasicAuthTest() {
		this.testScenario();
	}

	public void testScenario() {
		// login to application
		loginPage.loginToCrafter(userName, password);

		driverManager.waitUntilLoginCloses();

		// Step 2
		step2();

		// Step 3
		step3();

		// Step 4
		step4();

		// Step 5
		step5();

		// Step 6
		step6();

		// Step 7
		step7();

		// Step 8
		step8();

		// Step9
		step9();

		// Step 10
		step10();
		
		//Step 11
		step11();
		
		//Step 12
		step12();
		
		//Step 13
		step13();
		
		//Step 14
		step14();
		
		//Step 15
		step15();
		
		//Step 16
		step16();
		
		//Step 17
		step17();
		
		//Step 18
		step18();

		//Step 19
		step19();
		
		//Step 20
		step20();
	}

}
