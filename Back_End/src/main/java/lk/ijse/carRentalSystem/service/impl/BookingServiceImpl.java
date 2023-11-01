package lk.ijse.carRentalSystem.service.impl;

import lk.ijse.carRentalSystem.repo.BookingRepo;
import lk.ijse.carRentalSystem.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {
    @Autowired
    BookingRepo bookingRepo;

    @Override
    public String getNewBookingId() {
        return getNewBookId(bookingRepo.getLastBookingId());
    }

    public String getNewBookId(String currentBookId){
        if (currentBookId != null) {
            String[] split = currentBookId.split("B0");
            int id = Integer.parseInt(split[1]);
            id += 1;
            if (id >= 10) {
                return "B0" + id;
            }
            return "B00" + id;
        }
        return "B001";
    }
}
