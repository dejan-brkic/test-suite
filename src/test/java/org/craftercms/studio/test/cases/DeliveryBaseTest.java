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

import org.craftercms.studio.test.pages.DeliveryHomePage;
import org.craftercms.studio.test.utils.ConstantsPropertiesManager;
import org.craftercms.studio.test.utils.FilesLocations;
import org.craftercms.studio.test.utils.UIElementsPropertiesManager;
import org.craftercms.studio.test.utils.WebDriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * All Test Cases should extend this class
 */
public class DeliveryBaseTest {

	protected WebDriverManager driverManager;
	protected UIElementsPropertiesManager uiElementsPropertiesManager;
	protected ConstantsPropertiesManager constantsPropertiesManager;
	protected ConstantsPropertiesManager deliveryExecutionValuesManager;

	protected DeliveryHomePage deliveryHome;

	@BeforeClass
	public void setUp() {
		driverManager = new WebDriverManager();
		uiElementsPropertiesManager = new UIElementsPropertiesManager(
				FilesLocations.UIELEMENTSPROPERTIESFILEPATH);
		constantsPropertiesManager = new ConstantsPropertiesManager(
				FilesLocations.CONSTANTSPROPERTIESFILEPATH);
		deliveryExecutionValuesManager = new ConstantsPropertiesManager(
				FilesLocations.DELIVERYEXECUTIONVALUESPROPERTIESFILEPATH);

		driverManager.setConstantsPropertiesManager(constantsPropertiesManager);
		driverManager.setUIElementsPropertiesManager(uiElementsPropertiesManager);
		
		String currentSiteId = deliveryExecutionValuesManager.getSharedExecutionConstants()
				.getProperty("general.currentsiteid");
		deliveryHome = new DeliveryHomePage(driverManager, currentSiteId);
	}

	@AfterClass
	public void close() {
		driverManager.closeConnection();
	}

}
