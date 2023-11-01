package lk.ijse.carRentalSystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.ijse.carRentalSystem.service.BookingService;
import lk.ijse.carRentalSystem.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
