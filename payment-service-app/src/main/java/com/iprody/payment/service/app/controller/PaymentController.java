package com.iprody.payment.service.app.controller;

import com.iprody.payment.service.app.dto.PaymentDto;
import com.iprody.payment.service.app.dto.PaymentFilterDto;
import com.iprody.payment.service.app.repository.model.Payment;
import com.iprody.payment.service.app.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<PaymentDto>> getPayments() {
        return ResponseEntity.ok().body(paymentService.getPayments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> getByUuid(@PathVariable @Valid UUID id) {
        return ResponseEntity.ok().body(paymentService.getPaymentById(id));
    }

    @GetMapping("/search")
    public Page<Payment> searchPayments(
            @ModelAttribute PaymentFilterDto filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "amount") String sortBy,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return paymentService.search(filter, pageable);
    }

    @GetMapping("/search2")
    public ResponseEntity<Page<PaymentDto>> searchPayments2(
            @ModelAttribute PaymentFilterDto filter,
            @PageableDefault(page = 0, size = 10, sort = "amount", direction = Sort.Direction.ASC)
            Pageable pageable) {
        return ResponseEntity.ok().body(paymentService.searchPaged2(filter, pageable));
    }

}
