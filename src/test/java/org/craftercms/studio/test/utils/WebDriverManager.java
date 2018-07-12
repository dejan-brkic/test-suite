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
package org.craftercms.studio.test.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.api.objects.ContentAssetAPI;
import org.craftercms.studio.test.api.objects.SecurityAPI;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Constants;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.TestException;
import java.awt.*;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

public class WebDriverManager {

	private static final Logger logger = LogManager.getLogger(WebDriverManager.class);

	WebDriver driver;
	private ConstantsPropertiesManager constantsPropertiesManager;
	private int defaultTimeOut;
	private int numberOfAttemptsForElementsDisplayed;
	private int amountOfTestFoldersToGenerateForBulkUploadTest;
	private String webBrowserProperty;
	private String executionEnvironment;
	private int bulkUploadSyncTimeOut;
	private int numberOfAttemptsForLogFileUpdate;

	@SuppressWarnings("deprecation")
	public void openConnection() {

		final Properties runtimeProperties = new Properties();
		try {
			runtimeProperties.load(WebDriverManager.class.getResourceAsStream("/runtime.properties"));
			String enviromentPropertiesPath = runtimeProperties.getProperty("crafter.test.location");
			final Properties envProperties = new Properties();
			try {
				envProperties.load(new FileInputStream(enviromentPropertiesPath));
				webBrowserProperty = envProperties.getProperty("webBrowser");
				executionEnvironment = envProperties.getProperty("executionenvironment");
				DesiredCapabilities capabilities;
				switch (webBrowserProperty.toLowerCase()) {
				case "phantomjs":
					capabilities = DesiredCapabilities.phantomjs();
					System.setProperty("phantomjs.binary.path",
							envProperties.getProperty("phantomjs.binary.path"));
					driver = new PhantomJSDriver(capabilities);
					break;
				case "firefox":
					FirefoxOptions firefoxOptions = new FirefoxOptions();
					System.setProperty("webdriver.gecko.driver",
							envProperties.getProperty("firefox.driver.path"));
					driver = new FirefoxDriver(firefoxOptions);
					break;
				case "edge":
					System.setProperty("webdriver.edge.driver",
							envProperties.getProperty("edge.driver.path"));
					EdgeOptions options = new EdgeOptions();
					options.setPageLoadStrategy("eager");
					driver = new EdgeDriver(options);
					break;
				case "ie":
					InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
					System.setProperty("webdriver.ie.driver", envProperties.getProperty("ie.driver.path"));
					driver = new InternetExplorerDriver(internetExplorerOptions);
					break;
				case "chrome":
					ChromeOptions chromeOptions = new ChromeOptions();
					System.setProperty("webdriver.chrome.driver",
							envProperties.getProperty("chrome.driver.path"));
					driver = new ChromeDriver(chromeOptions);
					break;
				default:
					throw new IllegalArgumentException("webBrowser property is needed, valid values are:"
							+ "chrome,edge,ie,firefox,phantomjs");
				}

				driver.get(envProperties.getProperty("baseUrl"));
				this.defaultTimeOut = Integer.parseInt(constantsPropertiesManager
						.getSharedExecutionConstants().getProperty("crafter.defaulttimeout"));
				this.numberOfAttemptsForElementsDisplayed = Integer
						.parseInt(constantsPropertiesManager.getSharedExecutionConstants()
								.getProperty("crafter.numberofattemptsforelementdisplayed"));
				this.amountOfTestFoldersToGenerateForBulkUploadTest = Integer
						.parseInt(constantsPropertiesManager.getSharedExecutionConstants()
								.getProperty("crafter.bulkupload.amountoftestfolderstogenerate"));
				this.bulkUploadSyncTimeOut = Integer.parseInt(constantsPropertiesManager
						.getSharedExecutionConstants().getProperty("crafter.bulkupload.syncprocesstimeout"));
				this.numberOfAttemptsForLogFileUpdate = Integer
						.parseInt(constantsPropertiesManager.getSharedExecutionConstants()
								.getProperty("crafter.numberofattemptsforlogfileupdate"));
				if (!webBrowserProperty.equalsIgnoreCase("firefox")) {
					this.maximizeWindow();
				}

			} catch (IOException ex) {
				throw new FileNotFoundException("Unable to read runtime properties file");
			}
		} catch (IOException ex) {
			throw new TestException("Required Files are not found.");
		}

	}

	@SuppressWarnings("deprecation")
	public void openConnectionAndGotoDelivery(String siteId) {

		final Properties runtimeProperties = new Properties();
		try {
			runtimeProperties.load(WebDriverManager.class.getResourceAsStream("/runtime.properties"));
			String enviromentPropertiesPath = runtimeProperties.getProperty("crafter.test.location");
			final Properties envProperties = new Properties();
			try {
				envProperties.load(new FileInputStream(enviromentPropertiesPath));
				webBrowserProperty = envProperties.getProperty("webBrowser");
				executionEnvironment = envProperties.getProperty("executionenvironment");
				DesiredCapabilities capabilities;
				switch (webBrowserProperty.toLowerCase()) {
				case "phantomjs":
					capabilities = DesiredCapabilities.phantomjs();
					System.setProperty("phantomjs.binary.path",
							envProperties.getProperty("phantomjs.binary.path"));
					driver = new PhantomJSDriver(capabilities);
					break;
				case "firefox":
					FirefoxOptions firefoxOptions = new FirefoxOptions();
					System.setProperty("webdriver.gecko.driver",
							envProperties.getProperty("firefox.driver.path"));
					driver = new FirefoxDriver(firefoxOptions);
					break;
				case "edge":
					System.setProperty("webdriver.edge.driver",
							envProperties.getProperty("edge.driver.path"));
					EdgeOptions options = new EdgeOptions();
					options.setPageLoadStrategy("eager");
					driver = new EdgeDriver(options);
					break;
				case "ie":
					InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
					System.setProperty("webdriver.ie.driver", envProperties.getProperty("ie.driver.path"));
					driver = new InternetExplorerDriver(internetExplorerOptions);
					break;
				case "chrome":
					ChromeOptions chromeOptions = new ChromeOptions();
					System.setProperty("webdriver.chrome.driver",
							envProperties.getProperty("chrome.driver.path"));
					driver = new ChromeDriver(chromeOptions);
					break;
				default:
					throw new IllegalArgumentException("webBrowser property is needed, valid values are:"
							+ "chrome,edge,ie,firefox,phantomjs");
				}

				if (!webBrowserProperty.equalsIgnoreCase("firefox")) {
					this.maximizeWindow();
				}

				this.waitForDeliveryRefresh();
				driver.get((envProperties.getProperty("deliverybaseUrl")) + "?crafterSite=" + siteId);
				this.defaultTimeOut = Integer.parseInt(constantsPropertiesManager
						.getSharedExecutionConstants().getProperty("crafter.defaulttimeout"));
				this.numberOfAttemptsForElementsDisplayed = Integer
						.parseInt(constantsPropertiesManager.getSharedExecutionConstants()
								.getProperty("crafter.numberofattemptsforelementdisplayed"));
				this.amountOfTestFoldersToGenerateForBulkUploadTest = Integer
						.parseInt(constantsPropertiesManager.getSharedExecutionConstants()
								.getProperty("crafter.bulkupload.amountoftestfolderstogenerate"));
				this.numberOfAttemptsForLogFileUpdate = Integer
						.parseInt(constantsPropertiesManager.getSharedExecutionConstants()
								.getProperty("crafter.numberofattemptsforlogfileupdate"));
			} catch (IOException ex) {
				throw new FileNotFoundException("Unable to read runtime properties file");
			}
		} catch (IOException ex) {
			throw new TestException("Required Files are not found.");
		}

	}

	public void closeConnection() {
		this.driver.quit();
	}

