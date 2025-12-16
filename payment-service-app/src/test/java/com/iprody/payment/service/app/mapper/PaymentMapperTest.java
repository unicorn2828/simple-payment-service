package com.iprody.payment.service.app.mapper;

import com.iprody.payment.service.app.dto.PaymentDto;
import com.iprody.payment.service.app.repository.model.Payment;
import com.iprody.payment.service.app.repository.model.PaymentStatus;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PaymentMapperTest {

    private final PaymentMapper mapper = Mappers.getMapper(PaymentMapper.class);

    @Test
    void toDtoTest() {
        // given
        var uuid = UUID.randomUUID();
        var now = OffsetDateTime.now();

        var expected = new Payment();
        expected.setGuid(uuid);
        expected.setAmount(new BigDecimal("123.45"));
        expected.setCurrency("USD");
        expected.setInquiryRefId(UUID.randomUUID());
        expected.setStatus(PaymentStatus.APPROVED);
        expected.setCreatedAt(now);
        expected.setUpdatedAt(now);

        // when
        var actual = mapper.toDto(expected);

        // then
        assertNotNull(actual);
        assertThat(actual).isNotNull();

        assertEquals(expected.getAmount(), actual.getAmount());
        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getInquiryRefId(), actual.getInquiryRefId());
        assertEquals(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getCreatedAt(), actual.getCreatedAt());
        assertEquals(actual.getUpdatedAt(), expected.getUpdatedAt());
    }

    @Test
    void shouldMapToEntity() {
        // given
        var uuid = UUID.randomUUID();
        var now = OffsetDateTime.now();

        var expected = PaymentDto.builder()
                .note("some note")
                .amount(new BigDecimal("999.99"))
                .status(PaymentStatus.PENDING)
                .currency("EUR")
                .updatedAt(now)
                .createdAt(now)
                .inquiryRefId(uuid)
                .build();

        // when
        var actual = mapper.toEntity(expected);

        // then
        assertNotNull(actual);

        assertEquals(expected.getAmount(), actual.getAmount());
        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getInquiryRefId(), actual.getInquiryRefId());
        assertEquals(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getCreatedAt(), actual.getCreatedAt());
        assertEquals(actual.getUpdatedAt(), expected.getUpdatedAt());
    }
}
