package com.pocms.email.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "EMAIL")
public class Email implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FK", nullable = false)
    private Long fk;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "DATA", nullable = false)
    private LocalDateTime data;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "ROTINA", nullable = false)
    private Rotina rotina;

    @Column(name = "TITULO", nullable = false)
    private String titulo;

    @Lob
    @Column(name = "CONTEUDO", nullable = false)
    private String conteudo;

    @Deprecated
    public Email() {
    }

    public Email(
            @NonNull Long fk,
            @NonNull String email,
            @NonNull Rotina rotina,
            @NonNull String titulo,
            @NonNull String conteudo) {
        this.fk = Objects.requireNonNull(fk, "fk não pode ser nulo");
        this.email = Objects.requireNonNull(email, "email não pode ser nulo");
        this.rotina = Objects.requireNonNull(rotina, "rotina não pode ser nula");
        this.titulo = Objects.requireNonNull(titulo, "titulo não pode ser nulo");
        this.conteudo = Objects.requireNonNull(conteudo, "conteudo não pode ser nulo");
        this.data = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Long getFk() {
        return fk;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getData() {
        return data;
    }

    public Rotina getRotina() {
        return rotina;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", data=" + data +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(id, email.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
