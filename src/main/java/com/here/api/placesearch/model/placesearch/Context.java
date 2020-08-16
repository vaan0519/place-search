
package com.here.api.placesearch.model.placesearch;

import lombok.Data;

@Data
public class Context {
    private Location location;
    private String type;
    private String href;
}
