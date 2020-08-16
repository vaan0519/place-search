
package com.here.api.placesearch.model.geosearch;

import lombok.Data;

@Data
public class Item {
    private String title;
    private String id;
    private String resultType;
    private String localityType;
    private Address address;
    private MapView mapView;
    private Scoring scoring;
    private Position position;
}
