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
 * @author Juan Camacho A.
 *
 */
// Test Case created to cover ticket
// https://github.com/craftercms/craftercms/issues/1447
public class AutomateCheckingNoErrorsDisplayedInTheDashboard extends StudioBaseTest {

	private String userName;
	private String password;
	private String siteDropdownElementXPath;
	private String createSiteErrorNotificationWindow;
	private String dashboardSiteContent;
	private String dashboardMenuOption;
	private String dashboardItemsWaitingForApprovalContainer;

	private String itemsWaitingForApprovalContainer;
	private String itemsWaitingForApprovalCollapseAllButton;
	private String itemsWaitingForApprovalShowInProgressItemsButton;
	private String itemsWaitingForApprovalItemsCheckbox;
	private String itemsWaitingForApprovalItemNameTitle;
	private String itemsWaitingForApprovalEdittitle;
	private String itemsWaitingForApprovalUrlTitle;
	private String itemsWaitingForApprovalPublishDateTimeTitle;
	private String itemsWaitingForApprovalLastEditedByTitle;
	private String itemsWaitingForApprovalLastEditedTitle;

	private String approvedScheduledItemsContainer;
	private String approvedScheduledItemsCollapseAllButton;
	private String approvedScheduledItemsFilterSelector;
	private String approvedScheduledItemsItemsCheckBox;
	private String approvedScheduledItemsGoToLiveDateTitle;
	private String approvedScheduledItemsEdittitle;
	private String approvedScheduledItemsUrlTitle;
	private String approvedScheduledLastEditedTitle;

	private String recentlyPublishedContainer;
	private String recentlyPublishedShowLabel;
	private String recentlyPublishedShowLabelInput;
	private String recentlyPublishedCollapseAllButton;
	private String recentlyPublishedFilterSelector;
	private String recentlyPublishedItemsCheckBox;
	private String recentlyPublishedItemNameTitle;
	private String recentlyPublishedEdittitle;
	private String recentlyPublishedUrlTitle;
	private String recentlyPublishedServerTitle;
	private String recentlyPublishedPublishDateTitle;
	private String recentlyPublishedPublishedByTitle;

	private String myRecentActivityContainer;
	private String myRecentActivityShowLabel;
	private String myRecentActivityShowLabelInput;
	private String myRecentActivityHideLiveItemsButton;
	private String myRecentActivityFilterSelector;
	private String myRecentActivityItemsCheckbox;
	private String myRecentActivityItemNameTitle;
	private String myRecentActivityEdittitle;
	private String myRecentActivityUrlTitle;
	private String myRecentActivityPublishDateTitle;
	private String myRecentActivityLastEditedBytitle;
	private String myRecentActivityMyLastEditTitle;

	private String workflowPanel;
	private String editedStateItem;
	private String neverPublishedItem;
	private String inWorkFlowItem;
	private String scheduledStateItem;
	private String processingStateItem;
	private String deletedStateItem;
	private String lockedStateItem;

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		createSiteErrorNotificationWindow = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.createsite.errowindow");
		dashboardSiteContent = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.site_content");
		dashboardMenuOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.dashboard_menu_option");
		dashboardItemsWaitingForApprovalContainer = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.itemswaitingforapproval.container");

		itemsWaitingForApprovalContainer = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.itemswaitingforapproval.container");
		itemsWaitingForApprovalCollapseAllButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.itemswaitingforapproval.collapseall.button");
		itemsWaitingForApprovalShowInProgressItemsButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.itemswaitingforapproval.showinprogressitems.button");
		itemsWaitingForApprovalItemsCheckbox = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.itemswaitingforapproval.item.checkbox");
		itemsWaitingForApprovalItemNameTitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.itemswaitingforapproval.itemnametitle");
		itemsWaitingForApprovalEdittitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.itemswaitingforapproval.edittitle");
		itemsWaitingForApprovalUrlTitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.itemswaitingforapproval.urltitle");
		itemsWaitingForApprovalPublishDateTimeTitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.itemswaitingforapproval.publishdatetimetitle");
		itemsWaitingForApprovalLastEditedByTitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.itemswaitingforapproval.lasteditedbytitle");
		itemsWaitingForApprovalLastEditedTitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.itemswaitingforapproval.lasteditedtitle");

