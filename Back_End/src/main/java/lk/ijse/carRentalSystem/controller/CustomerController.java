package lk.ijse.carRentalSystem.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lk.ijse.carRentalSystem.dto.CustomerDTO;
import lk.ijse.carRentalSystem.service.CustomerService;
import lk.ijse.carRentalSystem.util.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping
    public ResponseUtil addCustomer(@RequestParam("cusNICPhoto") MultipartFile cusNICPhoto,@RequestParam("cusDetails") String cusDetails) throws IOException {

        System.out.println(cusDetails);
        System.out.println(cusNICPhoto.getOriginalFilename());

        CustomerDTO customerDTO = objectMapper.readValue(cusDetails, CustomerDTO.class);
        System.out.println(customerDTO.getCusId());

        customerService.addCustomer(customerDTO,cusNICPhoto);

        return new ResponseUtil("Ok","Successfully Added",null);
    }
}
