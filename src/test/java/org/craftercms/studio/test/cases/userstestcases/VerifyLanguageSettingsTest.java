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
package org.craftercms.studio.test.cases.userstestcases;

import org.craftercms.studio.test.cases.StudioBaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class VerifyLanguageSettingsTest extends StudioBaseTest {

    private String userName;
    private String password;
    private String defaultAdminLanguage;

    @Parameters({"testUser1", "testUser2"})
    @BeforeMethod
    public void beforeTest(String testUser1, String testUser2) {
        apiTestHelper.createUser(testUser1);
        apiTestHelper.createUser(testUser2);
        userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
        password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
        defaultAdminLanguage = "English";
    }

    @Parameters({"testUser1", "testUser2", "langUser1", "langUser2"})
    @Test
    public void verifyLanguageSettingsTest(String testUser1, String testUser2, String langUser1, String langUser2) {
        Assert.assertEquals(loginPage.getSelectedLanguage(), defaultAdminLanguage);
        loginPage.setLanguage(langUser1);
        loginPage.loginToCrafter(testUser1, testUser1);
        createSitePage.clickAdmin();
        createSitePage.clickOnSettingsOption();
        Assert.assertEquals(loginPage.getSelectedLanguage(), langUser1);
        homePage.clickLogoutOutCrafter();

        Assert.assertEquals(loginPage.getSelectedLanguage(), langUser1);
        loginPage.setLanguage(langUser2);
        loginPage.loginToCrafter(testUser2, testUser2);
        createSitePage.clickAdmin();
        createSitePage.clickOnSettingsOption();
        Assert.assertEquals(loginPage.getSelectedLanguage(), langUser2);
        homePage.clickLogoutOutCrafter();

        loginPage.setLanguage(defaultAdminLanguage);
        loginPage.loginToCrafter(userName, password);
        createSitePage.clickAdmin();
        createSitePage.clickOnSettingsOption();
        Assert.assertEquals(loginPage.getSelectedLanguage(), defaultAdminLanguage);
        homePage.clickLogoutOutCrafter();

        loginPage.setUserName(testUser1);
        Assert.assertEquals(loginPage.getSelectedLanguage(), langUser1);
        loginPage.setUserName(testUser2);
        Assert.assertEquals(loginPage.getSelectedLanguage(), langUser2);
        loginPage.setUserName(userName);
        Assert.assertEquals(loginPage.getSelectedLanguage(), defaultAdminLanguage);
    }

    @Parameters({"testUser1", "testUser2"})
    @AfterMethod(alwaysRun = true)
    public void afterTest(String testUser1, String testUser2) {
        apiTestHelper.deleteSite(testUser1);
        apiTestHelper.deleteUser(testUser2);
    }
}
