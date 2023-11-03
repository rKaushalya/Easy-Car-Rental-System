package lk.ijse.carRentalSystem.service.impl;

import lk.ijse.carRentalSystem.entity.Admins;
import lk.ijse.carRentalSystem.repo.AdminRepo;
import lk.ijse.carRentalSystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepo adminRepo;

    @Override
    public boolean checkAdmin(String username, String password) {
        Admins admin = adminRepo.findByUserNameAndPassword(username, password);
        return admin.getUserName().equals(username);
    }
}
