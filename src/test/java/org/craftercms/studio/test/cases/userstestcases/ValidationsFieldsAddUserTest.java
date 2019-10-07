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

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.craftercms.studio.test.cases.StudioBaseTest;

/**
 *
 * @author Yacdaniel Hurtado
 *
 */
public class ValidationsFieldsAddUserTest extends StudioBaseTest {

    private String chars32NewUserLimit;

    @BeforeMethod
    public void beforeTest() {
        chars32NewUserLimit = "123456789012345678901234567890123";
    }

    @Parameters({"testUser"})
    @Test()
    public void verifyThatStudioAllowsToCreateANewUser(String testUser) {
        loginPage.loginToCrafter();
        createSitePage.clickOnUsersOption();

        usersPage.clickOnNewUser();
        usersPage.setFirstName("")
                .clickSaveNewUserDisable();
        Assert.assertEquals(usersPage.getFirstNameValidationMsg(), "Name is required.",
                "First Name validation message is incorrect");

        usersPage.setFirstName(chars32NewUserLimit);
        Assert.assertEquals(usersPage.getFirstNameValidationMsg(), "First Name can't be longer than 32 characters",
                "First Name validation message is incorrect");

        usersPage.setFirstName("FN" + testUser)
                .setLastName("")
                .clickSaveNewUserDisable();
        Assert.assertEquals(usersPage.getLastNameValidationMsg(), "Last Name is required.",
                "Last Name validation message is incorrect");
        usersPage.setFirstName("FN" + testUser)
                .setLastName(chars32NewUserLimit);
        Assert.assertEquals(usersPage.getLastNameValidationMsg(), "Last Name can't be longer than 32 characters",
                "Last Name validation message is incorrect");

        usersPage.setFirstName("FN" + testUser)
                .setLastName("LN" + testUser)
                .setEmail("")
                .clickSaveNewUserDisable();
        Assert.assertEquals(usersPage.getEmailValidationMsg(), "Email is required.",
                "Email validation message is incorrect");

        usersPage.setFirstName("FN" + testUser)
                .setLastName("LN" + testUser)
                .setEmail(testUser)
                .clickSaveNewUserDisable();
        Assert.assertEquals(usersPage.getEmailValidationMsg(), "Enter a valid email.",
                "Email validation message is incorrect");

        usersPage.setFirstName("FN" + testUser)
                .setLastName("LN" + testUser)
                .setEmail(testUser + "@" + testUser)
                .setUserName("")
                .clickSaveNewUserDisable();
        Assert.assertEquals(usersPage.getUserNameValidationMsg(), "User Name is required.",
                "User Name validation message is incorrect");
        usersPage.setFirstName("FN" + testUser)
                .setLastName("LN" + testUser)
                .setEmail(testUser + "@" + testUser)
                .setUserName(chars32NewUserLimit);
        Assert.assertEquals(usersPage.getUserNameValidationMsg(), "User Name can't be longer than 32 characters",
                "User Name validation message is incorrect");

        usersPage.setFirstName("FN" + testUser)
                .setLastName("LN" + testUser)
                .setEmail(testUser + "@" + testUser)
                .setUserName(testUser)
                .setPassword("")
                .clickSaveNewUserDisable();
        Assert.assertEquals(usersPage.getPwdValidationMsg(), "Password is required.",
                "Password validation message is incorrect");

        usersPage.setFirstName("FN" + testUser)
                .setLastName("LN" + testUser)
                .setEmail(testUser + "@" + testUser)
                .setUserName(testUser)
                .setPassword(testUser)
                .setVerificationPassword("")
                .clickSaveNewUserDisable();
        Assert.assertEquals(usersPage.getPwdVerifyValidationMsg(), "Must match the previous entry.",
                "Password Verification validation message is incorrect");

        usersPage.setFirstName("FN" + testUser)
                .setLastName("LN" + testUser)
                .setEmail(testUser + "@" + testUser)
                .setUserName(testUser)
                .setPassword(testUser)
                .setVerificationPassword(testUser)
                .clickOnSaveNewUser();
        getWebDriverManager().waitUntilAddUserCreatedNotificationCloses(testUser);
    }

    @Parameters({"testUser"})
    @AfterMethod(alwaysRun = true)
    public void afterTest(String testUser) {
        apiTestHelper.deleteUser(testUser);
    }
}
