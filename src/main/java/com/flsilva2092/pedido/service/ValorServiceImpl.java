package com.flsilva2092.pedido.service;

import com.flsilva2092.pedido.model.Pedido;
import com.flsilva2092.pedido.model.Status;
import com.flsilva2092.pedido.model.form.StatusForm;

import java.math.BigDecimal;

public class ValorServiceImpl implements ValorService{

    @Override
    public Status validaValor(StatusForm statusForm, Pedido pedido) {
        BigDecimal soma= pedido.getItems().stream().map(item->item.getPrecoUnitario()).reduce(BigDecimal.ZERO,BigDecimal::add);
        Integer compara = statusForm.getValorAprovado().compareTo(soma);

        return (compara == 0 ? Status.APROVADO : (compara == -1 ? Status.APROVADO_VALOR_A_MENOR : Status.APROVADO_VALOR_A_MAIOR));
    }
}
