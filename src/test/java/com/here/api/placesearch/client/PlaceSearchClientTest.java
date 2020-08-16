package com.here.api.placesearch.client;

import com.here.api.placesearch.model.LatitudeLongitude;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.PrintWriter;
import java.io.StringWriter;

import static com.here.api.placesearch.client.ClientConstants.*;
import static junit.framework.TestCase.assertEquals;

public class PlaceSearchClientTest extends Mockito {

    private CloseableHttpClient httpClient;
    private CloseableHttpResponse response;
    private HttpEntity entity;
    private StatusLine statusline;
    @Test
    void getNearbyRestaurants() {

    }

    @Test
    void getNearbyParkingLots() {
    }

    @Test
    void getNearbyChargingStations() {
    }

    @Test
    void getLocationFromAddress() {
    }

    @Test
    public void connectToHereApi() {
            Header[] mockedHeaders = null;
            UriComponents uri = UriComponentsBuilder.fromUriString("https://places.demo.api.here.com" + PLACE_URI)
                    .query(AT_PATH + "48.85302" + COMMA + "2.33874")
                    .queryParam(CATEGORY_PATH,"restaurent")
                    .queryParam(APP_ID_PATH,"devportal-demo-20180625")
                    .queryParam(APP_CODE_PATH,"9v2BkviRwi9Ot26kp2IysQ")
                    .build();
            try {
                this.httpClient = mock(CloseableHttpClient.class);
                this.response = mock(CloseableHttpResponse.class);
                this.entity = mock(HttpEntity.class);
                this.statusline = mock(StatusLine.class);
                HttpEntity mockedHttpEntity = Mockito.mock(HttpEntity.class);
                when(statusline.getStatusCode()).thenReturn(200);
                when(response.getStatusLine()).thenReturn(statusline);
                when(response.getEntity()).thenReturn(mockedHttpEntity);
                mockedHeaders = new Header[] { new BasicHeader("headerA", "valueA") };
                when(response.getAllHeaders()).thenReturn(mockedHeaders);
                when(httpClient.execute(Matchers.any(HttpGet.class))).thenReturn(response);
                HttpGet request = new HttpGet(uri.toString());
                org.apache.http.HttpResponse response = httpClient.execute(request);
                assertEquals(statusline, response.getStatusLine());
            } catch (Exception ex) {
                StringWriter errors = new StringWriter();
                ex.printStackTrace(new PrintWriter(errors));
                Assert.assertTrue(errors.toString(), false);
            }
        }

    @Test
    void getPlacesearchUrl() {
    }

    @Test
    void getAppId() {
    }

    @Test
    void getAppCode() {
    }

    @Test
    void getGeosearchUrl() {
    }

    @Test
    void getApiKey() {
    }

    @Test
    void setPlacesearchUrl() {
    }

    @Test
    void setAppId() {
    }

    @Test
    void setAppCode() {
    }

    @Test
    void setGeosearchUrl() {
    }

    @Test
    void setApiKey() {
    }
}