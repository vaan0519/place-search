package com.here.api.placesearch.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.here.api.placesearch.model.placesearch.Item;
import com.here.api.placesearch.service.PlaceSearchService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import static com.here.api.placesearch.constants.constants.CITY;
import static com.here.api.placesearch.constants.constants.PLACE_SEARCH_URI;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PlaceSearchController.class)
@Import(PlaceSearchController.class)
public class PlaceSearchControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    @MockBean
    private PlaceSearchService placeSearchService;

    @Autowired
    private PlaceSearchController placeSearchController;

    public PlaceSearchControllerTest(){
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getNearbyPlaces() throws Exception {
        final String city = "Paris";
        File file = ResourceUtils.getFile("classpath:response.json");
        String responseJSON = new String(Files.readAllBytes(file.toPath()));
        ObjectMapper objectMapper  = new ObjectMapper();
        final List<Item> response = objectMapper.readValue(responseJSON,new TypeReference<List<Item>>(){});

        when(placeSearchService.getAllNearbyPlaces(city)).thenReturn(response);
        mockMvc.perform(get(PLACE_SEARCH_URI + CITY,city))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        verify(placeSearchService, times(1)).getAllNearbyPlaces(city);
    }
}