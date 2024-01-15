package com.ifpi.eventosviana.domain;

import javax.persistence.*;

@Entity
@Table(name = "TB03_EVENTOS_PARTICIPANTES")
@SequenceGenerator(name = "TB03_EVENTOS_PARTICIPANTES_TB03_COD_SEQ", sequenceName = "TB03_EVENTOS_PARTICIPANTES_TB03_COD_SEQ", allocationSize = 1)
public class EventosParticipantes {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB03_EVENTOS_PARTICIPANTES_TB03_COD_SEQ")
    @Column(name = "TB03_COD")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "TB03_COD_EVENTOS")
    private Eventos codEvento;

    @ManyToOne
    @JoinColumn(name = "TB03_COD_PARTICIPANTES")
    private Participantes codParticipante;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Eventos getCodEvento() {
        return codEvento;
    }

    public void setCodEvento(Eventos codEvento) {
        this.codEvento = codEvento;
    }

    public Participantes getCodParticipante() {
        return codParticipante;
    }

    public void setCodParticipante(Participantes codParticipante) {
        this.codParticipante = codParticipante;
    }
}
