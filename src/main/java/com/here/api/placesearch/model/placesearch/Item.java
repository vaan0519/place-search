
package com.here.api.placesearch.model.placesearch;

import lombok.Data;

import java.util.List;

@Data
public class Item {
    private List<Float> position;
    private Integer distance;
    private String title;
    private Float averageRating;
    private Category category;
    private String icon;
    private String vicinity;
    private List<Object> having;
    private String type;
    private List<Tag> tags;
    private String href;
    private String id;
    private OpeningHours openingHours;
    private List<AlternativeName> alternativeNames = null;
    private List<String> chainIds;

}
