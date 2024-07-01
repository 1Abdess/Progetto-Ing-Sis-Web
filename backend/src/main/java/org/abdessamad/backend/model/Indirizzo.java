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

    @Column(nullable = false, name = "via")
    private String via;

    @Column(nullable = false, name = "citta")
    private String citta;

    @Column(name = "stato")
    private String stato;

    @Column(name = "codice_postale")
    private String codicePostale;

    @Column(nullable = false, name = "paese")
    private String paese;
}
