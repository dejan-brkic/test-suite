/*
 * Copyright (C) 2007-2018 Crafter Software Corporation. All Rights Reserved.
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

// Test Case Studio- Site Content ID:5
public class DependenciesCalculationItemRefersToTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String siteDropdownElementXPath;
	private String siteDropdownListElementXPath;
	private String generalEditOption;
	private String dependenciesMenuOption;
	private String styleLocator;
	private String articlesFolder;
	private String folder2016Locator;
	private String selectAllSegmentsCheckBox;
	private String selectAllCategoriesCheckBox;
	private String folder2017Locator;
	private String dependeciesOption;
	private String searchResultsPageXpath;
	private String testpageXpath;
	private String componentsTreeXpath;
	private String componentSubTreeXpath;
	private String articlesWidgetFolder;
	private String latestArticlesWidget;
	private String headerFolderXpath;
	private String headerXpath;
	private String featuresFolderXpath;
	private String fourXpath;
	private String leftrailsfolder;
	private String wommenStylesArticlePage;
	private String leftrailwithlastestarticles;
	private String staticAssetsTreeXpath;
	private String staticAssetsSubTreeXpath;
	private String staticAssetsimagesFolder;
	private String bookWomanImage;
	private String templatesTreeXpath;
	private String templatesSubTreeXpath;
	private String templatesWebFolder;
	private String templatesPagesFolder;
	private String articleFTLXpath;
	private String categoryLandingFTLXpath;
	private String homeFTLXpath;
	private String searchResultsFTLXpath;
	private String scriptsTreeXpath;
	private String scriptsSubTreeXpath;
	private String scriptsPagesFolderXpath;
	private String categoryLandingGroovy;
	private String homeGroovy;
	private String searchResultsGroovy;
	private String siteMapGroovy;
	private String dashboardLink;
	private String staticAssetsCssFolder;
	private String staticAssetsJSFolder;
	private String fontAwesomeMinCss;
	private String ie8Css;
	private String jqueryjs;
	private static Logger logger = LogManager.getLogger(DependenciesCalculationItemRefersToTest.class);
	@BeforeMethod
	public void beforeTest() {

		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		initLocatorsFirstPart();
		initLocatorsSecondPart();

	}

	public void initLocatorsFirstPart() {
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		siteDropdownListElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdownlielement");
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
		searchResultsPageXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.page.searchresults");
		testpageXpath = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("general.page.testpage");
		componentsTreeXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.components.componentstree");
		componentSubTreeXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.components.componentssubtree");
		articlesWidgetFolder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.components.articleswidgetfolder");
		latestArticlesWidget = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.components.latestarticleswidget");
		headerFolderXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.components.headersfolder");
		headerXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.components.header");
		featuresFolderXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.components.featuresfolder");
		fourXpath = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("general.components.four");
		leftrailsfolder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.components.leftrailsfolder");
		wommenStylesArticlePage = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.articles.wommenstyles");
		leftrailwithlastestarticles = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.components.leftrailwithlatestarticles");
	}

	public void initLocatorsSecondPart() {
		staticAssetsTreeXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.staticassets.staticassetstree");
		staticAssetsSubTreeXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.staticassets.staticassetssubtree");
		staticAssetsimagesFolder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.staticassets.imagesfolder");
		bookWomanImage = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.staticassets.bookwomanimage");
		templatesTreeXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.templates.templatestree");
		templatesSubTreeXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.templates.templatessubtree");
		templatesWebFolder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.templates.webfolder");
		templatesPagesFolder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.templates.pagesfolder");
		articleFTLXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.templates.articleftl");
		categoryLandingFTLXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.templates.categorylandingftl");
		homeFTLXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.templates.homeftl");
		searchResultsFTLXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.templates.searchresultsftl");
		scriptsTreeXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.scripts.scriptstree");
		scriptsSubTreeXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.scripts.scriptssubtree");
		scriptsPagesFolderXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.scripts.pagesfolder");
		categoryLandingGroovy = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.scripts.categorylandinggroovy");
		homeGroovy = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.scripts.homegroovy");
		searchResultsGroovy = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.scripts.searchresultsgroovy");
		siteMapGroovy = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.scripts.sitemapgroovy");
		dashboardLink = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.dashboard.dashboardlink");
		staticAssetsCssFolder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.staticassets.cssfolder");
		staticAssetsJSFolder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.staticassets.jsfolder");
		fontAwesomeMinCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.staticassets.fontawesomemincss");
		ie8Css = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("general.staticassets.ie8css");
		jqueryjs = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.staticassets.jqueryjs");
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
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dependenciesMenuOption);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dependenciesMenuOption)
				.click();

		// check dependencies are listed
		logger.info("Check Listed Dependencies");
		previewPage.checkDependenciesForPageItem("Home", false);
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
		previewPage.checkDependenciesForPageItem("Style",false);
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

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", wommenStylesArticlePage);
		this.driverManager.waitUntilContentTooltipIsHidden();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", wommenStylesArticlePage)
				.click();

		logger.info("Open dependencies for the previous created element");
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", generalEditOption);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dependenciesMenuOption);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dependenciesMenuOption)
				.click();

		// check dependencies are listed
		logger.info("Check Listed Dependencies");
		previewPage.checkDependenciesForPageItem("Women Styles for Winter",false);
	}

	public void step4() {
		this.driverManager.waitForAnimation();
		dashboardPage.expandParentFolder(folder2016Locator);

		this.driverManager.waitForAnimation();
		dashboardPage
				.expandParentFolder(folder2016Locator + "/../../../../../div[@class='ygtvchildren']//span[text()='6']");

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", testpageXpath);
		this.driverManager.waitUntilContentTooltipIsHidden();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", testpageXpath).click();

		logger.info("Open dependencies for the previous created element");
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", generalEditOption);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dependenciesMenuOption);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dependenciesMenuOption)
				.click();

		// check dependencies are listed
		logger.info("Check Listed Dependencies");
		previewPage.checkDependenciesForPageItem("Testing1",false);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articlesFolder);
		dashboardPage.collapseParentFolder(articlesFolder);
	}

	public void step5() {

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", searchResultsPageXpath);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", searchResultsPageXpath)
				.click();

		logger.info("Open dependencies for the previous created element");
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", generalEditOption);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dependenciesMenuOption);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dependenciesMenuOption)
				.click();

		// check dependencies are listed
		logger.info("Check Listed Dependencies");
		previewPage.checkDependenciesForPageItem("Search Results",false);
	}

	public void step6() {
		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", componentsTreeXpath);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", componentsTreeXpath)
				.click();

		this.driverManager.waitForAnimation();
		dashboardPage.expandParentFolder(componentSubTreeXpath);

		this.driverManager.waitForAnimation();
		dashboardPage.expandParentFolder(articlesWidgetFolder);

		this.driverManager.waitForAnimation();
		this.driverManager.scrollDownIntoSideBar();
		this.rightClickAndClickOnDependencies(latestArticlesWidget, "Components");

		// check dependencies are listed
		logger.info("Check Listed Dependencies");
		previewPage.checkDependenciesForComponentItem("Latest Articles Widget",false);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articlesWidgetFolder);
		dashboardPage.collapseParentFolder(articlesWidgetFolder);
	}

	public void step7() {

		this.driverManager.waitForAnimation();
		dashboardPage.expandParentFolder(headerFolderXpath);

		this.driverManager.waitForAnimation();
		this.driverManager.scrollDownIntoSideBar();
		this.rightClickAndClickOnDependencies(headerXpath, "Components");

		// check dependencies are listed
		logger.info("Check Listed Dependencies");
		previewPage.checkDependenciesForComponentItem("Header",false);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", headerFolderXpath);
		dashboardPage.collapseParentFolder(headerFolderXpath);
	}

	public void step8() {
		this.driverManager.waitForAnimation();
		dashboardPage.expandParentFolder(featuresFolderXpath);

		this.driverManager.waitForAnimation();
		this.driverManager.scrollDownIntoSideBar();
		this.rightClickAndClickOnDependencies(fourXpath, "Components");

		// check dependencies are listed
		logger.info("Check Listed Dependencies");
		previewPage.checkDependenciesForComponentItem("Four",false);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", featuresFolderXpath);
		dashboardPage.collapseParentFolder(featuresFolderXpath);
	}

	public void step9() {
		this.driverManager.waitForAnimation();
		dashboardPage.expandParentFolder(leftrailsfolder);

		this.driverManager.waitForAnimation();
		this.driverManager.scrollDownIntoSideBar();
		this.rightClickAndClickOnDependencies(leftrailwithlastestarticles, "Components");

		// check dependencies are listed
		logger.info("Check Listed Dependencies");
		previewPage.checkDependenciesForComponentItem("Left Rail with Latest Articles",false);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", leftrailsfolder);
		dashboardPage.collapseParentFolder(leftrailsfolder);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", componentSubTreeXpath);
		dashboardPage.collapseParentFolder(componentSubTreeXpath);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", componentsTreeXpath);
		dashboardPage.collapseParentFolder(componentsTreeXpath);
	}

	public void step10() {
		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", staticAssetsTreeXpath);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", staticAssetsTreeXpath)
				.click();

		this.driverManager.waitForAnimation();
		dashboardPage.expandParentFolder(staticAssetsSubTreeXpath);

		this.driverManager.waitForAnimation();
		dashboardPage.expandParentFolder(staticAssetsimagesFolder);

		this.driverManager.waitForAnimation();
		this.driverManager.scrollDownIntoSideBar();
		this.rightClickAndClickOnDependencies(bookWomanImage, "Static Assets");

		// check dependencies are listed
		logger.info("Check Listed Dependencies");
		previewPage.checkNoDependenciesForItem("book-woman-pic.jpg",false);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", staticAssetsimagesFolder);
		dashboardPage.collapseParentFolder(staticAssetsimagesFolder);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", staticAssetsSubTreeXpath);
		dashboardPage.collapseParentFolder(staticAssetsSubTreeXpath);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", staticAssetsTreeXpath);
		dashboardPage.collapseParentFolder(staticAssetsTreeXpath);

	}

	public void step11() {
		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", templatesTreeXpath);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", templatesTreeXpath).click();

		this.driverManager.waitForAnimation();
		dashboardPage.expandParentFolder(templatesSubTreeXpath);

		this.driverManager.waitForAnimation();
		dashboardPage.expandParentFolder(templatesWebFolder);

		this.driverManager.waitForAnimation();
		dashboardPage.expandParentFolder(templatesPagesFolder);

		this.driverManager.waitForAnimation();
		this.driverManager.scrollDownIntoSideBar();
		this.rightClickAndClickOnDependencies(articleFTLXpath, "Templates");

		// check dependencies are listed
		logger.info("Check Listed Dependencies");
		previewPage.checkDependenciesForTemplateItem("article.ftl",false);

	}

	public void step12() {
		this.driverManager.waitForAnimation();
		this.driverManager.scrollDownIntoSideBar();
		this.rightClickAndClickOnDependencies(categoryLandingFTLXpath, "Templates");

		// check dependencies are listed
		logger.info("Check Listed Dependencies");
		previewPage.checkDependenciesForTemplateItem("category-landing.ftl",false);

	}

	public void step13() {
		this.driverManager.waitForAnimation();
		this.driverManager.scrollDownIntoSideBar();
		this.rightClickAndClickOnDependencies(homeFTLXpath, "Templates");

		// check dependencies are listed
		logger.info("Check Listed Dependencies");
		previewPage.checkDependenciesForTemplateItem("home.ftl",false);
	}

	public void step14() {
		this.driverManager.waitForAnimation();
		this.driverManager.scrollDownIntoSideBar();
		this.rightClickAndClickOnDependencies(searchResultsFTLXpath, "Templates");

		// check dependencies are listed
		logger.info("Check Listed Dependencies");
		previewPage.checkDependenciesForTemplateItem("search-results.ftl",false);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", templatesPagesFolder);
		dashboardPage.collapseParentFolder(templatesPagesFolder);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", templatesWebFolder);
		dashboardPage.collapseParentFolder(templatesWebFolder);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", templatesSubTreeXpath);
		dashboardPage.collapseParentFolder(templatesSubTreeXpath);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", templatesTreeXpath);
		dashboardPage.collapseParentFolder(templatesTreeXpath);
	}

	public void step15() {
		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", scriptsTreeXpath);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", scriptsTreeXpath).click();

		this.driverManager.waitForAnimation();
		dashboardPage.expandParentFolder(scriptsSubTreeXpath);

		this.driverManager.waitForAnimation();
		dashboardPage.expandParentFolder(scriptsPagesFolderXpath);

		this.driverManager.waitForAnimation();
		this.driverManager.scrollDownIntoSideBar();
		this.rightClickAndClickOnDependencies(categoryLandingGroovy, "Scripts");

		// check dependencies are listed
		logger.info("Check Listed Dependencies");
		previewPage.checkNoDependenciesForItem("category-landing.groovy",false);
	}

	public void step16() {
		this.driverManager.waitForAnimation();
		this.driverManager.scrollDownIntoSideBar();
		this.rightClickAndClickOnDependencies(homeGroovy, "Scripts");

		// check dependencies are listed
		logger.info("Check Listed Dependencies");
		previewPage.checkNoDependenciesForItem("home.groovy",false);
	}

	public void step17() {
		this.driverManager.waitForAnimation();
		this.driverManager.scrollDownIntoSideBar();
		this.rightClickAndClickOnDependencies(searchResultsGroovy, "Scripts");

		// check dependencies are listed
		logger.info("Check Listed Dependencies");
		previewPage.checkNoDependenciesForItem("search-results.groovy",false);
	}

	public void step18() {
		this.driverManager.waitForAnimation();
		this.driverManager.scrollDownIntoSideBar();
		this.rightClickAndClickOnDependencies(siteMapGroovy, "Scripts");

		// check dependencies are listed
		logger.info("Check Listed Dependencies");
		previewPage.checkNoDependenciesForItem("site-map.groovy",false);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", scriptsPagesFolderXpath);
		dashboardPage.collapseParentFolder(scriptsPagesFolderXpath);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", scriptsSubTreeXpath);
		dashboardPage.collapseParentFolder(scriptsSubTreeXpath);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", scriptsTreeXpath);
		dashboardPage.collapseParentFolder(scriptsTreeXpath);
	}

	public void step19() {
		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dashboardLink);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dashboardLink).click();

		this.driverManager.waitForFullExpansionOfTree();
		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", staticAssetsTreeXpath);
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", staticAssetsTreeXpath)
				.click();

		this.driverManager.waitForAnimation();
		dashboardPage.expandParentFolder(staticAssetsSubTreeXpath);

		this.driverManager.waitForAnimation();
		dashboardPage.expandParentFolder(staticAssetsCssFolder);

		this.driverManager.waitForAnimation();
		this.driverManager.scrollDownIntoSideBar();
		this.rightClickAndClickOnDependencies(fontAwesomeMinCss, "Static Assets");

		// check dependencies are listed
		logger.info("Check Listed Dependencies");
		previewPage.checkNoDependenciesForItem("font-awesome.min.css",false);
	}

	public void step20() {
		this.driverManager.waitForAnimation();
		this.driverManager.scrollDownIntoSideBar();
		this.rightClickAndClickOnDependencies(ie8Css, "Static Assets");

		// check dependencies are listed
		logger.info("Check Listed Dependencies");
		previewPage.checkNoDependenciesForItem("ie8.css",false);

		this.driverManager.waitForAnimation();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", staticAssetsCssFolder);
		dashboardPage.collapseParentFolder(staticAssetsCssFolder);
	}

	public void step21() {
		this.driverManager.waitForAnimation();
		dashboardPage.expandParentFolder(staticAssetsJSFolder);

		this.driverManager.waitForAnimation();
		this.driverManager.scrollDownIntoSideBar();
		this.rightClickAndClickOnDependencies(jqueryjs, "Static Assets");

		// check dependencies are listed
		logger.info("Check Listed Dependencies");
		previewPage.checkNoDependenciesForItem("jquery.min.js",false);
	}

	public void rightClickAndClickOnDependencies(String itemLocator, String menuLocation) {
		this.driverManager.waitForAnimation();
		WebElement element = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", itemLocator);

		this.driverManager.contextClick(this.driverManager.getDriver(), element, false);
		driverManager.usingContextMenu(() -> {
			this.driverManager.waitUntilContentTooltipIsHidden();
			WebElement dependenciesOption = this.driverManager
					.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", dependeciesOption);
			dependenciesOption.click();
		}, menuLocation);

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
	public void verifyDependencyCalculationForWhatAnItemRefersTo() {
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

		step7();

		step8();

		step9();

		step10();

		step11();

		step12();

		step13();

		step14();

		step15();

		step16();

		step17();

		step18();

		step19();

		step20();

		step21();
	}
}
