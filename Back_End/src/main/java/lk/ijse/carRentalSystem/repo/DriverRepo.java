package lk.ijse.carRentalSystem.repo;

import lk.ijse.carRentalSystem.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DriverRepo extends JpaRepository<Driver,String> {

    @Query(value = "SELECT driverId FROM driver ORDER BY driverId DESC LIMIT 1",nativeQuery = true)
    List<String> getLastDriverId();

    Driver findDriverByName(String name);
}
