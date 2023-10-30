package lk.ijse.carRentalSystem.service.impl;

import lk.ijse.carRentalSystem.dto.VehicleDTO;
import lk.ijse.carRentalSystem.dto.VehicleShowDTO;
import lk.ijse.carRentalSystem.repo.VehicleDetailsRepo;
import lk.ijse.carRentalSystem.repo.VehicleRepo;
import lk.ijse.carRentalSystem.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    VehicleRepo vehicleRepo;

    @Autowired
    VehicleDetailsRepo vehicleDetailsRepo;

    @Override
    public void addVehicle(VehicleDTO dto, MultipartFile front, MultipartFile back, MultipartFile side, MultipartFile interior) {

    }

    @Override
    public List<VehicleShowDTO> getAllVehicle() {
        return null;
    }

    @Override
    public void deleteVehicle(String id) {

    }

    @Override
    public void updateVehicle(VehicleDTO dto, MultipartFile front, MultipartFile back, MultipartFile side, MultipartFile interior) {

    }
}
