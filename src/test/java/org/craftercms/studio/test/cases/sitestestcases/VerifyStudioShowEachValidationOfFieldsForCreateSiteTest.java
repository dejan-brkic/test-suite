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

	private String userName;
	private String password;
	private String createSiteDescriptionId;
	private String validationMessageXpath;
	private String siteId;
	private String validationMessageForRepositoryName;
	private String validationMessageForRepositoryURL;
	private String validationMessageForRepositoryUserName;
	private String validationMessageForRepositoryUserPassword;
	private String validationMessageForRepositoryToken;
	private String validationMessageForRepositoryPrivateKey;
	private String siteIdNumericExpectedMsg;
	private String siteIdRequiredExpectedMsg;
	private String gitRepoNameExpectedMsg;
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
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		createSiteDescriptionId = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.createsitedescription");
		validationMessageXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.createsitevalidationmessage");
		siteId = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("create.site_name");
		validationMessageForRepositoryName = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.repositorynamevalidation");
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
		siteIdNumericExpectedMsg = "If site id is numeric, it can't start with 0";
		siteIdRequiredExpectedMsg = "Site Id is required.";
		gitRepoNameExpectedMsg = "Remote Git Repository Name is required.";
		gitRepoUrlExpectedMsg = "Remote Git Repository URL is required.";
		gitRepoPasswordExpectedMsg = "Remote Git Repository Password is required.";
		gitRepoUsernameExpectedMsg  = "Remote Git Repository Username is required.";
		gitRepoTokenExpectedMsg = "Remote Git Repository Token is required.";
		gitRepoPrivateKeyExpectedMsg = "Remote Git Repository Private Key is required.";
		testingSiteIDWithUpperCasesAndSpaces = "TestingUPPERCASE SPACE";
		testingSiteIDSpecialCharacters = "testing!!$%";
		testingSiteIdWithDash = "-testing";
		testingSiteIdWithUnderscore = "_testing";
	}

	public void checkSiteNameRestrictions(String siteToType, String errorMessage) {
		createSitePage.setSiteNameForSiteIDRestrictions(siteToType);
		String currentErrorValue =  getWebDriverManager().waitUntilElementIsDisplayed(
				"xpath",validationMessageXpath).getText();
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
		getWebDriverManager().waitUntilElementIsClickable("xpath", createSiteDescriptionId).click();

		// Assert Id site is required.
		WebElement siteID = getWebDriverManager().waitUntilElementIsDisplayed("xpath", validationMessageXpath);

		Assert.assertTrue(siteID.isDisplayed(), "ERROR: site ID is not required");
		Assert.assertTrue(siteID.getText().contains(siteIdRequiredExpectedMsg));
		checkSiteNameRestrictions("01", siteIdNumericExpectedMsg);
		checkSiteNameRestrictions("0", siteIdNumericExpectedMsg);
		checkSiteIdRename("1", "1");
		checkSiteIdRename(testingSiteIdWithUnderscore, testingSiteIdWithUnderscore.replace("_", ""));
		checkSiteIdRename(testingSiteIdWithDash, testingSiteIdWithDash.replace("-", ""));

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
		createSitePage.clickAdditionalDeveloperOptions()
				.clickPushSiteToRemoteGitCheckbox();
	}

	public void step7() {
		createSitePage.setPushRepositoryName("");

		createSitePage.setPushRepositoryURL("testurl");

		WebElement validationOnRepoName = this.getWebDriverManager().waitUntilElementIsDisplayed("xpath",
				validationMessageForRepositoryName);

		Assert.assertTrue(validationOnRepoName.isDisplayed());
		Assert.assertTrue(validationOnRepoName.getText().contains(gitRepoNameExpectedMsg));
	}

	public void step8() {
		createSitePage.setPushRepositoryName("testreponame")
				.setPushRepositoryURL("")
				.setPushRepositoryName("testreponame");

		WebElement validationOnRepoURL = this.getWebDriverManager().waitUntilElementIsDisplayed("xpath",
				validationMessageForRepositoryURL);

		Assert.assertTrue(validationOnRepoURL.isDisplayed());
		Assert.assertTrue(validationOnRepoURL.getText().contains(gitRepoUrlExpectedMsg));
	}

	public void step9() {
		createSitePage.setPushRepositoryName("testreponame")
				.setPushRepositoryURL("testrepourl")
				.selectPushGitRepoBasicAuthenticationType();
	}

	public void step10() {
		createSitePage.setPushRepositoryUserName("")
				.setPushRepositoryUserPassword("testpassword");

		WebElement validationOnRepoUserName = this.getWebDriverManager().waitUntilElementIsDisplayed("xpath",
				validationMessageForRepositoryUserName);

		Assert.assertTrue(validationOnRepoUserName.isDisplayed());
		Assert.assertTrue(validationOnRepoUserName.getText().contains(gitRepoUsernameExpectedMsg));
	}

	public void step11() {
		createSitePage.setPushRepositoryUserName("testusername")
				.setPushRepositoryUserPassword("")
				.setPushRepositoryUserName("testusername");

		WebElement validationOnRepoUserPassword = this.getWebDriverManager()
				.waitUntilElementIsDisplayed("xpath", validationMessageForRepositoryUserPassword);

		Assert.assertTrue(validationOnRepoUserPassword.isDisplayed());
		Assert.assertTrue(
				validationOnRepoUserPassword.getText().contains(gitRepoPasswordExpectedMsg));
	}

	public void step12() {
		createSitePage.setPushRepositoryUserName("testusername")
				.setPushRepositoryUserPassword("testuserpassword")
				.selectPushGitRepoTokenAuthenticationType();
	}

	public void step13() {
		createSitePage.setPushRepositoryToken("")
				.setPushRepositoryUserName("testusername");

		WebElement validationOnRepoToken = this.getWebDriverManager().waitUntilElementIsDisplayed("xpath",
				validationMessageForRepositoryToken);

		Assert.assertTrue(validationOnRepoToken.isDisplayed());
		Assert.assertTrue(validationOnRepoToken.getText().contains(gitRepoTokenExpectedMsg));
	}

	public void step14() {
		createSitePage.setPushRepositoryUserName("testusername")
				.setPushRepositoryToken("testtoken")
				.selectPushGitRepoPrivateKeyAuthenticationType();
	}

	public void step15() {
		createSitePage.setPushRepositoryPrivateKey("")
				.setPushRepositoryName("testreponame");

		WebElement validationOnRepoPrivateKey = this.getWebDriverManager().waitUntilElementIsDisplayed("xpath",
				validationMessageForRepositoryPrivateKey);

		Assert.assertTrue(validationOnRepoPrivateKey.isDisplayed());
		Assert.assertTrue(
				validationOnRepoPrivateKey.getText().contains(gitRepoPrivateKeyExpectedMsg));
	}


	public void step16() {
		createSitePage.cancelButton();
		homePage.clickOnCreateSiteButton();
		createSitePage.clickUseRemoteGitRepoSiteCheckbox()
				.clickBasicInformation();
		// Click on description to show the validations
		this.getWebDriverManager().waitUntilElementIsClickable("xpath", createSiteDescriptionId).click();

		// Assert Id site is required.
		WebElement siteID = this.getWebDriverManager().waitUntilElementIsDisplayed("xpath",
				validationMessageXpath);

		Assert.assertTrue(siteID.isDisplayed(), "ERROR: site ID is not required");
		Assert.assertTrue(siteID.getText().contains(siteIdRequiredExpectedMsg));
		checkSiteNameRestrictions("01", siteIdNumericExpectedMsg);
		checkSiteNameRestrictions("0", siteIdNumericExpectedMsg);
		checkSiteIdRename("1", "1");
		checkSiteIdRename(testingSiteIdWithUnderscore, testingSiteIdWithUnderscore.replace("_", ""));
		checkSiteIdRename(testingSiteIdWithDash, testingSiteIdWithDash.replace("-", ""));;
	}

	public void step17() {
		step4();
	}

	public void step18() {
		step5();
	}

	public void step19(){
		createSitePage.clickBasicDeveloperOptions();
	}

	public void step20() {
		createSitePage.setFromGitRepositoryName("");

		createSitePage.setFromGitRepositoryURL("testurl");

		WebElement validationOnRepoName = this.getWebDriverManager().waitUntilElementIsDisplayed("xpath",
				validationMessageForRepositoryName);

		Assert.assertTrue(validationOnRepoName.isDisplayed());
		Assert.assertTrue(validationOnRepoName.getText().contains(gitRepoNameExpectedMsg));
	}

	public void step21() {
		createSitePage.setFromGitRepositoryName("testreponame")
				.setFromGitRepositoryURL("")
				.setFromGitRepositoryName("testreponame");

		WebElement validationOnRepoURL = this.getWebDriverManager().waitUntilElementIsDisplayed("xpath",
				validationMessageForRepositoryURL);

		Assert.assertTrue(validationOnRepoURL.isDisplayed());
		Assert.assertTrue(validationOnRepoURL.getText().contains(gitRepoUrlExpectedMsg));
	}

	public void step22() {
		createSitePage.setFromGitRepositoryName("testreponame")
				.setFromGitRepositoryURL("testrepourl")
				.selectFromGitRepoBasicAuthenticationType();
	}

	public void step23() {
		createSitePage.setFromGitRepositoryUserName("")
				.setFromGitRepositoryUserPassword("testpassword");

		WebElement validationOnRepoUserName = this.getWebDriverManager().waitUntilElementIsDisplayed("xpath",
				validationMessageForRepositoryUserName);

		Assert.assertTrue(validationOnRepoUserName.isDisplayed());
		Assert.assertTrue(validationOnRepoUserName.getText().contains(gitRepoUsernameExpectedMsg));
	}

	public void step24() {
		createSitePage.setFromGitRepositoryUserName("testusername")
				.setFromGitRepositoryUserPassword("")
				.setFromGitRepositoryUserName("testusername");

		WebElement validationOnRepoUserPassword = this.getWebDriverManager()
				.waitUntilElementIsDisplayed("xpath", validationMessageForRepositoryUserPassword);

		Assert.assertTrue(validationOnRepoUserPassword.isDisplayed());
		Assert.assertTrue(
				validationOnRepoUserPassword.getText().contains(gitRepoPasswordExpectedMsg));
	}

	public void step25() {
		createSitePage.setFromGitRepositoryUserName("testusername")
				.setFromGitRepositoryUserPassword("testuserpassword")
				.selectFromGitRepoTokenAuthenticationType();
	}

	public void step26(){
		createSitePage.setFromGitRepositoryToken("")
				.setFromGitRepositoryUserName("testusername");

		WebElement validationOnRepoToken = this.getWebDriverManager().waitUntilElementIsDisplayed("xpath",
				validationMessageForRepositoryToken);

		Assert.assertTrue(validationOnRepoToken.isDisplayed());
		Assert.assertTrue(validationOnRepoToken.getText().contains(gitRepoTokenExpectedMsg));
	}

	public void step27() {
		createSitePage.setFromGitRepositoryUserName("testusername")
				.setFromGitRepositoryToken("testtoken")
				.selectFromGitRepoPrivateKeyAuthenticationType();
	}

	public void step28() {
		createSitePage.setFromGitRepositoryPrivateKey("")
				.setFromGitRepositoryName("testreponame");

		WebElement validationOnRepoPrivateKey = this.getWebDriverManager().waitUntilElementIsDisplayed("xpath",
				validationMessageForRepositoryPrivateKey);

		Assert.assertTrue(validationOnRepoPrivateKey.isDisplayed());
		Assert.assertTrue(
				validationOnRepoPrivateKey.getText().contains(gitRepoPrivateKeyExpectedMsg));

	}

	@Test(priority = 0)
	public void verifyStudioShowEachValidationOfFieldsForCreateSiteTest() {

		// login to application
		loginPage.loginToCrafter(userName, password);

		getWebDriverManager().waitUntilLoginCloses();

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

		// Step 13
		step13();

		// Step 14
		step14();

		// Step 15
		step15();

		// Step 16
		step16();

		// Step 17
		step17();

		// Step 18
		step18();

		// Step 19
		step19();

		// Step 20
		step20();

		// Step 21
		step21();

		// Step 22
		step22();

		// Step 23
		step23();

		// Step 24
		step24();

		// Step 25
		step25();

		// Step 26
		step26();

		// Step 27
		step27();

		// Step 26
		step28();
	}
}
