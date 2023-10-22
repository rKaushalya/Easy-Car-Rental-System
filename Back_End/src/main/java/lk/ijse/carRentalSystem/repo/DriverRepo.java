package lk.ijse.carRentalSystem.repo;

import lk.ijse.carRentalSystem.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepo extends JpaRepository<Driver,String> {
}
