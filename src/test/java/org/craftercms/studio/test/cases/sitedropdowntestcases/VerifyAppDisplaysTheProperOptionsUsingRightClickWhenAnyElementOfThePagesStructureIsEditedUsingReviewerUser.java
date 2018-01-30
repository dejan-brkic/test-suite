package org.craftercms.studio.test.cases.sitedropdowntestcases;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.cases.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

/**
 * 
 * 
 * @author Juan Camacho A
 *
 */
// Test Case ID:107
public class VerifyAppDisplaysTheProperOptionsUsingRightClickWhenAnyElementOfThePagesStructureIsEditedUsingReviewerUser
		extends BaseTest {

	private String userName;
	private String password;
	private String siteDropdownElementXPath;
	private String pagesTreeLink;
	private String pagesTree;
	private String homeContent;
	private String createFormFrameElementCss;
	private String homePageEditedName;
	private String homePageEditedExpand;
	private String menStylesEditedPageName;
	private String menStylesForWinterEditedPageName;
	private String rightClickOptions;
	
	private String newUserFirstNameId;
	private String newUserLastNameId;
	private String newUserEmailId;
	private String newUserUserNameId;
	private String newUserPasswordId;
	private String newUserPasswordVerificationId;
	private String newUserUserNameCreatedXpath;
	private String crafterLogo;
	private String expandPagesTree;
	private String createSiteButton;
	private String adminConsole;
	private String siteconfigGroupsOption;
	private String addTouserIframe;
	private String editReviewerGroupOption;
	private String groupsAddNewMembersCheckbox;
	private String groupsAddNewMembersInput;
	private String groupsAddNewMembersAutocompleteOption1;
	private String groupsAddNewMembersButton;
	private String navigationSitebarNameId;
	private String userOptions;
	private String userOptionsLogout;

	private String rightclickViewOption;
	private String rightclickCopyOption;
	private String rightclickApproveAndPublishOption;
	private String rightclickDependenciesOption;
	private String styleLandingpage;
	private String articlesFolder;
	private String articlesFolder2017;
	private String articlesFolder1;
	private String articlesFolderMenStylesForWinter;
	private LinkedList<String> rightClickOptionsListInHomePage;
	private LinkedList<String> rightClickOptionsListInCategoryLandingPage;
	private LinkedList<String> rightClickOptionsListInMenStylesForWinterPage;
	private static Logger logger = LogManager.getLogger(
			VerifyAppDisplaysTheProperOptionsUsingRightClickWhenAnyElementOfThePagesStructureIsEditedUsingReviewerUser.class);

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdownmenuinnerxpath");
		pagesTreeLink = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sitecontent.expandpages");
		pagesTree = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.expand_Pages_Tree");
		homeContent = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.home_Content_Page");
		homePageEditedName = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.new_home_Content_Page_name_edited");
		menStylesEditedPageName = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.pagestree.style.landingpage.edited");
		homePageEditedExpand = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.new_home_Content_Page_name_edited_expand");
		menStylesForWinterEditedPageName = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.pagestree.articlesfolder.menstylesforwinter.page.edited");
		
		rightclickViewOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.view.option");
		rightclickCopyOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.copy.option");
		rightclickApproveAndPublishOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.approveandpublish.option");
		rightclickDependenciesOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.dependenciesoption");
		styleLandingpage = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.pagestree.style.landingpage");
		articlesFolder = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.articlesfolder");
		articlesFolder2017 = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.articles.folder.2017");
		articlesFolder1 = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.articles.folder.2017.1");
		articlesFolderMenStylesForWinter = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.articles.folder.2017.1.menstylesforwinter");
		createFormFrameElementCss = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.createformframe");
		rightClickOptions = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("rightclick.list.all.options");
		newUserFirstNameId = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.firstname");
		newUserLastNameId = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.lastname");
		newUserEmailId = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("general.users.email");
		newUserUserNameId = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.username");
		newUserPasswordId = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.password");
		newUserPasswordVerificationId = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.passwordVerification");
		newUserUserNameCreatedXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.reviewerusernamecreated");
		crafterLogo = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("users.crafterlogo");
		expandPagesTree = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.expand_Pages_Tree");
		adminConsole = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("general.adminconsole");
		siteconfigGroupsOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.groups_option");
		createSiteButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsitebutton");
		addTouserIframe = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.adduser.iframe");
		editReviewerGroupOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("groups.edit_reviewer_group_option");
		groupsAddNewMembersCheckbox = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("groups.add_new_members_checkbox");
		groupsAddNewMembersInput = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("groups.add_new_members_input");
		groupsAddNewMembersAutocompleteOption1 = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("groups.add_new_members_autocomplete_option1");
		groupsAddNewMembersButton = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("groups.add_new_members_button");
		navigationSitebarNameId = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.navigation_sitebar_name_id");
		userOptions = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("dashboard.user_options");
		userOptionsLogout = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("dashboard.user_options_logout");

	}

	public void rightClickHomePage() {
		this.driverManager.waitUntilPageLoad();
		this.driverManager.waitUntilSidebarOpens();

		this.driverManager.waitUntilFolderOpens("xpath", pagesTree);
		this.driverManager.waitForAnimation();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", homePageEditedName);
		dashboardPage.getDriverManager().contextClick("xpath", homePageEditedName, false);

	}

	public void rightClickCategoryLandingPage() {
		this.driverManager.waitUntilPageLoad();
		this.driverManager.waitUntilSidebarOpens();
		this.driverManager.waitForAnimation();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", menStylesEditedPageName);
		dashboardPage.getDriverManager().contextClick("xpath", menStylesEditedPageName, false);
		this.driverManager.waitForAnimation();
	}

	public void rightClickArticlesFolderMenStylesForWinter() {
		this.driverManager.waitUntilPageLoad();
		this.driverManager.waitUntilSidebarOpens();
		this.driverManager.waitForAnimation();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				menStylesForWinterEditedPageName);
		dashboardPage.getDriverManager().contextClick("xpath", menStylesForWinterEditedPageName, false);
		this.driverManager.waitForAnimation();
	}

	public void verifyViewOptionIsPresent(String section) {
		WebElement rightclickViewOptionElement = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				rightclickViewOption);
		Assert.assertTrue(rightclickViewOptionElement.isDisplayed(),
				"ERROR: Right click View Option is not present on right click of " + section);
	}

	public void verifyCopyOptionIsPresent(String section) {
		WebElement rightclickCopyOptionElement = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				rightclickCopyOption);
		Assert.assertTrue(rightclickCopyOptionElement.isDisplayed(),
				"ERROR: Right click Copy Option is not present on right click of " + section);
	}

	public void verifyApproveAndPublishOptionIsPresent(String section) {
		WebElement rightclickApproveAndPublishOptionElement = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", rightclickApproveAndPublishOption);
		Assert.assertTrue(rightclickApproveAndPublishOptionElement.isDisplayed(),
				"ERROR: Right click Approve and Publish Option is not present on right click of " + section);
	}

	public void verifyDependenciesOptionIsPresent(String section) {
		WebElement rightclickDependenciesOptionElement = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", rightclickDependenciesOption);
		Assert.assertTrue(rightclickDependenciesOptionElement.isDisplayed(),
				"ERROR: Right click Dependencies Option is not present on right click of " + section);
	}

	public void step4() {
		// Step 4 Right Right click on "Home" and verify options
		this.rightClickHomePage();

		driverManager.usingContextMenu(() -> {
			logger.info("Checking that only the expected options are listed");
			rightClickOptionsListInHomePage = new LinkedList<String>();
			
			rightClickOptionsListInHomePage.add(0, "View");
			rightClickOptionsListInHomePage.add(1, "Copy");
			rightClickOptionsListInHomePage.add(2, "Approve & Publish");
			rightClickOptionsListInHomePage.add(3, "Dependencies");

			List<WebElement> rightClickOptionsList = this.driverManager.getDriver()
					.findElements(By.xpath(rightClickOptions));
			int currentIndex = 0;
			for (WebElement element : rightClickOptionsList) {
				this.driverManager.waitForAnimation();
				this.driverManager.waitUntilSidebarOpens();
				Assert.assertTrue(element.getText().equals(rightClickOptionsListInHomePage.get(currentIndex)),
						"ERROR: Link Option: " + element.getText()
								+ " is not in the correct order in the HomePage, check that the correct options are listed");
				currentIndex++;
			}

			String section = "Step 4 Right Click on 'Home'";

			verifyViewOptionIsPresent(section);
			verifyCopyOptionIsPresent(section);
			verifyApproveAndPublishOptionIsPresent(section);
			verifyDependenciesOptionIsPresent(section);
			
			this.driverManager.getDriver().navigate().refresh();
			this.driverManager.waitUntilSidebarOpens();
			this.driverManager.waitForAnimation();
			
	
		});
	}

	public void step6() {
		// Step 6 Right click on any Category Landing page and verify options
		this.rightClickCategoryLandingPage();
		
		driverManager.usingContextMenu(() -> {
			logger.info("Checking that only the expected options are listed");
			rightClickOptionsListInCategoryLandingPage = new LinkedList<String>();
			
			rightClickOptionsListInCategoryLandingPage.add(0, "View");
			rightClickOptionsListInCategoryLandingPage.add(1, "Copy");
			rightClickOptionsListInCategoryLandingPage.add(2, "Approve & Publish");
			rightClickOptionsListInCategoryLandingPage.add(3, "Dependencies");
		

			List<WebElement> rightClickOptionsList = this.driverManager.getDriver()
					.findElements(By.xpath(rightClickOptions));
			int currentIndex = 0;
			for (WebElement element : rightClickOptionsList) {
				this.driverManager.waitForAnimation();
				this.driverManager.waitUntilSidebarOpens();
				Assert.assertTrue(
						element.getText().equals(rightClickOptionsListInCategoryLandingPage.get(currentIndex)),
						"ERROR: Link Option: " + element.getText()
								+ " is not in the correct order in the selected category landing page, check that the correct options are listed");
				currentIndex++;
			}

			String section = "Step 6 Right click on a 'Category Landing' page";

			verifyViewOptionIsPresent(section);
			verifyCopyOptionIsPresent(section);
			verifyApproveAndPublishOptionIsPresent(section);
			verifyDependenciesOptionIsPresent(section);

			this.driverManager.getDriver().navigate().refresh();
			this.driverManager.waitUntilSidebarOpens();
			this.driverManager.waitForAnimation();
		});
	}

	public void step8() {
		// Step 8 Click on the + of folder 2017
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articlesFolder2017);

		this.driverManager.waitUntilContentTooltipIsHidden();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articlesFolder2017).click();
		this.driverManager.waitUntilFolderOpens("xpath", articlesFolder2017);
	}

	public void step10() {
		// Step 10 Right click on any of the article (Men Styles For Winter)
		this.driverManager.waitForAnimation();

		this.rightClickArticlesFolderMenStylesForWinter();

		driverManager.usingContextMenu(() -> {
			
			logger.info("Checking that only the expected options are listed");
			rightClickOptionsListInMenStylesForWinterPage = new LinkedList<String>();

			rightClickOptionsListInMenStylesForWinterPage.add(0, "View");
			rightClickOptionsListInMenStylesForWinterPage.add(1, "Copy");
			rightClickOptionsListInMenStylesForWinterPage.add(2, "Approve & Publish");
			rightClickOptionsListInMenStylesForWinterPage.add(3, "Dependencies");

			List<WebElement> rightClickOptionsList = this.driverManager.getDriver()
					.findElements(By.xpath(rightClickOptions));
			int currentIndex = 0;
			for (WebElement element : rightClickOptionsList) {
				this.driverManager.waitForAnimation();
				this.driverManager.waitUntilSidebarOpens();
				Assert.assertTrue(
						element.getText().equals(rightClickOptionsListInMenStylesForWinterPage.get(currentIndex)),
						"ERROR: Link Option: " + element.getText()
								+ " is not in the correct order in the Men Styles For Winter page, check that the correct options are listed");
				currentIndex++;
			}

			String section = "Step 11 Right click on folder articles -2017- 1- Men Styles For Winter;";

			verifyViewOptionIsPresent(section);
			verifyCopyOptionIsPresent(section);
			verifyApproveAndPublishOptionIsPresent(section);
			verifyDependenciesOptionIsPresent(section);
			
			this.driverManager.getDriver().navigate().refresh();
		});

	}

	public void editHomePage() {
		// Edit the Home Page and Save
		logger.info("Edit the Home Page and Save");
		dashboardPage.rightClickEditOnAPresentPage(homeContent);

		driverManager.usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {

			// creating random values for URL field and InternalName field

			String randomInternalName = "HomePageEdited";

			// Set basics fields of the new content created

			dashboardPage.editInternalName(randomInternalName);
		});

		this.driverManager.waitUntilSidebarOpens();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("Xpath", homePageEditedName);

	}

	public void editLandingPageStylePage() {
		
		logger.info("Edit the Styles Page and Save");
		dashboardPage.rightClickEditOnAPresentPage(styleLandingpage);

		driverManager.usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {

			// creating random values for URL field and InternalName field

			String randomInternalName = "StylePageEdited";

			// Set basics fields of the new content created

			dashboardPage.editInternalName(randomInternalName);
		});

		this.driverManager.waitUntilSidebarOpens();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("Xpath", menStylesEditedPageName);

	}

	public void editMenStylesForWinterPage() {
		
		logger.info("Edit the Men Styles For Winter Page and Save");
		
		dashboardPage.rightClickEditOnAPresentPage(articlesFolderMenStylesForWinter);

		driverManager.usingCrafterForm("cssSelector", createFormFrameElementCss, () -> {

			// creating random values for URL field and InternalName field

			String randomInternalName = "MenStylesForWinterEdited";

			// Set basics fields of the new content created

			dashboardPage.editInternalName(randomInternalName);
		});

		this.driverManager.waitUntilSidebarOpens();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("Xpath", menStylesForWinterEditedPageName);

	}

	public void expandHomeTree() {
		this.driverManager.waitUntilSidebarOpens();
		this.driverManager.waitForAnimation();

		// Verify if the home tree is already expanded
		if (!(this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", homePageEditedName)
				.getAttribute("class").contains("open"))) {
			this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", homePageEditedExpand)
					.click();
		}

	}
	
	public void login(String user, String loginpassword) {

		// login to application
		loginPage.loginToCrafter(user, loginpassword);

		// Wait for login page to close
		driverManager.waitUntilLoginCloses();
	}
	
	public void addNewUser() {

		// click On Users option

		createSitePage.clickOnUsersOption();

		// click on new user button

		usersPage.clickOnNewUser();

		// Follow the form
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", newUserFirstNameId).sendKeys("reviewer");

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", newUserLastNameId)
				.sendKeys("Last Name");

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", newUserEmailId)
				.sendKeys("email@email.com");

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", newUserUserNameId).sendKeys("reviewer");

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", newUserPasswordId).sendKeys("reviewer");

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", newUserPasswordVerificationId)
				.sendKeys("reviewer");

		// Save Button
		usersPage.clickOnSaveNewUser();

		// Assert new users created is present
		WebElement newUserCreated = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				newUserUserNameCreatedXpath);

		Assert.assertTrue(newUserCreated.isDisplayed(), "ERROR: Recently created user is not displayed");

		// Switch to the form

		driverManager.getDriver().navigate().refresh();

		driverManager.getDriver().switchTo().defaultContent();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",

				crafterLogo);

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",

				crafterLogo).click();
	}
	
	private void goToSiteContentPagesStructure() {

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				createSiteButton);
		
		this.driverManager.waitForAnimation();
		homePage.goToPreviewPage();

		if (this.driverManager.isElementPresentByXpath(siteDropdownElementXPath))
			this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", siteDropdownElementXPath).click();
		else
			throw new NoSuchElementException(
					"Site creation process is taking too long time and the element was not found");
	}
	
	public void addUserToReviewerGroup() {

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", adminConsole);

		WebElement siteConfigButton = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				adminConsole);

		siteConfigButton.click();

		this.driverManager.waitForAnimation();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", siteconfigGroupsOption);

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", siteconfigGroupsOption)

				.click();

		driverManager.getDriver().switchTo().defaultContent();

		this.driverManager.getDriver().switchTo()

				.frame(this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", addTouserIframe));

		this.driverManager.isElementPresentAndClickableByXpath(addTouserIframe);

		this.driverManager.getDriver().switchTo().activeElement();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",

				editReviewerGroupOption);

		this.driverManager

				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", editReviewerGroupOption)

				.click();

		driverManager.getDriver().switchTo().defaultContent();

		this.driverManager.getDriver().switchTo()

				.frame(this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", addTouserIframe));

		this.driverManager.isElementPresentAndClickableByXpath(addTouserIframe);

		this.driverManager.getDriver().switchTo().activeElement();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", groupsAddNewMembersCheckbox);

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", groupsAddNewMembersCheckbox)

				.click();

		this.driverManager

				.driverWaitUntilElementIsPresentAndDisplayed("xpath", groupsAddNewMembersInput)

				.sendKeys("reviewer");

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",

				groupsAddNewMembersAutocompleteOption1);

		this.driverManager

				.driverWaitUntilElementIsPresentAndDisplayed("xpath",

						groupsAddNewMembersAutocompleteOption1)

				.click();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",

				groupsAddNewMembersButton);

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",

				groupsAddNewMembersButton).click();

		driverManager.getDriver().switchTo().defaultContent();

		this.driverManager.getDriver().switchTo().activeElement();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", navigationSitebarNameId);

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", navigationSitebarNameId)
				.click();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", adminConsole);
	}
	
	private void logoutFromCrafter() {

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", userOptions);

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", userOptions).click();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", userOptionsLogout);

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", userOptionsLogout).click();

	}

	@Test(priority = 0)
	public void verifyProperOptionDisplayedOnPagesStructureWithReviewerUser() {
	
		this.login(userName, password);

		logger.info("Adding New User");

		this.addNewUser();

		this.driverManager.getDriver().navigate().refresh();

		logger.info("Go to Site Preview");

		this.goToSiteContentPagesStructure();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", expandPagesTree);

		this.driverManager.waitUntilSidebarOpens();

		logger.info("Add previous created user to Reviewer Group");

		this.addUserToReviewerGroup();
		
		// Expand the site bar
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", siteDropdownElementXPath);
		
		Assert.assertTrue(this.driverManager.isElementPresentAndClickableByXpath(siteDropdownElementXPath));
		
		// Step 2 Expand the site bar Step No needed
		// Step 3 Click on Pages tree
		WebElement pagesTreeLinkElement = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				pagesTreeLink);
		pagesTreeLinkElement.click();
		this.driverManager.waitUntilFolderOpens("xpath", pagesTreeLink);
		
		this.editHomePage();
		
		logger.info("Click on the + of Home tree and Edit Home Page");
		this.expandHomeTree();
		
		this.editLandingPageStylePage();
		
		logger.info("Click on the + of Articles tree");
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articlesFolder);

		this.driverManager.waitUntilContentTooltipIsHidden();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articlesFolder).click();
		this.driverManager.waitUntilFolderOpens("xpath", articlesFolder);

		logger.info("Click on the + of folder 2017");
		this.step8();

		logger.info(
				"Click on the + of folder 1 and Edit then article page Men Styles For Winter under articles/2017/1");
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", articlesFolder1).click();
		this.driverManager.waitUntilFolderOpens("xpath", articlesFolder1);

		this.editMenStylesForWinterPage();
		
		// logout from Crafter
		logger.info("logout from Crafter");
		this.logoutFromCrafter();

		// login to application with reviewer user
		logger.info("login to application with reviewer user");
		loginPage.loginToCrafter("reviewer", "reviewer");

		logger.info("Go to Preview Page");
		this.homePage.goToPreviewPage();

		this.driverManager.waitForAnimation();

		logger.info("Verifications of Step 4 Right Right click on Home and verify options");
		this.step4();

		logger.info("Verifications of Step 6 Right click on any Category Landing page style and verify options");
		this.step6();

		logger.info("Verifications of Step 10 Right click on any of the article, using Men Styles For Winter and verify options");
		this.step10();
	}
}
