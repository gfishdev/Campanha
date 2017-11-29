package br.com.guilherme.campanha.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "CAMPANHA")
public class Campanha implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CAMPANHA", columnDefinition = "int(11)")
    private Long id;

    @Column(name = "NOME", columnDefinition = "varchar(45)")
    private String nome;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Time.class)
    @JoinColumn(name = "ID_TIME")
    private Time time;

    @Column(name = "DT_VIGENCIA_INICIO", columnDefinition = "date")
    private LocalDate vigenciaInicio;

    @Column(name = "DT_VIGENCIA_FIM", columnDefinition = "date")
    private LocalDate vigenciaFim;

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

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public LocalDate getVigenciaInicio() {
        return vigenciaInicio;
    }

    public void setVigenciaInicio(LocalDate vigenciaInicio) {
        this.vigenciaInicio = vigenciaInicio;
    }

    public LocalDate getVigenciaFim() {
        return vigenciaFim;
    }

    public void setVigenciaFim(LocalDate vigenciaFim) {
        this.vigenciaFim = vigenciaFim;
    }

    @Override
    public String toString() {
        return id + " of " + nome;
    }
}
