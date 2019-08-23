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

package org.craftercms.studio.test.api2.objects;

import org.apache.http.HttpStatus;
import org.craftercms.studio.test.api.objects.BaseAPI;
import org.craftercms.studio.test.utils.APIConnectionManager;
import org.craftercms.studio.test.utils.JsonTester;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

/**
 *
 * @author yacdaniel
 *
 */
public class RepositoryAPI2 extends BaseAPI {

    private final String ADD_REMOTE_URL = "/studio/api/2/repository/add_remote";
    private final String PULL_FROM_REMOTE_URL = "/studio/api/2/repository/pull_from_remote";
    private final String PUSH_TO_REMOTE_URL ="/studio/api/2/repository/push_to_remote";
    private final String REBUILD_DATABASE_URL = "/studio/api/2/repository/rebuild_database";
    private final String REMOVE_REMOTE_URL = "/studio/api/2/repository/remove_remote";
    private final String LIST_REMOTE_URL= "/studio/api/2/repository/list_remotes";

    public RepositoryAPI2(JsonTester api, APIConnectionManager apiConnectionManager) {
        super(api, apiConnectionManager);
    }

    private Object addRemotePayload(String siteId, String remoteName, String remoteUrl, String authenticationType,
                                    String remoteUsername, String remotePassword, String remoteToken,
                                    String remotePrivateKey, Boolean badRequest) {
        Map<String, Object> requestBody = new HashMap<>();
        if (badRequest) {
            requestBody.put("siteBadRequest", siteId);
        }
        else {
            requestBody.put("siteId", siteId);
        }
        requestBody.put("remoteName", remoteName);
        requestBody.put("remoteUrl", remoteUrl);
        requestBody.put("authenticationType", authenticationType);
        if (authenticationType.equalsIgnoreCase("basic")) {
            requestBody.put("remoteUsername", remoteUsername);
            requestBody.put("remotePassword", remotePassword);
        }
        else if(authenticationType.equalsIgnoreCase("token")) {
            requestBody.put("remoteToken", remoteToken);
        }
        else {
            requestBody.put("remotePrivateKey", remotePrivateKey);
        }
        return requestBody;
    }

    private Object pushToRemotePayload(String siteId, String remoteName, String remoteBranch, boolean force,
                                       boolean badRequest) {
        Map<String, Object> requestBody = new HashMap<>();
        if (badRequest) {
            requestBody.put("siteBadRequest", siteId);
        }
        else {
            requestBody.put("siteId", siteId);
        }
        requestBody.put("remoteName", remoteName);
        requestBody.put("remoteBranch", remoteBranch);
        requestBody.put("force", force);
        return requestBody;
    }

    private Object pullFromRemotePayload(String siteId, String remoteName, String remoteBranch, String mergeStrategy,
                                         boolean badRequest) {
        Map<String, Object> requestBody = new HashMap<>();
        if (badRequest) {
            requestBody.put("siteBadRequest", siteId);
        }
        else {
            requestBody.put("siteId", siteId);
        }
        requestBody.put("remoteName", remoteName);
        requestBody.put("remoteBranch", remoteBranch);
        requestBody.put("mergeStrategy", mergeStrategy);
        return requestBody;
    }


    public void testAddRemote(String siteId, String remoteName, String remoteUrl, String authenticationType,
                                       String remoteUsername, String remotePassword, String remoteToken, String remotePrivateKey){
        Object requestBody = addRemotePayload(siteId, remoteName, remoteUrl, authenticationType, remoteUsername,
                remotePassword, remoteToken, remotePrivateKey, false);
        api.post(ADD_REMOTE_URL).json(requestBody).execute().status(HttpStatus.SC_CREATED)
                .json("$.response.message", is("Created"));
    }

    public void testAddRemoteAlreadyExist(String siteId, String remoteName, String remoteUrl, String authenticationType,
                              String remoteUsername, String remotePassword, String remoteToken, String remotePrivateKey) {
        Object requestBody = addRemotePayload(siteId, remoteName, remoteUrl, authenticationType, remoteUsername,
                remotePassword, remoteToken, remotePrivateKey, false);
        api.post(ADD_REMOTE_URL).json(requestBody).execute().status(HttpStatus.SC_CONFLICT)
                .json("$.response.message", is("Remote repository already exists"));
    }

