package org.abdessamad.backend.repository;

import org.abdessamad.backend.model.Buono;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BuonoRepository extends JpaRepository<Buono, Long> {
    List<Buono> findByAttivoTrueAndValidoAAfter(LocalDate date);
}
