package org.craftercms.studio.test.api.objects;

import static org.hamcrest.Matchers.is;

import java.util.HashMap;
import java.util.Map;

import org.craftercms.studio.test.utils.APIConnectionManager;
import org.craftercms.studio.test.utils.JsonTester;

public class SiteManagementAPI extends BaseAPI {

	private String siteId = "mysite";
	private String description = "Description!";
	private String blueprint = "empty";

	public SiteManagementAPI(JsonTester api, APIConnectionManager apiConnectionManager) {
		super(api, apiConnectionManager);
	}

	public void testCreateSite(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("site_id", siteId);
		json.put("description", description);
		json.put("blueprint", blueprint);
		api.post("/studio/api/1/services/api/1/site/create.json").json(json).execute().status(201)
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

		api.post("/studio/api/1/services/api/1/site/create.json").json(json).execute().status(400)
				.json("$.message", is("Invalid parameter(s): [site_id]")).debug();
	}

	public void testCreateSiteSiteAlreadyExists() {
		Map<String, Object> json = new HashMap<>();
		json.put("site_id", siteId);
		json.put("description", description);
		json.put("blueprint", blueprint);

		api.post("/studio/api/1/services/api/1/site/create.json").json(json).execute().status(409)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/site/get.json?site_id=" + siteId))
				.json("$.message", is("Site already exists")).debug();
	}
	
	public void testCreateSiteUnauthorized(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("site_id", siteId);
		json.put("description", description);
		json.put("blueprint", blueprint);
		api.post("/studio/api/1/services/api/1/site/create.json").json(json).execute().status(401)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/site/get.json?site_id=" + siteId))
				.debug();
	}


	public void testDeleteSite(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("siteId", siteId);
		api.post("/studio/api/1/services/api/1/site/delete-site.json").json(json).execute().status(200);
				
	}
	
	public void testDeleteSiteUnauthorized(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("siteId", siteId);
		api.post("/studio/api/1/services/api/1/site/delete-site.json").json(json).execute().status(401);
				
	}

	public void testClearConfigurationCache() {
		api.get("/studio/api/1/services/api/1/site/clear-configuration-cache.json").urlParam("site", this.siteId)
				.execute().status(200);
	}

	public void testExistsSite() {
		api.get("/studio/api/1/services/api/1/site/exists.json").urlParam("site", this.siteId).execute().status(200);
	}

	public void testGetAvailableBlueprints() {
		api.get("/studio/api/1/services/api/1/site/get-available-blueprints.json").execute().status(200);
	}
	
	public void testGetConfigurationOfSite() {
		api.get("/studio/api/1/services/api/1/site/get-configuration.json")
		.urlParam("site", this.siteId)
		.urlParam("path", "/site-config.xml").execute().status(200);
	}
	
	public void testGetSite() {
		api.get("/studio/api/1/services/api/1/site/get.json")
		.urlParam("site_id", siteId).execute()
		.status(200)
		.header("Location", is(headerLocationBase+"/studio/api/1/services/api/1/site/get.json?site_id="+ siteId));
   	}
	
	public void testGetSiteUnauthorized() {
		api.get("/studio/api/1/services/api/1/site/get.json")
		.urlParam("site_id", siteId).execute()
		.status(401)
		.header("Location", is(headerLocationBase+"/studio/api/1/services/api/1/site/get.json?site_id="+ siteId));
   	}
	
	public void testGetSiteInvalidParameters() {
		api.get("/studio/api/1/services/api/1/site/get.json")
		.urlParam("site_idnonvalid", siteId).execute()
		.status(400);
   	}
	
	public void testGetSiteSiteNotFound() {
    		api.get("/studio/api/1/services/api/1/site/get.json")
    		.urlParam("site_id", siteId+"nonvalid").execute()
		.status(404)
		.json("$.message", is("Site not found")).debug();
   	}

	public void testGetSitesPerUser(String userName) {
		api.get("/studio/api/1/services/api/1/site/get-per-user.json")
		.urlParam("username", userName).execute()
		.status(200)
		.header("Location", is(headerLocationBase+"/studio/api/1/services/api/1/site/get-per-user.json?username="+ userName+"&start=0&number=25"));
   	}
	
	public void testGetSitesPerUserInvalidParameter(String userName) {
		api.get("/studio/api/1/services/api/1/site/get-per-user.json")
		.urlParam("usernamenonvalid", userName).execute()
		.status(400);
   	}
	
	public void testGetSitesPerUserUserNotFound(String userName) {
		api.get("/studio/api/1/services/api/1/site/get-per-user.json")
		.urlParam("username", userName+"nonvalid").execute()
		.status(404);
   	}
	
	public void testGetSitesPerUserUnauthorized(String userName) {
		api.get("/studio/api/1/services/api/1/site/get-per-user.json")
		.urlParam("username", userName).execute()
		.status(401)
		.header("Location", is(headerLocationBase+"/studio/api/1/services/api/1/site/get-per-user.json?username="+ userName+"&start=0&number=25"));
   	}
	
	public void testCreateSiteGitRepo(String siteId) throws Exception {
		
		//temp git repo creation 
		Map<String, Object> json = new HashMap<>();
		json.put("site_id", siteId);
		json.put("description", description);
		json.put("blueprint", blueprint);
		api.post("/studio/api/1/services/api/1/site/create.json").json(json).execute().status(201)
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
