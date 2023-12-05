package lk.ijse.carRentalSystem.repo;

import lk.ijse.carRentalSystem.entity.VehicleDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleDetailsRepo extends JpaRepository<VehicleDetails,String> {
}
