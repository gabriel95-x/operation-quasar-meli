package com.mercadolibre.quasaroperation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InfoSateliteModel {

    @NotNull
    Double distance;

    @NotNull
    List<String> message;
}
