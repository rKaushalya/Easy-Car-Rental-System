package lk.ijse.carRentalSystem.service.impl;

import lk.ijse.carRentalSystem.dto.PaymentDTO;
import lk.ijse.carRentalSystem.entity.Booking;
import lk.ijse.carRentalSystem.entity.PaymentDetails;
import lk.ijse.carRentalSystem.repo.BookingRepo;
import lk.ijse.carRentalSystem.repo.PaymentRepo;
import lk.ijse.carRentalSystem.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentRepo paymentRepo;

    @Autowired
    BookingRepo bookingRepo;

    @Override
    public void makeAPayment(PaymentDTO dto) {
        System.out.println("test 2 "+dto.getBookingId());
        Booking book = bookingRepo.getBookingDetails(dto.getBookingId());
        book.setState("Complete");
        System.out.println("test 1 "+book.getOnlineOrPhysical());
        bookingRepo.save(book);

        PaymentDetails details = new PaymentDetails();
        details.setPaymentId(dto.getPaymentId());
        details.setDamagePrice(dto.getDamagePrice());
        details.setExtraKMPrice(dto.getExtraKMPrice());
        details.setForTheCar(dto.getForTheCar());
        details.setLateFee(dto.getLateFee());
        details.setBooking(book);

        paymentRepo.save(details);
    }

    @Override
    public String getNewPaymentId() {
        return getNewPId(paymentRepo.getLastPaymentId());
    }

    public String getNewPId(String currentPaymentId){
        if (currentPaymentId != null) {
            String[] split = currentPaymentId.split("P0");
            int id = Integer.parseInt(split[1]);
            id += 1;
            if (id >= 10) {
                return "P0" + id;
            }
            return "P00" + id;
        }
        return "P001";
    }
}
