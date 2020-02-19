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

	private String localRepoName;
	private String siteId;
	private String topNavSitesOption;

	@Parameters({"testId"})
	@BeforeMethod
	public void beforeTest(String testId) {
		localRepoName = constantsPropertiesManager.getSharedExecutionConstants()
				.getProperty("crafter.gitrepository.localrepositoryname");
		topNavSitesOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.preview.sitesoption");
		siteId =  testId + "remotebarerepositoryforpushlocal";
		this.setup();
	}

	@Parameters({"testId"})
	@Test()
	public void verifyStudioAllowsToCreateSiteBasedOnBluePrintThenPushToRemoteGitRepoTest(String testId) {
		loginPage.loginToCrafter();
		homePage.clickOnCreateSiteButton();
		createSitePage.selectWebSiteEditorialBluePrintOption()
				.setSiteName(siteId)
				.clickPushSiteToRemoteGitCheckbox()
				.setRepositoryURL(this.getWebDriverManager().getLocalBareRepoURL(localRepoName))
				.clickReview()
				.clickOnCreateButton();
		previewPage.clickSidebar();
		previewPage.clickAdminConsoleOption();
		siteConfigPage.clickRemoteRepositoriesOption();
		siteConfigPage.checkThatRepositoriesListIsNotEmptyAndListContainsRepo("origin", localRepoName);

		getWebDriverManager().clickElement("xpath", topNavSitesOption);

		homePage.clickOnCreateSiteButton();
		createSitePage.selectWebSiteEditorialBluePrintOption()
				.setSiteName(testId)
				.clickPushSiteToRemoteGitCheckbox()
				.setRepositoryURL(this.getWebDriverManager().getLocalBareRepoURL(localRepoName))
				.clickReview()
				.clickOnCreateButton();
		previewPage.clickAdminConsoleOption();
		siteConfigPage.clickRemoteRepositoriesOption();
		siteConfigPage.checkThatRepositoriesListIsEmpty();
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
