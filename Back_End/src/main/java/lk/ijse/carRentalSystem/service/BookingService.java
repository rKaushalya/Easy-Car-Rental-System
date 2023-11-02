package lk.ijse.carRentalSystem.service;

import lk.ijse.carRentalSystem.dto.BookingDTO;
import lk.ijse.carRentalSystem.dto.BookingViewDTO;
import lk.ijse.carRentalSystem.entity.BookingDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BookingService {
    String getNewBookingId();

    void addBooking(BookingDTO dto, MultipartFile file) throws IOException;

    List<BookingViewDTO> getBookingDetailsForAdmin();
}
