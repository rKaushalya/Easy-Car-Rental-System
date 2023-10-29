package lk.ijse.carRentalSystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.ijse.carRentalSystem.dto.DriverDTO;
import lk.ijse.carRentalSystem.service.DriverService;
import lk.ijse.carRentalSystem.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    DriverService driverService;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping
    public ResponseUtil saveDriver(@RequestParam("file") MultipartFile file, @RequestParam("driver") String driver) throws IOException {

        DriverDTO driverDTO = objectMapper.readValue(driver, DriverDTO.class);
        driverService.AddDriver(driverDTO,file);

        return new ResponseUtil("OK","Driver added Successfully",driverDTO.getDriverId());
    }

    @GetMapping(path = "/id")
    public ResponseUtil getDriverId(){
        return new ResponseUtil("OK","Successfully get driver Id",driverService.getLastDriverId());
    }

    @GetMapping
    public ResponseUtil getAllDrivers(){
        return new ResponseUtil("OK","Successfully get all",driverService.getAllDrivers());
    }
}
