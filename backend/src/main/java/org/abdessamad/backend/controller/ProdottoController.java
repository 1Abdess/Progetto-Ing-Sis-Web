package org.abdessamad.backend.controller;

import org.abdessamad.backend.model.Prodotto;
import org.abdessamad.backend.service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProdottoController {
    @Autowired
    private ProdottoService prodottoService;

    @GetMapping("/prodotti")
    public ResponseEntity<List<Prodotto>> getProducts(@RequestParam(required = false) String categoria, @RequestParam(required = false) String marca) {
        List<Prodotto> prodotti = prodottoService.getProductsByCategoryAndBrand(categoria, marca);
        return ResponseEntity.ok(prodotti);
    }


    @PostMapping("/admin/prodotti/aggiungi")
    public ResponseEntity<Prodotto> addProduct(@RequestBody Prodotto prodotto) {
        Prodotto newProdotto = prodottoService.addProduct(prodotto);
        return ResponseEntity.ok(newProdotto);
    }

    @PutMapping("/admin/prodotti/modifica/{id}")
    public ResponseEntity<Prodotto> updateProduct(@PathVariable Long id, @RequestBody Prodotto prodottoDetails) {
        Prodotto updatedProdotto = prodottoService.updateProduct(id, prodottoDetails);
        return ResponseEntity.ok(updatedProdotto);
    }

    @PostMapping("/admin/prodotti/modifica/{id}/blocca")
    public ResponseEntity<String> blockProduct(@PathVariable Long id) {
        prodottoService.blockProduct(id);
        return ResponseEntity.ok("Prodotto bloccato con successo.");
    }

    @PostMapping("/admin/prodotti/modifica/{id}/sblocca")
    public ResponseEntity<String> unblockProduct(@PathVariable Long id) {
        prodottoService.unblockProduct(id);
        return ResponseEntity.ok("Prodotto sbloccato con successo.");
    }

    @PutMapping("/admin/prodotti/modifica/{id}/stock")
    public ResponseEntity<String> updateProductStock(@PathVariable Long id, @RequestParam int quantita) {
        prodottoService.updateProductStock(id, quantita);
        return ResponseEntity.ok("Disponibilità del prodotto aggiornata con successo.");
    }

}
