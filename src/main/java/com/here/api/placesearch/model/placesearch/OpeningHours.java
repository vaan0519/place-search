
package com.here.api.placesearch.model.placesearch;

import java.util.List;
import lombok.Data;

@Data
public class OpeningHours {
    private String text;
    private String label;
    private Boolean isOpen;
    private List<Structured> structured;

}
