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


import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

import org.apache.http.HttpStatus;
import org.craftercms.studio.test.api.objects.BaseAPI;
import org.craftercms.studio.test.utils.APIConnectionManager;
import org.craftercms.studio.test.utils.JsonTester;

/**
 * @author luishernandez
 *
 */
public class AuditAPI2 extends BaseAPI {

	private final String AUDIT_URL = "/studio/api/2/audit";

	public AuditAPI2(JsonTester api, APIConnectionManager apiConnectionManager) {
		super(api, apiConnectionManager);
	}

 	public void testGetAuditLog(String siteId) {		
   		api.get(AUDIT_URL).urlParam("siteId", siteId).execute().status(HttpStatus.SC_OK)
   				.json("$.response.message", is("OK"));
   	}

  	public void testGetAuditLogSiteNotFound(String siteId) {
  		api.get(AUDIT_URL).urlParam("siteId", siteId+"nonvalid").execute().status(HttpStatus.SC_NOT_FOUND)
				.json("$.response.message", is("Project not found"));
   	}
  	
  	public void testGetAuditLogUnauthorized(String siteId) {
  		api.get(AUDIT_URL).urlParam("siteId", siteId).execute().status(HttpStatus.SC_UNAUTHORIZED);
   	}

   	public void testGetAuditLogEntry(int id) {
		api.get(AUDIT_URL + "/" + id).execute().status(HttpStatus.SC_OK)
				.json("$.response.message", is("OK"))
				.json("$.auditLog.id", is(id))
				.json("$.auditLog.siteName", is("studio_root"));
	}

	public void testGetAuditLogByUser(String user) {
		api.get(AUDIT_URL).urlParam("user", user).execute().status(HttpStatus.SC_OK)
				.json("$..auditLog[0].actorId", contains(user))
				.json("$..auditLog[1].actorId", contains(user))
				.json("$..auditLog[2].actorId", contains(user))
				.json("$..auditLog[3].actorId", contains(user))
				.json("$..auditLog[4].actorId", contains(user))
				.json("$..auditLog[5].actorId", contains(user))
				.json("$..auditLog[6].actorId", contains(user))
				.json("$..auditLog[7].actorId", contains(user))
				.json("$..auditLog[8].actorId", contains(user))
				.json("$..auditLog[9].actorId", contains(user));
	}
}
