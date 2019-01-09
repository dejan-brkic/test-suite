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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.FormBodyPartBuilder;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.fail;

/**
 * Created by cortiz on 3/15/17.
 */
public class JsonRequest {

	private final CloseableHttpClient httpClient;
	private final int port;
	private final String schema;
	private final BasicCookieStore cookieJar;
	private String host;
	private String type;
	private String path;
	private Map<String, String> urlParams;
	private Map<String, String> params;
	private Map<String, String> headers;
	private Map<String, File> files;
	private Object jsonParam;

	public JsonRequest(String schema, String host, int port, String path, String type,
			CloseableHttpClient httpClient, BasicCookieStore cookies) {
		this.host = host;
		this.path = path;
		this.type = type;
		this.port = port;
		this.schema = schema;
		this.urlParams = new HashMap<>();
		this.params = new HashMap<>();
		this.headers = new HashMap<>();
		this.files = new HashMap<>();
		this.httpClient = httpClient;
		this.cookieJar = cookies;
	}

	public JsonRequest headers(Map<String, String> headers) {
		for (String headerKey : headers.keySet()) {
			this.header(headerKey, headers.get(headerKey));
		}
		return this;
	}

	public JsonRequest header(String name, String value) {
		headers.put(name, value);
		return this;
	}

	public JsonRequest urlParam(String name, String value) {
		urlParams.put(name, value);
		return this;
	}

	public JsonRequest urlParams(Map<String, String> urlParams) {
		for (String paramKey : urlParams.keySet()) {
			this.urlParam(paramKey, urlParams.get(paramKey));
		}
		return this;
	}

	public JsonRequest cookie(String name, String value, Date expire, String domain, boolean isSecure,
			String cookiePath) {
		BasicClientCookie cookie = new BasicClientCookie(name, value);
		cookie.setPath(cookiePath);
		cookie.setExpiryDate(expire);
		cookie.setDomain(domain);
		cookie.setSecure(isSecure);
		cookieJar.addCookie(cookie);
		return this;
	}

	public JsonRequest param(String name, String value) {
		this.params.put(name, value);
		return this;
	}

	public JsonRequest params(Map<String, String> params) {
		for (String paramKey : urlParams.keySet()) {
			this.param(paramKey, urlParams.get(paramKey));
		}
		return this;
	}

	public JsonRequest json(Object json) {
		if (json != null) {
			this.jsonParam = json;
		}
		return this;
	}

	public JsonRequest file(String name, File file) {
		files.put(name, file);
		return this;
	}

	public JsonResponse execute() {
		try {
			HttpRequestBase request = null;
			if (this.type.equalsIgnoreCase("POST")) {
				request = new HttpPost();
				((HttpPost) request).setEntity(createPostParams(!files.isEmpty()));
			} else if (this.type.equalsIgnoreCase("DELETE")) {
				request = new HttpDelete();
			} else if (this.type.equalsIgnoreCase("PATCH")) {
				request = new HttpPatch();
				((HttpPatch) request).setEntity(createPostParams(!files.isEmpty()));
			} else {
				request = new HttpGet();
			}

			URI uri = buildURI();

			request.setURI(uri);
			CloseableHttpResponse response = httpClient.execute(request);
			return new JsonResponse(response, this.cookieJar);
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
			return null;// needed only for compiler
		}
	}

	protected HttpEntity createPostParams(boolean multipart) throws UnsupportedEncodingException {
		try {
			if (multipart) {
				return buildMultipart();
			} else {
				if (jsonParam != null) {
					return buildJsonEntity();
				} else {
					return buildFormParams();
				}
			}
		} catch (JsonProcessingException ex) {
			fail(ex.getMessage());
			return null;
		}
	}

	protected UrlEncodedFormEntity buildFormParams() throws UnsupportedEncodingException {
		ArrayList<NameValuePair> postParameters = new ArrayList<>();
		for (String param : params.keySet()) {
			postParameters.add(new BasicNameValuePair(param, params.get(param)));
		}
		return new UrlEncodedFormEntity(postParameters);
	}

	protected StringEntity buildJsonEntity() {
		try {
			return new StringEntity(new ObjectMapper().writeValueAsString(jsonParam),
					ContentType.APPLICATION_JSON);
		} catch (JsonProcessingException e) {
			fail(e.getMessage());
			return null;
		}
	}

	protected HttpEntity buildMultipart() throws UnsupportedEncodingException, JsonProcessingException {
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		if (!params.isEmpty()) {
			for (String s : params.keySet()) {
				FormBodyPartBuilder paramsBodyPartBuilder = FormBodyPartBuilder.create();
				paramsBodyPartBuilder.setName(s);
				paramsBodyPartBuilder.setBody(new StringBody(params.get(s), ContentType.TEXT_PLAIN));
				builder.addPart(paramsBodyPartBuilder.build());
			}
		}
		if (jsonParam != null) {
			FormBodyPartBuilder jsonBodyPartBuilder = FormBodyPartBuilder.create();
			jsonBodyPartBuilder.setBody(new StringBody(new ObjectMapper().writeValueAsString(jsonParam),
					ContentType.APPLICATION_JSON));
			builder.addPart(jsonBodyPartBuilder.build());
		}
		if (!files.isEmpty()) {
			for (String file : files.keySet()) {
				builder.addBinaryBody(file, files.get(file));
			}
		}
		return builder.build();
	}

	protected URI buildURI() throws URISyntaxException {
		URIBuilder builder = new URIBuilder();

		builder.setHost(this.host);
		builder.setPath(this.path);
		builder.setPort(this.port);
		builder.setScheme(this.schema);
		for (String key : urlParams.keySet()) {
			builder.addParameter(key, urlParams.get(key));
		}
		
		return builder.build();
	}

	public JsonResponse execute(Integer integer) {
		return null;
	}
}