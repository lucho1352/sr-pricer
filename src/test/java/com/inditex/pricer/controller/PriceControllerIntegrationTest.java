package com.inditex.pricer.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class PriceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String GET_V1_PRICE = "/v1/prices";
    private static final String BRAND_ID = "brandId";
    private static final String PRODUCT_ID = "productId";
    private static final String DATE = "date";
    private DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Test
    void test1() throws Exception {
        //give
        Integer brandId = 1;
        Integer productId = 35455;
        LocalDateTime date = LocalDateTime.of(2020,6,14,10,0,0);
        log.info("Test 1: Requesting price for product id {}, brand id {} (Zara) on {}",productId, brandId, DATE_FORMAT.format(date));
        log.info("        brandId->{}",productId);
        log.info("        productId->{}",brandId);
        log.info("        date->{}",DATE_FORMAT.format(date));

        //when
        ResultActions resultActions = mockMvc.perform(get(GET_V1_PRICE)
                        .param(BRAND_ID,brandId.toString())
                        .param(PRODUCT_ID,productId.toString())
                        .param(DATE,DATE_FORMAT.format(date)))
                //then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.startDateTime").value("2020-06-14T00:00:00"))
                .andExpect(jsonPath("$.endDateTime").value("2020-12-31T23:59:59"))
                .andExpect(jsonPath("$.finalPrice").value(35.50));
    }

    @Test
    void test2() throws Exception {
        //give
        Integer brandId = 1;
        Integer productId = 35455;
        LocalDateTime date = LocalDateTime.of(2020,6,14,16,0,0);
        log.info("Test 2: Requesting price for product id {}, brand id {} (Zara) on {}",productId, brandId, DATE_FORMAT.format(date));
        log.info("        brandId->{}",productId);
        log.info("        productId->{}",brandId);
        log.info("        date->{}",DATE_FORMAT.format(date));

        //when
        ResultActions resultActions = mockMvc.perform(get(GET_V1_PRICE)
                        .param(BRAND_ID,brandId.toString())
                        .param(PRODUCT_ID,productId.toString())
                        .param(DATE,DATE_FORMAT.format(date)))
                //then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.priceList").value(2))
                .andExpect(jsonPath("$.startDateTime").value("2020-06-14T15:00:00"))
                .andExpect(jsonPath("$.endDateTime").value("2020-06-14T18:30:00"))
                .andExpect(jsonPath("$.finalPrice").value(25.45));
    }

    @Test
    void test3() throws Exception {
        //give
        Integer brandId = 1;
        Integer productId = 35455;
        LocalDateTime date = LocalDateTime.of(2020,6,14,21,0,0);
        log.info("Test 3: Requesting price for product id {}, brand id {} (Zara) on {}",productId, brandId, DATE_FORMAT.format(date));
        log.info("        brandId->{}",productId);
        log.info("        productId->{}",brandId);
        log.info("        date->{}",DATE_FORMAT.format(date));

        //when
        ResultActions resultActions = mockMvc.perform(get(GET_V1_PRICE)
                        .param(BRAND_ID,brandId.toString())
                        .param(PRODUCT_ID,productId.toString())
                        .param(DATE,DATE_FORMAT.format(date)))
                //then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.startDateTime").value("2020-06-14T00:00:00"))
                .andExpect(jsonPath("$.endDateTime").value("2020-12-31T23:59:59"))
                .andExpect(jsonPath("$.finalPrice").value(35.50));
    }

    @Test
    void test4() throws Exception {
        //give
        Integer brandId = 1;
        Integer productId = 35455;
        LocalDateTime date = LocalDateTime.of(2020,6,15,10,0,0);
        log.info("Test 4: Requesting price for product id {}, brand id {} (Zara) on {}",productId, brandId, DATE_FORMAT.format(date));
        log.info("        brandId->{}",productId);
        log.info("        productId->{}",brandId);
        log.info("        date->{}",DATE_FORMAT.format(date));

        //when
        ResultActions resultActions = mockMvc.perform(get(GET_V1_PRICE)
                        .param(BRAND_ID,brandId.toString())
                        .param(PRODUCT_ID,productId.toString())
                        .param(DATE,DATE_FORMAT.format(date)))
                //then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.priceList").value(3))
                .andExpect(jsonPath("$.startDateTime").value("2020-06-15T00:00:00"))
                .andExpect(jsonPath("$.endDateTime").value("2020-06-15T11:00:00"))
                .andExpect(jsonPath("$.finalPrice").value(30.50));
    }

    @Test
    void test5() throws Exception {
        //give
        Integer brandId = 1;
        Integer productId = 35455;
        LocalDateTime date = LocalDateTime.of(2020,6,16,21,0,0);
        log.info("Test 5: Requesting price for product id {}, brand id {} (Zara) on {}",productId, brandId, DATE_FORMAT.format(date));
        log.info("        brandId->{}",productId);
        log.info("        productId->{}",brandId);
        log.info("        date->{}",DATE_FORMAT.format(date));

        //when
        ResultActions resultActions = mockMvc.perform(get(GET_V1_PRICE)
                        .param(BRAND_ID,brandId.toString())
                        .param(PRODUCT_ID,productId.toString())
                        .param(DATE,DATE_FORMAT.format(date)))
                //then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.priceList").value(4))
                .andExpect(jsonPath("$.startDateTime").value("2020-06-15T16:00:00"))
                .andExpect(jsonPath("$.endDateTime").value("2020-12-31T23:59:59"))
                .andExpect(jsonPath("$.finalPrice").value(38.95));
    }

    @Test
    void test6_NotFound() throws Exception {
        //give
        Integer brandId = 1;
        Integer productId = 35456;
        LocalDateTime date = LocalDateTime.of(2020,6,16,21,0,0);
        log.info("Test 6: Requesting price for product id {}, brand id {} (Zara) on {}",productId, brandId, DATE_FORMAT.format(date));
        log.info("        brandId->{}",productId);
        log.info("        productId->{}",brandId);
        log.info("        date->{}",DATE_FORMAT.format(date));

        //when
        ResultActions resultActions = mockMvc.perform(get(GET_V1_PRICE)
                        .param(BRAND_ID,brandId.toString())
                        .param(PRODUCT_ID,productId.toString())
                        .param(DATE,DATE_FORMAT.format(date)))
                //then
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(1000))
                .andExpect(jsonPath("$.errorMessage").value("Price wasn't found for the given criteria"));
    }

    @Test
    void test7_BadRequest() throws Exception {
        //give
        Integer brandId = 1;
        Integer productId = 35456;
        LocalDateTime date = LocalDateTime.of(2020,6,16,21,0,0);
        log.info("Test 6: Requesting price for product id {}, brand id {} (Zara) on {}",productId, brandId, DATE_FORMAT.format(date));
        log.info("        brandId->{}",productId);
        log.info("        productId->{}",brandId);
        log.info("        date->{}",DATE_FORMAT.format(date));

        //when
        ResultActions resultActions = mockMvc.perform(get(GET_V1_PRICE)
                        .param(BRAND_ID,brandId.toString())
                        .param(PRODUCT_ID,productId.toString())
                        .param(DATE,DATE_FORMAT.format(date) + "ERROR"))
                //then
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(1002));
    }

}
