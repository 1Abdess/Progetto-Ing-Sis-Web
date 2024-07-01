package org.abdessamad.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Prodotti")
@Data
public class Prodotto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prodotto")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "prezzo", nullable = false)
    private double prezzo;

    @Column(name = "quantita_disponibile", nullable = false)
    private int quantitaDisponibile;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "marca")
    private String marca;
}
