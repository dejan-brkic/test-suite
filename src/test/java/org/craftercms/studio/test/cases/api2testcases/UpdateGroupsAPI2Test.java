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

package org.craftercms.studio.test.cases.api2testcases;

import org.apache.commons.lang3.RandomStringUtils;
import org.craftercms.studio.test.api.objects.SecurityAPI;
import org.craftercms.studio.test.api2.objects.GroupsManagementAPI2;
import org.craftercms.studio.test.utils.APIConnectionManager;
import org.craftercms.studio.test.utils.JsonTester;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * 
 * @author luishernandez
 *
 */

public class UpdateGroupsAPI2Test {

	private SecurityAPI securityAPI;
	private GroupsManagementAPI2 groupsManagementAPI2;
	private int randomID;
	private String groupName;
	private String groupId;
	private String offSet = "0";
	private String limit = "1000";
	private String sort = "asc";

	public UpdateGroupsAPI2Test() {
		APIConnectionManager apiConnectionManager = new APIConnectionManager();
		JsonTester api = new JsonTester(apiConnectionManager.getProtocol(), apiConnectionManager.getHost(),
				apiConnectionManager.getPort());
		securityAPI = new SecurityAPI(api, apiConnectionManager);
		groupsManagementAPI2 = new GroupsManagementAPI2(api, apiConnectionManager,offSet,limit,sort);
		randomID = (int)(((Math.random() * 2)*5) + Math.random());
		groupName = "Test_Groups" + RandomStringUtils.randomAlphabetic(5).toLowerCase();
		
	}

	@BeforeTest
	public void beforeTest() {
		securityAPI.logInIntoStudioUsingAPICall();
		groupsManagementAPI2.testCreateGroupsWithGivenID(String.valueOf(randomID),groupName);
		groupId = groupsManagementAPI2.getGroupIDForGroupName(groupName);
	}

	@Test(
			priority = 1,
			groups = { "updateGroups" })
	public void testUpdateGroups() {
		groupsManagementAPI2.testUpdateGroups(groupId,groupName);
	}

	@Test(
			priority = 2,
			groups = { "updateGroups" })
	public void testUpdateGroupsNotAllowed() {
		groupsManagementAPI2.testUpdateGroupsResourceNotFound("0",groupName);
	}

	@Test(
			priority = 3,
			groups = { "updateGroups" })
	public void testUpdateGroupsBadRequest() {
		groupsManagementAPI2.testUpdateGroupsBadRequest(groupId,groupName);
	}

	@AfterGroups(
			groups = { "updateGroups" })
	public void afterTest() {
		groupsManagementAPI2.testDeleteGroups(groupId);
		securityAPI.logOutFromStudioUsingAPICall();
	}

	@Test(
			dependsOnGroups = { "updateGroups" })
	public void testUpdateGroupsUnauthorized() {
		groupsManagementAPI2.testUpdateGroupsUnauthorized(groupId,groupName);
	}
}
