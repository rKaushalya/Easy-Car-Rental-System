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
}
