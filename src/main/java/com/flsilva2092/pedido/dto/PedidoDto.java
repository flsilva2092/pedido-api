package com.flsilva2092.pedido.dto;

import com.flsilva2092.pedido.model.Pedido;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoDto {

	private Long id;
	private String pedido;
	private List<ItemDto> items;

	public PedidoDto(Pedido pedido) {
		this.id = pedido.getId();
		this.pedido = pedido.getNumeroPedido();
		this.items = new ArrayList<>();
		this.items.addAll(pedido.getItems().stream().map(ItemDto::new).collect(Collectors.toList()));
	}

    public PedidoDto(String pedido, List<ItemDto> lista) {
		this.pedido = pedido;
		this.items = lista;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPedido() {
		return pedido;
	}

	public void setPedido(String pedido) {
		this.pedido = pedido;
	}

	public List<ItemDto> getItems() {
		return items;
	}

	public void setItems(List<ItemDto> items) {
		this.items = items;
	}

	public static List<PedidoDto> converter(List<Pedido> pedidos) {
		return pedidos.stream().map(PedidoDto::new).collect(Collectors.toList());
	}

}
