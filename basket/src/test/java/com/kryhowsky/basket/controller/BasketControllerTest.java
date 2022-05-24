package com.kryhowsky.basket.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.kryhowsky.basket.model.dto.BasketDto;
import com.kryhowsky.common.rest.ProductDto;
import com.kryhowsky.common.rest.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
//@ContextConfiguration(classes = WiremockConfig.class)
@AutoConfigureWireMock
public class BasketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

//    @Autowired
//    private WireMockServer wireMockServer;

    @Test
    void test() {
        System.out.println("TEST");
    }

    @Test
    @WithMockUser
    void addProductToBasket() throws Exception {
        stubFor(WireMock.get("/current")
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withBody(objectMapper.writeValueAsBytes(UserDto.builder()
                                .login("login")
                                .build()))));

        stubFor(WireMock.get("/1")
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withBody(objectMapper.writeValueAsBytes(ProductDto.builder()
                                .id(1L)
                                .quantity(20)
                                .build()))));

        mockMvc.perform(post("/")
                .header(HttpHeaders.AUTHORIZATION, "ghijetdgjfgj")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(BasketDto.builder()
                        .productId(1L)
                        .quantity(10)
                        .build())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }
}
