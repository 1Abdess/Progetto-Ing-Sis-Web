package org.abdessamad.backend.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CarrelloDto {
    private Long id;
    private String email;
    private List<ElementoCarrelloDto> elementi;
    private BigDecimal totale;
    private String stato;
}
