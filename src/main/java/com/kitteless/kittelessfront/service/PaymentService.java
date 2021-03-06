package com.kitteless.kittelessfront.service;

import com.kitteless.kittelessfront.data.Charge;
import com.kitteless.kittelessfront.data.ChargeData;
import com.kitteless.kittelessfront.data.PaymentDataResponse;
import com.kitteless.kittelessfront.data.Stamp;
import com.kitteless.kittelessfront.repository.PaymentRepository;
import com.kitteless.kittelessfront.repository.PaypayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    public Stamp getStampWithPayment(String userId, int price) {
        PaymentDataResponse result = paymentRepository.postPayment(userId, price);

        if (result.getPaymentResult().equals("success")) {
            Stamp stamp = new Stamp();
            stamp.setCode(result.getStampCode());
            return stamp;
        }

        return null;
    }

    public List<Charge> getChargeData() {
        return paymentRepository.postCharge();
    }

    @Autowired
    PaypayRepository paypayRepository;

    public String paypay(String userId, int price) {

        String url = paypayRepository.call(userId, price);
        return url;
    }
}
