package lk.ijse.carRentalSystem.controller;

import lk.ijse.carRentalSystem.dto.PaymentDTO;
import lk.ijse.carRentalSystem.service.PaymentService;
import lk.ijse.carRentalSystem.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseUtil addPaymentDetails(@RequestBody PaymentDTO dto){
        System.out.println("Booking Id is : "+dto.getBookingId());
        paymentService.makeAPayment(dto);
        return new ResponseUtil("OK","Payment Success.! ",dto.getPaymentId());
    }
}
