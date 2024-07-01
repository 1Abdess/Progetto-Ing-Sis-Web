package org.abdessamad.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Utenti")
@Data
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utente")
    private Long id;

    @Column(name = "nome_utente", unique = true, nullable = false)
    private String nomeUtente;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "ruolo", nullable = false)
    private Ruolo ruolo;

    public enum Ruolo {
        pubblico,
        registrato,
        amministratore
    }
}
