package org.abdessamad.backend.repository;

import org.abdessamad.backend.model.Carrello;
import org.abdessamad.backend.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarrelloRepository extends JpaRepository<Carrello, Long> {
    List<Carrello> findByUtenteAndTipo(Utente utente, String tipo);
    List<Carrello> findByUtenteAndTipoAndStato(Utente utente, String tipo, String stato);
}
