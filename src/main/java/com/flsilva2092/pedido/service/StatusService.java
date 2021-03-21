package com.flsilva2092.pedido.service;

import com.flsilva2092.pedido.dto.StatusDto;
import com.flsilva2092.pedido.model.form.StatusForm;

public interface StatusService {
    StatusDto validaStatus(StatusForm statusForm);
}
