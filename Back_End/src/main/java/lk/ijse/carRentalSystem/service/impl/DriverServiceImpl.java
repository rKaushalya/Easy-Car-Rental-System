package lk.ijse.carRentalSystem.service.impl;

import lk.ijse.carRentalSystem.dto.DriverDTO;
import lk.ijse.carRentalSystem.entity.Driver;
import lk.ijse.carRentalSystem.entity.DriverDetails;
import lk.ijse.carRentalSystem.repo.DriverDetailsRepo;
import lk.ijse.carRentalSystem.repo.DriverRepo;
import lk.ijse.carRentalSystem.service.DriverService;
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
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverRepo driverRepo;

    @Autowired
    DriverDetailsRepo driverDetailsRepo;

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
                    driver.getCity(),driverDetails));
        }

        file.transferTo(new File(filePath));

    }

    @Override
    public String getLastDriverId() {
        List<String> lastDriverId = driverRepo.getLastDriverId();
        String driverId = "Not Working";
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
    public void updateDriver(Driver driver) {

    }

    @Override
    public void deleteDriver(String id) {

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
