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

package org.craftercms.studio.test.cases.api2testcases;

import org.craftercms.studio.test.api.objects.SecurityAPI;
import org.craftercms.studio.test.api2.objects.MarketplaceAPI2;
import org.craftercms.studio.test.utils.*;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GetBlueprintsAPI2Test {

    private SecurityAPI securityAPI;
    private MarketplaceAPI2 marketplaceAPI2;

    public GetBlueprintsAPI2Test() {
        APIConnectionManager apiConnectionManager = new APIConnectionManager();
        JsonTester api = new JsonTester(apiConnectionManager.getProtocol(), apiConnectionManager.getHost(),
                apiConnectionManager.getPort());
        securityAPI = new SecurityAPI(api, apiConnectionManager);
        marketplaceAPI2 = new MarketplaceAPI2(api, apiConnectionManager);
    }

    @BeforeGroups(groups = {"GetMarketplace"})
    public void beforeTestGroup() {
        securityAPI.logInIntoStudioUsingAPICall();
    }

    @Test(priority = 1, groups = {"GetMarketplace"})
    public void getBlueprintsPlugin(){
        marketplaceAPI2.testGetBlueprintPlugin();
    }

    @Test(priority = 2, groups = {"GetMarketplace"})
    public void getBlueprintsNoExistingPlugin(){
        marketplaceAPI2.testGetBlueprintNoExistingPlugin();
    }

    @Test(priority = 3, groups = {"GetMarketplace"})
    public void getBlueprintsPluginByKeyword(){
        marketplaceAPI2.testGetBlueprintsByKeyword();
    }

    @Test(priority = 4, groups = {"GetMarketplace"})
    public void getBlueprintsPluginByMultipleKeyword(){
        marketplaceAPI2.testGetBlueprintsByMultiplesKeyword();
    }

    @Test(priority = 5, groups = {"GetMarketplace"})
    public void getBlueprintsPluginByOffsetLimit(){
        marketplaceAPI2.testGetBlueprintOffsetLimit();
    }

    @AfterGroups(groups={"GetMarketplace"})
    public void afterTest() {
        securityAPI.logOutFromStudioUsingAPICall();
    }

    @Test(alwaysRun = true, priority = 100)
    public void testGetAuditLogUnauthorized() {
        marketplaceAPI2.testGetBlueprintPluginUnauthorized();
    }

}
