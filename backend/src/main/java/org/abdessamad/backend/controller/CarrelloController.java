package org.abdessamad.backend.controller;

import org.abdessamad.backend.model.Prodotto;
import org.abdessamad.backend.service.CarrelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrello")
public class CarrelloController {

    @Autowired
    private CarrelloService carrelloService;

    @PostMapping("/aggiungi")
    public ResponseEntity<String> addProductToShoppingCart(@RequestParam Long userId, @RequestParam Long productId, @RequestParam int quantita) {
        try {
            carrelloService.addProductToShoppingCart(userId, productId, quantita);
            return ResponseEntity.ok("Prodotto aggiunto al carrello con successo.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Errore nell'aggiungere il prodotto al carrello: " + e.getMessage());
        }
    }

    @PostMapping("/acquista")
    public ResponseEntity<String> purchaseCart(@RequestParam Long userId) {
        try {
            carrelloService.purchaseCart(userId);
            return ResponseEntity.ok("Acquisto completato con successo.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Errore nell'effettuare l'acquisto: " + e.getMessage());
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getShoppingCartByUserId(@PathVariable Long userId) {
        try {
            List<Prodotto> prodotti = carrelloService.getShoppingCartByUserId(userId);
            return ResponseEntity.ok(prodotti);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Errore nel recuperare il carrello: " + e.getMessage());
        }
    }
}
