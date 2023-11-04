package lk.ijse.carRentalSystem.controller;

import lk.ijse.carRentalSystem.service.MaintenanceService;
import lk.ijse.carRentalSystem.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/maintenance")
public class MaintenanceController {
    @Autowired
    MaintenanceService maintenanceService;

    @GetMapping
    public ResponseUtil getAllMaintenance(){
        return new ResponseUtil("OK","Successfully Loaded.!",maintenanceService.getAllMaintenance());
    }

    @PostMapping
    public ResponseUtil updateVehicleMaintenance(@RequestParam("maintenanceId") long maintenanceId,@RequestParam("regNo") String regNo){
        maintenanceService.markAsMaintenance(maintenanceId,regNo);
        return new ResponseUtil("Ok","Successfully Updated.! ",regNo);
    }
}
