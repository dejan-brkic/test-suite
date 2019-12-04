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

// Test Case Studio- Sites ID:4
public class VerifyStudioShowEachValidationOfFieldsForCreateSiteTest extends StudioBaseTest {

	private String validationMessageXpath;
	private String siteId;
	private String validationMessageForRepositoryURL;
	private String validationMessageForRepositoryUserName;
	private String validationMessageForRepositoryUserPassword;
	private String validationMessageForRepositoryToken;
	private String validationMessageForRepositoryPrivateKey;
	private String siteIdNumericExpectedMsg;
	private String siteIdRequiredExpectedMsg;
	private String gitRepoUrlExpectedMsg;
	private String gitRepoUsernameExpectedMsg;
	private String gitRepoPasswordExpectedMsg;
	private String gitRepoTokenExpectedMsg;
	private String gitRepoPrivateKeyExpectedMsg;
	private String testingSiteIDWithUpperCasesAndSpaces;
	private String testingSiteIDSpecialCharacters;
	private String testingSiteIdWithDash;
	private String testingSiteIdWithUnderscore;

	@BeforeMethod
	public void beforeTest() {
		validationMessageXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.createsitevalidationmessage");
		siteId = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("create.site_name");
		validationMessageForRepositoryURL = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.repositoryurlvalidation");
		validationMessageForRepositoryUserName = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.repositoryusernamevalidation");
		validationMessageForRepositoryUserPassword = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.repositoryuserpasswordvalidation");
		validationMessageForRepositoryToken = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.repositorytokenvalidation");
		validationMessageForRepositoryPrivateKey = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.repositoryprivatekeyvalidation");
		siteIdNumericExpectedMsg = "Site names may not start with zeros, dashes (-) or underscores (_).";
		siteIdRequiredExpectedMsg = "Site ID is required.";
		gitRepoUrlExpectedMsg = "The git repository URL to push.";
		gitRepoPasswordExpectedMsg = "Password is required.";
		gitRepoUsernameExpectedMsg  = "Username is required.";
		gitRepoTokenExpectedMsg = "Token is required.";
		gitRepoPrivateKeyExpectedMsg = "Private Key is required.";
		testingSiteIDWithUpperCasesAndSpaces = "TestingUPPERCASE SPACE";
		testingSiteIDSpecialCharacters = "testing!!$%";
		testingSiteIdWithDash = "-testing";
		testingSiteIdWithUnderscore = "_testing";
	}

	public void checkSiteNameRestrictions(String siteToType, String errorMessage) {
		createSitePage.setSiteNameForSiteIDRestrictions(siteToType);
		String currentErrorValue =  getWebDriverManager().waitUntilElementIsDisplayed(
				"xpath", validationMessageXpath).getText();
		Assert.assertEquals(currentErrorValue, errorMessage, "Expected to have error: " +
				errorMessage + ". But found: " + currentErrorValue);
	}

	public void checkSiteIdRename(String siteToType, String expectedSiteId) {
		createSitePage.setSiteNameForSiteIDRestrictions(siteToType);
		String currentSiteId =  getWebDriverManager().waitUntilElementIsDisplayed(
				"xpath", siteId).getAttribute("value");
		Assert.assertEquals(currentSiteId, expectedSiteId, "Expected to have the site id: " +
				expectedSiteId + ". But found: " + currentSiteId);
	}

	public void step3() {
		// Click on description to show the validations
		createSitePage.clickReview();
		// Assert Id site is required.
		Assert.assertEquals(getWebDriverManager().getText("xpath", validationMessageXpath), siteIdRequiredExpectedMsg, "Site ID message is incorrect");
		checkSiteNameRestrictions("01", siteIdNumericExpectedMsg);
		checkSiteNameRestrictions("0", siteIdNumericExpectedMsg);
		checkSiteIdRename("1", "1");
		checkSiteNameRestrictions(testingSiteIdWithUnderscore, siteIdNumericExpectedMsg);
		checkSiteNameRestrictions(testingSiteIdWithDash, siteIdNumericExpectedMsg);

	}

	public void step4() {
		checkSiteIdRename(testingSiteIDWithUpperCasesAndSpaces,
				testingSiteIDWithUpperCasesAndSpaces.toLowerCase().replace(" ", ""));
	}

	public void step5() {
		checkSiteIdRename(testingSiteIDSpecialCharacters,
				testingSiteIDSpecialCharacters.toLowerCase().replace("!!$%", ""));
	}

	public void step6() {
		createSitePage.clickPushSiteToRemoteGitCheckbox()
				.clickReview();
		Assert.assertEquals(getWebDriverManager().getText("xpath", validationMessageForRepositoryURL),
				gitRepoUrlExpectedMsg, "Git Repo URL message is incorrect");
	}

	public void step7() {
		createSitePage.setRepositoryURL("https://github.com/craftercms/craftercms.git")
				.selectGitRepoBasicAuthenticationType()
				.clickReview();
		Assert.assertEquals(getWebDriverManager().waitUntilElementIsDisplayed("xpath", validationMessageForRepositoryUserName).getText(),
				gitRepoUsernameExpectedMsg, "Git Repo Username message is incorrect");
		Assert.assertEquals(getWebDriverManager().waitUntilElementIsDisplayed("xpath", validationMessageForRepositoryUserPassword).getText(),
				gitRepoPasswordExpectedMsg, "Git Repo Password message is incorrect");
	}

	public void step8() {
		createSitePage.selectGitRepoTokenAuthenticationType()
				.clickReview();
		Assert.assertEquals(getWebDriverManager().getText("xpath", validationMessageForRepositoryUserName),
				gitRepoUsernameExpectedMsg, "Git Repo Username message is incorrect");
		Assert.assertEquals(getWebDriverManager().getText("xpath", validationMessageForRepositoryToken),
				gitRepoTokenExpectedMsg, "Git Repo Token message is incorrect");
	}


	public void step9() {
		createSitePage.selectGitRepoPrivateKeyAuthenticationType()
				.clickReview();
		Assert.assertEquals(getWebDriverManager().getText("xpath", validationMessageForRepositoryPrivateKey),
				gitRepoPrivateKeyExpectedMsg, "Git Private Key message is incorrect");

	}

	public void step10() {
		createSitePage.cancelButton()
				.confirmCancelCreateSite();
		homePage.clickOnCreateSiteButton();
		createSitePage.selectRemoteGitRepositorySite()
				.clickReview();
		step3();
	}

	public void step11() {
		step4();
	}

	public void step12() {
		step5();
	}

	public void step13step14() {
		step7();
	}

	public void step15() {
		step8();
	}

	public void step16() {
		step9();
	}


	@Test(priority = 0)
	public void verifyStudioShowEachValidationOfFieldsForCreateSiteTest() {
		// login to application
		loginPage.loginToCrafter();
		// Click on the create site button
		homePage.clickOnCreateSiteButton();
		createSitePage.selectEmptyBluePrintOption();
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

		// Step 9
		step9();

		// Step 10
		step10();

		// Step 11
		step11();

		// Step 12
		step12();

		// Step 13 Step 14
		step13step14();

		// Step 15
		step15();

		// Step 16
		step16();
	}
}
