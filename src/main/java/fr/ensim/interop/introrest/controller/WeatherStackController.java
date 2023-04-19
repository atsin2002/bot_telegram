package fr.ensim.interop.introrest.controller;

import model.openweather.Weather;
import model.openweather.WeatherStackException;
import model.openweather.WeatherStackResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WeatherStackController {

    private String apiKey = "f1ff106a4a6c33773122ce6af4ff6ef7";

    @GetMapping("/weather")
    public String getWeather(@RequestParam("city") String city) throws WeatherStackException {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://api.weatherstack.com/current?access_key=" + apiKey + "&query=" + city;
        WeatherStackResponse response = restTemplate.getForObject(apiUrl, WeatherStackResponse.class);
        if (response.getError() != null) {
            throw new WeatherStackException(response.getError());
        }
        return "La meteo à "+ city+" est : "+ response.getCurrent().getTemperature() + " degres";
    }
}
