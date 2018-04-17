package org.craftercms.delivery.test.cases.verification;

import org.craftercms.studio.test.cases.DeliveryBaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author Luis Hernandez
 *
 */
// Test Case Studio- Site Content ID:40
public class VerifyThatRenamedAndPublishedArticleIsOnLive extends DeliveryBaseTest {
	private String pageTitleXpath;

	@BeforeMethod
	public void beforeTest() {
		pageTitleXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("delivery.verification.pagetitle");
	}

	@Test(priority = 0)
	public void verifyThatTheRenamedAndPublishedArticleIsOnLiveTest() {
		//click on entertainment and check the article 
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",".//a[text()='Entertainment']").click();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",".//a[text()='foo']").click();
		
		this.driverManager.waitUntilElementIsDisplayed("xpath", pageTitleXpath);
		Assert.assertTrue(this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", pageTitleXpath)
				.getText().equalsIgnoreCase("foo"));
		Assert.assertTrue(this.driverManager.getDriver().getCurrentUrl()
				.equalsIgnoreCase("http://localhost:9080/articles/2016/12/bar.html"));
	}

}
