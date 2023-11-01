package lk.ijse.carRentalSystem.service;

import lk.ijse.carRentalSystem.dto.BookingDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BookingService {
    String getNewBookingId();

    void addBooking(BookingDTO dto, MultipartFile file) throws IOException;
}
