package com.flsilva2092.pedido.service;

import com.flsilva2092.pedido.dto.StatusDto;
import com.flsilva2092.pedido.model.Pedido;
import com.flsilva2092.pedido.model.Status;
import com.flsilva2092.pedido.model.form.StatusForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private PedidoService pedidoService;

    private final QuantidadeService quantidadeService = new QuantidadeServiceImpl();

    private final ValorService valorService = new ValorServiceImpl();

    @Override
    public StatusDto validaStatus(StatusForm statusForm) {

        Pedido pedido = pedidoService.getPedido(statusForm.getPedido());
        if (null == pedido) {
            return new StatusDto(statusForm.getPedido(), Arrays.asList(Status.CODIGO_PEDIDO_INVALIDO));
        }

        if (statusForm.getStatus().equals(Status.REPROVADO.name())) {
            return new StatusDto(statusForm.getPedido(), Arrays.asList(Status.REPROVADO));
        }

        if (!statusForm.getStatus().equals(Status.APROVADO.name())) {
            return new StatusDto(statusForm.getPedido(), Arrays.asList());
        }

        List<Status> statusList = new ArrayList<>();
        statusList.add(quantidadeService.validaQuantidade(statusForm, pedido));
        statusList.add(valorService.validaValor(statusForm, pedido));

        int contador = Collections.frequency(statusList, Status.APROVADO);
        if (contador == 1) {
            statusList.remove(Status.APROVADO);
        } else if (contador == 2) {
            statusList = Arrays.asList(Status.APROVADO);
        }

        return new StatusDto(statusForm.getPedido(), statusList);
    }
}
