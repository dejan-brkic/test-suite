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
package org.craftercms.studio.test.cases.dashboardtestcases;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.craftercms.studio.test.cases.StudioBaseTest;

/**
 * 
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class RecentActivityItemTypesSectionTest extends StudioBaseTest{

	private String userName;
	private String password;

	private String navigationPageItem;
	private String floatingPageItem;
	private String componentItem;
	private String templateScriptItem;
	private String taxonomyItem;
	private String imageItem;
	private String videoItem;
	private String cssItem;
	private String fontItem;
	private String pdfItem;
	private String msPowerPointItem;
	private String msWordItem;
	private String msExcelItem;
	private String zipItem;
	private String groovyItem;
	private String otherItem;
	private String itemsTypePanel;
	
	@BeforeMethod
	public void beforeTest() {
		
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		navigationPageItem = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.workflowpanel.navigationpage");
		floatingPageItem = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.workflowpanel.floatingpage");
		componentItem = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.workflowpanel.component");
		templateScriptItem = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.workflowpanel.templatescript");
		taxonomyItem = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.workflowpanel.taxonomy");
		imageItem = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.workflowpanel.image");
		videoItem = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.workflowpanel.video");
		cssItem = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("dashboard.workflowpanel.css");
		fontItem = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.workflowpanel.font");
		pdfItem = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("dashboard.workflowpanel.pdf");
		msPowerPointItem = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.workflowpanel.mspowerpoint");
		msWordItem = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.workflowpanel.msword");
		msExcelItem = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.workflowpanel.msexcel");
		zipItem = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("dashboard.workflowpanel.Zip");
		groovyItem = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.workflowpanel.Groovy");
		otherItem = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.workflowpanel.Other");
		itemsTypePanel = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.itemtypespanel");

	}

	public void verifyAllItemTypesOnIconGuide() {

		// Assert Item Types tittle is present.

		WebElement itemTypes = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
				itemsTypePanel);
		Assert.assertTrue(itemTypes.isDisplayed());

		// Assert navigation page is present.
		WebElement navigationPage = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
				navigationPageItem);
		Assert.assertTrue(navigationPage.isDisplayed());

		// Assert floating page is present.
		WebElement floatingPage = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
				floatingPageItem);
		Assert.assertTrue(floatingPage.isDisplayed());

		// Assert component is present.
		WebElement component = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", componentItem);
		Assert.assertTrue(component.isDisplayed());

		// Assert template is present.
		WebElement template = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
				templateScriptItem);
		Assert.assertTrue(template.isDisplayed());

		// Assert taxonomy is present.
		WebElement taxonomy = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", taxonomyItem);
		Assert.assertTrue(taxonomy.isDisplayed());

		// Assert iamge is present.
		WebElement image = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", imageItem);
		Assert.assertTrue(image.isDisplayed());

		// Assert css is present.
		WebElement video = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", videoItem);
		Assert.assertTrue(video.isDisplayed());

		// Assert css is present.
		WebElement css = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", cssItem);
		Assert.assertTrue(css.isDisplayed());

		// Assert Font for edit is present.
		WebElement font = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", fontItem);
		Assert.assertTrue(font.isDisplayed());

		// Assert pdf is present.
		WebElement pdf = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", pdfItem);
		Assert.assertTrue(pdf.isDisplayed());

		// Assert MS Power Point for edit is present.
		WebElement msPowerPoint = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
				msPowerPointItem);
		Assert.assertTrue(msPowerPoint.isDisplayed());

		// Assert MS Word is present.
		WebElement msWord = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", msWordItem);
		Assert.assertTrue(msWord.isDisplayed());

		// Assert MS Excel for edit is present.
		WebElement msExcel = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", msExcelItem);
		Assert.assertTrue(msExcel.isDisplayed());

		// Assert zip is present.
		WebElement zip = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", zipItem);
		Assert.assertTrue(zip.isDisplayed());

		// Assert zip is present.
		WebElement groovy = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", groovyItem);
		Assert.assertTrue(groovy.isDisplayed());

		// Assert other is present.
		WebElement other = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", otherItem);
		Assert.assertTrue(other.isDisplayed());

	}

	@Test(priority = 0)

	public void verifyAllItemTypesOnIconGuideTest() {

		// login to application

		loginPage.loginToCrafter(userName, password);
		
		//Wait for login page to close
		getWebDriverManager().waitUntilLoginCloses();

		// go to dashboard page
		homePage.goToDashboardPage();
		
		this.verifyAllItemTypesOnIconGuide();

	}
}
