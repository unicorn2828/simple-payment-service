package com.iprody.payment.service.app.service;

import com.iprody.payment.service.app.dto.PaymentDto;
import com.iprody.payment.service.app.repository.model.PaymentStatus;

import java.util.List;
import java.util.UUID;

public interface PaymentService {

    PaymentDto getById(UUID id);

    List<PaymentDto> getByStatus(PaymentStatus status);

}
