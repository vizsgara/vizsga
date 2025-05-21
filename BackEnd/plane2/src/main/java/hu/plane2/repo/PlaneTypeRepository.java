package hu.plane2.repo;

import hu.plane2.model.PlaneType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaneTypeRepository extends JpaRepository<PlaneType, Integer> {
}
