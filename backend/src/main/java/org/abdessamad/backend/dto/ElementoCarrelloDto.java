package org.abdessamad.backend.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ElementoCarrelloDto {
    private Long id;
    private String nomeProdotto;
    private int quantita;
    private BigDecimal prezzoUnitario;
}
