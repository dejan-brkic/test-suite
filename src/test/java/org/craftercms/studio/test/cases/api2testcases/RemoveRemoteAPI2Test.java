package org.craftercms.studio.test.cases.api2testcases;

import org.craftercms.studio.test.api.objects.SecurityAPI;
import org.craftercms.studio.test.api.objects.SiteManagementAPI;
import org.craftercms.studio.test.api2.objects.RepositoryAPI2;
import org.craftercms.studio.test.utils.*;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 *
 * @author yacdaniel
 *
 */
public class RemoveRemoteAPI2Test {

    private SecurityAPI securityAPI;
    private RepositoryAPI2 repositoryAPI2;
    private SiteManagementAPI siteManagementAPI;
    private WebDriverManager webDriverManager;
    private ConstantsPropertiesManager constantsPropertiesManager;
    private String gitRepoUrl;
    private String gitRepoUrlSsh;
    private String gitUsername;
    private String gitPassword;
    private String gitToken;
    private String gitPrivateKey;
    private String siteId = "removeremoteapi2test";

    public RemoveRemoteAPI2Test() {
        APIConnectionManager apiConnectionManager = new APIConnectionManager();
        JsonTester api = new JsonTester(apiConnectionManager.getProtocol(), apiConnectionManager.getHost(),
                apiConnectionManager.getPort());
        securityAPI = new SecurityAPI(api, apiConnectionManager);
        siteManagementAPI = new SiteManagementAPI(api, apiConnectionManager);
        repositoryAPI2 = new RepositoryAPI2(api, apiConnectionManager);
        constantsPropertiesManager = new ConstantsPropertiesManager(FilesLocations.CONSTANTSPROPERTIESFILEPATH);
        webDriverManager = new WebDriverManager();
    }

    @BeforeTest
    public void beforeTest() {
        gitRepoUrl = constantsPropertiesManager.getSharedExecutionConstants()
                .getProperty("crafter.api2.repository.url.http");
        gitRepoUrlSsh = constantsPropertiesManager.getSharedExecutionConstants()
                .getProperty("crafter.api2.repository.url.ssh");
        gitUsername = constantsPropertiesManager.getSharedExecutionConstants()
                .getProperty("crafter.gitrepository.username");
        gitPassword = constantsPropertiesManager.getSharedExecutionConstants()
                .getProperty("crafter.gitrepository.password");
        gitToken = constantsPropertiesManager.getSharedExecutionConstants()
                .getProperty("crafter.gitrepository.token");
        gitPrivateKey = webDriverManager.getPrivateKeyContentFromPrivateKeyTestFile(FilesLocations.PRIVATEKEYCONTENTFILEPATH);

        securityAPI.logInIntoStudioUsingAPICall();
        siteManagementAPI.testCreateSite(siteId);
        repositoryAPI2.testAddRemote(siteId, "originbasic", gitRepoUrl, "basic",
                gitUsername, gitPassword, "", "");
        repositoryAPI2.testAddRemote(siteId, "origintoken", gitRepoUrl, "token",
                "", "", gitToken, "");
        repositoryAPI2.testAddRemote(siteId, "originkey", gitRepoUrlSsh, "key",
                "", "", "", gitPrivateKey);

    }

    @Test(groups = {"RemoveRemoteAPI2"})
    public void testRebuildDBRemote() {
        repositoryAPI2.testRemoveRemote(siteId, "originbasic");
        repositoryAPI2.testRemoveRemote(siteId, "origintoken");
        repositoryAPI2.testRemoveRemote(siteId, "originkey");
    }

    @Test(groups = {"RemoveRemoteAPI2"})
    public void testRebuildDBRemoteBadRequest() {
        repositoryAPI2.testRemoveRemoteBadRequest(siteId, "originbasic");
        repositoryAPI2.testRemoveRemoteBadRequest(siteId, "origintoken");
        repositoryAPI2.testRemoveRemoteBadRequest(siteId, "originkey");
    }

    @Test(groups = {"RemoveRemoteAPI2"})
    public void testRebuildDBRemoteInvalidSiteId() {
        repositoryAPI2.testRemoveRemoteInvalidSiteId(siteId + "invalid", "originbasic");
        repositoryAPI2.testRemoveRemoteInvalidSiteId(siteId + "invalid", "origintoken");
        repositoryAPI2.testRemoveRemoteInvalidSiteId(siteId + "invalid", "originkey");
    }

    @AfterGroups(groups = {"RemoveRemoteAPI2"})
    public void afterTest() {
        siteManagementAPI.testDeleteSite(siteId);
        securityAPI.logOutFromStudioUsingAPICall();
    }

    @Test(dependsOnGroups = {"RemoveRemoteAPI2"})
    public void testRebuildDBRemoteUnauthorized() {
        repositoryAPI2.testRemoveRemoteUnAuthorized(siteId, "originbasic");
        repositoryAPI2.testRemoveRemoteUnAuthorized(siteId, "origintoken");
        repositoryAPI2.testRemoveRemoteUnAuthorized(siteId, "originkey");
    }
}