package com.iprody.payment.service.app.service;

import com.iprody.payment.service.app.dto.PaymentDto;
import com.iprody.payment.service.app.repository.model.Payment;
import com.iprody.payment.service.app.dto.PaymentFilterDto;
import com.iprody.payment.service.app.repository.model.PaymentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface PaymentService {

    List<PaymentDto> getPayments();

    PaymentDto getPaymentById(UUID id);

    Page<Payment> search(PaymentFilterDto request, Pageable pageable);

    Page<PaymentDto> searchPaged2(PaymentFilterDto request, Pageable pageable);

}
