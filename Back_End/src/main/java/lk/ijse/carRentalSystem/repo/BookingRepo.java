package lk.ijse.carRentalSystem.repo;

import lk.ijse.carRentalSystem.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookingRepo extends JpaRepository<Booking,String> {
    @Modifying
    @Query(value = "UPDATE booking SET state=:states WHERE bookingId=:bookId",nativeQuery = true)
    void updateBookingState(@Param("bookId") String bookId, @Param("states") String status);

    @Query(value = "SELECT bookingId FROM booking ORDER BY bookingId DESC LIMIT 1",nativeQuery = true)
    String getLastBookingId();

    @Query(value = "SELECT carPrice FROM booking WHERE bookingId=:id",nativeQuery = true)
    String getCarPrice(@Param("id") String id);

    @Query(value = "SELECT * FROM booking WHERE bookingId=:bId",nativeQuery = true)
    Booking getBookingDetails(@Param("bId") String bId);
}
