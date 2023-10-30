package lk.ijse.carRentalSystem.repo;

import lk.ijse.carRentalSystem.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepo extends JpaRepository<Vehicle,String> {
}
