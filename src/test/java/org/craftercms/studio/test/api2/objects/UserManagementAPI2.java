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

/**
 * @author luishernandez
 *
 */
public class UserManagementAPI2 extends BaseAPI {

	public UserManagementAPI2(JsonTester api, APIConnectionManager apiConnectionManager) {
		super(api, apiConnectionManager);
	
	}

	public void testGetCurrentAuthenticatedUser() {
		api.get("/studio/api/2/users/me").execute().status(HttpStatus.SC_OK);
	}

	public void testGetCurrentAuthenticatedUserUnauthorized() {
		api.get("/studio/api/2/users/me").execute().status(HttpStatus.SC_UNAUTHORIZED);
	}
	
	public void testGetCurrentAuthenticatedUserSites() {
		api.get("/studio/api/2/users/me/sites").execute().status(HttpStatus.SC_OK);
	}

	public void testGetCurrentAuthenticatedUserSitesUnauthorized() {
		api.get("/studio/api/2/users/me/sites").execute().status(HttpStatus.SC_UNAUTHORIZED);
	}
	
	public void testGetCurrentAuthenticatedUserSiteRoles(String siteId) {
		api.get("/studio/api/2/users/me/sites/" + siteId + "/roles").execute()
				.status(HttpStatus.SC_OK);
	}

	public void testGetCurrentAuthenticatedUserSiteRolesUnauthorized(String siteId) {
		api.get("/studio/api/2/users/me/sites/" + siteId + "/roles").execute()
				.status(HttpStatus.SC_UNAUTHORIZED);
	}

}