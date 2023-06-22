package com.mercadolibre.quasaroperation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SateliteModel extends  InfoSateliteModel{
    @NotBlank
    String name;

}
