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

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.utils.UIElementsPropertiesManager;
import org.craftercms.studio.test.utils.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class HomePage {

	private WebDriverManager driverManager;
	private String previewSite;
	private String previewSiteLink;
	private String dashboardSite;
	private String dashboardSiteLink;
	private String createSiteButton;
	private String deleteSiteIcon;
	private String deleteSiteButton;
	private String yesDeleteButton;
	private String logOutLink;
	private String signOutLink;
	private String usersContextualNavigationOption;
	private String deleteIconsListXpath;
	private String sitesOptionXpath;
	private String sitesPageTitleXpath;
	private String sitesPerPageInputXpath;
	private String siteIdLabel;
	private String siteTableRow;
	private String engineWindowId;
	private Object siteIDColumn;
	private static Logger logger = LogManager.getLogger(HomePage.class);

	public HomePage(WebDriverManager driverManager, UIElementsPropertiesManager UIElementsPropertiesManager) {
		this.driverManager = driverManager;

		previewSite = UIElementsPropertiesManager.getSharedUIElementsLocators().getProperty("home.previewlink");
		previewSiteLink = UIElementsPropertiesManager.getSharedUIElementsLocators().getProperty("home.previewlink.site");
		dashboardSite = UIElementsPropertiesManager.getSharedUIElementsLocators().getProperty("home.dashboardlink");
		dashboardSiteLink = UIElementsPropertiesManager.getSharedUIElementsLocators().getProperty("home.dashboardlink.site");
		createSiteButton = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsitebutton");
		deleteSiteIcon = UIElementsPropertiesManager.getSharedUIElementsLocators().getProperty("home.deletesiteicon");
		deleteSiteButton = UIElementsPropertiesManager.getSharedUIElementsLocators().getProperty("home.deletesite");
		yesDeleteButton = UIElementsPropertiesManager.getSharedUIElementsLocators().getProperty("home.confirmtodelete");
		logOutLink = UIElementsPropertiesManager.getSharedUIElementsLocators().getProperty("home.expandaccount");
		signOutLink = UIElementsPropertiesManager.getSharedUIElementsLocators().getProperty("home.signout");
		usersContextualNavigationOption = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.userscontextualnavigationoption");
		deleteIconsListXpath = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.deletesiteiconlist");
		sitesOptionXpath = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.homesites");
		sitesPageTitleXpath = UIElementsPropertiesManager.getSharedUIElementsLocators().getProperty("sites.pagetitle");
		sitesPerPageInputXpath = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.sitesperpageinput");
		engineWindowId = UIElementsPropertiesManager.getSharedUIElementsLocators().getProperty("general.sites.iframe.engine.id");
		siteIdLabel = UIElementsPropertiesManager.getSharedUIElementsLocators().getProperty("home.siteidlabel");
		siteTableRow = UIElementsPropertiesManager.getSharedUIElementsLocators().getProperty("home.general.sitetablerow");
		siteIDColumn = UIElementsPropertiesManager.getSharedUIElementsLocators().getProperty("home.general.siteidtablecolumn");
	}

	// Click on preview link
	public void clickPreviewOption() {
		logger.info("Going to preview page");
		this.driverManager.waitForAnimation();
		WebElement previewLink = this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				previewSite);
		previewLink.click();
		this.driverManager.waitForAnimation();
	}

	public void goToPreviewPage(String siteId) {
		logger.info("Going to preview page");
		driverManager.clickElement("xpath", String.format(previewSiteLink,siteId));
		driverManager.getDriver().switchTo()
				.frame(driverManager.driverWaitUntilElementIsPresentAndDisplayed("id", engineWindowId));
		driverManager.getDriver().switchTo().defaultContent();
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

	public void goToDashboardPage(String siteId) {
		logger.info("Going to dashboard page");
		driverManager.clickElement("xpath", String.format(dashboardSiteLink, siteId));
	}

	public WebDriverManager getDriverManager() {
		return driverManager;
	}

	public void setDriverManager(WebDriverManager driverManager) {
		this.driverManager = driverManager;
	}

	public void clickOnCreateSiteButton() {
		this.driverManager.clickElement("xpath", createSiteButton);
	}

	// Click on Delete icon to the site
	public void clickDeleteSiteIcon() {
		WebElement deleteIcon = this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				deleteSiteIcon);
		deleteIcon.click();
	}

	public void clickDeleteSiteIcon(String siteId) {
		WebElement siteElement = this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				String.format(deleteSiteButton, siteId));
		siteElement.click();
		clickYesButton();
		driverManager.waitUntilElementIsRemoved(siteElement);
	}

	public void clickOnDeleteSiteIcon() {
		// Click on Delete icon to the site
		this.clickDeleteSiteIcon();
	}

	// Click on YES button
	public void clickYesButton() {
		this.driverManager.isElementPresentAndClickableByXpath(yesDeleteButton);
		WebElement yesButton = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", yesDeleteButton);
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

	public void deleteSite(String siteId) {
		// Click on Delete icon
		this.clickDeleteSiteIcon(siteId);
	}

	public void deleteAllSites() {
		List<WebElement> siteListitem = this.driverManager.getDriver().findElements(By.xpath(deleteIconsListXpath));

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

	public void checkElementsOnSitePageWithoutSites() {
		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", createSiteButton).isDisplayed());
		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", sitesPerPageInputXpath).isDisplayed());
		Assert.assertTrue(this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", sitesPageTitleXpath)
				.isDisplayed());
		
		this.driverManager.waitWhileElementIsNotDisplayedByXpath(previewSite);
		
		Assert.assertFalse(this.driverManager
				.isElementPresentAndClickableByXpath(previewSite));
		Assert.assertFalse(this.driverManager
				.isElementPresentAndClickableByXpath(dashboardSite));
		Assert.assertFalse(this.driverManager
				.isElementPresentAndClickableByXpath(siteIdLabel));
		Assert.assertFalse(this.driverManager
				.isElementPresentAndClickableByXpath(deleteSiteIcon));
	}

	public void checkElementsOnSitePageWithSites() {
		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", createSiteButton).isDisplayed());
		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", sitesPerPageInputXpath).isDisplayed());
		Assert.assertTrue(this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", sitesPageTitleXpath)
				.isDisplayed());
		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", previewSite).isDisplayed());
		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dashboardSite).isDisplayed());
		Assert.assertTrue(
				this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", siteIdLabel).isDisplayed());
		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", deleteSiteIcon).isDisplayed());
	}
	
	public void checkIfSiteIsListedOnSitesPage(String siteId) {
		String currentSiteIdColumn= siteTableRow+siteId+siteIDColumn;
		Assert.assertTrue(
				this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", currentSiteIdColumn).isDisplayed());
	}
}