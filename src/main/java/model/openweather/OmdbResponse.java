package model.openweather;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)

public class OmdbResponse {

    private String plot;

    private String posterUrl;




    @JsonProperty("Plot")
    public String getPlot() {
        return plot;
    }

    @JsonProperty("Plot")
    public void setPlot(String plot) {
        this.plot = plot;
    }


    @JsonProperty("Poster")
    public String getPosterUrl() {
        return posterUrl;
    }

    @JsonProperty("Poster")
    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }




}
