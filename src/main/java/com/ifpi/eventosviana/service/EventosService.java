package com.ifpi.eventosviana.service;

import com.ifpi.eventosviana.domain.Eventos;
import com.ifpi.eventosviana.dto.EventoDto;
import com.ifpi.eventosviana.handler.exceptions.ConflitoException;
import com.ifpi.eventosviana.handler.exceptions.NotFound;
import com.ifpi.eventosviana.repository.EventoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EventosService {

    private final EventoRepository eventoRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public EventosService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    @Transactional
    public EventoDto cadastrar(EventoDto eventoDto) {
        validarEventoCadastro(eventoDto);
        Eventos eventos = modelMapper.map(eventoDto, Eventos.class);
        Eventos eventosCadastrados = eventoRepository.save(eventos);
        return modelMapper.map(eventosCadastrados, EventoDto.class);
    }

    private void validarEventoCadastro(EventoDto eventoDto) {

        List<Eventos> eventos = eventoRepository.findByNomeIgnoreCase(eventoDto.getNome());

        if (!eventos.isEmpty() && Objects.nonNull(eventos))
            throw new ConflitoException("Evento já cadastrado!");
    }


    @Transactional
    public EventoDto atualizar(Long id, EventoDto evenentoDto) {
        evenentoDto.setId(id);
        Eventos eventos = modelMapper.map(evenentoDto, Eventos.class);
        Eventos eventosAtualizado = eventoRepository.save(eventos);
        return modelMapper.map(eventosAtualizado, EventoDto.class);
    }

    public Page<EventoDto> listar(Pageable pageable) {

        Page<Eventos> eventos = eventoRepository.findAll(pageable);

        List<EventoDto> listaEventos = eventos.getContent()
                .stream()
                .map(evento -> modelMapper.map(evento,EventoDto.class))
                .collect(Collectors.toList());

        return new PageImpl<>(listaEventos,pageable,eventos.getTotalElements());
    }

    public void deletarEvento(Long id) {
        eventoRepository.findById(id).orElseThrow(new NotFound("Evento não encontrado"));
        eventoRepository.deleteById(id);
    }
}
