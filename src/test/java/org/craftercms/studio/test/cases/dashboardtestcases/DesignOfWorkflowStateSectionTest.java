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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.craftercms.studio.test.cases.StudioBaseTest;


/**
 * 
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class DesignOfWorkflowStateSectionTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String workflowPanel;
	private String editedStateItem;
	private String neverPublishedItem;
	private String inWorkFlowItem;
	private String scheduledStateItem;
	private String processingStateItem;
	private String deletedStateItem;
	private String lockedStateItem;

	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String testId, String blueprint) {
		apiTestHelper.createSite(testId, "", blueprint);
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		workflowPanel = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.workflowpanel");
		neverPublishedItem = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.workflowpanel.neverpublishedstate");
		editedStateItem = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.workflowpanel.editedstate");
		inWorkFlowItem = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.workflowpanel.inworkflowstate");
		scheduledStateItem = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.workflowpanel.scheduledstate");
		processingStateItem = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.workflowpanel.processingstate");
		deletedStateItem = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.workflowpanel.deletedstate");
		lockedStateItem = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.workflowpanel.lockedstate");
	}

	@Parameters({"testId"})
	@Test()
	public void verifyAllWorkflowStatesOnIconGuideTest(String testId) {

		// login to application
		loginPage.loginToCrafter(userName, password);
		
		//Wait for login page to close
		getWebDriverManager().waitUntilLoginCloses();

		// go to dashboard page
		homePage.goToDashboardPage(testId);

		// Assert workflow guide section is present.
		WebElement workflowSection = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
				workflowPanel);
		Assert.assertTrue(workflowSection.isDisplayed(),"Error: Workflow section is not displayed");

		// Assert neverpub is present.
		WebElement neverPublished = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
				neverPublishedItem);
		Assert.assertTrue(neverPublished.isDisplayed(),"Error: Never published item is not present");

		// Assert edited is present.
		WebElement edited = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", editedStateItem);
		Assert.assertTrue(edited.isDisplayed(),"Error: Edited item is not present");

		// Assert in workflow is present.
		WebElement inWorkflow = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", inWorkFlowItem);
		Assert.assertTrue(inWorkflow.isDisplayed(), "Error: Workflow item is not present");

		// Assert scheduled is present.
		WebElement scheduled = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
				scheduledStateItem);
		Assert.assertTrue(scheduled.isDisplayed(),"Error: Scheduled item is not present");

		// Assert processing is present.
		WebElement processing = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
				processingStateItem);
		Assert.assertTrue(processing.isDisplayed(), "Error: Processing item is not present");

		// Assert deleted for edit is present.
		WebElement deleted = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", deletedStateItem);
		Assert.assertTrue(deleted.isDisplayed(),"Error: Never publisehd item is not present");

		// Assert Locked for edit is present.
		WebElement locked = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", lockedStateItem);
		Assert.assertTrue(locked.isDisplayed(),"Error: Locked item is not present");

	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
