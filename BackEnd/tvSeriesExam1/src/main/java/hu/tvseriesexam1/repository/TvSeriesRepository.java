package hu.tvseriesexam1.repository;

import hu.tvseriesexam1.model.TvSeries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TvSeriesRepository extends JpaRepository<TvSeries, Integer> {
}
