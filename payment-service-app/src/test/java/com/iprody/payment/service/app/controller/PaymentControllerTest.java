package com.iprody.payment.service.app.controller;

import com.iprody.payment.service.app.dto.PaymentDto;
import com.iprody.payment.service.app.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PaymentController.class)
class PaymentControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private PaymentService paymentService;

    @Test
    void getPaymentTest() throws Exception {
        // given
        when(paymentService.getPayment(1L)).thenReturn(PaymentDto.builder()
                .id(1L)
                .value(99.99)
                .name("Test")
                .build());

        // when
        mvc.perform(get("/payments/1")
                        .contentType(MediaType.APPLICATION_JSON))

        // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.value").value(99.99))
                .andExpect(jsonPath("$.name").value("Test"));
    }

}
