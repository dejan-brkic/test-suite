package org.craftercms.studio.test.cases.contenttestcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.cases.StudioBaseTest;
import org.openqa.selenium.WebElement;

/**
 * 
 * @author Luis Hernandez
 *
 */

// Test Case Studio- Site Content ID:3
public class DependenciesCalculationRefersToAnItemTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String siteDropdownElementXPath;
	private String siteDropdownListElementXPath;
	private String pasteOptionLocator;
	private String generalEditOption;
	private String dependenciesMenuOption;
	private String styleLocator;
	private String articlesFolder;
	private String folder2016Locator;
	private String selectAllSegmentsCheckBox;
	private String selectAllCategoriesCheckBox;
	private String folder2017Locator;
	private String dependeciesOption;
	private static Logger logger = LogManager.getLogger(DependenciesCalculationRefersToAnItemTest.class);

	@BeforeMethod
	public void beforeTest() {

		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		siteDropdownListElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdownlielement");
		pasteOptionLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.paste.option");
		generalEditOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.edittopnavoption");
		dependenciesMenuOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.dependenciestopnavoption");
		styleLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.crafter3loadtest.stylecontentpage");
		articlesFolder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.articlesfolder");
		folder2016Locator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.articles.2016folder");
		selectAllSegmentsCheckBox = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame2.article_select_all_segments_checkbox");
		selectAllCategoriesCheckBox = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("frame2.select_All_Categories_CheckBox");
		folder2017Locator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.articles.2017folder");
		dependeciesOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.dependenciesoption");
	}

	public void copyAndPasteLongTreeIntoExistentFolder(String childLocator, String destinationFolderLocator) {
		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", childLocator);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", childLocator);
		this.driverManager.waitForAnimation();
		dashboardPage.rightClickCopyFolder(childLocator);
		this.driverManager.waitForAnimation();
		this.driverManager.waitForFullExpansionOfTree();
		dashboardPage.clickCopyButtonOnTreeSelector();

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", destinationFolderLocator);
		this.driverManager.waitForAnimation();
		this.driverManager.waitForFullExpansionOfTree();
		this.driverManager.contextClick("xpath", destinationFolderLocator, false);
		driverManager.usingContextMenu(() -> {
			this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", pasteOptionLocator)
					.click();
		});

	}

	public void loginAndGoToPreview() {
		loginPage.loginToCrafter(userName, password);

		driverManager.waitUntilLoginCloses();

		// go to preview page
		homePage.goToPreviewPage();

		if (this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", siteDropdownElementXPath)
				.isDisplayed())
			if (!(this.driverManager.waitUntilElementIsPresent("xpath", siteDropdownListElementXPath)
					.getAttribute("class").contains("site-dropdown-open")))
				this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", siteDropdownElementXPath)
						.click();
			else
				throw new NoSuchElementException(
						"Site creation process is taking too long time and the element was not found");
	}

	public void step1() {
		// Click on Home
		dashboardPage.clickHomeTree();

		logger.info("Open dependencies for the previous created element");
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", generalEditOption);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dependenciesMenuOption);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dependenciesMenuOption)
				.click();

		// check dependencies are listed
		logger.info("Check Listed Dependencies");
		previewPage.checkNoDependenciesForRefersToAPage();
	}

	public void step2() {
		// Click on Style page
		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", styleLocator);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", styleLocator).click();

		logger.info("Open dependencies for the previous created element");
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", generalEditOption);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dependenciesMenuOption);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dependenciesMenuOption)
				.click();

		// check dependencies are listed
		logger.info("Check Listed Dependencies");
		previewPage.checkNoDependenciesForRefersToAPage();
	}

	public void step3() {
		// Click on Home
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articlesFolder);
		dashboardPage.expandParentFolder(articlesFolder);

		this.driverManager.waitForAnimation();
		dashboardPage.expandParentFolder(folder2017Locator);

		this.driverManager.waitForAnimation();
		dashboardPage
				.expandParentFolder(folder2017Locator + "/../../../../../div[@class='ygtvchildren']//span[text()='1']");

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				".//span[text()='Women Styles for Winter']");
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				".//span[text()='Women Styles for Winter']").click();

		logger.info("Open dependencies for the previous created element");
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", generalEditOption);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dependenciesMenuOption);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dependenciesMenuOption)
				.click();

		// check dependencies are listed
		logger.info("Check Listed Dependencies");
		previewPage.checkNoDependenciesForRefersToAPage();
	}

	public void step4() {
		this.driverManager.waitForAnimation();
		dashboardPage.expandParentFolder(folder2016Locator);

		this.driverManager.waitForAnimation();
		dashboardPage
				.expandParentFolder(folder2016Locator + "/../../../../../div[@class='ygtvchildren']//span[text()='6']");

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				".//span[text()='Testing1']");
		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", ".//span[text()='Testing1']").click();

		logger.info("Open dependencies for the previous created element");
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", generalEditOption);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dependenciesMenuOption);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dependenciesMenuOption)
				.click();

		// check dependencies are listed
		logger.info("Check Listed Dependencies");
		previewPage.checkNoDependenciesForRefersToAPage();
		
		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articlesFolder);
		dashboardPage.collapseParentFolder(articlesFolder);
	}

	public void step5() {

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				".//span[text()='Search Results']");
		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", ".//span[text()='Search Results']").click();

		logger.info("Open dependencies for the previous created element");
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", generalEditOption);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dependenciesMenuOption);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dependenciesMenuOption)
				.click();

		// check dependencies are listed
		logger.info("Check Listed Dependencies");
		previewPage.checkNoDependenciesForRefersToAPage();
	}

	public void step6() {
		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				".//a[@id='components-tree']");
		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", ".//a[@id='components-tree']").click();
		
		this.driverManager.waitForAnimation();
		dashboardPage.expandParentFolder(".//span[text()='components']");

		this.driverManager.waitForAnimation();
		dashboardPage
				.expandParentFolder(".//span[text()='articles-widget']");

		this.driverManager.waitForAnimation();
		this.driverManager.scrollDownIntoSideBar();
		this.rightClickAndClickOnDependencies(
				".//span[text()='Latest Articles Widget']");
		
		// check dependencies are listed
		logger.info("Check Listed Dependencies");
		previewPage.checkDependenciesForRefersToAComponent("Latest Articles Widget");
	}
	
	public void rightClickAndClickOnDependencies(String itemLocator) {
		this.driverManager.waitForAnimation();
		WebElement element = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				itemLocator);
		this.driverManager.contextClick(this.driverManager.getDriver(), element, false);
		
		driverManager.usingContextMenu(() -> {
			WebElement copyComponentToNewFolder = this.driverManager
					.driverWaitUntilElementIsPresentAndDisplayed("xpath", dependeciesOption);
			copyComponentToNewFolder.click();
		});		
		this.driverManager.waitForAnimation();
	}
	
	public void changeBodyToNotRequiredOnPageArticleContent() {
		previewPage.changeBodyOfArticlePageToNotRequired();
	}

	public void createNewPageArticle(String folderLocation) {
		logger.info("Create Article Content");
		this.driverManager.waitForAnimation();
		previewPage.createPageArticleContentUsingUploadedImage("test", "Testing1", "test", folderLocation,
				selectAllCategoriesCheckBox, selectAllSegmentsCheckBox, "ArticleSubject", "ArticleAuthor",
				"ArticleSummary");

		this.driverManager.waitUntilSidebarOpens();
	}

	@Test(priority = 0)
	public void verifyThatStudioAllowsToCopyPasteContentWithSharedComponents() {
		loginAndGoToPreview();

		logger.info("Change Article Page body content to not required");
		this.changeBodyToNotRequiredOnPageArticleContent();

		this.driverManager.waitUntilSidebarOpens();
		// expand pages folder
		dashboardPage.expandPagesTree();

		// Expand Home Tree
		this.driverManager.waitForAnimation();
		dashboardPage.expandHomeTree();

		// expand Articles folder
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articlesFolder);
		dashboardPage.expandParentFolder(articlesFolder);

		this.driverManager.waitForAnimation();
		dashboardPage.expandParentFolder(folder2016Locator);

		this.driverManager.waitForAnimation();
		dashboardPage
				.expandParentFolder(folder2016Locator + "/../../../../../div[@class='ygtvchildren']//span[text()='6']");
		this.createNewPageArticle(folder2016Locator + "/../../../../../div[@class='ygtvchildren']//span[text()='6']");

		this.driverManager.waitForAnimation();
		dashboardPage.collapseParentFolder(
				folder2016Locator + "/../../../../../div[@class='ygtvchildren']//span[text()='6']");

		this.driverManager.waitForAnimation();
		dashboardPage.collapseParentFolder(folder2016Locator);

		this.driverManager.waitForFullExpansionOfTree();

		step1();

		step2();

		step3();

		step4();

		step5();
		
		step6();
	}
}
