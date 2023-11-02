package lk.ijse.carRentalSystem.controller;

import lk.ijse.carRentalSystem.service.PaymentService;
import lk.ijse.carRentalSystem.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @GetMapping
    public ResponseUtil loadNewPaymentId(){
        return new ResponseUtil("OK","Successfully Loaded.!",paymentService.getNewPaymentId());
    }
}
