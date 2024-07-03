package org.abdessamad.backend.controller;

import org.abdessamad.backend.model.Utente;
import org.abdessamad.backend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/utenti")
    public ResponseEntity<List<Utente>> getAllRegisteredUsers() {
        List<Utente> users = adminService.getAllRegisteredUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/utenti/{id}")
    public ResponseEntity<Utente> getUserById(@PathVariable Long id) {
        Optional<Utente> user = adminService.getUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/utenti/{id}/blocca")
    public ResponseEntity<String> blockUser(@PathVariable Long id) {
        adminService.blockUser(id);
        return ResponseEntity.ok("Utente bloccato con successo.");
    }

    @PostMapping("/utenti/{id}/sblocca")
    public ResponseEntity<String> unblockUser(@PathVariable Long id) {
        adminService.unblockUser(id);
        return ResponseEntity.ok("Utente sbloccato con successo.");
    }

    @PostMapping("/utenti/{id}/revoca-amministratore")
    public ResponseEntity<String> revokeAdmin(@PathVariable Long id) {
        adminService.revokeAdmin(id);
        return ResponseEntity.ok("Stato di amministratore rimosso con successo.");
    }
}
