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

public class HomePage {

	private WebDriverManager driverManager;
	private WebDriver driver;
	private String previewSite;
	private String dashboardSite;
	private String createSiteButton;
	private String deleteSiteIcon;
	private String yesDeleteButton;
	private String logOutLink;
	private String signOutLink;
	private String usersContextualNavigationOption;
	private String deleteIconsListXpath;
	private String sitesOptionXpath;
	private String sitesPageTitleXpath;
	private static Logger logger = LogManager.getLogger(HomePage.class);

	public HomePage(WebDriverManager driverManager, UIElementsPropertiesManager UIElementsPropertiesManager) {
		this.driverManager = driverManager;
		this.driver = this.driverManager.getDriver();

		previewSite = UIElementsPropertiesManager.getSharedUIElementsLocators().getProperty("home.previewlink");
		dashboardSite = UIElementsPropertiesManager.getSharedUIElementsLocators().getProperty("home.dashboardlink");
		createSiteButton = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsitebutton");
		deleteSiteIcon = UIElementsPropertiesManager.getSharedUIElementsLocators().getProperty("home.deletesiteicon");
		yesDeleteButton = UIElementsPropertiesManager.getSharedUIElementsLocators().getProperty("home.confirmtodelete");
		logOutLink = UIElementsPropertiesManager.getSharedUIElementsLocators().getProperty("home.expandaccount");
		signOutLink = UIElementsPropertiesManager.getSharedUIElementsLocators().getProperty("home.signout");
		usersContextualNavigationOption = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.userscontextualnavigationoption");
		deleteIconsListXpath = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.deletesiteiconlist");
		sitesOptionXpath = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.homesites");
		sitesPageTitleXpath = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("sites.pagetitle");
	}

	// Click on preview link
	public void clickPreviewOption() {
		WebElement previewLink = this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				previewSite);
		previewLink.click();
		this.driverManager.waitForAnimation();
	}

	public void goToPreviewPage() {
		// Click on preview link
		this.clickPreviewOption();
	}

	// Click on dashboard link

	public void clickDashboardOption() {
		this.driverManager.isElementPresentAndClickableByXpath(dashboardSite);
		WebElement dashboardLink = this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				dashboardSite);
		dashboardLink.click();
		this.driverManager.waitForAnimation();
	}

	public void goToDashboardPage() {
		logger.debug("Go to Dashboard Page");
		this.clickDashboardOption();
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

	// Click on Create Site button
	public void clickCreateSiteButton() {
		this.driverManager.isElementPresentAndClickableByXpath(createSiteButton);
		WebElement createSiteOption = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", createSiteButton);
		createSiteOption.click();
	}

	public void clickOnCreateSiteButton() {
		// Click on Create Site button
		this.clickCreateSiteButton();
	}

	// Click on Delete icon to the site
	public void clickDeleteSiteIcon() {
		WebElement deleteIcon = this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				deleteSiteIcon);
		deleteIcon.click();
	}

	public void clickOnDeleteSiteIcon() {
		// Click on Delete icon to the site
		this.clickDeleteSiteIcon();
	}

	// Click on YES button
	public void clickYesButton() {
		this.driverManager.isElementPresentAndClickableByXpath(yesDeleteButton);
		WebElement yesButton = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				yesDeleteButton);
		yesButton.click();
		this.driverManager.waitForAnimation();
	}

	public void clickOnYesToDeleteSite() {
		// Click on YES button
		this.clickYesButton();
	}

	// Logout to the crafter
	public void expandAccount() {
		this.driverManager.isElementPresentAndClickableByXpath(logOutLink);
		WebElement expandAccount = this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				logOutLink);
		expandAccount.click();
	}

	public void clickSignOut() {
		WebElement signOut = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", signOutLink);
		signOut.click();
	}

	public void clickLogoutOutCrafter() {
		// Expand account
		this.expandAccount();
		// Click on SignOut
		this.clickSignOut();
	}

	public void clickUsersContextualNavigationOption() {
		WebElement usersContextualNavigationOptionWebElement = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", usersContextualNavigationOption);
		usersContextualNavigationOptionWebElement.click();
	}

	public Boolean isUsersContextualNavigationOptionPresent() {
		return this.driverManager.isElementPresentByXpath(usersContextualNavigationOption);
	}

	public void deleteSite() {
		// Click on Delete icon
		this.clickOnDeleteSiteIcon();
		// Click on YES to confirm the delete.
		this.clickOnYesToDeleteSite();
	}

	public void deleteAllSites() {
		List<WebElement> siteListitem = this.driverManager.getDriver()
				.findElements(By.xpath(deleteIconsListXpath));

		for (int i = 0; i < siteListitem.size(); i++) {
			this.driverManager.waitForAnimation();
			this.driverManager.waitUntilPageLoad();		
			// get the delete button element
			WebElement element = this.driverManager.waitUntilElementIsClickable("xpath", deleteIconsListXpath);
			// click on the delete button
			element.click();
			// confirm and wait
			this.clickOnYesToDeleteSite();
			
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

	public boolean isSitePageTitlePresent() {
		return this.driverManager.isElementPresentByXpath(sitesPageTitleXpath);
	}

}