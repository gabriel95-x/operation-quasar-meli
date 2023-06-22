package com.mercadolibre.quasaroperation.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ActiveSatelliteConfig {


    /** KENOBI **/
    @Value("${satelite.kenobi.positionX}")
    public  double KENOBI_POINT_X;
    @Value("${satelite.kenobi.positionY}")
    public double KENOBI_POINT_Y;

    /** SKYWALKER **/
    @Value("${satelite.skywalker.positionX}")
    public double SKYWALKER_POINT_X;
    @Value("${satelite.skywalker.positionY}")
    public double SKYWALKER_POINT_Y;


    /** SATO **/
    @Value("${satelite.sato.positionX}")
    public double SATO_POINT_X;
    @Value("${satelite.sato.positionY}")
    public double SATO_POINT_Y;



}
