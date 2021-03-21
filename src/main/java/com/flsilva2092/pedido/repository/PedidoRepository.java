package com.flsilva2092.pedido.repository;

import com.flsilva2092.pedido.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    Pedido findByNumeroPedido(String numeroPedido);

}
