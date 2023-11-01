package lk.ijse.carRentalSystem.repo;

import lk.ijse.carRentalSystem.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookingRepo extends JpaRepository<Booking,String> {
    @Query(value = "SELECT bookingId FROM booking ORDER BY bookingId DESC LIMIT 1",nativeQuery = true)
    String getLastBookingId();
}
