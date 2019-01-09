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

package org.craftercms.studio.test.api.objects;

import static org.hamcrest.Matchers.is;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.craftercms.studio.test.utils.APIConnectionManager;
import org.craftercms.studio.test.utils.JsonResponse;
import org.craftercms.studio.test.utils.JsonTester;

public class ContentAssetAPI extends BaseAPI {

	private String contentPath = "/site/website";
	private String folderName = "newFolder";
	private String fileName = "index.xml";
	private String contentType = "/page/entry";

	public ContentAssetAPI(JsonTester api, APIConnectionManager apiConnectionManager) {
		super(api, apiConnectionManager);
	}

	public void testChangeContentType(String siteId) {

		api.post("/studio/api/1/services/api/1/content/change-content-type.json").urlParam("site", siteId)
				.urlParam("path", contentPath + "/" + fileName).urlParam("contentType", "/page/entry").execute()
				.status(HttpStatus.SC_OK).debug();
	}

	public void testContentExists(String siteId) {

		api.get("/studio/api/1/services/api/1/content/content-exists.json").urlParam("site", siteId)
				.urlParam("path", contentPath).execute().status(HttpStatus.SC_OK)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/content/content-exists.json?site="
								+ siteId + "&path=" + contentPath))
				.debug();
	}

	public void testCreateFolder(String siteId) {

		api.post("/studio/api/1/services/api/1/content/create-folder.json").urlParam("site", siteId)
				.urlParam("path", contentPath).urlParam("name", folderName).execute().status(HttpStatus.SC_OK).debug();
	}

	public void testCreateFolder(String siteId, String folderName) {

		api.post("/studio/api/1/services/api/1/content/create-folder.json").urlParam("site", siteId)
				.urlParam("path", contentPath).urlParam("name", folderName).execute().status(HttpStatus.SC_OK).debug();
	}

	public void testCreateFolderOnAPath(String siteId, String path, String folderName) {

		api.post("/studio/api/1/services/api/1/content/create-folder.json").urlParam("site", siteId)
				.urlParam("path", path).urlParam("name", folderName).execute().status(HttpStatus.SC_OK).debug();
	}

	public void testRenameFolder(String siteId) {

		api.post("/studio/api/1/services/api/1/content/rename-folder.json").urlParam("site", siteId)
				.urlParam("path", contentPath + "/" + folderName).urlParam("name", "newer" + folderName).execute()
				.status(HttpStatus.SC_OK).debug();
	}

	public void testGetContent(String siteId) {

		api.get("/studio/api/1/services/api/1/content/get-content.json").urlParam("site", siteId)
				.urlParam("path", contentPath + "/" + fileName).execute().status(HttpStatus.SC_OK)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/content/get-content.json?site=" + siteId
								+ "&path=" + contentPath + "/" + fileName))
				.debug();
	}

	public void testGetContentAtPath(String siteId) {

		api.get("/studio/api/1/services/api/1/content/get-content-at-path.json").urlParam("site", siteId)
				.urlParam("path", contentPath).execute().status(HttpStatus.SC_OK)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/content/get-content-at-path.json?site="
								+ siteId + "&path=" + contentPath))
				.debug();
	}

	public void testGetContentType(String siteId) {

		api.get("/studio/api/1/services/api/1/content/get-content-type.json").urlParam("site", siteId)
				.urlParam("type", contentType).execute().status(HttpStatus.SC_OK)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/content/get-content-type.json?site="
								+ siteId + "&type=" + contentType))
				.debug();
	}

	public void testGetContentTypes(String siteId) {

		api.get("/studio/api/1/services/api/1/content/get-content-types.json").urlParam("site", siteId)
				.urlParam("path", contentPath).execute().status(HttpStatus.SC_OK)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/content/get-content-types.json?site="
								+ siteId + "&path=" + contentPath))
				.debug();
	}

	public void testGetContentItem(String siteId) {

		api.get("/studio/api/1/services/api/1/content/get-item.json").urlParam("site", siteId)
				.urlParam("path", contentPath + "/" + fileName).execute().status(HttpStatus.SC_OK)
				.header("Location", is(headerLocationBase + "/studio/api/1/services/api/1/content/get-item.json?site="
						+ siteId + "&path=" + contentPath + "/" + fileName))
				.debug();
	}

	public void testGetItemOrders(String siteId) {

		api.get("/studio/api/1/services/api/1/content/get-item-orders.json").urlParam("site", siteId)
				.urlParam("path", contentPath + "/" + fileName).execute().status(HttpStatus.SC_OK)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/content/get-item-orders.json?site="
								+ siteId + "&path=" + contentPath + "/" + fileName))
				.debug();
	}

	public void testGetItemStates(String siteId) {

		api.get("/studio/api/1/services/api/1/content/get-item-states.json").urlParam("site", siteId)
				.urlParam("state", "ALL").execute().status(HttpStatus.SC_OK)
				.header("Location", is(headerLocationBase
						+ "/studio/api/1/services/api/1/content/get-item-states.json?site=" + siteId + "&state=ALL"))
				.debug();
	}

	public JsonResponse testGetItemVersions(String siteId) {

		return api.get("/studio/api/1/services/api/1/content/get-item-versions.json").urlParam("site", siteId)
				.urlParam("path", contentPath + "/" + fileName).execute().status(HttpStatus.SC_OK).header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/content/get-item-versions.json?site="
								+ siteId + "&path=" + contentPath + "/" + fileName));
	}

	public void testGetItemsTree(String siteId) {

		api.get("/studio/api/1/services/api/1/content/get-items-tree.json").urlParam("site", siteId)
				.urlParam("path", contentPath + "/" + fileName).urlParam("depth", "1").execute().status(HttpStatus.SC_OK)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/content/get-items-tree.json?site="
								+ siteId + "&path=" + contentPath + "/" + fileName + "&depth=1"))
				.debug();
	}

	public void testGetNextItemOrder(String siteId) {

		api.get("/studio/api/1/services/api/1/content/get-next-item-order.json").urlParam("site", siteId)
				.urlParam("parentpath", contentPath + "/" + fileName).execute().status(HttpStatus.SC_OK)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/content/get-next-item-order.json?site="
								+ siteId + "&parentpath=" + contentPath + "/" + fileName))
				.debug();
	}

	public void testGetPages(String siteId) {

		api.get("/studio/api/1/services/api/1/content/get-pages.json").urlParam("site", siteId)
				.urlParam("path", contentPath + "/" + fileName).urlParam("depth", "1").urlParam("order", "default")
				.execute().status(HttpStatus.SC_OK).debug();
	}

	public void testUnlockContent(String siteId) {

		api.get("/studio/api/1/services/api/1/content/unlock-content.json").urlParam("site", siteId)
				.urlParam("path", contentPath + "/" + fileName).execute().status(HttpStatus.SC_OK)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/content/unlock-content.json?site="
								+ siteId + "&path=" + contentPath + "/" + fileName))
				.debug();
	}

	public void testReorderContentItems(String siteId, String path, String after) {

		api.get("/studio/api/1/services/api/1/content/reorder-items.json").urlParam("site", siteId)
				.urlParam("path", path).urlParam("after", after).execute().status(HttpStatus.SC_OK)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/content/reorder-items.json?site=" + siteId
								+ "&path=" + path + "&after=" + after))
				.debug();
	}

	public void testRevertContentItem(String siteId) {

		String response = testGetItemVersions(siteId).getRaw();
		String versionNum = response.substring(response.lastIndexOf("versionNumber")).split("\"")[2];

		api.get("/studio/api/1/services/api/1/content/revert-content.json").urlParam("site", siteId)
				.urlParam("path", contentPath + "/" + fileName).urlParam("version", versionNum).execute().status(
						HttpStatus.SC_OK)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/content/reorder-items.json?site=" + siteId
								+ "&path=" + contentPath + "/" + fileName + "&version=" + versionNum))
				.debug();
	}

	public void testSearch(String siteId) {

		Map<String, Object> json = new HashMap<>();
		json.put("contentTypes", new String[0]);
		json.put("includeAspects", new String[0]);
		json.put("excludeAspects", new String[0]);
		json.put("keyword", "index");
		json.put("page", "1");
		json.put("pageSize", "20");
		json.put("sortBy", "");
		json.put("sortAscending", "true");
		json.put("filters", new int[0]);
		json.put("columns", new int[0]);

		api.post("/studio/api/1/services/api/1/content/search.json").urlParam("site", siteId).json(json).execute()
				.status(HttpStatus.SC_OK)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/content/search.json?site=" + siteId))
				.debug();
	}

	public void testSetItemState(String siteId) {

		api.post("/studio/api/1/services/api/1/content/set-item-state.json").urlParam("site", siteId)
				.urlParam("path", contentPath + "/" + fileName).urlParam("state", "EXISTING_UNEDITED_UNLOCKED")
				.urlParam("systemprocessing", "false").execute().status(HttpStatus.SC_OK).debug();
	}

	public void testWriteContent(String siteId) {

		File test = new File("src/test/resources/index.xml");

		api.post("/studio/api/1/services/api/1/content/write-content.json").param("site", siteId)
				.param("path", "site/website/").param("phase", "onSave").param("fileName", fileName)
				.param("contentType", contentType).param("unlock", "true").file("file", test).execute().status(HttpStatus.SC_OK)
				.debug();
	}
	
	public void testWriteContentOnFolder(String siteId, String path, String contentType, File content) {
		api.post("/studio/api/1/services/api/1/content/write-content.json").param("site", siteId)
				.param("path", path).param("fileName", content.getName())
				.param("contentType", contentType).param("unlock", "true").file("file", content).execute().status(HttpStatus.SC_OK)
				.debug();
	}

	public void testWriteContent(String siteId, String newPath) {

		File test = new File("src/test/resources/index.xml");

		api.post("/studio/api/1/services/api/1/content/write-content.json").param("site", siteId).param("path", newPath)
				.param("phase", "onSave").param("fileName", fileName).param("contentType", contentType)
				.param("unlock", "true").file("file", test).execute().status(HttpStatus.SC_OK).debug();
	}

	public void writeImageContent(String siteId) {

		File test = new File("src/test/resources/logo.png");

		api.post("/studio/api/1/services/api/1/content/write-content.json").param("site", siteId)
				.param("path", "static-assets/images/").param("phase", "onSave").param("fileName", "logo.png")
				.param("isImage", "true").param("unlock", "true").file("file", test).execute().debug();
	}

	public void testCropImage(String siteId) {

		api.get("/studio/api/1/services/api/1/content/crop-image.json").urlParam("site", siteId)
				.urlParam("path", "/static-assets/images/logo.png").urlParam("newname", "croppedlogo.png")
				.urlParam("t", "10").urlParam("l", "10").urlParam("w", "214").urlParam("h", "115").execute().status(HttpStatus.SC_OK)
				.debug();
	}

	public String getContentPath() {
		return contentPath;
	}

	public String getFolderName() {
		return folderName;
	}

	public String getFileName() {
		return fileName;
	}

}
