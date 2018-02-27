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

public class PasteItemAPITest {
	private SecurityAPI securityAPI;
	private SiteManagementAPI siteManagementAPI;
	private ClipboardAPI clipboardAPI;
	private ContentAssetAPI contentAssetAPI;
	private String siteId="pasteitemapitest";
	
	public PasteItemAPITest() {
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
		contentAssetAPI.testCreateFolder(siteId, "folder2");
		contentAssetAPI.testWriteContent(siteId, "site/website/folder1");
		clipboardAPI.testCopyItem(siteId);
	}

	@Test(priority = 1)
	public void testPasteItem() {
		clipboardAPI.testPasteItem(siteId);
	}
	
	@AfterTest
	public void afterTest() {
		siteManagementAPI.testDeleteSite(siteId);
		securityAPI.logOutFromStudioUsingAPICall();
	}
}
