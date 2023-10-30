package lk.ijse.carRentalSystem.service;

import lk.ijse.carRentalSystem.dto.VehicleDTO;
import lk.ijse.carRentalSystem.dto.VehicleShowDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface VehicleService {
    void addVehicle(VehicleDTO dto, MultipartFile front, MultipartFile back, MultipartFile side, MultipartFile interior) throws IOException;

    List<VehicleShowDTO> getAllVehicle();

    void deleteVehicle(String id);

    void updateVehicle(VehicleDTO dto, MultipartFile front, MultipartFile back, MultipartFile side, MultipartFile interior);
}
