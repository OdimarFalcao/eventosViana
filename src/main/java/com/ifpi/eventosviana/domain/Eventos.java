package com.ifpi.eventosviana.domain;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "TB01_EVENTOS")
@SequenceGenerator(name = "TB01_EVENTOS_TB01_COD_EVENTOS_SEQ", sequenceName = "TB01_EVENTOS_TB01_COD_EVENTOS_SEQ", allocationSize = 1)
public class Eventos {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB01_EVENTOS_TB01_COD_EVENTOS_SEQ")
    @Column(name = "TB01_COD_EVENTOS")
    private Long id;

    @Column(name = "TB01_NOME")
    private String nome;

    @Column(name = "TB01_LOCAL")
    private String local;

    @Column(name = "TB01_DATA")
    private Date data;

    @Column(name = "TB01_HORARIO")
    private String horario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}
