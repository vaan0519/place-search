package com.here.api.placesearch.service;

import com.here.api.placesearch.model.placesearch.Item;

import java.util.List;

public interface PlaceSearchService {

    List<Item> getAllNearbyPlaces(String city) throws Exception;

}
