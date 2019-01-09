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

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.craftercms.studio.test.utils.APIConnectionManager;
import org.craftercms.studio.test.utils.JsonTester;

/**
 * @author luishernandez
 *
 */
public class RepoManagementAPI extends BaseAPI {


	public RepoManagementAPI(JsonTester api, APIConnectionManager apiConnectionManager) {
		super(api, apiConnectionManager);
	}

 	public void testSyncFromRepo(String siteId) {
    	Map<String, Object> json = new HashMap<>();
		json.put("site_id", siteId);
		
   		api.post("/studio/api/1/services/api/1/repo/sync-from-repo.json")
   		.json(json).execute().status(HttpStatus.SC_OK)
   				.json("$.message", is("OK")).debug();

   	}
 	
 	public void testRebuildDatabase(String siteId) {
    	Map<String, Object> json = new HashMap<>();
		json.put("site_id", siteId);
		
   		api.post("/studio/api/1/services/api/1/repo/rebuild-database.json")
   		.json(json).execute().status(HttpStatus.SC_OK);
   	}
 	
	public void testSyncFromRepoInvalidParameter(String siteId) {
    	Map<String, Object> json = new HashMap<>();
		json.put("site_idnonvalid", siteId);
		
   		api.post("/studio/api/1/services/api/1/repo/sync-from-repo.json")
   		.json(json).execute().status(HttpStatus.SC_BAD_REQUEST)
   				.json("$.message", is("Invalid parameter: site_id")).debug();

   	}
	
	public void testRebuildDatabaseInvalidParameter(String siteId) {
    	Map<String, Object> json = new HashMap<>();
		json.put("site_idnonvalid", siteId);
		
   		api.post("/studio/api/1/services/api/1/repo/rebuild-database.json")
   		.json(json).execute().status(HttpStatus.SC_BAD_REQUEST);

   	}
	
  	public void testSyncFromRepoSiteNotFound(String siteId) {
    	Map<String, Object> json = new HashMap<>();
		json.put("site_id", siteId+"nonvalid");
   
	api.post("/studio/api/1/services/api/1/repo/sync-from-repo.json")
    	.json(json).execute().status(HttpStatus.SC_NOT_FOUND)
   	.json("$.message", is("Site not found")).debug();

   	}

 	public void testSyncFromRepoUnauthorized(String siteId) {
    	Map<String, Object> json = new HashMap<>();
		json.put("site_id", siteId);
		
   		api.post("/studio/api/1/services/api/1/repo/sync-from-repo.json")
   		.json(json).execute().status(HttpStatus.SC_UNAUTHORIZED).debug();

   	}
}
