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

public class SiteManagementAPI extends BaseAPI {

	private String siteId = "mysite";
	private String description = "Description!";
	private String blueprint = "org.craftercms.blueprint.empty";

	public SiteManagementAPI(JsonTester api, APIConnectionManager apiConnectionManager) {
		super(api, apiConnectionManager);
	}

	public void testCreateSite(String siteId, String description, String blueprint){
		Map<String, Object> json = new HashMap<>();
		json.put("site_id", siteId);
		json.put("description", description);
		json.put("blueprint", blueprint);
		api.post("/studio/api/1/services/api/1/site/create.json").json(json).execute().status(HttpStatus.SC_CREATED)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/site/get.json?site_id=" + siteId))
				.json("$.message", is("OK")).debug();
		this.setSiteId(siteId);
	}

	public void testCreateSite(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("site_id", siteId);
		json.put("description", description);
		json.put("blueprint", blueprint);
		api.post("/studio/api/1/services/api/1/site/create.json").json(json).execute().status(HttpStatus.SC_CREATED)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/site/get.json?site_id=" + siteId))
				.json("$.message", is("OK")).debug();
		this.setSiteId(siteId);
	}

	public void testCreateSiteInvalidParameters() {
		Map<String, Object> json = new HashMap<>();
		json.put("site_idnonvalid", siteId);
		json.put("description", description);
		json.put("blueprint", blueprint);

		api.post("/studio/api/1/services/api/1/site/create.json").json(json).execute().status(HttpStatus.SC_BAD_REQUEST)
				.json("$.message", is("Invalid parameter(s): [site_id]")).debug();
	}

	public void testCreateSiteSiteAlreadyExists() {
		Map<String, Object> json = new HashMap<>();
		json.put("site_id", siteId);
		json.put("description", description);
		json.put("blueprint", blueprint);

		api.post("/studio/api/1/services/api/1/site/create.json").json(json).execute().status(HttpStatus.SC_CONFLICT)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/site/get.json?site_id=" + siteId))
				.json("$.message", is("Site already exists")).debug();
	}
	
	public void testCreateSiteUnauthorized(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("site_id", siteId);
		json.put("description", description);
		json.put("blueprint", blueprint);
		api.post("/studio/api/1/services/api/1/site/create.json").json(json).execute().status(HttpStatus.SC_UNAUTHORIZED)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/site/get.json?site_id=" + siteId))
				.debug();
	}


	public void testDeleteSite(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("siteId", siteId);
		api.post("/studio/api/1/services/api/1/site/delete-site.json").json(json).execute().status(HttpStatus.SC_OK);
				
	}
	
	public void testDeleteSiteUnauthorized(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("siteId", siteId);
		api.post("/studio/api/1/services/api/1/site/delete-site.json").json(json).execute().status(HttpStatus.SC_UNAUTHORIZED);
				
	}

	public void testClearConfigurationCache() {
		api.get("/studio/api/1/services/api/1/site/clear-configuration-cache.json").urlParam("site", this.siteId)
				.execute().status(HttpStatus.SC_OK);
	}

	public void testExistsSite() {
		api.get("/studio/api/1/services/api/1/site/exists.json").urlParam("site", this.siteId).execute().status(HttpStatus.SC_OK);
	}

	public void testGetAvailableBlueprints() {
		api.get("/studio/api/1/services/api/1/site/get-available-blueprints.json").execute().status(HttpStatus.SC_OK);
	}
	
	public void testGetConfigurationOfSite() {
		api.get("/studio/api/1/services/api/1/site/get-configuration.json")
		.urlParam("site", this.siteId)
		.urlParam("path", "/site-config.xml").execute().status(HttpStatus.SC_OK);
	}
	
	public void testGetSite() {
		api.get("/studio/api/1/services/api/1/site/get.json")
		.urlParam("site_id", siteId).execute()
		.status(HttpStatus.SC_OK)
		.header("Location", is(headerLocationBase+"/studio/api/1/services/api/1/site/get.json?site_id="+ siteId));
   	}
	
	public void testGetSiteUnauthorized() {
		api.get("/studio/api/1/services/api/1/site/get.json")
		.urlParam("site_id", siteId).execute()
		.status(HttpStatus.SC_UNAUTHORIZED)
		.header("Location", is(headerLocationBase+"/studio/api/1/services/api/1/site/get.json?site_id="+ siteId));
   	}
	
	public void testGetSiteInvalidParameters() {
		api.get("/studio/api/1/services/api/1/site/get.json")
		.urlParam("site_idnonvalid", siteId).execute()
		.status(HttpStatus.SC_BAD_REQUEST);
   	}
	
	public void testGetSiteSiteNotFound() {
    		api.get("/studio/api/1/services/api/1/site/get.json")
    		.urlParam("site_id", siteId+"nonvalid").execute()
		.status(HttpStatus.SC_NOT_FOUND)
		.json("$.message", is("Site not found")).debug();
   	}

	public void testGetSitesPerUser(String userName) {
		api.get("/studio/api/1/services/api/1/site/get-per-user.json")
		.urlParam("username", userName).execute()
		.status(HttpStatus.SC_OK)
		.header("Location", is(headerLocationBase+"/studio/api/1/services/api/1/site/get-per-user.json?username="+ userName+"&start=0&number=25"));
   	}
	
	public void testGetSitesPerUserInvalidParameter(String userName) {
		api.get("/studio/api/1/services/api/1/site/get-per-user.json")
		.urlParam("usernamenonvalid", userName).execute()
		.status(HttpStatus.SC_BAD_REQUEST);
   	}
	
	public void testGetSitesPerUserUserNotFound(String userName) {
		api.get("/studio/api/1/services/api/1/site/get-per-user.json")
		.urlParam("username", userName+"nonvalid").execute()
		.status(HttpStatus.SC_NOT_FOUND);
   	}
	
	public void testGetSitesPerUserUnauthorized(String userName) {
		api.get("/studio/api/1/services/api/1/site/get-per-user.json")
		.urlParam("username", userName).execute()
		.status(HttpStatus.SC_UNAUTHORIZED)
		.header("Location", is(headerLocationBase+"/studio/api/1/services/api/1/site/get-per-user.json?username="+ userName+"&start=0&number=25"));
   	}
	
	public void testCreateSiteGitRepo(String siteId) throws Exception {
		
		//temp git repo creation 
		Map<String, Object> json = new HashMap<>();
		json.put("site_id", siteId);
		json.put("description", description);
		json.put("blueprint", blueprint);
		api.post("/studio/api/1/services/api/1/site/create.json").json(json).execute().status(HttpStatus.SC_CREATED)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/site/get.json?site_id=" + siteId))
				.json("$.message", is("OK")).debug();
		this.setSiteId(siteId);
	}
	
	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
}
