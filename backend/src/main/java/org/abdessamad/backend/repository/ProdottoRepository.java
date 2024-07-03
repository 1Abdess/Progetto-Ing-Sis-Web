package org.abdessamad.backend.repository;

import org.abdessamad.backend.model.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {
    @Query(
            "SELECT p " +
            "FROM Prodotto p " +
            "WHERE (:categoria IS NULL OR p.categoria = :categoria) AND (:marca IS NULL OR p.marca = :marca)" +
            "ORDER BY p.categoria, p.marca"
    )
    List<Prodotto> findByCategoryAndBrand(@Param("categoria") String categoria, @Param("marca") String marca);

    List<Prodotto> findByBloccatoFalse();
}
