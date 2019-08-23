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
package org.craftercms.studio.test.cases.contenttestcases;

import org.craftercms.studio.test.cases.StudioBaseTest;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * 
 * @author Luis Hernandez
 *
 */

//Related to ticket: https://github.com/craftercms/craftercms/issues/1869
public class DeleteDeliveryContentPageTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String siteDropdownElementXPath;
	private String createdContentXPath;
	private String siteDropdownListElementXPath;

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		createdContentXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.studio.deliverypagecontenttodelete");
		siteDropdownListElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdownlielement");
	}

	@Parameters({"testId"})
	@Test()
	public void deleteDeliveryContentPageTest(String testId) {
		// dropdown panel)
		this.loginAndGoToSiteContentPagesStructure(testId);

		// expand pages folder
		dashboardPage.expandPagesTree();

		// Step2
		getWebDriverManager().waitUntilSidebarOpens();

		// Expand Home Tree
		dashboardPage.expandHomeTree();

		// right click to delete
		dashboardPage.rightClickToDeleteContent(createdContentXPath);

		// confirmation
		dashboardPage.clicktoDeleteContent();

		// submittal complete ok
		dashboardPage.clickOKSubmittalComplete();

		this.getWebDriverManager().waitForAnimation();
		Assert.assertFalse(this.getWebDriverManager().isElementPresentByXpath(createdContentXPath));
	}

	public void loginAndGoToSiteContentPagesStructure(String siteId) {
		// login to application
		loginPage.loginToCrafter(userName, password);

		// Wait for login page to close
		getWebDriverManager().waitUntilLoginCloses();

		// go to preview page
		homePage.goToDashboardPage(siteId);
		if (this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", siteDropdownElementXPath)
				.isDisplayed())
			if (!(this.getWebDriverManager().waitUntilElementIsPresent("xpath", siteDropdownListElementXPath)
					.getAttribute("class").contains("site-dropdown-open")))
			this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", siteDropdownElementXPath).click();
		else
			throw new NoSuchElementException(
					"Site creation process is taking too long time and the element was not found");
	}

}
