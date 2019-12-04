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

	private String siteId;
	private String gitPrivateKey;
	private String gitRepoUrl;
	private String topNavSitesOption;
	private String siteIdFromGit;

	@Parameters({"testId", "remoteSshUrl"})
	@BeforeMethod
	public void beforeTest(String testId, String remoteSshUrl) {
		gitRepoUrl = remoteSshUrl;
		gitPrivateKey = FilesLocations.PRIVATEKEYCONTENTFILEPATH;
		topNavSitesOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.preview.sitesoption");
		siteId = testId + "pushremote";
		siteIdFromGit = testId + "fromremote";
	}

	@Test()
	public void verifyStudioAllowsToCreateASiteWithLinkToUpstreamRemoteGitRepoWithPrivateKeyAuthenticationTypeTest() {
		loginPage.loginToCrafter();
		homePage.clickOnCreateSiteButton();
		createSitePage.selectWebSiteEditorialBluePrintOption()
				.setSiteName(siteId)
				.clickPushSiteToRemoteGitCheckbox()
				.setRepositoryURL(gitRepoUrl)
				.selectGitRepoPrivateKeyAuthenticationType()
				.setRepositoryPrivateKey(gitPrivateKey)
				.clickReview()
				.clickOnCreateButton();
		previewPage.clickSidebar()
				.clickAdminConsoleOption();
		siteConfigPage.clickRemoteRepositoriesOption()
				.checkThatRepositoriesListIsNotEmptyAndListContainsRepo("origin", gitRepoUrl);
		getWebDriverManager().clickElement("xpath", topNavSitesOption);
		homePage.clickOnCreateSiteButton();
		createSitePage.selectRemoteGitRepositorySite()
				.setSiteName(siteIdFromGit)
				.setRepositoryURL(gitRepoUrl)
				.selectGitRepoPrivateKeyAuthenticationType()
				.setRepositoryPrivateKey(gitPrivateKey)
				.clickReview()
				.clickOnCreateButton();
		previewPage.clickAdminConsoleOption();
		siteConfigPage.clickRemoteRepositoriesOption()
				.checkThatRepositoriesListIsNotEmptyAndListContainsRepo("origin", gitRepoUrl);
	}

	@AfterMethod(alwaysRun = true)
	public void afterTest() {
		apiTestHelper.deleteSite(siteId);
		apiTestHelper.deleteSite(siteIdFromGit);
	}
}
