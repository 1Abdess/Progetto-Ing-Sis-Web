package org.abdessamad.backend.service;

import org.abdessamad.backend.dto.IndirizzoDTO;
import org.abdessamad.backend.model.Indirizzo;
import org.abdessamad.backend.model.Utente;
import org.abdessamad.backend.repository.IndirizzoRepository;
import org.abdessamad.backend.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class IndirizzoService {

    private static final Logger logger = Logger.getLogger(IndirizzoService.class.getName());

    @Autowired
    private IndirizzoRepository indirizzoRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    public Indirizzo addAddress(Long userId, Indirizzo indirizzo) {
        logger.info("Trovando l'utente con ID: " + userId);
        Utente utente = utenteRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));

        logger.info("Impostando l'utente per l'indirizzo");
        indirizzo.setUtente(utente);

        logger.info("Salvando l'indirizzo");
        return indirizzoRepository.save(indirizzo);
    }

    public IndirizzoDTO convertToDTO(Indirizzo indirizzo) {
        IndirizzoDTO dto = new IndirizzoDTO();
        dto.setId(indirizzo.getId());
        dto.setVia(indirizzo.getVia());
        dto.setCitta(indirizzo.getCitta());
        dto.setStato(indirizzo.getStato());
        dto.setCodicePostale(indirizzo.getCodicePostale());
        dto.setPaese(indirizzo.getPaese());
        dto.setUserId(indirizzo.getUtente().getId());
        return dto;
    }
}
