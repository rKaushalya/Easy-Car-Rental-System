package lk.ijse.carRentalSystem.repo;

import lk.ijse.carRentalSystem.entity.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDetailsRepo extends JpaRepository<CustomerDetails,String> {
}
