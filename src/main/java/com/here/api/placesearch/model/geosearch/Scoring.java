
package com.here.api.placesearch.model.geosearch;

import lombok.Data;

@Data
public class Scoring {
    private Float queryScore;
    private FieldScore fieldScore;
}
