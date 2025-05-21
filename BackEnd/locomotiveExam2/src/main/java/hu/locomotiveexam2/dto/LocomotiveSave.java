package hu.locomotiveexam2.dto;

import hu.locomotiveexam2.enumeration.Driving;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LocomotiveSave {

    @NotNull
    private String name;
    @NotNull
    private int lengthCm;
    @NotNull
    @Min(1)
    private double maxSpeed;
    @NotNull
    private Driving driving;
}
