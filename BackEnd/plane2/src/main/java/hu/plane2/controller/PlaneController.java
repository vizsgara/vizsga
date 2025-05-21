package hu.plane2.controller;

import hu.plane2.dto.PlaneCreate;
import hu.plane2.dto.PlaneRead;
import hu.plane2.dto.PlaneUpdate;
import hu.plane2.service.PlaneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("plane")
@Tag(name="Managing planes")
public class PlaneController {

    @Autowired
    private PlaneService service;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new plane")
    public PlaneRead create(@RequestBody @Valid PlaneCreate planeCreate) {
        return service.create(planeCreate);
    }

    @PutMapping("{id}")
    @Operation(summary = "Update new plane")
    public PlaneRead update(@PathVariable int id, @RequestBody PlaneUpdate planeUpdate) {
        return service.update(id, planeUpdate);
    }

    @GetMapping("{id}")
    @Operation(summary = "Read selected pane")
    public PlaneRead get(@PathVariable int id) {
        return service.get(id);
    }

}
