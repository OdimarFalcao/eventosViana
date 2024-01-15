package com.ifpi.eventosviana.repository;

import com.ifpi.eventosviana.domain.Eventos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Eventos,Long> {

    List<Eventos> findByNomeIgnoreCase(String nome);

}
