package com.iprody.payment.service.app.dto;

import com.iprody.payment.service.app.repository.model.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * This is the PaymentDto class
 *
 * @author Konanau Vital'
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {

    /**
     * Payment id in UUID format
     */
    private UUID guid;
    /**
     * Payment inquiryRefId
     */
    private UUID inquiryRefId;
    /**
     * Payment amount, example = 99.99
     */
    private BigDecimal amount;
    /**
     * Payment currency, example = "USD"
     */
    private String currency;
    /**
     * Payment transactionRefId
     */
    private UUID transactionRefId;
    /**
     * Payment status
     * Please see the {@link PaymentStatus} class for true identity
     */
    private PaymentStatus status;
    /**
     * Payment note, example = "some notes"
     */
    private String note;
    /**
     * Payment created date
     */
    private LocalDateTime createdAt;
    /**
     * Payment updated date
     */
    private LocalDateTime updatedAt;

}
