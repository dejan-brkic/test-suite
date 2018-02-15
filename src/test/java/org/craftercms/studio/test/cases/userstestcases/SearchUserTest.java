package org.craftercms.studio.test.cases.userstestcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import org.craftercms.studio.test.cases.StudioBaseTest;


/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class SearchUserTest extends StudioBaseTest {


	private String userName;
	private String password;
	private String newUserFirstNameId;
	private String newUserLastNameId;
	private String newUserEmailId;
	private String newUserUserNameId;
	private String newUserPasswordId;
	private String newUserPasswordVerificationId;
	private String userSearchXpath;
	private String newUserUserNameXpath;
	private String searchResultUserNameXpath;
	private String deleteYesButtonXpath;
	private String usersRowsXpath;

	@BeforeMethod
	public void beforeTest() {
		
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");

		newUserFirstNameId = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.firstname");
		newUserLastNameId = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.lastname");
		newUserEmailId = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("general.users.email");
		newUserUserNameId = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.username");
		newUserPasswordId = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.password");
		newUserPasswordVerificationId = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.passwordVerification");
		userSearchXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.userssearchinput");
		searchResultUserNameXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.newuserusernamecell");
		newUserUserNameXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.usernamecreated");
		deleteYesButtonXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.deleteyesbutton");
		usersRowsXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.usersrows");
	}

	public void createUser() {

		// click On Users option
		createSitePage.clickOnUsersOption();

		// click on new user button
		usersPage.clickOnNewUser();

		// Follow the form
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", newUserFirstNameId).sendKeys("Name");

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", newUserLastNameId).sendKeys("Last Name");

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", newUserEmailId)
				.sendKeys("email@email.com");

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", newUserUserNameId).sendKeys("username");

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", newUserPasswordId).sendKeys("password");

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", newUserPasswordVerificationId)
				.sendKeys("password");

		// Save Button
		usersPage.clickOnSaveNewUser();
	}

	public void searchUsers() {

		// Search user recently created
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", userSearchXpath).sendKeys("username");

		// Assert to search is properly
		String searchUsername = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", searchResultUserNameXpath).getText();
		Assert.assertEquals(searchUsername, "username", "ERROR: searched username is not displayed");

		// Search user admin

		// Cleaning search field
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", userSearchXpath).clear();

		// Search admin
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", userSearchXpath).sendKeys("admin");

		// Assert to search is properly

		String searchAdminUser = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", searchResultUserNameXpath).getText();

		Assert.assertEquals(searchAdminUser, "admin", "ERROR: admin user is not displayed");

	}

	@Test(priority = 0)

	public void searchUser() {

		// login to applicatioN
		loginPage.loginToCrafter(userName, password);
		
		//Wait for login page to close
		driverManager.waitUntilLoginCloses();

		// create new user
		createUser();

		// Assert new users created is present
		WebElement newUserCreated = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				newUserUserNameXpath);

		Assert.assertTrue(newUserCreated.isDisplayed());

		// search users
		searchUsers();
		
		// Cleaning search field
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", userSearchXpath).clear();

		// Click on delete user
		driverManager.getDriver().navigate().refresh();
		usersPage.clickOnDeleteUserCreated();

		// Confirmation to delete user connected
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", deleteYesButtonXpath).click();

		// Assert new users created is deleted

		Assert.assertTrue(this.driverManager.elementHasChildsByXPath(usersRowsXpath));

		List<WebElement> usersList = this.driverManager.getDriver().findElements(By.xpath(usersRowsXpath));

		Assert.assertTrue(usersList.size() == 1);

	}
}
