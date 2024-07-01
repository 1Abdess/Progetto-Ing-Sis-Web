package org.abdessamad.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "utenti")
@Data
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtente;

    @Column(nullable = false, unique = true, name = "nome_utente")
    private String nomeUtente;

    @Column(nullable = false, name = "password")
    private String password;

    @Column(nullable = false, unique = true, name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "ruolo")
    private Ruolo ruolo;
}