package br.com.guilherme.campanha.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "time")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIME", columnDefinition = "int(11)")
    private Long id;

    @Column(name = "NOME", columnDefinition = "varchar(45)")
    private String nome;

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
}
