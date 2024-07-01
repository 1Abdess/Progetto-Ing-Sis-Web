package org.abdessamad.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "Elementi_Carrello")
@Data
public class ElementoCarrello {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idElementoCarrello;

    @ManyToOne
    @JoinColumn(name = "id_carrello")
    private Carrello carrello;

    @ManyToOne
    @JoinColumn(name = "id_prodotto")
    private Prodotto prodotto;

    @Column(nullable = false, name = "quantita")
    private int quantita;

    @Column(nullable = false, precision = 10, scale = 2, name = "prezzo_unitario")
    private BigDecimal prezzoUnitario;
}
