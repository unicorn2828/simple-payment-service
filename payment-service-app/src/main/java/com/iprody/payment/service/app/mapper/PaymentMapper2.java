package com.iprody.payment.service.app.mapper;

import com.iprody.payment.service.app.dto.PaymentDto;
import com.iprody.payment.service.app.dto.PaymentFilterDto;
import com.iprody.payment.service.app.repository.model.Payment;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
@DecoratedWith(PaymentMapper2Decorator.class)
public interface PaymentMapper2 {

    String CURRENCY_USD = "USD";

    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "currency", ignore = true)
    @Mapping(target = "note", defaultValue = "some note")
    PaymentDto toDto(Payment payment);

    @Mapping(target = "currency", constant = CURRENCY_USD)
    Payment toEntity(PaymentDto dto);

    @Mapping(target = "currency", source = "filterDto.currency")
    @Mapping(target = "status", source = "dto.status")
    Payment toEntity(PaymentDto dto, PaymentFilterDto filterDto);

}
