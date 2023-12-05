package lk.ijse.carRentalSystem.repo;

import lk.ijse.carRentalSystem.entity.BookingDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingDetailsRepo extends JpaRepository<BookingDetails,String> {
}
