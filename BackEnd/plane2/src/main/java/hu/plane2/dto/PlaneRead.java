package hu.plane2.dto;

import hu.plane2.model.PlaneType;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Data
public class PlaneRead {
    private Integer id;
    private String typeName;
    private Date productionTime;
    private String airline;
    private int travelledDistance;
    private int passengers;
}
