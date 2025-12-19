package com.iprody.payment.service.app.service;

import com.iprody.payment.service.app.dto.PaymentDto;
import com.iprody.payment.service.app.mapper.PaymentMapper;
import com.iprody.payment.service.app.repository.PaymentRepository;
import com.iprody.payment.service.app.repository.model.Payment;
import com.iprody.payment.service.app.repository.model.PaymentStatus;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ExtendWith(RandomBeansExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;
    @Mock
    private PaymentMapper paymentMapper;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    private Payment payment;
    private PaymentDto paymentDto;
    private UUID guid;

    @BeforeEach
    void setUp() {
        guid = UUID.randomUUID();

        payment = new Payment();
        payment.setGuid(guid);
        payment.setInquiryRefId(UUID.randomUUID());
        payment.setAmount(new BigDecimal("100.00"));
        payment.setCurrency("USD");
        payment.setStatus(PaymentStatus.APPROVED);
        payment.setCreatedAt(LocalDateTime.now());
        payment.setUpdatedAt(LocalDateTime.now());

        paymentDto = PaymentDto.builder()
                .currency(payment.getCurrency())
                .status(payment.getStatus())
                .build();
    }

    @Test
    void f() {
        LocalDate d1 = LocalDate.of(2025, 05, 03);
        LocalDate d2 = d1.plusDays(181);
        System.out.println(d1);
        System.out.println(d2);
    }
    @Test
    void getPaymentByIdTest(@Random PaymentDto exampleDto,
                            @Random(size = 5, type = Payment.class)List<Payment> exampleList) {
        // given
        System.out.println(exampleDto);
        System.out.println(exampleList);
        when(paymentRepository.findById(guid)).thenReturn(Optional.of(payment));
        when(paymentMapper.toDto(payment)).thenReturn(paymentDto);

        // when
        var actual = paymentService.getPaymentById(guid);

        // then
        assertEquals("USD", actual.getCurrency());
        assertEquals(PaymentStatus.APPROVED, actual.getStatus());

        verify(paymentRepository).findById(guid);
        verify(paymentMapper).toDto(payment);
    }

    @ParameterizedTest
    @EnumSource(PaymentStatus.class)
    void shouldMapDifferentPaymentStatuses(PaymentStatus status) {
        // given
        payment.setStatus(status);
        paymentDto.setStatus(status);

        when(paymentRepository.findById(guid)).thenReturn(Optional.of(payment));
        when(paymentMapper.toDto(payment)).thenReturn(paymentDto);

        // when
        var actual = paymentService.getPaymentById(guid);

        // then
        assertNotNull(actual);
        assertEquals(status, actual.getStatus());

        verify(paymentRepository).findById(guid);
        verify(paymentMapper).toDto(payment);
    }
}
