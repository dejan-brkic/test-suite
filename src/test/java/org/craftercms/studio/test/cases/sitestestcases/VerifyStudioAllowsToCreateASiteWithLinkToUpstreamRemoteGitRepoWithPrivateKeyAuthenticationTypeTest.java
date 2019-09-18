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

// Test Case Studio- Sites ID:17
public class VerifyStudioAllowsToCreateASiteWithLinkToUpstreamRemoteGitRepoWithPrivateKeyAuthenticationTypeTest
		extends StudioBaseTest {

	private String userName;
	private String password;
	private String siteId;
	private String siteDropdownElementXPath;
	private String gitPrivateKey;
	private String gitRepoUrl;
	private String topNavSitesOption;
	private String siteIdFromGit;

	@Parameters({"testId", "remoteSshUrl"})
	@BeforeMethod
	public void beforeTest(String testId, String remoteSshUrl) {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		gitRepoUrl = remoteSshUrl;
		gitPrivateKey = FilesLocations.PRIVATEKEYCONTENTFILEPATH;
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		topNavSitesOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.preview.sitesoption");
		siteId = testId + "pushremote";
		siteIdFromGit = testId + "fromremote";
	}

	public void step2() {
		homePage.clickOnCreateSiteButton();
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
		createSitePage.setPushRepositoryURL(gitRepoUrl);
	}

	public void step9() {
		createSitePage.selectPushGitRepoPrivateKeyAuthenticationType();
	}

	public void step10() {
		createSitePage.setPushRepositoryPrivateKey(gitPrivateKey);
	}

	public void step11() {
		createSitePage.clickReviewAndCreate();
	}

	public void step12() {
		// Click on Create button
		createSitePage.clickOnCreateButton();
		Assert.assertTrue(this.getWebDriverManager()
				.waitUntilElementIsClickable("xpath", siteDropdownElementXPath)
				.isDisplayed());
		previewPage.clickSidebar();
		previewPage.clickAdminConsoleOption();
		siteConfigPage.clickRemoteRepositoriesOption();
		siteConfigPage.checkThatRepositoriesListIsNotEmptyAndListContainsRepo("origin", gitRepoUrl);
	}

	public void step13() {
		this.getWebDriverManager().waitUntilElementIsClickable("xpath", topNavSitesOption)
				.click();
	}

	public void step14() {
		homePage.clickOnCreateSiteButton();
	}

	public void step15() {
		createSitePage.clickUseRemoteGitRepoSiteCheckbox();
	}

	public void step16() {
		createSitePage.clickBasicInformation();
	}

	public void step17() {
		createSitePage.setSiteName(siteIdFromGit);
	}

	public void step18() {
		createSitePage.clickBasicDeveloperOptions();
	}

	public void step19() {
		createSitePage.setFromGitRepositoryName("origin");
	}

	public void step20() {
		createSitePage.setFromGitRepositoryURL(gitRepoUrl);
	}

	public void step21() {
		createSitePage.selectFromGitRepoPrivateKeyAuthenticationType();
	}

	public void step22() {
		createSitePage.setFromGitRepositoryPrivateKey(gitPrivateKey);
	}

	public void step23() {
		createSitePage.clickReviewAndCreate();
	}

	public void step24() {
		createSitePage.clickOnCreateButton();
		Assert.assertTrue(this.getWebDriverManager()
				.waitUntilElementIsClickable("xpath", siteDropdownElementXPath)
				.isDisplayed());
		previewPage.clickAdminConsoleOption();
		siteConfigPage.clickRemoteRepositoriesOption();
		siteConfigPage.checkThatRepositoriesListIsNotEmptyAndListContainsRepo("origin", gitRepoUrl);
	}

	@Test()
	public void verifyStudioAllowsToCreateASiteWithLinkToUpstreamRemoteGitRepoWithPrivateKeyAuthenticationTypeTest() {
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

		// Step 12
		step12();

		// Step 13
		step13();

		// Step 14
		step14();

		// Step 15
		step15();

		// Step 16
		step16();

		// Step 17
		step17();

		// Step18
		step18();

		// Step 19
		step19();

		// Step 20
		step20();

		// Step 21
		step21();

		// Step 22
		step22();

		// Step 23
		step23();

		// Step 24
		step24();
	}

	@AfterMethod(alwaysRun = true)
	public void afterTest() {
		apiTestHelper.deleteSite(siteId);
		apiTestHelper.deleteSite(siteIdFromGit);
	}
}
