package com.iprody.payment.service.app.dto;

import com.iprody.payment.service.app.repository.model.PaymentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
public class PaymentDto {

    @NotNull(message = "UUID cannot be null")
    private UUID guid;

    @NotNull
    private UUID inquiryRefId;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private String currency;

    private UUID transactionRefId;

    @NotNull
    private PaymentStatus status;

    private String note;

    @NotNull
    private OffsetDateTime createdAt;

    @NotNull
    private OffsetDateTime updatedAt;

}
