package lk.ijse.carRentalSystem.repo;

import lk.ijse.carRentalSystem.entity.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepo extends JpaRepository<Maintenance,String> {
}
