package lk.ijse.carRentalSystem.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lk.ijse.carRentalSystem.dto.BookingDTO;
import lk.ijse.carRentalSystem.service.BookingService;
import lk.ijse.carRentalSystem.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    BookingService bookingService;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping(path = "/id")
    public ResponseUtil loadNewBookingId(){
        return new ResponseUtil("OK","Booking Id Generated",bookingService.getNewBookingId());
    }

    @PostMapping
    public ResponseUtil addBooking(@RequestParam("slip") MultipartFile slip,@RequestParam("book") String book) throws IOException {
        BookingDTO bookingDTO = objectMapper.readValue(book, BookingDTO.class);
        System.out.println("Book ID : "+bookingDTO.getBookId());
        bookingService.addBooking(bookingDTO,slip);
        return new ResponseUtil("OK","Successfully Book.! ",bookingDTO.getBookId());
    }

    @GetMapping
    public ResponseUtil getAllBookings(){
        return new ResponseUtil("OK","Successfully loaded.!",bookingService.getBookingDetailsForAdmin());
    }
}
