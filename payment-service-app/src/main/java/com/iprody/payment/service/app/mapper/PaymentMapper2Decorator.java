package com.iprody.payment.service.app.mapper;

import com.iprody.payment.service.app.dto.PaymentDto;
import com.iprody.payment.service.app.repository.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class PaymentMapper2Decorator implements PaymentMapper2 {

    @Autowired
    private PaymentMapper2 delegate;

    @Override
    public PaymentDto toDto(Payment payment) {
        PaymentDto dto = delegate.toDto(payment);
        dto.setNote(getNote());
        return dto;
    }

    private String getNote() {
        return "new note";
    }
}
