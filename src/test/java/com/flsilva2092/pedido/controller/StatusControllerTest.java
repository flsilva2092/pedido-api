package com.flsilva2092.pedido.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flsilva2092.pedido.model.Item;
import com.flsilva2092.pedido.model.Pedido;
import com.flsilva2092.pedido.model.form.StatusForm;
import com.flsilva2092.pedido.service.PedidoService;
import org.junit.jupiter.api.*;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class })
public class StatusControllerTest {

    static final String URL = "/api/status";

    HttpHeaders headers;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PedidoService pedidoService;

    @BeforeAll
    private void setUp() {
        headers = new HttpHeaders();
    }

    @Test
    @Order(1)
    public void testCenario1() throws Exception {
        BDDMockito.given(pedidoService.getPedido(Mockito.any())).willReturn(getMockPedido());
        mockMvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload(new StatusForm("APROVADO", 10, new BigDecimal(10), "123456")))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .headers(headers))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pedido").value("123456"))
                .andExpect(jsonPath("$.status").value("APROVADO"));
    }

    @Test
    @Order(2)
    public void testCenario2() throws Exception {
        BDDMockito.given(pedidoService.getPedido(Mockito.any())).willReturn(getMockPedido());
        mockMvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload(new StatusForm("APROVADO", 10, new BigDecimal(9), "123456")))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .headers(headers))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pedido").value("123456"))
                .andExpect(jsonPath("$.status").value("APROVADO_VALOR_A_MENOR"));
    }

    @Test
    @Order(3)
    public void testCenario3() throws Exception {
        BDDMockito.given(pedidoService.getPedido(Mockito.any())).willReturn(getMockPedido());
        mockMvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload(new StatusForm("APROVADO", 11, new BigDecimal(11), "123456")))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .headers(headers))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pedido").value("123456"))
                .andExpect(jsonPath("$.status.[0]").value("APROVADO_QTD_A_MAIOR"))
                .andExpect(jsonPath("$.status.[1]").value("APROVADO_VALOR_A_MAIOR"));
    }

    @Test
    @Order(4)
    public void testCenario4() throws Exception {
        BDDMockito.given(pedidoService.getPedido(Mockito.any())).willReturn(getMockPedido());
        mockMvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload(new StatusForm("APROVADO", 9, new BigDecimal(10), "123456")))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .headers(headers))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pedido").value("123456"))
                .andExpect(jsonPath("$.status").value("APROVADO_QTD_A_MENOR"));
    }

    @Test
    @Order(5)
    public void testCenario5() throws Exception {
        BDDMockito.given(pedidoService.getPedido(Mockito.any())).willReturn(getMockPedido());
        mockMvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload(new StatusForm("REPROVADO", 9, new BigDecimal(10), "123456")))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .headers(headers))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pedido").value("123456"))
                .andExpect(jsonPath("$.status").value("REPROVADO"));
    }

    @Test
    @Order(6)
    public void testCenario6() throws Exception {
        BDDMockito.given(pedidoService.getPedido(Mockito.any())).willReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload(new StatusForm("APROVADO", 9, new BigDecimal(10), "123476")))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .headers(headers))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pedido").value("123476"))
                .andExpect(jsonPath("$.status").value("CODIGO_PEDIDO_INVALIDO"));
    }

    private Pedido getMockPedido() {
        return new Pedido("123456", Arrays.asList(new Item("teste", new BigDecimal(10), 10)) );
    }

    private String getJsonPayload(StatusForm statusForm)
            throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(statusForm);
    }

}
