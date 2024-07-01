package org.abdessamad.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Carrelli")
@Data
public class Carrello {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarrello;

    @OneToOne
    @JoinColumn(name = "id_utente")
    private Utente utente;

    @OneToMany(mappedBy = "carrello")
    private List<ElementoCarrello> elementiCarrello;

    public BigDecimal calcolaTotale() {
        return elementiCarrello.stream()
                .map(item -> item.getPrezzoUnitario().multiply(new BigDecimal(item.getQuantita())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
