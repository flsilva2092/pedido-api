package com.flsilva2092.pedido.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flsilva2092.pedido.model.Item;
import com.flsilva2092.pedido.model.Pedido;
import com.flsilva2092.pedido.model.form.ItemForm;
import com.flsilva2092.pedido.model.form.PedidoForm;
import com.flsilva2092.pedido.repository.PedidoRepository;
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
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class })
public class PedidosControllerTest {

    static final String URL = "/api/pedido";

    HttpHeaders headers;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PedidoRepository pedidoRepository;

    @BeforeAll
    private void setUp() {
        headers = new HttpHeaders();
    }

    @Test
    @Order(1)
    public void testSave() throws Exception {

        BDDMockito.given(pedidoRepository.save(Mockito.any(Pedido.class))).willReturn(getMockPedido());

        mockMvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload("123456", Arrays.asList(new ItemForm("teste", new BigDecimal(1), 1))))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .headers(headers))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.pedido").value("123456"));
    }

    private Pedido getMockPedido() {
        return new Pedido("123456", Arrays.asList(new Item("teste", new BigDecimal(1), 1)) );
    }

    private String getJsonPayload(String pedido, List<ItemForm> lista)
            throws JsonProcessingException {
        PedidoForm dto = new PedidoForm(pedido, lista);
        return new ObjectMapper().writeValueAsString(dto);
    }

}
