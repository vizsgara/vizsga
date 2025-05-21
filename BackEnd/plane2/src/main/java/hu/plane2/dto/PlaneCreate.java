package hu.plane2.dto;

import hu.plane2.model.PlaneType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class PlaneCreate {
    @NotNull
    private Integer typeId;
    @NotNull
    private Date productionTime;
    @NotNull
    private String airline;
    @NotNull
    private int travelledDistance;
    @NotNull
    @Min(1)
    private int passengers;
}
