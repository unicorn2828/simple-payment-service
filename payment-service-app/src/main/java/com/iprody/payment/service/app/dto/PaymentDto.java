package com.iprody.payment.service.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentDto {

    private Long id;
    private Double value;
    private String name;

}
