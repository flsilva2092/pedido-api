package com.flsilva2092.pedido.model.form;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ItemForm {

	@NotNull @NotEmpty @Length(min = 6, max = 50)
	private String descricao;
	
	@NotNull @NotEmpty
	private BigDecimal precoUnitario;
	
	@NotNull @NotEmpty
	private Integer qtd;

	public ItemForm() {}

	public ItemForm(String descricao, BigDecimal precoUnitario, int qtd) {
		this.descricao = descricao;
		this.precoUnitario = precoUnitario;
		this.qtd = qtd;
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

	public Integer getQtd() {
		return qtd;
	}

	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}
}