		approvedScheduledItemsContainer = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.approvedscheduleditems.container");
		approvedScheduledItemsCollapseAllButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.approvedscheduleditems.collapseall.button");
		approvedScheduledItemsFilterSelector = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.approvedscheduleditems.filter.selector");
		approvedScheduledItemsItemsCheckBox = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.approvedscheduleditems.item.checkbox");
		approvedScheduledItemsGoToLiveDateTitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.approvedscheduleditems.golivedatetitle");
		approvedScheduledItemsEdittitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.approvedscheduleditems.edittitle");
		approvedScheduledItemsUrlTitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.approvedscheduleditems.urltitle");
		approvedScheduledLastEditedTitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.approvedscheduleditems.lasteditedtitle");

		recentlyPublishedContainer = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.recentlypublished.container");
		recentlyPublishedShowLabel = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.recentlypublished.show.label");
		recentlyPublishedShowLabelInput = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.recentlypublished.showlabel.input");
		recentlyPublishedCollapseAllButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.recentlypublished.collapseall.button");
		recentlyPublishedFilterSelector = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.recentlypublished.filter.selector");
		recentlyPublishedItemsCheckBox = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.recentlypublished.item.checkbox");
		recentlyPublishedItemNameTitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.recentlypublished.itemnametitle");
		recentlyPublishedEdittitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.recentlypublished.edittitle");
		recentlyPublishedUrlTitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.recentlypublished.urltitle");
		recentlyPublishedServerTitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.recentlypublished.servertitle");
		recentlyPublishedPublishDateTitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.recentlypublished.publishdatetitle");
		recentlyPublishedPublishedByTitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.recentlypublished.publishedbytitle");

		myRecentActivityContainer = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.container");
		myRecentActivityShowLabel = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.show.label");
		myRecentActivityShowLabelInput = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.showlabel.input");
		myRecentActivityHideLiveItemsButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.hideliveitems.button");
		myRecentActivityFilterSelector = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.filter.selector");
		myRecentActivityItemsCheckbox = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.item.checkbox");
		myRecentActivityItemNameTitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.itemnametitle");
		myRecentActivityEdittitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.edittitle");
		myRecentActivityUrlTitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.urltitle");
		myRecentActivityPublishDateTitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.publishdatetitle");
		myRecentActivityLastEditedBytitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.lasteditedbytitle");
		myRecentActivityMyLastEditTitle = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.mylastedittitle");

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
	public void automateCheckingNoErrorsDisplayedInTheDashboard(String testId) {

		// login to application
		loginPage.loginToCrafter(userName, password);

		// Wait for login page to close
		getWebDriverManager().waitUntilLoginCloses();

		// Click on the create site button
		homePage.clickOnCreateSiteButton();

		//select blueprint, set site name, set description, click review and create site
		createSitePage.selectWebSiteEditorialBluePrintOption()
				.setSiteName(testId)
				.setDescription("Description")
				.clickReviewAndCreate()
				.clickOnCreateButton();

		// Verify No error messages after clicking on the Create button
		Assert.assertFalse(getWebDriverManager().isElementPresentByXpath(createSiteErrorNotificationWindow));
		this.getWebDriverManager().waitWhileElementIsDisplayedAndClickableByXpath(siteDropdownElementXPath);

		WebElement sidebar = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
				dashboardSiteContent);

