package lk.ijse.carRentalSystem.repo;

import lk.ijse.carRentalSystem.entity.BookingSlip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingSlipRepo extends JpaRepository<BookingSlip,String> {
}
