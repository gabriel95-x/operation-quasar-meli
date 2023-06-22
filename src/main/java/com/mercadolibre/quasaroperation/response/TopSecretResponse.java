package com.mercadolibre.quasaroperation.response;

import com.mercadolibre.quasaroperation.model.PositionModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TopSecretResponse {

    PositionModel position;
    String message;
}
