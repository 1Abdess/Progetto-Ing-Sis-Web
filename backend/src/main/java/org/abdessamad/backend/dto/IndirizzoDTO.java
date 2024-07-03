package org.abdessamad.backend.dto;

import lombok.Data;

@Data
public class IndirizzoDTO {
    private Long id;
    private String via;
    private String citta;
    private String stato;
    private String codicePostale;
    private String paese;
    private Long userId;
}
