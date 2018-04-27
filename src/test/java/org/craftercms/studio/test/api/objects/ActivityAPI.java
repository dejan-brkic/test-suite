package org.craftercms.studio.test.api.objects;

import org.craftercms.studio.test.utils.APIConnectionManager;
import org.craftercms.studio.test.utils.JsonTester;

public class ActivityAPI extends BaseAPI{

	public ActivityAPI(JsonTester api, APIConnectionManager apiConnectionManager) {
		super(api, apiConnectionManager);
	}
	
	public void testGetUserActivity(String siteId) {
		
		api.get("/studio/api/1/services/api/1/activity/get-user-activities.json")
		.urlParam("site", siteId)
		.urlParam("user","admin")
		.urlParam("num","10")
		.urlParam("excludeLive","false")
		.urlParam("filterType","all")
		.execute().status(200)
		.debug();
	}
	
	public void testPostActivity(String siteId) {
		
		api.post("/studio/api/1/services/api/1/activity/post-activity.json")
		.urlParam("site", siteId)
		.urlParam("user","admin")
		.urlParam("path","/site/website/index.xml")
		.urlParam("activity","UPDATED")
		.urlParam("contentTypeClass","pages")
		.execute().status(200)
		.debug();
	}
}
