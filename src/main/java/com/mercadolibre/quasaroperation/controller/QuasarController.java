package com.mercadolibre.quasaroperation.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.mercadolibre.quasaroperation.model.InfoSateliteModel;
import com.mercadolibre.quasaroperation.model.SateliteModel;
import com.mercadolibre.quasaroperation.request.TopSecretRequest;
import com.mercadolibre.quasaroperation.response.TopSecretResponse;
import com.mercadolibre.quasaroperation.service.QuasarService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/quasar")
public class QuasarController {
    @Autowired
    QuasarService quasarService;
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Error" ),
            @ApiResponse(code = 400, message = "Error en request" ),
            @ApiResponse(code = 500, message = "Server Error" )
    } )
    @ApiOperation(value = "se encarga de obtener la ubicación de la nave y el mensaje que emite.")
    @PostMapping(value = "/topsectret",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TopSecretResponse> topsectret ( @RequestBody TopSecretRequest satellites){

        var positionAndMessage = quasarService.getPositionAndMessage(satellites);
        return ResponseEntity.ok(positionAndMessage);
    }


    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Error" ),
            @ApiResponse(code = 400, message = "Error en request" ),
            @ApiResponse(code = 500, message = "Server Error" )
    } )
    @ApiOperation(value = "obtiene cordenadas segun la informacion guardada en la base de datos")
    @GetMapping(value = "/topsectret_split")
    public ResponseEntity<TopSecretResponse> topsectret (){

        var topsectretSplit = quasarService.getPositionAndMessageByData();
        return ResponseEntity.ok(topsectretSplit);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Error" ),
            @ApiResponse(code = 400, message = "Error en request" ),
            @ApiResponse(code = 500, message = "Server Error" )
    } )
    @ApiOperation(value = "actualiza las cordenadas del satelite siempre que exista en la base.")
    @PostMapping(value = "/topsectret_split/{satellite_name}")
    public ResponseEntity<TopSecretResponse> topsectret (@RequestParam(value = "satellite_name") String satelliteName,@Valid @RequestBody InfoSateliteModel satelliteInfo ) throws JsonProcessingException {


        SateliteModel satelliteToUpdate = new SateliteModel();
        satelliteToUpdate.setMessage(satelliteInfo.getMessage());
        satelliteToUpdate.setDistance(satelliteInfo.getDistance());
        satelliteToUpdate.setName(satelliteName);
        var upate = quasarService.updateInfoSatellite(satelliteToUpdate);

         return ResponseEntity.ok(TopSecretResponse.builder().message(satelliteName).build());
    }





}
