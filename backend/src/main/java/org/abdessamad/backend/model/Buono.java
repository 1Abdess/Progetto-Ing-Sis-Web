package org.abdessamad.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "buoni")
@Data
public class Buono {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_buono")
    private Long id;

    @Column(name = "codice", unique = true, nullable = false)
    private String codice;

    @Column(name = "percentuale_sconto", nullable = false)
    private int percentualeSconto;

    @Column(name = "valido_da")
    private LocalDate validoDa;

    @Column(name = "valido_a")
    private LocalDate validoA;

    @Column(name = "attivo", nullable = false)
    private boolean attivo;
}
