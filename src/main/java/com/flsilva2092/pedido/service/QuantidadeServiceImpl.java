package com.flsilva2092.pedido.service;

import com.flsilva2092.pedido.model.Pedido;
import com.flsilva2092.pedido.model.Status;
import com.flsilva2092.pedido.model.form.StatusForm;

public class QuantidadeServiceImpl implements QuantidadeService {

    @Override
    public Status validaQuantidade(StatusForm statusForm, Pedido pedido) {
        Integer soma = pedido.getItems().stream().map(item->
                item.getQuantidade()).mapToInt(Integer::intValue).sum();
        return (soma == statusForm.getItensAprovados() ? Status.APROVADO : (soma > statusForm.getItensAprovados() ? Status.APROVADO_QTD_A_MENOR : Status.APROVADO_QTD_A_MAIOR));
    }
}
