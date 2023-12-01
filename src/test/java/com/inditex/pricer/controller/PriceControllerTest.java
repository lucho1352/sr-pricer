package com.inditex.pricer.controller;

import com.inditex.pricer.common.error.handling.ControllerErrorAdvice;
import com.inditex.pricer.common.error.handling.exceptions.PriceNotFoundException;
import com.inditex.pricer.dto.PriceResponse;
import com.inditex.pricer.service.PriceCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

    @InjectMocks
    private PriceController priceController;
    @Mock
    private PriceCalculator priceCalculator;

    private MockMvc mockMvc;
    private static final String GET_V1_PRICE = "/v1/prices";

    @BeforeEach
    void setUp(){
        mockMvc = standaloneSetup(priceController)
                .setControllerAdvice(ControllerErrorAdvice.class)
                .build();
    }

    @Test
    void determinePriceTest_400() throws Exception {
        //given
        //when
        ResultActions mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(GET_V1_PRICE)
                .contentType(MediaType.APPLICATION_JSON));

        //then
        mvcResult.andExpect(status().isBadRequest());
        verify(priceCalculator,times(0)).calculatePrice(any());
    }

    @Test
    void determinePriceTest_404() throws Exception {
        //given
        when(priceCalculator.calculatePrice(any())).thenThrow(new PriceNotFoundException("Price wasn't found for the given criteria"));

        //when
        ResultActions mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(GET_V1_PRICE)
                .param("brandId","1")
                .param("productId","35455")
                .param("date","2022-06-16T21:00:00")
                .contentType(MediaType.APPLICATION_JSON));

        //then
        mvcResult.andExpect(status().isNotFound());
        verify(priceCalculator,times(1)).calculatePrice(any());
    }

    @Test
    void determinePriceTest_200() throws Exception {
        //given
        PriceResponse response = PriceResponse.builder()
                .productId(35455)
                .brandId(1)
                .priceList(4)
                .startDateTime(LocalDateTime.of(2020,6,15,16,0,0))
                .endDateTime(LocalDateTime.of(2020,12,31,23,59,59))
                .finalPrice(new BigDecimal("38.95"))
                .build();

        when(priceCalculator.calculatePrice(any())).thenReturn(response);

        //when
        ResultActions mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(GET_V1_PRICE)
                .param("brandId","1")
                .param("productId","35455")
                .param("date","2020-06-16T21:00:00")
                .contentType(MediaType.APPLICATION_JSON));

        //then
        mvcResult.andExpect(status().isOk());
        mvcResult.andExpect(jsonPath("$").isNotEmpty());
        verify(priceCalculator,times(1)).calculatePrice(any());
    }

}
