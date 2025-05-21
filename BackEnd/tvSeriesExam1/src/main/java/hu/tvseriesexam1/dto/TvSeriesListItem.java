package hu.tvseriesexam1.dto;

import lombok.Data;

@Data
public class TvSeriesListItem {
    private Integer id;
    private String title;
    private double imdbRating;
}
