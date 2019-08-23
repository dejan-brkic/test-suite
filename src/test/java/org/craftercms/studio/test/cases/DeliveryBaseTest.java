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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.cases.contenttestcases.CopyPasteLargeTreesTest;
import org.craftercms.studio.test.pages.DeliveryHomePage;
import org.craftercms.studio.test.utils.*;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

/**
 * All Test Cases should extend this class
 */
public class DeliveryBaseTest {

	private ThreadLocal<WebDriverManager>  driverManager = new ThreadLocal<WebDriverManager>();
	protected UIElementsPropertiesManager uiElementsPropertiesManager;
	protected ConstantsPropertiesManager constantsPropertiesManager;
	protected ConstantsPropertiesManager deliveryExecutionValuesManager;
	protected APITestHelper apiTestHelper;
	protected DeliveryHomePage deliveryHome;
	protected static Logger logger = LogManager.getLogger(CopyPasteLargeTreesTest.class);
	protected String testName;


	@Parameters({"testId"})
	@BeforeClass
	public void setUp(String testId, ITestContext ctx) {
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
		deliveryHome = new DeliveryHomePage(getWebDriverManager(), testId);
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
