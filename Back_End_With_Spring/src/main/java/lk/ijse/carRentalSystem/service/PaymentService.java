package lk.ijse.carRentalSystem.service;

import lk.ijse.carRentalSystem.dto.PaymentDTO;

public interface PaymentService {
    void makeAPayment(PaymentDTO dto);

    String getNewPaymentId();
}
