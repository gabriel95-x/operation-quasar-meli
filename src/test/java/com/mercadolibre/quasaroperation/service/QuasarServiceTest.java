package com.mercadolibre.quasaroperation.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mercadolibre.quasaroperation.model.SateliteModel;
import com.mercadolibre.quasaroperation.model.table.SateliteTable;
import com.mercadolibre.quasaroperation.presitence.SatelitePresistence;
import com.mercadolibre.quasaroperation.request.TopSecretRequest;
import com.mercadolibre.quasaroperation.util.QuasarUtils;
import com.mercadolibre.quasaroperation.util.UtilMeli;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.util.CollectionUtils;


import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class QuasarServiceTest {

    @Mock
    QuasarUtils quasarUtilsMock;
    @Mock
    UtilMeli utilMeli;
    @Mock
    SatelitePresistence satelitePresistence;

    @InjectMocks
    QuasarService serviceMock;

    @Test
    void getPositionAndMessage() {
        when(quasarUtilsMock.getLocation(new double[]{100.0, 115.0, 142.0})).thenReturn(new Point(-200, 50));
        when(quasarUtilsMock.getMessage(any())).thenReturn("esto es un mensaje mocked");


        List<SateliteModel> listaDeSatelites = new ArrayList<>();

        var sateliteKenobi = new SateliteModel();
        sateliteKenobi.setName("kenobi");
        sateliteKenobi.setDistance(100.0);
        sateliteKenobi.setMessage(Arrays.asList("este", "", "", "mensaje", ""));

        var sateliteSkywalker = new SateliteModel();
        sateliteSkywalker.setName("skywalker");
        sateliteSkywalker.setDistance(115.0);
        sateliteSkywalker.setMessage(Arrays.asList("", "es", "", "", "secreto"));

        var sateliteSato = new SateliteModel();
        sateliteSato.setName("sato");
        sateliteSato.setDistance(142.0);
        sateliteSato.setMessage(Arrays.asList("este", "", "un", "", ""));

        listaDeSatelites.add(sateliteKenobi);
        listaDeSatelites.add(sateliteSkywalker);
        listaDeSatelites.add(sateliteSato);

        serviceMock.getPositionAndMessage(TopSecretRequest.builder().satellites(listaDeSatelites).build());

    }
    @Test
    void getPositionAndMessageByData() throws JsonProcessingException {

        var sateliteTable1 = new SateliteTable();
        sateliteTable1.setName("kenobi");
        sateliteTable1.setMessage("[\"este\"]");
        sateliteTable1.setDistance(BigDecimal.valueOf(100.0));

        var sateliteTable2 = new SateliteTable();
        sateliteTable2.setName("skywalker");
        sateliteTable2.setMessage("[\"este\"]");
        sateliteTable2.setDistance(BigDecimal.valueOf(115.0));
        var sateliteTable3 = new SateliteTable();
        sateliteTable3.setName("sato");
        sateliteTable3.setMessage("[\"este\"]");
        sateliteTable3.setDistance(BigDecimal.valueOf(142.0));

        List<SateliteTable> sateliteTable = new ArrayList<>();
        sateliteTable.add(sateliteTable1);
        sateliteTable.add(sateliteTable2);
        sateliteTable.add(sateliteTable3);

        when(satelitePresistence.findAll()).thenReturn(sateliteTable);
        when(quasarUtilsMock.getLocation(new double[]{100.0, 115.0, 142.0})).thenReturn(new Point(-200, 50));
        when(quasarUtilsMock.getMessage(any())).thenReturn("esto es un mensaje mocked");
        when(utilMeli.toListString(any())).thenReturn(Collections.singletonList("este"));

        serviceMock.getPositionAndMessageByData();

    }
    @Test
    void updateInfoSatellite() throws JsonProcessingException {
        SateliteTable satelite = new SateliteTable();

        satelite.setMessage("[\"este\",\"es\"]");
        satelite.setDistance(BigDecimal.valueOf(100.0));
        satelite.setName("sato");

        when(satelitePresistence.findOneByName("sato")).thenReturn(satelite);
        when(utilMeli.writeValueAsString(any())).thenReturn("este");
        when(satelitePresistence.save(satelite)).thenReturn(any());


        SateliteModel sateliteModel = new SateliteModel();

        sateliteModel.setDistance(100.0);
        sateliteModel.setName("sato");
        sateliteModel.setMessage(Arrays.asList("este"));

       assertTrue(serviceMock.updateInfoSatellite(sateliteModel));

    }
}