package org.abdessamad.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Carrelli")
@Data
@NoArgsConstructor
public class Carrello {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarrello;

    @OneToOne
    @JoinColumn(name = "id_utente")
    private Utente utente;

    @OneToMany(mappedBy = "carrello")
    private List<ElementoCarrello> elementiCarrello;

    @Column(name = "tipo")
    private String tipo;

    public BigDecimal calcolaTotale() {
        return elementiCarrello.stream()
                .map(item -> item.getPrezzoUnitario().multiply(new BigDecimal(item.getQuantita())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Getter e setter per elementi
    public List<ElementoCarrello> getElementi() {
        return elementiCarrello;
    }
    public void setElementi(List<ElementoCarrello> elementi) {
        this.elementiCarrello = elementi;
    }
}
