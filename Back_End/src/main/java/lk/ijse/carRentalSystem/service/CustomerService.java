package lk.ijse.carRentalSystem.service;

import lk.ijse.carRentalSystem.dto.CustomerDTO;
import lk.ijse.carRentalSystem.dto.LoginCustomerDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CustomerService {
    CustomerDTO searchCustomer(String id);

    List<CustomerDTO> getAllCustomers();

    void addCustomer(CustomerDTO customerDTO, MultipartFile cusNICPhoto) throws IOException;

    void updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(String id);

    void updateCustomerPassword(String id,String password);

    String getNewCustomerId();

    LoginCustomerDTO checkCustomerLogin(String email, String password);
}
