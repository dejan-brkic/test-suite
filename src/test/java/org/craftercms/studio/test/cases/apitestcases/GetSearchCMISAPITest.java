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

package org.craftercms.studio.test.cases.apitestcases;

import org.apache.http.HttpStatus;
import org.craftercms.studio.test.utils.APIConnectionManager;
import org.craftercms.studio.test.utils.JsonTester;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gustavo Ortiz Alfaro.
 */

public class GetSearchCMISAPITest {

	private JsonTester api;
	private String headerLocationBase;

	private String username = "admin";
	private String password = "admin";
	private String siteId = "mysite";
	private String description = "Description!";
	private String blueprint = "empty";
	private String groupName1 = "contributors01";
	private String groupName2 = "contributors02";

	public GetSearchCMISAPITest() {
		APIConnectionManager apiConnectionManager = new APIConnectionManager();
		api = new JsonTester(apiConnectionManager.getProtocol(), apiConnectionManager.getHost(),
				apiConnectionManager.getPort());
		headerLocationBase = apiConnectionManager.getHeaderLocationBase();
	}

	@BeforeTest
	public void login() {
		Map<String, Object> json = new HashMap<>();
		json.put("username", username);
		json.put("password", password);
		api.post("/studio/api/1/services/api/1/security/login.json")
				.json(json).execute().status(HttpStatus.SC_OK);
	}

	@Test(priority = 1)
	public void testCreateSite() {
		Map<String, Object> json = new HashMap<>();
		json.put("site_id", siteId);
		json.put("description", description);
		json.put("blueprint", blueprint);

		api.post("/studio/api/1/services/api/1/site/create.json")
				.json(json).execute().status(HttpStatus.SC_CREATED)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/site/get.json?site_id=" + siteId))
				.json("$.message", is("OK")).debug();
	}

	@Test(priority = 2)
	public void testCreateStudioGroup01() {
		Map<String, Object> json = new HashMap<>();
		json.put("group_name", groupName1);
		json.put("site_id", siteId);
		json.put("description", description);

		api.post("/studio/api/1/services/api/1/group/create.json")
				.json(json).execute().status(HttpStatus.SC_CREATED)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/group/get.json?group_name=" + groupName1))
				.json("$.message", is("OK")).debug();
	}

	@Test(priority = 3)
	public void testCreateStudioGroup02() {
		Map<String, Object> json = new HashMap<>();
		json.put("group_name", groupName2);
		json.put("site_id", siteId);
		json.put("description", description);

		api.post("/studio/api/1/services/api/1/group/create.json")
				.json(json).execute().status(HttpStatus.SC_CREATED)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/group/get.json?group_name=" + groupName2))
				.json("$.message", is("OK")).debug();
	}

	@Test(priority = 4)
	public void testGetSearchCMIS() {
		Map<String, Object> json = new HashMap<>();
		json.put("site_id", siteId);
		json.put("cmis_repo_id", "repo1");
		json.put("search_term", "*");
		json.put("path", "/assets`");

		api.get("/studio/api/1/services/api/1/cmis/search.json")
		.json(json).execute().status(HttpStatus.SC_OK);

	}

	@Test(priority = 5)
	public void testInvalidParameters() {
		Map<String, Object> json = new HashMap<>();
		json.put("site_id", siteId);
		json.put("cmis_repo_idnonvalid", "repo1");
		json.put("search_term", "*");
		json.put("path", "/assets`");

		api.get("/studio/api/1/services/api/1/cmis/search.json")
	    .json(json).execute().status(HttpStatus.SC_BAD_REQUEST).json("$.message", is("Invalid parameter(s): [cmis_repo_id]")).debug();

	}
}
