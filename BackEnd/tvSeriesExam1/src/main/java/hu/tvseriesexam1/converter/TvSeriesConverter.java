package hu.tvseriesexam1.converter;

import hu.tvseriesexam1.dto.TvSeriesListItem;
import hu.tvseriesexam1.dto.TvSeriesRead;
import hu.tvseriesexam1.dto.TvSeriesSave;
import hu.tvseriesexam1.model.TvSeries;

import java.util.ArrayList;
import java.util.List;

public class TvSeriesConverter {

    public static List<TvSeriesListItem> convertModelsToList(List<TvSeries> models) {
        List<TvSeriesListItem> tvSeriesListItems = new ArrayList<TvSeriesListItem>();
        models.forEach( model -> {tvSeriesListItems.add(convertModelToListItem(model));});
        return tvSeriesListItems;
    }

    private static TvSeriesListItem convertModelToListItem(TvSeries model) {
        TvSeriesListItem tvSeriesListItem = new TvSeriesListItem();
        tvSeriesListItem.setId(model.getId());
        tvSeriesListItem.setTitle(model.getTitle());
        tvSeriesListItem.setImdbRating(model.getImdbRating());
        return tvSeriesListItem;
    }

    public static TvSeriesRead convertModelToRead(TvSeries referenceById) {
        TvSeriesRead tvSeriesRead = new TvSeriesRead();
        tvSeriesRead.setId(referenceById.getId());
        tvSeriesRead.setTitle(referenceById.getTitle());
        tvSeriesRead.setImdbRating(referenceById.getImdbRating());
        tvSeriesRead.setGenre(referenceById.getGenre());
        tvSeriesRead.setPremiere(referenceById.getPremiere());
        return tvSeriesRead;
    }

    public static TvSeries convertSaveToModel(TvSeriesSave tvSeriesSave) {
        TvSeries tvSeries = new TvSeries();
        tvSeries.setTitle(tvSeriesSave.getTitle());
        tvSeries.setImdbRating(tvSeriesSave.getImdbRating());
        tvSeries.setGenre(tvSeriesSave.getGenre());
        tvSeries.setPremiere(tvSeriesSave.getPremiere());
        return tvSeries;
    }
}
