package org.abdessamad.backend.controller;

import org.abdessamad.backend.model.Utente;
import org.abdessamad.backend.model.autenticazione.AuthenticationRequest;
import org.abdessamad.backend.model.autenticazione.AuthenticationResponse;
import org.abdessamad.backend.model.autenticazione.RegistrazioneRequest;
import org.abdessamad.backend.service.RegistrazioneService;

import org.abdessamad.backend.config.CustomUserDetailsService;
import org.abdessamad.backend.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autenticazione")
public class AutenticazioneController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private RegistrazioneService registrazioneService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );
        } catch (Exception e) {
            return ResponseEntity.status(403).body(new AuthenticationResponse("Credenziali non valide"));
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/registrazione")
    public ResponseEntity<String> registerUser(@RequestBody RegistrazioneRequest registrazioneRequest) {
        try {
            Utente nuovoUtente = registrazioneService.registraNuovoUtente(registrazioneRequest);
            return ResponseEntity.ok("Registrazione avvenuta con successo per l'utente: " + nuovoUtente.getEmail());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Errore durante la registrazione: " + e.getMessage());
        }
    }
}
