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
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class PreviewPage {
	private WebDriverManager driverManager;
	private String adminConsole;
	private String clickPreviewTools;
	private String expandInContextEditing;
	private String clickInContextEdit;
	private String approvePublish;
	private String submitButtonApprovePublish;
	private String previewDuplicate;
	private String duplicateName;
	private String expandPagesTree;
	private String expandHomeTree;
	private String previewDelete;
	private String deleteDependencies;
	private String OKdeleteDependencies;
	private String previewEdit;
	private String saveAndCloseiFrame;
	private String previewHistory;
	private String previewDependecies;
	private String dependenciesSelector;
	private String dependenciesCloseButton;
	private String siteconfigPublishingOperationsoption;
	private String bulkOperationsPathToPublishInput;
	private String bulkoperationsPublishButton;
	private String bulkoperationsAcceptWarning;
	private String bulkoperationsMessage;
	private SiteConfigPage siteConfigPage;
	private DashboardPage dashboardPage;
	private String studioLogo;
	private String siteDropdownElementXPath;
	private String adminConsoleXpath;
	private String entryContentTypeBodyXpath;
	private String entryContentTypeBodyCheckXpath;
	private String createFormFrameElementCss;
	private String articleContentCreatedName;
	private String generalDeleteOption;
	private String generalEditOption;
	private String siteStatusIcon;
	private String siteContentXpath;
	private String articlesContentTypeRepeatingGroup;
	private String gearItemXpath;
	private String bulkPublishTab;
	private String publishingFrame;
	private String siteDropdownListElementXPath;
	private String lastPropertiesElementCssSelector;
	private String dependenciesForXpath;
	private String articlesContentTypeDate;
	private String itemsTree;
	private String itemsSubtree;
	private String authorsTree;
	private String publishingStatusOption;
	private String searchOption;
	private String previewToolsOption;
	private String inContextEditOption;
	private String draftSavedNotification;
	private static Logger logger = LogManager.getLogger(PreviewPage.class);

	public PreviewPage(WebDriverManager driverManager,
			UIElementsPropertiesManager UIElementsPropertiesManager) {
		this.driverManager = driverManager;
		this.siteConfigPage = new SiteConfigPage(driverManager, UIElementsPropertiesManager);
		this.dashboardPage = new DashboardPage(driverManager, UIElementsPropertiesManager);
		adminConsole = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sitecontent.siteconfig");
		clickPreviewTools = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.previewtools.previewtoolsoption");
		expandInContextEditing = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.previewtools.expansorincontextediting");
		clickInContextEdit = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.previewtools.enabledisableincontextedit");
		approvePublish = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.approve&publish");
		submitButtonApprovePublish = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.approve&publish.submit");
		previewDuplicate = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.duplicate");
		duplicateName = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.onduplicate.internalname");
		expandPagesTree = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sitecontent.expandpages");
		expandHomeTree = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sitecontent.expandhome");
		previewDelete = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.delete");
		deleteDependencies = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.delete.delete");
		OKdeleteDependencies = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.submitall.ok");
		previewEdit = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.edittopnavoption");
		saveAndCloseiFrame = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame1.save_close");
		previewHistory = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.historytopnavoption");
		previewDependecies = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.dependenciestopnavoption");
		publishingStatusOption = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.publishingstatustopnavoption");
		searchOption = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.searchtopnavoption");
		previewToolsOption = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.previewtoolstopnavoption");
		inContextEditOption = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.incontextedittopnavoption");
		dependenciesSelector = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dependencies.content_selector");
		dependenciesCloseButton = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dependencies.close_button");
		siteconfigPublishingOperationsoption = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.publish_operations_option");
		bulkOperationsPathToPublishInput = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("bulkoperations.path_to_publish_input");
		bulkPublishTab = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("bulkoperations.bulkpublishtab");
		bulkoperationsPublishButton = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("bulkoperations.publish_button");
		bulkoperationsAcceptWarning = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("bulkoperations.accept_warning");
		bulkoperationsMessage = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("bulkoperations.message");
		studioLogo = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.studiologo");
		siteDropdownElementXPath = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		siteDropdownListElementXPath = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdownlielement");
		adminConsoleXpath = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.adminconsole");
		entryContentTypeBodyXpath = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.entrycontenttype.body");
		lastPropertiesElementCssSelector = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.entrycontenttype.propertiesdivlastelement");
		entryContentTypeBodyCheckXpath = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.entrycontenttype.bodyrequiredcheck");
		createFormFrameElementCss = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformframe");
		articleContentCreatedName = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.testingcontentitem");
		generalDeleteOption = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.deletetopnavoption");
		generalEditOption = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.edittopnavoption");
		siteStatusIcon = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.statustopbaricon");
		siteContentXpath = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.site_content");
		articlesContentTypeRepeatingGroup = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.edit.articles.content.type.sections.repeating.group");
		articlesContentTypeDate = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.edit.articles.content.type.date");
		gearItemXpath = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.gearlocator");
		publishingFrame = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("bulkoperations.frame");
		dependenciesForXpath = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.dependencies.dependeciesforitem");
		itemsTree = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sitecontent.itemstree");
		itemsSubtree = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sitecontent.itemssubtree");
		authorsTree = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sitecontent.authorstree");
		draftSavedNotification = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("preview.saved_draft_notification");
	}

	// Click on admin console link
	public void clickAdminConsoleOption() {
		driverManager.clickElement("xpath", adminConsole);
	}

	public void goToAdminConsolePage() {
		// Click on admin console link
		this.clickAdminConsoleOption();
	}

	// Click on preview tools icon
	public void previewTools() {
		WebElement toolsIcon = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				clickPreviewTools);
		toolsIcon.click();
	}

	public void clickOnPreviewTools() {
		// Click on preview tools icon
		this.previewTools();
	}

	// Expand the In Context Menu
	public void expandInContextEditing() {
		WebElement expandInContextMenu = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", expandInContextEditing);
		expandInContextMenu.click();
	}

	public void clickToExpandInContextEditing() {
		// Expand the In Context Menu
		this.expandInContextEditing();
	}

	// Enable/Diseble In-Context Editing
	public void inContextEditing() {
		WebElement inContextEditingOption = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", clickInContextEdit);
		inContextEditingOption.click();
	}

	public void clickToEnableDisableInContextEditing() {
		// Enable/Diseble In-Context Editing
		this.inContextEditing();
	}

	// Click on Approve&Publish option
	public void approvePublish() {
		WebElement publishIcon = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", approvePublish);
		publishIcon.click();
	}

	public void clickOnApprovePublish() {
		// Click on Approve&Publish option
		this.approvePublish();
	}

	// Click on submit button of Approve&Publish
	public void submitApprovePublish() {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				submitButtonApprovePublish);
		WebElement toolsIcon = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				submitButtonApprovePublish);
		toolsIcon.click();
	}

	public void clickOnSubmitButtonOfApprovePublish() {
		// Click on submit button of Approve&Publish
		this.submitApprovePublish();
	}

	// Click on duplicate button of the menu
	public void duplicateButton() {
		WebElement duplicateOption = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				previewDuplicate);
		duplicateOption.click();
	}

	public void clickOnDuplicateOption() {
		// Click on duplicate button of the menu
		this.duplicateButton();
	}

	public WebDriverManager getDriverManager() {
		return driverManager;
	}

	// Click on delete button of the menu
	public void deleteButton() {
		WebElement deleteOption = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				previewDelete);
		deleteOption.click();
	}

	public void clickOnDeleteOption() {
		// Click on delete button of the menu
		this.deleteButton();
	}

	// Click on delete dependencies
	public void deleteDependencies() {
		WebElement deleteDepen = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				deleteDependencies);
		deleteDepen.click();
	}

	public void clickOnDeleteDependencies() {
		// Click on delete dependencies
		this.deleteDependencies();
	}

	// Click on OK to delete dependencies
	public void okDeleteDependencies() {
		WebElement OKdeleteDepen = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				OKdeleteDependencies);
		OKdeleteDepen.click();
	}

	public void clickOnOKDeleteDependencies() {
		// Click on OK to delete dependencies
		this.okDeleteDependencies();
	}

	// Click on edit button of the menu
	public void EditButton() {
		WebElement editOption = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				previewEdit);
		editOption.click();
	}

	public void clickOnEditOption() {
		// Click on edit button of the menu
		this.EditButton();
	}

	// Click on history button of the menu
	public void historyButton() {
		WebElement historyOption = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				previewHistory);
		historyOption.click();
	}

	public void clickOnHistoryOption() {
		// Click on history button of the menu
		this.historyButton();
	}

	// Click on dependencies button of the menu
	public void dependenciesButton() {
		WebElement dependenciesOption = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", previewDependecies);
		dependenciesOption.click();
	}

	public void clickOnDependenciesOption() {
		// Click on dependencies button of the menu
		this.dependenciesButton();
	}

	public void clickOnPublishingStatusOption() {
		WebElement publishingStatusOptionElement = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", publishingStatusOption);
		publishingStatusOptionElement.click();
	}

	public void clickOnSearchOption() {
		WebElement searchOptionElement = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", searchOption);
		searchOptionElement.click();
	}

	public void clickOnPreviewToolsOption() {
		WebElement previewToolsOptionElement = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", previewToolsOption);
		previewToolsOptionElement.click();
	}

	public void clickOnInContextEditOption() {
		WebElement inContextEditOptionElement = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", inContextEditOption);
		inContextEditOptionElement.click();
	}

	// Set the new name duplicated
	public void duplicateName(String strDuplicateName) {
		WebElement internalName = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				duplicateName);
		internalName.sendKeys(strDuplicateName);
	}

	public void setDuplicateName(String strDuplicateName) {
		// Set the new name duplicated
		this.duplicateName(strDuplicateName);
	}

	// Expand pages tree
	public void clickPagesTree() {
		WebElement expandPagesTree = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				this.expandPagesTree);
		expandPagesTree.click();
	}

	public void expandPagesTree() {
		// Expand pages tree
		this.clickPagesTree();
	}

	public void expandItemsTree() {
		WebElement expandItemsTree = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				this.itemsTree);
		expandItemsTree.click();
	}

	public void expandItemsSubtree() {
		WebElement expandItemsSubtree = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", this.itemsSubtree);
		expandItemsSubtree.click();
	}

	public void expandAuthorsTree() {
		this.driverManager.waitUntilContentTooltipIsHidden();
		WebElement expandAuthorsTree = this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				this.authorsTree);
		expandAuthorsTree.click();
	}

	// Expand global entry content
	public void clickHomeContent() {
		this.driverManager.isElementPresentAndClickableByXpath(expandHomeTree);
		WebElement homeContent = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", expandHomeTree);
		homeContent.click();
	}

	public void expandHomeTree() {
		// Expand global entry content
		this.clickHomeContent();
	}

	// Click save and close
	public void saveAndCloseButton() {
		WebElement saveClose = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				saveAndCloseiFrame);
		saveClose.click();
	}

	public void clickOnSaveAndCloseButton() {
		// Click save and close
		this.saveAndCloseButton();
	}

	public void changeBodyOfEntryContentPageToNotRequired() {
		// Show site content panel
		this.driverManager.clickElement("xpath", siteDropdownElementXPath);
		// go to admin console page
		this.driverManager.clickElement("xpath", adminConsoleXpath);
		// select content types
		siteConfigPage.selectContentTypeOption();
		// open content types
		siteConfigPage.clickExistingTypeOption();
		// Confirm the content type selected
		siteConfigPage.confirmContentTypeSelected();
		// wait for element is clickeable
		driverManager.getDriver().switchTo().defaultContent();
		// select main content
		this.driverManager.waitUntilSiteConfigMaskedModalCloses();
		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", entryContentTypeBodyXpath)
				.click();
		// Mark Body not required
		this.driverManager.waitForAnimation();
		this.driverManager.focusAndScrollDownToBottomInASection("#properties-container",
				lastPropertiesElementCssSelector);
		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", entryContentTypeBodyCheckXpath).click();
		// save
		this.driverManager.waitForAnimation();
		siteConfigPage.saveDragAndDropProcess();
		driverManager.getDriver().switchTo().defaultContent();
		// go to dashboard
		this.driverManager.getDriver().navigate().refresh();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", studioLogo)
				.click();
	}

	public void modifyPageXMLDefinitionContentAsFolderEntryContentType(String configurationSetUp) {
		// Show site content panel
		if (!(this.driverManager.waitUntilElementIsPresent("xpath", siteDropdownListElementXPath)
				.getAttribute("class").contains("site-dropdown-open")))
			this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
					siteDropdownElementXPath).click();
		// go to admin console page
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", adminConsoleXpath)
				.click();
		// select content types
		siteConfigPage.selectContentTypeOption();
		// open content types
		siteConfigPage.clickExistingTypeOption();
		// Confirm the content type selected
		siteConfigPage.confirmContentTypeSelected();
		// wait for element is clickeable
		driverManager.getDriver().switchTo().defaultContent();
		// select main content
		this.driverManager.waitUntilSiteConfigMaskedModalCloses();
		this.driverManager.waitForAnimation();

		this.driverManager.waitUntilElementIsClickable("xpath",
				".//div[@class='property-label label-configuration']/../input").click();

		// Click on pencil icon
		// on chrome the div id="properties-container" over the pencil doesn't allow to click it
		// try normal click then with js
		this.driverManager.clickElement("cssselector", ".edit.fa-pencil");

		this.driverManager.waitForAnimation();
		this.driverManager.getDriver().switchTo().activeElement();

		this.driverManager.setContentTypeConfiguration(configurationSetUp);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				".//div[@id='template-editor-update-button']").click();

		// save
		this.driverManager.waitForAnimation();
		siteConfigPage.saveDragAndDropProcess();
		driverManager.getDriver().switchTo().defaultContent();
		
		// go to dashboard
		this.driverManager.getDriver().navigate().refresh();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", studioLogo)
				.click();
	}

	public void modifyPageXMLDefinitionContentAsFolderForPageArticle(String configurationSetUp) {
		// Show site content panel
		if (!(this.driverManager.waitUntilElementIsPresent("xpath", siteDropdownListElementXPath)
				.getAttribute("class").contains("site-dropdown-open")))
			this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
					siteDropdownElementXPath).click();
		// go to admin console page
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", adminConsoleXpath)
				.click();
		// select content types
		siteConfigPage.selectContentTypeOption();
		// open content types
		siteConfigPage.clickExistingTypeOption();
		// select Page Article content type
		siteConfigPage.selectPageArticleContentType();
		// Confirm the content type selected
		siteConfigPage.confirmContentTypeSelected();
		// wait for element is clickeable
		driverManager.getDriver().switchTo().defaultContent();
		// select main content
		this.driverManager.waitUntilSiteConfigMaskedModalCloses();
		this.driverManager.waitForAnimation();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				".//div[@class='property-label label-configuration']/../input").click();

		// Click on pencil icon
		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
						".//div[@class='property-label label-configuration']/../div[@class='options']/div")
				.click();

		this.driverManager.waitForAnimation();
		this.driverManager.getDriver().switchTo().activeElement();
		
		this.driverManager.setContentTypeConfiguration(configurationSetUp);


		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				".//div[@id='template-editor-update-button']").click();

		// save
		this.driverManager.waitForAnimation();
		siteConfigPage.saveDragAndDropProcess();
		driverManager.getDriver().switchTo().defaultContent();
		// go to dashboard
		this.driverManager.getDriver().navigate().refresh();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", studioLogo)
				.click();
	}

	public void changeDateOfArticlePageToNotRequired() {
		// Show site content panel
		if (!(this.driverManager.waitUntilElementIsPresent("xpath", siteDropdownListElementXPath)
				.getAttribute("class").contains("site-dropdown-open")))
			this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", siteContentXpath).click();
		// go to admin console page
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", adminConsoleXpath).click();
		// Click on Content Types Option
		siteConfigPage.clickContentTypeOption();
		// open content types
		siteConfigPage.clickExistingTypeOption();
		// select content types
		siteConfigPage.selectPageArticleContentType();
		// Confirm the content type selected
		siteConfigPage.confirmContentTypeSelected();
		// wait for element is clickeable
		driverManager.getDriver().switchTo().defaultContent();
		// Scroll Down to select the item
		this.driverManager.scrollDown();
		// select main content
		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", articlesContentTypeDate)
				.click();
		// Mark Body not required
		this.driverManager.waitForAnimation();
		this.driverManager.focusAndScrollDownToBottomInASection("#properties-container",
				lastPropertiesElementCssSelector);
		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", entryContentTypeBodyCheckXpath).click();
		// save
		siteConfigPage.saveDragAndDropProcess();
		this.driverManager.getDriver().switchTo().defaultContent();
		// go to dashboard
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", studioLogo)
				.click();
	}

	public void changeBodyOfArticlePageToNotRequired() {
		// go to admin console page
		this.driverManager.clickElement("xpath", adminConsoleXpath);
		// Click on Content Types Option
		siteConfigPage.clickContentTypeOption();
		// open content types
		siteConfigPage.clickExistingTypeOption();
		// select content types
		siteConfigPage.selectPageArticleContentType();
		// Confirm the content type selected
		siteConfigPage.confirmContentTypeSelected();
		// wait for element is clickeable
		driverManager.getDriver().switchTo().defaultContent();
		// Scroll Down to select the item
		this.driverManager.scrollDown();
		// select main content
		this.driverManager.waitForAnimation();
		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", articlesContentTypeRepeatingGroup)
				.click();
		// Mark Body not required
		this.driverManager.waitForAnimation();
		this.driverManager.focusAndScrollDownToBottomInASection("#properties-container",
				lastPropertiesElementCssSelector);
		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", entryContentTypeBodyCheckXpath).click();
		// save
		siteConfigPage.saveDragAndDropProcess();
		this.driverManager.getDriver().switchTo().defaultContent();
		// go to dashboard
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", studioLogo)
				.click();
	}

	public void createPageArticleContentUsingUploadedImage(String url, String name, String title,
			String folderLocation, String selectedSegments, String selectedCategories, String subject,
			String author, String summary) {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", folderLocation);
		// right click to see the the menu
		dashboardPage.rightClickToSeeMenuOfSpecificFolder(folderLocation);
		// Select Entry Content Type
		dashboardPage.clickEntryCT();
		// Confirm the Content Type selected
		dashboardPage.clickOKButton();
		this.driverManager.waitForAnimation();
		this.driverManager.waitForFullExpansionOfTree();
		this.driverManager.waitForAnimation();
		driverManager.usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {
			// Fill the New Article page Fields
			this.driverManager.waitForAnimation();
			dashboardPage.setPageURL1(url);
			this.driverManager.waitForAnimation();
			dashboardPage.setInternalName1(name);
			this.driverManager.waitForAnimation();
			dashboardPage.setArticlesTitle(title);
			this.driverManager.waitForAnimation();
			// Fill the New Article Content Section
			this.driverManager.scrollDown();
			this.driverManager.waitForAnimation();
			dashboardPage.setNewArticleContentSection(subject, author, summary);
			// Select the catergory of the Article Page
			this.driverManager.scrollMiddle();
			this.driverManager.waitForAnimation();
			dashboardPage.selectCategoriesOfNewPageArticle(selectedCategories);
			// Select the segment of the Article Page
			this.driverManager.waitForAnimation();
			dashboardPage.selectSegmentsOfNewPageArticle(selectedSegments);
			this.driverManager.scrollDown();
			// Add an Image
			this.driverManager.waitForAnimation();
			dashboardPage.addAnImageToAnArticleUsingUploadOption();
			// Switch to the iframe
			driverManager.getDriver().switchTo().defaultContent();
			driverManager.getDriver().switchTo()
					.frame(this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("cssSelector",
							".studio-ice-dialog > .bd iframe"));
			this.driverManager.isElementPresentAndClickableBycssSelector(".studio-ice-dialog > .bd iframe");
			// save and close
			this.driverManager.waitForAnimation();
			this.driverManager.waitForFullExpansionOfTree();
			this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("id", "cstudioSaveAndClose")
					.click();
		});
		this.driverManager.waitUntilSidebarOpens();
	}

	public void createPageArticleContent(String url, String name, String title, String folderLocation,
			String selectedSegments, String selectedCategories, String subject, String author,
			String summary) {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", folderLocation);
		// right click to see the the menu
		dashboardPage.rightClickToSeeMenuOfSpecificFolder(folderLocation);
		// Select Entry Content Type
		dashboardPage.clickEntryCT();
		// Confirm the Content Type selected
		dashboardPage.clickOKButton();
		this.driverManager.waitForAnimation();
		this.driverManager.waitForAnimation();
		driverManager.usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {
			// Fill the New Article page Fields
			this.driverManager.waitForAnimation();
			dashboardPage.setPageURL1(url);
			this.driverManager.waitForAnimation();
			dashboardPage.setInternalName1(name);
			this.driverManager.waitForAnimation();
			dashboardPage.setArticlesTitle(title);
			this.driverManager.waitForAnimation();
			// Fill the New Article Content Section
			this.driverManager.scrollDown();
			this.driverManager.waitForAnimation();
			dashboardPage.setNewArticleContentSection(subject, author, summary);
			// Select the catergory of the Article Page
			this.driverManager.scrollMiddle();
			this.driverManager.waitForAnimation();
			dashboardPage.selectCategoriesOfNewPageArticle(selectedCategories);
			// Select the segment of the Article Page
			this.driverManager.waitForAnimation();
			dashboardPage.selectSegmentsOfNewPageArticle(selectedSegments);
			this.driverManager.scrollDown();
			// Add an Image
			this.driverManager.waitForAnimation();
			dashboardPage.addAnImageToAnArticle();
			// Switch to the iframe
			driverManager.getDriver().switchTo().defaultContent();
			driverManager.getDriver().switchTo()
					.frame(this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("cssSelector",
							".studio-ice-dialog > .bd iframe"));
			this.driverManager.isElementPresentAndClickableBycssSelector(".studio-ice-dialog > .bd iframe");
			// save and close
			this.driverManager.waitForAnimation();
			this.driverManager.waitForFullExpansionOfTree();
			this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("id", "cstudioSaveAndClose")
					.click();
		});
		this.driverManager.waitUntilSidebarOpens();

	}

	public void createPageArticleContentUsingWinterWomanPicture(String url, String name, String title,
			String folderLocation, String selectedSegments, String selectedCategories, String subject,
			String author, String summary) {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", folderLocation);
		// right click to see the the menu
		dashboardPage.rightClickToSeeMenuOfSpecificFolder(folderLocation);
		// Select Entry Content Type
		dashboardPage.clickEntryCT();
		// Confirm the Content Type selected
		dashboardPage.clickOKButton();
		this.driverManager.waitForAnimation();
		this.driverManager.waitForAnimation();
		driverManager.usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {
			// Fill the New Article page Fields
			this.driverManager.waitForAnimation();
			dashboardPage.setPageURL1(url);
			this.driverManager.waitForAnimation();
			dashboardPage.setInternalName1(name);
			this.driverManager.waitForAnimation();
			dashboardPage.setArticlesTitle(title);
			this.driverManager.waitForAnimation();
			// Fill the New Article Content Section
			this.driverManager.scrollDown();
			this.driverManager.waitForAnimation();
			dashboardPage.setNewArticleContentSection(subject, author, summary);
			// Select the catergory of the Article Page
			this.driverManager.scrollMiddle();
			this.driverManager.waitForAnimation();
			dashboardPage.selectCategoriesOfNewPageArticle(selectedCategories);
			// Select the segment of the Article Page
			this.driverManager.waitForAnimation();
			dashboardPage.selectSegmentsOfNewPageArticle(selectedSegments);
			this.driverManager.scrollDown();
			// Add an Image
			this.driverManager.waitForAnimation();
			dashboardPage.addWinterWomanAssetImageToAnArticle();
			// Switch to the iframe
			driverManager.getDriver().switchTo().defaultContent();
			driverManager.getDriver().switchTo()
					.frame(this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("cssSelector",
							".studio-ice-dialog > .bd iframe"));
			this.driverManager.isElementPresentAndClickableBycssSelector(".studio-ice-dialog > .bd iframe");
			// save and close
			this.driverManager.waitForAnimation();
			this.driverManager.waitForFullExpansionOfTree();
			this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("id", "cstudioSaveAndClose")
					.click();
		});
		this.driverManager.waitUntilSidebarOpens();

	}

	public void createPageArticleContentAsDraft(String url, String name, String title, String folderLocation,
			String selectedSegments, String selectedCategories, String subject, String author,
			String summary) {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", folderLocation);
		// right click to see the the menu
		dashboardPage.rightClickToSeeMenuOfSpecificFolder(folderLocation);
		// Select Entry Content Type
		dashboardPage.clickEntryCT();
		// Confirm the Content Type selected
		dashboardPage.clickOKButton();
		this.driverManager.waitForAnimation();
		this.driverManager.waitForAnimation();
		driverManager.usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {
			// Fill the New Article page Fields
			this.driverManager.waitForAnimation();
			dashboardPage.setPageURL1(url);
			this.driverManager.waitForAnimation();
			dashboardPage.setInternalName1(name);
			this.driverManager.waitForAnimation();
			dashboardPage.setArticlesTitle(title);
			this.driverManager.waitForAnimation();
			// Fill the New Article Content Section
			this.driverManager.scrollDown();
			this.driverManager.waitForAnimation();
			dashboardPage.setNewArticleContentSection(subject, author, summary);
			// Select the catergory of the Article Page
			this.driverManager.scrollMiddle();
			this.driverManager.waitForAnimation();
			dashboardPage.selectCategoriesOfNewPageArticle(selectedCategories);
			// Select the segment of the Article Page
			this.driverManager.waitForAnimation();
			dashboardPage.selectSegmentsOfNewPageArticle(selectedSegments);
			this.driverManager.scrollDown();
			// Add an Image
			this.driverManager.waitForAnimation();

			// Switch to the iframe
			driverManager.getDriver().switchTo().defaultContent();
			driverManager.getDriver().switchTo()
					.frame(this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("cssSelector",
							".studio-ice-dialog > .bd iframe"));
			this.driverManager.isElementPresentAndClickableBycssSelector(".studio-ice-dialog > .bd iframe");
			// save and close
			this.driverManager.waitForAnimation();
			this.driverManager.waitForFullExpansionOfTree();
			this.driverManager
					.driverWaitUntilElementIsPresentAndDisplayedAndClickable("id", "cstudioSaveAndCloseDraft")
					.click();
			this.driverManager.waitUntilElementIsDisplayed("xpath", draftSavedNotification);
			this.driverManager.waitUntilElementIsNotDisplayed("xpath", draftSavedNotification);
			this.driverManager.waitForFullExpansionOfTree();
			this.driverManager
					.driverWaitUntilElementIsPresentAndDisplayedAndClickable("id", "colExpButtonBtn").click();

			this.driverManager.waitForFullExpansionOfTree();
			this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("id", "cancelBtn")
					.click();
		});
		this.driverManager.waitUntilSidebarOpens();
	}

	public void checkDependencies() {
		this.driverManager.waitForAnimation();
		// Switch to the frame
		driverManager.getDriver().switchTo().defaultContent();
		driverManager.getDriver().switchTo().activeElement();
		driverManager.waitUntilPageLoad();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				dependenciesSelector);
		Select categoriesDropDown = new Select(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", dependenciesSelector));
		categoriesDropDown.selectByValue("depends-on");
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", gearItemXpath);
		Assert.assertTrue(this.getDriverManager().isElementPresentByXpath(gearItemXpath));
		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dependenciesCloseButton)
				.click();
	}

	public void checkNoDependenciesForItem(String itemText, boolean dependsOn) {
		this.driverManager.waitForAnimation();
		// Switch to the frame
		driverManager.getDriver().switchTo().defaultContent();
		driverManager.getDriver().switchTo().activeElement();
		driverManager.waitUntilPageLoad();
		// checking if the item name is the correct on the dependencies dialog
		this.driverManager.waitForAnimation();
		this.driverManager.waitForFullExpansionOfTree();
		WebElement dependenciesForItemElement = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", dependenciesForXpath);
		Assert.assertTrue(dependenciesForItemElement.getText().equalsIgnoreCase(itemText));

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				dependenciesSelector);
		Select categoriesDropDown = new Select(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", dependenciesSelector));

		if (dependsOn) {
			// depends on case
			categoriesDropDown.selectByValue("depends-on");
		} else {
			// dependes on me case
			categoriesDropDown.selectByValue("depends-on-me");
		}

		this.driverManager.waitForFullExpansionOfTree();
		List<WebElement> dependeciesItems = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath",
						".//div[@id='dependencies-dialog']//table[contains(@class,'item-listing')]/tbody")
				.findElements(By.tagName("tr"));
		Assert.assertTrue(dependeciesItems.size() == 0);
		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dependenciesCloseButton)
				.click();
	}

	public void checkDependentItemsForPage(String pageName, WebElement element) {
		String dependentItemName = element.findElement(By.xpath("td[1]")).getText();
		String dependentItemLocation = element.findElement(By.xpath("td[2]/div")).getText();
		boolean firstCheckPass = false;
		boolean secondCheckPass = false;

		switch (pageName) {
		case "Home":
			if ((dependentItemName.equalsIgnoreCase("home.ftl"))
					|| (dependentItemName.equalsIgnoreCase("home.groovy"))
					|| (dependentItemName.equalsIgnoreCase("strawberries.jpg"))
					|| (dependentItemName.equalsIgnoreCase("Three"))
					|| (dependentItemName.equalsIgnoreCase("Two"))
					|| (dependentItemName.equalsIgnoreCase("Left Rail with Latest Articles"))) {
				firstCheckPass = true;
			}
			if ((dependentItemLocation.equalsIgnoreCase("/templates/web/pages/home.ftl"))
					|| (dependentItemLocation.equalsIgnoreCase("/scripts/pages/home.groovy"))
					|| (dependentItemLocation.equalsIgnoreCase("/static-assets/images/strawberries.jpg"))
					|| (dependentItemLocation
							.equalsIgnoreCase("/site/components/features/sapien-veroeros.xml"))
					|| (dependentItemLocation
							.equalsIgnoreCase("/site/components/features/quam-lorem-ipsum.xml"))
					|| (dependentItemLocation.equalsIgnoreCase(
							"/site/components/left-rails/left-rail-with-latest-articles.xml"))) {
				secondCheckPass = true;
			}
			Assert.assertTrue(firstCheckPass);
			Assert.assertTrue(secondCheckPass);
			break;
		case "Style":
			if ((dependentItemName.equalsIgnoreCase("category-landing.groovy"))
					|| (dependentItemName.equalsIgnoreCase("category-landing.ftl"))) {
				firstCheckPass = true;
			}
			if ((dependentItemLocation.equalsIgnoreCase("/scripts/pages/category-landing.groovy"))
					|| (dependentItemLocation
							.equalsIgnoreCase("/templates/web/pages/category-landing.ftl"))) {
				secondCheckPass = true;
			}
			Assert.assertTrue(firstCheckPass);
			Assert.assertTrue(secondCheckPass);
			break;
		case "Women Styles for Winter":
			if ((dependentItemName.equalsIgnoreCase("article.ftl"))
					|| (dependentItemName.equalsIgnoreCase("winter-woman-pic.jpg"))) {
				firstCheckPass = true;
			}
			if ((dependentItemLocation.equalsIgnoreCase("/templates/web/pages/article.ftl"))
					|| (dependentItemLocation
							.equalsIgnoreCase("/static-assets/images/winter-woman-pic.jpg"))) {
				secondCheckPass = true;
			}
			Assert.assertTrue(firstCheckPass);
			Assert.assertTrue(secondCheckPass);
			break;
		case "Testing1":
			String testImageLocation = "/static-assets/item/images/" + this.driverManager.getCurrentYear()
					+ "/" + this.driverManager.getCurrentMonth() + "/" + this.driverManager.getCurrentDay()
					+ "/testimage.jpg";

			if ((dependentItemName.equalsIgnoreCase("article.ftl"))
					|| (dependentItemName.equalsIgnoreCase("testimage.jpg"))) {
				firstCheckPass = true;
			}
			if ((dependentItemLocation.equalsIgnoreCase("/templates/web/pages/article.ftl"))
					|| (dependentItemLocation.contains(testImageLocation))) {
				secondCheckPass = true;
			}
			Assert.assertTrue(firstCheckPass);
			Assert.assertTrue(secondCheckPass);
			break;
		case "Search Results":
			if ((dependentItemName.equalsIgnoreCase("search-results.groovy"))
					|| (dependentItemName.equalsIgnoreCase("Left Rail with Latest Articles"))
					||  (dependentItemName.equalsIgnoreCase("Categories"))
					|| (dependentItemName.equalsIgnoreCase("search-results.ftl"))) {
				firstCheckPass = true;
			}
			if ((dependentItemLocation.equalsIgnoreCase("/scripts/pages/search-results.groovy"))
					|| (dependentItemLocation.equalsIgnoreCase(
							"/site/components/left-rails/left-rail-with-latest-articles.xml"))
					||  (dependentItemLocation.equalsIgnoreCase("/site/taxonomy/categories.xml"))
					|| (dependentItemLocation.equalsIgnoreCase("/templates/web/pages/search-results.ftl"))) {
				secondCheckPass = true;
			}
			Assert.assertTrue(firstCheckPass);
			Assert.assertTrue(secondCheckPass);
			break;
		default:
			throw new IllegalArgumentException("No page case for provided page name: " + pageName);
		}
	}

	public void checkDependenciesForPageItem(String staticAssetName, boolean dependsOn) {
		this.driverManager.waitForAnimation();
		// Switch to the frame
		driverManager.getDriver().switchTo().defaultContent();
		driverManager.getDriver().switchTo().activeElement();
		driverManager.waitUntilPageLoad();
		this.driverManager.waitForAnimation();

		// checking if the item name is the correct on the dependencies dialog
		this.driverManager.waitForAnimation();
		this.driverManager.waitForFullExpansionOfTree();
		WebElement dependenciesForItemElement = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", dependenciesForXpath);
		Assert.assertTrue(dependenciesForItemElement.getText().equalsIgnoreCase(staticAssetName));

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				dependenciesSelector);
		Select categoriesDropDown = new Select(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", dependenciesSelector));
		if (dependsOn) {
			// depends on case
			categoriesDropDown.selectByValue("depends-on");
		} else {
			// depends on me case
			categoriesDropDown.selectByValue("depends-on-me");
		}

		this.driverManager.waitForFullExpansionOfTree();
		List<WebElement> dependeciesItems = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath",
						".//div[@id='dependencies-dialog']//table[contains(@class,'item-listing')]/tbody")
				.findElements(By.tagName("tr"));
		this.checkNumberOfItemOnDependencies(staticAssetName, dependeciesItems, dependsOn);

		for (WebElement webElement : dependeciesItems) {
			this.checkDependentItemsForPage(staticAssetName, webElement);
		}
		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dependenciesCloseButton)
				.click();

	}

	public void checkDependentItemsForStaticAsset(String staticAssetName, WebElement element) {
		String dependentItemName = element.findElement(By.xpath("td[1]")).getText();
		String dependentItemLocation = element.findElement(By.xpath("td[2]/div")).getText();
		boolean firstCheckPass = false;
		boolean secondCheckPass = false;

		switch (staticAssetName) {
		case "book-woman-pic.jpg":
			Assert.assertTrue("Top Books For Young Women".equalsIgnoreCase(dependentItemName));
			Assert.assertTrue("/site/website/articles/2016/12/top-books-for-young-women/index.xml"
					.equalsIgnoreCase(dependentItemLocation));
			break;
		case "duplication-winter-woman-pic.jpg":
			if (("Testing2".equalsIgnoreCase(dependentItemName))
					|| ("Women Styles for Winter".equalsIgnoreCase(dependentItemName))
					|| ("Testing2Duplicated".equalsIgnoreCase(dependentItemName))) {
				firstCheckPass = true;
			}
			if (("/site/website/articles/2017/1/women-styles-for-winter/index.xml"
					.equalsIgnoreCase(dependentItemLocation))
					|| ("/site/website/articles/2017/1/test2/index.xml"
							.equalsIgnoreCase(dependentItemLocation))
					|| ((dependentItemLocation.contains("/site/website/articles/2017/1/test2-"))
							&& (dependentItemLocation.contains("/index.xml")))
					|| ((dependentItemLocation.contains("/site/website/articles/2017/2/test2-"))
							&& (dependentItemLocation.contains("/index.xml")))) {
				secondCheckPass = true;
			}

			Assert.assertTrue(firstCheckPass);
			Assert.assertTrue(secondCheckPass);
			break;
		case "winter-woman-pic.jpg":
			if (("Women Styles for Winter".equalsIgnoreCase(dependentItemName))
					|| ("Testing2".equalsIgnoreCase(dependentItemName))) {
				firstCheckPass = true;
			}
			if (("/site/website/articles/2017/1/women-styles-for-winter/index.xml"
					.equalsIgnoreCase(dependentItemLocation))
					|| ("/site/website/articles/2017/1/test2/index.xml"
							.equalsIgnoreCase(dependentItemLocation))
					|| ("/site/website/articles/2017/2/test2/index.xml"
							.equalsIgnoreCase(dependentItemLocation))) {
				secondCheckPass = true;
			}

			Assert.assertTrue(firstCheckPass);
			Assert.assertTrue(secondCheckPass);
			break;
		case "ie8.css":
			if (("404.ftl".equalsIgnoreCase(dependentItemName))
					|| ("500.ftl".equalsIgnoreCase(dependentItemName))
					|| ("article.ftl".equalsIgnoreCase(dependentItemName))
					|| ("home.ftl".equalsIgnoreCase(dependentItemName))
					|| ("search-results.ftl".equalsIgnoreCase(dependentItemName))
					|| ("category-landing.ftl".equalsIgnoreCase(dependentItemName))) {
				firstCheckPass = true;
			}
			if (("/templates/web/errors/404.ftl".equalsIgnoreCase(dependentItemLocation))
					|| ("/templates/web/errors/500.ftl".equalsIgnoreCase(dependentItemLocation))
					|| ("/templates/web/pages/article.ftl".equalsIgnoreCase(dependentItemLocation))
					|| ("/templates/web/pages/home.ftl".equalsIgnoreCase(dependentItemLocation))
					|| ("/templates/web/pages/search-results.ftl".equalsIgnoreCase(dependentItemLocation))
					|| ("/templates/web/pages/category-landing.ftl"
							.equalsIgnoreCase(dependentItemLocation))) {
				secondCheckPass = true;
			}
			Assert.assertTrue(firstCheckPass);
			Assert.assertTrue(secondCheckPass);
			break;
		case "jquery.min.js":
			if (("404.ftl".equalsIgnoreCase(dependentItemName))
					|| ("500.ftl".equalsIgnoreCase(dependentItemName))
					|| ("article.ftl".equalsIgnoreCase(dependentItemName))
					|| ("home.ftl".equalsIgnoreCase(dependentItemName))
					|| ("search-results.ftl".equalsIgnoreCase(dependentItemName))
					|| ("category-landing.ftl".equalsIgnoreCase(dependentItemName))) {
				firstCheckPass = true;
			}
			if (("/templates/web/errors/404.ftl".equalsIgnoreCase(dependentItemLocation))
					|| ("/templates/web/errors/500.ftl".equalsIgnoreCase(dependentItemLocation))
					|| ("/templates/web/pages/article.ftl".equalsIgnoreCase(dependentItemLocation))
					|| ("/templates/web/pages/home.ftl".equalsIgnoreCase(dependentItemLocation))
					|| ("/templates/web/pages/search-results.ftl".equalsIgnoreCase(dependentItemLocation))
					|| ("/templates/web/pages/category-landing.ftl"
							.equalsIgnoreCase(dependentItemLocation))) {
				secondCheckPass = true;
			}
			Assert.assertTrue(firstCheckPass);
			Assert.assertTrue(secondCheckPass);
			break;
		default:
			throw new IllegalArgumentException(
					"No static asset case for provided static asset name: " + staticAssetName);
		}
	}

	public void checkDependenciesForStaticAssetItem(String staticAssetName, boolean dependsOn,
			boolean isDuplicationScenario) {
		this.driverManager.waitForAnimation();
		// Switch to the frame
		driverManager.getDriver().switchTo().defaultContent();
		driverManager.getDriver().switchTo().activeElement();
		driverManager.waitUntilPageLoad();
		// checking if the item name is the correct on the dependencies dialog
		this.driverManager.waitForAnimation();
		this.driverManager.waitForFullExpansionOfTree();
		WebElement dependenciesForItemElement = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", dependenciesForXpath);
		if (isDuplicationScenario) {
			Assert.assertTrue(dependenciesForItemElement.getText()
					.equalsIgnoreCase(staticAssetName.replace("duplication-", "")));
		} else {
			Assert.assertTrue(dependenciesForItemElement.getText().equalsIgnoreCase(staticAssetName));
		}

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				dependenciesSelector);
		Select categoriesDropDown = new Select(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", dependenciesSelector));
		if (dependsOn) {
			// depends on case
			categoriesDropDown.selectByValue("depends-on");
		} else {
			// depends on me case
			categoriesDropDown.selectByValue("depends-on-me");
		}

		this.driverManager.waitForFullExpansionOfTree();
		List<WebElement> dependeciesItems = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath",
						".//div[@id='dependencies-dialog']//table[contains(@class,'item-listing')]/tbody")
				.findElements(By.tagName("tr"));
		this.checkNumberOfItemOnDependencies(staticAssetName, dependeciesItems, dependsOn);

		for (WebElement webElement : dependeciesItems) {
			this.checkDependentItemsForStaticAsset(staticAssetName, webElement);
		}
		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dependenciesCloseButton)
				.click();

	}

	public void checkDependentItemsForScript(String scriptName, WebElement element) {
		String dependentItemName = element.findElement(By.xpath("td[1]")).getText();
		String dependentItemLocation = element.findElement(By.xpath("td[2]/div")).getText();
		boolean firstCheckPass = false;
		boolean secondCheckPass = false;

		switch (scriptName) {
		case "category-landing.groovy":
			if ((dependentItemName.equalsIgnoreCase("Style"))
					|| (dependentItemName.equalsIgnoreCase("Health"))
					|| (dependentItemName.equalsIgnoreCase("Technology"))
					|| (dependentItemName.equalsIgnoreCase("Entertainment"))) {
				firstCheckPass = true;
			}
			if ((dependentItemLocation.equalsIgnoreCase("/site/website/style/index.xml"))
					|| (dependentItemLocation.equalsIgnoreCase(
							"/site/website/articles/2017/1/men-styles-for-winter/index.xml"))
					|| (dependentItemLocation.equalsIgnoreCase("/site/website/health/index.xml"))
					|| (dependentItemLocation.equalsIgnoreCase("/site/website/technology/index.xml"))
					|| (dependentItemLocation.equalsIgnoreCase("/site/website/entertainment/index.xml"))) {
				secondCheckPass = true;
			}
			Assert.assertTrue(firstCheckPass);
			Assert.assertTrue(secondCheckPass);
			break;
		case "home.groovy":
			if ((dependentItemName.equalsIgnoreCase("Home"))) {
				firstCheckPass = true;
			}
			if ((dependentItemLocation.equalsIgnoreCase("/site/website/index.xml"))) {
				secondCheckPass = true;
			}
			Assert.assertTrue(firstCheckPass);
			Assert.assertTrue(secondCheckPass);
			break;
		case "search-results.groovy":
			if ((dependentItemName.equalsIgnoreCase("Search Results"))) {
				firstCheckPass = true;
			}
			if ((dependentItemLocation.equalsIgnoreCase("/site/website/search-results/index.xml"))) {
				secondCheckPass = true;
			}
			Assert.assertTrue(firstCheckPass);
			Assert.assertTrue(secondCheckPass);
			break;
		default:
			throw new IllegalArgumentException("No scrit case for provided script name: " + scriptName);
		}
	}

	public void checkDependenciesFoScriptItem(String scriptName, boolean dependsOn) {
		this.driverManager.waitForAnimation();
		// Switch to the frame
		driverManager.getDriver().switchTo().defaultContent();
		driverManager.getDriver().switchTo().activeElement();
		driverManager.waitUntilPageLoad();
		// checking if the item name is the correct on the dependencies dialog
		this.driverManager.waitForAnimation();
		this.driverManager.waitForFullExpansionOfTree();
		WebElement dependenciesForItemElement = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", dependenciesForXpath);
		Assert.assertTrue(dependenciesForItemElement.getText().equalsIgnoreCase(scriptName));

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				dependenciesSelector);
		Select categoriesDropDown = new Select(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", dependenciesSelector));
		if (dependsOn) {
			// depends on case
			categoriesDropDown.selectByValue("depends-on");

		} else {
			// depends on me case
			categoriesDropDown.selectByValue("depends-on-me");
		}

		this.driverManager.waitForFullExpansionOfTree();
		List<WebElement> dependeciesItems = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath",
						".//div[@id='dependencies-dialog']//table[contains(@class,'item-listing')]/tbody")
				.findElements(By.tagName("tr"));
		this.checkNumberOfItemOnDependencies(scriptName, dependeciesItems, dependsOn);
		for (WebElement webElement : dependeciesItems) {
			checkDependentItemsForScript(scriptName, webElement);
		}
		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dependenciesCloseButton)
				.click();
	}

	public void checkDependsOnArticleFTL(String dependentItemName, String dependentItemLocation) {
		boolean firstCheckPass = false;
		boolean secondCheckPass = false;
		if ((dependentItemName.equalsIgnoreCase("Top Clubs In Virginia"))
				|| (dependentItemName.equalsIgnoreCase("Men Styles For Winter"))
				|| (dependentItemName.equalsIgnoreCase("New ACME Phone Released Today"))
				|| (dependentItemName.equalsIgnoreCase("Top Romantic Valentine Movies"))
				|| (dependentItemName.equalsIgnoreCase("Top Books For Young Women"))
				|| (dependentItemName.equalsIgnoreCase("Coffee is Good for Your Health"))
				|| (dependentItemName.equalsIgnoreCase("Women Styles for Winter"))
				|| (dependentItemName.equalsIgnoreCase("5 Popular Diets for Women"))
				|| (dependentItemName.equalsIgnoreCase("10 Tips to Get a Six Pack"))
				|| (dependentItemName.equalsIgnoreCase("Testing1"))) {
			firstCheckPass = true;
		}
		if ((dependentItemLocation
				.equalsIgnoreCase("/site/website/articles/2017/3/top-clubs-in-virginia/index.xml"))
				|| (dependentItemLocation
						.equalsIgnoreCase("/site/website/articles/2017/1/men-styles-for-winter/index.xml"))
				|| (dependentItemLocation.equalsIgnoreCase(
						"/site/website/articles/2016/7/new-acme-phone-released-today/index.xml"))
				|| (dependentItemLocation.equalsIgnoreCase(
						"/site/website/articles/2017/2/top-romantic-valentine-movies/index.xml"))
				|| (dependentItemLocation.equalsIgnoreCase(
						"/site/website/articles/2016/12/top-books-for-young-women/index.xml"))
				|| (dependentItemLocation.equalsIgnoreCase(
						"/site/website/articles/2016/6/coffee-is-good-for-your-health/index.xml"))
				|| (dependentItemLocation
						.equalsIgnoreCase("/site/website/articles/2017/1/women-styles-for-winter/index.xml"))
				|| (dependentItemLocation.equalsIgnoreCase(
						"/site/website/articles/2017/3/5-popular-diets-for-women/index.xml"))
				|| (dependentItemLocation.equalsIgnoreCase(
						"/site/website/articles/2017/2/10-tips-to-get-a-six-pack/index.xml"))
				|| (dependentItemLocation.equalsIgnoreCase("/site/website/articles/2016/6/test/index.xml"))) {
			secondCheckPass = true;
		}
		Assert.assertTrue(firstCheckPass);
		Assert.assertTrue(secondCheckPass);

	}

	public void checkDependsOnCategoryLandingFTL(String dependentItemName, String dependentItemLocation) {
		boolean firstCheckPass = false;
		boolean secondCheckPass = false;
		if ((dependentItemName.equalsIgnoreCase("Style")) || (dependentItemName.equalsIgnoreCase("Health"))
				|| (dependentItemName.equalsIgnoreCase("Technology"))
				|| (dependentItemName.equalsIgnoreCase("Entertainment"))) {
			firstCheckPass = true;
		}
		if ((dependentItemLocation.equalsIgnoreCase("/site/website/style/index.xml"))
				|| (dependentItemLocation.equalsIgnoreCase("/site/website/health/index.xml"))
				|| (dependentItemLocation.equalsIgnoreCase("/site/website/technology/index.xml"))
				|| (dependentItemLocation.equalsIgnoreCase("/site/website/entertainment/index.xml"))) {
			secondCheckPass = true;
		}
		Assert.assertTrue(firstCheckPass);
		Assert.assertTrue(secondCheckPass);
	}

	public void checkDependsOnHomeFTL(String dependentItemName, String dependentItemLocation) {
		boolean firstCheckPass = false;
		boolean secondCheckPass = false;

		if (dependentItemName.equalsIgnoreCase("Home")) {
			firstCheckPass = true;
		}
		if (dependentItemLocation.equalsIgnoreCase("/site/website/index.xml")) {
			secondCheckPass = true;
		}
		Assert.assertTrue(firstCheckPass);
		Assert.assertTrue(secondCheckPass);
	}

	public void checkDependsOnSearchResultsFTL(String dependentItemName, String dependentItemLocation) {
		boolean firstCheckPass = false;
		boolean secondCheckPass = false;

		if (dependentItemName.equalsIgnoreCase("Search Results")) {
			firstCheckPass = true;
		}
		if (dependentItemLocation.equalsIgnoreCase("/site/website/search-results/index.xml")) {
			secondCheckPass = true;
		}
		Assert.assertTrue(firstCheckPass);
		Assert.assertTrue(secondCheckPass);
	}

	public void checkDependsOn(String templateName, WebElement element) {
		String dependentItemName = element.findElement(By.xpath("td[1]")).getText();
		String dependentItemLocation = element.findElement(By.xpath("td[2]/div")).getText();

		switch (templateName) {
		case "article.ftl":
			checkDependsOnArticleFTL(dependentItemName, dependentItemLocation);
			break;
		case "category-landing.ftl":
			checkDependsOnCategoryLandingFTL(dependentItemName, dependentItemLocation);
			break;
		case "home.ftl":
			checkDependsOnHomeFTL(dependentItemName, dependentItemLocation);
			break;
		case "search-results.ftl":
			checkDependsOnSearchResultsFTL(dependentItemName, dependentItemLocation);
			break;
		default:
			throw new IllegalArgumentException(
					"No template case for provided template name: " + templateName);
		}
	}

	public void checkDependsOnMeArticleFTL(String dependentItemName, String dependentItemLocation) {
		boolean firstCheckPass = false;
		boolean secondCheckPass = false;

		if ((dependentItemName.equalsIgnoreCase("jquery.min.js"))
				|| (dependentItemName.equalsIgnoreCase("jquery-ui.min.js"))
				|| (dependentItemName.equalsIgnoreCase("skel.min.js"))
				|| (dependentItemName.equalsIgnoreCase("util.js"))
				|| (dependentItemName.equalsIgnoreCase("main.css"))
				|| (dependentItemName.equalsIgnoreCase("main.js"))
				|| (dependentItemName.equalsIgnoreCase("ie9.css"))
				|| (dependentItemName.equalsIgnoreCase("html5shiv.js"))
				|| (dependentItemName.equalsIgnoreCase("cstudio-support.ftl"))
				|| (dependentItemName.equalsIgnoreCase("jquery-ui.min.css"))
				|| (dependentItemName.equalsIgnoreCase("ie8.css"))
				|| (dependentItemName.equalsIgnoreCase("placeholder.png"))
				|| (dependentItemName.equalsIgnoreCase("respond.min.js"))) {
			firstCheckPass = true;
		}
		if ((dependentItemLocation.equalsIgnoreCase("/static-assets/js/jquery.min.js"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/js/jquery-ui.min.js"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/js/skel.min.js"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/js/util.js"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/css/main.css"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/js/main.js"))
				|| (dependentItemLocation.equalsIgnoreCase("/templates/system/common/cstudio-support.ftl"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/css/ie9.css"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/js/ie/html5shiv.js"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/css/jquery-ui.min.css"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/css/ie8.css"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/images/placeholder.png"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/js/ie/respond.min.js"))) {
			secondCheckPass = true;
		}
		Assert.assertTrue(firstCheckPass);
		Assert.assertTrue(secondCheckPass);
	}

	public void checkDependsOnMeCategoryLandingFTL(String dependentItemName, String dependentItemLocation) {
		boolean firstCheckPass = false;
		boolean secondCheckPass = false;

		if ((dependentItemName.equalsIgnoreCase("jquery.min.js"))
				|| (dependentItemName.equalsIgnoreCase("jquery-ui.min.js"))
				|| (dependentItemName.equalsIgnoreCase("skel.min.js"))
				|| (dependentItemName.equalsIgnoreCase("util.js"))
				|| (dependentItemName.equalsIgnoreCase("main.css"))
				|| (dependentItemName.equalsIgnoreCase("main.js"))
				|| (dependentItemName.equalsIgnoreCase("ie9.css"))
				|| (dependentItemName.equalsIgnoreCase("html5shiv.js"))
				|| (dependentItemName.equalsIgnoreCase("cstudio-support.ftl"))
				|| (dependentItemName.equalsIgnoreCase("jquery-ui.min.css"))
				|| (dependentItemName.equalsIgnoreCase("ie8.css"))
				|| (dependentItemName.equalsIgnoreCase("placeholder.png"))
				|| (dependentItemName.equalsIgnoreCase("respond.min.js"))) {
			firstCheckPass = true;
		}
		if ((dependentItemLocation.equalsIgnoreCase("/static-assets/js/jquery.min.js"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/js/jquery-ui.min.js"))
				|| (dependentItemLocation.equalsIgnoreCase("/templates/system/common/cstudio-support.ftl"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/js/skel.min.js"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/js/util.js"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/css/main.css"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/js/main.js"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/css/ie9.css"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/js/ie/html5shiv.js"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/css/jquery-ui.min.css"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/css/ie8.css"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/images/placeholder.png"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/js/ie/respond.min.js"))) {
			secondCheckPass = true;
		}
		Assert.assertTrue(firstCheckPass);
		Assert.assertTrue(secondCheckPass);
	}

	public void checkDependsOnMeHomeFTL(String dependentItemName, String dependentItemLocation) {
		boolean firstCheckPass = false;
		boolean secondCheckPass = false;

		if ((dependentItemName.equalsIgnoreCase("jquery.min.js"))
				|| (dependentItemName.equalsIgnoreCase("jquery-ui.min.js"))
				|| (dependentItemName.equalsIgnoreCase("skel.min.js"))
				|| (dependentItemName.equalsIgnoreCase("util.js"))
				|| (dependentItemName.equalsIgnoreCase("main.css"))
				|| (dependentItemName.equalsIgnoreCase("main.js"))
				|| (dependentItemName.equalsIgnoreCase("ie9.css"))
				|| (dependentItemName.equalsIgnoreCase("cstudio-support.ftl"))
				|| (dependentItemName.equalsIgnoreCase("html5shiv.js"))
				|| (dependentItemName.equalsIgnoreCase("jquery-ui.min.css"))
				|| (dependentItemName.equalsIgnoreCase("ie8.css"))
				|| (dependentItemName.equalsIgnoreCase("placeholder.png"))
				|| (dependentItemName.equalsIgnoreCase("respond.min.js"))) {
			firstCheckPass = true;
		}
		if ((dependentItemLocation.equalsIgnoreCase("/static-assets/js/jquery.min.js"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/js/jquery-ui.min.js"))
				|| (dependentItemLocation.equalsIgnoreCase("/templates/system/common/cstudio-support.ftl"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/js/skel.min.js"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/js/util.js"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/css/main.css"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/js/main.js"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/css/ie9.css"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/js/ie/html5shiv.js"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/css/jquery-ui.min.css"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/css/ie8.css"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/images/placeholder.png"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/js/ie/respond.min.js"))) {
			secondCheckPass = true;
		}
		Assert.assertTrue(firstCheckPass);
		Assert.assertTrue(secondCheckPass);
	}

	public void checkDependsOnMeSearchResultsFTL(String dependentItemName, String dependentItemLocation) {
		boolean firstCheckPass = false;
		boolean secondCheckPass = false;

		if ((dependentItemName.equalsIgnoreCase("util.js")) || (dependentItemName.equalsIgnoreCase("main.js"))
				|| (dependentItemName.equalsIgnoreCase("ie9.css"))
				|| (dependentItemName.equalsIgnoreCase("html5shiv.js"))
				|| (dependentItemName.equalsIgnoreCase("cstudio-support.ftl"))
				|| (dependentItemName.equalsIgnoreCase("jquery-ui.min.css"))
				|| (dependentItemName.equalsIgnoreCase("respond.min.js"))
				|| (dependentItemName.equalsIgnoreCase("jquery.min.js"))
				|| (dependentItemName.equalsIgnoreCase("jquery-ui.min.js"))
				|| (dependentItemName.equalsIgnoreCase("skel.min.js"))
				|| (dependentItemName.equalsIgnoreCase("main.css"))
				|| (dependentItemName.equalsIgnoreCase("handlebars.min-latest.js"))
				|| (dependentItemName.equalsIgnoreCase("ie8.css"))
				|| (dependentItemName.equalsIgnoreCase("search.js"))) {
			firstCheckPass = true;
		}
		if ((dependentItemLocation.equalsIgnoreCase("/static-assets/js/util.js"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/js/main.js"))
				|| (dependentItemLocation.equalsIgnoreCase("/templates/system/common/cstudio-support.ftl"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/css/ie9.css"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/js/ie/html5shiv.js"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/css/jquery-ui.min.css"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/js/ie/respond.min.js"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/js/jquery.min.js"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/js/jquery-ui.min.js"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/js/skel.min.js"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/css/main.css"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/js/handlebars.min-latest.js"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/css/ie8.css"))
				|| (dependentItemLocation.equalsIgnoreCase("/static-assets/js/search.js"))) {
			secondCheckPass = true;
		}
		Assert.assertTrue(firstCheckPass);
		Assert.assertTrue(secondCheckPass);
	}

	public void checkDependsOnMe(String templateName, WebElement element) {
		String dependentItemName = element.findElement(By.xpath("td[1]")).getText();
		String dependentItemLocation = element.findElement(By.xpath("td[2]/div")).getText();

		switch (templateName) {
		case "article.ftl":
			checkDependsOnMeArticleFTL(dependentItemName, dependentItemLocation);
			break;
		case "category-landing.ftl":
			checkDependsOnMeCategoryLandingFTL(dependentItemName, dependentItemLocation);
			break;
		case "home.ftl":
			checkDependsOnMeHomeFTL(dependentItemName, dependentItemLocation);
			break;
		case "search-results.ftl":
			checkDependsOnMeSearchResultsFTL(dependentItemName, dependentItemLocation);
			break;
		default:
			throw new IllegalArgumentException(
					"No template case for provided template name: " + templateName);
		}
	}

	public void checkDependentItemsForTemplate(String templateName, WebElement element, boolean dependsOn) {
		if (!dependsOn) {
			checkDependsOn(templateName, element);
		} else {
			checkDependsOnMe(templateName, element);
		}
	}

	public void checkDependenciesForTemplateItem(String templateName, boolean dependsOn) {
		this.driverManager.waitForAnimation();
		// Switch to the frame
		driverManager.getDriver().switchTo().defaultContent();
		driverManager.getDriver().switchTo().activeElement();
		driverManager.waitUntilPageLoad();
		// checking if the item name is the correct on the dependencies dialog
		this.driverManager.waitForAnimation();
		this.driverManager.waitForFullExpansionOfTree();
		WebElement dependenciesForItemElement = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", dependenciesForXpath);
		Assert.assertTrue(dependenciesForItemElement.getText().equalsIgnoreCase(templateName));

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				dependenciesSelector);
		Select categoriesDropDown = new Select(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", dependenciesSelector));
		if (dependsOn) {
			// depends on case
			categoriesDropDown.selectByValue("depends-on");

		} else {
			// depends on me case
			categoriesDropDown.selectByValue("depends-on-me");
		}

		this.driverManager.waitForFullExpansionOfTree();
		List<WebElement> dependeciesItems = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath",
						".//div[@id='dependencies-dialog']//table[contains(@class,'item-listing')]/tbody")
				.findElements(By.tagName("tr"));
		this.checkNumberOfItemOnDependencies(templateName, dependeciesItems, dependsOn);
		for (WebElement webElement : dependeciesItems) {
			checkDependentItemsForTemplate(templateName, webElement, dependsOn);
		}
		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dependenciesCloseButton)
				.click();
	}

	public void checkDependentItemsForComponent(String componentName, WebElement element, boolean dependsOn) {
		String dependentItemName = element.findElement(By.xpath("td[1]")).getText();
		String dependentItemLocation = element.findElement(By.xpath("td[2]/div")).getText();
		boolean firstCheckPass = false;
		boolean secondCheckPass = false;

		if (!dependsOn) {
			switch (componentName) {
			case "Latest Articles Widget":
				Assert.assertTrue(dependentItemName.equalsIgnoreCase("Left Rail with Latest Articles"));
				Assert.assertTrue(dependentItemLocation
						.equalsIgnoreCase("/site/components/left-rails/left-rail-with-latest-articles.xml"));
				break;
			case "Header":
				Assert.assertTrue(dependentItemName.equalsIgnoreCase(""));
				Assert.assertTrue(dependentItemLocation
						.equalsIgnoreCase("/site/website/crafter-level-descriptor.level.xml"));
				break;
			case "Left Rail with Latest Articles":
				if ((dependentItemName.equalsIgnoreCase("Home"))
						|| (dependentItemName.equalsIgnoreCase("Search Results"))) {
					firstCheckPass = true;
				}
				if ((dependentItemLocation.equalsIgnoreCase("/site/website/index.xml"))
						|| (dependentItemLocation
								.equalsIgnoreCase("/site/website/search-results/index.xml"))) {
					secondCheckPass = true;
				}
				Assert.assertTrue(firstCheckPass);
				Assert.assertTrue(secondCheckPass);
				break;
			default:
				throw new IllegalArgumentException(
						"No component case for provided component name: " + componentName);
			}
		} else {
			switch (componentName) {
			case "Latest Articles Widget":
				if ((dependentItemName.equalsIgnoreCase("latest-articles.groovy"))
						|| (dependentItemName.equalsIgnoreCase("articles-widget.ftl"))) {
					firstCheckPass = true;
				}
				if ((dependentItemLocation.equalsIgnoreCase("/scripts/components/latest-articles.groovy"))
						|| (dependentItemLocation
								.equalsIgnoreCase("/templates/web/components/articles-widget.ftl"))) {
					secondCheckPass = true;
				}
				break;
			case "Header":
				if ((dependentItemName.equalsIgnoreCase("header.ftl"))) {
					firstCheckPass = true;
				}
				if ((dependentItemLocation.equalsIgnoreCase("/templates/web/components/header.ftl"))) {
					secondCheckPass = true;
				}
				break;
			case "Four":
				if ((dependentItemName.equalsIgnoreCase("feature.ftl"))) {
					firstCheckPass = true;
				}
				if ((dependentItemLocation.equalsIgnoreCase("/templates/web/components/feature.ftl"))) {
					secondCheckPass = true;
				}
				break;
			case "Left Rail with Latest Articles":
				if ((dependentItemName.equalsIgnoreCase("left-rail.ftl"))
						|| (dependentItemName.equalsIgnoreCase("Latest Articles Widget"))
						|| (dependentItemName.equalsIgnoreCase("Contact Widget"))) {
					firstCheckPass = true;
				}
				if ((dependentItemLocation.equalsIgnoreCase("/templates/web/components/left-rail.ftl"))
						|| (dependentItemLocation.equalsIgnoreCase(
								"/site/components/articles-widget/latest-articles-widget.xml"))
						|| (dependentItemLocation
								.equalsIgnoreCase("/site/components/contacts/contact-widget.xml"))) {
					secondCheckPass = true;
				}
				break;
			default:
				throw new IllegalArgumentException(
						"No component case for provided component name: " + componentName);
			}
		}

	}

	public void checkDependenciesForComponentItem(String componentName, boolean dependsOn) {
		this.driverManager.waitForAnimation();
		// Switch to the frame
		driverManager.getDriver().switchTo().defaultContent();
		driverManager.getDriver().switchTo().activeElement();
		driverManager.waitUntilPageLoad();
		// checking if the item name is the correct on the dependencies dialog
		this.driverManager.waitForFullExpansionOfTree();
		WebElement dependenciesForItemElement = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", dependenciesForXpath);
		Assert.assertTrue(dependenciesForItemElement.getText().equalsIgnoreCase(componentName));

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				dependenciesSelector);
		Select categoriesDropDown = new Select(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", dependenciesSelector));
		if (dependsOn) {
			// depends on case
			categoriesDropDown.selectByValue("depends-on");
		} else {
			// depends on me case
			categoriesDropDown.selectByValue("depends-on-me");
		}

		this.driverManager.waitForFullExpansionOfTree();
		List<WebElement> dependeciesItems = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath",
						".//div[@id='dependencies-dialog']//table[contains(@class,'item-listing')]/tbody")
				.findElements(By.tagName("tr"));
		this.checkNumberOfItemOnDependencies(componentName, dependeciesItems, dependsOn);
		for (WebElement webElement : dependeciesItems) {
			this.checkDependentItemsForComponent(componentName, webElement, dependsOn);
		}
		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dependenciesCloseButton)
				.click();
	}

	public void checkNumberOfItemOnDependencies(String componentName, List<WebElement> dependeciesItems,
			boolean dependsOn) {
		if (!dependsOn) {
			switch (componentName) {
			case "Latest Articles Widget":
				Assert.assertTrue(dependeciesItems.size() == 1);
				break;
			case "Header":
				Assert.assertTrue(dependeciesItems.size() == 1);
				break;
			case "Left Rail with Latest Articles":
				Assert.assertTrue(dependeciesItems.size() == 2);
				break;
			case "book-woman-pic.jpg":
				Assert.assertTrue(dependeciesItems.size() == 1);
				break;
			case "duplication-winter-woman-pic.jpg":
				Assert.assertTrue(dependeciesItems.size() == 4);
				break;
			case "winter-woman-pic.jpg":
				Assert.assertTrue(dependeciesItems.size() == 3);
				break;
			case "article.ftl":
				Assert.assertTrue(dependeciesItems.size() == 10);
				break;
			case "category-landing.ftl":
				Assert.assertTrue(dependeciesItems.size() == 4);
				break;
			case "home.ftl":
				Assert.assertTrue(dependeciesItems.size() == 1);
				break;
			case "search-results.ftl":
				Assert.assertTrue(dependeciesItems.size() == 1);
				break;
			case "category-landing.groovy":
				Assert.assertTrue(dependeciesItems.size() == 4);
				break;
			case "home.groovy":
				Assert.assertTrue(dependeciesItems.size() == 1);
				break;
			case "search-results.groovy":
				Assert.assertTrue(dependeciesItems.size() == 1);
				break;
			case "ie8.css":
				Assert.assertTrue(dependeciesItems.size() == 6);
				break;
			case "jquery.min.js":
				Assert.assertTrue(dependeciesItems.size() == 6);
				break;
			default:
				throw new IllegalArgumentException("No case for provided item name: " + componentName);
			}
		} else {
			switch (componentName) {
			case "Home":
				Assert.assertTrue(dependeciesItems.size() == 6);
				break;
			case "Style":
				Assert.assertTrue(dependeciesItems.size() == 2);
				break;
			case "Women Styles for Winter":
				Assert.assertTrue(dependeciesItems.size() == 2);
				break;
			case "Testing1":
				Assert.assertTrue(dependeciesItems.size() == 2);
				break;
			case "Search Results":
				Assert.assertTrue(dependeciesItems.size() == 4);
				break;
			case "Latest Articles Widget":
				Assert.assertTrue(dependeciesItems.size() == 2);
				break;
			case "Header":
				Assert.assertTrue(dependeciesItems.size() == 1);
				break;
			case "Four":
				Assert.assertTrue(dependeciesItems.size() == 1);
				break;
			case "Left Rail with Latest Articles":
				Assert.assertTrue(dependeciesItems.size() == 3);
				break;
			case "article.ftl":
				Assert.assertTrue(dependeciesItems.size() == 12);
				break;
			case "category-landing.ftl":
				Assert.assertTrue(dependeciesItems.size() == 12);
				break;
			case "home.ftl":
				Assert.assertTrue(dependeciesItems.size() == 12);
				break;
			case "search-results.ftl":
				Assert.assertTrue(dependeciesItems.size() == 13);
				break;
			case "ie8.css":
				Assert.assertTrue(dependeciesItems.size() == 0);
				break;
			default:
				throw new IllegalArgumentException("No case for provided item name: " + componentName);
			}
		}
	}

	public void bulkPublish(String path, int waitTimeForPublish) {
		WebElement siteConfigButton = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("id", "admin-console");
		siteConfigButton.click();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				siteconfigPublishingOperationsoption);
		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				siteconfigPublishingOperationsoption).click();
		this.driverManager.waitForAnimation();
		this.driverManager.getDriver().switchTo().frame(
				this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", publishingFrame));
		this.driverManager.isElementPresentAndClickableByXpath(publishingFrame);
		this.driverManager.getDriver().switchTo().activeElement();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", bulkPublishTab)
				.click();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				bulkOperationsPathToPublishInput).click();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				bulkOperationsPathToPublishInput).clear();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				bulkOperationsPathToPublishInput).sendKeys(path);
		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", bulkoperationsPublishButton)
				.click();
		this.driverManager.getDriver().switchTo().activeElement();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				bulkoperationsAcceptWarning);

		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", bulkoperationsAcceptWarning)
				.click();
		this.driverManager.waitForAnimation();
		this.driverManager.waitForFullExpansionOfTree();
		this.driverManager.getDriver().switchTo().activeElement();
		// wait for bulk publish notification according with length of tree to be
		// published
		this.driverManager.waitForAnimation();
		this.driverManager.waitForBulkPublish(waitTimeForPublish);
		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", bulkoperationsMessage).isDisplayed());
		// Switch back to the dashboard page
		driverManager.getDriver().switchTo().defaultContent();
		this.driverManager.getDriver().switchTo().activeElement();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("id", "navbar-site-name");
		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("id", "navbar-site-name")
				.click();
		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("cssSelector",
				"#admin-console");

	}

	public void verifyPageArticleIsPublished() {
		this.driverManager.waitForFullExpansionOfTree();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				articleContentCreatedName);
		this.driverManager.waitForFullExpansionOfTree();
		this.driverManager.waitUntilContentTooltipIsHidden();
		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articleContentCreatedName)
				.click();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				generalDeleteOption);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				generalEditOption);
		for (int i = 0; i < driverManager.getNumberOfAttemptsForElementsDisplayed(); i++) {
			try {
				this.driverManager.waitUntilAttributeContains("xpath", siteStatusIcon, "class",
						"undefined live");
				break;
			} catch (TimeoutException e) {
				this.driverManager.takeScreenshot("PageNotPublishedOnTopNavBar");
				logger.warn(
						"Content page is not published yet, checking again if it has published icon on top bar");
				driverManager.getDriver().navigate().refresh();
				this.driverManager.waitForAnimation();
				this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
						articleContentCreatedName).click();
			}
		}
		this.driverManager.waitForFullExpansionOfTree();
		Assert.assertTrue(this.driverManager.getDriver().findElement(By.xpath(siteStatusIcon))
				.getAttribute("class").contains("undefined live"));

	}

	public PreviewPage clickSidebar() {
		driverManager.clickElement("xpath", siteDropdownElementXPath);
		return this;
	}
}
