package lk.ijse.carRentalSystem.service.impl;

import lk.ijse.carRentalSystem.entity.Driver;
import lk.ijse.carRentalSystem.repo.DriverRepo;
import lk.ijse.carRentalSystem.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverRepo driverRepo;

    @Override
    public void AddDriver(Driver driver){
        driverRepo.save(driver);
    }
}
