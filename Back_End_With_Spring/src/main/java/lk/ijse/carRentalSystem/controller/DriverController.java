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

    @GetMapping(path = "/name")
    public ResponseUtil findDriverByName(String name){
        return new ResponseUtil("OK","Successfully get all",driverService.findDriverByName(name));
    }

    @DeleteMapping
    public ResponseUtil deleteDriver(String id){
        driverService.deleteDriver(id);
        return new ResponseUtil("OK","Successfully Deleted.!",id);
    }

    @PutMapping
    public ResponseUtil updateDriver(@RequestParam("file") MultipartFile file,@RequestParam("driver") String data) throws IOException {
        DriverDTO driverDTO = objectMapper.readValue(data, DriverDTO.class);
        driverService.updateDriver(driverDTO,file);
        return new ResponseUtil("OK","Update Success.!",driverDTO.getDriverId());
    }

    @GetMapping(path = "/check")
    public ResponseUtil checkDriverLogin(String driverId){
        return new ResponseUtil("OK","Successfully get all",driverService.checkDriverLogin(driverId));
    }

    @GetMapping(path = "/schedule")
    public ResponseUtil getDriverSchedule(String driverId){
        return new ResponseUtil("OK","Successfully get Driver Schedule",driverService.loadDriverSchedule(driverId));
    }

    @GetMapping(path = "/count")
    public ResponseUtil getDriverCount(){
        return new ResponseUtil("OK","Successfully get Driver Count",driverService.getDriverCount());
    }
}
