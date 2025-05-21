package hu.tvseriesexam1.model;

import hu.tvseriesexam1.enumerated.Genre;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class TvSeries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private int premiere;
    private double imdbRating;
    @Enumerated(EnumType.STRING)
    private Genre genre;
}
