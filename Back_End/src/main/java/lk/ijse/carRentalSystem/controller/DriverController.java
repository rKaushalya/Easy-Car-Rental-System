package lk.ijse.carRentalSystem.controller;

import lk.ijse.carRentalSystem.entity.Driver;
import lk.ijse.carRentalSystem.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Driver")
public class DriverController {

    @Autowired
    DriverService driverService;

    @PostMapping
    public void saveDriver(Driver driver){
        driverService.AddDriver(driver);
    }
}
