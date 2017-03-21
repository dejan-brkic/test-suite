package org.craftercms.studio.test.utils;

import com.jayway.jsonassert.JsonAssert;
import com.jayway.jsonassert.JsonAsserter;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.hamcrest.Matcher;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class JsonResponse{

    private final CloseableHttpResponse httpResponse;
    private final JsonAsserter json;
    private final BasicCookieStore cookieJar;

    public JsonResponse(CloseableHttpResponse response, BasicCookieStore cookieJar) throws IOException {
        this.httpResponse =response;
        this.json= JsonAssert.with(response.getEntity().getContent());
        this.cookieJar=cookieJar;

    }

    public JsonResponse status(int httpStatus){
        assertEquals(httpResponse.getStatusLine().getStatusCode(),httpStatus);
        return this;
    }

   public JsonResponse header(String headerName,Matcher matcher){
       Header[] headers = httpResponse.getHeaders(headerName);
       assertThat(headerName, notNullValue());
       for (Header header : headers) {
           assertThat(header.getValue(),matcher);
       }
       return this;
   }

   public JsonResponse json(String jsonPath, Matcher matcher){
       json.assertThat(jsonPath,matcher);
       return this;
   }

   public JsonResponse cookie(String cookieName,Matcher matcher){
       for (Cookie cookie : cookieJar.getCookies()) {
           if(cookie.getName().equalsIgnoreCase(cookieName)){
               assertThat(cookie.getValue(),matcher);
               return this;
           }
       }
       fail("Cookie "+cookieName+" not found");
       return this;
   }

}
