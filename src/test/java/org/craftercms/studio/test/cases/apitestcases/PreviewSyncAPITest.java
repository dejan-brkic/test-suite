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
package org.craftercms.studio.test.cases.apitestcases;

import org.craftercms.studio.test.api.objects.PreviewAPI;
import org.craftercms.studio.test.api.objects.SecurityAPI;
import org.craftercms.studio.test.api.objects.SiteManagementAPI;
import org.craftercms.studio.test.utils.APIConnectionManager;
import org.craftercms.studio.test.utils.JsonTester;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by chris lim
 */

public class PreviewSyncAPITest {

    private PreviewAPI previewAPI;
    private SiteManagementAPI siteManagementAPI;
    private SecurityAPI securityAPI;
    private String siteId = "previewsyncsitetest";
    
    public PreviewSyncAPITest(){
    	APIConnectionManager apiConnectionManager = new APIConnectionManager();
		JsonTester api = new JsonTester(apiConnectionManager.getProtocol(), apiConnectionManager.getHost(),
				apiConnectionManager.getPort());
    	
		previewAPI = new PreviewAPI(api, apiConnectionManager);
    	siteManagementAPI = new SiteManagementAPI(api, apiConnectionManager);
    	securityAPI = new SecurityAPI(api, apiConnectionManager);
    }

    @BeforeTest
    public void beforeTest() {
    	securityAPI.logInIntoStudioUsingAPICall();
    	siteManagementAPI.testCreateSite(siteId);
    }
    
    @Test(priority=1)
    public void testPreviewSync(){
    	previewAPI.testPreviewSync(siteId);
    }
    
    @AfterTest
    public void afterTest() {
    	
    	siteManagementAPI.testDeleteSite(siteId);
    	securityAPI.logOutFromStudioUsingAPICall();
    }
}
