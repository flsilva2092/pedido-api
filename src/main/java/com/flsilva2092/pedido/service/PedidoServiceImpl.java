package com.flsilva2092.pedido.service;

import com.flsilva2092.pedido.model.Pedido;
import com.flsilva2092.pedido.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    @Override
    public Pedido getPedido(String numeroPedido) {
        return pedidoRepository.findByNumeroPedido(numeroPedido);
    }
}
