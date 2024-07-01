package org.abdessamad.backend.repository;

import org.abdessamad.backend.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<Utente, Long> {
}
