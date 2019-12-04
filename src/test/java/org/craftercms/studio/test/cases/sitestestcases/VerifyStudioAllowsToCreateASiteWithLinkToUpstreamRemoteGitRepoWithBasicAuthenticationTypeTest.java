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
 * @author yacdaniel
 *
 */
public class VerifyStudioAllowsToCreateASiteWithLinkToUpstreamRemoteGitRepoWithBasicAuthenticationTypeTest
		extends StudioBaseTest {

	private String gitRepoUrl;
	private String gitRepositoryUserName;
	private String gitRepositoryPassword;

	@Parameters({"remoteUrl", "remoteUsername", "remotePassword"})
	@BeforeMethod
	public void beforeTest(String remoteUrl, String remoteUsername, String remotePassword) {
		gitRepoUrl = remoteUrl;
		gitRepositoryUserName = remoteUsername;
		gitRepositoryPassword = remotePassword;
	}

	@Parameters({"testId", "pathRawFile", "expectValueRawFile"})
	@Test()
	public void verifyStudioAllowsToCreateASiteWithLinkToUpstreamRemoteGitRepoWithBasicAuthenticationTypeTest(
			String testId, String pathRawFile, String expectValueRawFile){
		loginPage.loginToCrafter();
		homePage.clickOnCreateSiteButton();

		createSitePage.selectWebSiteEditorialBluePrintOption()
				.setSiteName(testId)
				.clickPushSiteToRemoteGitCheckbox()
				.setRepositoryURL(gitRepoUrl)
				.selectGitRepoBasicAuthenticationType()
				.setRepositoryUserName(gitRepositoryUserName)
				.setRepositoryUserPassword(gitRepositoryPassword)
				.clickReview()
				.clickOnCreateButton();

		previewPage.clickSidebar();
		previewPage.clickAdminConsoleOption();
		siteConfigPage.clickRemoteRepositoriesOption();
		siteConfigPage.checkThatRepositoriesListIsNotEmptyAndListContainsRepo("origin", gitRepoUrl);
		getWebDriverManager().goToWebRepoUrlFile(gitRepoUrl, pathRawFile);
		getWebDriverManager().clickRawGitRepoWeb(gitRepoUrl);

		Assert.assertTrue(getWebDriverManager().isTextPresentPageSource(expectValueRawFile));
	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
