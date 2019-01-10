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

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
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
		.execute().status(HttpStatus.SC_OK)
		.debug();
	}
	
	public void testGetGoLiveItems(String siteId) {
		
		api.get("/studio/api/1/services/api/1/workflow/get-go-live-items.json")
		.urlParam("site", siteId)
		.urlParam("sort","eventDate")
		.urlParam("ascending","true")
		.urlParam("includeInProgress","true")
		.execute().status(HttpStatus.SC_OK)
		.debug();
	}
	
	public void testGetWorkflowAffectedPaths(String siteId) {
		api.get("/studio/api/1/services/api/1/workflow/get-workflow-affected-paths.json")
		.urlParam("site", siteId)
		.urlParam("path","/site/website/index.xml")
		.execute().status(HttpStatus.SC_OK)
		.debug();
	}
	
	public void testGoDelete(String siteId) {
		
		Map<String, Object> json = new HashMap<>();
		String[] items = new String[1];
		items[0] = "/site/website/folder1/index.xml";
		json.put("items", items);
		
		api.post("/studio/api/1/services/api/1/workflow/go-delete.json")
		.urlParam("site", siteId)
		.urlParam("user", "admin")
		.json(json)
		.execute().status(HttpStatus.SC_OK)
		.debug();
	}
	
	public void testGoLive(String siteId) {
		
		Map<String, Object> json = new HashMap<>();
		String[] items = new String[1];
		items[0] = "/site/website/folder1/index.xml";
		json.put("schedule", "now");
		json.put("submissionComment", " ");
		json.put("publishOptionComment", " ");
		json.put("publishChannel", "Live");
		json.put("items", items);
		
		api.post("/studio/api/1/services/api/1/workflow/go-live.json")
		.urlParam("site", siteId)
		.json(json)
		.execute().status(HttpStatus.SC_OK)
		.debug();
	}
	
	public void testReject(String siteId) {
		
		Map<String, Object> json = new HashMap<>();
		String[] items = new String[1];
		items[0] = "/site/website/index.xml";
		json.put("reason", "Not approved");
		json.put("scheduledDate", "now");
		json.put("items", items);
		
		api.post("/studio/api/1/services/api/1/workflow/reject.json")
		.urlParam("site", siteId)
		.urlParam("user", "admin")
		.json(json)
		.execute().status(HttpStatus.SC_OK)
		.debug();
	}
}
