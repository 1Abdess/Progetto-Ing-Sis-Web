package org.abdessamad.backend.service;

import jakarta.transaction.Transactional;
import org.abdessamad.backend.model.Carrello;
import org.abdessamad.backend.model.ElementoCarrello;
import org.abdessamad.backend.model.Prodotto;
import org.abdessamad.backend.model.Utente;
import org.abdessamad.backend.repository.CarrelloRepository;
import org.abdessamad.backend.repository.ProdottoRepository;
import org.abdessamad.backend.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarrelloService {

    @Autowired
    private CarrelloRepository carrelloRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private ProdottoRepository prodottoRepository;

    public void addProductToWishList(Long userId, Long productId) {
        Utente utente = utenteRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));
        Prodotto prodotto = prodottoRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Prodotto non trovato"));

        List<Carrello> wishLists = carrelloRepository.findByUtenteAndTipo(utente, "wishlist");
        Carrello wishList;
        if (wishLists.isEmpty()) {
            wishList = new Carrello();
            wishList.setUtente(utente);
            wishList.setTipo("wishlist");
        } else {
            wishList = wishLists.get(0);  // Prendi la prima wishlist trovata
        }

        ElementoCarrello elemento = new ElementoCarrello();
        elemento.setCarrello(wishList);
        elemento.setProdotto(prodotto);
        elemento.setQuantita(1);  // Wishlist typically has quantity 1 for each item
        elemento.setPrezzoUnitario(BigDecimal.valueOf(prodotto.getPrezzo()));  // Converti double in BigDecimal
        wishList.getElementi().add(elemento);

        carrelloRepository.save(wishList);
    }

    public void removeProductFromWishList(Long userId, Long productId) {
        Utente utente = utenteRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));
        Prodotto prodotto = prodottoRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Prodotto non trovato"));

        List<Carrello> wishLists = carrelloRepository.findByUtenteAndTipo(utente, "wishlist");
        if (wishLists.isEmpty()) {
            throw new RuntimeException("Wishlist non trovata");
        }

        Carrello wishList = wishLists.get(0);  // Prendi la prima wishlist trovata

        wishList.getElementi().removeIf(elemento -> elemento.getProdotto().getId().equals(productId));
        carrelloRepository.save(wishList);
    }

    public List<Prodotto> getWishListByUserId(Long userId) {
        Optional<Utente> utenteOpt = utenteRepository.findById(userId);
        if (!utenteOpt.isPresent()) {
            return new ArrayList<>();  // Restituisce una lista vuota se l'utente non viene trovato
        }

        Utente utente = utenteOpt.get();

        List<Carrello> wishLists = carrelloRepository.findByUtenteAndTipo(utente, "wishlist");
        if (wishLists.isEmpty()) {
            return new ArrayList<>();  // Restituisce una lista vuota se non esiste una wishlist
        }

        Carrello wishList = wishLists.get(0);  // Prendi la prima wishlist trovata

        List<Prodotto> prodotti = new ArrayList<>();
        for (ElementoCarrello elemento : wishList.getElementi()) {
            prodotti.add(elemento.getProdotto());
        }
        return prodotti;
    }

    public void addProductToShoppingCart(Long userId, Long productId, int quantita) {
        Utente utente = utenteRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));
        Prodotto prodotto = prodottoRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Prodotto non trovato"));

        if (prodotto.getQuantitaDisponibile() < quantita || quantita <= 0) {
            throw new RuntimeException("Quantità non disponibile");
        }

        List<Carrello> shoppingCarts = carrelloRepository.findByUtenteAndTipo(utente, "shopping");
        Carrello shoppingCart;
        if (shoppingCarts.isEmpty()) {
            shoppingCart = new Carrello();
            shoppingCart.setUtente(utente);
            shoppingCart.setTipo("shopping");
            shoppingCart.setTotale(0);
        } else {
            shoppingCart = shoppingCarts.get(0);  // Prendi il primo carrello trovato
        }

        ElementoCarrello elemento = new ElementoCarrello();
        elemento.setCarrello(shoppingCart);
        elemento.setProdotto(prodotto);
        elemento.setQuantita(quantita);
        elemento.setPrezzoUnitario(BigDecimal.valueOf(prodotto.getPrezzo()));

        shoppingCart.getElementi().add(elemento);
        shoppingCart.setTotale(shoppingCart.getTotale() + prodotto.getPrezzo() * quantita);

        carrelloRepository.save(shoppingCart);
    }

    @Transactional
    public void purchaseCart(Long userId) {
        Utente utente = utenteRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));

        List<Carrello> shoppingCarts = carrelloRepository.findByUtenteAndTipo(utente, "shopping");
        if (shoppingCarts.isEmpty()) {
            throw new RuntimeException("Carrello non trovato");
        }

        Carrello shoppingCart = shoppingCarts.get(0);  // Prendi il primo carrello trovato

        for (ElementoCarrello elemento : shoppingCart.getElementi()) {
            Prodotto prodotto = elemento.getProdotto();
            int quantitaAcquistata = elemento.getQuantita();
            if (prodotto.getQuantitaDisponibile() < quantitaAcquistata) {
                throw new RuntimeException("Quantità non disponibile per il prodotto: " + prodotto.getNome());
            }
            prodotto.setQuantitaDisponibile(prodotto.getQuantitaDisponibile() - quantitaAcquistata);
            prodottoRepository.save(prodotto);
        }

        // Svuota il carrello dopo l'acquisto
        shoppingCart.getElementi().clear();
        shoppingCart.setTotale(0);
        carrelloRepository.save(shoppingCart);
    }

    public List<Prodotto> getShoppingCartByUserId(Long userId) {
        Utente utente = utenteRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));

        List<Carrello> shoppingCarts = carrelloRepository.findByUtenteAndTipo(utente, "shopping");
        if (shoppingCarts.isEmpty()) {
            return new ArrayList<>();  // Restituisce una lista vuota se non esiste un carrello
        }

        Carrello shoppingCart = shoppingCarts.get(0);  // Prendi il primo carrello trovato

        List<Prodotto> prodotti = new ArrayList<>();
        for (ElementoCarrello elemento : shoppingCart.getElementi()) {
            prodotti.add(elemento.getProdotto());
        }
        return prodotti;
    }
}
