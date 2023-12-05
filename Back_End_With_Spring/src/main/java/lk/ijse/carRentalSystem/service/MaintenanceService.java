package lk.ijse.carRentalSystem.service;

import lk.ijse.carRentalSystem.dto.MaintenanceDTO;

import java.util.List;

public interface MaintenanceService {
    List<MaintenanceDTO> getAllMaintenance();

    void markAsMaintenance(long mId, String regNo);
}
