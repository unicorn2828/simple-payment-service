package com.iprody.payment.service.app.service;

import com.iprody.payment.service.app.dto.PaymentDto;
import com.iprody.payment.service.app.exception.ServiceException;
import com.iprody.payment.service.app.mapper.PaymentMapper;
import com.iprody.payment.service.app.repository.PaymentRepository;
import com.iprody.payment.service.app.repository.model.Payment;
import com.iprody.payment.service.app.repository.model.PaymentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.iprody.payment.service.app.exception.ErrorMessage.PAYMENT_NOT_EXIST;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public PaymentDto getById(UUID id) {
        return paymentRepository.findById(id)
                .map(paymentMapper::toDto)
                .orElseThrow(() -> new ServiceException(PAYMENT_NOT_EXIST, id));
    }

    @Override
    public List<PaymentDto> getByStatus(PaymentStatus status) {
        List<Payment> payments = paymentRepository.findByStatus(status);
        return payments.stream().map(paymentMapper::toDto).toList();
    }

}
