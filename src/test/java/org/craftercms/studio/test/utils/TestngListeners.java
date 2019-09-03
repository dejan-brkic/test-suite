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
package org.craftercms.studio.test.utils;

import org.craftercms.studio.test.cases.DeliveryBaseTest;
import org.craftercms.studio.test.cases.StudioBaseTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestngListeners implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        //implementation not needed
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        //implementation not needed
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Object currentClass = result.getInstance();
        if (!currentClass.getClass().toString().contains("API")){
            if (currentClass.getClass().toString().contains("delivery.test")){
                ((DeliveryBaseTest) currentClass).getWebDriverManager().takeScreenshot(result.getInstanceName());
            }
            else {
                ((StudioBaseTest) currentClass).getWebDriverManager().takeScreenshot(result.getInstanceName());
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        //implementation not needed
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        //implementation not needed
    }

    @Override
    public void onStart(ITestContext context) {
        //implementation not needed
    }

    @Override
    public void onFinish(ITestContext context) {
        //implementation not needed
    }
}