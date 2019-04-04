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

package org.craftercms.studio.test.api2.objects;


import org.apache.http.HttpStatus;
import org.craftercms.studio.test.api.objects.BaseAPI;
import org.craftercms.studio.test.utils.APIConnectionManager;
import org.craftercms.studio.test.utils.JsonTester;

/**
 * @author chris lim
 *
 */
public class MonitoringAPI2 extends BaseAPI {


	public MonitoringAPI2(JsonTester api, APIConnectionManager apiConnectionManager) {
		super(api, apiConnectionManager);
	}

 	public void testVersion(){
   		api.get("studio/api/2/monitoring/version.json").execute().status(HttpStatus.SC_OK).debug();
 	}
 	
 	public void testStatus(){
   		api.get("studio/api/2/monitoring/status.json").execute().status(HttpStatus.SC_OK).debug();
 	}

 	public void testMemory(){
   		api.get("studio/api/2/monitoring/memory.json").execute().status(HttpStatus.SC_OK).debug();
 	}
 	
}
