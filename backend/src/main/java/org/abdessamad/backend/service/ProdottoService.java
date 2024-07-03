package org.abdessamad.backend.service;

import org.abdessamad.backend.model.Prodotto;
import org.abdessamad.backend.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdottoService {
    @Autowired
    private ProdottoRepository prodottoRepository;

    public Prodotto addProduct(Prodotto prodotto) {
        return prodottoRepository.save(prodotto);
    }


    public List<Prodotto> getProductsByCategoryAndBrand(String categoria, String marca) {
        return prodottoRepository.findByCategoryAndBrand(categoria, marca);
    }

    public Prodotto updateProduct(Long id, Prodotto prodottoDetails) {
        Optional<Prodotto> optionalProdotto = prodottoRepository.findById(id);
        if (optionalProdotto.isPresent()) {
            Prodotto prodotto = optionalProdotto.get();
            prodotto.setNome(prodottoDetails.getNome());
            prodotto.setDescrizione(prodottoDetails.getDescrizione());
            prodotto.setPrezzo(prodottoDetails.getPrezzo());
            prodotto.setQuantitaDisponibile(prodottoDetails.getQuantitaDisponibile());
            prodotto.setCategoria(prodottoDetails.getCategoria());
            prodotto.setMarca(prodottoDetails.getMarca());
            return prodottoRepository.save(prodotto);
        } else {
            throw new RuntimeException("Prodotto non trovato");
        }
    }

    public void blockProduct(Long id) {
        Optional<Prodotto> optionalProdotto = prodottoRepository.findById(id);
        if (optionalProdotto.isPresent()) {
            Prodotto prodotto = optionalProdotto.get();
            prodotto.setBloccato(true);
            prodottoRepository.save(prodotto);
        } else {
            throw new RuntimeException("Prodotto non trovato");
        }
    }

    public void unblockProduct(Long id) {
        Optional<Prodotto> optionalProdotto = prodottoRepository.findById(id);
        if (optionalProdotto.isPresent()) {
            Prodotto prodotto = optionalProdotto.get();
            prodotto.setBloccato(false);
            prodottoRepository.save(prodotto);
        } else {
            throw new RuntimeException("Prodotto non trovato");
        }
    }

    public void updateProductStock(Long id, int quantita) {
        Optional<Prodotto> optionalProdotto = prodottoRepository.findById(id);
        if (optionalProdotto.isPresent()) {
            Prodotto prodotto = optionalProdotto.get();
            prodotto.setQuantitaDisponibile(quantita);
            prodottoRepository.save(prodotto);
        } else {
            throw new RuntimeException("Prodotto non trovato");
        }
    }
}
