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

import org.apache.commons.lang3.RandomStringUtils;
import org.craftercms.studio.test.utils.APIConnectionManager;
import org.craftercms.studio.test.utils.JsonTester;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;


/**
 * Created by cortiz on 3/15/17.
 */

public class SampleTest1 {

    private JsonTester api;

    public SampleTest1(){
    	APIConnectionManager apiConnectionManager = new APIConnectionManager();
		api = new JsonTester(apiConnectionManager.getProtocol()
				, apiConnectionManager.getHost(),apiConnectionManager.getPort());
    }

    @BeforeTest
    public void login(){
        api.post("/studio/api/1/services/api/1/user/login.json")
                .param("username","admin")
                .param("password","admin")
                .execute()
                .status(200)
                .header("Content-Language",is("en-US"))
                .header("Content-Type",is("application/json;charset=UTF-8"))
                .json("$",notNullValue())
                .json("$.user.email",not(empty()))
                .json("$.user.username",is("admin"));
    }


    @Test
    public void test(){
       api.get("/studio/api/1/services/api/1/user/get-sites-3.json")
               .execute().status(200).json("$", not(empty()));
    }

    @Test
    public void testExistSite(){
        api.get("/studio/api/1/services/api/1/site/exists.json")
                .urlParam("site", RandomStringUtils.randomAlphanumeric(10))
                .execute()
                .json("$.exists",is(false));

    }

    @Test
    public void testCreateSite(){
        String siteName = RandomStringUtils.randomAlphabetic(5);
        Map<String,Object> json=new HashMap<>();
        json.put("blueprintName","website_editorial");
        json.put("description",siteName);
        json.put("siteId",siteName);
        json.put("siteName",siteName);
        api.post("/studio/api/1/services/api/1/site/create-site.json")
                .json(json)
                .execute()
                .status(200);
        api.get("/studio/api/1/services/api/1/site/exists.json")
                .urlParam("site", siteName)
                .execute()
                .json("$.exists",is(true));
        
    }
}
