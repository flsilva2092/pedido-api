package com.flsilva2092.pedido.dto;

import com.flsilva2092.pedido.model.Item;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ItemDto {

	private String descricao;
	private BigDecimal precoUnitario;
	private Integer quantidade;

	public ItemDto(Item item) {
		this.descricao = item.getDescricao();
		this.precoUnitario = item.getPrecoUnitario();
		this.quantidade = item.getQuantidade();
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public static List<ItemDto> converter(List<Item> items) {
		return items.stream().map(ItemDto::new).collect(Collectors.toList());
	}

}
