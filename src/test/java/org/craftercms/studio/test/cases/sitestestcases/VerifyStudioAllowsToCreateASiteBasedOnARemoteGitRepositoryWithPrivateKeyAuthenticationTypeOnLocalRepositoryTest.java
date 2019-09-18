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
import org.craftercms.studio.test.utils.FilesLocations;
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

// Test Case Studio- Sites ID:16
public class VerifyStudioAllowsToCreateASiteBasedOnARemoteGitRepositoryWithPrivateKeyAuthenticationTypeOnLocalRepositoryTest
		extends StudioBaseTest {

	private String userName;
	private String password;
	private String siteId;
	private String siteDropdownElementXPath;
	private String gitPrivateKey;
	private String localRepoName;
	private String gitRepoUrlForSSH;

	@Parameters({"testId"})
	@BeforeMethod
	public void beforeTest(String testId) {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		localRepoName = constantsPropertiesManager.getSharedExecutionConstants()
				.getProperty("crafter.gitrepository.localrepositoryname");
		gitPrivateKey = FilesLocations.PRIVATEKEYCONTENTFILEPATH;
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");

		this.setup();
		siteId = testId;
	}

	public void step2() {
		this.clickOnCreateSiteButton();
	}

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
		createSitePage.setPushRepositoryURL(gitRepoUrlForSSH);
	}

	public void step9() {
		createSitePage.selectPushGitRepoPrivateKeyAuthenticationType();
	}

	public void step10() {
		// Select website blueprint
		createSitePage.setPushRepositoryPrivateKey(gitPrivateKey);
	}

	public void step11() {

		createSitePage.clickReviewAndCreate();
		// Click on Create button

	}

	public void step12() {
		createSitePage.clickOnCreateButton();
		Assert.assertTrue(this.getWebDriverManager()
				.waitUntilElementIsClickable("xpath", siteDropdownElementXPath)
				.isDisplayed());
	}

	public void clickOnCreateSiteButton() {
		// Click on the create site button
		homePage.clickOnCreateSiteButton();
	}

	@Test()
	public void verifyStudioallowsToCreateASiteBasedOnARemoteGitRepositoryWithPrivateKeyAuthenticationTypeOnLocalRepositoryTest() {
		this.testScenario();
	}

	public void testScenario() {
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

		// Step9
		step9();

		// Step 10
		step10();
		
		// Step 11
		step11();

		step12();

	}

	public void setup() {
		int exitCode = this.getWebDriverManager().goToFolderAndExecuteGitInitBareRepository(localRepoName);
		Assert.assertEquals(exitCode, 0, "Init bare repository process failed");
		//Getting the repository url for local ssh
		gitRepoUrlForSSH = constantsPropertiesManager.getSharedExecutionConstants()
				.getProperty("crafter.gitrepository.localsshprefix") + getWebDriverManager().getLocalBareRepoURL(localRepoName);
	}

	public void deleteRepositoryFolder() {
		int exitCode = this.getWebDriverManager().goToFolderAndExecuteDeleteBareRepositoryFolder(localRepoName);
		Assert.assertEquals(exitCode, 0, "Delete bare repository process failed");
	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
		this.deleteRepositoryFolder();
	}
}
