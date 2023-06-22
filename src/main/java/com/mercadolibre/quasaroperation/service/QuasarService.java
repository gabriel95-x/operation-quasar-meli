package com.mercadolibre.quasaroperation.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.mercadolibre.quasaroperation.exception.QuasarNotFoundException;
import com.mercadolibre.quasaroperation.model.PositionModel;
import com.mercadolibre.quasaroperation.model.SateliteModel;
import com.mercadolibre.quasaroperation.model.table.SateliteTable;
import com.mercadolibre.quasaroperation.presitence.SatelitePresistence;
import com.mercadolibre.quasaroperation.request.TopSecretRequest;
import com.mercadolibre.quasaroperation.response.ResponseDefinition;
import com.mercadolibre.quasaroperation.response.TopSecretResponse;
import com.mercadolibre.quasaroperation.util.QuasarUtils;
import com.mercadolibre.quasaroperation.util.UtilMeli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class QuasarService {

    @Autowired
    QuasarUtils quasarUtils;

    @Autowired
    UtilMeli utilMeli;

    @Autowired
    private SatelitePresistence satelitePresistence;


    /**LEVEL 2**/
    public TopSecretResponse getPositionAndMessage(TopSecretRequest dataSatellites) {

        //obtener posicion
        double[] ditancias;
        ditancias = dataSatellites.getSatellites().stream()
                .mapToDouble(item -> Objects.isNull(item.getDistance()) ? 0 : item.getDistance().doubleValue())
                .toArray();

        var coordenadas = this.quasarUtils.getLocation(ditancias);

        var positions = new PositionModel();
        positions.setX(BigDecimal.valueOf(coordenadas.x));
        positions.setY(BigDecimal.valueOf(coordenadas.y));


        //obteniendo mensaje
        String[][] listMessages = dataSatellites.getSatellites().stream()
                .map(i -> i.getMessage().toArray(new String[0]))
                .toArray(String[][]::new);

        return TopSecretResponse.builder()
                .position(positions)
                .message(this.quasarUtils.getMessage(listMessages)).build();

    }


    public TopSecretResponse getPositionAndMessageByData() {



        List<SateliteTable> dataSatelite = this.satelitePresistence.findAll();

        TopSecretRequest request = TopSecretRequest.builder()
                .satellites(dataSatelite.stream().map(stl -> {
                            try {
                                var satelite = new SateliteModel();
                                satelite.setName(stl.getName());
                                satelite.setDistance(stl.getDistance().doubleValue());
                                satelite.setMessage(utilMeli.toListString(stl.getMessage()));
                                return satelite;

                            } catch (JsonProcessingException e) {
                                throw new RuntimeException("mensaje:" + e);
                            }
                        })
                        .collect(Collectors.toList()))
                .build();

        return getPositionAndMessage(request);

    }





    public boolean updateInfoSatellite(SateliteModel satellite) throws JsonProcessingException {

            SateliteTable sateliteDB = this.satelitePresistence.findOneByName(satellite.getName());
            if (Objects.isNull(sateliteDB)) {
                throw new QuasarNotFoundException(ResponseDefinition.builder()
                        .action("obteniendo informacion del satelite")
                        .messageDisplay("Satelite: ".concat(satellite.getName()).concat(" inexistente."))
                        .build());
            }
            sateliteDB.setDistance(BigDecimal.valueOf(satellite.getDistance()));
            sateliteDB.setMessage(utilMeli.writeValueAsString(satellite.getMessage()));
            this.satelitePresistence.save(sateliteDB);
            return true;


    }
}
