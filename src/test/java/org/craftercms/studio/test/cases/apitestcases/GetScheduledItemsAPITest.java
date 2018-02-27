package org.craftercms.studio.test.cases.apitestcases;

import org.craftercms.studio.test.api.objects.ContentAssetAPI;
import org.craftercms.studio.test.api.objects.SecurityAPI;
import org.craftercms.studio.test.api.objects.SiteManagementAPI;
import org.craftercms.studio.test.api.objects.DeploymentAPI;
import org.craftercms.studio.test.utils.APIConnectionManager;
import org.craftercms.studio.test.utils.JsonTester;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GetScheduledItemsAPITest {
	
	private SecurityAPI securityAPI;
	private SiteManagementAPI siteManagementAPI;
	private DeploymentAPI deploymentAPI;
	private ContentAssetAPI contentAssetAPI;
	private String siteId="getscheduleditemsapitest";
	public GetScheduledItemsAPITest() {
		APIConnectionManager apiConnectionManager = new APIConnectionManager();
		JsonTester api = new JsonTester(apiConnectionManager.getProtocol(), apiConnectionManager.getHost(),
				apiConnectionManager.getPort());
		securityAPI = new SecurityAPI(api, apiConnectionManager);
		siteManagementAPI = new SiteManagementAPI(api, apiConnectionManager);
		deploymentAPI = new DeploymentAPI(api, apiConnectionManager);
		contentAssetAPI = new ContentAssetAPI(api, apiConnectionManager);
	}
	
	@BeforeTest
	public void beforeTest() {
		securityAPI.logInIntoStudioUsingAPICall();
		siteManagementAPI.testCreateSite(siteId);
		contentAssetAPI.testWriteContent(siteId);
	}

	@Test(priority = 1)
	public void testGetDeploymentHistory() {
		deploymentAPI.testGetScheduledItems(siteId);
	}
	
	@AfterTest
	public void afterTest() {
		siteManagementAPI.testDeleteSite(siteManagementAPI.getSiteId());
		securityAPI.logOutFromStudioUsingAPICall();
	}
}
