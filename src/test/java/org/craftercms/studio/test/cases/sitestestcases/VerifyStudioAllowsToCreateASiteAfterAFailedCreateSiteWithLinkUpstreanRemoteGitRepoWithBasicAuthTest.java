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

// Test Case Studio- Sites ID:18
public class VerifyStudioAllowsToCreateASiteAfterAFailedCreateSiteWithLinkUpstreanRemoteGitRepoWithBasicAuthTest
		extends StudioBaseTest {

	private String gitUserName;
	private String gitPassword;
	private String gitRepositoryURL;

	@Parameters({"testId", "remoteUrl", "remoteUsername", "remotePassword"})
	@BeforeMethod
	public void beforeTest(String testId,String remoteUrl, String remoteUsername, String remotePassword) {
		gitUserName = remoteUsername;
		gitPassword = remotePassword;
		gitRepositoryURL = remoteUrl;
	}

	@Parameters({"testId", "pathRawFile"})
	@Test()
	public void verifyStudioAllowsToCreateASiteAfterAFailedCreateSiteWithLinkUpstreamRemoteGitRepoWithBasicAuthTest(
			String testId, String pathRawFile) {
		loginPage.loginToCrafter();
		homePage.clickOnCreateSiteButton();
		createSitePage.selectEmptyBluePrintOption()
				.setSiteName(testId)
				.clickPushSiteToRemoteGitCheckbox()
				.setRepositoryURL(gitRepositoryURL + "incorrect")
				.selectGitRepoBasicAuthenticationType()
				.setRepositoryUserName(gitUserName)
				.setRepositoryUserPassword(gitPassword)
				.clickReview()
				.clickOnCreateButton();
		previewPage.clickSidebar()
				.clickAdminConsoleOption();
		siteConfigPage.clickRemoteRepositoriesOption()
				.checkThatRepositoriesListIsEmpty()
				.clickAddNewRepositoryButton()
				.addNewRepository("basic", "origin", gitRepositoryURL, gitUserName, gitPassword, "", "")
				.pushSiteChangesToRemoteRepo("")
				.checkThatRepositoriesListIsNotEmptyAndListContainsRepo("origin", gitRepositoryURL);
		getWebDriverManager().goToWebRepoUrlFile(gitRepositoryURL, pathRawFile);
		getWebDriverManager().clickRawGitRepoWeb(gitRepositoryURL);
		Assert.assertTrue(getWebDriverManager().isTextPresentPageSource(testId));
	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
