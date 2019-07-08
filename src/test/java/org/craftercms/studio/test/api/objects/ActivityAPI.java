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

import static org.hamcrest.Matchers.is;

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
		.execute().status(HttpStatus.SC_OK)
		.debug();
	}
	
	public void testPostActivity(String siteId) {
		
		api.post("/studio/api/1/services/api/1/activity/post-activity.json")
		.urlParam("site", siteId)
		.urlParam("user","admin")
		.urlParam("path","/site/website/index.xml")
		.urlParam("activity","UPDATED")
		.urlParam("contentTypeClass","pages")
		.execute().status(HttpStatus.SC_GONE)
		.json("$.message", is("API deprecated."))
		.debug();
	}
}
