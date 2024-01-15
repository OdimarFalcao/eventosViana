package com.ifpi.eventosviana.resource;

import com.ifpi.eventosviana.dto.EventoDto;
import com.ifpi.eventosviana.dto.ParticipantesDto;
import com.ifpi.eventosviana.service.ParticipanteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/participantes")
@RestController
public class ParticipanteResource {

    private final ParticipanteService participanteService;

    public ParticipanteResource(ParticipanteService participanteService) {
        this.participanteService = participanteService;
    }

    @PostMapping
    public ResponseEntity<ParticipantesDto> cadastrar(@Valid @RequestBody ParticipantesDto partcipanteDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(participanteService.cadastrar(partcipanteDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParticipantesDto> atualizar(
            @PathVariable("id") Long id,
            @Valid @RequestBody ParticipantesDto participantesDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(participanteService.atualizar(id, participantesDto));
    }

    @GetMapping("/participantesEvento/{id}")
    public ResponseEntity<List<ParticipantesDto>> listar(@PathVariable("id") Long id, Pageable pageable) {
        return ResponseEntity.ok(participanteService.listarParticipantesEvento(id,pageable));
    }

    @GetMapping
    public ResponseEntity<Page<ParticipantesDto>> listarParticpantesEvento(Pageable pageable) {
        return ResponseEntity.ok(participanteService.listar(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarParticipante(@PathVariable("id") Long id) {
        participanteService.deletarParticipante(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}