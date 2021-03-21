package com.flsilva2092.pedido.dto;

import com.flsilva2092.pedido.model.Status;
import java.util.List;

public class StatusDto {

	private String pedido;
	private List<Status> status;

	public StatusDto(String pedido, List<Status> status) {
		this.pedido = pedido;
		this.status = status;
	}

	public String getPedido() {
		return pedido;
	}

	public void setPedido(String pedido) {
		this.pedido = pedido;
	}

	public List<Status> getStatus() {
		return status;
	}

	public void setStatus(List<Status> status) {
		this.status = status;
	}
}
