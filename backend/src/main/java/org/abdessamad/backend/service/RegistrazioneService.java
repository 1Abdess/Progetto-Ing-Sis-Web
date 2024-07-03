package org.abdessamad.backend.service;

import org.abdessamad.backend.model.Utente;
import org.abdessamad.backend.model.autenticazione.RegistrazioneRequest;
import org.abdessamad.backend.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrazioneService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Utente registraNuovoUtente(RegistrazioneRequest registrazioneRequest) {
        Utente utente = new Utente();
        utente.setEmail(registrazioneRequest.getEmail());
        utente.setNomeUtente(registrazioneRequest.getNomeUtente());
        utente.setPassword(passwordEncoder.encode(registrazioneRequest.getPassword()));
        utente.setRuolo(Utente.Ruolo.registrato);
        utente.setBloccato(false);

        return utenteRepository.save(utente);
    }
}
