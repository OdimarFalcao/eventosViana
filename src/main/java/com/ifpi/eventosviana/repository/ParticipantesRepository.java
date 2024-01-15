package com.ifpi.eventosviana.repository;

import com.ifpi.eventosviana.domain.Participantes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParticipantesRepository extends JpaRepository<Participantes,Long> {
    List<Participantes> findByNomeIgnoreCase(String nome);

    @Query(value = "select  * from tb02_participantes tp inner join tb03_eventos_participantes tep on tep.tb03_cod_participante = tp.tb02_cod_participantes\n" +
            "where tep.tb03_cod_evento = :id ;\n", nativeQuery = true)
    List<Participantes> findAllByCodEvento(@Param("id") Long id);
}
