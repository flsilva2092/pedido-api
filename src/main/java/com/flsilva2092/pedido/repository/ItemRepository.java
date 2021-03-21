package com.flsilva2092.pedido.repository;

import com.flsilva2092.pedido.model.Item;
import com.flsilva2092.pedido.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Item findByNumeroPedido(String numero);
}
