package lk.ijse.carRentalSystem.service.impl;

import lk.ijse.carRentalSystem.dto.DriverDTO;
import lk.ijse.carRentalSystem.dto.DriverScheduleDTO;
import lk.ijse.carRentalSystem.entity.BookingDetails;
import lk.ijse.carRentalSystem.entity.Driver;
import lk.ijse.carRentalSystem.entity.DriverDetails;
import lk.ijse.carRentalSystem.repo.BookingDetailsRepo;
import lk.ijse.carRentalSystem.repo.BookingRepo;
import lk.ijse.carRentalSystem.repo.DriverDetailsRepo;
import lk.ijse.carRentalSystem.repo.DriverRepo;
import lk.ijse.carRentalSystem.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverRepo driverRepo;

    @Autowired
    DriverDetailsRepo driverDetailsRepo;

    @Autowired
    BookingDetailsRepo bookingDetailsRepo;

    private final String FOLDER_PATH = "C:\\Users\\ASUS\\Documents\\Car rental Images\\";

    @Override
    public void AddDriver(DriverDTO driver, MultipartFile file) throws IOException {

        String filePath = FOLDER_PATH+file.getOriginalFilename();

        DriverDetails driverDetails = new DriverDetails();
        driverDetails.setLicenseNo(driver.getLicenseNo());
        driverDetails.setFileName(file.getOriginalFilename());
        driverDetails.setFilePath(filePath);
        driverDetails.setFileType(file.getContentType());

        if (driverDetailsRepo.existsById(driver.getLicenseNo())){
            throw new RuntimeException(driver.getLicenseNo()+" is already available, please insert a new License ID");
        }else {
            driverDetailsRepo.save(driverDetails);
        }

        if (driverRepo.existsById(driver.getDriverId())){
            throw new RuntimeException(driver.getDriverId()+" is already available, please insert a new Driver ID");
        }else {
            driverRepo.save(new Driver(driver.getDriverId(),driver.getName(),driver.getAddress(),driver.getDob(),
                    driver.getCity(),"Yes",driverDetails));
        }

        file.transferTo(new File(filePath));

    }

    @Override
    public String getLastDriverId() {
        List<String> lastDriverId = driverRepo.getLastDriverId();
        String driverId = null;
        for (String id : lastDriverId) {
            driverId=id;
        }
        return newDriverID(driverId);
    }

    @Override
    public List<DriverDTO> getAllDrivers() {
        List<Driver> all = driverRepo.findAll();
        List<DriverDTO> drivers = new ArrayList<>();
        for (Driver d: all) {
            drivers.add(new DriverDTO(d.getDriverId(),d.getName(),d.getAddress(),d.getDob(),d.getCity()
                    ,d.getDriverDetails().getLicenseNo()));
        }
        return drivers;
    }

    @Override
    public void updateDriver(DriverDTO driver, MultipartFile file) throws IOException {
        String filePath = FOLDER_PATH+file.getOriginalFilename();

        DriverDetails driverDetails = new DriverDetails();
        driverDetails.setLicenseNo(driver.getLicenseNo());
        driverDetails.setFileName(file.getOriginalFilename());
        driverDetails.setFilePath(filePath);
        driverDetails.setFileType(file.getContentType());

        if (driverDetailsRepo.existsById(driver.getLicenseNo())){
            driverDetailsRepo.save(driverDetails);
        }else {
            throw new RuntimeException(driver.getLicenseNo()+" This Driver Not available please check the id.!");
        }

        if (driverRepo.existsById(driver.getDriverId())){
            driverRepo.save(new Driver(driver.getDriverId(),driver.getName(),driver.getAddress(),driver.getDob(),
                    driver.getCity(),"Yes",driverDetails));
        }else {
            throw new RuntimeException(driver.getDriverId()+" This Driver Not available please check the id.!");
        }

        file.transferTo(new File(filePath));
    }

    @Override
    public void deleteDriver(String id) {
        if (driverRepo.existsById(id)){
            driverRepo.deleteById(id);
        }else {
            throw new RuntimeException(id+" this id not available please check the id before delete");
        }
    }

    @Override
    public DriverDTO findDriverByName(String name) {
        Driver driver = driverRepo.findDriverByName(name);
        return new DriverDTO(driver.getDriverId(),driver.getName(),driver.getAddress(),driver.getDob()
                ,driver.getCity(),driver.getDriverDetails().getLicenseNo());
    }

    @Override
    public boolean checkDriverLogin(String driverId) {
        Driver driver = driverRepo.findDriverByDriverId(driverId);
        return driver.getDriverId().equals(driverId);
    }

    @Override
    public DriverScheduleDTO loadDriverSchedule(String dId) {
        DriverScheduleDTO dto = new DriverScheduleDTO();
        List<BookingDetails> all = bookingDetailsRepo.findAll();
        for (BookingDetails b : all) {
            if (b.getDriver()!=null){
                if (b.getDriver().getDriverId().equals(dId) && b.getBooking().getState().equals("Accept")){
                    dto.setBookingDate(b.getCarBookDate());
                    dto.setCusName(b.getBooking().getCustomer().getName());
                    dto.setCusContact(b.getBooking().getCustomer().getContactNo());
                    dto.setPickupDate(b.getPickupDate());
                    dto.setCarName(b.getVehicle().getBrand());
                }
            }
        }
        return dto;
    }

    @Override
    public int getDriverCount() {
        int i = 0;
        List<Driver> all = driverRepo.findAll();
        for (Driver v: all) {
            i++;
        }
        return i;
    }

    public String newDriverID(String currentDriverId) {
        if (currentDriverId != null) {
            String[] split = currentDriverId.split("D0");
            int id = Integer.parseInt(split[1]);
            id += 1;
            if (id >= 10) {
                return "D0" + id;
            }
            return "D00" + id;
        }
        return "D001";
    }
}
