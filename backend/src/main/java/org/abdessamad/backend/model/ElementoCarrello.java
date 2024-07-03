package org.abdessamad.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "elementi_carrello")
@Data
public class ElementoCarrello {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_elemento_carrello")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_carrello", nullable = false)
    @JsonBackReference
    private Carrello carrello;

    @ManyToOne
    @JoinColumn(name = "id_prodotto", nullable = false)
    private Prodotto prodotto;

    @Column(name = "quantita", nullable = false)
    private int quantita;

    @Column(name = "prezzo_unitario", nullable = false)
    private BigDecimal prezzoUnitario;
}
