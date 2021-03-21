package com.flsilva2092.pedido.controller;

import com.flsilva2092.pedido.dto.StatusDto;
import com.flsilva2092.pedido.model.form.StatusForm;
import com.flsilva2092.pedido.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/status")
public class StatusController {

	@Autowired
	private StatusService statusService;

	@PostMapping
	public ResponseEntity<StatusDto> cadastrar(@RequestBody @Valid StatusForm form) {
		return ResponseEntity.ok(statusService.validaStatus(form));
	}

}