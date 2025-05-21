package hu.tvseriesexam1.controller;

import hu.tvseriesexam1.dto.TvSeriesListItem;
import hu.tvseriesexam1.dto.TvSeriesRead;
import hu.tvseriesexam1.dto.TvSeriesSave;
import hu.tvseriesexam1.service.TvSeriesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tv-series")
@Tag(name="Tv series")
public class TvSeriesController {

    @Autowired
    private TvSeriesService service;

    @GetMapping("list")
    @Operation(summary = "List items")
    public List<TvSeriesListItem> listItems(){
        return service.listItems();
    }

    @GetMapping("{id}")
    @Operation(summary = "Read item")
    public TvSeriesRead readItem( @PathVariable int id ){
        return service.readItem(id);
    }

    @PostMapping()
    @Operation(summary = "Create new item")
    public TvSeriesRead createItem( @RequestBody TvSeriesSave tvSeriesSave ){
        return service.createItem(tvSeriesSave);
    }

}
