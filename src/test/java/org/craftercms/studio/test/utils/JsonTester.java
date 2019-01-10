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

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;

public class JsonTester {

	private static final String CSRF_COOKIE_NAME = "XSRF-TOKEN";
	private static final String CSRF_COOKIE_PATH = "/studio";
	private static final String CSRF_HEADER_NAME = "X-XSRF-TOKEN";

	private final int port;
	private String host;
	private String schema;
	private CloseableHttpClient httpClient;
    private BasicCookieStore cookieJar;

	public JsonTester(String schema,String host, int port){
		this.host =host;
		this.port=port;
		this.schema=schema;
		this.cookieJar = new BasicCookieStore();

		String uuid = UUID.randomUUID().toString();
		BasicClientCookie cookie = new BasicClientCookie(CSRF_COOKIE_NAME, uuid);
		cookie.setDomain(host);
		cookie.setPath(CSRF_COOKIE_PATH);
		this.cookieJar.addCookie(cookie);

		List<BasicHeader> headers = Arrays.asList(new BasicHeader(CSRF_HEADER_NAME, uuid));

		this.httpClient = HttpClientBuilder.create()
											.setDefaultCookieStore(cookieJar).setDefaultHeaders(headers).build();
	}

	public JsonRequest get(String url){
	     return new JsonRequest(schema,host,port,url,"GET",httpClient,cookieJar);
	}
	public JsonRequest post(String url){
		return new JsonRequest(schema,host,port,url,"POST",httpClient,cookieJar);
	}
	public JsonRequest delete(String url){
		return new JsonRequest(schema,host,port,url,"DELETE",httpClient,cookieJar);
	}
	public JsonRequest patch(String url){
		return new JsonRequest(schema,host,port,url,"PATCH",httpClient,cookieJar);
	}
}
