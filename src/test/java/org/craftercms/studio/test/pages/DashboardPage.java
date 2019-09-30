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

import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.utils.FilesLocations;
import org.craftercms.studio.test.utils.UIElementsPropertiesManager;
import org.craftercms.studio.test.utils.WebDriverManager;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

/**
 *
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class DashboardPage {

	private WebDriverManager driverManager;

	private String pagesTree;
	private String homeContent;
	private String addNewContent;
	private String okButton;
	private String setPageURL;
	private String setInternalName;
	private String saveCloseButton;
	private String saveDraft1;
	private String addNewFolder;
	private String createButton;
	private String setFolderName;
	private String copyContent;
	private String aboutUSContentPage;
	private String cutContent;
	private String newContentCreated;
	private String clickOnSiteContent;
	private String deleteContentOK;
	private String submittalCompleteOK;
	private String editParentOption;
	private String editRecentlyContentCreated;
	private String selectEntryCT;
	private String pageArticleContentTypeLocator;
	private String homeTree;
	private String folderCreated;
	private String previewDuplicate;
	private String levelDescriptorContentType;
	private String pasteContent;
	private String newFolderCreated;
	private String deleteContent;
	private String usersContextualNavigationOption;
	private String articlesTitleLocator;
	private String copyOptionLocator;
	private String categoriesLocator;
	private String copyButonOnTreeSelector;
	private String pasteOptionLocator;
	private String cutOptionLocator;
	private String contextualNavigationEditLocator;
	private String contextualNavigationHistoryLocator;
	private String closeButtonLocator;
	private String historyCloseButtonLocator;
	private String publishOptionLocator;
	private String approveAndPublishPublishButtonLocator;
	private String deleteOptionLocator;
	private String deleteDeletButtonLocator;
	private String addNewFolderOption;
	private String addNewContentOption;
	private String deleteOKButtonLocator;
	private String copyOptionLocatorForContentPage;
	private String articlesSubjectInput;
	private String articlesAuthorInput;
	private String articlesSummaryInput;
	private String articleAddImageButton;
	private String compareButtonByXpath;
	private String existingImagesButton;
	private String addCloseGearImageButton;
	private String editRecentActivity;
	private String seeThePageEdited;
	private String copyContentButton;
	private String componentsTree;
	private String componentsSubTree;
	private String requestPublishOption;
	private String requestPublishSubmitButton;
	private String sitesOptionXpath;
	private String uploadImagesButton;
	private String chooseFileInput;
	private String chooseFileButton;
	private String recentActivityContentURL;
	private String recentActivityContentIcon;
	private String recentActivityContentName;
	private String recentActivitySecondContentURL;
	private String recentActivitySecondContentName;
	private String recentActivitySecondContentIcon;
	private String authorReplaceImageButton;
	private String changeTemplateSubmitButtonLocator;
	private String changeTemplateArticlesTitleLocator;
	private String addCloseWinterWomanButton;
	private static Logger logger = LogManager.getLogger(DashboardPage.class);

	/**
	 * 
	 */
	public DashboardPage(WebDriverManager driverManager,
			UIElementsPropertiesManager uiElementsPropertiesManager) {
		this.driverManager = driverManager;

		pagesTree = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.expand_Pages_Tree");
		homeTree = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.expand_GlobalEntry_Tree");
		homeContent = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.home_Content_Page");
		addNewContent = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.new.Content.option");
		addNewFolder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.new.folder.option");
		selectEntryCT = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.entry_Content_Type");
		okButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.ok_Button");
		setPageURL = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("frame2.page_URL");
		setInternalName = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame2.internal_Name");
		saveCloseButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame2.save_Close_Button");
		saveDraft1 = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame2.save_Draft");
		createButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.create_Button");
		setFolderName = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.folder_name");
		copyContent = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.copy.option");
		copyContentButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.copy_contentButton");
		aboutUSContentPage = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.aboutuscontentpage");
		pasteContent = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.paste.option");
		deleteContent = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.delete.option");
		cutContent = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.cut.option");
		newContentCreated = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.new_content");
		clickOnSiteContent = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.site_content");
		deleteContentOK = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.delete_content_OK");
		submittalCompleteOK = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.submitall.ok");
		editParentOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.edit.option");
		editRecentlyContentCreated = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.edit.option");
		folderCreated = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.folder_created");
		previewDuplicate = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.duplicate");
		levelDescriptorContentType = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.level_descriptor");
		newFolderCreated = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.add_new_folder");
		usersContextualNavigationOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.userscontextualnavigationoption");
		pageArticleContentTypeLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.pageArticle_Content_Type");
		articlesTitleLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame2.articlestitlefield");
		changeTemplateArticlesTitleLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame2.changetemplate.articlestitlefield");
		categoriesLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame2.categoriesdropdownlist");
		copyOptionLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.copy.option");
		copyButonOnTreeSelector = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.treeselectorcopybutton");
		pasteOptionLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.paste.option");
		cutOptionLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.cut.option");
		contextualNavigationEditLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.edittopnavoption");
		contextualNavigationHistoryLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.historytopnavoption");
		compareButtonByXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.compare_button");
		closeButtonLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.closebutton");
		historyCloseButtonLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.historyClosebutton");
		publishOptionLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.approveandpublish.option");
		approveAndPublishPublishButtonLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.approveandpublishsubmitbutton");
		changeTemplateSubmitButtonLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.changetemplatesubmitbutton");
		deleteOptionLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.delete.option");
		deleteDeletButtonLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.deletedeletebutton");
		addNewFolderOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.new.folder.option");
		addNewContentOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.new.Content.option");
		deleteOKButtonLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.deleteOkbutton");
		copyOptionLocatorForContentPage = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.copy.option");
		articlesSubjectInput = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame2.article_subject_input");
		articlesAuthorInput = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame2.article_author_input");
		articlesSummaryInput = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame2.article_summary_input");
		articleAddImageButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame2.article_add_image_button");
		existingImagesButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame2.article_existing_images_button");
		uploadImagesButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame2.article_upload_images_button");
		addCloseGearImageButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame2.article_addclose_gear_image");
		addCloseWinterWomanButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame2.article_addclose_winterwomand_image");
		editRecentActivity = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.editoption");
		seeThePageEdited = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.viewpage");
		componentsTree = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.expand_components_tree");
		componentsSubTree = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.expand_components_subtree");
		requestPublishOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.requestpublish.option");
		requestPublishSubmitButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.approveandpublishsubmitbutton");
		sitesOptionXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.preview.sitesoption");
		chooseFileInput = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame2.article.choosefileinput");
		chooseFileButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame2.article.choosefilebutton");
		recentActivityContentURL = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.contenturl");
		recentActivityContentIcon = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.contenticon");
		recentActivityContentName = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.contentname");
		recentActivitySecondContentURL = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.contentsecondurl");
		recentActivitySecondContentName = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.contentsecondname");
		recentActivitySecondContentIcon = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.myrecentactivity.contentsecondicon");
		authorReplaceImageButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame2.author.replaceimage");
	}


	// Expand pages tree
	public void clickPagesTree() {
		this.driverManager.waitUntilSidebarOpens();
		WebElement expandPagesTree = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				pagesTree);
		if (!expandPagesTree.getAttribute("class").contains("open")) {
			expandPagesTree.click();
			driverManager.waitUntilFolderOpens("xpath", pagesTree);
			this.driverManager.waitForAnimation();
		}
	}

	// Expand components tree
	public void clickComponentsTree() {
		this.driverManager.waitUntilSidebarOpens();
		WebElement expandComponentsTree = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", componentsTree);
		if (!expandComponentsTree.getAttribute("class").contains("open")) {
			expandComponentsTree.click();
			driverManager.waitUntilFolderOpens("xpath", componentsTree);
		}
	}

	// Expand components Sub-tree
	public void clickComponentsSubTree() {
		this.driverManager.waitUntilSidebarOpens();
		WebElement expandComponentsSubTree = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", componentsSubTree);
		if (!expandComponentsSubTree.getAttribute("class").contains("open")) {
			expandComponentsSubTree.click();
			driverManager.waitUntilFolderOpens("xpath", componentsSubTree);
		}
		driverManager.waitUntilFolderOpens("xpath", componentsSubTree);
	}

	public void expandPagesTree() {
		// Expand pages tree
		logger.info("Expanding Pages tree");
		this.clickPagesTree();
	}

	public void expandComponentsTree() {
		// Expand components
		logger.info("Expanding Components tree");
		this.clickComponentsTree();
	}

	public void expandComponentsSubTree() {
		// Expand components
		logger.info("Expanding Components Sub-tree");
		this.clickComponentsSubTree();
	}

	// Expand global entry content

	public void clickGlobalEntryContent() {
		this.driverManager.waitUntilSidebarOpens();
		this.driverManager.waitForAnimation();

		// Verify if the home tree is already expanded
		if (!(this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", homeContent)
				.getAttribute("class").contains("open"))) {
			this.driverManager.waitForAnimation();
			this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", homeTree)
					.click();
		}
	}

	public void expandHomeTree() {
		// Expand global entry content
		logger.info("Expanding Home tree");
		this.driverManager.waitForFullExpansionOfTree();
		this.clickGlobalEntryContent();
	}

	// Expand home content
	public void clickHomeContent() {
		this.driverManager.waitForAnimation();
		WebElement home = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				homeContent);
		home.click();
	}

	public void clickHomeTree() {
		// Expand home content
		this.clickHomeContent();
	}

	// Press right click and select new content
	public void rightClickHome() {
		this.driverManager.waitUntilPageLoad();
		this.driverManager.waitUntilSidebarOpens();

		this.driverManager.waitUntilFolderOpens("xpath", pagesTree);
		this.driverManager.waitForAnimation();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", homeContent);
		this.getDriverManager().contextClick("xpath", homeContent, false);

		driverManager.usingContextMenu(() -> {
			WebElement addContent = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
					addNewContent);
			addContent.click();
		}, "Pages");
	}

	public void rightClickToSeeMenu() {
		logger.info("Right Click to see Menu");
		// Press right click and select new content
		driverManager.waitUntilPageLoad();
		driverManager.waitUntilSidebarOpens();

		this.rightClickHome();
	}

	public void rightClickToSeeMenuOfSpecificFolder(String folderLocation) {
		// Press right click and select new content
		this.rightClickSpecificFolder(folderLocation);
	}

	public void rightClickSpecificFolder(String folderLocation) {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", folderLocation);

		this.getDriverManager().contextClick("xpath", folderLocation, false);
		driverManager.usingContextMenu(() -> {
			WebElement addContent = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
					addNewContent);
			addContent.click();
		}, "Pages");
	}

	// Press right click select new folder
	public void rightClickNewFolderOnHome() {
		// wait for the animation to end
		this.driverManager.waitUntilPageLoad();
		this.driverManager.waitUntilSidebarOpens();

		this.driverManager.waitUntilFolderOpens("xpath", pagesTree);
		this.driverManager.waitForAnimation();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", homeContent);
		this.getDriverManager().contextClick("xpath", homeContent, false);

		driverManager.usingContextMenu(() -> {
			WebElement addFolder = this.driverManager
					.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", addNewFolder);
			addFolder.click();
		}, "Pages");
	}

	// Press right click select new folder
	public void rightClickNewFolderOnAPresentFolder(String parentWebElementLocator) {
		this.driverManager.waitForFullExpansionOfTree();
		this.getDriverManager().contextClick("xpath", parentWebElementLocator, true);
		driverManager.usingContextMenu(() -> {
			this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", addNewFolderOption)
					.click();
		}, "Pages");
	}

	// Press right click to see the menu
	public void rightClickCreatePageOnAPresentFolder(String parentWebElementLocator) {
		this.getDriverManager().contextClick("xpath", parentWebElementLocator, false);
		driverManager.usingContextMenu(() -> {
			WebElement addContent = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
					addNewContentOption);
			addContent.click();
		}, "Pages");
	}

	public void rightClickToFolderOnHome() {
		// Press right click select new folder
		logger.info("Right Click  on Folder Home");
		this.rightClickNewFolderOnHome();
	}

	// Select Entry Content type
	public void selectEntryCT() {
		driverManager.getDriver().switchTo().defaultContent();
		driverManager.getDriver().switchTo().activeElement();
		driverManager.waitUntilElementIsDisplayed("xpath", selectEntryCT);
		Assert.assertTrue(driverManager.isElementPresentByXpath(selectEntryCT));
	}

	public void selectPageArticleContentType() {
		driverManager.getDriver().switchTo().defaultContent();
		driverManager.getDriver().switchTo().activeElement();
		driverManager.waitUntilElementIsDisplayed("cssSelector", pageArticleContentTypeLocator);
		Assert.assertTrue(driverManager.isElementPresentBycssSelector(pageArticleContentTypeLocator));
	}

	public void clickEntryCT() {
		logger.info("Select Entry Content Type");
		// Select Generic Content type
		this.selectEntryCT();
	}

	// Select Level Descriptor Content type
	public void selectLDCT() {
		driverManager.getDriver().switchTo().defaultContent();
		driverManager.getDriver().switchTo().activeElement();
		WebElement levelDescriptorCT = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				levelDescriptorContentType);
		levelDescriptorCT.click();
	}

	public void clickLevelDescriptorCT() {
		// Select Level Descriptor Content type
		this.selectLDCT();
	}

	// Confirm the CT selected
	public void confirmCTSelected() {
		WebElement okButton = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				this.okButton);
		okButton.click();
	}

	public void clickOKButton() {
		// Confirm the CT selected
		this.confirmCTSelected();
	}

	// Set page URL
	public void setPageURL1(String strPageURL) {
		driverManager.sendText("xpath", setPageURL, strPageURL.toLowerCase());
	}

	// Set internal name
	public void setInternalName1(String strInternalName) {
		driverManager.sendText("xpath", setInternalName, strInternalName);
	}

	// Click on save and close button
	public void clickSaveClose() {
		WebElement saveCloseButton = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", this.saveCloseButton);
		saveCloseButton.click();
	}

	// Click on save and close button
	public void clickSaveDraft() {
		this.driverManager.isElementPresentAndClickableByXpath(saveDraft1);
		WebElement saveDraftButton = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", saveDraft1);
		saveDraftButton.click();
	}

	// Setting basic fields of the nre content
	public void setBasicFieldsOfNewContent(String strPageURL, String strInternalName) {
		// Fill page URL
		this.setPageURL1(strPageURL);
		// Fill internal name
		this.driverManager.waitForAnimation();
		this.setInternalName1(strInternalName);
	}

	// Click on save and close button
	public void clickSaveAndDraft() {
		WebElement saveDraftButton = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("id",
				saveDraft1);
		saveDraftButton.click();
	}

	public void setLevelDescriptorName(String strFileName) {
		// Fill page URL
		this.setPageURL1(strFileName);
		// Click on save and draft button
		this.clickSaveAndDraft();

	}

	// Set the name of the folder
	public void folderName(String strFolderName) {
		driverManager.usingYuiDialog(() -> {
			driverManager.sendText("xpath", setFolderName, strFolderName);
			createButton();
		});
	}

	public void setFolderName(String strFolderName) {
		// Set the name of the folder
		logger.info("Creating a new folder");
		this.folderName(strFolderName);
	}

	// create button
	public void createButton() {
		WebElement buttonCreate = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				createButton);
		buttonCreate.click();
	}

	public void clickCreateButton() {
		// create button
		this.createButton();
	}

	// Press right click and press copy option (about us page)
	public void rightClickCopyOptionAboutUs() {

		// wait for the animation to end
		driverManager.waitUntilSidebarOpens();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				aboutUSContentPage);
		this.getDriverManager().contextClick("xpath", aboutUSContentPage, false);
		driverManager.usingContextMenu(() -> {
			WebElement copyContent = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
					this.copyContent);
			copyContent.click();
		}, "Pages");

	}

	public void requestPublish() {

		driverManager.usingContextMenu(() -> {
			WebElement requestPublishOption = this.driverManager
					.driverWaitUntilElementIsPresentAndDisplayed("xpath", this.requestPublishOption);
			requestPublishOption.click();
		}, "Pages");

		this.driverManager.getDriver().switchTo().activeElement();

		WebElement requestPublishSubmitButtonElement = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", this.requestPublishSubmitButton);
		requestPublishSubmitButtonElement.click();

		this.driverManager.waitForAnimation();
	}

	public void rightClickToCopyOptionAboutUs() {
		// Press right click and press copy option
		this.rightClickCopyOptionAboutUs();
	}

	// Press right click and press copy option (service page)
	public void rightClickCopyOptionService() {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("id", newContentCreated);
		this.getDriverManager().contextClick("id", newContentCreated, false);
		driverManager.usingContextMenu(() -> {
			WebElement copyContent = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
					this.copyContent);
			copyContent.click();
		}, "Pages");
	}

	public void rightClickToCopyOptionService() {
		// Press right click and press copy option
		this.rightClickCopyOptionService();
	}

	// Press right click and press paste option
	public void rightClickPasteOption() {

		// wait for the animation to end
		driverManager.waitUntilSidebarOpens();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", homeContent);
		this.getDriverManager().contextClick("xpath", homeContent, false);
		driverManager.usingContextMenu(() -> {
			WebElement pasteContent = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
					this.pasteContent);
			pasteContent.click();
		}, "Pages");
	}

	public void rightClickToPasteOption() {
		// Press right click and press paste option
		this.rightClickPasteOption();
	}

	// Press right click and press paste option
	public void rightClickToPasteIntoFolderToTest() {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", newFolderCreated);
		this.getDriverManager().contextClick("xpath", newFolderCreated, false);
		driverManager.usingContextMenu(() -> {
			WebElement pasteContentElement = this.driverManager
					.driverWaitUntilElementIsPresentAndDisplayed("xpath", pasteContent);
			pasteContentElement.click();
		}, "Pages");
	}

	public void rightClickToPasteIntoFolder() {
		// Press right click and press paste option
		logger.debug("Right Click to Paste into Folder");
		this.rightClickToPasteIntoFolderToTest();
	}

	// edit internal name
	public void editInternalName(String strInternalName) {
		// Fill internal name
		this.setInternalName1(strInternalName);

		this.driverManager.waitForAnimation();

		// Save and close button.
		this.clickSaveClose();
	}

	// Press right click and press cut option
	public void rightClickCutOption() {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				newContentCreated);
		this.getDriverManager().contextClick("xpath", newContentCreated, false);
		driverManager.usingContextMenu(() -> {
			WebElement cutContent = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
					this.cutContent);
			cutContent.click();
		}, "Pages");
	}

	public void rightClickToCutOption() {
		// Press right click and press cut option
		logger.debug("Right Click to Cut Option");
		this.rightClickCutOption();
	}

	// Press right click and copy the component to new folder created.
	public void rightClickCopyComponent() {
		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				newContentCreated);

		this.getDriverManager().contextClick("xpath", newContentCreated, false);
		driverManager.usingContextMenu(() -> {
			WebElement copyComponentToNewFolder = this.driverManager
					.driverWaitUntilElementIsPresentAndDisplayed("xpath", copyContent);
			copyComponentToNewFolder.click();
		}, "Pages");
		this.driverManager.waitForAnimation();
	}

	public void rightClickToCopyComponentToNewFolder() {
		// Press right click and copy the component to new folder created.
		this.rightClickCopyComponent();
	}

	// Press right click and copy the new content to the new folder
	public void rightClickCopyNewContent() {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				newContentCreated);
		this.getDriverManager().contextClick("xpath", newContentCreated, false);
		driverManager.usingContextMenu(() -> {
			WebElement copyNewContentToNewFolder = this.driverManager
					.driverWaitUntilElementIsPresentAndDisplayed("xpath", this.copyContent);
			copyNewContentToNewFolder.click();
		}, "Pages");
	}

	public void rightClickToCopyNewContentToNewFolder() {
		// Press right click and copy the new content to the new folder
		this.rightClickCopyNewContent();
	}

	// Press right click and press paste option to the new folder
	public void rightClickPaste() {
		this.driverManager.isElementPresentAndClickableByXpath(folderCreated);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", folderCreated);

		this.getDriverManager().contextClick("xpath", folderCreated, false);
		driverManager.usingContextMenu(() -> {
			WebElement pasteContentElement = this.driverManager
					.driverWaitUntilElementIsPresentAndDisplayed("xpath", pasteContent);
			pasteContentElement.click();
		}, "Pages");
		this.driverManager.waitForAnimation();
	}

	public void rightClickToPasteToNewFolder() {
		// Press right click and press paste option to the new folder
		this.rightClickPaste();
	}

	// copy button
	public void copyButton() {
		WebElement buttonCopy = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				copyContentButton);
		buttonCopy.click();
	}

	public void clickCopyButton() {
		// copy button
		this.copyButton();
	}

	public void clickCopyButtonOnTreeSelector() {
		this.driverManager.waitForFullExpansionOfTree();
		WebElement buttonCopy = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", copyButonOnTreeSelector);
		buttonCopy.click();
	}

	// click on Site Content
	public void clickSiteContent() {

		WebElement siteContent = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				clickOnSiteContent);
		siteContent.click();
	}

	public void clickOnSiteContentOption() {
		// click on Site Content
		this.clickSiteContent();
	}

	// Press right click and select new content

	public void deleteContent() {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				newContentCreated);
		this.driverManager.contextClick("xpath", newContentCreated, false);
		driverManager.usingContextMenu(() -> {
			WebElement delContent = this.driverManager
					.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", deleteContent);
			delContent.click();
		}, "Pages");
	}

	public void rightClickToDeleteContent() {
		// Press right click and select new content
		this.deleteContent();
	}

	public void rightClickToDeleteContent(String elementLocator) {
		// Press right click and select new content
		this.deleteContent(elementLocator);
	}

	public void deleteContent(String elementLocator) {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", elementLocator);

		this.driverManager.contextClick("xpath", elementLocator, false);
		driverManager.usingContextMenu(() -> {
			WebElement delContent = this.driverManager
					.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", deleteContent);
			delContent.click();
		}, "Pages");
	}

	// Ok delete content option
	public void deleteContentOK() {
		driverManager.clickElement("xpath", deleteContentOK);
	}

	public void clicktoDeleteContent() {
		// Ok delete content option
		this.deleteContentOK();
	}

	// Ok submittal complete
	public void submittalCompleteOK() {
		driverManager.clickElement("xpath", submittalCompleteOK);
	}

	public void clickOKSubmittalComplete() {
		// Ok submittal complete
		this.submittalCompleteOK();
	}

	// Press right click and select edit to the content created
	public void rightClickToEdit() {

		// wait for the animation to end
		driverManager.waitUntilSidebarOpens();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				newContentCreated);
		this.getDriverManager().contextClick("xpath", newContentCreated, false);
		driverManager.usingContextMenu(() -> {
			WebElement editOption = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
					editRecentlyContentCreated);
			editOption.click();
		}, "Pages");
	}

	public void rightClickToSelectEditOption() {
		// Press right click and select edit to the content created
		this.rightClickToEdit();
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

	public void setDriverManager(WebDriverManager driverManager) {
		this.driverManager = driverManager;
	}

	public void clickUsersContextualNavigationOption() {
		WebElement usersContextualNavigationOptionWebElement = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", usersContextualNavigationOption);
		usersContextualNavigationOptionWebElement.click();
	}

	public void setBasicFieldsOfNewPageArticleContent(String strPageURL, String strInternalName,
			String strArticlesTitle) {
		// Fill page URL
		this.setPageURL1(strPageURL);
		// Fill internal name
		this.setInternalName1(strInternalName);
		// Fill the Subject field
		this.setArticlesTitle(strArticlesTitle);
		// select the Category
		this.selectFirstCategoryOfPagArticle();
	}

	public void updateBasicFieldsOfNewPageArticleContent(String strInternalName, String strArticlesTitle) {
		// Fill internal name
		this.setInternalName1(strInternalName);
		// Fill the Subject field
		this.setArticlesTitle(strArticlesTitle);
		// select the Category
		this.selectFirstCategoryOfPagArticle();
	}

	public void updateFieldsOfPageArticleContent(String strInternalName, String strArticlesTitle) {
		// Fill internal name
		this.driverManager.waitForAnimation();
		this.setInternalName1(strInternalName);
		this.driverManager.waitForAnimation();
		// Fill the Subject field
		this.setArticlesTitle(strArticlesTitle);

	}

	public void setArticlesTitle(String strArticlesTitle) {
		driverManager.sendText("xpath", articlesTitleLocator, strArticlesTitle);
	}

	public void setArticlesTitleWhenChangeTemplate(String articlesTitle) {
		driverManager.sendText("xpath", changeTemplateArticlesTitleLocator, articlesTitle);
	}

	public void setNewArticleContentSection(String subject, String author, String summary) {
		driverManager.sendText("xpath", articlesSubjectInput, subject);
		driverManager.sendText("xpath", articlesAuthorInput, author);
		driverManager.sendText("xpath", articlesSummaryInput, summary);
	}

	public void selectFirstCategoryOfPagArticle() {
		Select categoriesDropDown = new Select(
				this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", categoriesLocator));
		categoriesDropDown.selectByValue("style");
	}

	public void selectCategoriesOfNewPageArticle(String category) {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", category);
		WebElement categoryToCheck = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				category);
		categoryToCheck.click();
	}

	public void selectSegmentsOfNewPageArticle(String segments) {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", segments);
		WebElement segmentToCheck = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				segments);
		segmentToCheck.click();
	}

	public void selectAllTreeOnSelector(String folderXPath) {
		WebElement checkAllTree = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				folderXPath);
		if (!checkAllTree.getAttribute("checked").equals("true"))
			checkAllTree.click();
	}

	public void clickOnHomeTree() {
		WebElement homeTree = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", this.homeTree);
		homeTree.click();
	}

	public void clickOnContextualNavigationEditOption() {
		driverManager.clickElement("xpath", contextualNavigationEditLocator);
	}

	public void clickOnContextualNavigationHistoryOption() {
		driverManager.clickElement("xpath", contextualNavigationHistoryLocator);
	}

	public void clickCompareButton() {
		try {
			WebElement compareButton = this.driverManager
					.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", compareButtonByXpath);
			compareButton.click();
		} catch (WebDriverException e) {
			this.driverManager.takeScreenshot("CompareButtonNotClickable");
			logger.warn("Compare button can't be clicked, The History dialog is not completely rendered");
		}

	}

	public void clickCloseButton() {
		WebElement closeButton = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				closeButtonLocator);
		closeButton.click();
	}

	public void clickHistoryCloseButton() {
		WebElement historyCloseButton = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", historyCloseButtonLocator);
		historyCloseButton.click();
	}

	public void clickOnPublishOption() {
		WebElement publishOption = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", publishOptionLocator);

		publishOption.click();
	}

	public void rightClickOnAContentPage(String elementLocator) {

		this.getDriverManager().contextClick("xpath", elementLocator, false);
	}

	public void rightClickOnAContentPageByJavascript(String elementLocator) {
		this.getDriverManager().contextClick("xpath", elementLocator, true);
	}

	public void clickApproveAndPublishSubmitButton() {
		driverManager.clickElement("xpath", approveAndPublishPublishButtonLocator);
	}

	public void clickChangeTemplateSubmitButton() {
		WebElement submitButton = this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable(
				"xpath", changeTemplateSubmitButtonLocator);
		submitButton.click();
		this.driverManager.waitForAnimation();
	}

	public void clickOnChangeTemplateYesButton() {
		WebElement changeTemplateYesButton = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("id", "acceptCTChange");
		changeTemplateYesButton.click();
		this.driverManager.waitForAnimation();
	}

	public void clickDeleteDeleteSubmitButton() {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				deleteDeletButtonLocator);
		WebElement deleteButton = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", deleteDeletButtonLocator);
		deleteButton.click();
		this.acceptDeletionAction();
	}

	public void acceptDeletionAction() {
		// Switch to the dialog
		driverManager.getDriver().switchTo().activeElement();
		// Click on Publish button
		this.driverManager.waitForAnimation();
		WebElement okButton = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				deleteOKButtonLocator);
		okButton.click();
	}

	public void rightClickCopyFolder(String parentWebElementLocator) {
		this.driverManager.waitForAnimation();
		this.getDriverManager().contextClick("xpath", parentWebElementLocator, false);
		driverManager.usingContextMenu(() -> {
			WebElement copyOption = this.driverManager
					.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", copyOptionLocator);
			copyOption.click();
		}, "Pages");
	}

	public void rightClickPasteOnAFolder(String parentWebElementLocator) {
		this.driverManager.waitForAnimation();
		this.driverManager.contextClick("xpath", parentWebElementLocator, true);
		driverManager.usingContextMenu(() -> {
			this.driverManager
					.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", pasteOptionLocator)
					.click();
		}, "Pages");
	}

	public void rightClickCutAFolder(String parentWebElementLocator) {
		this.getDriverManager().waitForAnimation();
		this.getDriverManager().contextClick("xpath", parentWebElementLocator, false);
		driverManager.usingContextMenu(() -> {
			WebElement cutOption = this.driverManager
					.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", cutOptionLocator);
			cutOption.click();
		}, "Pages");
	}

	public void rightClickDeleteAFolder(String parentWebElementLocator) {
		this.getDriverManager().contextClick("xpath", parentWebElementLocator, true);
		driverManager.usingContextMenu(() -> {
			WebElement deleteOption = this.driverManager
					.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", deleteOptionLocator);
			deleteOption.click();
		}, "Pages");
	}

	public void expandParentFolder(String parentElementLocator) {
		logger.info("Expanding folder on sidebar");
		this.driverManager.waitForAnimation();
		WebElement parentElement = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", parentElementLocator);
		if (!parentElement.getAttribute("class").contains("open")) {
			this.driverManager.waitUntilContentTooltipIsHidden();
			parentElement.click();
		}
	}

	public void collapseParentFolder(String parentElementLocator) {
		logger.info("Collapsing folder on sidebar");
		this.driverManager.waitForAnimation();

		WebElement parentElement = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", parentElementLocator);
		if (parentElement.getAttribute("class").contains("open")) {
			this.driverManager.waitUntilContentTooltipIsHidden();
			parentElement.click();
		}
	}

	public void switchToAFormByCssSelector(String cSSSelector) {
		// Switch to the iframe
		driverManager.getDriver().switchTo().defaultContent();
		driverManager.getDriver().switchTo().frame(
				this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("cssSelector", cSSSelector));
		this.driverManager.isElementPresentAndClickableBycssSelector(cSSSelector);
	}

	public void rightClickCopyContentPage(String parentWebElementLocator) {
		this.driverManager.waitForAnimation();
		this.getDriverManager().contextClick("xpath", parentWebElementLocator, true);
		driverManager.usingContextMenu(() -> {
			this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
					copyOptionLocatorForContentPage).click();
		}, "Pages");
	}

	public void rightClickCreatePageOnAPresentPage(String webElementLocator) {
		this.driverManager.waitForAnimation();
		this.getDriverManager().contextClick("xpath", webElementLocator, false);
		driverManager.usingContextMenu(() -> {
			WebElement addContent = this.driverManager
					.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", addNewContent);
			addContent.click();
		}, "Pages");
	}

	public void rightClickEditOnAPresentPage(String webElementLocator) {
		this.driverManager.waitForAnimation();
		this.getDriverManager().contextClick("xpath", webElementLocator, false);
		driverManager.usingContextMenu(() -> {
			WebElement editContent = this.driverManager
					.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", editParentOption);
			editContent.click();
		}, "Pages");
	}

	public void addAnImageToAnArticle() {
		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articleAddImageButton)
				.click();

		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", existingImagesButton)
				.click();

		// Switch to the iframe
		driverManager.getDriver().switchTo().defaultContent();
		driverManager.getDriver().switchTo()
				.frame(this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("cssSelector",
						".studio-ice-dialog > .bd iframe"));
		this.driverManager.isElementPresentAndClickableBycssSelector(".studio-ice-dialog > .bd iframe");
		driverManager.getDriver().switchTo().defaultContent();
		this.driverManager.getDriver().switchTo().frame(2);
		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", addCloseGearImageButton)
				.click();

	}

	public void addWinterWomanAssetImageToAnArticle() {
		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articleAddImageButton)
				.click();

		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", existingImagesButton)
				.click();

		// Switch to the iframe
		driverManager.getDriver().switchTo().defaultContent();
		driverManager.getDriver().switchTo()
				.frame(this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("cssSelector",
						".studio-ice-dialog > .bd iframe"));
		this.driverManager.isElementPresentAndClickableBycssSelector(".studio-ice-dialog > .bd iframe");
		driverManager.getDriver().switchTo().defaultContent();
		this.driverManager.getDriver().switchTo().frame(2);
		this.driverManager.scrollDown();
		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", addCloseWinterWomanButton)
				.click();

	}

	public void addAnImageToAnArticleUsingUploadOption() {
		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articleAddImageButton)
				.click();

		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", uploadImagesButton).click();

		// Switch to the form
		this.driverManager.waitForAnimation();
		driverManager.getDriver().switchTo().activeElement();

		File file = new File(FilesLocations.TESTINGIMAGEFILEPATH);
		this.driverManager.fileUploadUsingSendKeys(chooseFileInput, chooseFileButton, file.getAbsolutePath());

		driverManager.getDriver().switchTo().activeElement();


	}

	public void addAnImageToAnAuthorUsingUploadOption() {
		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", authorReplaceImageButton)
				.click();
		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", uploadImagesButton).click();

		// Switch to the form
		this.driverManager.waitForAnimation();
		driverManager.getDriver().switchTo().activeElement();

		File file = new File(FilesLocations.TESTINGIMAGEFILEPATH);
		this.driverManager.fileUploadUsingSendKeys(chooseFileInput, chooseFileButton, file.getAbsolutePath());

		this.driverManager.waitForAnimation();
		driverManager.getDriver().switchTo().activeElement();

		this.driverManager.waitForFullExpansionOfTree();
	}

	public void clickEditOptionOfRecentActivitySection() {
		this.driverManager.waitForAnimation();
		WebElement editOptionMyRecentActivity = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", editRecentActivity);
		editOptionMyRecentActivity.click();
	}

	public void clickOnEditOptionRecentActivity() {
		// Click on edit option of my recent activity senction
		this.clickEditOptionOfRecentActivitySection();
	}

	// See the page edited
	public void displayPageEdited() {
		WebElement seeThePageMyRecentActivity = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", seeThePageEdited);
		seeThePageMyRecentActivity.click();
	}

	public void seeThePageEdited() {
		// See the page edited
		this.displayPageEdited();
	}

	public void clickOnSitesOption() {
		this.driverManager.clickElement("xpath", sitesOptionXpath);
	}

	public void validateItemsOnRecentActivity(Boolean contentAsFolder) {
		this.driverManager.waitUntilDashboardWidgetsAreLoaded();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentName);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentURL);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentIcon);

		this.validateFooItemsOnRecentActivity(contentAsFolder,
				this.driverManager
						.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentIcon)
						.getAttribute("class"),
				this.driverManager
						.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentName)
						.getText(),
				this.driverManager
						.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivityContentURL)
						.getText());

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				recentActivitySecondContentName);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				recentActivitySecondContentURL);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				recentActivitySecondContentIcon);

		this.validateFooItemsOnRecentActivity(contentAsFolder,
				this.driverManager
						.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivitySecondContentIcon)
						.getAttribute("class"),
				this.driverManager
						.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivitySecondContentName)
						.getText(),
				this.driverManager
						.driverWaitUntilElementIsPresentAndDisplayed("xpath", recentActivitySecondContentURL)
						.getText());

	}

	public void validateFooItemsOnRecentActivity(Boolean contentAsFolder, String iconClass,
			String recentActivityContentName, String recenteActivityContentURL) {
		if (!contentAsFolder) {
			if (iconClass.contains("edited")) {
				this.driverManager.waitForAnimation();
				Assert.assertTrue(recentActivityContentName.contains("foo"));
				this.driverManager.waitForAnimation();
				Assert.assertTrue(recenteActivityContentURL.contains("/articles/2016/12/bar.xml"));
			} else {
				this.driverManager.waitForAnimation();
				Assert.assertTrue(recentActivityContentName.contains("foo"));
				this.driverManager.waitForAnimation();
				Assert.assertTrue(recenteActivityContentURL.contains("/articles/2016/12/foo.html"));
			}
		} else {
			if (iconClass.contains("edited")) {
				this.driverManager.waitForAnimation();
				Assert.assertTrue(recentActivityContentName.contains("foo"));
				this.driverManager.waitForAnimation();
				Assert.assertTrue(recenteActivityContentURL.contains("/articles/2016/12/bar"));
			} else {
				this.driverManager.waitForAnimation();
				Assert.assertTrue(recentActivityContentName.contains("foo"));
				this.driverManager.waitForAnimation();
				Assert.assertTrue(recenteActivityContentURL.contains("/articles/2016/12/foo"));
			}
		}
	}

}