		sidebar.click();

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dashboardMenuOption);

		WebElement dashboardMenu = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				dashboardMenuOption);

		dashboardMenu.click();

		this.getWebDriverManager().waitForAnimation();

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				dashboardItemsWaitingForApprovalContainer);

		// Verify Items Waiting For Approval Section is listed with all of its
		// items

		WebElement itemsWaitingForApprovalContainerElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", itemsWaitingForApprovalContainer);
		Assert.assertTrue(itemsWaitingForApprovalContainerElement.isDisplayed(),
				"ERROR: Items Waiting for Approval container is not present");
		
		WebElement itemsWaitingForApprovalCollapseAllButtonElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", itemsWaitingForApprovalCollapseAllButton);
		Assert.assertTrue(itemsWaitingForApprovalCollapseAllButtonElement.isDisplayed(),
				"ERROR: Items Waiting for Approval Collapse Button is not present");
		
		WebElement itemsWaitingForApprovalShowInProgressItemsButtonElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", itemsWaitingForApprovalShowInProgressItemsButton);
		Assert.assertTrue(itemsWaitingForApprovalShowInProgressItemsButtonElement.isDisplayed(),
				"ERROR: Items Waiting for Approval Show In Progress button is not present");
		
		WebElement itemsWaitingForApprovalItemsCheckboxElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", itemsWaitingForApprovalItemsCheckbox);
		Assert.assertTrue(itemsWaitingForApprovalItemsCheckboxElement.isDisplayed(),
				"ERROR: Items Waiting for Approval Select items checkbox is not present");

		WebElement itemsWaitingForApprovalItemNameTitleElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", itemsWaitingForApprovalItemNameTitle);
		Assert.assertTrue(itemsWaitingForApprovalItemNameTitleElement.isDisplayed(),
				"ERROR: Items Waiting for Approval Iten Name title is not present");

		WebElement itemsWaitingForApprovalEdittitleElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", itemsWaitingForApprovalEdittitle);
		Assert.assertTrue(itemsWaitingForApprovalEdittitleElement.isDisplayed(),
				"ERROR: Items Waiting for Approval Edit title is not present");
			
		WebElement itemsWaitingForApprovalUrlTitleElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", itemsWaitingForApprovalUrlTitle);
		Assert.assertTrue(itemsWaitingForApprovalUrlTitleElement.isDisplayed(),
				"ERROR: Items Waiting for Approval URL title is not present");

		WebElement itemsWaitingForApprovalPublishDateTimeTitleElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", itemsWaitingForApprovalPublishDateTimeTitle);
		Assert.assertTrue(itemsWaitingForApprovalPublishDateTimeTitleElement.isDisplayed(),
				"ERROR: Items Waiting for Approval Approval Publish Date Time title is not present");

		WebElement itemsWaitingForApprovalLastEditedByTitleElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", itemsWaitingForApprovalLastEditedByTitle);
		Assert.assertTrue(itemsWaitingForApprovalLastEditedByTitleElement.isDisplayed(),
				"ERROR: Items Waiting for Approval Approval Last Edited By title is not present");
				
		WebElement itemsWaitingForApprovalLastEditedTitleElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", itemsWaitingForApprovalLastEditedTitle);
		Assert.assertTrue(itemsWaitingForApprovalLastEditedTitleElement.isDisplayed(),
				"ERROR: Items Waiting for Approval Approval Last Edited title is not present");
		
		// Verify Approved Scheduled Items Section is listed with all of its
		// items
		
		WebElement approvedScheduledItemsContainerElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", approvedScheduledItemsContainer);
		Assert.assertTrue(approvedScheduledItemsContainerElement.isDisplayed(),
				"ERROR: Approved Scheduled Items container is not present");

		WebElement approvedScheduledItemsCollapseAllButtonElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", approvedScheduledItemsCollapseAllButton);
		Assert.assertTrue(approvedScheduledItemsCollapseAllButtonElement.isDisplayed(),
				"ERROR: Approved Scheduled Items Collapse All Button is not present");	

		WebElement approvedScheduledItemsFilterSelectorElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", approvedScheduledItemsFilterSelector);
		Assert.assertTrue(approvedScheduledItemsFilterSelectorElement.isDisplayed(),
				"ERROR: Approved Scheduled Items Filter Selector is not present");
				
		WebElement approvedScheduledItemsItemsCheckBoxElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", approvedScheduledItemsItemsCheckBox);
		Assert.assertTrue(approvedScheduledItemsItemsCheckBoxElement.isDisplayed(),
				"ERROR: Approved Scheduled Items Select items checkbox is not present");
		
		WebElement approvedScheduledItemsGoToLiveDateTitleElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", approvedScheduledItemsGoToLiveDateTitle);
		Assert.assertTrue(approvedScheduledItemsGoToLiveDateTitleElement.isDisplayed(),
				"ERROR: Approved Scheduled Items Go To Live Date title is not present");	
				
		WebElement approvedScheduledItemsEdittitleElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", approvedScheduledItemsEdittitle);
		Assert.assertTrue(approvedScheduledItemsEdittitleElement.isDisplayed(),
				"ERROR: Approved Scheduled Items Edit Title is not present");
		
		WebElement approvedScheduledItemsUrlTitleElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", approvedScheduledItemsUrlTitle);
		Assert.assertTrue(approvedScheduledItemsUrlTitleElement.isDisplayed(),
				"ERROR: Approved Scheduled Items URL title is not present");	
				
		WebElement aapprovedScheduledLastEditedTitleElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", approvedScheduledLastEditedTitle);
		Assert.assertTrue(aapprovedScheduledLastEditedTitleElement.isDisplayed(),
				"ERROR: Approved Scheduled Items Last Edited title is not present");	
		
		// Verify Recently Published Section is listed with all of its items

		WebElement recentlyPublishedContainerElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentlyPublishedContainer);
		Assert.assertTrue(recentlyPublishedContainerElement.isDisplayed(),
				"ERROR: Recently Published container is not present");
		
		WebElement recentlyPublishedShowLabelElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentlyPublishedShowLabel);
		Assert.assertTrue(recentlyPublishedShowLabelElement.isDisplayed(),
				"ERROR: Recently Published Show label is not present");

		WebElement recentlyPublishedShowLabelInputElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentlyPublishedShowLabelInput);
		Assert.assertTrue(recentlyPublishedShowLabelInputElement.isDisplayed(),
				"ERROR: Recently Published Show label Input is not present");
			
		WebElement recentlyPublishedCollapseAllButtonElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentlyPublishedCollapseAllButton);
		Assert.assertTrue(recentlyPublishedCollapseAllButtonElement.isDisplayed(),
				"ERROR: Recently Published Collapse button is not present");

		WebElement recentlyPublishedFilterSelectorElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentlyPublishedFilterSelector);
		Assert.assertTrue(recentlyPublishedFilterSelectorElement.isDisplayed(),
				"ERROR: Recently Published Filter Selector is not present");
		
		WebElement recentlyPublishedItemsCheckBoxElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentlyPublishedItemsCheckBox);
		Assert.assertTrue(recentlyPublishedItemsCheckBoxElement.isDisplayed(),
				"ERROR: Recently Published Select items checkbox is not present");
		
		WebElement recentlyPublishedItemNameTitleElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentlyPublishedItemNameTitle);
		Assert.assertTrue(recentlyPublishedItemNameTitleElement.isDisplayed(),
				"ERROR: Recently Published Item Name is not present");

		WebElement recentlyPublishedEdittitleElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentlyPublishedEdittitle);
		Assert.assertTrue(recentlyPublishedEdittitleElement.isDisplayed(),
				"ERROR: Recently Published Edit label is not present");
		
		WebElement recentlyPublishedUrlTitleElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentlyPublishedUrlTitle);
		Assert.assertTrue(recentlyPublishedUrlTitleElement.isDisplayed(),
				"ERROR: Recently Published URL title is not present");
	
		WebElement recentlyPublishedServerTitleElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentlyPublishedServerTitle);
		Assert.assertTrue(recentlyPublishedServerTitleElement.isDisplayed(),
				"ERROR: Recently Published Server Title is not present");

		WebElement recentlyPublishedPublishDateTitleElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentlyPublishedPublishDateTitle);
		Assert.assertTrue(recentlyPublishedPublishDateTitleElement.isDisplayed(),
				"ERROR: Recently Published Publish Date Title is not present");
		
		WebElement recentlyPublishedPublishedByTitleElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentlyPublishedPublishedByTitle);
		Assert.assertTrue(recentlyPublishedPublishedByTitleElement.isDisplayed(),
				"ERROR: Recently Published Publish By title is not present");	
		
		// Verify My Recent Activity Section is listed with all of its items
		
		WebElement myRecentActivityContainerElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", myRecentActivityContainer);
		Assert.assertTrue(myRecentActivityContainerElement.isDisplayed(),
				"ERROR: My Recent Activity Section container is not present");

		WebElement myRecentActivityShowLabelElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", myRecentActivityShowLabel);
		Assert.assertTrue(myRecentActivityShowLabelElement.isDisplayed(),
				"ERROR: My Recent Activity Section Show label is not present");
		
		WebElement myRecentActivityShowLabelInputElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", myRecentActivityShowLabelInput);
		Assert.assertTrue(myRecentActivityShowLabelInputElement.isDisplayed(),
				"ERROR: My Recent Activity Section Show label input is not present");
		
		WebElement myRecentActivityHideLiveItemsButtonElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", myRecentActivityHideLiveItemsButton);
		Assert.assertTrue(myRecentActivityHideLiveItemsButtonElement.isDisplayed(),
				"ERROR: My Recent Activity Section hide Live Items button is not present");

		WebElement myRecentActivityFilterSelectorButtonElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", myRecentActivityFilterSelector);
		Assert.assertTrue(myRecentActivityFilterSelectorButtonElement.isDisplayed(),
				"ERROR: My Recent Activity Section Filter Selector is not present");

		WebElement myRecentActivityItemsCheckboxElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", myRecentActivityItemsCheckbox);
		Assert.assertTrue(myRecentActivityItemsCheckboxElement.isDisplayed(),
				"ERROR: My Recent Activity Section Items Checkbox is not present");
				
		WebElement myRecentActivityItemNameTitleElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", myRecentActivityItemNameTitle);
		Assert.assertTrue(myRecentActivityItemNameTitleElement.isDisplayed(),
				"ERROR: My Recent Activity Section Item Name Title is not present");
				
		WebElement myRecentActivityEdittitleElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", myRecentActivityEdittitle);
		Assert.assertTrue(myRecentActivityEdittitleElement.isDisplayed(),
				"ERROR: My Recent Activity Section Edit Title is not present");
	
		WebElement myRecentActivityUrlTitleElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", myRecentActivityUrlTitle);
		Assert.assertTrue(myRecentActivityUrlTitleElement.isDisplayed(),
				"ERROR: My Recent Activity Section URL Title is not present");
		
		WebElement myRecentActivityPublishDateTitleElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", myRecentActivityPublishDateTitle);
		Assert.assertTrue(myRecentActivityPublishDateTitleElement.isDisplayed(),
				"ERROR: My Recent Activity Section Publish Date title is not present");
		
		WebElement myRecentActivityLastEditedBytitleElement  = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", myRecentActivityLastEditedBytitle);
		Assert.assertTrue(myRecentActivityLastEditedBytitleElement.isDisplayed(),
				"ERROR: My Recent Activity Section Last Edited By Title is not present");

		WebElement myRecentActivityMyLastEditTitleElement  = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", myRecentActivityMyLastEditTitle);
		Assert.assertTrue(myRecentActivityMyLastEditTitleElement.isDisplayed(),
				"ERROR: My Recent Activity Section My Last Edit Title is not present");

		// Verify Icon Guide Section is listed with all of its items

		// Assert workflow guide section is present.
		WebElement workflowSection = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
				workflowPanel);
		Assert.assertTrue(workflowSection.isDisplayed(), "Error: Workflow section is not displayed");

		// Assert neverpub is present.
		WebElement neverPublished = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
				neverPublishedItem);
		Assert.assertTrue(neverPublished.isDisplayed(), "Error: Never published item is not present");

		// Assert edited is present.
		WebElement edited = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", editedStateItem);
		Assert.assertTrue(edited.isDisplayed(), "Error: Edited item is not present");

		// Assert in workflow is present.
		WebElement inWorkflow = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", inWorkFlowItem);
		Assert.assertTrue(inWorkflow.isDisplayed(), "Error: Workflow item is not present");

		// Assert scheduled is present.
		WebElement scheduled = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
				scheduledStateItem);
		Assert.assertTrue(scheduled.isDisplayed(), "Error: Scheduled item is not present");

		// Assert processing is present.
		WebElement processing = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
				processingStateItem);
		Assert.assertTrue(processing.isDisplayed(), "Error: Processing item is not present");

		// Assert deleted for edit is present.
		WebElement deleted = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", deletedStateItem);
		Assert.assertTrue(deleted.isDisplayed(), "Error: Never publisehd item is not present");

		// Assert Locked for edit is present.
		WebElement locked = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath", lockedStateItem);
		Assert.assertTrue(locked.isDisplayed(), "Error: Locked item is not present");

	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
