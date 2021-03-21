package com.flsilva2092.pedido.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedido")
public class Pedido {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String numeroPedido;

	@OneToMany(mappedBy = "numeroPedido", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Item> items = new ArrayList<>();;
	private Status status;
	private LocalDateTime dataCriacao = LocalDateTime.now();

	public Pedido() {}

	public Pedido(String numeroPedido, List<Item> lista) {
		this.numeroPedido = numeroPedido;
		this.items = lista;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(String numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items.clear();
		this.items = items;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
}
