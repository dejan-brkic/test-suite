package org.craftercms.studio.test.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.utils.UIElementsPropertiesManager;
import org.craftercms.studio.test.utils.WebDriverManager;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class SiteConfigPage {

	private WebDriverManager driverManager;
	private String contentTypeOption;
	private String openExistingTypeOption;
	private String okButton;
	private String saveButton;
	private String genericTitle;
	private String inputTitle;
	private String inputIceGroup;
	private String inputDescription;
	private String inputDefaultValue;
	private String clickOnInputSection;
	private String clickOnRepeatingGroupSection;
	private String clickOnTextAreaSection;
	private String pageArticleContentTypeOption;
	private String clickOnRTESection;
	private String clickOnDropdownSection;
	private String clickOnCheckBoxSection;
	private String clickOnGroupedCheckBoxesSection;
	private String clickOnItemSelectorSection;
	public String clickOnImageSection;
	public String clickOnVideoSection;
	public String clickOnLabelSection;
	public String clickOnPageOrderSection;
	public String clickOnFileNameSection;
	public String clickOnAutoFileNameSection;
	public String clickOnDataSourceChildContentSection;
	public String clickOnDataSourceImageUploadedFromDesktopSection;
	public String clickOnDataSourceImageUploadedFromRepositorySection;
	public String clickOnDataSourceImageUploadedFromCMISRepositorySection;
	private String contentTypeVisualContainer;
	private String contentTypeSavedNotification;
	private static Logger logger = LogManager.getLogger(SiteConfigPage.class);

	public SiteConfigPage(WebDriverManager driverManager, UIElementsPropertiesManager UIElementsPropertiesManager) {
		this.driverManager = driverManager;
		contentTypeOption = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.content_type_option");
		openExistingTypeOption = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.open_Existing_Type_Option");
		okButton = UIElementsPropertiesManager.getSharedUIElementsLocators().getProperty("adminconsole.ok_Button");
		saveButton = UIElementsPropertiesManager.getSharedUIElementsLocators().getProperty("adminconsole.save_Button");
		genericTitle = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.generic_title");
		inputTitle = UIElementsPropertiesManager.getSharedUIElementsLocators().getProperty("adminconsole.input_Title");
		inputIceGroup = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.input_Ice_Group");
		inputDescription = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.input_Description");
		inputDefaultValue = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.input_Default_Value");
		clickOnInputSection = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.contenttypecontainerinput");
		clickOnRepeatingGroupSection = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.contenttypecontainerrepeatinggroup");
		clickOnTextAreaSection = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.contenttypecontainertextarea");
		pageArticleContentTypeOption = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.pagearticleoption");
		clickOnRTESection = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.contenttypecontainerrte");
		clickOnDropdownSection = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.contenttypecontainerdropdown");
		clickOnCheckBoxSection = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.contenttypecontainercheckbox");
		clickOnGroupedCheckBoxesSection = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.contenttypecontainergroupedcheckboxes");
		clickOnItemSelectorSection = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.contenttypecontaineritemselector");
		clickOnImageSection = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.contenttypecontainerimage");
		clickOnVideoSection = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.contenttypecontainervideo");
		clickOnLabelSection = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.contenttypecontainerlabel");
		clickOnPageOrderSection = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.contenttypecontainerpageorder");
		clickOnFileNameSection = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.contenttypecontainerfilename");
		clickOnAutoFileNameSection = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.contenttypecontainerautofilename");
		clickOnDataSourceChildContentSection = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.contenttypecontainerdatasourcechildcontent");
		clickOnDataSourceImageUploadedFromDesktopSection = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.contenttypecontainerdatasourceimageuploadedfromdesktop");
		clickOnDataSourceImageUploadedFromRepositorySection = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty(
						"adminconsole.contenttype.entry.contenttypecontainerdatasourceimageuploadedfromrepository");
		clickOnDataSourceImageUploadedFromCMISRepositorySection = UIElementsPropertiesManager
				.getSharedUIElementsLocators().getProperty(
						"adminconsole.contenttype.entry.contenttypecontainerdatasourceimageuploadedfromCMISrepository");
		contentTypeVisualContainer = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.contenttype.visualcontainer");
		contentTypeSavedNotification = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.contenttype.savednotification");
	}

	// Click on Content Type option

	public void clickContentTypeOption() {
		WebElement contentTypeOpt = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				contentTypeOption);
		contentTypeOpt.click();
	}

	public void selectContentTypeOption() {
		// Click on Content Type option
		logger.debug("select content types");
		this.clickContentTypeOption();
	}

	// Click on open existing Type option

	public void clickOpenExistingTypeOption() {
		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", openExistingTypeOption);
		WebElement openExistingTypeOpt = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				openExistingTypeOption);
		openExistingTypeOpt.click();
	}

	public void clickExistingTypeOption() {
		// Click on open existing Type option
		this.clickOpenExistingTypeOption();
	}

	public void selectPageArticleContentTypeOption() {
		logger.debug("Click on Existing Type Option");
		WebElement selectPageArticleOption = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", pageArticleContentTypeOption);
		selectPageArticleOption.click();

	}

	// Confirm the content type selected
	public void okContentTypeSelected() {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", okButton);
		WebElement okButtonOpt = this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				okButton);
		okButtonOpt.click();
		// Delete thread of 2 seconds
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", contentTypeVisualContainer);
	}

	public void confirmContentTypeSelected() {
		// Confirm the content type selected
		logger.info("Confirm Selected type");
		this.okContentTypeSelected();
	}

	 // Save the section dropped.
    public void saveSectionDropped() {
        this.driverManager.waitForAnimation();
        for (int i = 0; i < 3; i++) {
            try {
                this.driverManager.waitUntilElementIsClickable("xpath", saveButton).click();
                WebElement notification = this.driverManager.waitUntilElementIsDisplayed("xpath",
                        contentTypeSavedNotification);
                this.driverManager.waitUntilContentTypeNotificationIsNotDisplayed("xpath", "div", notification);
                this.driverManager.waitForAnimation();
                break;
            } catch (TimeoutException e) {
                logger.warn("Click on Save button didn't work, trying again");
            } catch (WebDriverException exception) {
                driverManager.takeScreenshot("ErrorDialogWasDisplayed");
                WebElement error = this.driverManager.waitUntilElementIsDisplayed("xpath",
                        ".//div[@class='bd']");
                logger.warn("Error dialog was displayed, the error is: {}", error.getText());        
            }
        }
    }
   
	public void saveDragAndDropProcess() {
		// Save the section dropped.
		logger.debug("Click on Save button");
		this.driverManager.waitForAnimation();
		this.saveSectionDropped();
	}

	// Click on generic title to edit the context type selected.

	public void clickOnGenericTitle() {
		WebElement ClickTitle = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				genericTitle);
		ClickTitle.click();
	}

	public void doClickOnGenericTitle() {
		// Click on generic title to edit the context type selected.
		this.clickOnGenericTitle();
	}

	// Set title
	public void setTitle(String strTitle) {
		driverManager.sendText("xpath", inputTitle, strTitle);
	}

	// Set ICE group
	public void setIceGroup(String strICEGroup) {
		driverManager.sendText("xpath",inputIceGroup,strICEGroup);
	}

	// Set description
	public void setDescription(String strDescription) {
		driverManager.sendText("xpath",inputDescription,strDescription);
	}

	// Set default value
	public void setDefaultValue(String strDefaultValue) {
		driverManager.sendText("xpath",inputDefaultValue,strDefaultValue);
	}

	public void completeControlsFieldsBasics(String strTitle, String strICEGroup, String strDescription,
			String strDefaultValue) {
		// Fill title
		this.setTitle(strTitle);
		// Fill Ice group
		this.setIceGroup(strICEGroup);
		// Fill description
		this.setDescription(strDescription);
		// Fill default value
		this.setDefaultValue(strDefaultValue);
	}

	public void completeControlsFieldsBasics2(String strTitle, String strICEGroup, String strDescription) {
		// Fill title
		this.setTitle(strTitle);
		// Fill Ice grou
		this.setIceGroup(strICEGroup);
		// Fill description
		this.setDescription(strDescription);
	}

	// Click on input section to can view the properties
	public void clickOnInputSectionToViewTheProperties() {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", clickOnInputSection);
		WebElement showSection = this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				clickOnInputSection);
		showSection.click();
	}

	public void clickInputSection() {
		// Confirm the content type selected
		this.clickOnInputSectionToViewTheProperties();
	}

	public void clickRepeatingGroupSection() {
		// Confirm the content type selected
		this.clickOnRepeatingGroupToViewTheProperties();
	}

	// Click on Repeating group to view the properties of it
	public void clickOnRepeatingGroupToViewTheProperties() {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				clickOnRepeatingGroupSection);
		WebElement showSection = this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				clickOnRepeatingGroupSection);
		showSection.click();
	}

	public void clickTextAreaSection() {
		// Confirm the content type selected
		this.clickOnTextAreaToViewTheProperties();
	}

	// Click on Repeating group to view the properties of it
	public void clickOnTextAreaToViewTheProperties() {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", clickOnTextAreaSection);
		WebElement showSection = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				clickOnTextAreaSection);
		showSection.click();
	}

	public void selectEntryContentTypeFromAdminConsole() {
		// select content types
		this.selectContentTypeOption();
		// open content types
		this.clickExistingTypeOption();
		// Confirm the content type selected
		this.confirmContentTypeSelected();
	}

	public WebDriverManager getDriverManager() {
		return driverManager;
	}

	public void setDriverManager(WebDriverManager driverManager) {
		this.driverManager = driverManager;
	}

	public void selectPageArticleContentType() {
		this.selectPageArticleContentTypeOption();
	}

	public void clickRTESectionToViewProperties() {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", clickOnRTESection);
		WebElement showSection = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				clickOnRTESection);
		showSection.click();
	}

	public void clickRTESection() {
		this.clickRTESectionToViewProperties();

	}

	public void clickDropdownSectionToViewProperties() {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", clickOnDropdownSection);
		WebElement showSection = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				clickOnDropdownSection);
		showSection.click();
	}

	public void clickDropdownSection() {
		this.clickDropdownSectionToViewProperties();
	}

	public void clickDateTimeSectionToViewProperties() {

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", clickOnDropdownSection);

		WebElement showSection = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				clickOnDropdownSection);
		showSection.click();
	}

	public void clickDateTimeSection() {
		this.clickDateTimeSectionToViewProperties();
	}

	public void clickCheckBoxSectionToViewProperties() {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", clickOnCheckBoxSection);
		WebElement showSection = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				clickOnCheckBoxSection);
		showSection.click();
	}

	public void clickCheckBoxSection() {
		this.clickCheckBoxSectionToViewProperties();

	}

	public void clickGroupedCheckBoxesSectionToViewProperties() {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				clickOnGroupedCheckBoxesSection);
		WebElement showSection = this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				clickOnGroupedCheckBoxesSection);
		showSection.click();
	}

	public void clickGroupedCheckBoxSection() {
		this.clickGroupedCheckBoxesSectionToViewProperties();
	}

	public void clickItemSelectorToViewProperties() {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", clickOnItemSelectorSection);
		WebElement showItemSelectorSection = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				clickOnItemSelectorSection);
		showItemSelectorSection.click();
	}

	public void clickItemSelectorSection() {
		this.clickItemSelectorToViewProperties();
	}

	public void clickImageToViewProperties() {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", clickOnImageSection);
		WebElement showSection = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				clickOnImageSection);
		showSection.click();
	}

	public void clickImageSection() {
		this.clickImageToViewProperties();
	}

	public void clickVideoToViewProperties() {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", clickOnVideoSection);
		WebElement showSection = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				clickOnVideoSection);
		showSection.click();
	}

	public void clickVideoSection() {
		this.clickVideoToViewProperties();
	}

	public void clickLabelToViewProperties() {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", clickOnLabelSection);
		WebElement showLabelSection = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				clickOnLabelSection);
		showLabelSection.click();
	}

	public void clickLabelSection() {
		this.clickLabelToViewProperties();
	}

	public void clickPageOrderToViewProperties() {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", clickOnPageOrderSection);
		WebElement showSection = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				clickOnPageOrderSection);
		showSection.click();
	}

	public void clickPageOrderSection() {
		this.clickPageOrderToViewProperties();
	}

	public void clickFileNameToViewProperties() {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", clickOnFileNameSection);
		WebElement showSection = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				clickOnFileNameSection);
		showSection.click();
	}

	public void clickFileNameSection() {
		this.clickFileNameToViewProperties();
	}

	public void completeControlFieldsBasics(String strTitle, String strICEGroup, String strDescription,
			String strDefaultValue) {
		this.completeControlsFieldsBasics(strTitle, strICEGroup, strDescription, strDefaultValue);
	}

	public void completeControlFieldsBasics2(String strTitle, String strICEGroup, String strDescription) {
		this.completeControlsFieldsBasics2(strTitle, strICEGroup, strDescription);
	}

	public void clickAutoFileNameToViewProperties() {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", clickOnAutoFileNameSection);
		WebElement showSection = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				clickOnAutoFileNameSection);
		showSection.click();
	}

	public void clickAutoFileNameSection() {
		this.clickAutoFileNameToViewProperties();
	}

	public void completeDataSourcesFieldsBasics(String strTitle) {
		// Fill title
		this.driverManager.waitForAnimation();
		this.setTitle(strTitle);
	}

	public void completeDataSourceFieldsBasics(String strTitle) {
		this.completeDataSourcesFieldsBasics(strTitle);
	}

	public void clickDataSourceChildContentToViewProperties() {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				clickOnDataSourceChildContentSection);
		WebElement showSection = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				clickOnDataSourceChildContentSection);
		showSection.click();
	}

	public void clickDataSourceChildContentSection() {
		clickDataSourceChildContentToViewProperties();
	}

	public void clickDataSourceImageUploadedFromDesktopToViewProperties() {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				clickOnDataSourceImageUploadedFromDesktopSection);
		WebElement showSection = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				clickOnDataSourceImageUploadedFromDesktopSection);
		showSection.click();
	}

	public void clickDataSourceImageUploadedFromDesktopSection() {
		clickDataSourceImageUploadedFromDesktopToViewProperties();
	}

	public void clickDataSourceImageUploadedFromRepositoryToViewProperties() {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				clickOnDataSourceImageUploadedFromRepositorySection);
		WebElement showSection = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				clickOnDataSourceImageUploadedFromRepositorySection);
		showSection.click();

	}

	public void clickDataSourceImageUploadedFromRepositorySection() {
		clickDataSourceImageUploadedFromRepositoryToViewProperties();
	}

	public void clickDataSourceImageUploadedFromCMISRepositoryToViewProperties() {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				clickOnDataSourceImageUploadedFromCMISRepositorySection);
		WebElement showSection = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				clickOnDataSourceImageUploadedFromCMISRepositorySection);
		showSection.click();
	}

	public void clickDataSourceImageUploadedFromCMISRepositorySection() {
		clickDataSourceImageUploadedFromCMISRepositoryToViewProperties();
	}
}