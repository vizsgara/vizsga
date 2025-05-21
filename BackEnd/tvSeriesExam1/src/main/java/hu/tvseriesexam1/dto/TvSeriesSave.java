package hu.tvseriesexam1.dto;

import hu.tvseriesexam1.enumerated.Genre;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TvSeriesSave {
    @NotNull
    private String title;
    @NotNull
    @Max(2025)
    private int premiere;
    @NotNull
    private double imdbRating;
    @NotNull
    private Genre genre;
}
