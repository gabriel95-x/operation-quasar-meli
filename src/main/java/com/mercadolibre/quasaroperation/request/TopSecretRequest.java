package com.mercadolibre.quasaroperation.request;

import com.mercadolibre.quasaroperation.model.SateliteModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopSecretRequest {
    List<SateliteModel> satellites;

}