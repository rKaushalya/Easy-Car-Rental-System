package lk.ijse.carRentalSystem.repo;

import lk.ijse.carRentalSystem.entity.DriverDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverDetailsRepo extends JpaRepository<DriverDetails,String> {
}