    public void testAddRemoteBadRequest(String siteId, String remoteName, String remoteUrl, String authenticationType,
                                        String remoteUsername, String remotePassword) {
        Object requestBody = addRemotePayload(siteId, remoteName, remoteUrl, authenticationType, remoteUsername,
                remotePassword, "", "", true);
        api.post(ADD_REMOTE_URL).json(requestBody).execute().status(HttpStatus.SC_BAD_REQUEST)
                .json("$.response.code", is(1001))
                .json("$.response.remedialAction", is("Check API and make sure you're sending the correct parameters"))
                .json("$.response.message", is("Invalid parameter(s)"));
    }

    public void testAddRemoteSiteIdInvalid(String siteId, String remoteName, String remoteUrl, String authenticationType,
                                        String remoteUsername, String remotePassword) {
        Object requestBody = addRemotePayload(siteId, remoteName, remoteUrl, authenticationType, remoteUsername,
                remotePassword, "", "", false);
        api.post(ADD_REMOTE_URL).json(requestBody).execute().status(HttpStatus.SC_NOT_FOUND)
                .json("$.response.message", is("Project not found"));
    }

    public void testAddRemoteUnAuthorized(String siteId, String remoteName, String remoteUrl, String authenticationType,
                                           String remoteUsername, String remotePassword) {
        Object requestBody = addRemotePayload(siteId, remoteName, remoteUrl, authenticationType, remoteUsername,
                remotePassword, "", "", false);
        api.post(ADD_REMOTE_URL).json(requestBody).execute().status(HttpStatus.SC_UNAUTHORIZED);
    }

    public void testPullFromRemote(String siteId, String remoteName, String remoteBranch){
        Object requestBody = pullFromRemotePayload(siteId, remoteName, remoteBranch, "none", false);
        api.post(PULL_FROM_REMOTE_URL).json(requestBody).execute().status(HttpStatus.SC_OK).debug()
                .json("$.response.message", is("OK"));
    }

    public void testPullFromRemoteBadRequest(String siteId, String remoteName, String remoteBranch){
        Object requestBody = pullFromRemotePayload(siteId, remoteName, remoteBranch, "none", true);
        api.post(PULL_FROM_REMOTE_URL).json(requestBody).execute().status(HttpStatus.SC_BAD_REQUEST)
                .json("$.response.code", is(1001))
                .json("$.response.remedialAction", is("Check API and make sure you're sending the correct parameters"))
                .json("$.response.message", is("Invalid parameter(s)"));    }

    public void testPullFromRemoteInvalidSiteId(String siteId, String remoteName, String remoteBranch){
        Object requestBody = pullFromRemotePayload(siteId, remoteName, remoteBranch, "none", false);
        api.post(PULL_FROM_REMOTE_URL).json(requestBody).execute().status(HttpStatus.SC_NOT_FOUND)
                .json("$.response.message", is("Project not found"));
    }

    public void testPullFromRemoteUnAuthorized(String siteId, String remoteName, String remoteBranch){
        Object requestBody = pullFromRemotePayload(siteId, remoteName, remoteBranch, "none", false);
        api.post(PULL_FROM_REMOTE_URL).json(requestBody).execute().status(HttpStatus.SC_UNAUTHORIZED);
    }

    public void testPushToRemote(String siteId, String remoteName, String remoteBranch){
        Object requestBody = pushToRemotePayload(siteId, remoteName, remoteBranch, true, false);
        api.post(PUSH_TO_REMOTE_URL).json(requestBody).execute().status(HttpStatus.SC_OK)
                .json("$.response.message", is("OK")).debug();
    }

    public void testPushToRemoteBadRequest(String siteId, String remoteName, String remoteBranch){
        Object requestBody = pushToRemotePayload(siteId, remoteName, remoteBranch, true, true);
        api.post(PUSH_TO_REMOTE_URL).json(requestBody).execute().status(HttpStatus.SC_BAD_REQUEST)
                .json("$.response.code", is(1001))
                .json("$.response.remedialAction", is("Check API and make sure you're sending the correct parameters"))
                .json("$.response.message", is("Invalid parameter(s)"));    }

    public void testPushToRemoteInvalidSiteId(String siteId, String remoteName, String remoteBranch ){
        Object requestBody = pushToRemotePayload(siteId, remoteName, remoteBranch, true, false);
        api.post(PUSH_TO_REMOTE_URL).json(requestBody).execute().status(HttpStatus.SC_NOT_FOUND)
                .json("$.response.message", is("Project not found"));
    }

