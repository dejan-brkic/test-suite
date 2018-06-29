/*
 * Copyright (C) 2007-2018 Crafter Software Corporation. All Rights Reserved.
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
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author luishernandez
 *
 */

// Test Case Studio- Sites ID:15
public class VerifyStudioAllowsToCreateASiteWithLinkToUpstreamRemoteGitRepoWithPrivateKeyAuthenticationTypeTest
		extends StudioBaseTest {

	private String userName;
	private String password;
	private String siteId;
	private String siteDropdownElementXPath;
	private String gitPrivateKey;
	private String pushToBareRepoInput;
	private String gitRepoUrl;
	private String topNavSitesOption;
	private String basedOnRemoteRepoInput;
	private String siteIdFromGit;

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		gitRepoUrl = constantsPropertiesManager.getSharedExecutionConstants()
				.getProperty("crafter.gitrepository.remotegiturl");
		gitPrivateKey = FilesLocations.PRIVATEKEYCONTENTFILEPATH;
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");
		pushToBareRepoInput = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.repositorypushtoremotebare");
		basedOnRemoteRepoInput = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("home.createsite.repositorybasedonremotegitrepo");
		topNavSitesOption = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.preview.sitesoption");
		siteId = "testingtargetsiteforpushremotegit";
		siteIdFromGit = "testingtargetsitefromremotegit";
	}

	public void step2() {
		this.clickOnCreateSiteButton();
	}

	public void step3() {
		// Filling the name of site
		createSitePage.setSiteName(siteId);
	}

	public void step4() {
		this.homePage.clickOnLinkToUpstreamRemoteGitRepository();
	}

	public void step5() {
		createSitePage.setRepositoryName("origin");
	}

	public void step6() {
		createSitePage.setRepositoryURL(gitRepoUrl);
	}

	public void step7() {
		createSitePage.selectGitRepoPrivateKeyAutheticationType();
	}

	public void step8() {
		createSitePage.setRepositoryPrivateKey(
				driverManager.getPrivateKeyContentFromPrivateKeyTestFile(gitPrivateKey));
	}

	public void step9() {
		WebElement pushRemoteBareRepoInputElement = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", pushToBareRepoInput);
		pushRemoteBareRepoInputElement.click();
	}

	public void step10() {
		// Select website blueprint
		createSitePage.selectWebSiteEditorialBluePrintOption();
	}

	public void step11() {
		// Click on Create button
		createSitePage.clickOnCreateSiteButton();

		this.driverManager.waitForAnimation();
		this.driverManager.waitUntilCreateSiteModalCloses();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				siteDropdownElementXPath);

		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", siteDropdownElementXPath)
				.isDisplayed());
	}

	public void step12() {
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", topNavSitesOption)
				.click();
	}

	public void step13() {
		this.clickOnCreateSiteButton();
	}

	public void step14() {
		// Filling the name of site
		createSitePage.setSiteName(siteIdFromGit);
	}

	public void step15() {
		this.homePage.clickOnLinkToUpstreamRemoteGitRepository();
	}

	public void step16() {
		createSitePage.setRepositoryName("origin2");
	}

	public void step17() {
		createSitePage.setRepositoryURL(gitRepoUrl);
	}

	public void step18() {
		createSitePage.selectGitRepoPrivateKeyAutheticationType();
	}

	public void step19() {
		createSitePage.setRepositoryPrivateKey(
				driverManager.getPrivateKeyContentFromPrivateKeyTestFile(gitPrivateKey));
	}

	public void step20() {
		WebElement baseOnRepoInputElement = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", basedOnRemoteRepoInput);
		baseOnRepoInputElement.click();
	}

	public void step21() {
		// Click on Create button
		createSitePage.clickOnCreateSiteButton();

		this.driverManager.waitForAnimation();
		this.driverManager.waitUntilCreateSiteModalCloses();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath",
				siteDropdownElementXPath);

		Assert.assertTrue(this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", siteDropdownElementXPath)
				.isDisplayed());
	}

	public void clickOnCreateSiteButton() {
		// Click on the create site button
		homePage.clickOnCreateSiteButton();
	}

	@Test(
			priority = 0)
	public void verifyStudioAllowsToCreateASiteWithLinkToUpstreamRemoteGitRepoWithPrivateKeyAuthenticationTypeTest() {
		this.testScenario();
	}

	public void testScenario() {
		// login to application
		loginPage.loginToCrafter(userName, password);

		driverManager.waitUntilLoginCloses();

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
	}
}
