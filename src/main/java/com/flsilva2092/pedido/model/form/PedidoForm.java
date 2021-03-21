package com.flsilva2092.pedido.model.form;

import com.flsilva2092.pedido.model.Item;
import com.flsilva2092.pedido.model.Pedido;
import com.flsilva2092.pedido.repository.PedidoRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class PedidoForm {

	@NotNull @NotEmpty @Length(min = 6, max = 6)
	private String pedido;

	@NotEmpty
	private List<ItemForm> itens;

	public PedidoForm() {}

	public PedidoForm(String pedido, List<ItemForm> lista) {
		this.pedido = pedido;
		this.itens = lista;
	}

	public Pedido converter() {
		Pedido pedido = new Pedido();
		getPedido(pedido);
		return pedido;
	}

	public String getPedido() {
		return pedido;
	}

	public void setPedido(String pedido) {
		this.pedido = pedido;
	}

	public List<ItemForm> getItens() {
		return itens;
	}

	public void setItens(List<ItemForm> itens) {
		this.itens = itens;
	}

	private void getPedidoAlterar(Pedido pedido) {
		pedido.setNumeroPedido(this.pedido);
		int cont = 0;
		for (Item item: pedido.getItems()) {
			item.setNumeroPedido(pedido);
			item.setDescricao(this.itens.get(cont).getDescricao());
			item.setQuantidade(this.itens.get(cont).getQtd());
			item.setPrecoUnitario(this.itens.get(cont).getPrecoUnitario());
			cont++;
		}
	}

	private void getPedido(Pedido pedido) {
		pedido.setNumeroPedido(this.pedido);
		for (ItemForm itemForm: itens) {
			Item item = new Item();
			item.setNumeroPedido(pedido);
			item.setDescricao(itemForm.getDescricao());
			item.setQuantidade(itemForm.getQtd());
			item.setPrecoUnitario(itemForm.getPrecoUnitario());
			pedido.getItems().add(item);
		}
	}

	public Pedido atualizar(Long id, PedidoRepository pedidoRepository) {
		Pedido pedido = pedidoRepository.getOne(id);
		getPedidoAlterar(pedido);
		return pedido;
	}

}