	public void maximizeWindow() {
		// Getting the size width and height
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int width = (int) toolkit.getScreenSize().getWidth();
		int height = (int) toolkit.getScreenSize().getHeight();
		// locating webdriver at coordinate 0,0
		this.driver.manage().window().setPosition(new Point(0, 0));
		// maximize the window at normal size
		// //scaling to full screen
		this.driver.manage().window().setSize(new Dimension(width, height));

	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	protected By getSelector(String typeOfSelector, String selectorValue) {
		switch (typeOfSelector.toLowerCase()) {
		case "cssselector":
			return By.cssSelector(selectorValue);
		case "xpath":
			return By.xpath(selectorValue);
		case "id":
			return By.id(selectorValue);
		case "classname":
			return By.className(selectorValue);
		case "tagname":
			return By.tagName(selectorValue);
		case "linktext":
			return By.linkText(selectorValue);
		case "partialLinktext":
			return By.partialLinkText(selectorValue);
		case "name":
			return By.name(selectorValue);
		default:
			throw new IllegalArgumentException("selectortype is needed, valid values are:"
					+ "xpath,cssselector,id,tagname,classname,linktext,partiallinkText,name");
		}
	}

	public WebElement waitUntilElementIsDisplayed(String typeOfSelector, String selectorValue) {
		logger.debug("Waiting for element to be displayed: {}, {}", typeOfSelector, selectorValue);
		By selector = getSelector(typeOfSelector, selectorValue);
		new WebDriverWait(driver, defaultTimeOut)
				.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(selector)));
		return driver.findElement(selector);
	}

	public WebElement waitUntilElementIsPresent(String typeOfSelector, String selectorValue) {
		logger.debug("Waiting for element to be displayed: {}, {}", typeOfSelector, selectorValue);
		By selector = getSelector(typeOfSelector, selectorValue);
		new WebDriverWait(driver, defaultTimeOut)
				.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(selector)));
		return driver.findElement(selector);
	}

	public WebElement waitUntilElementIsClickable(String typeOfSelector, String selectorValue) {
		logger.debug("Waiting for element to be clickable: {}, {}", typeOfSelector, selectorValue);
		By selector = getSelector(typeOfSelector, selectorValue);
		new WebDriverWait(driver, defaultTimeOut)
				.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(selector)));
		try {
			return driver.findElement(selector);
		} catch (NoSuchElementException e) {
			logger.warn("Element has been removed {}, {}", typeOfSelector, selectorValue);
			return waitUntilElementIsDisplayed(typeOfSelector, selectorValue);
		}
	}

	public void waitUntilContentTypeNotificationIsNotDisplayed(String typeOfSelector,
			String selectorValueForChilds, WebElement parentElement) {
		logger.debug("Waiting for element has childs");
		By selector = getSelector(typeOfSelector, selectorValueForChilds);
		int hasChild = parentElement.findElements(selector).size();
		while (hasChild == 1) {
			hasChild = parentElement.findElements(selector).size();
		}
	}

	public void waitUntilElementIsNotDisplayed(String typeOfSelector, String selectorValue) {
		logger.debug("Waiting for element to be hidden: {} , {}", typeOfSelector, selectorValue);
		By selector = getSelector(typeOfSelector, selectorValue);
		new WebDriverWait(driver, defaultTimeOut).until(ExpectedConditions
				.refreshed(ExpectedConditions.invisibilityOf(driver.findElement(selector))));
	}

	public void waitUntilElementIsHidden(WebElement element) {
		logger.debug("Waiting for element to be hidden: {}", element);
		new WebDriverWait(driver, defaultTimeOut)
				.until(ExpectedConditions.refreshed(ExpectedConditions.invisibilityOf(element)));
	}

	public void waitUntilPopupIsHidden() {
		logger.debug("Waiting for Popup to be hidden");
		WebElement popupElement = null;
		try {
			popupElement = driverWaitUntilElementIsPresentAndDisplayed("id", "cstudio-wcm-popup-div_mask");
		} catch (TimeoutException e) {
			logger.info("Popup is already closed");
			return;
		}
		waitUntilElementIsHidden(popupElement);
	}

	public void waitUntilAttributeIs(String selectorType, String selectorValue, String attributeName,
			String attributeValue) {
		logger.debug("Waiting for element {}, {} to have attribute {} = {}", selectorType, selectorValue,
				attributeName, attributeValue);
		new WebDriverWait(driver, defaultTimeOut).until(ExpectedConditions.refreshed(ExpectedConditions
				.attributeToBe(getSelector(selectorType, selectorValue), attributeName, attributeValue)));
	}

	public void waitUntilAttributeContains(String selectorType, String selectorValue, String attributeName,
			String attributeValue) {
		logger.debug("Waiting for element {}, {} to have attribute {} with {}", selectorType, selectorValue,
				attributeName, attributeValue);
		new WebDriverWait(driver, defaultTimeOut).until(ExpectedConditions.refreshed(ExpectedConditions
				.attributeContains(getSelector(selectorType, selectorValue), attributeName, attributeValue)));
	}

	public void waitUntilTextIs(String selectorType, String selectorValue, String textValue) {
		logger.debug("Waiting for element {}, {} to have the text {}", selectorType, selectorValue,
				textValue);
		new WebDriverWait(driver, defaultTimeOut).until(ExpectedConditions
				.refreshed(ExpectedConditions.textToBe(getSelector(selectorType, selectorValue), textValue)));
	}

	public void waitUntilElementIsRemoved(WebElement element) {
		logger.debug("Waiting for element to be removed: {}", element);
		new WebDriverWait(driver, defaultTimeOut).until(ExpectedConditions.stalenessOf(element));
	}

	public WebElement waitUntilElementIsSelected(String selectorType, String selectorValue) {
		logger.debug("Waiting for element to be selected: {}, {}", selectorType, selectorValue);
		By selector = getSelector(selectorType, selectorValue);
		new WebDriverWait(driver, defaultTimeOut)
				.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeSelected(selector)));
		return driver.findElement(selector);
	}

	public WebElement findElement(String selectorType, String selectorValue) {
		return driver.findElement(getSelector(selectorType, selectorValue));
	}

	public void dragAndDropElement(WebElement fromWebElement, WebElement toWebElement) {
		// Creating an actions builder
		Actions builder = new Actions(this.getDriver());

		// Creating the action for click and hold from the origin web element
		Action dragAndDrop = builder.clickAndHold(fromWebElement).moveToElement(toWebElement)
				.release(toWebElement).build();

		// commit the actions above
		dragAndDrop.perform();
	}

	public boolean isElementPresentByXpath(String xpathOfTheElement) {
		boolean isElementPresent = true;

		try {
			@SuppressWarnings("unused")
			WebElement webElement = this.findElement("xpath", xpathOfTheElement);
		} catch (NoSuchElementException e) {
			isElementPresent = false;
		} catch (Exception e) {
			isElementPresent = false;
		}

		return isElementPresent;
	}

	public boolean isElementPresentAndClickableByXpath(String xpathOfTheElement) {
		boolean isElementPresent = true;

		try {
			@SuppressWarnings("unused")
			WebElement webElement = findElement("xpath", xpathOfTheElement);

		} catch (NoSuchElementException e) {
			isElementPresent = false;
		} catch (Exception e) {
			isElementPresent = false;
		}

		return isElementPresent;
	}

	public boolean isElementPresentById(String id) {
		boolean isElementPresent = true;

		try {
			@SuppressWarnings("unused")
			WebElement webElement = this.findElement("id", id);

		} catch (NoSuchElementException e) {
			isElementPresent = false;
		} catch (Exception e) {
			isElementPresent = false;
		}

		return isElementPresent;
	}

	public boolean isElementPresentAndClickableByName(String name) {
		boolean isElementPresent = true;

		try {
			@SuppressWarnings("unused")
			WebElement webElement = this.findElement("name", name);

		} catch (NoSuchElementException e) {
			isElementPresent = false;
		} catch (Exception e) {
			isElementPresent = false;
		}

		return isElementPresent;
	}

	public boolean isElementPresentAndClickableById(String id) {
		boolean isElementPresent = true;

		try {
			@SuppressWarnings("unused")
			WebElement webElement = this.findElement("id", id);

		} catch (NoSuchElementException e) {
			isElementPresent = false;
		} catch (Exception e) {
			isElementPresent = false;
		}

		return isElementPresent;
	}

	public boolean isElementPresentBycssSelector(String cssSelector) {
		boolean isElementPresent = true;

		try {
			@SuppressWarnings("unused")
			WebElement webElement = this.findElement("cssSelector", cssSelector);

		} catch (NoSuchElementException e) {
			isElementPresent = false;
		} catch (Exception e) {
			isElementPresent = false;
		}

		return isElementPresent;
	}

	public boolean isElementPresentAndClickableBycssSelector(String cssSelector) {
		boolean isElementPresent = true;

		try {
			@SuppressWarnings("unused")
			WebElement webElement = this.findElement("cssSelector", cssSelector);
		} catch (NoSuchElementException e) {
			isElementPresent = false;
		} catch (Exception e) {
			isElementPresent = false;
		}

		return isElementPresent;
	}

	public void contextClick(String selectorType, String selectorValue, boolean executeThroughJavaScript) {
		for (int i = 0; i < numberOfAttemptsForElementsDisplayed; i++) {
			try {
				waitUntilElementIsClickable(selectorType, selectorValue);
				if (executeThroughJavaScript) {
					String script = "var element = arguments[0];"
							+ "var event = document.createEvent('HTMLEvents');"
							+ "event.initEvent('contextmenu', true, false);"
							+ "element.dispatchEvent(event);";
					((JavascriptExecutor) driver).executeScript(script,
							new Object[] { waitUntilElementIsClickable(selectorType, selectorValue) });
					break;
				} else {
					this.waitForAnimation();
					(new Actions(driver))
							.moveToElement(waitUntilElementIsClickable(selectorType, selectorValue)).build()
							.perform();
					this.waitUntilContentTooltipIsHidden();
					this.waitForAnimation();
					(new Actions(driver))
							.contextClick(waitUntilElementIsClickable(selectorType, selectorValue)).build()
							.perform();
					break;
				}
			} catch (StaleElementReferenceException e) {
				logger.warn("Element was moved or changed {}, {}, trying again", selectorType, selectorValue);
			}
		}

	}

	public void contextClick(WebDriver driver, WebElement element, Boolean executeThroughJavaScript) {
		if (executeThroughJavaScript) {
			String script = "var element = arguments[0];" + "var event = document.createEvent('HTMLEvents');"
					+ "event.initEvent('contextmenu', true, false);" + "element.dispatchEvent(event);";
			((JavascriptExecutor) driver).executeScript(script, new Object[] { element });
		} else {
			(new Actions(driver)).moveToElement(element, 0, 0).build().perform();
			this.waitUntilContentTooltipIsHidden();
			(new Actions(driver)).contextClick(element).build().perform();
		}
	}

	public void scrollUp() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0)");
	}

	public void scrollMiddle() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0)");
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,500)");
	}

	public void scrollDown() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,2000)");
	}

	public void scrollDownPx(int px) {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + px + ")");
	}

	public ConstantsPropertiesManager getConstantsPropertiesManager() {
		return constantsPropertiesManager;
	}

	public void setConstantsPropertiesManager(ConstantsPropertiesManager constantsPropertiesManager) {
		this.constantsPropertiesManager = constantsPropertiesManager;
	}

	public boolean elementHasChildsByXPath(String childsLocator) {
		boolean hasChilds = false;
		List<WebElement> childs = this.driver.findElements(By.xpath(childsLocator));

		if (!(childs.isEmpty()))
			hasChilds = true;

		return hasChilds;
	}

	public void moveMouseToElement(WebElement toElement) {
		// Creating an actions builder
		Actions builder = new Actions(this.getDriver());
		// Creating the action for click and hold from the origin web element
		Action action = builder.moveToElement(toElement).build();

		// commit the actions above
		action.perform();
	}

	public void waitUntilLoginCloses() {
		logger.debug("Waiting for login dialog to close");
		if ((webBrowserProperty.toLowerCase().equalsIgnoreCase("edge"))
				|| (webBrowserProperty.toLowerCase().equalsIgnoreCase("ie"))) {
			new WebDriverWait(this.driver, defaultTimeOut).until(ExpectedConditions
					.refreshed(ExpectedConditions.attributeToBe(By.tagName("body"), "class", "iewarning")));
		} else {
			new WebDriverWait(this.driver, defaultTimeOut).until(ExpectedConditions
					.refreshed(ExpectedConditions.attributeToBe(By.tagName("body"), "class", "")));
		}
	}

	public void waitUntilSidebarOpens() {
		logger.debug("Waiting for sidebar to open");
		this.waitUntilAttributeContains("xpath", ".//li[@id='acn-dropdown-wrapper']", "class",
				"site-dropdown-open");
	}

	public void waitUntilSidebarCloses() {
		logger.debug("Waiting for sidebar to close");
		waitUntilElementIsNotDisplayed("cssSelector", "div.acn-resize.ui-resizable");
	}

	public void waitUntilModalCloses() {
		logger.debug("Waiting for notification modal to close");
		WebElement element = this.waitUntilElementIsDisplayed("xpath",
				".//*[@class='modal fade ng-isolate-scope centered-dialog studioMedium in']");
		waitUntilElementIsRemoved(element);
	}

	public void waitUntilCreateSiteModalCloses() {
		logger.debug("Waiting for notification modal to close");
		WebElement element = this.waitUntilElementIsDisplayed("xpath", ".//div[@class='modal-content']");
		for (int i = 0; i < numberOfAttemptsForElementsDisplayed; i++) {
			try {
				waitUntilElementIsRemoved(element);
				break;
			} catch (TimeoutException e) {
				logger.warn("Element {} selected by {} does not disappear ", ".//div[@class='modal-content']",
						"xpath");
			}
		}
	}

	public void waitUntilPublishMaskedModalCloses() {
		logger.debug("Waiting for publish dialog to close");
		this.waitForAnimation();
		if ((webBrowserProperty.toLowerCase().equalsIgnoreCase("edge"))
				|| (webBrowserProperty.toLowerCase().equalsIgnoreCase("ie"))) {
			new WebDriverWait(this.driver, defaultTimeOut).until(ExpectedConditions
					.refreshed(ExpectedConditions.attributeToBe(By.tagName("body"), "class", "iewarning")));
		} else {
			new WebDriverWait(this.driver, defaultTimeOut).until(ExpectedConditions
					.refreshed(ExpectedConditions.attributeToBe(By.tagName("body"), "class", "")));
		}
	}

	public void waitUntilDashboardWidgetsAreLoaded() {
		logger.debug("Waiting for dashboard widgets are fully loaded");
		this.waitForAnimation();
		this.waitUntilLoadingAnimationIsNotDisplayedOnAllDashboardWidgets();
		this.waitUntilAttributeContains("xpath", ".//div[@id='GoLiveQueue']", "style", "display: block;");
		this.waitUntilAttributeContains("xpath", ".//div[@id='approvedScheduledItems']", "style",
				"display: block;");
		this.waitUntilAttributeContains("xpath", ".//div[@id='recentlyMadeLive']", "style",
				"display: block;");
		this.waitUntilAttributeContains("xpath", ".//div[@id='MyRecentActivity']", "style",
				"display: block;");
	}

	public void waitUntilLoadingAnimationIsNotDisplayedOnAllDashboardWidgets() {
		this.waitUntilDashboardLoadingAnimationIsNotDisplayedOnRecentActivity();
		this.waitUntilDashboardLoadingAnimationIsNotDisplayedOnRecentlyPublished();
		this.waitUntilDashboardLoadingAnimationIsNotDisplayedOnApprovedScheduled();
		this.waitUntilDashboardLoadingAnimationIsNotDisplayedOnItemsWaitingForApproval();
	}

	public void waitUntilDashboardLoadingAnimationIsNotDisplayedOnRecentActivity() {
		logger.debug("Waiting for loading animation is gone on Recent Activity");
		WebElement element = this.waitUntilElementIsPresent("xpath", ".//li[@id='loading-MyRecentActivity']");
		waitUntilElementIsHidden(element);
	}

	public void waitUntilDashboardLoadingAnimationIsNotDisplayedOnRecentlyPublished() {
		logger.debug("Waiting for loading animation is gone on Recently Published");
		WebElement element = this.waitUntilElementIsPresent("xpath", ".//li[@id='loading-recentlyMadeLive']");
		waitUntilElementIsHidden(element);
	}

	public void waitUntilDashboardLoadingAnimationIsNotDisplayedOnApprovedScheduled() {
		logger.debug("Waiting for loading animation is gone on Approved Scheduled");
		WebElement element = this.waitUntilElementIsPresent("xpath",
				".//li[@id='loading-approvedScheduledItems']");
		waitUntilElementIsHidden(element);
	}

	public void waitUntilDashboardLoadingAnimationIsNotDisplayedOnItemsWaitingForApproval() {
		logger.debug("Waiting for loading animation is gone on Waiting For Approval");
		WebElement element = this.waitUntilElementIsPresent("xpath", ".//li[@id='loading-GoLiveQueue']");
		waitUntilElementIsHidden(element);
	}

	public void waitUntilHomeIsOpened() {
		logger.debug("Waiting for home childs are displayed");
		this.waitUntilElementIsDisplayed("xpath",
				".//span[text()='Home']/../../../../../div[@class='ygtvchildren']");
	}

	public void waitUntilSiteConfigMaskedModalCloses() {
		logger.debug("Waiting for publish dialog to close");
		this.waitForAnimation();
		if ((webBrowserProperty.toLowerCase().equalsIgnoreCase("edge"))
				|| (webBrowserProperty.toLowerCase().equalsIgnoreCase("ie"))) {
			new WebDriverWait(this.driver, defaultTimeOut)
					.until(ExpectedConditions.refreshed(ExpectedConditions.attributeToBe(By.tagName("body"),
							"class", "yui-skin-cstudioTheme iewarning")));
		} else {
			new WebDriverWait(this.driver, defaultTimeOut).until(ExpectedConditions.refreshed(
					ExpectedConditions.attributeToBe(By.tagName("body"), "class", "yui-skin-cstudioTheme")));
		}
	}

	public void waitUntilDeleteSiteModalCloses() {
		logger.debug("Waiting for delete site dialog to close");
		this.waitForAnimation();

		for (int i = 0; i < numberOfAttemptsForElementsDisplayed; i++) {
			try {
				if ((webBrowserProperty.toLowerCase().equalsIgnoreCase("edge"))
						|| (webBrowserProperty.toLowerCase().equalsIgnoreCase("ie"))) {
					new WebDriverWait(this.driver, defaultTimeOut).until(ExpectedConditions.refreshed(
							ExpectedConditions.attributeToBe(By.tagName("body"), "class", "iewarning")));
				} else {
					new WebDriverWait(this.driver, defaultTimeOut).until(ExpectedConditions
							.refreshed(ExpectedConditions.attributeToBe(By.tagName("body"), "class", "")));
				}
				break;
			} catch (TimeoutException e) {
				logger.warn("Element {} selected by {} does not disappear ", ".//div[@class='modal-content']",
						"xpath");
			}
		}

	}

	public void waitUntilAddUserModalCloses() {
		logger.debug("Waiting for add user dialog to close");
		this.waitForAnimation();
		if ((webBrowserProperty.toLowerCase().equalsIgnoreCase("edge"))
				|| (webBrowserProperty.toLowerCase().equalsIgnoreCase("ie"))) {
			new WebDriverWait(this.driver, defaultTimeOut).until(ExpectedConditions
					.refreshed(ExpectedConditions.attributeToBe(By.tagName("body"), "class", "iewarning")));
		} else {
			new WebDriverWait(this.driver, defaultTimeOut).until(ExpectedConditions
					.refreshed(ExpectedConditions.attributeToBe(By.tagName("body"), "class", "")));
		}
	}

	public void waitUntilFolderOpens(String selectorType, String selectorValue) {
		logger.debug("Waiting for folder to open: {}, {}", selectorType, selectorValue);
		waitUntilAttributeContains(selectorType, selectorValue, "class", "open");
	}

	public void waitUntilContentTooltipIsHidden() {
		logger.debug("Waiting for content tooltip is hidden");
		WebElement element = this.waitUntilElementIsPresent("xpath",
				".//div[@id='acn-context-tooltipWrapper']");

		if (!element.getAttribute("style").contains("visibility: hidden;")) {
			waitUntilElementIsHidden(element);
		}
	}

	
	public void sendText(String selectorType, String selectorValue, String text) {
		logger.debug("Filling element {}, {} with value {}", selectorType, selectorValue, text);
		WebElement input = waitUntilElementIsClickable(selectorType, selectorValue);
		input.clear();
		input.sendKeys(text);
		waitUntilAttributeIs(selectorType, selectorValue, "value", text);
	}

	public void sendTextForSiteIDRestrictions(String selectorType, String selectorValue, String text) {
		logger.debug("Filling element {}, {} with value {}", selectorType, selectorValue, text);
		WebElement input = waitUntilElementIsClickable(selectorType, selectorValue);
		input.clear();
		input.sendKeys(text);
		waitUntilAttributeIs(selectorType, selectorValue, "value",
				text.toLowerCase().replaceAll("[^a-zA-Z0-9_-]", ""));
	}

	public void usingContextMenu(Runnable actions, String menuOption) {
		String selector;
		if ((menuOption.equalsIgnoreCase("Pages")) || (menuOption.equalsIgnoreCase("Components"))
				|| (menuOption.equalsIgnoreCase("Taxonomy"))) {
			selector = "div.yui-module.yui-overlay.yuimenu.wcm-root-folder-context-menu.visible";
		} else {
			selector = "div.yui-module.yui-overlay.yuimenu.visible";
		}

		WebElement menu = waitUntilElementIsClickable("cssSelector", selector);
		this.waitForAnimation();
		actions.run();
		waitUntilElementIsHidden(menu);
	}

	public void usingYuiDialog(Runnable actions) {
		String selector = ".//div[contains(@class, 'yui-panel-container') and contains(@class, 'yui-dialog') and contains(@style, 'visibility: visible;')]";
		WebElement dialog = waitUntilElementIsDisplayed("xpath", selector);
		waitUntilAttributeContains("xpath", selector, "style", "opacity: 1;");
		actions.run();
		waitUntilElementIsHidden(dialog);
	}

	public void usingYuiContainer(Runnable actions) {
		logger.debug("Switching to YUI container");
		String selector = "div.yui-panel-container.yui-dialog.yui-simple-dialog.cstudio-dialogue";
		driver.switchTo().defaultContent();

		waitUntilElementIsDisplayed("cssSelector", selector);
		this.waitForAnimation();

		waitUntilAttributeContains("cssSelector", selector, "style", "visibility: visible;");
		this.waitForAnimation();

		driver.switchTo().activeElement();

		actions.run();
		driver.switchTo().defaultContent();
	}

	public void usingCrafterForm(String selectorType, String selectorValue, Runnable actions) {
		waitForAnimation();
		logger.debug("Switching to iframe for form: {}, {}", selectorType, selectorValue);
		driver.switchTo().defaultContent();

		// Wait until animation completes
		waitForAnimation();
		WebElement frame = waitUntilElementIsDisplayed(selectorType, selectorValue);

		// Switch to iframe
		driver.switchTo().frame(frame);

		// Wait until any input is selected
		try {
			new WebDriverWait(driver, defaultTimeOut)
					.until(webDriver -> webDriver.switchTo().activeElement().getTagName().equals("input"));
		} catch (TimeoutException e) {
			logger.warn("No input was selected by Studio, this may cause an error later");
		}

		// Do stuff
		actions.run();

		driver.switchTo().defaultContent();

		// Wait until iframe is hidden
		try {
			waitUntilElementIsHidden(frame);
		} catch (TimeoutException e) {
			logger.warn("Forcing exit from form");

			driver.switchTo().frame(frame);

			WebElement saveCloseButton = this.driverWaitUntilElementIsPresentAndDisplayedAndClickable("id",
					"cstudioSaveAndClose");

			saveCloseButton.click();
			this.waitForAnimation();
			driver.switchTo().defaultContent();
		}
	}

	// Same as previuous but without inputs
	public void usingCrafterDialog(String selectorType, String selectorValue, Runnable actions) {
		logger.debug("Switching to iframe for form: {}, {}", selectorType, selectorValue);
		driver.switchTo().defaultContent();

		// Wait until animation completes
		WebElement frame = waitUntilElementIsDisplayed(selectorType, selectorValue);

		// Switch to iframe
		driver.switchTo().frame(frame);

		// Do stuff
		actions.run();

		// Exit ifreame
		driver.switchTo().defaultContent();

		// Wait until iframe is hidden
		waitUntilElementIsHidden(frame);
	}

	// Old API, kept to avoid a huge refactor

	public void waitUntilPageLoad() {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};

		Boolean isLoaded = new WebDriverWait(this.driver, this.defaultTimeOut).until(expectation);
		while (!isLoaded) {
			isLoaded = new WebDriverWait(this.driver, this.defaultTimeOut).until(expectation);
		}

	}

	public WebElement driverWaitUntilElementIsPresentAndDisplayed(String typeOfSelector,
			String selectorValue) {
		return waitUntilElementIsDisplayed(typeOfSelector, selectorValue);
	}

	public WebElement driverWaitUntilElementIsPresentAndDisplayedAndClickable(String typeOfSelector,
			String selectorValue) {
		return waitUntilElementIsClickable(typeOfSelector, selectorValue);
	}

	public void waitWhileElementIsDisplayedAndClickableByXpath(String xpath) {
		waitUntilElementIsClickable("xpath", xpath);
	}

	public void waitWhileElementIsPresentByXpath(String xpath) {
		waitUntilElementIsDisplayed("xpath", xpath);
	}

	public void waitWhileElementIsNotDisplayedByXpath(String xpath) {
		Boolean isPresent = this.isElementPresentAndClickableByXpath(xpath);

		while (isPresent) {
			isPresent = this.isElementPresentAndClickableByXpath(xpath);
			this.getDriver().navigate().refresh();
		}
	}

	public void waitForAnimation() {
		try {
			Thread.sleep(600);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void waitForFullExpansionOfTree() {
		try {
			Thread.sleep(1600);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void waitForDeliveryRefresh() {
		try {
			// wait for a minute for delivery refresh
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void takeScreenshot(String screenShotName) {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot,
					new File(FilesLocations.SCREENSHOTSFOLDEPATH, screenShotName + screenshot.getName()));
			logger.info("Screenshot saved: {}", screenShotName + screenshot.getName());
		} catch (IOException e) {
			logger.warn("Couldn't take screenshot", e);
		}
	}

	public void waitUntilElementHasPublishedIcon(String elementIconLocator) {
		try {
			this.waitUntilAttributeContains("xpath", elementIconLocator, "class", "undefined live");
		} catch (TimeoutException e) {
			this.takeScreenshot("PageNotPublished");
			logger.warn(
					"Content page is not published yet, it does not have published icon on pages structure");
		}
	}

	public int goToFolderAndExecuteInitSiteScriptThroughCommandLine(String siteId) {
		String script;
		String shell;
		String folder = System.getProperty("user.dir") + File.separator + ".." + File.separator + ".."
				+ File.separator + "crafter-delivery" + File.separator + "bin";

		if (executionEnvironment.equalsIgnoreCase("unix")) {
			shell = "/bin/bash";
			script = "init-site.sh";

			try {
				String[] command = { shell, script, siteId };

				ProcessBuilder processBuilder = new ProcessBuilder(command);

				processBuilder.directory(new File(folder));

				Process process = processBuilder.start();

				process.waitFor();

				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(process.getInputStream()));

				// Read the output from the command
				String output = "";
				String lineString = null;
				while ((lineString = bufferedReader.readLine()) != null) {
					output = output + lineString;
				}

				if (!(output.contains("{\"message\":\"Error from server at http://localhost:8695/solr: "
						+ "Core with name 'testsitefordeliverytest' already exists.\"}"))) {
					int occurencesOfCreatingSolrCore = StringUtils.countMatches(output, "Creating Solr Core");
					Assert.assertTrue((occurencesOfCreatingSolrCore == 1),
							"The init-site result was: " + output);
					int occurencesOfCreatingTarget = StringUtils.countMatches(output,
							"Creating Deployer Target");
					Assert.assertTrue((occurencesOfCreatingTarget == 1),
							"The init-site result was: " + output);
					int occurencesOfSuccessfully = StringUtils.countMatches(output, "successfully");
					Assert.assertTrue((occurencesOfSuccessfully == 2), "The init-site result was: " + output);
				}

				return process.exitValue();
			} catch (Exception exception) {
				exception.printStackTrace();
				return -1;
			}
		} else {
			String[] command = new String[3];
			command[0] = "cmd";
			command[1] = "/C";
			command[2] = "init-site.bat " + siteId;

			try {

				ProcessBuilder processBuilder = new ProcessBuilder(command);

				processBuilder.directory(new File(folder));

				Process process = processBuilder.start();

				process.waitFor();

				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(process.getInputStream()));

				// Read the output from the command
				String output = "";
				String lineString = null;
				while ((lineString = bufferedReader.readLine()) != null) {
					output = output + lineString;
				}
				if (!(output.contains("java.io.IOException: Server returned HTTP response code: 500"
						+ " for URL: http://localhost:9080/crafter-search/api/2/admin/index/create"))) {
					int occurencesOfCreatingSolrCore = StringUtils.countMatches(output, "Creating Solr Core");
					Assert.assertTrue((occurencesOfCreatingSolrCore == 1),
							"The init-site result was: " + output);
					int occurencesOfCreatingTarget = StringUtils.countMatches(output,
							"Creating Deployer Target");
					Assert.assertTrue((occurencesOfCreatingTarget == 1),
							"The init-site result was: " + output);
					int occurencesOfSuccessfully = StringUtils.countMatches(output, "successfully");
					Assert.assertTrue((occurencesOfSuccessfully == 2), "The init-site result was: " + output);
				}

				return process.exitValue();
			} catch (Exception exception) {
				exception.printStackTrace();
				return -1;
			}
		}

	}

	@SuppressWarnings("deprecation")
	public int goToFolderAndExecuteGitInitBareRepository(String repositoryName) {
		String repositoryFolder = System.getProperty("user.home") + File.separator + "craftercms_testrepos"
				+ File.separator + repositoryName + File.separator;

		try {
			// if the repository folder does not exist, it will create it.
			Git bareRepo = Git.init().setBare(true).setDirectory(new File(repositoryFolder)).call();

			// Assert if there is the HEAD file
			Assert.assertNotNull(bareRepo.getRepository().getRef(Constants.HEAD));

			return 0;
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return -1;
		} catch (GitAPIException e) {
			e.printStackTrace();
			return -1;
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public int goToFolderAndExecuteDeleteBareRepositoryFolder(String repositoryName) {
		try {
			String repositoryFolder = System.getProperty("user.home") + File.separator
					+ "craftercms_testrepos";

			FileUtils.deleteDirectory(new File(repositoryFolder));

			repositoryFolder = repositoryFolder + File.separator + repositoryName + File.separator;
			
			FileUtils.deleteDirectory(new File(repositoryFolder));
			
			return 0;
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public String getLocalBareRepoURL(String repositoryName) {
		String repositoryFolder = System.getProperty("user.home") + File.separator + "craftercms_testrepos"
				+ File.separator + repositoryName + File.separator;

		if ("unix".equalsIgnoreCase(executionEnvironment)) {
			return repositoryFolder;
		} else {
			// Remove the drive name and replacing to :/ to convert to openSSH format
			// Replace \ windows separator to / openSSh path format
			repositoryFolder = ":/" + FilenameUtils.getPath(repositoryFolder)
					+ FilenameUtils.getName(repositoryFolder);
			return repositoryFolder.replace("\\", "/");
		}
	}

	public String getAuthoringSiteSandboxRepoURL(String siteID) {
		String repositoryFolder = System.getProperty("user.dir") + File.separator + ".." + File.separator
				+ ".." + File.separator + "crafter-authoring" + File.separator + "data" + File.separator
				+ "repos" + File.separator + "sites" + File.separator + siteID + File.separator + "sandbox";

		return repositoryFolder;
	}

	public void focusAndScrollDownToBottomInASection(String cssContainer, String cssSelectorValue) {
		if ((webBrowserProperty.toLowerCase().equalsIgnoreCase("edge"))
				|| (webBrowserProperty.toLowerCase().equalsIgnoreCase("ie"))) {
			((JavascriptExecutor) driver).executeScript("$('" + cssContainer + "').scrollTop($('"
					+ cssSelectorValue + "').last().offset().top);");

		}
	}

	public void focusAndScrollDownToMiddleInASection(String cssContainer, String cssSelectorValue) {
		if ((webBrowserProperty.toLowerCase().equalsIgnoreCase("edge"))
				|| (webBrowserProperty.toLowerCase().equalsIgnoreCase("ie"))) {
			((JavascriptExecutor) driver).executeScript("$('" + cssContainer + "').scrollTop($('"
					+ cssSelectorValue + ":first-child').height()*7);");

		}
	}

	public void scrollDownIntoSideBar() {
		WebElement siteConfigButton = this.driverWaitUntilElementIsPresentAndDisplayed("id", "admin-console");
		((JavascriptExecutor) this.driver).executeScript("arguments[0].scrollIntoView(true);",
				siteConfigButton);
	}

	public void scrollUpIntoSideBar(String selectorValue) {
		WebElement element = this.driverWaitUntilElementIsPresentAndDisplayed("xpath", selectorValue);
		((JavascriptExecutor) this.driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void scrollRightIntoSideBar(String element) {
		WebElement webelement = this.driverWaitUntilElementIsPresentAndDisplayed("xpath", element);
		((JavascriptExecutor) this.driver).executeScript("arguments[0].scrollIntoView(true);", webelement);
	}

	public void clickIfFolderIsNotExpanded(String selectorValue) {
		if (!(this.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", selectorValue)
				.getAttribute("class").contains("open")))
			this.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", selectorValue).click();
	}

	public void fileUploadUsingSendKeys(String locator, String filePath) {
		By selector = getSelector("xpath", locator);
		new WebDriverWait(driver, defaultTimeOut).until(ExpectedConditions.elementToBeClickable(selector));
		WebElement element;

		try {
			element = driver.findElement(selector);
		} catch (NoSuchElementException e) {
			logger.warn("Element has been removed {}, {}", "xpath", locator);
			element = waitUntilElementIsDisplayed("xpath", locator);
		}

		element.sendKeys(filePath);
	}

	public void waitForBulkPublish(int waitTimeOut) {
		try {
			Thread.sleep(waitTimeOut);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void selectAllAndDeleteContentAsFolderValueOnCodeArea(String elementLocator, String newTextValue) {
		WebElement element = this.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				elementLocator);
		element.click();
		(new Actions(this.driver)).moveToElement(element).sendKeys(Keys.chord(Keys.CONTROL, "A"))
				.sendKeys(Keys.chord(Keys.DELETE)).pause(100).sendKeys(Keys.chord(newTextValue)).perform();
		this.waitForAnimation();
	}

	public String getCurrentYear() {
		Calendar calendar = Calendar.getInstance();
		return String.valueOf(calendar.get(Calendar.YEAR));
	}

	public String getCurrentDay() {
		Calendar calendar = Calendar.getInstance();
		if ((calendar.get(Calendar.DATE)) < 10) {
			return "0" + String.valueOf(calendar.get(Calendar.DATE));
		} else {
			return String.valueOf(calendar.get(Calendar.DATE));
		}
	}

	public String getCurrentMonth() {
		Calendar calendar = Calendar.getInstance();
		if ((calendar.get(Calendar.MONTH) + 1) < 10) {
			return "0" + String.valueOf((calendar.get(Calendar.MONTH) + 1));
		} else {
			return String.valueOf((calendar.get(Calendar.MONTH) + 1));
		}
	}

	public void uploadFilesOnADirectoryUsingAPICalls(File rootDirectory, String siteId, String rootPath) {
		logger.info("Executing bulk upload using API calls to upload files into path {} on site {}", rootPath,
				siteId);
		// Uploading files and folders on site repository given path
		APIConnectionManager apiConnectionManager = new APIConnectionManager();
		JsonTester api = new JsonTester(apiConnectionManager.getProtocol(), apiConnectionManager.getHost(),
				apiConnectionManager.getPort());
		SecurityAPI securityAPI = new SecurityAPI(api, apiConnectionManager);
		ContentAssetAPI contentAssetAPI = new ContentAssetAPI(api, apiConnectionManager);

		securityAPI.logInIntoStudioUsingAPICall();
		createFoldersAndFilesStructureUsingAPICalls(rootDirectory, siteId, rootPath, contentAssetAPI);
		securityAPI.logOutFromStudioUsingAPICall();
	}

	public void createFoldersAndFilesStructureUsingAPICalls(File rootDirectory, String siteId,
			String rootPath, ContentAssetAPI contentAssetAPI) {

		// Creating parent folder on site repository path
		createFolderUsingAPICall(rootDirectory, siteId, rootPath, contentAssetAPI);

		String childPath = rootPath + File.separator + rootDirectory.getName();

		File[] listOfFiles = rootDirectory.listFiles();

		for (File file : listOfFiles) {
			if (file.isDirectory()) {
				// Create folder on site repository path using API call
				createFoldersAndFilesStructureUsingAPICalls(file, siteId, childPath, contentAssetAPI);
			} else {
				// Write file on site repository path using API Call
				writeFileUsingAPICall(file, siteId, childPath, contentAssetAPI);
			}
		}
	}

	public void createFolderUsingAPICall(File file, String siteId, String path,
			ContentAssetAPI contentAssetAPI) {
		logger.info("Executing create folder API call to create folder into path {} on site {}", path,
				siteId);
		// Calling api call to create folder on site repository path
		if (file.isDirectory()) {
			contentAssetAPI.testCreateFolderOnAPath(siteId, path, file.getName());
		}
	}

	public void writeFileUsingAPICall(File file, String siteId, String path,
			ContentAssetAPI contentAssetAPI) {
		logger.info("Executing write content API call to create file into path {} on site {}", path, siteId);
		// Calling api call to write file or content on site repository path
		if (!file.isDirectory()) {
			contentAssetAPI.testWriteContentOnFolder(siteId, path, "folder", file);
		}
	}

	public int createFoldersStructureForTestingCopiedFromSite(String siteId) {
		logger.info("Creating test folders structures based on repository folders of site: {}", siteId);
		try {
			// First create folders con craftercms folder
			this.createFoldersOnCrafterFolder();
			// Go to siteFolder and copy folders to previously created folders
			this.goToScriptsFolderAndCreateTestStructure(siteId);
			this.goToStaticAssetsImagesFolderAndCreateTestStructure(siteId);
			this.goToStaticAssetsFolderAndCreateTestStructure(siteId);
			this.goToTemplatesFolderAndCreateTestStructure(siteId);
			this.goToStaticAssetsFontsFolderAndCreateTestStructure(siteId);
			this.goToStaticAssetsCSSFolderAndCreateTestStructure(siteId);
			this.goToStaticAssetsJSFolderAndCreateTestStructure(siteId);

			return 0;
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}

	}

	public void createFoldersOnCrafterFolder() {
		logger.info("Creating test folders structures on craftercms temporal folder");
		// Creating the test folders structures on craftercms (empty structure)
		logger.info("Creating test folder: {}", FilesLocations.BULKUPLOAD_FOLDERFILEPATH);
		new File(FilesLocations.BULKUPLOAD_FOLDERFILEPATH).mkdir();
		logger.info("Creating test folder: {}", FilesLocations.BULK_CSSFOLDERFILEPATH);
		new File(FilesLocations.BULK_CSSFOLDERFILEPATH).mkdir();
		logger.info("Creating test folder: {}", FilesLocations.BULK_FONTSFOLDERFILEPATH);
		new File(FilesLocations.BULK_FONTSFOLDERFILEPATH).mkdir();
		logger.info("Creating test folder: {}", FilesLocations.BULK_IMAGEFOLDERFILEPATH);
		new File(FilesLocations.BULK_IMAGEFOLDERFILEPATH).mkdir();
		logger.info("Creating test folder: {}", FilesLocations.BULK_JSFOLDERFILEPATH);
		new File(FilesLocations.BULK_JSFOLDERFILEPATH).mkdir();
		logger.info("Creating test folder: {}", FilesLocations.BULK_SCRIPTSFOLDERFILEPATH);
		new File(FilesLocations.BULK_SCRIPTSFOLDERFILEPATH).mkdir();
		logger.info("Creating test folder: {}", FilesLocations.BULK_TEMPLATESFILEPATH);
		new File(FilesLocations.BULK_TEMPLATESFILEPATH).mkdir();
		logger.info("Creating test folder: {}", FilesLocations.BULK_TXTFILEPATH);
		new File(FilesLocations.BULK_TXTFILEPATH).mkdir();

		logger.info("Creating test folder: {}", FilesLocations.BULK_SCRIPTSFOLDERFILEPATH + "scripts");
		new File(FilesLocations.BULK_SCRIPTSFOLDERFILEPATH + "scripts" + File.separator).mkdir();
		logger.info("Creating test folder: {}", FilesLocations.BULK_IMAGEFOLDERFILEPATH + "images");
		new File(FilesLocations.BULK_IMAGEFOLDERFILEPATH + "images" + File.separator).mkdir();
		logger.info("Creating test folder: {}", FilesLocations.BULK_TXTFILEPATH + "txt");
		new File(FilesLocations.BULK_TXTFILEPATH + "txt" + File.separator).mkdir();
		logger.info("Creating test folder: {}", FilesLocations.BULK_TEMPLATESFILEPATH + "templates");
		new File(FilesLocations.BULK_TEMPLATESFILEPATH + "templates" + File.separator).mkdir();
		logger.info("Creating test folder: {}", FilesLocations.BULK_FONTSFOLDERFILEPATH + "fonts");
		new File(FilesLocations.BULK_FONTSFOLDERFILEPATH + "fonts" + File.separator).mkdir();
		logger.info("Creating test folder: {}", FilesLocations.BULK_CSSFOLDERFILEPATH + "css");
		new File(FilesLocations.BULK_CSSFOLDERFILEPATH + "css" + File.separator).mkdir();
		logger.info("Creating test folder: {}", FilesLocations.BULK_JSFOLDERFILEPATH + "js");
		new File(FilesLocations.BULK_JSFOLDERFILEPATH + "js" + File.separator).mkdir();
	}

	public void waitForBulkUploadSyncProcess() {
		// wait for given amount seconds for full update of log file of tomcat after
		// bulk upload proccess sync
		logger.info("Waiting for {} milliseconds to full upgrade of log file", bulkUploadSyncTimeOut);
		try {
			Thread.sleep(bulkUploadSyncTimeOut);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void goToScriptsFolderAndCreateTestStructure(String siteId) throws IOException {
		// Creating test folder structure for scripts bulk upload test
		logger.info(
				"Copying full files and folders structure from Scripts site repository folder to test folder structure");
		String testFolderPath = FilesLocations.BULK_SCRIPTSFOLDERFILEPATH + "scripts" + File.separator;
		for (int i = 0; i <= amountOfTestFoldersToGenerateForBulkUploadTest; i++) {
			File sourceFolder = this.getAuthoringSiteFolder(siteId, "scripts");
			this.copyFolderStructureToTestFolder(sourceFolder, new File(testFolderPath));
			testFolderPath = testFolderPath + "rest" + File.separator + "scripts" + File.separator;
		}
	}

	public void goToStaticAssetsImagesFolderAndCreateTestStructure(String siteId) throws IOException {
		// Creating test folder structure for static-asssets/images bulk upload test
		logger.info(
				"Copying full files and folders structure from static-assets/images site repository folder to test folder structure");
		String testFolderPath = FilesLocations.BULK_IMAGEFOLDERFILEPATH + "images" + File.separator;
		for (int i = 0; i <= amountOfTestFoldersToGenerateForBulkUploadTest; i++) {
			File sourceFolder = this.getAuthoringSiteFolder(siteId,
					"static-assets" + File.separator + "images");
			this.copyFolderStructureToTestFolder(sourceFolder, new File(testFolderPath));
			testFolderPath = testFolderPath + "images" + File.separator;
		}
	}

	public void goToStaticAssetsFolderAndCreateTestStructure(String siteId) throws IOException {
		// Creating test folder structure for static-asssets/ bulk upload test
		logger.info(
				"Copying full files and folders structure from static-assets site repository folder to test folder structure");
		String testFolderPath = FilesLocations.BULK_TXTFILEPATH + "txt" + File.separator;
		for (int i = 0; i <= amountOfTestFoldersToGenerateForBulkUploadTest; i++) {
			File sourceFolder = this.getAuthoringSiteFolder(siteId, "static-assets");
			this.copyFolderStructureToTestFolder(sourceFolder, new File(testFolderPath));
			testFolderPath = testFolderPath + "txt" + File.separator;
		}
	}

	public void goToTemplatesFolderAndCreateTestStructure(String siteId) throws IOException {
		// Creating test folder structure for templates bulk upload test
		logger.info(
				"Copying full files and folders structure from templates site repository folder to test folder structure");
		String restFolderPath = FilesLocations.BULK_TEMPLATESFILEPATH + "templates" + File.separator;
		for (int i = 0; i <= amountOfTestFoldersToGenerateForBulkUploadTest; i++) {
			File sourceFolder = this.getAuthoringSiteFolder(siteId, "templates");
			this.copyFolderStructureToTestFolder(sourceFolder, new File(restFolderPath));
			restFolderPath = restFolderPath + "web" + File.separator + "templates" + File.separator;
		}
	}

	public void goToStaticAssetsFontsFolderAndCreateTestStructure(String siteId) throws IOException {
		// Creating test folder structure for static-asssets/fonts bulk upload test
		logger.info(
				"Copying full files and folders structure from static-assets/fonts site repository folder to test folder structure");
		String testFolderPath = FilesLocations.BULK_FONTSFOLDERFILEPATH + "fonts" + File.separator;
		for (int i = 0; i <= amountOfTestFoldersToGenerateForBulkUploadTest; i++) {
			File sourceFolder = this.getAuthoringSiteFolder(siteId,
					"static-assets" + File.separator + "fonts");
			this.copyFolderStructureToTestFolder(sourceFolder, new File(testFolderPath));
			testFolderPath = testFolderPath + "fonts" + File.separator;
		}
	}

	public void goToStaticAssetsCSSFolderAndCreateTestStructure(String siteId) throws IOException {
		// Creating test folder structure for static-asssets/css bulk upload test
		logger.info(
				"Copying full files and folders structure from static-assets/css site repository folder to test folder structure");
		String testFolderPath = FilesLocations.BULK_CSSFOLDERFILEPATH + "css" + File.separator;
		for (int i = 0; i <= amountOfTestFoldersToGenerateForBulkUploadTest; i++) {
			File sourceFolder = this.getAuthoringSiteFolder(siteId, "static-assets" + File.separator + "css");
			this.copyFolderStructureToTestFolder(sourceFolder, new File(testFolderPath));
			testFolderPath = testFolderPath + "css" + File.separator;
		}
	}

	public void goToStaticAssetsJSFolderAndCreateTestStructure(String siteId) throws IOException {
		// Creating test folder structure for static-asssets/js bulk upload test
		logger.info(
				"Copying full files and folders structure from static-assets/js site repository folder to test folder structure");
		String testFolderPath = FilesLocations.BULK_JSFOLDERFILEPATH + "js" + File.separator;
		for (int i = 0; i <= amountOfTestFoldersToGenerateForBulkUploadTest; i++) {
			File sourceFolder = this.getAuthoringSiteFolder(siteId, "static-assets" + File.separator + "js");
			this.copyFolderStructureToTestFolder(sourceFolder, new File(testFolderPath));
			testFolderPath = testFolderPath + "ie" + File.separator + "js" + File.separator;
		}
	}

	public void copyFolderStructureToTestFolder(File source, File target) throws IOException {
		// Copy all folders structure to test folder structure
		logger.info(
				"Copying full Folder structure from given site repository folder to test folder structure");
		this.copy(source, target);
	}

	public File getAuthoringSiteFolder(String siteId, String requestedFolderPath) {
		// locate the given folder path on crafter-authoring repo folder
		File existentFolder = new File(System.getProperty("user.dir") + File.separator + ".." + File.separator
				+ ".." + File.separator + "crafter-authoring" + File.separator + "data" + File.separator
				+ "repos" + File.separator + "sites" + File.separator + siteId + File.separator + "sandbox"
				+ File.separator + requestedFolderPath);
		return existentFolder;
	}

	public void copy(File sourceLocation, File targetLocation) throws IOException {
		// copying file by file to test folder structure and checking if folder or file.
		if (sourceLocation.isDirectory()) {
			copyDirectory(sourceLocation, targetLocation);
		} else {
			copyFile(sourceLocation, targetLocation);
		}
	}

	private void copyDirectory(File source, File target) throws IOException {
		// copying source folder to target on test folders structure
		logger.info("Copying Folder from given site repository folder to test folder structure");
		if (!target.exists()) {
			target.mkdir();
		}

		for (String f : source.list()) {
			copy(new File(source, f), new File(target, f));
		}
	}

	public boolean isAnAllowedFile(File source) {
		// checking if the source file to be copied is allowed file type
		logger.info("Checking if file extension is allowed to copy from repository site folder");
		boolean isAllowed;
		switch (source.getName()) {
		case ".keep":
			isAllowed = false;
			break;
		case ".DS_Store":
			isAllowed = false;
			break;
		case "thumbs.db":
			isAllowed = false;
			break;
		default:
			isAllowed = true;
			break;
		}

		return isAllowed;
	}

	private void copyFile(File source, File target) throws IOException {
		// copying file (if allowed) to the test folders structure on craftercms folder
		logger.info("Copying File from given site repository folder to test folder structure");
		if (isAnAllowedFile(source)) {
			try (InputStream inputStream = new FileInputStream(source);
					OutputStream outputStream = new FileOutputStream(target)) {
				byte[] buf = new byte[1024];
				int length;
				while ((length = inputStream.read(buf)) > 0) {
					outputStream.write(buf, 0, length);
				}
			}
		}
	}

	public int deleteFoldersStructureForTestingCopiedFromSite() {
		// delete the parent folder of the folders used to test bulk upload proccess
		logger.info("Deleting the all test folders structure from craftercms folder");
		try {
			FileUtils.deleteDirectory(new File(FilesLocations.BULKUPLOAD_FOLDERFILEPATH));
			return 0;
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public int getNumberOfAttemptsForElementsDisplayed() {
		return numberOfAttemptsForElementsDisplayed;
	}

	public void setNumberOfAttemptsForElementsDisplayed(int numberOfAttemptsForElementsDisplayed) {
		this.numberOfAttemptsForElementsDisplayed = numberOfAttemptsForElementsDisplayed;
	}

	public String getStudioTomcatLog() {
		// locate the path of the tomcat log file
		String tomcatLog = System.getProperty("user.dir") + File.separator + ".." + File.separator + ".."
				+ File.separator + "crafter-authoring" + File.separator + "logs" + File.separator + "tomcat"
				+ File.separator;

		if (executionEnvironment.equalsIgnoreCase("unix")) {
			return tomcatLog + "catalina.out";
		} else {
			return tomcatLog + "studio.log";
		}
	}

	public int getAmountOfLinesOnLogFile(File file) {
		// reading and counting the current amount of lines in the log file
		logger.info("Getting the total amount of lines in the log file: {}", file.getName());
		this.waitForAnimation();
		int amoutOfLines = 0;

		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

			while ((bufferedReader.readLine()) != null) {
				amoutOfLines++;
			}

			fileInputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return amoutOfLines;
	}

	public int getCorrectAmountOfLogLinesAfterWaitTime(File file, int currentAmount) {
		int newAmountOfLines = currentAmount;
		for (int i = 0; i < numberOfAttemptsForLogFileUpdate; i++) {
			// waiting for last update on the tomcatlog for get all the lines into it.
			this.waitForBulkUploadSyncProcess();
			int amountOfLinesReturned = this.getAmountOfLinesOnLogFile(file);
			if (currentAmount < amountOfLinesReturned) {
				newAmountOfLines = amountOfLinesReturned;
			}
		}
		return newAmountOfLines;
	}

	public void checkNoErrorsOnStudioTomcatLogInGivenLastLines(String siteId, int amountOfLastLinesToCheck) {

		int countOfMatchesForWriteContentError = 0;
		int countOfMatchesForSyncingDataBase = 0;
		int countOfMatchesForDoneOnSyncingDataBase = 0;
		int countOfMatchesForLastCommitOnSite = 0;

		try {
			// determining the amount of important lines of the log file to check (last
			// lines)
			int startingLineToCheck = this.getCorrectAmountOfLogLinesAfterWaitTime(
					new File(getStudioTomcatLog()),
					this.getAmountOfLinesOnLogFile(new File(getStudioTomcatLog())))
					- amountOfLastLinesToCheck;

			File tomcatLogFile = new File(getStudioTomcatLog());

			FileInputStream fileInputStream = new FileInputStream(tomcatLogFile);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

			int currentLine = 1;
			String logLine;

			while ((logLine = bufferedReader.readLine()) != null) {
				// start checking only when currenLine be larger than the amount of last lines
				// to check
				if (currentLine >= startingLineToCheck) {
					// checking if there is an Error associated with Write content call
					if (logLine.contains("[ERROR]")) {
						if (this.checkIfThereIsNotErrorOnLogLinesWhenWriteContent(logLine)) {
							countOfMatchesForWriteContentError++;
						}
					} else if (logLine.contains("[INFO]")) {
						// checking if there are syncing success messages
						if (checkIfThereIsSyncingDataBaseMessageWhenWriteContent(logLine, siteId)) {
							countOfMatchesForSyncingDataBase++;
						}

						if (checkIfThereIsDoneSyncingDataBaseMessageWhenWriteContent(logLine, siteId)) {
							countOfMatchesForDoneOnSyncingDataBase++;
						}

						if (checkIfThereIsLastCommitMessageWhenWriteContent(logLine, siteId)) {
							countOfMatchesForLastCommitOnSite++;
						}
					}
				}

				// continuing increasing the amount of lines read
				currentLine++;
			}

			Assert.assertTrue((countOfMatchesForWriteContentError == 0),
					"There is an error processig content on bulk upload");
			Assert.assertTrue((countOfMatchesForSyncingDataBase >= 1), "There is not match for log message: "
					+ "Syncing database with repository for site: " + siteId);
			Assert.assertTrue((countOfMatchesForDoneOnSyncingDataBase >= 1),
					"There is not match for log message: "
							+ "Done syncing database with repository for site: " + siteId);
			Assert.assertTrue((countOfMatchesForLastCommitOnSite >= 1),
					"There is not match for log message: " + "Last commit ID for site: " + siteId);

			fileInputStream.close();
		} catch (

		IOException e) {
			e.printStackTrace();
		}

	}

	public boolean checkIfThereIsNotErrorOnLogLinesWhenWriteContent(String logLine) {
		return (logLine.contains("Error processing content") && (logLine.contains(
				"org.craftercms.studio.api.v1.exception.ContentProcessException: Failed to write")));
	}

	public boolean checkIfThereIsSyncingDataBaseMessageWhenWriteContent(String logLine, String siteID) {
		return logLine.contains("Syncing database with repository for site: " + siteID);
	}

	public boolean checkIfThereIsDoneSyncingDataBaseMessageWhenWriteContent(String logLine, String siteID) {
		return logLine.contains("Done syncing database with repository for site: " + siteID);
	}

	public boolean checkIfThereIsLastCommitMessageWhenWriteContent(String logLine, String siteID) {
		return logLine.contains("Last commit ID for site: " + siteID);
	}

	public String getPrivateKeyContentFromPrivateKeyTestFile(String fileLocation) {
		String keyContent = "";

		try {
			File privateKeyFile = new File(fileLocation);
			DataInputStream dataInputStream = new DataInputStream(new FileInputStream(privateKeyFile));

			byte[] privateKeyBytes = new byte[(int) privateKeyFile.length()];

			dataInputStream.readFully(privateKeyBytes);
			dataInputStream.close();

			keyContent = new String(privateKeyBytes, StandardCharsets.UTF_8);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return keyContent;
	}
	
	public void waitUntilNotificationModalIsNotPresent() {
		logger.debug("Waiting for notification modal to close");
		WebElement notification = this.waitUntilElementIsDisplayed("xpath",
				".//div[@class='modal-dialog modal-sm']");
		this.waitUntilElementIsRemoved(notification);
	}
}