/*
 * Copyright (C) 2007-2019 Crafter Software Corporation. All Rights Reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 3 as published by
 * the Free Software Foundation.
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

import org.apache.http.impl.cookie.BasicClientCookie;
import org.craftercms.studio.test.api.objects.SecurityAPI;
import org.craftercms.studio.test.api.objects.SiteManagementAPI;
import org.craftercms.studio.test.api2.objects.GroupsManagementAPI2;
import org.craftercms.studio.test.api2.objects.UsersManagementAPI2;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

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
    private APIConnectionManager apiConnectionManager;
    private String cookieAWSALB = "AWSALB";
    private String cookieAWSALBCORS = "AWSALBCORS";

    public APITestHelper() {
        apiConnectionManager = new APIConnectionManager();
        JsonTester api = new JsonTester(apiConnectionManager.getProtocol(), apiConnectionManager.getHost(),
                apiConnectionManager.getPort());
        securityAPI = new SecurityAPI(api, apiConnectionManager);
        siteManagementAPI = new SiteManagementAPI(api, apiConnectionManager);
        usersManagementAPI2 = new UsersManagementAPI2(api, apiConnectionManager, "0", "100", "asc");
        groupsManagementAPI2 = new GroupsManagementAPI2(api, apiConnectionManager, "0", "100", "asc");
    }

    public void deleteSite(String siteId) {
        ArrayList<String> awslbCookies = securityAPI.logInIntoStudioUsingAPICall(cookieAWSALB, cookieAWSALBCORS);
        if (!awslbCookies.isEmpty()) {
            siteManagementAPI.testDeleteSite(siteId, createCookie(awslbCookies.get(0)), createCookie(awslbCookies.get(1)));
        }
        else {
            siteManagementAPI.testDeleteSite(siteId);
        }
    }

    public void createSite(String siteId, String description, String blueprint) {
        ArrayList<String> awslbCookies = securityAPI.logInIntoStudioUsingAPICall(cookieAWSALB, cookieAWSALBCORS);
        if (blueprint.equalsIgnoreCase("empty") || blueprint.equalsIgnoreCase("editorial") ||
                blueprint.equalsIgnoreCase("headless_store") || blueprint.equalsIgnoreCase("videoCenter") ||
                blueprint.equalsIgnoreCase("headless_blog")
        ) {
            blueprint = "org.craftercms.blueprint." + blueprint;
        }
        else {
            blueprint = "org.craftercms.blueprint.editorial";
        }
        if (!awslbCookies.isEmpty()) {
            siteManagementAPI.testCreateSite(siteId, description, blueprint, createCookie(awslbCookies.get(0)),
                    createCookie(awslbCookies.get(1)));
        }
        else {
            siteManagementAPI.testCreateSite(siteId, description, blueprint);
        }
    }

    public void deleteUser(String username) {
        ArrayList<String> awslbCookies = securityAPI.logInIntoStudioUsingAPICall(cookieAWSALB, cookieAWSALBCORS);
        if (!awslbCookies.isEmpty()) {
            usersManagementAPI2.testDeleteUserByUserName(username, createCookie(awslbCookies.get(0)), createCookie(awslbCookies.get(1)));
        } else {
            usersManagementAPI2.testDeleteUserByUserName(username);
        }
    }

    public void createUser(String username) {
        ArrayList<String> awslbCookies = securityAPI.logInIntoStudioUsingAPICall(cookieAWSALB, cookieAWSALBCORS);
        if (!awslbCookies.isEmpty()) {
            usersManagementAPI2.testCreateUser("1", username, createCookie(awslbCookies.get(0)), createCookie(awslbCookies.get(1)));
        } else {
            usersManagementAPI2.testDeleteUserByUserName(username);
        }
    }

    public void createUserAddToGroup(String username, String groupName) {
        ArrayList<String> awslbCookies = securityAPI.logInIntoStudioUsingAPICall(cookieAWSALB, cookieAWSALBCORS);
        if (!awslbCookies.isEmpty()) {
            BasicClientCookie cookie1 = createCookie(awslbCookies.get(0));
            BasicClientCookie cookie2 = createCookie(awslbCookies.get(1));

            usersManagementAPI2.testCreateUser("1", username, cookie1, cookie2);
            String groupId = groupsManagementAPI2.getGroupIDForGroupName(groupName, cookie1, cookie2);
            groupsManagementAPI2.testAddMemberToGroupUsingUsername(groupId, username, cookie1, cookie2);
        }
        else {
            usersManagementAPI2.testCreateUser("1", username);
            String groupId = groupsManagementAPI2.getGroupIDForGroupName(groupName);
            groupsManagementAPI2.testAddMemberToGroupUsingUsername(groupId, username);
        }
    }

    private BasicClientCookie createCookie(String _cookieToCreate) {
        String[] cookieToCreate = _cookieToCreate.split(";");
        String[] cookieName = cookieToCreate[0].split("=");
        String[] cookieExpires = cookieToCreate[1].split("=");
        String[] cookiePath = cookieToCreate[2].split("=");

        DateTimeFormatter dateFormat = DateTimeFormatter.RFC_1123_DATE_TIME;
        LocalDateTime expiresDate = LocalDateTime.parse(cookieExpires[1], dateFormat);

        BasicClientCookie cookie = new BasicClientCookie(cookieName[0], cookieName[1]);
        cookie.setPath(cookiePath[1]);
        cookie.setExpiryDate(Date.from(expiresDate.atZone(ZoneId.systemDefault()).toInstant()));
        cookie.setDomain(apiConnectionManager.getHost());
        cookie.setSecure(false);
        return cookie;
    }
}
