package org.craftercms.studio.test.cases.userstestcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.craftercms.studio.test.cases.StudioBaseTest;


/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class UsersPerPageTest extends StudioBaseTest{

	private String userName;
	private String password;

	private String newUserFirstNameId;
	private String newUserLastNameId;
	private String newUserEmailId;
	private String newUserUserNameId;
	private String newUserPasswordId;
	private String newUserPasswordVerificationId;
	private String usersPerPageInputXpath;
	private String usersRowsXpath;
	private String lastNumberOfPaginationXpath;

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
		usersRowsXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.usersrows");
		lastNumberOfPaginationXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.pagination.lastnumberelement");
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
				.sendKeys("testuser"+RandomStringUtils.randomAlphabetic(5));
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", newUserPasswordId).sendKeys("password");
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", newUserPasswordVerificationId)
				.sendKeys("password");

		// Save Button
		usersPage.clickOnSaveNewUser();
	}

	public void filters() {

		// Show 1 user
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", usersPerPageInputXpath);
		driverManager.sendText("xpath",usersPerPageInputXpath,"1");

		// Asser only 1 users displayed
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",lastNumberOfPaginationXpath);
		Assert.assertTrue(this.driverManager.elementHasChildsByXPath(usersRowsXpath));

		List<WebElement> usersList1item = this.driverManager.getDriver().findElements(By.xpath(usersRowsXpath));
		Assert.assertTrue(usersList1item.size() == 1);
		
		// Show 3 users
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",usersPerPageInputXpath);
		driverManager.sendText("xpath",usersPerPageInputXpath,"3");

		// Assert 3 users displayed
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",lastNumberOfPaginationXpath);
		Assert.assertTrue(this.driverManager.elementHasChildsByXPath(usersRowsXpath));

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",usersPerPageInputXpath);
		driverManager.sendText("xpath",usersPerPageInputXpath,"10");
	}

	public void deleteUsers() {
		// Click on delete user
		driverManager.getDriver().navigate().refresh();
		usersPage.deleteAllUsersExceptAdmin();
	}

	@Test(priority = 0)
	public void verifyThatTheShowUsersPerPageWorksProperlyTest() {
		// login to application
		loginPage.loginToCrafter(userName, password);
		
		//Wait for login page to close
		driverManager.waitUntilLoginCloses();

		// Create user 1
		createUserRandom();
		// Create user 2
		driverManager.getDriver().navigate().refresh();
		createUserRandom();
		// Create user 3
		driverManager.getDriver().navigate().refresh();
		createUserRandom();
		driverManager.getDriver().navigate().refresh();
		
		// filters
		filters();
		// Delete all users
		deleteUsers();
	}

}
