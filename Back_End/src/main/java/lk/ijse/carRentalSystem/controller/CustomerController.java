package lk.ijse.carRentalSystem.controller;

import lk.ijse.carRentalSystem.dto.CustomerDTO;
import lk.ijse.carRentalSystem.service.CustomerService;
import lk.ijse.carRentalSystem.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping
    public ResponseUtil addCustomer(@ModelAttribute CustomerDTO dto) throws IOException {
        System.out.println(dto.getCusId());
        System.out.println(dto.getCusAddress());
        customerService.addCustomer(dto);
        return new ResponseUtil("Ok","Successfully Added",dto);
    }
}
