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
import static com.iprody.payment.service.app.util.TimeUtil.getNow;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public List<PaymentDto> getPayments() {
        return paymentRepository.findAll()
                .stream()
                .map(paymentMapper::toDto)
                .toList();
    }

    @Override
    public PaymentDto getPaymentById(UUID id) {
        return paymentRepository.findById(id)
                .map(paymentMapper::toDto)
                .orElseThrow(() -> new ServiceException(PAYMENT_NOT_EXIST, id));
    }

    @Override
    public Page<PaymentDto> search(PaymentFilterDto filter, Pageable pageable) {
        Specification<Payment> spec = PaymentFilterFactory.filter(filter);
        return paymentRepository.findAll(spec, pageable).map(paymentMapper::toDto);
    }

    @Override
    public void delete(UUID id) {
        if (!paymentRepository.existsById(id)) {
            throw new ServiceException(PAYMENT_NOT_EXIST, id);
        }
        paymentRepository.deleteById(id);
    }

    @Override
    public PaymentDto create(PaymentDto dto) {
        Payment payment = paymentMapper.toEntity(dto);
        payment.setCreatedAt(getNow());
        payment.setUpdatedAt(getNow());
        return paymentMapper.toDto(paymentRepository.save(payment));
    }

    @Override
    public PaymentDto update(PaymentDto dto, UUID id) {
        if (!paymentRepository.existsById(id)) {
            throw new ServiceException(PAYMENT_NOT_EXIST, id);
        }
        Payment payment = paymentMapper.toEntity(dto);
        payment.setGuid(id);
        payment.setUpdatedAt(getNow());
        return paymentMapper.toDto(paymentRepository.save(payment));
    }

}
