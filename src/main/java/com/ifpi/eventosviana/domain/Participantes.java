package com.ifpi.eventosviana.domain;


import javax.persistence.*;

@Entity
@Table(name = "TB02_PARTICIPANTES")
@SequenceGenerator(name = "TB02_PARTICIPANTES_TB02_COD_PARTICIPANTES_SEQ", sequenceName = "TB02_PARTICIPANTES_TB02_COD_PARTICIPANTES_SEQ", allocationSize = 1)
public class Participantes {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB02_PARTICIPANTES_TB02_COD_PARTICIPANTES_SEQ")
    @Column(name = "TB02_COD_PARTICIPANTES")
    private Long id;


    @Column(name = "TB02_NOME")
    private String nome;

    @Column(name = "TB02_RG")
    private String rg;

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

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }
}
