package lk.ijse.carRentalSystem.service.impl;

import lk.ijse.carRentalSystem.dto.PaymentDTO;
import lk.ijse.carRentalSystem.entity.*;
import lk.ijse.carRentalSystem.repo.*;
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

    @Autowired
    BookingDetailsRepo bookingDetailsRepo;

    @Autowired
    MaintenanceRepo maintenanceRepo;

    @Autowired
    DriverRepo driverRepo;

    @Autowired
    VehicleRepo vehicleRepo;

    @Override
    public void makeAPayment(PaymentDTO dto) {
        System.out.println("test 2 " + dto.getBookingId());
        Booking book = bookingRepo.getBookingDetails(dto.getBookingId());
        book.setState("Complete");
        System.out.println("test 1 " + book.getOnlineOrPhysical());
        bookingRepo.save(book);

        List<BookingDetails> all = bookingDetailsRepo.findAll();

        for (BookingDetails b : all) {
            if (b.getBooking().getBookingId().equals(dto.getBookingId())) {
                Vehicle vehicle = vehicleRepo.getVehicleByRegisterNo(b.getVehicle().getRegisterNo());
                List<Maintenance> maintenances = maintenanceRepo.findAll();
                if (maintenances.isEmpty()){
                    Maintenance m2 = new Maintenance();
                    m2.setRunKm(vehicle.getFreeMileage());
                    m2.setVehicle(vehicle);
                    maintenanceRepo.save(m2);
                }else {
                    for (Maintenance m : maintenances) {
                        if (m.getVehicle().getRegisterNo().equals(vehicle.getRegisterNo())) {
                            int runKm = m.getRunKm();
                            m.setRunKm(runKm + vehicle.getFreeMileage());
                            maintenanceRepo.save(m);
                        }
                    }
                }
            }
        }

        PaymentDetails details = new PaymentDetails();
        details.setPaymentId(dto.getPaymentId());
        details.setDamagePrice(dto.getDamagePrice());
        details.setExtraKMPrice(dto.getExtraKMPrice());
        details.setForTheCar(dto.getForTheCar());
        details.setLateFee(dto.getLateFee());
        details.setBooking(book);

        paymentRepo.save(details);

        if (!dto.getDriverStatus().equals("No Need Driver")) {
            Driver driver = driverRepo.findDriverByDriverId(dto.getDriverStatus());
            driver.setAvailability("Yes");
            driverRepo.save(driver);
        }
    }

    @Override
    public String getNewPaymentId() {
        return getNewPId(paymentRepo.getLastPaymentId());
    }

    public String getNewPId(String currentPaymentId) {
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
