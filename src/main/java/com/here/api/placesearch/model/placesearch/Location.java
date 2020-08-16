
package com.here.api.placesearch.model.placesearch;

import java.util.List;
import lombok.Data;

@Data
public class Location {
    private List<Float> position;
    private Address address;

}
