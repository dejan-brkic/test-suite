package org.craftercms.studio.test.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.utils.UIElementsPropertiesManager;
import org.craftercms.studio.test.utils.WebDriverManager;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author Luis Hernandez
 *
 */

public class DeliveryHomePage {

	private WebDriverManager driverManager;
	private WebDriver driver;
	private static Logger logger = LogManager.getLogger(DeliveryHomePage.class);

	public DeliveryHomePage(WebDriverManager driverManager, UIElementsPropertiesManager UIElementsPropertiesManager,String siteId) {
		this.driverManager = driverManager;
		logger.info("Go to delivery site");
		this.driverManager.openConnectionAndGotoDelivery(siteId);
		this.driver = this.driverManager.getDriver();
	}

	public DeliveryHomePage(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriverManager getDriverManager() {
		return driverManager;
	}

	public void setDriverManager(WebDriverManager driverManager) {
		this.driverManager = driverManager;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
}