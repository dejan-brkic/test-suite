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
package org.craftercms.studio.test.cases.sitestestcases;

import org.craftercms.studio.test.cases.StudioBaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * 
 * @author luishernandez
 *
 */

// Test Case Studio- Sites ID:10
public class VerifyStudioAllowsToCreateSiteBasedOnBluePrintThenPushToRemoteGitRepoTest extends StudioBaseTest {

	private String userName;
	private String password;
	private String localRepoName;
	private String siteId;
	private String siteDropdownElementXPath;
	private String topNavSitesOption;

	@Parameters({"testId"})
	@BeforeMethod
	public void beforeTest(String testId) {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		localRepoName = constantsPropertiesManager.getSharedExecutionConstants()
				.getProperty("crafter.gitrepository.localrepositoryname");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		topNavSitesOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.preview.sitesoption");
		siteId =  testId + "remotebarerepositoryforpushlocal";
		this.setup();
	}

	public void step2() {
		homePage.clickOnCreateSiteButton();	}

	public void step3() {
		createSitePage.selectWebSiteEditorialBluePrintOption();
	}

	public void step4() {
		createSitePage.setSiteName(siteId);
	}

	public void step5() {
		createSitePage.clickAdditionalDeveloperOptions();
	}

	public void step6() {
		createSitePage.clickPushSiteToRemoteGitCheckbox();
	}

	public void step7() {
		createSitePage.setPushRepositoryName("origin");
	}

	public void step8() {
		createSitePage.setPushRepositoryURL(this.getWebDriverManager().getLocalBareRepoURL(localRepoName));
	}

	public void step10() {
		createSitePage.clickReviewAndCreate();
	}

	public void step11() {
		// Click on Create button
		createSitePage.clickOnCreateButton();
		Assert.assertTrue(this.getWebDriverManager()
				.waitUntilElementIsClickable("xpath", siteDropdownElementXPath)
				.isDisplayed());
	}

	public void step12() {
		this.getWebDriverManager().waitUntilElementIsClickable("xpath", topNavSitesOption).click();
	}

	public void step13() {
		homePage.clickOnCreateSiteButton();
	}

	public void step14() {
		createSitePage.selectWebSiteEditorialBluePrintOption();
	}

	public void step15(String siteId) {
		createSitePage.setSiteName(siteId);
	}

	public void step16() {
		createSitePage.clickAdditionalDeveloperOptions();
	}

	public void step17() {
		createSitePage.clickPushSiteToRemoteGitCheckbox();
	}

	public void step18() {
		createSitePage.setPushRepositoryName("origin");
	}

	public void step19() {
		createSitePage.setPushRepositoryURL(this.getWebDriverManager().getLocalBareRepoURL(localRepoName));
	}

	public void step21() {
		createSitePage.clickReviewAndCreate();
	}

	public void step22() {
		// Click on Create button
		createSitePage.clickOnCreateButton();
		previewPage.clickSidebar();
		previewPage.clickAdminConsoleOption();
		siteConfigPage.clickRemoteRepositoriesOption();
		siteConfigPage.checkThatRepositoriesListIsEmpty();
	}


	@Parameters({"testId"})
	@Test()
	public void verifyStudioAllowsToCreateSiteBasedOnBluePrintThenPushToRemoteGitRepoTest(String testId) {
		this.testScenario(testId);
	}

	public void testScenario(String siteId) {
		// login to application
		loginPage.loginToCrafter(userName, password);

		getWebDriverManager().waitUntilLoginCloses();

		// Step 2
		step2();

		// Step 3
		step3();

		// Step 4
		step4();

		// Step 5
		step5();

		// Step 6
		step6();

		// Step 7
		step7();

		// Step 8
		step8();

		// Step 10
		step10();

		// Step 11
		step11();

		// Step 12
		step12();

		// Step 13
		step13();

		// Step 14
		step14();

		// Step 15
		step15(siteId);

		// Step 16
		step16();

		// Step 17
		step17();

		// Step 18
		step18();

		// Step 19
		step19();

		// Step 20
		step21();

		// Step 22
		step22();

	}

	public void setup() {
		int exitCode = this.getWebDriverManager().goToFolderAndExecuteGitInitBareRepository(localRepoName);
		Assert.assertEquals(exitCode, 0, "Init bare repository process failed");
	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		int exitCode = this.getWebDriverManager().goToFolderAndExecuteDeleteBareRepositoryFolder(localRepoName);
		Assert.assertEquals(exitCode, 0, "Delete bare repository process failed");
		apiTestHelper.deleteSite(testId);
		apiTestHelper.deleteSite(siteId);
	}
}
