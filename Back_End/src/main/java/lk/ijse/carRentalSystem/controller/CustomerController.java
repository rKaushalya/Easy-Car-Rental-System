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

    @GetMapping
    public ResponseUtil searchAllCustomers(){
        return new ResponseUtil("OK","Successfully Loaded",customerService.getAllCustomers());
    }

    @GetMapping(path = "/id")
    public ResponseUtil getDriverId(){
        return new ResponseUtil("OK","Successfully get driver Id",customerService.getNewCustomerId());
    }

    @PostMapping
    public ResponseUtil addCustomer(@RequestParam("cusNICPhoto") MultipartFile cusNICPhoto,@RequestParam("cusDetails") String cusDetails) throws IOException {

        System.out.println(cusDetails);
        System.out.println(cusNICPhoto.getOriginalFilename());

        CustomerDTO customerDTO = objectMapper.readValue(cusDetails, CustomerDTO.class);
        System.out.println(customerDTO.getCusId());

        customerService.addCustomer(customerDTO,cusNICPhoto);

        return new ResponseUtil("Ok","Successfully Added",customerDTO.getCusId());
    }

    @DeleteMapping
    public ResponseUtil deleteCustomer(String cusId){
        customerService.deleteCustomer(cusId);
        return new ResponseUtil("OK","Successfully Deleted",cusId);
    }

    @PutMapping(path = "/password")
    public ResponseUtil customerPasswordUpdate(@RequestParam("id") String id,@RequestParam("password") String password){
        customerService.updateCustomerPassword(id,password);
        return new ResponseUtil("OK","successfully Updated",password);
    }

    @GetMapping(path = "/check")
    public ResponseUtil checkCustomer(String email,String password){
        return new ResponseUtil("OK","Customer authenticated.!",customerService.checkCustomerLogin(email,password));
    }
}
