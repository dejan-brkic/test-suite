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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.craftercms.studio.test.utils.APIConnectionManager;
import org.craftercms.studio.test.utils.JsonTester;

public class DependencyAPI extends BaseAPI{

	private String path = "/site/website";
	
	public DependencyAPI(JsonTester api, APIConnectionManager apiConnectionManager) {
		super(api, apiConnectionManager);
	}
	
	public void testGetDependantItems(String siteId) {
		api.post("/studio/api/1/services/api/1/dependency/get-dependant.json")
		.urlParam("site", siteId)
		.urlParam("path", path)
		.execute().status(HttpStatus.SC_OK);
	}
	
	public void testGetDependencies(String siteId) {
		
		Map<String, Object> jsonInner = new HashMap<>();
		jsonInner.put("uri", "/site/website/index.xml");
		List<Map<String, Object>> json = new ArrayList<Map<String, Object>>();
		json.add(jsonInner);
		
		api.post("/studio/api/1/services/api/1/dependency/get-dependencies.json")
		.urlParam("site", siteId).json(json).execute().status(HttpStatus.SC_OK);
	}
	
	public void testGetSimpleDependencies(String siteId) {
		api.post("/studio/api/1/services/api/1/dependency/get-simple-dependencies.json")
		.urlParam("site", siteId)
		.urlParam("path", path)
		.execute().status(HttpStatus.SC_OK);
	}
}
