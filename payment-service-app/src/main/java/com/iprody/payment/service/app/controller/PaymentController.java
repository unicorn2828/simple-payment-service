package com.iprody.payment.service.app.controller;

import com.iprody.payment.service.app.dto.PaymentDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @GetMapping
    public ResponseEntity<PaymentDto>get(){
        return ResponseEntity.ok(PaymentDto.builder()
                .id(2L)
                .value(99.99)
                .name("other")
                .build());
    }

}
