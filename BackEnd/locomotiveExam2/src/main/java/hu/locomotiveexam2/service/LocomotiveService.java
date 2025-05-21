package hu.locomotiveexam2.service;

import hu.locomotiveexam2.converter.LocomotiveConverter;
import hu.locomotiveexam2.dto.LocomotiveListItem;
import hu.locomotiveexam2.dto.LocomotiveRead;
import hu.locomotiveexam2.dto.LocomotiveSave;
import hu.locomotiveexam2.repository.LocomotiveRepository;
import jakarta.validation.Valid;
import org.antlr.v4.runtime.tree.pattern.ParseTreePattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocomotiveService {

    @Autowired
    private LocomotiveRepository repository;

    public List<LocomotiveListItem> list() {
        return LocomotiveConverter.convertModelsToList(repository.findAll());
    }

    public LocomotiveRead getItem(int id) {
        if(!repository.existsById(id))
            throw new RuntimeException("nana");
        return LocomotiveConverter.convertModelToRead(repository.getReferenceById(id));
    }

    public LocomotiveRead createItem(LocomotiveSave save) {
        return LocomotiveConverter.convertModelToRead(repository.save(LocomotiveConverter.convertSaveToModel(save)));
    }
}