    public void testPushToRemoteUnAuthorized(String siteId, String remoteName, String remoteBranch){
        Object requestBody = pushToRemotePayload(siteId, remoteName, remoteBranch, true, false);
        api.post(PUSH_TO_REMOTE_URL).json(requestBody).execute().status(HttpStatus.SC_UNAUTHORIZED);
    }

    public void testRebuildDBRemote(String siteId){
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("siteId", siteId);
        api.post(REBUILD_DATABASE_URL).json(requestBody).execute().status(HttpStatus.SC_OK)
                .json("$.response.message", is("OK"));
    }

    public void testRebuildDBRemoteBadRequest(String siteId){
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("siteIdBad", siteId);
        api.post(REBUILD_DATABASE_URL).json(requestBody).execute().status(HttpStatus.SC_BAD_REQUEST)
                .json("$.response.code", is(1001))
                .json("$.response.remedialAction", is("Check API and make sure you're sending the correct parameters"))
                .json("$.response.message", is("Invalid parameter(s)"));    }

    public void testRebuildDBRemoteInvalidSiteId(String siteId){
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("siteId", siteId);
        api.post(REBUILD_DATABASE_URL).json(requestBody).execute().status(HttpStatus.SC_NOT_FOUND)
                .json("$.response.message", is("Project not found"));
    }

    public void testRebuildDBRemoteUnAuthorized(String siteId){
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("siteId", siteId);
        api.post(REBUILD_DATABASE_URL).json(requestBody).execute().status(HttpStatus.SC_UNAUTHORIZED);
    }

    public void testRemoveRemote(String siteId, String remoteName){
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("siteId", siteId);
        requestBody.put("remoteName", remoteName);
        api.post(REMOVE_REMOTE_URL).json(requestBody).execute().status(HttpStatus.SC_OK)
                .json("$.response.message", is("OK"));
    }

    public void testRemoveRemoteBadRequest(String siteId, String remoteName){
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("siteIdBad", siteId);
        requestBody.put("remoteName", remoteName);
        api.post(REMOVE_REMOTE_URL).json(requestBody).execute().status(HttpStatus.SC_BAD_REQUEST)
                .json("$.response.code", is(1001))
                .json("$.response.remedialAction", is("Check API and make sure you're sending the correct parameters"))
                .json("$.response.message", is("Invalid parameter(s)"));
    }

    public void testRemoveRemoteInvalidSiteId(String siteId, String remoteName){
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("siteId", siteId);
        requestBody.put("remoteName", remoteName);
        api.post(REMOVE_REMOTE_URL).json(requestBody).execute().status(HttpStatus.SC_NOT_FOUND)
                .json("$.response.message", is("Project not found"));
    }

    public void testRemoveRemoteUnAuthorized(String siteId, String remoteName){
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("siteId", siteId);
        requestBody.put("remoteName", remoteName);
        api.post(REMOVE_REMOTE_URL).json(requestBody).execute().status(HttpStatus.SC_UNAUTHORIZED);
    }

    public void testListRemote(String siteId, String remoteName1, String url1, String remoteName2, String url2,
                               String remoteName3, String url3){
        api.get(LIST_REMOTE_URL).urlParam("siteId", siteId).execute().debug().status(HttpStatus.SC_OK)
                .json("$.response.message", is("OK"))
                .json("$..remotes[0].name", contains(remoteName1))
                .json("$..remotes[0].url", contains(url1))
                .json("$..remotes[1].name", contains(remoteName3))
                .json("$..remotes[1].url", contains(url3))
                .json("$..remotes[2].name", contains(remoteName2))
                .json("$..remotes[2].url", contains(url2));
    }

    public void testListRemoteInvalidSiteId(String siteId){
        api.post(LIST_REMOTE_URL).urlParam("siteId", siteId).execute().status(HttpStatus.SC_NOT_FOUND)
                .json("$.response.message", is("Project not found"));
    }

    public void testListRemoteUnAuthorized(String siteId){
        api.post(LIST_REMOTE_URL).urlParam("siteId", siteId).execute().status(HttpStatus.SC_UNAUTHORIZED);
    }
}
