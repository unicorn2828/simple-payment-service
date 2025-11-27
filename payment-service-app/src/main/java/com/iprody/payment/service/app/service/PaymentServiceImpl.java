package com.iprody.payment.service.app.service;

import com.iprody.payment.service.app.dto.PaymentDto;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public PaymentDto getPayment(Long id) {
        return PaymentDto.builder()
                .id(1L)
                .value(99.99)
                .name("Payment #1")
                .build();
    }

}
