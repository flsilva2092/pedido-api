package com.flsilva2092.pedido.controller;

import com.flsilva2092.pedido.dto.PedidoDto;
import com.flsilva2092.pedido.model.Pedido;
import com.flsilva2092.pedido.model.form.PedidoForm;
import com.flsilva2092.pedido.repository.ItemRepository;
import com.flsilva2092.pedido.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedido")
public class PedidosController {
	
	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ItemRepository itemRepository;

	@GetMapping
	public List<PedidoDto> lista() {
		List<Pedido> pedidos = pedidoRepository.findAll();
		return PedidoDto.converter(pedidos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PedidoDto> getPedido(@PathVariable Long id) {
		Optional<Pedido> optional = pedidoRepository.findById(id);
		if (optional.isPresent()) {
			return ResponseEntity.ok(new PedidoDto(optional.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<PedidoDto> cadastrar(@RequestBody @Valid PedidoForm form, UriComponentsBuilder uriBuilder) {
		Pedido pedido = form.converter();
		pedidoRepository.save(pedido);
		
		URI uri = uriBuilder.path("/api/pedido/{id}").buildAndExpand(pedido.getId()).toUri();
		return ResponseEntity.created(uri).body(new PedidoDto(pedido));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PedidoDto> atualizar(@PathVariable Long id, @RequestBody @Valid PedidoForm form) {
		Optional<Pedido> optional = pedidoRepository.findById(id);
		if (optional.isPresent()) {
			Pedido pedido = form.atualizar(id, pedidoRepository);
			return ResponseEntity.ok(new PedidoDto(pedido));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Pedido> optional = pedidoRepository.findById(id);
		if (optional.isPresent()) {
			pedidoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}