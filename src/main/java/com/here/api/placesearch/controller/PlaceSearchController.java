package com.here.api.placesearch.controller;

import com.here.api.placesearch.model.placesearch.Item;
import com.here.api.placesearch.service.PlaceSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.here.api.placesearch.constants.constants.*;

@RestController(PLACE_SEARCH_CONTROLLER)
@RequestMapping(PLACE_SEARCH_URI)
@Slf4j
public class PlaceSearchController {
    @Autowired
    PlaceSearchService placeSearchService;

    @GetMapping(CITY)
    @ResponseStatus(HttpStatus.OK)
    @Cacheable(value = "users", key = "#city")
    public List<Item> getNearbyPlaces(@PathVariable("city") String city) throws Throwable {
        log.info("Getting nearby places of city {}.", city);
        return placeSearchService.getAllNearbyPlaces(city);
    }

}
