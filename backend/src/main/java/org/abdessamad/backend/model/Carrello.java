package org.abdessamad.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Carrelli")
@Data
@NoArgsConstructor
public class Carrello {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrello")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_utente", nullable = false)
    private Utente utente;

    @OneToMany(mappedBy = "carrello", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ElementoCarrello> elementi;

    @Column(name = "totale", nullable = false)
    private double totale;

    @Column(name = "tipo")
    private String tipo;
}
