package lk.ijse.carRentalSystem.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lk.ijse.carRentalSystem.dto.VehicleDTO;
import lk.ijse.carRentalSystem.service.VehicleService;
import lk.ijse.carRentalSystem.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/vehicle")
public class VehicleController {
    @Autowired
    VehicleService vehicleService;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping
    public ResponseUtil saveVehicle(@RequestParam("data") String data,@RequestParam("front") MultipartFile front,@RequestParam("back") MultipartFile back,
                                    @RequestParam("side") MultipartFile side,@RequestParam("interior") MultipartFile interior) throws IOException {
        VehicleDTO vehicleDTO = objectMapper.readValue(data, VehicleDTO.class);
        vehicleService.addVehicle(vehicleDTO,front,back,side,interior);
        return new ResponseUtil("OK","Successfully Added.!",vehicleDTO.getRegisterNo());
    }
}
