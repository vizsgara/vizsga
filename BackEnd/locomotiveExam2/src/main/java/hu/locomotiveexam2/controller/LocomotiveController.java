package hu.locomotiveexam2.controller;

import hu.locomotiveexam2.dto.LocomotiveListItem;
import hu.locomotiveexam2.dto.LocomotiveRead;
import hu.locomotiveexam2.dto.LocomotiveSave;
import hu.locomotiveexam2.service.LocomotiveService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/locomotive")
@Tag(name="Locomotive functions")
public class LocomotiveController {

    @Autowired
    private LocomotiveService service;

    @GetMapping("/list")
    @Operation(summary = "List locomotives")
    public List<LocomotiveListItem> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Read locomotive")
    public LocomotiveRead getItem(@PathVariable int id) {
        return service.getItem(id);
    }

    @PostMapping("/")
    @Operation(summary = "Save locomotive")
    public LocomotiveRead createItem(@RequestBody @Valid LocomotiveSave save) {
        return service.createItem(save);
    }
}
