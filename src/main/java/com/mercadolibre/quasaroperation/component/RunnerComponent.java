package com.mercadolibre.quasaroperation.component;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.quasaroperation.model.SateliteModel;
import com.mercadolibre.quasaroperation.model.table.SateliteTable;
import com.mercadolibre.quasaroperation.presitence.SatelitePresistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class RunnerComponent implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(RunnerComponent.class);
    @Autowired
    private SatelitePresistence satelitePresistence;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws  JsonProcessingException, Exception{
        logger.info("iniciacion de datos en la base");

        List<SateliteModel> listaDeSatelites = new ArrayList<>();

        var sateliteKenobi = new SateliteModel();
        sateliteKenobi.setName("kenobi");
        sateliteKenobi.setDistance(100.0);
        sateliteKenobi.setMessage(Arrays.asList("este", "", "", "mensaje", ""));

        var sateliteSkywalker = new SateliteModel();
       sateliteSkywalker.setName("skywalker");
       sateliteSkywalker.setDistance(115.5);
       sateliteSkywalker.setMessage(Arrays.asList("", "es", "", "", "secreto"));

        var sateliteSato = new SateliteModel();
        sateliteSato.setName("sato");
        sateliteSato.setDistance(142.7);
        sateliteSato.setMessage(Arrays.asList("este", "", "un", "", ""));

        listaDeSatelites.add(sateliteKenobi);
        listaDeSatelites.add(sateliteSkywalker);
        listaDeSatelites.add(sateliteSato);


        listaDeSatelites.stream().forEach( item-> {
            var satelite = new SateliteTable();
            satelite.setDistance(BigDecimal.valueOf(item.getDistance()));
            satelite.setName(item.getName());

            try {
                satelite.setMessage(objectMapper.writeValueAsString(item.getMessage()));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            satelitePresistence.save(satelite);
        });

    }

}