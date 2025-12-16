package com.iprody.payment.service.app.service;

import com.iprody.payment.service.app.dto.PaymentDto;
import com.iprody.payment.service.app.dto.PaymentFilterDto;
import com.iprody.payment.service.app.exception.ServiceException;
import com.iprody.payment.service.app.mapper.PaymentMapper;
import com.iprody.payment.service.app.repository.PaymentRepository;
import com.iprody.payment.service.app.repository.model.Payment;
import com.iprody.payment.service.app.repository.specification.PaymentFilterFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
    public List<PaymentDto> getPayments() {
        return null;
    }

    @Override
    public PaymentDto getPaymentById(UUID id) {
        return paymentRepository.findById(id)
                .map(paymentMapper::toDto)
                .orElseThrow(() -> new ServiceException(PAYMENT_NOT_EXIST, id));
    }

    @Override
    public Page<Payment> search(PaymentFilterDto filter, Pageable pageable) {
        return paymentRepository.findAll(PaymentFilterFactory.filter(filter), pageable);
    }

    @Override
    public Page<PaymentDto> searchPaged2(PaymentFilterDto filter, Pageable pageable) {
        Specification<Payment> spec = PaymentFilterFactory.filter(filter);
        return paymentRepository.findAll(spec, pageable).map(paymentMapper::toDto);
    }

}
