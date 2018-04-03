package org.craftercms.studio.test.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

public class WebDriverManager {

	private static final Logger logger = LogManager.getLogger(WebDriverManager.class);

	WebDriver driver;
	private ConstantsPropertiesManager constantsPropertiesManager;
	private int defaultTimeOut;
	private String webBrowserProperty;
	private String executionEnvironment;

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
					System.setProperty("phantomjs.binary.path", envProperties.getProperty("phantomjs.binary.path"));
					driver = new PhantomJSDriver(capabilities);
					break;
				case "firefox":
					FirefoxOptions firefoxOptions = new FirefoxOptions();
					System.setProperty("webdriver.gecko.driver", envProperties.getProperty("firefox.driver.path"));
					driver = new FirefoxDriver(firefoxOptions);
					break;
				case "edge":
					System.setProperty("webdriver.edge.driver", envProperties.getProperty("edge.driver.path"));
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
					System.setProperty("webdriver.chrome.driver", envProperties.getProperty("chrome.driver.path"));
					driver = new ChromeDriver(chromeOptions);
					break;
				default:
					throw new IllegalArgumentException(
							"webBrowser property is needed, valid values are:" + "chrome,edge,ie,firefox,phantomjs");
				}

				driver.get(envProperties.getProperty("baseUrl"));
				this.defaultTimeOut = Integer.parseInt(
						constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.defaulttimeout"));

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
					System.setProperty("phantomjs.binary.path", envProperties.getProperty("phantomjs.binary.path"));
					driver = new PhantomJSDriver(capabilities);
					break;
				case "firefox":
					FirefoxOptions firefoxOptions = new FirefoxOptions();
					System.setProperty("webdriver.gecko.driver", envProperties.getProperty("firefox.driver.path"));
					driver = new FirefoxDriver(firefoxOptions);
					break;
				case "edge":
					System.setProperty("webdriver.edge.driver", envProperties.getProperty("edge.driver.path"));
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
					System.setProperty("webdriver.chrome.driver", envProperties.getProperty("chrome.driver.path"));
					driver = new ChromeDriver(chromeOptions);
					break;
				default:
					throw new IllegalArgumentException(
							"webBrowser property is needed, valid values are:" + "chrome,edge,ie,firefox,phantomjs");
				}
				this.waitForDeliveryRefresh();
				driver.get((envProperties.getProperty("deliverybaseUrl")) + "?crafterSite=" + siteId);
				this.defaultTimeOut = Integer.parseInt(
						constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.defaulttimeout"));

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

	public void waitUntilContentTypeNotificationIsNotDisplayed(String typeOfSelector, String selectorValueForChilds,
			WebElement parentElement) {
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
		new WebDriverWait(driver, defaultTimeOut)
				.until(ExpectedConditions.refreshed(ExpectedConditions.invisibilityOf(driver.findElement(selector))));
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
		logger.debug("Waiting for element {}, {} to have attribute {} = {}", selectorType, selectorValue, attributeName,
				attributeValue);
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
		logger.debug("Waiting for element {}, {} to have the text {}", selectorType, selectorValue, textValue);
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
		Action dragAndDrop = builder.clickAndHold(fromWebElement).moveToElement(toWebElement).release(toWebElement)
				.build();

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
		for (int i = 0; i < 3; i++) {
			try {
				waitUntilElementIsClickable(selectorType, selectorValue);
				if (executeThroughJavaScript) {
					String script = "var element = arguments[0];" + "var event = document.createEvent('HTMLEvents');"
							+ "event.initEvent('contextmenu', true, false);" + "element.dispatchEvent(event);";
					((JavascriptExecutor) driver).executeScript(script,
							new Object[] { waitUntilElementIsClickable(selectorType, selectorValue) });
					break;
				} else {
					this.waitForAnimation();
					(new Actions(driver)).moveToElement(waitUntilElementIsClickable(selectorType, selectorValue))
							.build().perform();
					this.waitUntilContentTooltipIsHidden();
					this.waitForAnimation();
					(new Actions(driver)).contextClick(waitUntilElementIsClickable(selectorType, selectorValue)).build()
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
			new WebDriverWait(this.driver, defaultTimeOut).until(
					ExpectedConditions.refreshed(ExpectedConditions.attributeToBe(By.tagName("body"), "class", "")));
		}
	}

	public void waitUntilSidebarOpens() {
		logger.debug("Waiting for sidebar to open");
		this.waitUntilAttributeContains("xpath", ".//li[@id='acn-dropdown-wrapper']", "class", "site-dropdown-open");
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

	public void waitUntilPublishMaskedModalCloses() {
		logger.debug("Waiting for publish dialog to close");
		this.waitForAnimation();
		if ((webBrowserProperty.toLowerCase().equalsIgnoreCase("edge"))
				|| (webBrowserProperty.toLowerCase().equalsIgnoreCase("ie"))) {
			new WebDriverWait(this.driver, defaultTimeOut).until(ExpectedConditions
					.refreshed(ExpectedConditions.attributeToBe(By.tagName("body"), "class", "iewarning")));
		} else {
			new WebDriverWait(this.driver, defaultTimeOut).until(
					ExpectedConditions.refreshed(ExpectedConditions.attributeToBe(By.tagName("body"), "class", "")));
		}
	}

	public void waitUntilSiteConfigMaskedModalCloses() {
		logger.debug("Waiting for publish dialog to close");
		this.waitForAnimation();
		if ((webBrowserProperty.toLowerCase().equalsIgnoreCase("edge"))
				|| (webBrowserProperty.toLowerCase().equalsIgnoreCase("ie"))) {
			new WebDriverWait(this.driver, defaultTimeOut).until(ExpectedConditions.refreshed(
					ExpectedConditions.attributeToBe(By.tagName("body"), "class", "yui-skin-cstudioTheme iewarning")));
		} else {
			new WebDriverWait(this.driver, defaultTimeOut).until(ExpectedConditions
					.refreshed(ExpectedConditions.attributeToBe(By.tagName("body"), "class", "yui-skin-cstudioTheme")));
		}
	}

	public void waitUntilDeleteSiteModalCloses() {
		logger.debug("Waiting for delete site dialog to close");
		this.waitForAnimation();
		if ((webBrowserProperty.toLowerCase().equalsIgnoreCase("edge"))
				|| (webBrowserProperty.toLowerCase().equalsIgnoreCase("ie"))) {
			new WebDriverWait(this.driver, defaultTimeOut).until(ExpectedConditions
					.refreshed(ExpectedConditions.attributeToBe(By.tagName("body"), "class", "iewarning")));
		} else {
			new WebDriverWait(this.driver, defaultTimeOut).until(
					ExpectedConditions.refreshed(ExpectedConditions.attributeToBe(By.tagName("body"), "class", "")));
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
			new WebDriverWait(this.driver, defaultTimeOut).until(
					ExpectedConditions.refreshed(ExpectedConditions.attributeToBe(By.tagName("body"), "class", "")));
		}
	}

	public void waitUntilFolderOpens(String selectorType, String selectorValue) {
		logger.debug("Waiting for folder to open: {}, {}", selectorType, selectorValue);
		waitUntilAttributeContains(selectorType, selectorValue, "class", "open");
	}

	public void waitUntilContentTooltipIsHidden() {
		logger.debug("Waiting for content tooltip is hidden");
		WebElement element = this.waitUntilElementIsPresent("xpath", ".//div[@id='acn-context-tooltipWrapper']");

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

	public WebElement driverWaitUntilElementIsPresentAndDisplayed(String typeOfSelector, String selectorValue) {
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
			Thread.sleep(300);
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
			logger.warn("Content page is not published yet, it does not have published icon on pages structure");
		}
	}

	public int goToFolderAndExecuteInitSiteScriptThroughCommandLine(String siteId) {
		String script;
		String shell;
		String folder;

		if (executionEnvironment.equalsIgnoreCase("unix")) {
			shell = "/bin/bash";
			script = "init-site.sh";
			folder = "../../crafter-delivery/bin";
			try {
				String[] command = { shell, script, siteId };

				ProcessBuilder processBuilder = new ProcessBuilder(command);

				processBuilder.directory(new File(folder));

				Process process = processBuilder.start();

				process.waitFor();

				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

				// Read the output from the command
				String output = "";
				String lineString = null;
				while ((lineString = bufferedReader.readLine()) != null) {
					output = output + lineString;
				}

				if (!(output.contains("{\"message\":\"Error from server at http://localhost:8695/solr: "
						+ "Core with name 'testsitefordeliverytest' already exists.\"}"))) {
					int occurencesOfCreatingSolrCore = StringUtils.countMatches(output, "Creating Solr Core");
					Assert.assertTrue((occurencesOfCreatingSolrCore == 1), "The init-site result was: " + output);
					int occurencesOfCreatingTarget = StringUtils.countMatches(output, "Creating Deployer Target");
					Assert.assertTrue((occurencesOfCreatingTarget == 1), "The init-site result was: " + output);
					int occurencesOfDone = StringUtils.countMatches(output, "Done");
					Assert.assertTrue((occurencesOfDone == 1), "The init-site result was: " + output);
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

			folder = System.getProperty("user.dir") + "\\..\\..\\crafter-delivery\\bin";

			try {

				ProcessBuilder processBuilder = new ProcessBuilder(command);

				processBuilder.directory(new File(folder));

				Process process = processBuilder.start();

				process.waitFor();

				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

				// Read the output from the command
				String output = "";
				String lineString = null;
				while ((lineString = bufferedReader.readLine()) != null) {
					output = output + lineString;
				}
				if (!(output.contains("java.io.IOException: Server returned HTTP response code: 500"
						+ " for URL: http://localhost:9080/crafter-search/api/2/admin/index/create"))) {
					int occurencesOfCreatingSolrCore = StringUtils.countMatches(output, "Creating Solr Core");
					Assert.assertTrue((occurencesOfCreatingSolrCore == 1), "The init-site result was: " + output);
					int occurencesOfCreatingTarget = StringUtils.countMatches(output, "Creating Deployer Target");
					Assert.assertTrue((occurencesOfCreatingTarget == 1), "The init-site result was: " + output);
					int occurencesOfDone = StringUtils.countMatches(output, "Done");
					Assert.assertTrue((occurencesOfDone == 1), "The init-site result was: " + output);
				}

				return process.exitValue();
			} catch (Exception exception) {
				exception.printStackTrace();
				return -1;
			}
		}

	}

	public void focusAndScrollDownToBottomInASection(String cssContainer, String cssSelectorValue) {
		if ((webBrowserProperty.toLowerCase().equalsIgnoreCase("edge"))
				|| (webBrowserProperty.toLowerCase().equalsIgnoreCase("ie"))) {
			((JavascriptExecutor) driver).executeScript(
					"$('" + cssContainer + "').scrollTop($('" + cssSelectorValue + "').last().offset().top);");

		}
	}

	public void focusAndScrollDownToMiddleInASection(String cssContainer, String cssSelectorValue) {
		if ((webBrowserProperty.toLowerCase().equalsIgnoreCase("edge"))
				|| (webBrowserProperty.toLowerCase().equalsIgnoreCase("ie"))) {
			((JavascriptExecutor) driver).executeScript(
					"$('" + cssContainer + "').scrollTop($('" + cssSelectorValue + ":first-child').height()*7);");

		}
	}

	public void scrollDownIntoSideBar() {
		WebElement siteConfigButton = this.driverWaitUntilElementIsPresentAndDisplayed("id", "admin-console");
		((JavascriptExecutor) this.driver).executeScript("arguments[0].scrollIntoView(true);", siteConfigButton);
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
		if (!(this.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", selectorValue).getAttribute("class")
				.contains("open")))
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
		WebElement element = this.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", elementLocator);
		element.click();
		(new Actions(this.driver)).moveToElement(element).sendKeys(Keys.chord(Keys.CONTROL, "A"))
				.sendKeys(Keys.chord(Keys.DELETE)).pause(100).sendKeys(Keys.chord(newTextValue)).perform();
		this.waitForAnimation();
	}
}