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
package org.craftercms.studio.test.utils;

import org.craftercms.studio.test.api.objects.SecurityAPI;
import org.craftercms.studio.test.api.objects.SiteManagementAPI;
import org.craftercms.studio.test.api2.objects.GroupsManagementAPI2;
import org.craftercms.studio.test.api2.objects.UsersManagementAPI2;

/**
 *
 * @author yacdaniel
 *
 */
public class APITestHelper {

    private SecurityAPI securityAPI;
    private SiteManagementAPI siteManagementAPI;
    private UsersManagementAPI2 usersManagementAPI2;
    private GroupsManagementAPI2 groupsManagementAPI2;

    public APITestHelper(){
        APIConnectionManager apiConnectionManager = new APIConnectionManager();
        JsonTester api = new JsonTester(apiConnectionManager.getProtocol(), apiConnectionManager.getHost(),
                apiConnectionManager.getPort());
        securityAPI = new SecurityAPI(api, apiConnectionManager);
        siteManagementAPI = new SiteManagementAPI(api, apiConnectionManager);
        usersManagementAPI2 = new UsersManagementAPI2(api, apiConnectionManager, "0" , "100", "asc");
        groupsManagementAPI2 = new GroupsManagementAPI2(api, apiConnectionManager, "0", "100", "asc");
    }

    public void deleteSite(String siteId){
        securityAPI.logInIntoStudioUsingAPICall();
        siteManagementAPI.testDeleteSite(siteId);
    }

    public void createSite(String siteId, String description, String blueprint){
        securityAPI.logInIntoStudioUsingAPICall();
        if (blueprint.equalsIgnoreCase("empty") || blueprint.equalsIgnoreCase("editorial") ||
                blueprint.equalsIgnoreCase("headless_store") || blueprint.equalsIgnoreCase("videoCenter") ||
                blueprint.equalsIgnoreCase("headless_blog")
        ) {
            blueprint = "org.craftercms.blueprint." + blueprint;
        }
        else {
            blueprint = "org.craftercms.blueprint.editorial";
        }
        siteManagementAPI.testCreateSite(siteId, description, blueprint);
    }

    public void deleteUser(String username){
        securityAPI.logInIntoStudioUsingAPICall();
        usersManagementAPI2.testDeleteUserByUserName(username);
    }

    public void createUser(String username){
        securityAPI.logInIntoStudioUsingAPICall();
        usersManagementAPI2.testCreateUser("1", username);
    }

    public void createUserAddToGroup(String username, String groupName){
        securityAPI.logInIntoStudioUsingAPICall();
        usersManagementAPI2.testCreateUser("1", username);
        String groupId = groupsManagementAPI2.getGroupIDForGroupName(groupName);
        groupsManagementAPI2.testAddMemberToGroupUsingUsername(groupId, username);
    }

}
