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
import org.craftercms.studio.test.utils.APITestHelper;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author yacdaniel
 *
 */
public class VerifyStudioAllowsToCreateASiteWithLinkToUpstreamRemoteGitRepoWithTokenAuthenticationTypeTest
		extends StudioBaseTest {

	private String userName;
	private String password;
	private String siteId;
	private String siteDropdownElementXPath;
	private String pushToBareRepoInput;
	private String gitRepoUrl;
	private String gitRepositoryUserName;
	private String gitRepositoryToken;

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		gitRepoUrl = constantsPropertiesManager.getSharedExecutionConstants()
				.getProperty("crafter.gitrepository.linkremote.createthenpush.tokenauth.url");
		gitRepositoryUserName = constantsPropertiesManager.getSharedExecutionConstants()
				.getProperty("crafter.gitrepository.username");
		gitRepositoryToken = constantsPropertiesManager.getSharedExecutionConstants()
				.getProperty("crafter.gitrepository.token");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		pushToBareRepoInput = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.repositorypushtoremotebare");
		siteId = "testingcreatesitelinkremotetoken";
	}

	@Test()
	public void verifyStudioAllowsToCreateASiteWithLinkToUpstreamRemoteGitRepoWithBasicAuthenticationTypeTest(){
		loginPage.loginToCrafter(userName, password);
		homePage.clickOnCreateSiteButton();
		createSitePage.setSiteId(siteId);
		homePage.clickOnLinkToUpstreamRemoteGitRepository();
		createSitePage.setRepositoryName("origin");
		createSitePage.setRepositoryURL(gitRepoUrl);
		createSitePage.selectGitRepoTokenAutheticationType();
		createSitePage.setRepositoryUserName(gitRepositoryUserName);
		createSitePage.setRepositoryToken(gitRepositoryToken);
		driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", pushToBareRepoInput)
				.click();
		createSitePage.selectWebSiteEditorialBluePrintOption();
		createSitePage.clickOnCreateSiteButton();
		driverManager.waitForAnimation();
		driverManager.waitUntilCreateSiteModalCloses();
		driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				siteDropdownElementXPath);
		Assert.assertTrue(driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", siteDropdownElementXPath)
				.isDisplayed());
	}

	@AfterMethod
	public void afterTest() {
		new APITestHelper().deleteSite(siteId);
	}

}
