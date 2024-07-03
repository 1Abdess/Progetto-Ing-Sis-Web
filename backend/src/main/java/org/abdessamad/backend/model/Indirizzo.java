package org.abdessamad.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "indirizzi")
@Data
public class Indirizzo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_indirizzo")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_utente", nullable = false)
    private Utente utente;

    @Column(name = "via", nullable = false)
    private String via;

    @Column(name = "citta", nullable = false)
    private String citta;

    @Column(name = "stato")
    private String stato;

    @Column(name = "codice_postale")
    private String codicePostale;

    @Column(name = "paese", nullable = false)
    private String paese;
}
