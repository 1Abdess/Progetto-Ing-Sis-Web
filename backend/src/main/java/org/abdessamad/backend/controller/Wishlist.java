package org.abdessamad.backend.controller;

import org.abdessamad.backend.model.Prodotto;
import org.abdessamad.backend.service.CarrelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class Wishlist {
    @Autowired
    private CarrelloService wishListService;

    // Aggiungi prodotto alla wishlist di un utente
    @PostMapping("/add")
    public ResponseEntity<String> addProductToWishList(@RequestParam Long userId, @RequestParam Long productId) {
        try {
            wishListService.addProductToWishList(userId, productId);
            return ResponseEntity.ok("Prodotto aggiunto alla wishlist con successo.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Errore nell'aggiungere il prodotto alla wishlist: " + e.getMessage());
        }
    }

    // Rimuovi prodotto dalla wishlist di un utente
    @DeleteMapping("/remove")
    public ResponseEntity<String> removeProductFromWishList(@RequestParam Long userId, @RequestParam Long productId) {
        try {
            wishListService.removeProductFromWishList(userId, productId);
            return ResponseEntity.ok("Prodotto rimosso dalla wishlist con successo.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Errore nella rimozione del prodotto dalla wishlist: " + e.getMessage());
        }
    }

    // Visualizza tutti i prodotti nella wishlist di un utente
    @GetMapping("/{userId}")
    public ResponseEntity<List<Prodotto>> getWishListByUserId(@PathVariable Long userId) {
        try {
            List<Prodotto> prodotti = wishListService.getWishListByUserId(userId);
            return ResponseEntity.ok(prodotti);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
