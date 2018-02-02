package org.craftercms.studio.test.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.utils.UIElementsPropertiesManager;
import org.craftercms.studio.test.utils.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class UsersPage {

	private static final Logger logger = LogManager.getLogger(UsersPage.class);

	private WebDriverManager driverManager;
	private WebDriver driver;
	private String deleteUserOption;
	private String newUserOption;
	private String saveNewUserOption;
	private String deleteUserOptionNonAdmin;
	private String editUserOption;
	private String usersPageTitle;
	private String crafterLogo;

	private String deleteYesButtonXpath;

	private String deleteUsersRowsXpath;

	private String deleteNonAdminUserIconXpath;

	private String sitesOptionXpath;

	public UsersPage(WebDriverManager driverManager, UIElementsPropertiesManager UIElementsPropertiesManager) {
		this.driverManager = driverManager;
		this.driver = this.driverManager.getDriver();

		deleteUserOption = UIElementsPropertiesManager.getSharedUIElementsLocators().getProperty("users.delete_option");
		newUserOption = UIElementsPropertiesManager.getSharedUIElementsLocators().getProperty("users.new_user");
		saveNewUserOption = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("users.save_new_user");
		deleteUserOptionNonAdmin = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("users.delete_option_nonadminuser");
		editUserOption = UIElementsPropertiesManager.getSharedUIElementsLocators().getProperty("users.edit_option");
		usersPageTitle = UIElementsPropertiesManager.getSharedUIElementsLocators().getProperty("users.page_title");
		crafterLogo = UIElementsPropertiesManager.getSharedUIElementsLocators().getProperty("users.crafterlogo");
		deleteYesButtonXpath = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.deleteyesbutton");
		deleteUsersRowsXpath = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.deleteusersrows");
		deleteNonAdminUserIconXpath = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.deletenonadminrow");
		sitesOptionXpath = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.homesites");

	}

	public UsersPage(WebDriver driver) {
		this.driver = driver;
	}

	// Try to delete the user connected

	public void clickDeleteOption() {
		logger.info("Deleting user");
		WebElement previewLink = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				deleteUserOption);
		previewLink.click();
	}

	// Try to delete the user connected
	public void clickOnDeleteUser() {
		// Try to delete the user connected
		this.clickDeleteOption();
	}

	// Click on New User Button
	public void clickNewUserButton() {
		WebElement newUserButton = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				newUserOption);
		newUserButton.click();
	}

	// Click on New User Button
	public void clickOnNewUser() {
		// Click on New User Button
		this.clickNewUserButton();
	}

	// Click on Save New User Button
	public void clickSaveNewUserButton() {
		WebElement saveNewUser = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				saveNewUserOption);
		saveNewUser.click();
	}

	// Click on Save New User Button
	public void clickOnSaveNewUser() {
		// Click on Save New User Button
		this.clickSaveNewUserButton();
	}

	// Delete User
	public void clickDeleteOptionCreated() {
		WebElement deleteIcon = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				deleteUserOptionNonAdmin);
		deleteIcon.click();
	}

	// Delete User
	public void clickOnDeleteUserCreated() {
		// Delete User
		this.clickDeleteOptionCreated();
	}

	// edit User
	public void clickEditOptionCreated() {
		WebElement edit = this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				editUserOption);
		edit.click();
	}

	// edit User
	public void clickOnEditUserCreated() {
		// edit User
		this.clickEditOptionCreated();
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

	public Boolean isUsersPageTitlePresent() {
		return this.driverManager.isElementPresentByXpath(usersPageTitle);
	}

	public void clickOnCrafterLogo() {
		WebElement crafterLogoWebElement = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				crafterLogo);
		crafterLogoWebElement.click();
	}

	public void deleteAllUsersExceptAdmin() {
		List<WebElement> usersListitem = this.driverManager.getDriver()
				.findElements(By.xpath(deleteUsersRowsXpath));

		for (int i = 1; i < usersListitem.size(); i++) {

			this.driverManager.waitForAnimation();
			this.driverManager.waitUntilPageLoad();

			// get the delete button element
			WebElement element = this.driverManager.waitUntilElementIsClickable("xpath",
					deleteNonAdminUserIconXpath);
			// click on the delete button
			element.click();

			// confirm and wait
			this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", deleteYesButtonXpath).click();
			
			this.driverManager.waitUntilDeleteSiteModalCloses();
			this.driverManager.waitForAnimation();
			this.driverManager.waitUntilElementIsRemoved(element);
		}
	}

	public void clickOnSitesOption() {
		WebElement siteOptionWebElement = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				sitesOptionXpath);
		siteOptionWebElement.click();
	}
}