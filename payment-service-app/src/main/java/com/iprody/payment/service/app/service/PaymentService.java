package com.iprody.payment.service.app.service;

import com.iprody.payment.service.app.dto.PaymentDto;
import com.iprody.payment.service.app.dto.PaymentFilterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface PaymentService {

    List<PaymentDto> getPayments();

    PaymentDto getPaymentById(UUID id);

    void delete(UUID id);

    PaymentDto create(PaymentDto dto);

    PaymentDto update(PaymentDto dto, UUID id);

    Page<PaymentDto> search(PaymentFilterDto request, Pageable pageable);

}
