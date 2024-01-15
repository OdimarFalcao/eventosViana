package com.ifpi.eventosviana.repository;

import com.ifpi.eventosviana.domain.EventosParticipantes;
import com.ifpi.eventosviana.domain.Participantes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EventosParticipantesRepository extends JpaRepository<EventosParticipantes,Long> {

    @Query(value = "select  * from tb02_participantes tp inner join tb03_eventos_participantes tep on tep.tb03_cod_participante = tp.tb02_cod_participantes\n" +
            "and tep.tb03_cod_evento =:id ;", nativeQuery = true)
    Page<Participantes> findAllByCodEvento(@Param("id") Long id, Pageable pageable);
}
