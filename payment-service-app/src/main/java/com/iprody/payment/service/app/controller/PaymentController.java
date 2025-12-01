package com.iprody.payment.service.app.controller;

import com.iprody.payment.service.app.dto.PaymentDto;
import com.iprody.payment.service.app.repository.model.Payment;
import com.iprody.payment.service.app.repository.model.PaymentStatus;
import com.iprody.payment.service.app.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> getByUuid(@PathVariable UUID id) {
        return ResponseEntity.ok().body(paymentService.getById(id));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<PaymentDto>> getByStatus(@PathVariable PaymentStatus status) {
        return ResponseEntity.ok().body(paymentService.getByStatus(status));
    }

}
