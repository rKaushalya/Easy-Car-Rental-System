package lk.ijse.carRentalSystem.service.impl;

import lk.ijse.carRentalSystem.dto.BookingDTO;
import lk.ijse.carRentalSystem.entity.*;
import lk.ijse.carRentalSystem.repo.*;
import lk.ijse.carRentalSystem.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {
    @Autowired
    BookingRepo bookingRepo;

    @Autowired
    BookingDetailsRepo bookingDetailsRepo;

    @Autowired
    BookingSlipRepo bookingSlipRepo;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    VehicleRepo vehicleRepo;

    @Autowired
    DriverRepo driverRepo;

    private final String FOLDER_PATH = "C:\\Users\\ASUS\\Documents\\Car rental Images\\";

    @Override
    public String getNewBookingId() {
        return getNewBookId(bookingRepo.getLastBookingId());
    }

    @Override
    public void addBooking(BookingDTO dto, MultipartFile file) throws IOException {
        String filePath = FOLDER_PATH+file.getOriginalFilename();

        BookingSlip slip = new BookingSlip();
        slip.setFileName(file.getOriginalFilename());
        slip.setFilePath(filePath);
        slip.setFileType(file.getContentType());

        Customer c = customerRepo.getCustomerByName(dto.getCName());

        Booking booking = new Booking();
        booking.setBookingId(dto.getBId());
        booking.setBookingLocation(dto.getLocation());
        booking.setLossDamagePrice(dto.getLossDamagePrice());
        booking.setCarPrice(dto.getCarPrice());
        booking.setState("Pending");
        booking.setOnlineOrPhysical(dto.getOnlineOrPhysical());
        booking.setBookingSlip(slip);
        booking.setCustomer(c);

        Vehicle vehicle = vehicleRepo.getVehicleByRegisterNo(dto.getRegNo());
        BookingDetailsPK bookingDetailsPK = new BookingDetailsPK(dto.getBId(),dto.getRegNo());

        Driver driver;
        if (dto.getDriverState().equals("Yes")){
            driver = driverRepo.getDriverForBooking();
            driver.setAvailability("No");
            driverRepo.save(driver);
        }else {
            driver = new Driver();
        }

        BookingDetails details = new BookingDetails();
        details.setBookingDetailsPK(bookingDetailsPK);
        details.setBooking(booking);
        details.setVehicle(vehicle);
        details.setDriver(driver);
        details.setCarBookDate(dto.getCarBookDate());
        details.setPickupDate(dto.getPickupDate());

        bookingRepo.save(booking);
        bookingSlipRepo.save(slip);
        bookingDetailsRepo.save(details);

        file.transferTo(new File(filePath));
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
