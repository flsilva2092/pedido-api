package com.flsilva2092.pedido.service;

import com.flsilva2092.pedido.model.Pedido;
import com.flsilva2092.pedido.model.Status;
import com.flsilva2092.pedido.model.form.StatusForm;

public interface ValorService {
    Status validaValor(StatusForm statusForm, Pedido pedido);

}
