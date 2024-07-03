package org.abdessamad.backend.repository;

import org.abdessamad.backend.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {
    List<Utente> findByRuolo(Utente.Ruolo ruolo);
    Optional<Utente> findByEmail(String email);
}
