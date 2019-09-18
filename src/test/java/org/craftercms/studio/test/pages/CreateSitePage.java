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
package org.craftercms.studio.test.pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.craftercms.studio.test.utils.UIElementsPropertiesManager;
import org.craftercms.studio.test.utils.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class CreateSitePage {

	private WebDriverManager driverManager;
	private String siteName;
	private String descriptionSite;
	private String blueprintSelect;
	private String reviewAndCreateButton;
	private String additionalDeveloperOptions;
	private String createSiteButton;
	private String cancelButton;
	private String usersOption;
	private String sitesOption;
	private String helpOption;
	private String aboutOption;
	private String documentationOption;
	private String adminDropdownOption;
	private String settingsOption;
	private String pushRepositoryName;
	private String pushRepositoryURL;
	private String pushRepositoryUserName;
	private String pushRepositoryUserPassword;
	private String pushRepositoryToken;
	private String pushRepositoryPrivateKey;
	private String pushBasicAuthenticationOpt;
	private String pushTokenAuthenticationOpt;
	private String pushPrivateKeyAuthenticationOpt;
	private String fromGitRepositoryName;
	private String fromGitRepositoryURL;
	private String fromGitRepositoryUserName;
	private String fromGitRepositoryUserPassword;
	private String fromGitRepositoryToken;
	private String fromGitRepositoryPrivateKey;
	private String fromGitBasicAuthenticationOpt;
	private String fromGitTokenAuthenticationOpt;
	private String fromGitPrivateKeyAuthenticationOpt;
	private String pushToRemoteGitCheckbox;
	private String createSiteFromGitRepoCheckbox;
	private String basicInformationButton;
	private String basicDeveloperOptions;
	private String creatingSpinnerImgCss;
	private int waitForCreateSite = 300;

	/**
	 * 
	 */
	public CreateSitePage(WebDriverManager driverManager, UIElementsPropertiesManager UIElementsPropertiesManager) {
		this.driverManager = driverManager;

		siteName = UIElementsPropertiesManager.getSharedUIElementsLocators().getProperty("create.site_name");
		descriptionSite = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("create.description_site");
		blueprintSelect = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("create.blueprint.select_button");
		reviewAndCreateButton = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("create.review.create_button");
		additionalDeveloperOptions = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("create.additional.developer.options");
		createSiteButton = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("create.create_button");
		cancelButton = UIElementsPropertiesManager.getSharedUIElementsLocators().getProperty("create.cancel_button");
		usersOption = UIElementsPropertiesManager.getSharedUIElementsLocators().getProperty("create.users_option");
		sitesOption= UIElementsPropertiesManager.getSharedUIElementsLocators().getProperty("create.sites_option");
		helpOption = UIElementsPropertiesManager.getSharedUIElementsLocators().getProperty("create_help_option");
		aboutOption = UIElementsPropertiesManager.getSharedUIElementsLocators().getProperty("create_about_option");
		documentationOption = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("create_documentation_option");
		adminDropdownOption = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("create_admin_dropdown_option");
		settingsOption = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("create_settings_option");
		pushRepositoryName = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.push.repositoryname");
		pushRepositoryURL = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.push.repositoryurl");
		pushRepositoryUserName = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.push.repositoryusername");
		pushRepositoryUserPassword = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.push.repositoryuserpassword");
		pushRepositoryToken = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.push.repositorytoken");
		pushRepositoryPrivateKey = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.push.repositoryprivatekey");
		pushBasicAuthenticationOpt = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.push.repositorybasicauthenticationtype");
		pushTokenAuthenticationOpt = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.push.repositorygittokenauthenticationtype");
		pushPrivateKeyAuthenticationOpt = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.push.repositorygitprivatekeyauthenticationtype");
		fromGitRepositoryName = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.fromgit.repositoryname");
		fromGitRepositoryURL = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.fromgit.repositoryurl");
		fromGitRepositoryUserName = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.fromgit.repositoryusername");
		fromGitRepositoryUserPassword = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.fromgit.repositoryuserpassword");
		fromGitRepositoryToken = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.fromgit.repositorytoken");
		fromGitRepositoryPrivateKey = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.fromgit.repositoryprivatekey");
		fromGitBasicAuthenticationOpt = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.fromgit.repositorybasicauthenticationtype");
		fromGitTokenAuthenticationOpt = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.fromgit.repositorygittokenauthenticationtype");
		fromGitPrivateKeyAuthenticationOpt = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.fromgit.repositorygitprivatekeyauthenticationtype");
		pushToRemoteGitCheckbox = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("create.push.remote.git");
		createSiteFromGitRepoCheckbox = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.repositorybasedonremotegitrepo");
		basicInformationButton = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("create.basic.information_button");
		basicDeveloperOptions = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("create.basic.developer.options");
		creatingSpinnerImgCss = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("creating.spinner.img");
	}
	public CreateSitePage setSiteName() {
		driverManager.sendText("xpath", siteName, "testsite" + RandomStringUtils.randomAlphabetic(5).toLowerCase());
		return this;
	}

	// Set site name
	public CreateSitePage setSiteName(String siteName) {
		driverManager.sendText("xpath", this.siteName, siteName);
		return  this;
	}

	public void setSiteNameForSiteIDRestrictions(String siteName) {
		driverManager.sendTextForSiteIDRestrictions("xpath", this.siteName, siteName);
	}

	public CreateSitePage setPushRepositoryName(String repositoryName) {
		driverManager.sendText("xpath", this.pushRepositoryName, repositoryName);
		return this;
	}

	public CreateSitePage setPushRepositoryURL(String repositoryURL) {
		driverManager.sendText("xpath", this.pushRepositoryURL, repositoryURL);
		return this;
	}

	public CreateSitePage setPushRepositoryUserName(String repositoryUserName) {
		driverManager.sendText("xpath", this.pushRepositoryUserName, repositoryUserName);
		return this;
	}

	public CreateSitePage setPushRepositoryUserPassword(String repositoryUserPassword) {
		driverManager.sendText("xpath", this.pushRepositoryUserPassword, repositoryUserPassword);
		return this;
	}

	public CreateSitePage setPushRepositoryToken(String repositoryToken) {
		driverManager.sendText("xpath", this.pushRepositoryToken, repositoryToken);
		return this;
	}

	public CreateSitePage setPushRepositoryPrivateKey(String repositoryPrivateKey) {
		driverManager.sendTextByLineJS("id", this.pushRepositoryPrivateKey, repositoryPrivateKey);
		return this;
	}


	public CreateSitePage setFromGitRepositoryName(String repositoryName) {
		driverManager.sendText("xpath", this.fromGitRepositoryName, repositoryName);
		return this;
	}

	public CreateSitePage setFromGitRepositoryURL(String repositoryURL) {
		driverManager.sendText("xpath", this.fromGitRepositoryURL, repositoryURL);
		return this;
	}

	public CreateSitePage setFromGitRepositoryUserName(String repositoryUserName) {
		driverManager.sendText("xpath", this.fromGitRepositoryUserName, repositoryUserName);
		return this;
	}

	public CreateSitePage setFromGitRepositoryUserPassword(String repositoryUserPassword) {
		driverManager.sendText("xpath", this.fromGitRepositoryUserPassword, repositoryUserPassword);
		return this;
	}

	public CreateSitePage setFromGitRepositoryToken(String repositoryToken) {
		driverManager.sendText("xpath", this.fromGitRepositoryToken, repositoryToken);
		return this;
	}

	public CreateSitePage setFromGitRepositoryPrivateKey(String repositoryPrivateKey) {
		driverManager.sendTextByLineJS("id", this.fromGitRepositoryPrivateKey, repositoryPrivateKey);
		return this;
	}

	// Set description
	public CreateSitePage setDescription(String strDescription) {
		driverManager.sendText("xpath", descriptionSite, strDescription);
		return this;
	}

	// Select blueprint button
	public CreateSitePage blueprintSelect(String blueprintName) {
		driverManager.waitUntilElementIsClickable("xpath", String.format(blueprintSelect,blueprintName)).click();
		return this;
	}

	public CreateSitePage clickReviewAndCreate() {
		driverManager.waitUntilElementIsClickable("xpath", reviewAndCreateButton).click();
		return this;
	}

	public CreateSitePage clickAdditionalDeveloperOptions(){
		driverManager.waitUntilElementIsClickable("xpath", additionalDeveloperOptions).click();
		return this;
	}

	public CreateSitePage selectEmptyBluePrintOption() {
		// select blue empty print
		this.blueprintSelect("Empty Blueprint");
		return this;
	}

	// Press on create site
	public CreateSitePage clickOnCreateButton() {
		this.driverManager.clickElement("id", createSiteButton);
		driverManager.waitUntilElementIsNotDisplayed("cssselector", creatingSpinnerImgCss, waitForCreateSite);
		return this;
	}

	// Press on users option
	public void usersOption() {
		this.driverManager.waitForAnimation();
		WebElement users = this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				usersOption);
		users.click();

	}

	public void clickOnUsersOption() {
		// Press on users option
		this.usersOption();
	}
	
	public void clickOnSitesOption() {
		// Press on sites option
		this.driverManager.waitForAnimation();
		WebElement sites = this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				sitesOption);
		sites.click();
	}

	// Press on Cancel button of the create site process.
	public CreateSitePage cancelButton() {
		this.driverManager.waitUntilElementIsClickable("xpath", cancelButton).click();
		return this;
	}

	public void clickOnCancelButtonOfTheCreateSiteProcess() {
		// Press on Cancel button of the create site.
		this.cancelButton();
	}

	// Press on help option
	public void clickHelp() {
		WebElement users = this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				helpOption);
		users.click();
	}

	public void clickOnHelpOption() {
		// Press on help option
		this.clickHelp();
	}

	// Press on about option
	public void clickAbout() {
		WebElement about = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", aboutOption);
		about.click();
	}

	public void clickOnAboutOption() {
		// Press on about option
		this.clickAbout();
	}

	// Press on documentation option
	public void clickDocumentation() {
		WebElement documentation = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				documentationOption);
		documentation.click();
	}

	public void clickOnDocumentationOption() {
		// Press on documentation option
		this.clickDocumentation();
	}

	// Press on admin option
	public void clickAdmin() {
		WebElement admin = this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				adminDropdownOption);
		admin.click();
	}

	public void clickOnAdminOption() {
		// Press on admin option
		this.clickAdmin();
	}

	// Press on settings option
	public void clickSettings() {
		this.driverManager.isElementPresentAndClickableByXpath(settingsOption);
		WebElement settings = this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				settingsOption);
		settings.click();
	}

	public void clickOnSettingsOption() {
		// Press on settings option
		this.clickSettings();
	}

	public WebDriverManager getDriverManager() {
		return driverManager;
	}

	public void setDriverManager(WebDriverManager driverManager) {
		this.driverManager = driverManager;
	}


	public CreateSitePage selectWebSiteEditorialBluePrintOption() {
		// select blue corporate print
		this.blueprintSelect("Website Editorial Blueprint");
		return this;
	}

	public CreateSitePage selectHeadlessBlogBluePrintOption() {
		// select blue corporate print
		this.blueprintSelect("Headless Blog Blueprint");
		return this;
	}

	public CreateSitePage selectHeadlessStoreBluePrintOption() {
		// select blue corporate print
		this.blueprintSelect("Headless Store Blueprint");
		return this;
	}

	public CreateSitePage selectVideoCenterBluePrintOption() {
		// select blue corporate print
		this.blueprintSelect("Video Center Blueprint");
		return this;
	}

	public CreateSitePage selectPushGitRepoBasicAuthenticationType() {
		this.driverManager.waitUntilElementIsClickable("xpath", pushBasicAuthenticationOpt).click();
		return this;
	}

	public CreateSitePage selectPushGitRepoTokenAuthenticationType() {
		this.driverManager.waitUntilElementIsClickable("xpath", pushTokenAuthenticationOpt).click();
		return this;
	}

	public CreateSitePage selectPushGitRepoPrivateKeyAuthenticationType() {
		this.driverManager.waitUntilElementIsClickable("xpath", pushPrivateKeyAuthenticationOpt).click();
		return this;
	}

	public CreateSitePage selectFromGitRepoBasicAuthenticationType() {
		this.driverManager.waitUntilElementIsClickable("xpath", fromGitBasicAuthenticationOpt).click();
		return this;
	}

	public CreateSitePage selectFromGitRepoTokenAuthenticationType() {
		this.driverManager.waitUntilElementIsClickable("xpath", fromGitTokenAuthenticationOpt).click();
		return this;
	}

	public CreateSitePage selectFromGitRepoPrivateKeyAuthenticationType() {
		this.driverManager.waitUntilElementIsClickable("xpath", fromGitPrivateKeyAuthenticationOpt).click();
		return this;
	}

	public CreateSitePage clickPushSiteToRemoteGitCheckbox() {
		this.driverManager.clickElement("xpath", pushToRemoteGitCheckbox);
		return this;
	}

	public CreateSitePage clickUseRemoteGitRepoSiteCheckbox() {
		this.driverManager.waitUntilElementIsDisplayed("id", createSiteFromGitRepoCheckbox).click();
		return this;
	}

	public CreateSitePage clickBasicInformation() {
		this.driverManager.waitUntilElementIsClickable("xpath", basicInformationButton).click();
		return this;
	}

	public CreateSitePage clickBasicDeveloperOptions() {
		this.driverManager.waitUntilElementIsClickable("xpath", basicDeveloperOptions).click();
		return this;
	}
}