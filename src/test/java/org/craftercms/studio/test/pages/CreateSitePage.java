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
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

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
	private String reviewButton;
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
	private String repositoryURL;
	private String repositoryUserName;
	private String repositoryUserPassword;
	private String repositoryToken;
	private String repositoryPrivateKey;
	private String basicAuthenticationOpt;
	private String tokenAuthenticationOpt;
	private String privateKeyAuthenticationOpt;
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
	private String creatingGearsImgCss;
	private String summaryCreationStrategyXpath;
	private String summarySiteInfoXpath;
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
		reviewButton = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("create.review_button");
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
		repositoryURL = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.push.repositoryurl");
		repositoryUserName = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.push.repositoryusername");
		repositoryUserPassword = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.push.repositoryuserpassword");
		repositoryToken = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.push.repositorytoken");
		repositoryPrivateKey = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.push.repositoryprivatekey");
		basicAuthenticationOpt = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.push.repositorybasicauthenticationtype");
		tokenAuthenticationOpt = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.push.repositorygittokenauthenticationtype");
		privateKeyAuthenticationOpt = UIElementsPropertiesManager.getSharedUIElementsLocators()
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
		creatingGearsImgCss = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("creating.gears.img");
		summaryCreationStrategyXpath = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("create_summary_creation_strategy");
		summarySiteInfoXpath = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("create_summary_site_info");
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

	public CreateSitePage setRepositoryURL(String repositoryURL) {
		driverManager.sendText("xpath", this.repositoryURL, repositoryURL);
		return this;
	}

	public CreateSitePage setRepositoryUserName(String repositoryUserName) {
		driverManager.sendText("xpath", this.repositoryUserName, repositoryUserName);
		return this;
	}

	public CreateSitePage setRepositoryUserPassword(String repositoryUserPassword) {
		driverManager.sendText("xpath", this.repositoryUserPassword, repositoryUserPassword);
		return this;
	}

	public CreateSitePage setRepositoryToken(String repositoryToken) {
		driverManager.sendText("xpath", this.repositoryToken, repositoryToken);
		return this;
	}

	public CreateSitePage setRepositoryPrivateKey(String repositoryPrivateKey) {
		driverManager.sendTextByLineJS("id", this.repositoryPrivateKey, repositoryPrivateKey);
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
		driverManager.sendText("xpath", descriptionSite, strDescription, false);
		return this;
	}

	// Select blueprint button
	public CreateSitePage blueprintSelect(String blueprintName) {
		driverManager.scrollIntoViewXpathElement(String.format(blueprintSelect, blueprintName));
		driverManager.clickElement("xpath", String.format(blueprintSelect, blueprintName));
		return this;
	}

	public CreateSitePage clickReview() {
		driverManager.waitUntilElementIsClickable("xpath", reviewButton).click();
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
		this.driverManager.clickElement("xpath", createSiteButton);
		Assert.assertTrue(driverManager.isTextInStudio("Please wait while your site is being created."));
		driverManager.waitUntilElementIsNotDisplayed("cssselector", creatingGearsImgCss, waitForCreateSite);
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

	public CreateSitePage confirmCancelCreateSite() {
		this.driverManager.clickLinkByText("Ok");
		return this;
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
		driverManager.clickElement("xpath", adminDropdownOption);
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

	public CreateSitePage selectRemoteGitRepositorySite() {
		this.blueprintSelect("Remote Git Repository");
		return this;
	}

	public CreateSitePage selectGitRepoBasicAuthenticationType() {
		this.driverManager.clickElement("xpath", basicAuthenticationOpt);
		return this;
	}

	public CreateSitePage selectGitRepoTokenAuthenticationType() {
		this.driverManager.clickElement("xpath", tokenAuthenticationOpt);
		return this;
	}

	public CreateSitePage selectGitRepoPrivateKeyAuthenticationType() {
		this.driverManager.clickElement("xpath", privateKeyAuthenticationOpt);
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

	private String getSummaryStrings(String selector, String value) {
		String newSelector = selector + String.format("//*[contains(text(),'%s')]/..", value);
		WebElement element = driverManager.findElement("xpath", newSelector);
		return driverManager.getNodeText(element).substring(1);
	}

	public String getSummaryCreationStrategy() {
		return driverManager.waitUntilElementIsDisplayed("xpath", summaryCreationStrategyXpath)
				.findElement(By.xpath(".//p")).getText();
	}

	public String getSummaryBlueprint() {
		return getSummaryStrings(summaryCreationStrategyXpath, "Blueprint");

	}

	public Boolean isSummaryCreationStrategyBtnClickable() {
		WebElement creationSummary = driverManager.waitUntilElementIsDisplayed("xpath", summaryCreationStrategyXpath)
				.findElement(By.xpath(".//button"));
		return creationSummary.isDisplayed() && creationSummary.isEnabled();
	}

	public Boolean isSummarySiteInfoBtnClickable() {
		WebElement siteInfo = driverManager.waitUntilElementIsDisplayed("xpath", summarySiteInfoXpath)
				.findElement(By.xpath(".//button"));
		return siteInfo.isDisplayed() && siteInfo.isEnabled();
	}

	public String getSummarySiteId() {
		return getSummaryStrings(summarySiteInfoXpath, "Site ID");
	}

	public String getSummaryDescription() {
		return getSummaryStrings(summarySiteInfoXpath, "Description");
	}

	public String getSummaryGitRepoUrl() {
		return getSummaryStrings(summarySiteInfoXpath, "Git Repo URL");
	}

	public String getSummaryGitRemoteName() {
		return getSummaryStrings(summarySiteInfoXpath, "Git Remote Name");
	}

	public String getSummaryGitBranch() {
		return getSummaryStrings(summarySiteInfoXpath, "Git Branch");
	}


	public String getSummarySandboxBranch() {
		return getSummaryStrings(summarySiteInfoXpath, "Sandbox Branch");
	}
}