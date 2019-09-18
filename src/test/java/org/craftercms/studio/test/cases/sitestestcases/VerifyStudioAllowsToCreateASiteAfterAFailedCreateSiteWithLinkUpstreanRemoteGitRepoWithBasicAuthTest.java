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

	private String userName;
	private String password;
	private String siteId;
	private String gitUserName;
	private String gitPassword;
	private String gitRepositoryURL;

	@Parameters({"testId", "remoteUrl", "remoteUsername", "remotePassword"})
	@BeforeMethod
	public void beforeTest(String testId,String remoteUrl, String remoteUsername, String remotePassword) {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		gitUserName = remoteUsername;
		gitPassword = remotePassword;
		gitRepositoryURL = remoteUrl;
		siteId =  testId + "targetforbasicauth";
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
	    createSitePage.setPushRepositoryURL(gitRepositoryURL+"incorrect");
    }

	public void step9() {
	    createSitePage.selectPushGitRepoBasicAuthenticationType()
                .setPushRepositoryUserName(gitUserName)
                .setPushRepositoryUserPassword(gitPassword);
	}

	public void step10() {
		createSitePage.clickReviewAndCreate();
	}

	public void step11() {
		// Click on Create button
		createSitePage.clickOnCreateButton();
	}

	public void step12() {
		previewPage.clickSidebar();
		previewPage.clickAdminConsoleOption();
	}

	public void step13() {
		siteConfigPage.clickRemoteRepositoriesOption();
		siteConfigPage.checkThatRepositoriesListIsEmpty();
	}

    public void step14() {
        siteConfigPage.clickAddNewRepositoryButton();
    }

    public void step15() {
       siteConfigPage.addNewRepository("basic", "origin", gitRepositoryURL, gitUserName, gitPassword, "", "");
       siteConfigPage.pushSiteChangesToRemoteRepo("");
    }

    public void step18() {
        siteConfigPage.checkThatRepositoriesListIsNotEmptyAndListContainsRepo("origin", gitRepositoryURL);
    }

	public void clickOnCreateSiteButton() {
		// Click on the create site button
		homePage.clickOnCreateSiteButton();
	}

	@Test(priority = 0)
	public void verifyStudioAllowsToCreateASiteAfterAFailedCreateSiteWithLinkUpstreanRemoteGitRepoWithBasicAuthTest() {
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
		
		//Step 11
		step11();
		
		//Step 12
		step12();
		
		//Step 13
		step13();
		
		//Step 14
		step14();
		
		//Step 15
		step15();
		
		//Step 18
		step18();
	}

	@Parameters({"testId"})
	@AfterMethod(alwaysRun = true)
	public void afterTest(String testId) {
		apiTestHelper.deleteSite(testId);
		apiTestHelper.deleteSite(siteId);
	}
}
