package com.mercadolibre.quasaroperation.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDefinition {
    String action;
    String messageDisplay;
    @Builder.Default
    LocalDateTime date = LocalDateTime.now();
}