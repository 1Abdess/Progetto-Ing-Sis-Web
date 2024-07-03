package org.abdessamad.backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "carrelli")
@Data
public class Carrello {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrello")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_utente", nullable = false)
    private Utente utente;

    @OneToMany(mappedBy = "carrello", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ElementoCarrello> elementi;

    @Column(name = "totale", nullable = false)
    private BigDecimal totale;

    @Column(name = "tipo", nullable = false)
    private String tipo; // può essere "shopping", "wishlist", ecc.

    @Column(name = "stato", nullable = false)
    private String stato; // può essere "In_Spedizione", "Consegnato", ecc.
}
