package lk.ijse.carRentalSystem.service;

import lk.ijse.carRentalSystem.dto.DriverDTO;
import lk.ijse.carRentalSystem.entity.Driver;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DriverService {
    void AddDriver(DriverDTO driver, MultipartFile file) throws IOException;

    String getLastDriverId();

    List<DriverDTO> getAllDrivers();

    void updateDriver(Driver driver);

    void deleteDriver(String id);

    DriverDTO findDriverByName(String name);
}
