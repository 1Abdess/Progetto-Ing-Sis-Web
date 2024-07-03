package org.abdessamad.backend.service;

import org.abdessamad.backend.model.Utente;
import org.abdessamad.backend.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private UtenteRepository utenteRepository;

    public List<Utente> getAllRegisteredUsers() {
        return utenteRepository.findByRuolo(Utente.Ruolo.registrato);
    }

    public Optional<Utente> getUserById(Long id) {
        return utenteRepository.findById(id);
    }

    public void blockUser(Long id) {
        Optional<Utente> userOptional = utenteRepository.findById(id);
        if (userOptional.isPresent()) {
            Utente user = userOptional.get();
            user.setBloccato(true);
            utenteRepository.save(user);
        }
    }

    public void unblockUser(Long id) {
        Optional<Utente> userOptional = utenteRepository.findById(id);
        if (userOptional.isPresent()) {
            Utente user = userOptional.get();
            user.setBloccato(false);
            utenteRepository.save(user);
        }
    }

    public void revokeAdmin(Long id) {
        Optional<Utente> userOptional = utenteRepository.findById(id);
        if (userOptional.isPresent()) {
            Utente user = userOptional.get();
            if (user.getRuolo() == Utente.Ruolo.amministratore) {
                user.setRuolo(Utente.Ruolo.registrato);
                utenteRepository.save(user);
            }
        }
    }
}
