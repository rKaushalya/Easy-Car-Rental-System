package lk.ijse.carRentalSystem.service.impl;

import lk.ijse.carRentalSystem.dto.CustomerDTO;
import lk.ijse.carRentalSystem.entity.Customer;
import lk.ijse.carRentalSystem.entity.CustomerDetails;
import lk.ijse.carRentalSystem.repo.CustomerDetailsRepo;
import lk.ijse.carRentalSystem.repo.CustomerRepo;
import lk.ijse.carRentalSystem.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    CustomerDetailsRepo customerDetailsRepo;

    @Autowired
    ModelMapper mapper;

    private final String FOLDER_PATH = "C:\\Users\\ASUS\\Documents\\Car rental Images\\";

    @Override
    public CustomerDTO searchCustomer(String id) {
       /* Customer customer = customerRepo.findById(id).get();
        return mapper.map(customer,CustomerDTO.class);*/
        return new CustomerDTO();
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
       /* List<Customer> customers = customerRepo.findAll();
        return mapper.map(customers,new TypeToken<List<CustomerDTO>>(){}.getType());*/
        return new ArrayList<>();
    }

    @Override
    public void addCustomer(CustomerDTO customerDTO) throws IOException {
        if (customerRepo.existsById(customerDTO.getCusId())) {
            throw new RuntimeException(customerDTO.getCusId()+" is already available, please insert a new ID");
        }else {

            String filePath = FOLDER_PATH+customerDTO.getCusNICPhoto().getOriginalFilename();

            CustomerDetails customerDetails = new CustomerDetails();
            customerDetails.setNicNo(customerDTO.getCusNIC());
            customerDetails.setFileName(customerDTO.getCusNICPhoto().getOriginalFilename());
            customerDetails.setFilePath(filePath);
            customerDetails.setFileType(customerDTO.getCusNICPhoto().getContentType());

            Customer customer = new Customer();
            customer.setCId(customerDTO.getCusId());
            customer.setName(customerDTO.getCusName());
            customer.setAddress(customerDTO.getCusAddress());
            customer.setEmail(customerDTO.getCusEmail());
            customer.setPassword(customerDTO.getCusPassword());
            customer.setContactNo(customerDTO.getCusContact());
            customer.setCustomerDetails(customerDetails);

            customerDetailsRepo.save(customerDetails);

            customerRepo.save(customer);

            customerDTO.getCusNICPhoto().transferTo(new File(filePath));
        }
    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO) {
        /*if (customerRepo.existsById(customerDTO.getCusId())) {
            customerRepo.save(mapper.map(customerDTO,Customer.class));
        }else {
            throw new RuntimeException(customerDTO.getCusId()+" Customer is not available, please check the ID before Update.!");
        }*/
    }

    @Override
    public void deleteCustomer(String id) {
        customerRepo.deleteById(id);
    }
}
