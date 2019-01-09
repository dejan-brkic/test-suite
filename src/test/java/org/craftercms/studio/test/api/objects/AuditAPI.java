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


import static org.hamcrest.Matchers.is;

import org.apache.http.HttpStatus;
import org.craftercms.studio.test.utils.APIConnectionManager;
import org.craftercms.studio.test.utils.JsonTester;

/**
 * @author luishernandez
 *
 */
public class AuditAPI extends BaseAPI {


	public AuditAPI(JsonTester api, APIConnectionManager apiConnectionManager) {
		super(api, apiConnectionManager);
	}

 	public void testGetAuditLog(String siteId) {		
   		api.get("studio/api/1/services/api/1/audit/get.json")
   		.urlParam("site_id", siteId).execute().status(HttpStatus.SC_OK)
   				.json("$.message", is("OK")).debug();

   	}
 	
 	
	public void testGetAuditLogInvalidParameter(String siteId) {
   		api.get("studio/api/1/services/api/1/audit/get.json")
   		.urlParam("site_idnonvalid", siteId).execute().status(HttpStatus.SC_BAD_REQUEST);

   	}
	
  	public void testGetAuditLogSiteNotFound(String siteId) {
  		api.get("studio/api/1/services/api/1/audit/get.json")
   		.urlParam("site_id", siteId+"nonvalid").execute().status(HttpStatus.SC_NOT_FOUND);
   	}
  	
  	public void testGetAuditLogUnauthorized(String siteId) {
  		api.get("studio/api/1/services/api/1/audit/get.json")
   		.urlParam("site_id", siteId).execute().status(HttpStatus.SC_UNAUTHORIZED);
   	}

}
