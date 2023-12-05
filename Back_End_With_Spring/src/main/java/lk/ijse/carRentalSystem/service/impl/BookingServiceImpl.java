package lk.ijse.carRentalSystem.service.impl;

import lk.ijse.carRentalSystem.dto.BookCountDTO;
import lk.ijse.carRentalSystem.dto.BookingDTO;
import lk.ijse.carRentalSystem.dto.BookingViewDTO;
import lk.ijse.carRentalSystem.entity.*;
import lk.ijse.carRentalSystem.repo.*;
import lk.ijse.carRentalSystem.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        Customer c = customerRepo.getCustomerByName(dto.getCustomerName());

        Booking booking = new Booking();
        booking.setBookingId(dto.getBookId());
        booking.setBookingLocation(dto.getLocation());
        booking.setLossDamagePrice(dto.getLossDamagePrice());
        booking.setCarPrice(dto.getCarPrice());
        booking.setState("Pending");
        booking.setOnlineOrPhysical(dto.getOnlineOrPhysical());
        booking.setBookingSlip(slip);
        booking.setCustomer(c);

        Vehicle vehicle = vehicleRepo.getVehicleByRegisterNo(dto.getRegisterNo());
        BookingDetailsPK bookingDetailsPK = new BookingDetailsPK(dto.getBookId(),dto.getRegisterNo());

        Driver driver;
        if (dto.getDriverState().equals("Yes")){
            driver = driverRepo.getDriverForBooking();
            driver.setAvailability("No");
            driverRepo.save(driver);
        }else {
            driver = null;
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

    @Override
    public List<BookingViewDTO> getBookingDetailsForAdmin() {
        List<BookingDetails> all = bookingDetailsRepo.findAll();
        List<BookingViewDTO> dto = new ArrayList<>();
        for (BookingDetails d : all) {
            String dId = "";
            if (d.getDriver()==null) {
                dId = "No Need Driver";
            }else {
                dId = d.getDriver().getDriverId();
            }
            String bookingState = d.getBooking().getState();
            dto.add(new BookingViewDTO(d.getBooking().getCustomer().getCId(),d.getBooking().getCustomer().getName(),
                    d.getBooking().getCustomer().getEmail(),d.getBooking().getCustomer().getContactNo(),d.getBooking().getCustomer().getAddress(),
                    d.getVehicle().getRegisterNo(),d.getBooking().getBookingId(),d.getCarBookDate(),d.getPickupDate(),dId,bookingState));

            System.out.println("This Booking is : "+bookingState);
        }
        return dto;
    }

    @Override
    public List<BookingViewDTO> getBookingDetailsForCustomer(String name) {
        List<BookingDetails> all = bookingDetailsRepo.findAll();
        List<BookingViewDTO> dto = new ArrayList<>();
        String dId = "";
        for (BookingDetails d : all) {
            if (d.getDriver()==null) {
                dId = "No Need Driver";
            }else {
                dId = d.getDriver().getDriverId();
            }
            String bookingState = d.getBooking().getState();
            if (d.getBooking().getCustomer().getName().equals(name)){
                dto.add(new BookingViewDTO(d.getBooking().getCustomer().getCId(),d.getBooking().getCustomer().getName(),
                        d.getBooking().getCustomer().getEmail(),d.getBooking().getCustomer().getContactNo(),d.getBooking().getCustomer().getAddress(),
                        d.getVehicle().getRegisterNo(),d.getBooking().getBookingId(),d.getCarBookDate(),d.getPickupDate(),dId,bookingState));
                System.out.println("This Booking is : "+bookingState);
            }
        }
        return dto;
    }

    @Override
    public void updateBookingState(String bId, String state) {
        bookingRepo.updateBookingState(bId,state);
    }

    @Override
    public String getCarPriceForPayment(String id) {
        return bookingRepo.getCarPrice(id);
    }

    @Override
    public List<BookingViewDTO> getBookingDetailsForSelectedState(String state) {
        List<BookingDetails> all = bookingDetailsRepo.findAll();
        List<BookingViewDTO> dto = new ArrayList<>();
        String dId = "";
        for (BookingDetails d : all) {
            if (d.getBooking().getState().equals(state)) {
                if (d.getDriver() == null) {
                    dId = "No Need Driver";
                } else {
                    dId = d.getDriver().getDriverId();
                }
                String bookingState = d.getBooking().getState();
                dto.add(new BookingViewDTO(d.getBooking().getCustomer().getCId(), d.getBooking().getCustomer().getName(),
                        d.getBooking().getCustomer().getEmail(), d.getBooking().getCustomer().getContactNo(), d.getBooking().getCustomer().getAddress(),
                        d.getVehicle().getRegisterNo(), d.getBooking().getBookingId(), d.getCarBookDate(), d.getPickupDate(), dId, bookingState));

                System.out.println("This Booking is : " + bookingState);
            }
        }
        return dto;
    }

    @Override
    public BookCountDTO getBookingCount() {
        int i = 0;
        int j = 0;
        List<Booking> all = bookingRepo.findAll();
        for (Booking b: all) {
            i++;
            if (b.getState().equals("Pending")){
                j++;
            }
        }
        return new BookCountDTO(i,j);
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
