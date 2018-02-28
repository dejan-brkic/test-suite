package org.craftercms.studio.test.cases.contenttestcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.cases.StudioBaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

/**
 * 
 * @author Luis Hernandez
 *
 */

// Test Case Studio- Site Content ID:1
public class CopyPasteLargeTreesTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String articlesFolder;
	private String siteDropdownElementXPath;
	private String siteDropdownListElementXPath;
	private String pasteOptionLocator;
	private String siteStatusIcon;
	private static Logger logger = LogManager.getLogger(CopyPasteLargeTreesTest.class);

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
		siteStatusIcon = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.statustopbaricon");
	}

	public void copyAndPasteLongTreeIntoExistentFolder(String childLocator, String destinationFolderLocator) {
		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", childLocator);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", childLocator);
		this.driverManager.waitForAnimation();
		dashboardPage.rightClickCopyFolder(childLocator);
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

	public void continuePastingLongTreeIntoExistentFolder(String destinationFolderLocator) {
		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", destinationFolderLocator);
		this.driverManager.waitForAnimation();
		this.driverManager.waitForAnimation();
		this.driverManager.contextClick("xpath", destinationFolderLocator, false);
		driverManager.usingContextMenu(() -> {
			this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", pasteOptionLocator)
					.click();
		});
	}

	@Test(priority = 0)
	public void verifyThatStudioAllowsToCopyPasteLargeTreesTest() {

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

		// expand pages folder
		dashboardPage.expandPagesTree();

		// Expand Home Tree
		dashboardPage.expandHomeTree();

		// expand Articles folder
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articlesFolder);
		dashboardPage.expandParentFolder(articlesFolder);

		String firstChildLocator = ".//span[text()='articles']/../../../../..//span[text()='2017']";
		String firstDestinationLocator = ".//span[text()='articles']/../../../../..//span[text()='2016']";
		String childFolder = "/../../../../../div[@class='ygtvchildren']//span[text()='2017']";

		copyAndPasteLongTreeIntoExistentFolder(firstChildLocator, firstDestinationLocator);
		logger.info("Checking if the element {} was pasted with success", firstDestinationLocator + childFolder);
		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", firstDestinationLocator + childFolder)
				.isDisplayed());

		this.driverManager.waitForAnimation();
		String secondDestinationLocator = firstDestinationLocator + childFolder;
		continuePastingLongTreeIntoExistentFolder(secondDestinationLocator);
		logger.info("Checking if the element {} was pasted with success", secondDestinationLocator + childFolder);
		Assert.assertTrue(this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				secondDestinationLocator + childFolder).isDisplayed());

		this.driverManager.scrollDownIntoSideBar();
		this.driverManager.waitForAnimation();
		String thirdDestinationLocator = secondDestinationLocator + childFolder;
		continuePastingLongTreeIntoExistentFolder(thirdDestinationLocator);
		logger.info("Checking if the element {} was pasted with success", thirdDestinationLocator + childFolder);
		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", thirdDestinationLocator + childFolder)
				.isDisplayed());

		this.driverManager.waitForAnimation();
		String fourthDestinationLocator = thirdDestinationLocator + childFolder;
		continuePastingLongTreeIntoExistentFolder(fourthDestinationLocator);
		logger.info("Checking if the element {} was pasted with success", fourthDestinationLocator + childFolder);
		Assert.assertTrue(this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				fourthDestinationLocator + childFolder).isDisplayed());

		this.driverManager.scrollDownIntoSideBar();
		this.driverManager.waitForAnimation();
		String fifthDestinationLocator = fourthDestinationLocator + childFolder;
		continuePastingLongTreeIntoExistentFolder(fifthDestinationLocator);
		logger.info("Checking if the element {} was pasted with success", fifthDestinationLocator + childFolder);
		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", fifthDestinationLocator + childFolder)
				.isDisplayed());

		this.driverManager.waitForAnimation();
		String sixthDestinationLocator = fifthDestinationLocator + childFolder;
		continuePastingLongTreeIntoExistentFolder(sixthDestinationLocator);
		logger.info("Checking if the element {} was pasted with success", sixthDestinationLocator + childFolder);
		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", sixthDestinationLocator + childFolder)
				.isDisplayed());

		this.driverManager.scrollDownIntoSideBar();
		this.driverManager.waitForAnimation();
		String seventhDestinationLocator = sixthDestinationLocator + childFolder;
		continuePastingLongTreeIntoExistentFolder(seventhDestinationLocator);
		logger.info("Checking if the element {} was pasted with success", seventhDestinationLocator + childFolder);
		Assert.assertTrue(this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				seventhDestinationLocator + childFolder).isDisplayed());

		this.driverManager.waitForAnimation();
		String eighthDestinationLocator = seventhDestinationLocator + childFolder;
		continuePastingLongTreeIntoExistentFolder(eighthDestinationLocator);
		logger.info("Checking if the element {} was pasted with success", eighthDestinationLocator + childFolder);
		Assert.assertTrue(this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				eighthDestinationLocator + childFolder).isDisplayed());

		this.driverManager.scrollDownIntoSideBar();
		this.driverManager.waitForAnimation();
		String ninthDestinationLocator = eighthDestinationLocator + childFolder;
		continuePastingLongTreeIntoExistentFolder(ninthDestinationLocator);
		logger.info("Checking if the element {} was pasted with success", ninthDestinationLocator + childFolder);
		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", ninthDestinationLocator + childFolder)
				.isDisplayed());

		this.driverManager.waitForAnimation();
		String tenthDestinationLocator = ninthDestinationLocator + childFolder;
		continuePastingLongTreeIntoExistentFolder(tenthDestinationLocator);
		logger.info("Checking if the element {} was pasted with success", tenthDestinationLocator + childFolder);
		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", tenthDestinationLocator + childFolder)
				.isDisplayed());
		this.driverManager.scrollDownIntoSideBar();
		this.driverManager.waitForAnimation();

		this.driverManager.waitForAnimation();
		this.driverManager.waitForFullExpansionOfTree();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				tenthDestinationLocator + childFolder);
		dashboardPage.expandParentFolder(tenthDestinationLocator + childFolder);
		this.driverManager.waitForAnimation();
		this.driverManager.waitForFullExpansionOfTree();

		logger.info("Executing bulk publish");
		previewPage.bulkPublish("/site/website/articles");

		this.driverManager.waitForAnimation();
		this.driverManager.scrollDownIntoSideBar();
		this.driverManager.waitForAnimation();
		String articleXpath = tenthDestinationLocator + childFolder
				+ "/../../../../../div[@class='ygtvchildren']/div//span[contains(text(),'Men Styles For Winter')]";

		logger.info("Verify Article is published");
		this.driverManager.waitForAnimation();
		this.driverManager.waitForFullExpansionOfTree();
		this.driverManager.scrollDownIntoSideBar();
		this.driverManager.waitForFullExpansionOfTree();
		this.driverManager.waitUntilSidebarOpens();
		this.driverManager.scrollRightIntoSideBar(
				tenthDestinationLocator + childFolder + "/../../../../../div[@class='ygtvchildren']//span[text()='1']");
		if (!(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
						tenthDestinationLocator + childFolder
								+ "/../../../../../div[@class='ygtvchildren']//span[text()='1']")
				.getAttribute("class").contains("open"))) {
			this.driverManager.waitUntilContentTooltipIsHidden();
			this.driverManager.waitForAnimation();
			this.driverManager.waitForFullExpansionOfTree();
			this.driverManager.waitForAnimation();
			this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", tenthDestinationLocator
					+ childFolder + "/../../../../../div[@class='ygtvchildren']//span[text()='1']").click();
		}
		
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articleXpath);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articleXpath).click();
		this.driverManager.waitForAnimation();

		Assert.assertTrue(this.driverManager.getDriver().findElement(By.xpath(siteStatusIcon)).getAttribute("class")
				.contains("undefined live"));
	}
}
