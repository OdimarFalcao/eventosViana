package com.ifpi.eventosviana.resource;

import com.ifpi.eventosviana.dto.EventoDto;
import com.ifpi.eventosviana.service.EventosService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/eventos")
@RestController
public class EventosResources {

    private final EventosService eventosService;

    public EventosResources(EventosService eventosService) {
        this.eventosService = eventosService;
    }

    @PostMapping
    public ResponseEntity<EventoDto> cadastrar(@Valid @RequestBody EventoDto eventoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventosService.cadastrar(eventoDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventoDto> atualizar(
            @PathVariable("id") Long id,
            @Valid @RequestBody EventoDto eventoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventosService.atualizar(id, eventoDto));
    }

    @GetMapping
    public ResponseEntity<Page<EventoDto>> listar(Pageable pageable) {
        return ResponseEntity.ok(eventosService.listar(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEvento(@PathVariable("id") Long id) {
        eventosService.deletarEvento(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
