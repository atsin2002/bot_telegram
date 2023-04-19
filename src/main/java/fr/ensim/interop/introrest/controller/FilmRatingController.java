package fr.ensim.interop.introrest.controller;

import model.openweather.OmdbResponse;
import model.openweather.WeatherStackException;
import model.openweather.WeatherStackResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

public class FilmRatingController {
    private static final String BASE_URL = "http://www.omdbapi.com/";
    private static final String API_KEY = "d8e8cfcf";



    @GetMapping("/film")
    public String getPosterUrl(@RequestParam("titre") String titre) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://www.omdbapi.com/?apikey=d8e8cfcf" + "&t=" + titre;
        OmdbResponse response = restTemplate.getForObject(apiUrl, OmdbResponse.class);

        return response.getPosterUrl();
    }
    @GetMapping("/film")
    public String getPlot(@RequestParam("titre") String titre) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://www.omdbapi.com/?apikey=" + API_KEY + "&t=" + titre;
        OmdbResponse response = restTemplate.getForObject(apiUrl, OmdbResponse.class);

        return response.getPlot();
    }
}
