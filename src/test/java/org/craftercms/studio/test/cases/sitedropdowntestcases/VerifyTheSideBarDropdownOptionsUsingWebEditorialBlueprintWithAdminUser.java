package org.craftercms.studio.test.cases.sitedropdowntestcases;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.LinkedList;
import java.util.List;

import org.craftercms.studio.test.cases.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * 
 * 
 * @author Juan Camacho A
 *
 */
//Test Case Studio- Site Dropdown ID:1
public class VerifyTheSideBarDropdownOptionsUsingWebEditorialBlueprintWithAdminUser extends BaseTest{

	private String userName;
	private String password;
	private String siteDropdownElementXPath;
	private String menuSitesButton;
	private String dashboardLink;
	private String pagesTreeLink;
	private String componentsTreeLink;
	private String taxonomyTreeLink;
	private String staticAssetsTreeLink;
	private String templatesTreeLink;
	private String scriptsTreeLink;
	private String siteConfigLink;
	private LinkedList<String> siteDropdownItemsInExpectedOrder;
	private String siteDropdownItemsXpath;
	
	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdownmenuinnerxpath");
		menuSitesButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("preview.sites.menu.button");
		dashboardLink = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.dashboard_menu_option");
		pagesTreeLink = uiElementsPropertiesManager.getSharedUIElementsLocators()
		.getProperty("general.sitecontent.expandpages");
		componentsTreeLink = uiElementsPropertiesManager.getSharedUIElementsLocators()
		.getProperty("dashboard.expand_components_tree");
		taxonomyTreeLink = uiElementsPropertiesManager.getSharedUIElementsLocators()
		.getProperty("dashboard.expand_taxonomy_tree");
		staticAssetsTreeLink = uiElementsPropertiesManager.getSharedUIElementsLocators()
		.getProperty("preview.static_assets_button");
		templatesTreeLink = uiElementsPropertiesManager.getSharedUIElementsLocators()
		.getProperty("dashboard.expand_templates_tree");
		scriptsTreeLink = uiElementsPropertiesManager.getSharedUIElementsLocators()
		.getProperty("dashboard.expand_scripts_tree");
		siteConfigLink = uiElementsPropertiesManager.getSharedUIElementsLocators()
		.getProperty("general.adminconsole");
		siteDropdownItemsXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.sitebar.dropdown.items");
		
		siteDropdownItemsInExpectedOrder = new LinkedList<String>();
		siteDropdownItemsInExpectedOrder.add(0,"Dashboard");
		siteDropdownItemsInExpectedOrder.add(1,"Pages");
		siteDropdownItemsInExpectedOrder.add(2,"Components");
		siteDropdownItemsInExpectedOrder.add(3,"Taxonomy");
		siteDropdownItemsInExpectedOrder.add(4,"Static Assets");
		siteDropdownItemsInExpectedOrder.add(5,"Templates");
		siteDropdownItemsInExpectedOrder.add(6,"Scripts");
		siteDropdownItemsInExpectedOrder.add(7,"Site Config");
			
	}
	
	public void deleteSite() {
		
		this.driverManager.getDriver().switchTo().defaultContent();
		
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable(
				"xpath", menuSitesButton).click();

		// Click on Delete icon
		homePage.clickOnDeleteSiteIcon();

		// Click on YES to confirm the delete.
		homePage.clickOnYesToDeleteSite();
		
		//Refresh the page
		driverManager.getDriver().navigate().refresh();

	}

	@AfterMethod
	public void afterTest() {
		deleteSite();
	}

	@Test(priority = 0)
	public void verifyTheSideBarDropdownOptionsUsingWebEditorialBlueprint() {

		// login to application
		loginPage.loginToCrafter(
				userName,password);
		
		//Wait for login page to close
		driverManager.waitUntilLoginCloses();

		// Click on the create site button
		homePage.clickOnCreateSiteButton();

		// Filling the name of site

		createSitePage.fillSiteName();

		// Filling the description of the site

		createSitePage.fillDescription("Description");

		// Open blueprint combo
		// Select blueprint

		createSitePage.selectWebSiteEditorialBluePrintOption();

		// Click on Create button

		createSitePage.clickOnCreateSiteButton();
	
		//Expand the site bar
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",siteDropdownElementXPath);
		WebElement sidebar = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",siteDropdownElementXPath);
		sidebar.click();
		Assert.assertTrue(this.driverManager.isElementPresentAndClickableByXpath(siteDropdownElementXPath));
		
		//Check all the section are present;
		WebElement dashboardLinkElement = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", dashboardLink);
		Assert.assertTrue(dashboardLinkElement.isDisplayed(),
				"ERROR: Dashboard link is not present");
		
		WebElement pagesTreeLinkElement = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", pagesTreeLink);
		Assert.assertTrue(pagesTreeLinkElement.isDisplayed(),
				"ERROR: Pages Tree link is not present");
		
		WebElement componentsTreeLinkElement = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", componentsTreeLink);
		Assert.assertTrue(componentsTreeLinkElement.isDisplayed(),
				"ERROR: Components Tree link is not present");
		
		WebElement taxonomyTreeLinkElement = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", taxonomyTreeLink);
		Assert.assertTrue(taxonomyTreeLinkElement.isDisplayed(),
				"ERROR: Taxonomy Tree link is not present");
		
		WebElement staticAssetsTreeLinkElement = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", staticAssetsTreeLink);
		Assert.assertTrue(staticAssetsTreeLinkElement.isDisplayed(),
				"ERROR: Static Assets Tree link is not present");
		
		WebElement templatesTreeLinkElement = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", templatesTreeLink);
		Assert.assertTrue(templatesTreeLinkElement.isDisplayed(),
				"ERROR: Templates Tree link is not present");
        
		WebElement scriptsTreeLinkElement = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", scriptsTreeLink);
		Assert.assertTrue(scriptsTreeLinkElement.isDisplayed(),
				"ERROR: Scripts Tree link is not present");
		
		WebElement siteConfigLinkElement = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", siteConfigLink);
		Assert.assertTrue(siteConfigLinkElement.isDisplayed(),
				"ERROR: Site Config link is not present");
		
		List<WebElement> siteDropdownItems = this.driverManager.getDriver()
				.findElements(By.xpath(siteDropdownItemsXpath));
		int currentIndex = 0;
		for(WebElement element:siteDropdownItems){
			this.driverManager.waitForAnimation();
			this.driverManager.waitUntilSidebarOpens();
			Assert.assertTrue(element.getText().equals(siteDropdownItemsInExpectedOrder.get(currentIndex)),
					"ERROR: Link Option: "+element.getText()+" is not in the correct order");
			currentIndex++;
		}	
	}
}
