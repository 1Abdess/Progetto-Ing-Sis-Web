package org.abdessamad.backend.controller;

import org.abdessamad.backend.dto.IndirizzoDTO;
import org.abdessamad.backend.model.Indirizzo;
import org.abdessamad.backend.service.IndirizzoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utente/indirizzo")
public class IndirizzoController {
    @Autowired
    private IndirizzoService indirizzoService;

    @PostMapping("/crea")
    public ResponseEntity<IndirizzoDTO> addAddress(@RequestParam Long userId, @RequestBody Indirizzo indirizzo) {
        Indirizzo newIndirizzo = indirizzoService.addAddress(userId, indirizzo);
        IndirizzoDTO indirizzoDTO = indirizzoService.convertToDTO(newIndirizzo);
        return ResponseEntity.ok(indirizzoDTO);
    }
}
