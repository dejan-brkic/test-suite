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

import org.craftercms.studio.test.api2.objects.MonitoringAPI2;
import org.craftercms.studio.test.api.objects.SecurityAPI;
import org.craftercms.studio.test.utils.APIConnectionManager;
import org.craftercms.studio.test.utils.JsonTester;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by gustavo ortiz
 */

public class MemoryAPI2Test {

    private MonitoringAPI2 monitoringAPI2;
    private SecurityAPI securityAPI;
    
    public MemoryAPI2Test(){
    	APIConnectionManager apiConnectionManager = new APIConnectionManager();
		JsonTester api = new JsonTester(apiConnectionManager.getProtocol(), apiConnectionManager.getHost(),
				apiConnectionManager.getPort());
    	
		securityAPI = new SecurityAPI(api, apiConnectionManager);
		monitoringAPI2 = new MonitoringAPI2(api, apiConnectionManager);
    }

    @BeforeTest
	public void beforeTest() {
		securityAPI.logInIntoStudioUsingAPICall();
	}
    
    @Test(priority=1)
    public void testMemory(){
		monitoringAPI2.testMemory();
    }
    
    @AfterTest
	public void afterTest() {
		securityAPI.logOutFromStudioUsingAPICall();
	}
}
