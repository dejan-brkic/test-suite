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

import org.craftercms.studio.test.utils.UIElementsPropertiesManager;
import org.craftercms.studio.test.utils.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class AccountManagementPage {

	private WebDriverManager driverManager;
	private String currentPassword;
	private String newPassword;
	private String confirmPassword;
	private String submitButton;
	private String accountManagementTitle;
	
	public AccountManagementPage(WebDriverManager driverManager,
			UIElementsPropertiesManager UIElementsPropertiesManager) {

		this.driverManager = driverManager;
		currentPassword = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("accountManagement.currentPass");
		newPassword = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("accountManagement.newPass");
		confirmPassword = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("accountManagement.confirmPass");
		submitButton = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("accountManagement.submitButton");
		accountManagementTitle = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("accountManagement.pageTitle");
	}

	// Set the current pass
	public void setCurrentPassword(String strCurrentPass) {
		WebElement currentPass = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed( "xpath",
				currentPassword);
		currentPass.sendKeys(strCurrentPass);

	}

	// Set the new pass
	public void setNewPassword(String strNewPassword) {
		WebElement newPass = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed( "xpath", newPassword);
		newPass.sendKeys(strNewPassword);

	}

	// Set the new pass confirmation
	public void setConfirmNewPassword(String strConfNewPassword) {
		WebElement confPass = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed( "xpath", confirmPassword);
		confPass.sendKeys(strConfNewPassword);
	}

	// Click on submit
	public void clickSubmit() {
		WebElement submitbtn = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed( "xpath",
				submitButton);
		submitbtn.click();
	}

	// change the password
	public void changeUserPassword(String strCurrentPass, String strNewPassword, String strConfNewPassword) {
		// Fill current pass
		this.setCurrentPassword(strCurrentPass);
		// Fill new pass
		this.setNewPassword(strNewPassword);
		// confirm new pass
		this.setConfirmNewPassword(strConfNewPassword);
		// Click Login button
		this.clickSubmit();
		
	}

	public WebDriverManager getDriverManager() {
		return driverManager;
	}

	public void setDriverManager(WebDriverManager driverManager) {
		this.driverManager = driverManager;
	}
	
	public boolean isAccountManagementTitlePresent() {
		return this.driverManager.isElementPresentByXpath(accountManagementTitle);
	}

}