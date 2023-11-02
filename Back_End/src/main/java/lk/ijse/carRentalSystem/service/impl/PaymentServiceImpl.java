package lk.ijse.carRentalSystem.service.impl;

import lk.ijse.carRentalSystem.dto.PaymentDTO;
import lk.ijse.carRentalSystem.repo.PaymentRepo;
import lk.ijse.carRentalSystem.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentRepo paymentRepo;

    @Override
    public void makeAPayment(PaymentDTO dto) {

    }

    @Override
    public String getNewPaymentId() {
        return getNewPId(paymentRepo.getLastPaymentId());
    }

    public String getNewPId(String currentPaymentId){
        if (currentPaymentId != null) {
            String[] split = currentPaymentId.split("P0");
            int id = Integer.parseInt(split[1]);
            id += 1;
            if (id >= 10) {
                return "P0" + id;
            }
            return "P00" + id;
        }
        return "P001";
    }
}
