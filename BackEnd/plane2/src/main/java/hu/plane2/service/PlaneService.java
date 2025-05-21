package hu.plane2.service;

import hu.plane2.converter.PlaneConverter;
import hu.plane2.dto.PlaneCreate;
import hu.plane2.dto.PlaneRead;
import hu.plane2.dto.PlaneUpdate;
import hu.plane2.model.Plane;
import hu.plane2.model.PlaneType;
import hu.plane2.repo.PlaneRepository;
import hu.plane2.repo.PlaneTypeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaneService {

    @Autowired
    private PlaneRepository repository;
    @Autowired
    private PlaneTypeRepository typeRepository;

    public PlaneRead create(PlaneCreate planeCreate) {
        if(!typeRepository.existsById(planeCreate.getTypeId()))
            throw new RuntimeException();
        PlaneType planeType = typeRepository.getReferenceById(planeCreate.getTypeId());
        Plane plane = PlaneConverter.convertCreateToModel(planeCreate, planeType);
        Plane savedPlane = repository.save(plane);
        return PlaneConverter.convertModelToRead(savedPlane);
    }

    public PlaneRead update(int id, PlaneUpdate planeUpdate) {
        if(!repository.existsById(id))
            throw new RuntimeException();
        Plane updatingPlane = repository.getReferenceById(id);
        if(planeUpdate.getAirline() != null)
            updatingPlane.setAirline(planeUpdate.getAirline());
        if(planeUpdate.getTravelledDistance() != null)
            updatingPlane.setTravelledDistance(planeUpdate.getTravelledDistance());
        return PlaneConverter.convertModelToRead(repository.save(updatingPlane));
    }

    public PlaneRead get(int id) {
        if(!repository.existsById(id))
            throw new RuntimeException();
        return PlaneConverter.convertModelToRead(repository.getReferenceById(id));
    }
}
