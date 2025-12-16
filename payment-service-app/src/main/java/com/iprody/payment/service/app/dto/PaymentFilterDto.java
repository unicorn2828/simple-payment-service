package com.iprody.payment.service.app.dto;

import com.iprody.payment.service.app.repository.model.PaymentStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
public class PaymentFilterDto {

    private PaymentStatus status;
    private String currency;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private Instant createdAfter;
    private Instant createdBefore;

}
