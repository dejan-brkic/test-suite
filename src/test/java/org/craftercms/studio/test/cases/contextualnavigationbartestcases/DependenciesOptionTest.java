package org.craftercms.studio.test.cases.contextualnavigationbartestcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.craftercms.studio.test.pages.HomePage;
import org.craftercms.studio.test.pages.LoginPage;
import org.craftercms.studio.test.pages.PreviewPage;
import org.craftercms.studio.test.utils.FilesLocations;
import org.craftercms.studio.test.utils.UIElementsPropertiesManager;
import org.craftercms.studio.test.utils.WebDriverManager;

/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class DependenciesOptionTest {

	WebDriver driver;

	private WebDriverManager driverManager;

	private LoginPage loginPage;

	private UIElementsPropertiesManager UIElementsPropertiesManager;

	private HomePage homePage;

	private PreviewPage previewPage;

	@BeforeClass
	public void beforeTest() {
		this.driverManager = new WebDriverManager();
		this.UIElementsPropertiesManager = new org.craftercms.studio.test.utils.UIElementsPropertiesManager(
				FilesLocations.UIELEMENTSPROPERTIESFILEPATH);
		this.loginPage = new LoginPage(driverManager, this.UIElementsPropertiesManager);
		this.homePage = new HomePage(driverManager, this.UIElementsPropertiesManager);
		this.previewPage = new PreviewPage(driverManager, this.UIElementsPropertiesManager);

	}

	@AfterClass
	public void afterTest() {
		driverManager.closeConnection();
	}

	@Test(priority = 0)

	public void dependenciesOptionTest() {

		// login to application

		loginPage.loginToCrafter("admin", "admin");

		// wait for element

		homePage.getDriverManager().driverWait(2000);

		// go to preview page
		homePage.goToPreviewPage();

		// wait for element is clickeable

		homePage.getDriverManager().driverWait(4000);
		// homePage.getDriverManager().driverWait();
		// Show site content panel
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed(2, "xpath",
				"/html/body/div[2]/div[1]/nav/div/div[2]/ul[1]/li/div/div[1]/a").click();
		// driverManager.getDriver().findElement(By.xpath("/html/body/div[2]/div[1]/nav/div/div[2]/ul[1]/li/div/div[1]/a"))
		// .click();

		// wait for element is clickeable

		homePage.getDriverManager().driverWait(1000);

		// expand pages folder

		previewPage.expandPagesTree();

		// wait for element is clickeable

		homePage.getDriverManager().driverWait(1000);

		// expand home content

		previewPage.expandHomeTree();

		// Select the content to view the history.
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed(2, "cssSelector", "#ygtvlabelel1").click();
		// driverManager.getDriver().findElement(By.cssSelector("#ygtvlabelel1")).click();

		// wait for element is clickeable

		homePage.getDriverManager().driverWait(3000);

		// click on history option

		previewPage.clickOnDependenciesOption();

		// wait for element is clickeable

		previewPage.getDriverManager().driverWait(3300);

		// Assert

		String historyPage = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed(2, "cssSelector", ".view-title").getText();
		// driverManager.getDriver().findElement(By.cssSelector(".view-title")).getText();
		Assert.assertEquals(historyPage, "Dependencies");

	}

}
