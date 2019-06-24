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

import com.jayway.jsonassert.JsonAssert;
import com.jayway.jsonassert.JsonAsserter;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matcher;
import java.io.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class JsonResponse {

	private final CloseableHttpResponse httpResponse;
	private final JsonAsserter json;
	private final BasicCookieStore cookieJar;
	private final ByteArrayInputStream raw;
	private static final Logger logger = LogManager.getLogger(JsonResponse.class);

	public JsonResponse(CloseableHttpResponse response, BasicCookieStore cookieJar) throws IOException {
		this.httpResponse = response;
		if (response.getStatusLine().getStatusCode() != 204) {
			raw = new ByteArrayInputStream(IOUtils.toByteArray(response.getEntity().getContent()));
			this.json = JsonAssert.with(raw);
			raw.reset();
		} else {
			raw = new ByteArrayInputStream(new byte[] {});
			json = JsonAssert.with("{}");
		}
		this.cookieJar = cookieJar;

	}

	public JsonResponse status(int httpStatus) {
		assertEquals(httpResponse.getStatusLine().getStatusCode(), httpStatus);
		return this;
	}

	public JsonResponse header(String headerName, Matcher matcher) {
		Header[] headers = httpResponse.getHeaders(headerName);
		assertThat(headerName, notNullValue());
		for (Header header : headers) {
			assertThat(header.getValue(), matcher);
		}
		return this;
	}

	public JsonResponse debug() {
		try {
			for (Header header : httpResponse.getAllHeaders()) {
				logger.info("{}={}", header.getName(), header.getValue());
			}
			logger.info(IOUtils.toString(raw));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}

	public JsonResponse json(String jsonPath, Matcher matcher) {
		json.assertThat(jsonPath, matcher);
		return this;
	}

	public JsonResponse cookie(String cookieName, Matcher matcher) {
		for (Cookie cookie : cookieJar.getCookies()) {
			if (cookie.getName().equalsIgnoreCase(cookieName)) {
				assertThat(cookie.getValue(), matcher);
				return this;
			}
		}
		fail("Cookie " + cookieName + " not found");
		return this;
	}

	public String getRaw(){

		try {
			 return IOUtils.toString(raw);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
	}
}
