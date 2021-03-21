package com.flsilva2092.pedido;

import com.flsilva2092.pedido.model.form.ItemForm;
import com.flsilva2092.pedido.model.form.PedidoForm;
import com.flsilva2092.pedido.model.form.StatusForm;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JavaApiApplicationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Order(1)
    public void testCreatePedido() {
        PedidoForm pedidoForm = new PedidoForm("123456", Arrays.asList(new ItemForm("teste", new BigDecimal(1), 1)));
        final HttpEntity<PedidoForm> entity = new HttpEntity<>(pedidoForm, new HttpHeaders());
        ResponseEntity<String> responseEntity = this.restTemplate.exchange("http://localhost:"
                + port + "/api/pedido", HttpMethod.POST, entity, String.class);
        assertEquals(201, responseEntity.getStatusCodeValue());
    }

    @Test
    @Order(2)
    public void testGetPedido() {
        final HttpEntity<PedidoForm> entity = new HttpEntity<>(null, new HttpHeaders());
        ResponseEntity<String> responseEntity = this.restTemplate.exchange("http://localhost:"
                + port + "/api/pedido", HttpMethod.GET, entity, String.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    @Order(3)
    public void testPutPedido() {
        PedidoForm pedidoForm = new PedidoForm("123456", Arrays.asList(new ItemForm("teste", new BigDecimal(1), 1)));
        final HttpEntity<PedidoForm> entity = new HttpEntity<>(pedidoForm, new HttpHeaders());
        ResponseEntity<String> responseEntity = this.restTemplate.exchange("http://localhost:"
                + port + "/api/pedido/1", HttpMethod.PUT, entity, String.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    @Order(4)
    public void testDeletePedido() {
        final HttpEntity<PedidoForm> entity = new HttpEntity<>(null, new HttpHeaders());
        ResponseEntity<String> responseEntity = this.restTemplate.exchange("http://localhost:"
                + port + "/api/pedido/2", HttpMethod.DELETE, entity, String.class);
        assertEquals(404, responseEntity.getStatusCodeValue());
    }

    @Test
    @Order(5)
    public void testStatusCenario1() {
        StatusForm statusForm = new StatusForm("APROVADO", 1, new BigDecimal(1), "123456");
        final HttpEntity<StatusForm> entity = new HttpEntity<>(statusForm, new HttpHeaders());
        ResponseEntity<String> responseEntity = this.restTemplate.exchange("http://localhost:"
                + port + "/api/status", HttpMethod.POST, entity, String.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }



}
