package org.craftercms.studio.test.cases.userstestcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.commons.lang3.RandomStringUtils;
import org.craftercms.studio.test.cases.StudioBaseTest;

/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class PaginationOfListOfUsersTest extends StudioBaseTest{

	private String userName;
	private String password;
	private String newUserFirstNameId;
	private String newUserLastNameId;
	private String newUserEmailId;
	private String newUserUserNameId;
	private String newUserPasswordId;
	private String newUserPasswordVerificationId;
	private String usersPerPageInputXpath;
	private String lastNumberOfPaginationXpath;
	private String firstNumberOfPaginationXpath;
	private String lastArrowOfPaginationXpath;
	private String firstArrowOfPaginationXpath;
	
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
		usersPerPageInputXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.usersperpageinput");
		lastNumberOfPaginationXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.pagination.lastnumberelement");
		firstNumberOfPaginationXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.pagination.firstnumberelement");
		lastArrowOfPaginationXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.pagination.lastarrowelement");
		firstArrowOfPaginationXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.pagination.firstarrowelement");

	}

	public void createUserRandom() {

		createSitePage.clickOnUsersOption();

		// click on new user button
		usersPage.clickOnNewUser();

		// Follow the form
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", newUserFirstNameId).sendKeys("Name");
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", newUserLastNameId).sendKeys("Last Name");
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", newUserEmailId)
				.sendKeys("email@email.com");
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", newUserUserNameId)
				.sendKeys("testuser" + RandomStringUtils.randomAlphabetic(5));
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", newUserPasswordId).sendKeys("password");
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", newUserPasswordVerificationId)
				.sendKeys("password");

		// Save Button
		usersPage.clickOnSaveNewUser();

		// Refresh the site
		driverManager.getDriver().navigate().refresh();
	}

	public void navigationOfPage() {

		// Show users
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", usersPerPageInputXpath).clear();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", usersPerPageInputXpath).sendKeys("1");

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", usersPerPageInputXpath).clear();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", usersPerPageInputXpath).sendKeys("2");

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", lastNumberOfPaginationXpath).click();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", firstNumberOfPaginationXpath).click();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", lastArrowOfPaginationXpath).click();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", firstArrowOfPaginationXpath).click();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", usersPerPageInputXpath).clear();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", usersPerPageInputXpath).sendKeys("10");

		this.driverManager.waitForAnimation();
	}

	public void deleteUsers() {
		// Click on delete user
		usersPage.deleteAllUsersExceptAdmin();
	}

	@Test(priority = 0)
	public void verifyThatThePaginationOfTheListOfUsersWorksProperlyTest() {
		
		// login to application
		loginPage.loginToCrafter(userName, password);
		
		//Wait for login page to close
		driverManager.waitUntilLoginCloses();

		createUserRandom();
		createUserRandom();
		createUserRandom();

		// filters
		navigationOfPage();

		// Delete users
		this.driverManager.waitForAnimation();
		deleteUsers();

	}

}
