package org.abdessamad.backend.controller;

import org.abdessamad.backend.model.Prodotto;
import org.abdessamad.backend.service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProdottoController {
    @Autowired
    private ProdottoService prodottoService;

    /*
    Esempi di richieste che posso fare usando quest'impostazione:
        /prodotti?categoria=electronics&marca=Samsung
        /prodotti?categoria=electronics
        /prodotti?marca=Samsung
        /prodotti per ottenere tutti i prodotti senza filtri
    */
    @RequestMapping("/prodotti")
    public ResponseEntity<List<Prodotto>> getProducts(@RequestParam(required = false) String categoria, @RequestParam(required = false) String marca) {
        List<Prodotto> prodotti = prodottoService.getProductsByCategoryAndBrand(categoria, marca);
        return ResponseEntity.ok(prodotti);
    }

}
