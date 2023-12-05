package lk.ijse.carRentalSystem.repo;

import lk.ijse.carRentalSystem.entity.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentRepo extends JpaRepository<PaymentDetails,String> {
    @Query(value = "SELECT paymentId FROM paymentdetails ORDER BY paymentId DESC LIMIT 1",nativeQuery = true)
    String getLastPaymentId();
}
