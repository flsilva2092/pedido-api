package com.flsilva2092.pedido.service;

import com.flsilva2092.pedido.dto.StatusDto;
import com.flsilva2092.pedido.model.Item;
import com.flsilva2092.pedido.model.Pedido;
import com.flsilva2092.pedido.model.form.StatusForm;
import org.junit.jupiter.api.*;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class })
public class StatusServiceTest {

    @Autowired
    private StatusService service;

    @MockBean
    private PedidoService repository;

    @Test
    @Order(1)
    public void testSave() {

        BDDMockito.given(repository.getPedido("123456"))
                .willReturn(getMockPedido());
        StatusDto response = service.validaStatus(new StatusForm());

        assertNotNull(response);
    }

    private Pedido getMockPedido() {
        return new Pedido("123456", Arrays.asList(new Item("teste", new BigDecimal(1), 1)) );
    }


}
