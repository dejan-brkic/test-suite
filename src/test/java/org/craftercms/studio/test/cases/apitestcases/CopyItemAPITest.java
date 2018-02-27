package org.craftercms.studio.test.cases.apitestcases;

import org.craftercms.studio.test.api.objects.ClipboardAPI;
import org.craftercms.studio.test.api.objects.ContentAssetAPI;
import org.craftercms.studio.test.api.objects.SecurityAPI;
import org.craftercms.studio.test.api.objects.SiteManagementAPI;
import org.craftercms.studio.test.utils.APIConnectionManager;
import org.craftercms.studio.test.utils.JsonTester;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * @author chris lim
 *
 */

public class CopyItemAPITest {
	private SecurityAPI securityAPI;
	private SiteManagementAPI siteManagementAPI;
	private ClipboardAPI clipboardAPI;
	private ContentAssetAPI contentAssetAPI;
	private String siteId = "copyitemapitest";
	
	public CopyItemAPITest() {
		APIConnectionManager apiConnectionManager = new APIConnectionManager();
		JsonTester api = new JsonTester(apiConnectionManager.getProtocol(), apiConnectionManager.getHost(),
				apiConnectionManager.getPort());
		securityAPI = new SecurityAPI(api, apiConnectionManager);
		siteManagementAPI = new SiteManagementAPI(api, apiConnectionManager);
		clipboardAPI = new ClipboardAPI(api, apiConnectionManager);
		contentAssetAPI = new ContentAssetAPI(api, apiConnectionManager);
	}
	
	@BeforeTest
	public void beforeTest() {
		securityAPI.logInIntoStudioUsingAPICall();
		siteManagementAPI.testCreateSite(siteId);
		contentAssetAPI.testWriteContent(siteId, "site/website/folder1");
	}

	@Test(priority = 1)
	public void testCopyItem() {
		clipboardAPI.testCopyItem(siteId);
	}
	
	@AfterTest
	public void afterTest() {
		siteManagementAPI.testDeleteSite(siteId);
		securityAPI.logOutFromStudioUsingAPICall();
	}
}
