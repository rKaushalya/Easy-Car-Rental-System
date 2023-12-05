package lk.ijse.carRentalSystem.controller;

import lk.ijse.carRentalSystem.service.AdminService;
import lk.ijse.carRentalSystem.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping(path = "/check")
    public ResponseUtil checkAdminLogin(@RequestParam("username") String username, @RequestParam("password") String password){
        System.out.println(username);
        return new ResponseUtil("OK","Admin Authenticated.!",adminService.checkAdmin(username,password));
    }
}
