package hu.plane2.dto;

import hu.plane2.model.PlaneType;
import lombok.Data;

import java.util.Date;

@Data
public class PlaneUpdate {
    private String airline;
    private Integer travelledDistance;
}
