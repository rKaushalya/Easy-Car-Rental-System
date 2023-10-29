package lk.ijse.carRentalSystem.repo;

import lk.ijse.carRentalSystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.ResultSet;

public interface CustomerRepo extends JpaRepository<Customer, String> {
    Customer findCustomerByName(String name);

    @Modifying
    @Query(value = "update customer set password=:pw where cId=:id",nativeQuery = true)
    void updateCustomerPassword(@Param("id") String id, @Param("pw") String password);
}
