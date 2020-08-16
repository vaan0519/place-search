package com.here.api.placesearch.service;

import com.here.api.placesearch.client.PlaceSearchClient;
import com.here.api.placesearch.model.placesearch.Item;
import com.here.api.placesearch.model.placesearch.NearbyPlaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class PlaceSearchServiceImpl implements PlaceSearchService {

    @Autowired
    PlaceSearchClient placeSearchClient;

    @Override
    public List<Item> getAllNearbyPlaces(String city) throws Exception  {
        NearbyPlaces restaurants = getNearbyRestaurants(city).join();
        NearbyPlaces chargingStations = getNearbyChargingStations(city).join();
        NearbyPlaces parkingLots = getNearbyParkingLots(city).join();

        List<Item> restaurantsList = restaurants.getResults().getItems().stream().limit(3).collect(Collectors.toList());
        List<Item> chargingStationsList = chargingStations.getResults().getItems().stream().limit(3).collect(Collectors.toList());
        List<Item> parkingLotsList = parkingLots.getResults().getItems().stream().limit(3).collect(Collectors.toList());
        List<Item> nearbyPlaces = new ArrayList<>();
        nearbyPlaces.addAll(restaurantsList);
        nearbyPlaces.addAll(chargingStationsList);
        nearbyPlaces.addAll(parkingLotsList);
        return nearbyPlaces;
    }

    public CompletableFuture<NearbyPlaces> getNearbyRestaurants(String city) throws Exception {
        return placeSearchClient.getNearbyRestaurants(city);
    }

    public CompletableFuture<NearbyPlaces> getNearbyParkingLots(String city) throws Exception {
        return placeSearchClient.getNearbyParkingLots(city);
    }

    public CompletableFuture<NearbyPlaces> getNearbyChargingStations(String city) throws Exception{
        return placeSearchClient.getNearbyChargingStations(city);
    }
}



