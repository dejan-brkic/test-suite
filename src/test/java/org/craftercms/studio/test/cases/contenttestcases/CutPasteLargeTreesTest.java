package org.craftercms.studio.test.cases.contenttestcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.cases.StudioBaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

/**
 * 
 * @author Luis Hernandez
 *
 */

// Test Case Studio- Site Content ID:2
public class CutPasteLargeTreesTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String articlesFolder;
	private String siteDropdownElementXPath;
	private String siteDropdownListElementXPath;
	private String pasteOptionLocator;
	private String firstChildLocator;
	private String firstDestinationLocator;
	private String childFolder;
	private String topNavStatusIcon;
	private String finalChildFolderLocator;
	private static Logger logger = LogManager.getLogger(CutPasteLargeTreesTest.class);

	@BeforeMethod
	public void beforeTest() {

		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		articlesFolder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.articlesfolder");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		siteDropdownListElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdownlielement");
		pasteOptionLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.paste.option");
		firstChildLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.articles.2017folder");
		firstDestinationLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.articles.2016folder");
		finalChildFolderLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.articles.2016childfolder");
		childFolder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.articles.childfolder2017");
		topNavStatusIcon = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.statustopbaricon");
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

	public void cutAndPasteLongTreeIntoExistentFolder(String childLocator, String destinationFolderLocator) {
		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", childLocator);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", childLocator);
		this.driverManager.waitForAnimation();
		dashboardPage.rightClickCutAFolder(childLocator);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", destinationFolderLocator);
		this.driverManager.waitForFullExpansionOfTree();
		this.driverManager.contextClick("xpath", destinationFolderLocator, true);
		driverManager.usingContextMenu(() -> {
			this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", pasteOptionLocator)
					.click();
		});
		this.driverManager.waitForAnimation();
		this.driverManager.waitForFullExpansionOfTree();
	}

	public void continuePastingLongTreeIntoExistentFolder(String destinationFolderLocator) {
		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", destinationFolderLocator);
		this.driverManager.waitForAnimation();
		this.driverManager.waitForAnimation();
		this.driverManager.contextClick("xpath", destinationFolderLocator, true);
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
		// expand pages folder
		dashboardPage.expandPagesTree();

		// Expand Home Tree
		dashboardPage.expandHomeTree();

		// expand Articles folder
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articlesFolder);
		dashboardPage.expandParentFolder(articlesFolder);

		copyAndPasteLongTreeIntoExistentFolder(firstChildLocator, firstDestinationLocator);
		logger.info("Checking if the element {} was pasted with success", "2016/2017");
		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", firstDestinationLocator + childFolder)
				.isDisplayed());
	}

	public void step2() {
		this.driverManager.waitForAnimation();
		String secondDestinationLocator = firstDestinationLocator + childFolder;
		continuePastingLongTreeIntoExistentFolder(secondDestinationLocator);
		logger.info("Checking if the element {} was pasted with success", "2016/2017/2017");
		Assert.assertTrue(this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				secondDestinationLocator + childFolder).isDisplayed());

		this.driverManager.scrollDownIntoSideBar();
		this.driverManager.waitForAnimation();
		String thirdDestinationLocator = secondDestinationLocator + childFolder;
		continuePastingLongTreeIntoExistentFolder(thirdDestinationLocator);
		logger.info("Checking if the element {} was pasted with success", "2016/2017/2017/2017");
		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", thirdDestinationLocator + childFolder)
				.isDisplayed());

		this.driverManager.waitForAnimation();
		String fourthDestinationLocator = thirdDestinationLocator + childFolder;
		continuePastingLongTreeIntoExistentFolder(fourthDestinationLocator);
		logger.info("Checking if the element {} was pasted with success", "2016/2017/2017/2017/2017");
		Assert.assertTrue(this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				fourthDestinationLocator + childFolder).isDisplayed());

		this.driverManager.scrollDownIntoSideBar();
		this.driverManager.waitForAnimation();
		String fifthDestinationLocator = fourthDestinationLocator + childFolder;
		continuePastingLongTreeIntoExistentFolder(fifthDestinationLocator);
		logger.info("Checking if the element {} was pasted with success", "2016/2017/2017/2017/2017/2017");
		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", fifthDestinationLocator + childFolder)
				.isDisplayed());

		this.driverManager.waitForAnimation();
		String sixthDestinationLocator = fifthDestinationLocator + childFolder;
		continuePastingLongTreeIntoExistentFolder(sixthDestinationLocator);
		logger.info("Checking if the element {} was pasted with success", "2016/2017/2017/2017/2017/2017/2017");
		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", sixthDestinationLocator+ childFolder)
				.isDisplayed());

		this.driverManager.scrollDownIntoSideBar();
		this.driverManager.waitForAnimation();
		String seventhDestinationLocator = sixthDestinationLocator + childFolder;
		continuePastingLongTreeIntoExistentFolder(seventhDestinationLocator);
		logger.info("Checking if the element {} was pasted with success", "2016/2017/2017/2017/2017/2017/2017/2017");
		Assert.assertTrue(this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				seventhDestinationLocator + childFolder).isDisplayed());

		this.driverManager.waitForAnimation();
		String eighthDestinationLocator = seventhDestinationLocator + childFolder;
		continuePastingLongTreeIntoExistentFolder(eighthDestinationLocator);
		logger.info("Checking if the element {} was pasted with success", "2016/2017/2017/2017/2017/2017/2017/2017/2017");
		Assert.assertTrue(this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				eighthDestinationLocator + childFolder).isDisplayed());

		this.driverManager.scrollDownIntoSideBar();
		this.driverManager.waitForAnimation();
		String ninthDestinationLocator = eighthDestinationLocator + childFolder;
		continuePastingLongTreeIntoExistentFolder(ninthDestinationLocator);
		logger.info("Checking if the element {} was pasted with success", "2016/2017/2017/2017/2017/2017/2017/2017/2017/2017");
		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", ninthDestinationLocator + childFolder)
				.isDisplayed());

		this.driverManager.waitForAnimation();
		String tenthDestinationLocator = ninthDestinationLocator + childFolder;
		continuePastingLongTreeIntoExistentFolder(tenthDestinationLocator);
		logger.info("Checking if the element {} was pasted with success", "2016/2017/2017/2017/2017/2017/2017/2017/2017/2017/2017");
		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", tenthDestinationLocator + childFolder)
				.isDisplayed());

		driverManager.getDriver().navigate().refresh();

		this.driverManager.scrollDownIntoSideBar();
		this.driverManager.waitForAnimation();
		this.driverManager.waitForFullExpansionOfTree();
		
		this.driverManager.scrollDownIntoSideBar();
		this.driverManager.waitForFullExpansionOfTree();
		this.driverManager.scrollDownIntoSideBar();
		dashboardPage.collapseParentFolder(tenthDestinationLocator);
		this.driverManager.waitForAnimation();
		dashboardPage.collapseParentFolder(ninthDestinationLocator);
		this.driverManager.waitForAnimation();
		dashboardPage.collapseParentFolder(eighthDestinationLocator);
		this.driverManager.waitForAnimation();
		dashboardPage.collapseParentFolder(seventhDestinationLocator);
		this.driverManager.waitForAnimation();
		dashboardPage.collapseParentFolder(sixthDestinationLocator);
		this.driverManager.waitForAnimation();
		dashboardPage.collapseParentFolder(fifthDestinationLocator);
		this.driverManager.waitForAnimation();
		dashboardPage.collapseParentFolder(fourthDestinationLocator);
		this.driverManager.waitForAnimation();
		dashboardPage.collapseParentFolder(thirdDestinationLocator);
		this.driverManager.waitForAnimation();
		dashboardPage.collapseParentFolder(secondDestinationLocator);
		this.driverManager.waitForAnimation();
		dashboardPage.collapseParentFolder(firstDestinationLocator);

		this.driverManager.waitForFullExpansionOfTree();
		cutAndPasteLongTreeIntoExistentFolder(firstDestinationLocator, firstChildLocator);
		
		this.driverManager.waitForPasteTreeProcess();
		String elementClassValue = "";
		while (!(elementClassValue.contains("open"))) {
			elementClassValue=this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", firstChildLocator).getAttribute("class");
		}		
	}

	public void expandAllCutTrees() {

		// expand 2017 parent
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				firstChildLocator + "/../../../../../div[@class='ygtvchildren']//span[text()='2016']");
		dashboardPage.expandParentFolder(
				firstChildLocator + "/../../../../../div[@class='ygtvchildren']//span[text()='2016']");
		this.driverManager.waitForAnimation();
		this.driverManager.waitForFullExpansionOfTree();

		// expand the all folder tree
		for (int i = 0; i < 10; i++) {
			// append the last opened 2017 folder
			finalChildFolderLocator = finalChildFolderLocator + childFolder;

			this.driverManager.scrollDownIntoSideBar();
			this.driverManager.waitForAnimation();
			this.driverManager.waitForFullExpansionOfTree();
			this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
					finalChildFolderLocator);

			// at first time expand the 2016 child on 2017 folder
			dashboardPage.expandParentFolder(finalChildFolderLocator);
		}
	}

	public void step3() {
		logger.info("Executing bulk publish");
		this.driverManager.waitForPasteTreeProcess();
		this.driverManager.scrollDownIntoSideBar();
		this.driverManager.waitForAnimation();
		previewPage.bulkPublish("/site/website/articles");

		driverManager.getDriver().navigate().refresh();
		this.driverManager.scrollDownIntoSideBar();
		this.driverManager.waitForAnimation();
		firstChildLocator = firstChildLocator.replace("/div[3]", "");
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", firstChildLocator);
		this.driverManager.waitForAnimation();
		dashboardPage.expandParentFolder(firstChildLocator);
		this.expandAllCutTrees();

		this.driverManager.waitForAnimation();
		this.driverManager.scrollDownIntoSideBar();
		this.driverManager.waitForAnimation();
		String articleXpath = finalChildFolderLocator
				+ "/../../../../../div[@class='ygtvchildren']/div//span[contains(text(),'Men Styles For Winter')]";

		logger.info("Verify Article is published");
		for (int i = 0; i < 2; i++) {
			this.driverManager.waitForAnimation();
			this.driverManager.waitForFullExpansionOfTree();
			this.driverManager.scrollDownIntoSideBar();
			this.driverManager.waitForFullExpansionOfTree();
			this.driverManager.waitUntilSidebarOpens();
			this.driverManager.scrollRightIntoSideBar(
					finalChildFolderLocator + "/../../../../../div[@class='ygtvchildren']//span[text()='1']");

			// if the folder is not expanded do a click on it
			if (!(this.driverManager
					.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
							finalChildFolderLocator + "/../../../../../div[@class='ygtvchildren']//span[text()='1']")
					.getAttribute("class").contains("open"))) {
				this.driverManager.waitUntilContentTooltipIsHidden();
				this.driverManager.waitForAnimation();
				this.driverManager.waitForFullExpansionOfTree();
				this.driverManager.waitForAnimation();
				this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
						finalChildFolderLocator + "/../../../../../div[@class='ygtvchildren']//span[text()='1']")
						.click();
			}

			try {
				// Wait for the article and click it.
				this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articleXpath);
				this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articleXpath)
						.click();
				this.driverManager.waitForAnimation();
				this.driverManager.waitUntilAttributeContains("xpath", topNavStatusIcon, "class", "undefined live");
				break;

			} catch (TimeoutException e) {
				this.driverManager.takeScreenshot("PageNotPublishedOnTopNavBar");
				logger.warn("Content page is not published yet, checking again if it has published icon on top bar");
				driverManager.getDriver().navigate().refresh();
			}
		}

		this.driverManager.waitForFullExpansionOfTree();
		String elementClassValue = this.driverManager.getDriver().findElement(By.xpath(topNavStatusIcon))
				.getAttribute("class");
		Assert.assertTrue(elementClassValue.contains("undefined live"));

	}

	@Test(priority = 0)
	public void verifyThatStudioAllowsToCutPasteLargeTreesTest() {
		loginAndGoToPreview();

		step1();

		step2();

		step3();
	}
}
