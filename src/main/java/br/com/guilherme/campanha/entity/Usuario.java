package br.com.guilherme.campanha.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO", columnDefinition = "int(11)")
    private Long id;

    @Column(name = "NOME_COMPLETO", columnDefinition = "varchar(145)")
    private String nome;

    @Column(name = "EMAIL", columnDefinition = "varchar(145)")
    private String email;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Time.class)
    @JoinColumn(name = "ID_TIME")
    private Time time;

    @Column(name = "DT_NASCIMENTO", columnDefinition = "date")
    private LocalDate dtNascimento;

    @Transient
    private List<Campanha> campanhas;

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

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Campanha> getCampanhas() {
        return campanhas;
    }

    public void setCampanhas(List<Campanha> campanhas) {
        this.campanhas = campanhas;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", time=" + time +
                ", dtNascimento=" + dtNascimento +
                ", campanhas=" + campanhas +
                '}';
    }
}
