package org.abdessamad.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "indirizzi")
@Data
public class Indirizzo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIndirizzo;

    @ManyToOne
    @JoinColumn(name = "id_utente")
    private Utente utente;

    @Column(nullable = false)
    private String via;

    @Column(nullable = false)
    private String citta;

    @Column
    private String stato;

    @Column
    private String codicePostale;

    @Column(nullable = false)
    private String paese;
}
