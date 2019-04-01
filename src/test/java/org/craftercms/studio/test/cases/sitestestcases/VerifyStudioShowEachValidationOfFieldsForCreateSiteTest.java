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
	private String basicAuthenticationInput;
	private String validationMessageForRepositoryUserName;
	private String validationMessageForRepositoryUserPassword;
	private String gitTokenAuthenticationInput;
	private String validationMessageForRepositoryToken;
	private String privateKeyAuthenticationInput;
	private String validationMessageForRepositoryPrivateKey;

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
		basicAuthenticationInput = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.push.repositorybasicauthenticationtype");
		gitTokenAuthenticationInput = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.push.repositorygittokenauthenticationtype");
		privateKeyAuthenticationInput = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.push.repositorygitprivatekeyauthenticationtype");
		validationMessageForRepositoryUserName = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.repositoryusernamevalidation");
		validationMessageForRepositoryUserPassword = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.repositoryuserpasswordvalidation");
		validationMessageForRepositoryToken = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.repositorytokenvalidation");
		validationMessageForRepositoryPrivateKey = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.repositoryprivatekeyvalidation");
	}

	public void step3() {
		// Click on description to show the validations
		this.driverManager.waitUntilElementIsClickable("xpath", createSiteDescriptionId).click();

		// Assert Id site is required.
		WebElement siteID = this.driverManager.waitUntilElementIsDisplayed("xpath",
				validationMessageXpath);

		Assert.assertTrue(siteID.isDisplayed(), "ERROR: site ID is not required");
		Assert.assertTrue(siteID.getText().contains("Site Id is required."));

	}

	public void step4() {
		String testingSiteIDWithUpperCasesAndSpaces = "TestingUPPERCASE SPACE";

		// Filling the name of site
		createSitePage.setSiteNameForSiteIDRestrictions(testingSiteIDWithUpperCasesAndSpaces);
		
		this.driverManager.waitForAnimation();
		WebElement siteID = this.driverManager.waitUntilElementIsClickable("xpath", siteId);

		String testingSiteID=((testingSiteIDWithUpperCasesAndSpaces.toLowerCase()).replace(" ", ""));
		String siteIDText=siteID.getAttribute("value");
		
		Assert.assertTrue(siteIDText.equals(testingSiteID));
	}

	public void step5() {
		String testingSiteIDSpecialCharacters = "testing!!$%";
		// Filling the name of site
		createSitePage.setSiteNameForSiteIDRestrictions(testingSiteIDSpecialCharacters);

		this.driverManager.waitForAnimation();
		WebElement siteID = this.driverManager.waitUntilElementIsDisplayed("xpath", siteId);

		String testingSiteID=((testingSiteIDSpecialCharacters.toLowerCase()).replace("!!$%", ""));
		String siteIDText=siteID.getAttribute("value");
		
		Assert.assertTrue(siteIDText.equals(testingSiteID));
	}

	public void step6() {
		createSitePage.clickAdditionalDeveloperOptions()
				.clickPushSiteToRemoteGitCheckbox();
	}

	public void step7() {
		createSitePage.setPushRepositoryName("");

		createSitePage.setPushRepositoryURL("testurl");

		WebElement validationOnRepoName = this.driverManager.waitUntilElementIsDisplayed("xpath",
				validationMessageForRepositoryName);

		Assert.assertTrue(validationOnRepoName.isDisplayed());
		Assert.assertTrue(validationOnRepoName.getText().contains("Remote Git Repository Name is required."));
	}

	public void step8() {
		createSitePage.setPushRepositoryName("testreponame")
				.setPushRepositoryURL("")
				.setPushRepositoryName("testreponame");

		WebElement validationOnRepoURL = this.driverManager.waitUntilElementIsDisplayed("xpath",
				validationMessageForRepositoryURL);

		Assert.assertTrue(validationOnRepoURL.isDisplayed());
		Assert.assertTrue(validationOnRepoURL.getText().contains("Remote Git Repository URL is required."));
	}

	public void step9() {
		createSitePage.setPushRepositoryName("testreponame")
				.setPushRepositoryURL("testrepourl")
				.selectPushGitRepoBasicAuthenticationType();
	}

	public void step10() {
		createSitePage.setPushRepositoryUserName("")
				.setPushRepositoryUserPassword("testpassword");

		WebElement validationOnRepoUserName = this.driverManager.waitUntilElementIsDisplayed("xpath",
				validationMessageForRepositoryUserName);

		Assert.assertTrue(validationOnRepoUserName.isDisplayed());
		Assert.assertTrue(validationOnRepoUserName.getText().contains("Remote Git Repository Username is required."));
	}

	public void step11() {
		createSitePage.setPushRepositoryUserName("testusername")
				.setPushRepositoryUserPassword("")
				.setPushRepositoryUserName("testusername");

		WebElement validationOnRepoUserPassword = this.driverManager
				.waitUntilElementIsDisplayed("xpath", validationMessageForRepositoryUserPassword);

		Assert.assertTrue(validationOnRepoUserPassword.isDisplayed());
		Assert.assertTrue(
				validationOnRepoUserPassword.getText().contains("Remote Git Repository Password is required."));
	}

	public void step12() {

		createSitePage.setPushRepositoryUserName("testusername")
				.setPushRepositoryUserPassword("testuserpassword");

		WebElement tokenAuthenticationInputElement = this.driverManager
				.waitUntilElementIsClickable("xpath", gitTokenAuthenticationInput);
		tokenAuthenticationInputElement.click();
	}

	public void step13() {

		createSitePage.setPushRepositoryToken("");

		createSitePage.setPushRepositoryUserName("testusername");

		WebElement validationOnRepoToken = this.driverManager.waitUntilElementIsDisplayed("xpath",
				validationMessageForRepositoryToken);

		Assert.assertTrue(validationOnRepoToken.isDisplayed());
		Assert.assertTrue(validationOnRepoToken.getText().contains("Remote Git Repository Token is required."));
	}

	public void step14() {

		createSitePage.setPushRepositoryUserName("testusername");

		createSitePage.setPushRepositoryToken("testtoken");

		WebElement privateKeyAuthenticationInputElement = this.driverManager
				.waitUntilElementIsClickable("xpath", privateKeyAuthenticationInput);
		privateKeyAuthenticationInputElement.click();
	}

	public void step15() {

		createSitePage.setPushRepositoryPrivateKey("");

		createSitePage.setPushRepositoryName("testreponame");

		WebElement validationOnRepoPrivateKey = this.driverManager.waitUntilElementIsDisplayed("xpath",
				validationMessageForRepositoryPrivateKey);

		Assert.assertTrue(validationOnRepoPrivateKey.isDisplayed());
		Assert.assertTrue(
				validationOnRepoPrivateKey.getText().contains("Remote Git Repository Private Key is required."));
	}


	public void step16() {
		createSitePage.cancelButton();
		homePage.clickOnCreateSiteButton();
		createSitePage.clickUseRemoteGitRepoSiteCheckbox();
		// Click on description to show the validations
		this.driverManager.waitUntilElementIsClickable("xpath", createSiteDescriptionId).click();

		// Assert Id site is required.
		WebElement siteID = this.driverManager.waitUntilElementIsDisplayed("xpath",
				validationMessageXpath);

		Assert.assertTrue(siteID.isDisplayed(), "ERROR: site ID is not required");
		Assert.assertTrue(siteID.getText().contains("Site Id is required."));
	}

	public void step17() {
		String testingSiteIDWithUpperCasesAndSpaces = "TestingUPPERCASE SPACE";

		// Filling the name of site
		createSitePage.setSiteNameForSiteIDRestrictions(testingSiteIDWithUpperCasesAndSpaces);

		this.driverManager.waitForAnimation();
		WebElement siteID = this.driverManager.waitUntilElementIsClickable("xpath", siteId);

		String testingSiteID=((testingSiteIDWithUpperCasesAndSpaces.toLowerCase()).replace(" ", ""));
		String siteIDText=siteID.getAttribute("value");

		Assert.assertTrue(siteIDText.equals(testingSiteID));
	}

	public void step18() {
		String testingSiteIDSpecialCharacters = "testing!!$%";
		// Filling the name of site
		createSitePage.setSiteNameForSiteIDRestrictions(testingSiteIDSpecialCharacters);

		this.driverManager.waitForAnimation();
		WebElement siteID = this.driverManager.waitUntilElementIsDisplayed("xpath", siteId);

		String testingSiteID=((testingSiteIDSpecialCharacters.toLowerCase()).replace("!!$%", ""));
		String siteIDText=siteID.getAttribute("value");

		Assert.assertTrue(siteIDText.equals(testingSiteID));
	}

	public void step19(){
		createSitePage.clickBasicDeveloperOptions();
	}

	public void step20() {
		createSitePage.setFromGitRepositoryName("");

		createSitePage.setFromGitRepositoryURL("testurl");

		WebElement validationOnRepoName = this.driverManager.waitUntilElementIsDisplayed("xpath",
				validationMessageForRepositoryName);

		Assert.assertTrue(validationOnRepoName.isDisplayed());
		Assert.assertTrue(validationOnRepoName.getText().contains("Remote Git Repository Name is required."));
	}

	public void step21() {
		createSitePage.setFromGitRepositoryName("testreponame")
				.setFromGitRepositoryURL("")
				.setFromGitRepositoryName("testreponame");

		WebElement validationOnRepoURL = this.driverManager.waitUntilElementIsDisplayed("xpath",
				validationMessageForRepositoryURL);

		Assert.assertTrue(validationOnRepoURL.isDisplayed());
		Assert.assertTrue(validationOnRepoURL.getText().contains("Remote Git Repository URL is required."));
	}

	public void step22() {
		createSitePage.setFromGitRepositoryName("testreponame")
				.setFromGitRepositoryURL("testrepourl")
				.selectFromGitRepoBasicAuthenticationType();
	}

	public void step23() {
		createSitePage.setPushRepositoryUserName("")
				.setPushRepositoryUserPassword("testpassword");

		WebElement validationOnRepoUserName = this.driverManager.waitUntilElementIsDisplayed("xpath",
				validationMessageForRepositoryUserName);

		Assert.assertTrue(validationOnRepoUserName.isDisplayed());
		Assert.assertTrue(validationOnRepoUserName.getText().contains("Remote Git Repository Username is required."));
	}

	public void step24() {

	}

	@Test(priority = 0)
	public void verifyStudioShowEachValidationOfFieldsForCreateSiteTest() {

		// login to application
		loginPage.loginToCrafter(userName, password);

		driverManager.waitUntilLoginCloses();

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

		//
	}
}
