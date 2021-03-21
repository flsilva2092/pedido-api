package com.flsilva2092.pedido.model.form;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class StatusForm {

	@NotNull @NotEmpty
	private String status;
	
	@NotNull @Positive
	private Integer itensAprovados;

	@NotNull @Positive
	private BigDecimal valorAprovado;

	@NotNull @NotEmpty @Length(min = 6, max = 6)
	private String pedido;

	public StatusForm(){}

	public StatusForm(String status, int itensAprovados, BigDecimal valorAprovado, String pedido) {
		this.status = status;
		this.itensAprovados = itensAprovados;
		this.valorAprovado = valorAprovado;
		this.pedido = pedido;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getItensAprovados() {
		return itensAprovados;
	}

	public void setItensAprovados(Integer itensAprovados) {
		this.itensAprovados = itensAprovados;
	}

	public BigDecimal getValorAprovado() {
		return valorAprovado;
	}

	public void setValorAprovado(BigDecimal valorAprovado) {
		this.valorAprovado = valorAprovado;
	}

	public String getPedido() {
		return pedido;
	}

	public void setPedido(String pedido) {
		this.pedido = pedido;
	}
}
