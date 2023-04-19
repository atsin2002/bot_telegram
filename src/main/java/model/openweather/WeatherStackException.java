package model.openweather;

public class WeatherStackException extends Exception {
    private String errorMessage;

    public WeatherStackException(String errorMessage) {
            super(errorMessage);
            this.errorMessage = errorMessage;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }

