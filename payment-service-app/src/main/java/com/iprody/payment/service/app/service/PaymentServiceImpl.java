package com.iprody.payment.service.app.service;

import com.iprody.payment.service.app.dto.PaymentDto;
import com.iprody.payment.service.app.service.exception.Error;
import com.iprody.payment.service.app.service.exception.ServiceException;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentDto dto = PaymentDto.builder()
            .id(1L)
            .value(99.99)
            .name("Payment #1")
            .build();

    @Override
    public PaymentDto getPayment(Long id) {

        validate(id);
        if (!id.equals(dto.getId())) {
            throw new ServiceException(Error.PAYMENT_NOT_EXIST, id);
        }

        return dto;
    }

    private void validate(Long id) {
        if (id == null) {
            throw new ServiceException(Error.NULL_ID);
        }
        if (id <= 0) {
            throw new ServiceException(Error.NEGATIVE_ID);
        }
    }

}
