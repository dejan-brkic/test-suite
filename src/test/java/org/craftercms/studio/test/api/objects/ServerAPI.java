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

package org.craftercms.studio.test.api.objects;

import org.apache.http.HttpStatus;
import org.craftercms.studio.test.utils.APIConnectionManager;
import org.craftercms.studio.test.utils.JsonTester;

/**
 * @author chris lim
 *
 */
public class ServerAPI extends BaseAPI {


	public ServerAPI(JsonTester api, APIConnectionManager apiConnectionManager) {
		super(api, apiConnectionManager);
	}

 	public void testGetAvailableLanguages(){
   		api.get("studio/api/1/services/api/1/server/get-available-languages.json").execute().status(HttpStatus.SC_OK).debug();
 	}
 	
 	public void testGetLoggers(){
   		api.get("studio/api/1/services/api/1/server/get-loggers.json").execute().status(HttpStatus.SC_OK).debug();
 	}
 	
 	public void testGetUIResourceOverride(){

   		api.get("studio/api/1/services/api/1/server/get-ui-resource-override.json").urlParam("resource", "src/test/resources/logo.png")
   		.execute().status(HttpStatus.SC_OK).debug();
 	}
 	
 	public void testSetLoggerState(){
 		String logger = "org.craftercms.studio.impl.v1.service.content.ContentServiceImpl";
   		api.get("studio/api/1/services/api/1/server/set-logger-state.json").urlParam("logger", logger).urlParam("level", "debug")
   		.execute().status(HttpStatus.SC_OK).debug();
 	}
}
