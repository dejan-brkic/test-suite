/**
 * 
 */
package org.craftercms.studio.test.cases.sanitytesttestcases;

import org.craftercms.studio.test.cases.StudioBaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author luishernandez
 *
 */
public class VerifyNoErrorsInHomePageOfSiteTest extends StudioBaseTest{

//	private String userName;
//	private String password;
//	private int defaultTimeOut;

	@BeforeMethod
	public void beforeTest() {
		//this.driverManager = new WebDriverManager();

//		UIElementsPropertiesManager UIElementsPropertiesManager = new UIElementsPropertiesManager(
//				FilesLocations.UIELEMENTSPROPERTIESFILEPATH);
//		ConstantsPropertiesManager constantsPropertiesManager = new ConstantsPropertiesManager(FilesLocations.CONSTANTSPROPERTIESFILEPATH);
		
//		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
//		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
//		defaultTimeOut = Integer.parseInt(
//				constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.defaulttimeout"));

	}

	@Test(priority = 0)
	public void verifyNoErrorsInHomePageOfSiteTest() {
	}

}
