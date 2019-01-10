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

/**
 * @author chris lim
 *
 */
public class PublishAPI extends BaseAPI {


	public PublishAPI(JsonTester api, APIConnectionManager apiConnectionManager) {
		super(api, apiConnectionManager);
	}

	public void testStartPublisher(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("site_id", siteId);
		
		api.post("/studio/api/1/services/api/1/publish/start.json")
		.json(json).execute().status(HttpStatus.SC_OK)
				.debug();
	}
	
	public void testStartPublisherInvalidParameters(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("site_idnonvalid", siteId);
		
		api.post("/studio/api/1/services/api/1/publish/start.json")
		.json(json).execute().status(HttpStatus.SC_BAD_REQUEST)
				.debug();
	}

	public void testStartPublisherSiteNotFound(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("site_id", "nonvalid");
		
		api.post("/studio/api/1/services/api/1/publish/start.json")
		.json(json).execute().status(HttpStatus.SC_NOT_FOUND)
		.debug();
	}
	
	public void testStartPublisherUnauthorized(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("site_id", siteId);
		
		api.post("/studio/api/1/services/api/1/publish/start.json")
		.json(json).execute().status(HttpStatus.SC_UNAUTHORIZED)
				.debug();
	}
	
	public void testStopPublisher(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("site_id", siteId);
		
		api.post("/studio/api/1/services/api/1/publish/stop.json")
		.json(json).execute().status(HttpStatus.SC_OK)
				.debug();
	}
	
	public void testStopPublisherInvalidParameters(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("site_idnonvalid", siteId);
		
		api.post("/studio/api/1/services/api/1/publish/stop.json")
		.json(json).execute().status(HttpStatus.SC_BAD_REQUEST)
				.debug();
	}

	public void testStopPublisherSiteNotFound(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("site_id", siteId+"nonvalid");
		
		api.post("/studio/api/1/services/api/1/publish/stop.json")
		.json(json).execute().status(HttpStatus.SC_NOT_FOUND)
				// .json("$.message", is("site not found"))
				.debug();
	}
	
	public void testStopPublisherUnauthorized(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("site_id", siteId);
		
		api.post("/studio/api/1/services/api/1/publish/stop.json")
		.json(json).execute().status(HttpStatus.SC_UNAUTHORIZED)
				.debug();
	}
	
	public void testPublishStatus(String siteId) {
		
		api.get("/studio/api/1/services/api/1/publish/status.json").urlParam("site_id", siteId)
		.execute().status(HttpStatus.SC_OK).debug();
	}
	
	public void testPublishStatusInvalidParameters(String siteId) {
		
		api.get("/studio/api/1/services/api/1/publish/status.json").urlParam("site_idnonvalid", siteId)
		.execute().status(HttpStatus.SC_BAD_REQUEST).debug();
	}
	
	public void testPublishStatusSiteNotFound(String siteId) {
		
		api.get("/studio/api/1/services/api/1/publish/status.json").urlParam("site_id", siteId+"nonvalid")
		.execute().status(HttpStatus.SC_NOT_FOUND).debug();
	}
	
	public void testPublishStatusUnauthorized(String siteId) {
		
		api.get("/studio/api/1/services/api/1/publish/status.json").urlParam("site_id", siteId)
		.execute().status(HttpStatus.SC_UNAUTHORIZED).debug();
	}
}
