package lk.ijse.carRentalSystem.service;

import lk.ijse.carRentalSystem.dto.BookCountDTO;
import lk.ijse.carRentalSystem.dto.BookingDTO;
import lk.ijse.carRentalSystem.dto.BookingViewDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BookingService {
    String getNewBookingId();

    void addBooking(BookingDTO dto, MultipartFile file) throws IOException;

    List<BookingViewDTO> getBookingDetailsForAdmin();

    List<BookingViewDTO> getBookingDetailsForCustomer(String name);

    void updateBookingState(String bId,String state);

    String getCarPriceForPayment(String id);

    List<BookingViewDTO> getBookingDetailsForSelectedState(String state);

    BookCountDTO getBookingCount();
}
