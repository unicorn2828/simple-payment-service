package com.iprody.payment.service.app.dto;

import com.iprody.payment.service.app.repository.model.PaymentStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
public class PaymentDto {

    private UUID guid;

    private UUID inquiryRefId;

    private BigDecimal amount;

    private String currency;

    private UUID transactionRefId;

    private PaymentStatus status;

    private String note;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

}
