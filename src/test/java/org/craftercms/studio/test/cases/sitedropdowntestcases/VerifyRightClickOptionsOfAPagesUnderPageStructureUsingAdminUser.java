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
package org.craftercms.studio.test.cases.sitedropdowntestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.cases.StudioBaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * 
 * 
 * @author Juan Camacho A
 *
 */
//Test Case Studio- Site Dropdown ID:8
public class VerifyRightClickOptionsOfAPagesUnderPageStructureUsingAdminUser
		extends StudioBaseTest {

	private String userName;
	private String password;
	private String siteDropdownElementXPath;
	private String pagesTreeLink;
	private String pagesTree;
	private String homeContent;

	private String rightclickEditOption;
	private String rightclickViewOption;
	private String rightclickNewContentOption;
	private String rightclickNewFolderOption;
	private String rightclickDeleteOption;
	private String rightclickChangeTemplateOption;
	private String rightclickCutOption;
	private String rightclickCopyOption;
	private String rightclickDependenciesOption;
	private String rightclickHistoryOption;
	private String technologyLandingpage;
	private String rightclickDuplicateOption;
	private String rightclickRenameFolderOption;
	private String articlesFolder;
	private String articlesFolder2017;
	private String articlesFolder1;
	private String articlesFolderMenStylesForWinter;
	private LinkedList<String> rightClickOptionsListInHomePage;
	private LinkedList<String> rightClickOptionsListInCategoryLandingPage;
	private LinkedList<String> rightClickOptionsListInMenStylesForWinterPage;
	private LinkedList<String> rightClickOptionsListInArticlesFolder1;
	private String rightClickOptions;
	private static Logger logger = LogManager.getLogger(
			VerifyRightClickOptionsOfAPagesUnderPageStructureUsingAdminUser.class);


	@Parameters({"testId", "blueprint"})
	@BeforeMethod
	public void beforeTest(String testId, String blueprint) {
		apiTestHelper.createSite(testId, "", blueprint);
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdownmenuinnerxpath");
		pagesTreeLink = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sitecontent.expandpages");
		pagesTree = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.expand_Pages_Tree");
		homeContent = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.home_Content_Page");

		rightclickEditOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.edit.option");
		rightclickViewOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.view.option");
		rightclickNewContentOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.new.Content.option");
		rightclickNewFolderOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.new.folder.option");
		rightclickDeleteOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.delete.option");
		rightclickChangeTemplateOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.change.template.option");
		rightclickCutOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.cut.option");
		rightclickCopyOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.copy.option");
		rightclickDependenciesOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.dependenciesoption");
		rightclickHistoryOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.history.option");
		technologyLandingpage = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.pagestree.technology.landingpage");
		rightclickDuplicateOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.duplicate.option");
		rightclickRenameFolderOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.rename.folder.option");
		articlesFolder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.articlesfolder");
		articlesFolder2017 = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.articles.folder.2017");
		articlesFolder1 = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.articles.folder.2017.1");
		articlesFolderMenStylesForWinter = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.articles.folder.2017.1.menstylesforwinter");
		rightClickOptions = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.list.all.options");
	}

	public void rightClickHome() {
		this.getWebDriverManager().waitUntilPageLoad();
		this.getWebDriverManager().waitUntilSidebarOpens();

		this.getWebDriverManager().waitUntilFolderOpens("xpath", pagesTree);
		this.getWebDriverManager().waitForAnimation();

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", homeContent);
		dashboardPage.getDriverManager().contextClick("xpath", homeContent, false);

	}

	public void rightClickCategoryLandingPage() {
		this.getWebDriverManager().waitUntilPageLoad();
		this.getWebDriverManager().waitUntilSidebarOpens();
		this.getWebDriverManager().waitForAnimation();

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", technologyLandingpage);
		dashboardPage.getDriverManager().contextClick("xpath", technologyLandingpage, false);
		this.getWebDriverManager().waitForAnimation();
	}

	public void rightClickArticlesFolder1() {
		this.getWebDriverManager().waitUntilPageLoad();
		this.getWebDriverManager().waitUntilSidebarOpens();
		this.getWebDriverManager().waitForAnimation();

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articlesFolder1);
		dashboardPage.getDriverManager().contextClick("xpath", articlesFolder1, false);
		this.getWebDriverManager().waitForAnimation();
	}

	public void rightClickArticlesFolderMenStylesForWinter() {
		this.getWebDriverManager().waitUntilPageLoad();
		this.getWebDriverManager().waitUntilSidebarOpens();
		this.getWebDriverManager().waitForAnimation();

		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				articlesFolderMenStylesForWinter);
		dashboardPage.getDriverManager().contextClick("xpath", articlesFolderMenStylesForWinter, false);
		this.getWebDriverManager().waitForAnimation();
	}

	public void verifyEditOptionIsPresent(String section) {
		WebElement rightclickEditOptionElement = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
				rightclickEditOption);
		Assert.assertTrue(rightclickEditOptionElement.isDisplayed(),
				"ERROR: Right click Edit Option is not present on right click of " + section);
	}

	public void verifyViewOptionIsPresent(String section) {
		WebElement rightclickViewOptionElement = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
				rightclickViewOption);
		Assert.assertTrue(rightclickViewOptionElement.isDisplayed(),
				"ERROR: Right click View Option is not present on right click of " + section);
	}

	public void verifyNewContentOptionIsPresent(String section) {
		WebElement rightclickNewContentOptionElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", rightclickNewContentOption);
		Assert.assertTrue(rightclickNewContentOptionElement.isDisplayed(),
				"ERROR: Right click New Content Option is not present on right click of " + section);
	}

	public void verifyNewFolderOptionIsPresent(String section) {
		WebElement rightclickNewFolderOptionElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", rightclickNewFolderOption);
		Assert.assertTrue(rightclickNewFolderOptionElement.isDisplayed(),
				"ERROR: Right click New Folder Option is not present on right click of " + section);
	}

	public void verifyDeleteOptionIsPresent(String section) {
		WebElement rightclickDeleteOptionElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", rightclickDeleteOption);
		Assert.assertTrue(rightclickDeleteOptionElement.isDisplayed(),
				"ERROR: Right click Delete Option is not present on right click of " + section);
	}

	public void verifyChangeTemplateOptionIsPresent(String section) {
		WebElement rightclickChangeTemplateOptionElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", rightclickChangeTemplateOption);
		Assert.assertTrue(rightclickChangeTemplateOptionElement.isDisplayed(),
				"ERROR: Right click Change Template Option is not present on right click of " + section);
	}

	public void verifyCutOptionIsPresent(String section) {
		WebElement rightclickCutOptionElement = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
				rightclickCutOption);
		Assert.assertTrue(rightclickCutOptionElement.isDisplayed(),
				"ERROR: Right click Cut Option is not present on right click of " + section);
	}

	public void verifyCopyOptionIsPresent(String section) {
		WebElement rightclickCopyOptionElement = this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayed("xpath",
				rightclickCopyOption);
		Assert.assertTrue(rightclickCopyOptionElement.isDisplayed(),
				"ERROR: Right click Copy Option is not present on right click of " + section);
	}

	public void verifyDependenciesOptionIsPresent(String section) {
		WebElement rightclickDependenciesOptionElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", rightclickDependenciesOption);
		Assert.assertTrue(rightclickDependenciesOptionElement.isDisplayed(),
				"ERROR: Right click Dependencies Option is not present on right click of " + section);
	}

	public void verifyHistoryOptionIsPresent(String section) {
		WebElement rightclickHistoryOptionElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", rightclickHistoryOption);
		Assert.assertTrue(rightclickHistoryOptionElement.isDisplayed(),
				"ERROR: Right click History Option is not present on right click of " + section);
	}

	public void verifyDuplicateOptionIsPresent(String section) {
		WebElement rightclickDuplicateOptionElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", rightclickDuplicateOption);
		Assert.assertTrue(rightclickDuplicateOptionElement.isDisplayed(),
				"ERROR: Right click Duplicate Option is not present on right click of " + section);
	}

	public void verifyRenameFolderOptionIsPresent(String section) {
		WebElement rightclickRenameFolderOptionElement = this.getWebDriverManager()
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", rightclickRenameFolderOption);
		Assert.assertTrue(rightclickRenameFolderOptionElement.isDisplayed(),
				"ERROR: Right click Rename Folder Option is not present on right click of " + section);
	}

	public void step4() {
		logger.info("Step 4 Right Right click on Home and verify options");
		// Step 4 Right Right click on "Home" and verify options
		this.rightClickHome();

		getWebDriverManager().usingContextMenu(() -> {
			
			logger.info("Checking that only the expected options are listed");
			rightClickOptionsListInHomePage = new LinkedList<String>();
			rightClickOptionsListInHomePage.add(0, "Edit");
			rightClickOptionsListInHomePage.add(1, "View");
			rightClickOptionsListInHomePage.add(2, "New Content");
			rightClickOptionsListInHomePage.add(3, "New Folder");
			rightClickOptionsListInHomePage.add(4, "Delete");
			rightClickOptionsListInHomePage.add(5, "Change Template");
			rightClickOptionsListInHomePage.add(6, "Cut");
			rightClickOptionsListInHomePage.add(7, "Copy");
			rightClickOptionsListInHomePage.add(8, "Dependencies");
			rightClickOptionsListInHomePage.add(9, "History");

			List<WebElement> rightClickOptionsList = this.getWebDriverManager().getDriver()
					.findElements(By.xpath(rightClickOptions));
			int currentIndex = 0;
			for (WebElement element : rightClickOptionsList) {
				this.getWebDriverManager().waitForAnimation();
				this.getWebDriverManager().waitUntilSidebarOpens();
				Assert.assertTrue(element.getText().equals(rightClickOptionsListInHomePage.get(currentIndex)),
						"ERROR: Link Option: " + element.getText()
								+ " is not in the correct order in the HomePage, check that the correct options are listed");
				currentIndex++;
			}

			String section = "Step 4 Right Click on 'Home'";

			verifyEditOptionIsPresent(section);
			verifyViewOptionIsPresent(section);
			verifyNewContentOptionIsPresent(section);
			verifyNewFolderOptionIsPresent(section);
			verifyDeleteOptionIsPresent(section);
			verifyChangeTemplateOptionIsPresent(section);
			verifyCutOptionIsPresent(section);
			verifyCopyOptionIsPresent(section);
			verifyDependenciesOptionIsPresent(section);
			verifyHistoryOptionIsPresent(section);

			this.getWebDriverManager().getDriver().navigate().refresh();
			this.getWebDriverManager().waitForAnimation();
		},"Pages");
	}

	public void step6() {
		logger.info("Step 6 Right click on any Category Landing page and verify options");
		// Step 6 Right click on any Category Landing page and verify options
		this.rightClickCategoryLandingPage();

		getWebDriverManager().usingContextMenu(() -> {
			
			logger.info("Checking that only the expected options are listed");
			rightClickOptionsListInCategoryLandingPage = new LinkedList<String>();
			rightClickOptionsListInCategoryLandingPage.add(0, "Edit");
			rightClickOptionsListInCategoryLandingPage.add(1, "View");
			rightClickOptionsListInCategoryLandingPage.add(2, "New Content");
			rightClickOptionsListInCategoryLandingPage.add(3, "New Folder");
			rightClickOptionsListInCategoryLandingPage.add(4, "Delete");
			rightClickOptionsListInCategoryLandingPage.add(5, "Change Template");
			rightClickOptionsListInCategoryLandingPage.add(6, "Cut");
			rightClickOptionsListInCategoryLandingPage.add(7, "Copy");
			rightClickOptionsListInCategoryLandingPage.add(8, "Duplicate");
			rightClickOptionsListInCategoryLandingPage.add(9, "Dependencies");
			rightClickOptionsListInCategoryLandingPage.add(10, "History");

			List<WebElement> rightClickOptionsList = this.getWebDriverManager().getDriver()
					.findElements(By.xpath(rightClickOptions));
			int currentIndex = 0;
			for (WebElement element : rightClickOptionsList) {
				this.getWebDriverManager().waitForAnimation();
				this.getWebDriverManager().waitUntilSidebarOpens();
				Assert.assertTrue(
						element.getText().equals(rightClickOptionsListInCategoryLandingPage.get(currentIndex)),
						"ERROR: Link Option: " + element.getText()
								+ " is not in the correct order in the selected category landing page, check that the correct options are listed");
				currentIndex++;
			}

			String section = "Step 6 Right click on a 'Category Landing' page";

			verifyEditOptionIsPresent(section);
			verifyViewOptionIsPresent(section);
			verifyNewContentOptionIsPresent(section);
			verifyNewFolderOptionIsPresent(section);
			verifyDeleteOptionIsPresent(section);
			verifyChangeTemplateOptionIsPresent(section);
			verifyCutOptionIsPresent(section);
			verifyCopyOptionIsPresent(section);
			verifyDuplicateOptionIsPresent(section);
			verifyDependenciesOptionIsPresent(section);
			verifyHistoryOptionIsPresent(section);

			this.getWebDriverManager().getDriver().navigate().refresh();
			this.getWebDriverManager().waitForAnimation();
		},"Pages");
	}

	public void step8() {
		logger.info("Step 8 Click on the + of folder 2017");
		// Step 8 Click on the + of folder 2017
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articlesFolder2017);

		this.getWebDriverManager().waitUntilContentTooltipIsHidden();

		this.getWebDriverManager().clickElement("xpath", articlesFolder2017);
		this.getWebDriverManager().waitUntilFolderOpens("xpath", articlesFolder2017);
	}

	public void step9() {
		// Step 9 Right click on folder 1 and verify options
		logger.info("Step 9 Right click on folder 1 and verify options");
		this.rightClickArticlesFolder1();

		getWebDriverManager().usingContextMenu(() -> {
			
			logger.info("Checking that only the expected options are listed");
			rightClickOptionsListInArticlesFolder1 = new LinkedList<String>();
			rightClickOptionsListInArticlesFolder1.add(0, "New Content");
			rightClickOptionsListInArticlesFolder1.add(1, "New Folder");
			rightClickOptionsListInArticlesFolder1.add(2, "Rename Folder");
			rightClickOptionsListInArticlesFolder1.add(3, "Delete");
			rightClickOptionsListInArticlesFolder1.add(4, "Cut");
			rightClickOptionsListInArticlesFolder1.add(5, "Copy");

			List<WebElement> rightClickOptionsList = this.getWebDriverManager().getDriver()
					.findElements(By.xpath(rightClickOptions));
			int currentIndex = 0;
			for (WebElement element : rightClickOptionsList) {
				this.getWebDriverManager().waitForAnimation();
				this.getWebDriverManager().waitUntilSidebarOpens();
				Assert.assertTrue(
						element.getText().equals(rightClickOptionsListInArticlesFolder1.get(currentIndex)),
						"ERROR: Link Option: " + element.getText()
								+ " is not in the correct order in the articles folder 1, check that the correct options are listed");
				currentIndex++;
			}

			String section = "Step 9 Right click on folder articles -2017- 1;";

			verifyNewContentOptionIsPresent(section);
			verifyNewFolderOptionIsPresent(section);
			verifyRenameFolderOptionIsPresent(section);
			verifyDeleteOptionIsPresent(section);
			verifyCutOptionIsPresent(section);
			verifyCopyOptionIsPresent(section);

			this.getWebDriverManager().getDriver().navigate().refresh();
			this.getWebDriverManager().waitForAnimation();
		},"Pages");
	}
	public void step11() {
		// Step 11 Right click on any of the article (Men Styles For Winter)
		logger.info("Step 11 Right click on any of the article (Men Styles For Winter)");
		this.getWebDriverManager().waitForAnimation();

		this.rightClickArticlesFolderMenStylesForWinter();

		getWebDriverManager().usingContextMenu(() -> {
			
			logger.info("Checking that only the expected options are listed");
			rightClickOptionsListInMenStylesForWinterPage = new LinkedList<String>();
			rightClickOptionsListInMenStylesForWinterPage.add(0, "Edit");
			rightClickOptionsListInMenStylesForWinterPage.add(1, "View");
			rightClickOptionsListInMenStylesForWinterPage.add(2, "New Content");
			rightClickOptionsListInMenStylesForWinterPage.add(3, "New Folder");
			rightClickOptionsListInMenStylesForWinterPage.add(4, "Delete");
			rightClickOptionsListInMenStylesForWinterPage.add(5, "Change Template");
			rightClickOptionsListInMenStylesForWinterPage.add(6, "Cut");
			rightClickOptionsListInMenStylesForWinterPage.add(7, "Copy");
			rightClickOptionsListInMenStylesForWinterPage.add(8, "Duplicate");
			rightClickOptionsListInMenStylesForWinterPage.add(9, "Dependencies");
			rightClickOptionsListInMenStylesForWinterPage.add(10, "History");

			List<WebElement> rightClickOptionsList = this.getWebDriverManager().getDriver()
					.findElements(By.xpath(rightClickOptions));
			int currentIndex = 0;
			for (WebElement element : rightClickOptionsList) {
				this.getWebDriverManager().waitForAnimation();
				this.getWebDriverManager().waitUntilSidebarOpens();
				Assert.assertTrue(
						element.getText().equals(rightClickOptionsListInMenStylesForWinterPage.get(currentIndex)),
						"ERROR: Link Option: " + element.getText()
								+ " is not in the correct order in the Men Styles For Witner page, check that the correct options are listed");
				currentIndex++;
			}

			String section = "Step 11 Right click on folder articles -2017- 1- Men Styles For Winter;";

			verifyEditOptionIsPresent(section);
			verifyViewOptionIsPresent(section);
			verifyNewContentOptionIsPresent(section);
			verifyNewFolderOptionIsPresent(section);
			verifyDeleteOptionIsPresent(section);
			verifyChangeTemplateOptionIsPresent(section);
			verifyCutOptionIsPresent(section);
			verifyCopyOptionIsPresent(section);
			verifyDuplicateOptionIsPresent(section);
			verifyDependenciesOptionIsPresent(section);
			verifyHistoryOptionIsPresent(section);

			this.getWebDriverManager().getDriver().navigate().refresh();
			this.getWebDriverManager().waitForAnimation();
		},"Pages");

	}

	@Parameters({"testId"})
	@Test()
	public void verifyRightClickOptionsOfAPagesUnderPageStructureUsingAdminUser(String testId) {
		loginPage.loginToCrafter(userName, password);

		homePage.goToPreviewPage(testId);

		// Expand the site bar Step 2
		logger.info("Step 2 Expand the site bar ");
		this.getWebDriverManager().clickElement("xpath", siteDropdownElementXPath);

		this.getWebDriverManager().waitUntilFolderOpens("xpath", pagesTreeLink);

		// Step 4 Right Right click on "Home" and verify options
		this.step4();

		// Step 5 Click on the + of Home tree
		this.dashboardPage.expandHomeTree();

		// Step 6 Right click on any Category Landing page and verify options
		this.step6();

		// Step 7 Click on the + of Articles tree
		logger.info("Step 7 Click on the + of Articles tree");
		this.getWebDriverManager().driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articlesFolder);

		this.getWebDriverManager().waitUntilContentTooltipIsHidden();

		this.getWebDriverManager().clickElement("xpath", articlesFolder);
		this.getWebDriverManager().waitUntilFolderOpens("xpath", articlesFolder);

		// Step 8 Click on the + of folder 2017
		this.step8();

		// Step 9 Right click on folder 1 and verify options
		this.step9();

		// Step 10 Click on the + of folder "1"
		logger.info("Step 10 Click on the + of folder 1");
		this.getWebDriverManager().clickElement("xpath", articlesFolder1);
		this.getWebDriverManager().waitUntilFolderOpens("xpath", articlesFolder1);

		// Step 11 Right click on any of the article (Men Styles For Winter)
		this.step11();
	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
