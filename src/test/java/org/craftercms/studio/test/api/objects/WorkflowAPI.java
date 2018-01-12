package org.craftercms.studio.test.api.objects;

import java.util.HashMap;
import java.util.Map;

import org.craftercms.studio.test.utils.APIConnectionManager;
import org.craftercms.studio.test.utils.JsonTester;

public class WorkflowAPI extends BaseAPI{

	public WorkflowAPI(JsonTester api, APIConnectionManager apiConnectionManager) {
		super(api, apiConnectionManager);
	}
	
	public void testCreateJobs(String siteId) {
		
		Map<String, Object> json = new HashMap<>();
		json.put("sendEmail", "true");
		json.put("schedule", "now");
		json.put("submissionComment", "");
		
		String[] items = new String[1];
		items[0] = "/site/website/index.xml";
		json.put("items", items);
		
		api.post("/studio/api/1/services/api/1/workflow/create-jobs.json")
		.urlParam("site", siteId)
		.urlParam("user","admin")
		.json(json)
		.execute().status(200)
		.debug();
	}
}
