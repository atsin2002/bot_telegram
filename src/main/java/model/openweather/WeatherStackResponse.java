package model.openweather;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WeatherStackResponse {
    private CurrentWeather current;

    public CurrentWeather getCurrent() {
        return current;
    }


    public void setCurrent(CurrentWeather current) {
        this.current = current;
    }
    public String getError() {
        if (current == null) {
            return "Aucune donnée météo disponible pour cette ville";
        } else {
            return null;
        }
    }


    public static class CurrentWeather {
        private String observationTime;
        private String temperature;
        private String weatherCode;
        private List<String> weatherDescriptions;

        @JsonProperty("observation_time")
        public String getObservationTime() {
            return observationTime;
        }

        @JsonProperty("observation_time")
        public void setObservationTime(String observationTime) {
            this.observationTime = observationTime;
        }

        public String getTemperature() {
            return temperature;
        }


        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        @JsonProperty("weather_code")
        public String getWeatherCode() {
            return weatherCode;
        }


        @JsonProperty("weather_code")
        public void setWeatherCode(String weatherCode) {
            this.weatherCode = weatherCode;
        }

        @JsonProperty("weather_descriptions")
        public List<String> getWeatherDescriptions() {
            return weatherDescriptions;
        }

        @JsonProperty("weather_descriptions")
        public void setWeatherDescriptions(List<String> weatherDescriptions) {
            this.weatherDescriptions = weatherDescriptions;
        }
    }
}
