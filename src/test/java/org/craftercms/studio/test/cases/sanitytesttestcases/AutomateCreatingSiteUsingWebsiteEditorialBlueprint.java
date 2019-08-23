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
package org.craftercms.studio.test.cases.sanitytesttestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.craftercms.studio.test.cases.StudioBaseTest;
import org.openqa.selenium.WebElement;

/**
 * 
 * 
 * @author Juan Camacho A
 *
 */
//Test Case created to cover ticket https://github.com/craftercms/craftercms/issues/1445
public class AutomateCreatingSiteUsingWebsiteEditorialBlueprint extends StudioBaseTest{

	private String userName;
	private String password;
	private String siteDropdownElementXPath;
	private String createSiteErrorNotificationWindow;
	private String editorialSitePreviewPageTitle;

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		createSiteErrorNotificationWindow = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.createsite.errowindow");
		editorialSitePreviewPageTitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("preview.editorial.site.title");
	}

	@Parameters({"testId"})
	@Test()
	public void automateCreatingSiteUsingWebsiteEditorialBlueprint(String testId) {

		// login to application
		loginPage.loginToCrafter(
				userName,password);
		
		//Wait for login page to close
		getWebDriverManager().waitUntilLoginCloses();

		// Click on the create site button
		homePage.clickOnCreateSiteButton();

		//select blueprint, set site name, set description, click review and create site
		createSitePage.selectWebSiteEditorialBluePrintOption()
				.setSiteName(testId)
				.setDescription("Description")
				.clickReviewAndCreate()
				.clickOnCreateButton();
		
		//Verify No error messages after clicking on the Create button
		
		Assert.assertFalse(getWebDriverManager().isElementPresentByXpath(createSiteErrorNotificationWindow));
		this.getWebDriverManager().waitWhileElementIsDisplayedAndClickableByXpath(siteDropdownElementXPath);

		//Assert Page is displayed
		//Move to the content frame
		Assert.assertTrue(this.getWebDriverManager().isElementPresentAndClickableByXpath(siteDropdownElementXPath));
		
		getWebDriverManager().getDriver().switchTo().defaultContent();
        getWebDriverManager().getDriver().switchTo().frame(this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed(
                "id", "engineWindow"));
        
		//Assert Title of the page correspond to a Editorial Blueprint site
        WebElement siteTitle = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed(
    		  "xpath", editorialSitePreviewPageTitle);

        Assert.assertTrue(siteTitle.getText().contains("Hi, I’m Editorial"));	
		
	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
