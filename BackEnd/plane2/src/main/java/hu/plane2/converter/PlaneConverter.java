package hu.plane2.converter;

import hu.plane2.dto.PlaneCreate;
import hu.plane2.dto.PlaneRead;
import hu.plane2.model.Plane;
import hu.plane2.model.PlaneType;

public class PlaneConverter {
    public static Plane convertCreateToModel(PlaneCreate planeCreate, PlaneType planeType) {
        Plane plane = new Plane();
        plane.setAirline(planeCreate.getAirline());
        plane.setPassengers(planeCreate.getPassengers());
        plane.setType(planeType);
        plane.setProductionTime(planeCreate.getProductionTime());
        plane.setTravelledDistance(planeCreate.getTravelledDistance());
        return plane;
    }

    public static PlaneRead convertModelToRead(Plane savedPlane) {
        PlaneRead planeRead = new PlaneRead();
        planeRead.setId(savedPlane.getId());
        planeRead.setTypeName(savedPlane.getType().getName());
        planeRead.setProductionTime(savedPlane.getProductionTime());
        planeRead.setAirline(savedPlane.getAirline());
        planeRead.setTravelledDistance(savedPlane.getTravelledDistance());
        planeRead.setPassengers(savedPlane.getPassengers());
        return planeRead;
    }
}
