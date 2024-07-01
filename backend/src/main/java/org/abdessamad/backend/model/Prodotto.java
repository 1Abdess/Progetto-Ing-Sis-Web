package org.abdessamad.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "prodotti")
@Data
@NoArgsConstructor
public class Prodotto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prodotto")
    private Long idProdotto;

    @Column(nullable = false, name = "nome")
    private String nome;

    @Column(nullable = false, name = "descrizione")
    private String descrizione;

    @Column(nullable = false, name = "prezzo")
    private double prezzo;

    @Column(nullable = false, name = "quantita_disponibile")
    private int quantitaDisponibile;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "marca")
    private String marca;

    public Long getId() {
        return idProdotto;
    }

    public void setId(Long id) {
        this.idProdotto = id;
    }
}
