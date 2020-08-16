package com.here.api.placesearch.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.here.api.placesearch.model.LatitudeLongitude;
import com.here.api.placesearch.model.geosearch.GeoSearch;
import com.here.api.placesearch.model.placesearch.NearbyPlaces;
import lombok.Data;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import static com.here.api.placesearch.client.ClientConstants.*;
import static com.here.api.placesearch.constants.constants.*;

@Component
@Data
public class PlaceSearchClient {

    @Value("${here-place-search-api-service.placesearch.url}")
    private String placesearchUrl;

    @Value("${here-place-search-api-service.placesearch.appId}")
    private String appId;

    @Value("${here-place-search-api-service.placesearch.appCode}")
    private String appCode;

    @Value("${geocode-search-hereapi-service.url}")
    private String geosearchUrl;

    @Value("${geocode-search-hereapi-service.apiKey}")
    String apiKey;


    @Async
    public CompletableFuture<NearbyPlaces> getNearbyRestaurants(String city) throws Exception {
        LatitudeLongitude latitudeLongitude = getLocationFromAddress(city);
        NearbyPlaces nearbyPlaces = connectToHereApi(latitudeLongitude, RESTAURANT);
        return CompletableFuture.completedFuture(nearbyPlaces);
    }

    @Async
    public CompletableFuture<NearbyPlaces> getNearbyParkingLots(String city) throws Exception {
        LatitudeLongitude latitudeLongitude = getLocationFromAddress(city);
        NearbyPlaces nearbyPlaces = connectToHereApi(latitudeLongitude, PARKING_LOT);
        return CompletableFuture.completedFuture(nearbyPlaces);
    }

    @Async
    public CompletableFuture<NearbyPlaces> getNearbyChargingStations(String city) throws Exception {
        LatitudeLongitude latitudeLongitude = getLocationFromAddress(city);
        NearbyPlaces nearbyPlaces = connectToHereApi(latitudeLongitude, CHARGING_STATION);
        return CompletableFuture.completedFuture(nearbyPlaces);
    }

    public LatitudeLongitude getLocationFromAddress(String city) throws IOException {
        UriComponents uri = UriComponentsBuilder.fromUriString(geosearchUrl + GEOCODE_URI )
                .queryParam(QUERY,city)
                .queryParam(API_KEY_PATH,apiKey)
                .build();
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(uri.toString());
        org.apache.http.HttpResponse response = client.execute(request);
        org.apache.http.HttpEntity entity = response.getEntity();
        String entityString = EntityUtils.toString(entity);
        ObjectMapper mapper = new ObjectMapper();
        GeoSearch geoSearch = mapper.readValue(entityString, GeoSearch.class);
        LatitudeLongitude latitudeLongitude = new LatitudeLongitude();
        latitudeLongitude.setLatitude(geoSearch.getItems().get(0).getPosition().getLat());
        latitudeLongitude.setLongitude(geoSearch.getItems().get(0).getPosition().getLng());
        return latitudeLongitude;
    }

    public NearbyPlaces connectToHereApi(LatitudeLongitude latitudeLongitude,String category) throws IOException{
        UriComponents uri = UriComponentsBuilder.fromUriString(placesearchUrl + PLACE_URI)
                .query(AT_PATH + latitudeLongitude.getLatitude() + COMMA + latitudeLongitude.getLongitude())
                .queryParam(CATEGORY_PATH,category)
                .queryParam(APP_ID_PATH,appId)
                .queryParam(APP_CODE_PATH,appCode)
                .build();
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(uri.toString());
        org.apache.http.HttpResponse response = client.execute(request);
        org.apache.http.HttpEntity entity = response.getEntity();
        String entityString = EntityUtils.toString(entity);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(entityString, NearbyPlaces.class);
    }
}
