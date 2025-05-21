package hu.plane2.repo;

import hu.plane2.model.Plane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaneRepository extends JpaRepository<Plane, Integer> {
}
