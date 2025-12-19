package com.iprody.payment.service.app.controller;

import com.iprody.payment.service.app.dto.PaymentDto;
import com.iprody.payment.service.app.dto.PaymentFilterDto;
import com.iprody.payment.service.app.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<PaymentDto>> getPayments() {
        return ResponseEntity.ok().body(paymentService.getPayments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> getByUuid(@PathVariable UUID id) {
        return ResponseEntity.ok().body(paymentService.getPaymentById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<PaymentDto>> searchPayments2(
            @ModelAttribute PaymentFilterDto filter,
            @PageableDefault(page = 0, size = 10, sort = "amount", direction = Sort.Direction.ASC)
            Pageable pageable) {
        return ResponseEntity.ok().body(paymentService.search(filter, pageable));
    }

    @PostMapping
    public ResponseEntity<PaymentDto> create(@RequestBody PaymentDto dto) {
        return ResponseEntity.ok().body(paymentService.create(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        paymentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDto> update(@RequestBody PaymentDto dto, @PathVariable UUID id) {
        return ResponseEntity.ok().body(paymentService.update(dto, id));
    }

}
