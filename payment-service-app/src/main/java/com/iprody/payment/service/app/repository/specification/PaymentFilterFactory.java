package com.iprody.payment.service.app.repository.specification;

import com.iprody.payment.service.app.repository.model.Payment;
import com.iprody.payment.service.app.dto.PaymentFilterDto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class PaymentFilterFactory {

    public static Specification<Payment> filter(PaymentFilterDto filter) {

        Specification<Payment> spec = Specification.unrestricted();

        if (filter.getStatus() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("status"), filter.getStatus()));
        }

        if (StringUtils.hasText(filter.getCurrency())) {
            spec = spec.and(PaymentSpecification.hasCurrency(filter.getCurrency()));
        }
        if (filter.getMinAmount() != null && filter.getMaxAmount() != null) {
            spec = spec.and(PaymentSpecification.amountBetween(
                    filter.getMinAmount(), filter.getMaxAmount()));
        }
        if (filter.getCreatedAfter() != null && filter.getCreatedBefore() != null) {
            spec = spec.and(PaymentSpecification.createdBetween(filter.getCreatedAfter(), filter.getCreatedBefore()));
        }

        return spec;
    }

}
