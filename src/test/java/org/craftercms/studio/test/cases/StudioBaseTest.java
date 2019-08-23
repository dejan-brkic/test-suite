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

package org.craftercms.studio.test.cases;

import org.craftercms.studio.test.pages.AccountManagementPage;
import org.craftercms.studio.test.pages.CreateSitePage;
import org.craftercms.studio.test.pages.DashboardPage;
import org.craftercms.studio.test.pages.HomePage;
import org.craftercms.studio.test.pages.LoginPage;
import org.craftercms.studio.test.pages.MyRecentActivityPage;
import org.craftercms.studio.test.pages.PreviewPage;
import org.craftercms.studio.test.pages.SiteConfigPage;
import org.craftercms.studio.test.pages.UsersPage;
import org.craftercms.studio.test.utils.*;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * All Test Cases should extend this class
 */
public class StudioBaseTest {

	private ThreadLocal<WebDriverManager>  driverManager = new ThreadLocal<WebDriverManager>();
	protected UIElementsPropertiesManager uiElementsPropertiesManager;
	protected ConstantsPropertiesManager constantsPropertiesManager;
	protected ConstantsPropertiesManager deliveryExecutionValuesManager;
	
	protected LoginPage loginPage;
	protected HomePage homePage;
	protected DashboardPage dashboardPage;
	protected PreviewPage previewPage;
	protected CreateSitePage createSitePage;
	protected AccountManagementPage accountManagementPage;
	protected SiteConfigPage siteConfigPage;
	protected MyRecentActivityPage myRecentActivityFramePage1;
	protected UsersPage usersPage;
	protected APITestHelper apiTestHelper;
	protected String testName;

	@BeforeClass
	public void setUp(ITestContext ctx) {
		driverManager.set(new WebDriverManager());
		testName = ctx.getCurrentXmlTest().getName();
		getWebDriverManager().setTestName(testName);
		uiElementsPropertiesManager = new UIElementsPropertiesManager(
				FilesLocations.UIELEMENTSPROPERTIESFILEPATH);
		constantsPropertiesManager = new ConstantsPropertiesManager(
				FilesLocations.CONSTANTSPROPERTIESFILEPATH);
		deliveryExecutionValuesManager = new ConstantsPropertiesManager(
				FilesLocations.DELIVERYEXECUTIONVALUESPROPERTIESFILEPATH);
		getWebDriverManager().setConstantsPropertiesManager(constantsPropertiesManager);
		getWebDriverManager().setUIElementsPropertiesManager(uiElementsPropertiesManager);

		loginPage = new LoginPage(getWebDriverManager(), uiElementsPropertiesManager);
		homePage = new HomePage(getWebDriverManager(), uiElementsPropertiesManager);
		previewPage = new PreviewPage(getWebDriverManager(), uiElementsPropertiesManager);
		dashboardPage = new DashboardPage(getWebDriverManager(), uiElementsPropertiesManager);
		createSitePage = new CreateSitePage(getWebDriverManager(), uiElementsPropertiesManager);
		accountManagementPage = new AccountManagementPage(getWebDriverManager(), uiElementsPropertiesManager);
		siteConfigPage = new SiteConfigPage(getWebDriverManager(), uiElementsPropertiesManager);
		myRecentActivityFramePage1 = new MyRecentActivityPage(getWebDriverManager(), uiElementsPropertiesManager);
		usersPage = new UsersPage(getWebDriverManager(), uiElementsPropertiesManager);
		apiTestHelper = new APITestHelper();
	}

	@AfterClass
	public void close() {
		getWebDriverManager().closeConnection();
	}

	public WebDriverManager getWebDriverManager(){
		return driverManager.get();
	}
}
