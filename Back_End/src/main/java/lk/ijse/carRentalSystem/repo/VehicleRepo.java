package lk.ijse.carRentalSystem.repo;

import lk.ijse.carRentalSystem.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleRepo extends JpaRepository<Vehicle,String> {
    Vehicle getVehicleByRegisterNo(String id);

    Vehicle findByBrand(String name);

    List<Vehicle> getVehicleByType(String type);
}
