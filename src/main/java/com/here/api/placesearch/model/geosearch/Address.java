
package com.here.api.placesearch.model.geosearch;

import lombok.Data;

@Data
public class Address {
    private String label;
    private String countryCode;
    private String countryName;
    private String state;
    private String county;
    private String city;
    private String postalCode;
}
