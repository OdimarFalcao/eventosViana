package com.ifpi.eventosviana.service;

import com.ifpi.eventosviana.domain.Participantes;
import com.ifpi.eventosviana.dto.ParticipantesDto;
import com.ifpi.eventosviana.handler.exceptions.ConflitoException;
import com.ifpi.eventosviana.handler.exceptions.NotFound;
import com.ifpi.eventosviana.repository.EventosParticipantesRepository;
import com.ifpi.eventosviana.repository.ParticipantesRepository;
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
public class ParticipanteService {

    private final ParticipantesRepository participantesRepository;

    private final EventosParticipantesRepository eventosParticipantesRepository;
    private final ModelMapper modelMapper = new ModelMapper();


    public ParticipanteService(ParticipantesRepository participantesRepository, EventosParticipantesRepository eventosParticipantesRepository) {
        this.participantesRepository = participantesRepository;
        this.eventosParticipantesRepository = eventosParticipantesRepository;
    }


    @Transactional
    public ParticipantesDto cadastrar(ParticipantesDto participantesDto) {
        validarEventoCadastro(participantesDto);
        Participantes participantes = modelMapper.map(participantesDto, Participantes.class);
        Participantes participantesCadastrados = participantesRepository.save(participantes);
        return modelMapper.map(participantesCadastrados, ParticipantesDto.class);
    }

    private void validarEventoCadastro(ParticipantesDto participantesDto) {

        List<Participantes> participantes = participantesRepository.findByNomeIgnoreCase(participantesDto.getNome());

        if (!participantes.isEmpty() && Objects.nonNull(participantes))
            throw new ConflitoException("Participante já cadastrado!");
    }


    @Transactional
    public ParticipantesDto atualizar(Long id, ParticipantesDto participantesDto) {
        participantesDto.setId(id);
        Participantes participantes = modelMapper.map(participantesDto, Participantes.class);
        Participantes partcipanteSalvo = participantesRepository.save(participantes);
        return modelMapper.map(partcipanteSalvo, ParticipantesDto.class);
    }

    public Page<ParticipantesDto> listar(Pageable pageable) {

        Page<Participantes> participantes = participantesRepository.findAll(pageable);

        List<ParticipantesDto> listaParticipantes = participantes.getContent()
                .stream()
                .map(participante -> modelMapper.map(participante,ParticipantesDto.class))
                .collect(Collectors.toList());

        return new PageImpl<>(listaParticipantes,pageable,participantes.getTotalElements());
    }

    public void deletarParticipante(Long id) {
        participantesRepository.findById(id).orElseThrow(new NotFound("Evento não encontrado"));
        participantesRepository.deleteById(id);
    }

    public List<ParticipantesDto> listarParticipantesEvento(Long id,Pageable pageable) {

        List<Participantes> participantes = participantesRepository.findAllByCodEvento(id);

        List<ParticipantesDto> listaParticipantes = participantes
                .stream()
                .map(participante -> modelMapper.map(participante,ParticipantesDto.class))
                .collect(Collectors.toList());

        return listaParticipantes;
    }
}
