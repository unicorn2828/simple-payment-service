package com.iprody.payment.service.app.repository;

import com.iprody.payment.service.app.repository.model.Payment;
import com.iprody.payment.service.app.repository.model.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {

    List<Payment> findByStatus(PaymentStatus status);

}
