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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.utils.UIElementsPropertiesManager;
import org.craftercms.studio.test.utils.WebDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class LoginPage {

	private WebDriverManager driverManager;
	private String userNameXpath;
	private String passwordXpath;
	private String loginXpath;
	private String loginLanguageSelector;
	private String userName;
	private String password;
	private static Logger logger = LogManager.getLogger(LoginPage.class);

	public LoginPage(WebDriverManager driverManager,
			UIElementsPropertiesManager UIElementsPropertiesManager) {
		this.driverManager = driverManager;
		this.driverManager.openConnection();

		userNameXpath = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("login.username");
		passwordXpath = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("login.password");
		loginXpath = UIElementsPropertiesManager.getSharedUIElementsLocators().getProperty("login.login");
		loginLanguageSelector = UIElementsPropertiesManager.getSharedUIElementsLocators().getProperty("login.languageselector");
		userName = getDriverManager().getConstantsPropertiesManager().getSharedExecutionConstants().getProperty("crafter.username");
		password = getDriverManager().getConstantsPropertiesManager().getSharedExecutionConstants().getProperty("crafter.password");

	}
	// Set user name in textbox
	public void setUserName(String strUserName) {
		WebElement userCrafter = this.driverManager.waitUntilElementIsClickable("xpath",
				userNameXpath);
		userCrafter.clear();
		userCrafter.sendKeys(strUserName);
	}

	// Set password in password textbox
	public void setPassword(String strPassword) {
		WebElement pwdCrafter = this.driverManager.waitUntilElementIsClickable("xpath",
				passwordXpath);
		pwdCrafter.clear();
		pwdCrafter.sendKeys(strPassword);

	}

	// Click on login button
	public void clickLogin() {
		driverManager.clickElement("xpath", loginXpath);
	}

	// Login to crafter
	public void loginToCrafter() {
		logger.info("Login into Crafter with admin user");
		setUserName(userName);
		setPassword(password);
		clickLogin();
		driverManager.waitUntilLoginCloses();
	}

	// Login to crafter
	public void loginToCrafter(String strUserName, String strPasword) {
		logger.info("Login into Crafter");
		// Fill user name
		this.setUserName(strUserName);
		// Fill password
		this.setPassword(strPasword);
		// Click Login button
		this.driverManager.waitForAnimation();
		this.clickLogin();

		// Wait for login page to close
		this.driverManager.waitUntilLoginCloses();
	}

	public void loginToCrafterWithWrongCredentials(String strUserName, String strPasword) {
		logger.info("Login into Crafter");
		// Fill user name
		this.setUserName(strUserName);
		// Fill password
		this.setPassword(strPasword);
		// Click Login button
		this.clickLogin();
	}

	public WebDriverManager getDriverManager() {
		return driverManager;
	}

	public void setDriverManager(WebDriverManager driverManager) {
		this.driverManager = driverManager;
	}

	public void setLanguage(String lang) {
		Select select = new Select(getDriverManager().findElement("xpath", loginLanguageSelector));
		select.selectByVisibleText(lang);
	}

	public String getSelectedLanguage() {
		Select select = new Select(getDriverManager().findElement("xpath", loginLanguageSelector));
		return select.getFirstSelectedOption().getText();
	}
}