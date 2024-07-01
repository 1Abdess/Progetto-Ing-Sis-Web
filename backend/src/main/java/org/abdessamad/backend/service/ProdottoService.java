package org.abdessamad.backend.service;

import org.abdessamad.backend.model.Prodotto;
import org.abdessamad.backend.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdottoService {
    @Autowired
    private ProdottoRepository prodottoRepository;

    public List<Prodotto> getProductsByCategoryAndBrand(String categoria, String marca) {
        return prodottoRepository.findByCategoryAndBrand(categoria, marca);
    }
}
