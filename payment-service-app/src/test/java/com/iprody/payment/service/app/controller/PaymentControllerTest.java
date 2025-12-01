package com.iprody.payment.service.app.controller;

import com.iprody.payment.service.app.dto.PaymentDto;
import com.iprody.payment.service.app.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

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
    void getByIdTest() throws Exception {
        // given
        UUID guid = UUID.randomUUID();
        when(paymentService.getById(guid)).thenReturn(PaymentDto.builder()
                .guid(guid)
                .build());

        // when
        mvc.perform(get("/payments/" + guid)
                        .contentType(MediaType.APPLICATION_JSON))

        // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.guid").value(guid.toString()));
    }

}
