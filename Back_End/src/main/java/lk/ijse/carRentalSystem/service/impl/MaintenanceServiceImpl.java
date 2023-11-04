package lk.ijse.carRentalSystem.service.impl;

import lk.ijse.carRentalSystem.dto.MaintenanceDTO;
import lk.ijse.carRentalSystem.entity.Maintenance;
import lk.ijse.carRentalSystem.entity.Vehicle;
import lk.ijse.carRentalSystem.repo.MaintenanceRepo;
import lk.ijse.carRentalSystem.repo.VehicleRepo;
import lk.ijse.carRentalSystem.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MaintenanceServiceImpl implements MaintenanceService {
    @Autowired
    MaintenanceRepo maintenanceRepo;

    @Autowired
    VehicleRepo vehicleRepo;

    @Override
    public List<MaintenanceDTO> getAllMaintenance() {
        List<Maintenance> all = maintenanceRepo.findAll();
        List<MaintenanceDTO> md = new ArrayList<>();
        for (Maintenance m : all) {
            md.add(new MaintenanceDTO((int) m.getMId(),m.getRunKm(),m.getVehicle().getRegisterNo()));
        }
        return md;
    }

    @Override
    public void markAsMaintenance(String regNo) {
        Vehicle vehicle = vehicleRepo.getVehicleByRegisterNo(regNo);
        vehicle.setState("Maintenance");
        vehicleRepo.save(vehicle);
    }
}
