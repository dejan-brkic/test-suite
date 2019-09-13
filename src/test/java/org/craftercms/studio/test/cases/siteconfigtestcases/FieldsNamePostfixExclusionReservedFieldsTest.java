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
package org.craftercms.studio.test.cases.siteconfigtestcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.craftercms.studio.test.cases.StudioBaseTest;

public class FieldsNamePostfixExclusionReservedFieldsTest extends StudioBaseTest{

    private String userName;
    private String password;
    private String[] nameValuesToTest;

    @Parameters({"testId", "blueprint", "valuesToTest"})
    @BeforeMethod
    public void beforeTest(String testId, String blueprint, String valuesToTest) {
        apiTestHelper.createSite(testId, "", blueprint);
        userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
        password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
        nameValuesToTest =  valuesToTest.split(",");
    }

    @Parameters({"testId"})
    @Test()
    public void verifyThatStudioAllowsToAddAnInputControlToExistingContentTypeTest(String testId) {

        // login to application
        loginPage.loginToCrafter(userName, password);

        // go to preview page
        homePage.goToPreviewPage(testId);

        previewPage.clickSidebar()
                .clickAdminConsoleOption();

        siteConfigPage.selectEntryContentTypeFromAdminConsole();
        siteConfigPage.dragAndDropFormSection()
                .dragAndDropControls("Input", "Default Section Title");
        siteConfigPage.completeControlsFieldsBasics("TestTitle", "TestVariableName", "TestICEGroup",
                "TestDescription", "TestDefault");
        // Save the data
        siteConfigPage.saveDragAndDropProcess(false);

        String[]  notificationPostfix = siteConfigPage.getPostfixNotificationError();

        Assert.assertTrue(notificationPostfix[0].contains("Model fields require their respective data type postfix:"));
        Assert.assertEquals(notificationPostfix[1], "TestTitle" + ":");
        getWebDriverManager().clickButtonByText("OK");

        siteConfigPage.completeControlsFieldsBasics("TestTitle", "TestTitle_s", "TestICEGroup",
                "TestDescription", "TestDefault");

        for (String value : nameValuesToTest) {
            siteConfigPage.dragAndDropControls("Input", "Default Section Title");
            siteConfigPage.completeControlsFieldsBasics(value, value, value + "ICEGroup",
                    value + "Description", value + "Default");
            siteConfigPage.saveDragAndDropProcess(true);
        }
    }

    @Parameters({"testId"})
    @AfterMethod(alwaysRun = true)
    public void afterTest(String testId) {
        apiTestHelper.deleteSite(testId);
    }
}
