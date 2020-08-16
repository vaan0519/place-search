
package com.here.api.placesearch.model.placesearch;

import lombok.Data;

import java.util.List;

@Data
public class Results {
    private String next;
    private List<Item> items;

}
