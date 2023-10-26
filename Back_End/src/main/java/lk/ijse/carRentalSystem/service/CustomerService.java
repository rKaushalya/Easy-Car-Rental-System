package lk.ijse.carRentalSystem.service;

import lk.ijse.carRentalSystem.dto.CustomerDTO;

import java.io.IOException;
import java.util.List;

public interface CustomerService {
    CustomerDTO searchCustomer(String id);

    List<CustomerDTO> getAllCustomers();

    void addCustomer(CustomerDTO customerDTO) throws IOException;

    void updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(String id);
}
