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
    @Column(name = "id_elemento_carrello")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_carrello", nullable = false)
    private Carrello carrello;

    @ManyToOne
    @JoinColumn(name = "id_prodotto", nullable = false)
    private Prodotto prodotto;

    @Column(name = "quantita", nullable = false)
    private int quantita;

    @Column(name = "prezzo_unitario", nullable = false)
    private BigDecimal prezzoUnitario;
}
