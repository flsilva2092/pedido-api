package com.flsilva2092.pedido.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "item")
public class Item {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Pedido numeroPedido;
	private String descricao;
	private BigDecimal precoUnitario;
	private Integer quantidade;

	public Item() {}

	public Item(String descricao, BigDecimal preco, Integer qtd) {
		this.setDescricao(descricao);
		this.setPrecoUnitario(preco);
		this.setQuantidade(qtd);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pedido getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(Pedido numeroPedido) {
		this.numeroPedido = numeroPedido;
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
}
