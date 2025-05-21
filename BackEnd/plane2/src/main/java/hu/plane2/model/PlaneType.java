package hu.plane2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class PlaneType {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;
}
