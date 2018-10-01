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
package org.craftercms.studio.test.pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.craftercms.studio.test.utils.UIElementsPropertiesManager;
import org.craftercms.studio.test.utils.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * 
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class CreateSitePage {

	private WebDriverManager driverManager;
	private WebDriver driver;
	private String siteName;
	private String siteID;
	private String descriptionSite;
	private String blueprintCombo;
	private String createSiteButton;
	private String cancelButton;
	private String usersOption;
	private String sitesOption;
	private String helpOption;
	private String aboutOption;
	private String documentationOption;
	private String adminDropdownOption;
	private String settingsOption;
	private String repositoryNameXpath;
	private String repositoryURLXpath;
	private String repositoryUserNameXpath;
	private String repositoryUserPasswordXpath;
	private String repositoryTokenXpath;
	private String repositoryPrivateKeyXpath;
	private String basicAuthenticationOption;
	private String tokenAuthenticationOption;
	private String privateKeyAuthenticationOption;

	/**
	 * 
	 */
	public CreateSitePage(WebDriverManager driverManager, UIElementsPropertiesManager UIElementsPropertiesManager) {
		this.driverManager = driverManager;
		this.driver = this.driverManager.getDriver();

		siteName = UIElementsPropertiesManager.getSharedUIElementsLocators().getProperty("create.site_name");
		siteID = UIElementsPropertiesManager.getSharedUIElementsLocators().getProperty("create.id_name");
		descriptionSite = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("create.description_site");
		blueprintCombo = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("create.blueprint_combo");
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
		repositoryNameXpath = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.repositoryname");
		repositoryURLXpath = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.repositoryurl");
		repositoryUserNameXpath = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.repositoryusername");
		repositoryUserPasswordXpath = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.repositoryuserpassword");
		repositoryTokenXpath = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.repositorytoken");
		repositoryPrivateKeyXpath = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.repositoryprivatekey");
		basicAuthenticationOption = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.repositorybasicauthentication");
		tokenAuthenticationOption = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.repositorygittokenauthenticationtype");
		privateKeyAuthenticationOption = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.repositorygitprivatekeyauthenticationtype");
	}

	public CreateSitePage(WebDriver driver) {

		this.driver = driver;

	}

	// Set site name

	public void setSiteName() {
		driverManager.sendText("xpath", siteName, "testsite" + RandomStringUtils.randomAlphabetic(5).toLowerCase());
	}

	public void fillSiteName() {
		// Set site name
		this.setSiteName();
	}

	// Set site ID
	public void setSiteName(String siteName) {
		driverManager.sendText("xpath", this.siteName, siteName);
	}

	public void setSiteNameForSiteIDRestrictions(String siteName) {
		driverManager.sendTextForSiteIDRestrictions("xpath", this.siteName, siteName);
	}

	public void fillSiteName(String siteName) {
		// Set site name
		this.setSiteName(siteName);
	}

	public void setSiteId(String strSiteID) {
		WebElement idSite = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", siteID);
		idSite.sendKeys(strSiteID);
	}

	public void setRepositoryName(String repositoryName) {
		driverManager.sendText("xpath", this.repositoryNameXpath, repositoryName);
	}

	public void setRepositoryURL(String repositoryURL) {
		driverManager.sendText("xpath", this.repositoryURLXpath, repositoryURL);
	}

	public void setRepositoryUserName(String repositoryUserName) {
		driverManager.sendText("xpath", this.repositoryUserNameXpath, repositoryUserName);
	}

	public void setRepositoryUserPassword(String repositoryUserPassword) {
		driverManager.sendText("xpath", this.repositoryUserPasswordXpath, repositoryUserPassword);
	}

	public void setRepositoryToken(String repositoryToken) {
		driverManager.sendText("xpath", this.repositoryTokenXpath, repositoryToken);
	}

	public void setRepositoryPrivateKey(String repositoryPrivateKey) {
		driverManager.sendText("xpath", this.repositoryPrivateKeyXpath, repositoryPrivateKey);
	}

	public void fillIdSite(String strSiteID) {
		// Set site ID
		this.setSiteId(strSiteID);
	}

	// Set description

	public void setDescription(String strDescription) {
		driverManager.sendText("xpath", descriptionSite, strDescription);
	}

	public void fillDescription(String strDescription) {
		// Set description
		this.setDescription(strDescription);
	}

	// Open blueprint combo

	public void blueprintCombo() {
		WebElement comboBlueprint = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				blueprintCombo);
		comboBlueprint.click();
	}

	public void openBlueprintCombo() {
		// Open blueprint combo
		this.blueprintCombo();
	}

	// select blue empty print
	public void selectEmptyBlueprint() {
		WebElement bluePrintCombo = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("id", "blueprint");
		Select select = new Select(bluePrintCombo);
		select.selectByVisibleText("Empty");
	}

	public void selectEmptyBluePrintOption() {
		// select blue empty print
		this.selectEmptyBlueprint();
	}

	// Press on create site
	public void createButton() {
		WebElement createButton = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				createSiteButton);
		createButton.click();
	}

	public void clickOnCreateSiteButton() {
		// Press on create site
		this.createButton();
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
	public void cancelButton() {
		WebElement createButton = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", cancelButton);
		createButton.click();
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

	public void createRandomSite() {
		// Filling the name of site
		this.fillSiteName();
		// Filling the description of the site
		this.fillDescription("Description");
		// Open blueprint combo
		// this.openBlueprintCombo();
		// Select empty blueprint
		this.selectEmptyBlueprint();
		// Click on Create button
		this.clickOnCreateSiteButton();
	}

	public WebDriverManager getDriverManager() {
		return driverManager;
	}

	public void setDriverManager(WebDriverManager driverManager) {
		this.driverManager = driverManager;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public void selectWebSiteEditorialBluePrintOption() {
		// select blue corporate print
		this.selectWebSiteEditorialBluePrint();
	}

	private void selectWebSiteEditorialBluePrint() {
		WebElement bluePrintCombo = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("id", "blueprint");
		Select select = new Select(bluePrintCombo);
		select.selectByVisibleText("Website_editorial");
	}

	public void selectHeadlessBlogBluePrintOption() {
		// select blue corporate print
		this.selectHeadlessBlogBluePrint();
	}

	private void selectHeadlessBlogBluePrint() {
		WebElement bluePrintCombo = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("id", "blueprint");
		Select select = new Select(bluePrintCombo);
		select.selectByVisibleText("Headless_blog");
	}

	public void selectHeadlessStoreBluePrintOption() {
		// select blue corporate print
		this.selectHeadlessStoreBluePrint();
	}

	public void selectHeadlessStoreBluePrint() {
		WebElement bluePrintCombo = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("id", "blueprint");
		Select select = new Select(bluePrintCombo);
		select.selectByVisibleText("Headless_store");
	}

	public void selectGitRepoBasicAutheticationType() {
		WebElement basicAuthenticationGitRepoOption = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", basicAuthenticationOption);
		basicAuthenticationGitRepoOption.click();
	}

	public void selectGitRepoTokenAutheticationType() {
		WebElement tokenAuthenticationGitRepoOption = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", tokenAuthenticationOption);
		tokenAuthenticationGitRepoOption.click();
	}
	
	public void selectGitRepoPrivateKeyAutheticationType() {
		WebElement privaetKeyAuthenticationGitRepoOption = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", privateKeyAuthenticationOption);
		privaetKeyAuthenticationGitRepoOption.click();
	}

}