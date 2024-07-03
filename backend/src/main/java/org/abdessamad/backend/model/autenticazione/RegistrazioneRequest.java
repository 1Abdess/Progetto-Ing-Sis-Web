package org.abdessamad.backend.model.autenticazione;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RegistrazioneRequest {

    private String email;
    private String password;
    private String nomeUtente;

}
