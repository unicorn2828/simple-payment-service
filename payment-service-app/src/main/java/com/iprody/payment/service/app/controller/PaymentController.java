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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'READER')")
    public ResponseEntity<List<PaymentDto>> getAllPayments() {
        return ResponseEntity.ok().body(paymentService.getPayments());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<PaymentDto> getPaymentByUuid(@PathVariable UUID id) {
        return ResponseEntity.ok().body(paymentService.getPaymentById(id));
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('USER', 'READER')")
    public ResponseEntity<Page<PaymentDto>> search(
            @ModelAttribute PaymentFilterDto filter,
            @PageableDefault(page = 0, size = 10, sort = "amount", direction = Sort.Direction.ASC)
            Pageable pageable) {
        return ResponseEntity.ok().body(paymentService.search(filter, pageable));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<PaymentDto> create(@RequestBody PaymentDto dto) {
        return ResponseEntity.ok().body(paymentService.create(dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        paymentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<PaymentDto> update(@RequestBody PaymentDto dto, @PathVariable UUID id) {
        return ResponseEntity.ok().body(paymentService.update(dto, id));
    }

}
