package hu.tvseriesexam1.service;

import hu.tvseriesexam1.converter.TvSeriesConverter;
import hu.tvseriesexam1.dto.TvSeriesListItem;
import hu.tvseriesexam1.dto.TvSeriesRead;
import hu.tvseriesexam1.dto.TvSeriesSave;
import hu.tvseriesexam1.repository.TvSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TvSeriesService {

    @Autowired
    private TvSeriesRepository repository;

    public List<TvSeriesListItem> listItems() {
        return TvSeriesConverter.convertModelsToList(repository.findAll());
    }

    public TvSeriesRead readItem(int id) {
        if(!repository.existsById(id))
            throw new RuntimeException();
        return TvSeriesConverter.convertModelToRead(repository.getReferenceById(id));
    }

    public TvSeriesRead createItem(TvSeriesSave tvSeriesSave) {
        return TvSeriesConverter.convertModelToRead(repository.save(TvSeriesConverter.convertSaveToModel(tvSeriesSave)));
    }
}
