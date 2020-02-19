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

// Test Case Studio- Sites ID:14
public class VerifyStudioAllowsToCreateASiteBasedOnARemoteGitRepositoryWithTokenAuthenticationTypeTest
		extends StudioBaseTest {

	private String siteId;
	private String gitUsername;
	private String gitToken;
	private String gitRepositoryURL;

	@Parameters({"testId", "remoteUrl", "remoteUsername", "remoteToken"})
	@BeforeMethod
	public void beforeTest(String testId, String remoteUrl, String remoteUsername, String remoteToken) {
		gitRepositoryURL = remoteUrl;
		gitUsername = remoteUsername;
		gitToken = remoteToken;
		siteId = testId;
	}

	@Test()
	public void verifyStudioAllowsToCreateASiteBasedOnARemoteGitRepositoryWithTokenAuthenticationTypeTest() {
		loginPage.loginToCrafter();
		homePage.clickOnCreateSiteButton();
		createSitePage.selectRemoteGitRepositorySite()
				.setSiteName(siteId)
				.setRepositoryURL(gitRepositoryURL)
				.selectGitRepoTokenAuthenticationType()
				.setRepositoryUserName(gitUsername)
				.setRepositoryToken(gitToken)
				.clickReview()
				.clickOnCreateButton();
		previewPage.clickSidebar();
		previewPage.clickAdminConsoleOption();
		siteConfigPage.clickRemoteRepositoriesOption();
		siteConfigPage.checkThatRepositoriesListIsNotEmptyAndListContainsRepo("origin", gitRepositoryURL);
	}


	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
	}
}
