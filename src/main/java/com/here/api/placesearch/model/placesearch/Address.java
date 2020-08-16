
package com.here.api.placesearch.model.placesearch;

import lombok.Data;

@Data
public class Address {
    private String text;
    private String house;
    private String street;
    private String postalCode;
    private String district;
    private String city;
    private String county;
    private String stateCode;
    private String country;
    private String countryCode;
}
