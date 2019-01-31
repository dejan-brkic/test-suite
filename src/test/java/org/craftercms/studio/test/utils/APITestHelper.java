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

import org.craftercms.studio.test.api.objects.SecurityAPI;
import org.craftercms.studio.test.api.objects.SiteManagementAPI;

/**
 *
 * @author yacdaniel
 *
 */
public class APITestHelper {

    private SecurityAPI securityAPI;
    private SiteManagementAPI siteManagementAPI;

    public APITestHelper(){
        APIConnectionManager apiConnectionManager = new APIConnectionManager();
        JsonTester api = new JsonTester(apiConnectionManager.getProtocol(), apiConnectionManager.getHost(),
                apiConnectionManager.getPort());
        securityAPI = new SecurityAPI(api, apiConnectionManager);
        siteManagementAPI = new SiteManagementAPI(api, apiConnectionManager);
    }

    public void deleteSite(String siteId){
        securityAPI.logInIntoStudioUsingAPICall();
        siteManagementAPI.testDeleteSite(siteId);
    }

    public void createSite(String siteId, String description, String blueprint){
        securityAPI.logInIntoStudioUsingAPICall();
        siteManagementAPI.testCreateSite(siteId, description, blueprint);
    }

}
