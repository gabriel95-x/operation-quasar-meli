package com.mercadolibre.quasaroperation.controller;


import com.mercadolibre.quasaroperation.exception.QuasarNotFoundException;
import com.mercadolibre.quasaroperation.response.ResponseDefinition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = QuasarNotFoundException.class)
    public ResponseDefinition  methodArgumentNotValidException (QuasarNotFoundException ex){

        return ex.getResponseDefinition();
    }

    /**
     * Validacion de Request
     **/
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public  HashMap<String,String>  methodArgumentNotValidException (MethodArgumentNotValidException ex){

        HashMap<String, String> camposMap = new HashMap<>();

        ex.getBindingResult().getFieldErrors().stream().forEach(item->camposMap.put(item.getField(),item.getDefaultMessage()));

        return camposMap;
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = RuntimeException.class)
    public  ResponseDefinition  methodArgumentNotValidException (RuntimeException ex){

        return ResponseDefinition.builder().messageDisplay(ex.getMessage()).build();
    }

    /**
     * excepcion general
     **/
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,String> exception(Exception exception) {
        final String exceptionMessage = exception.getMessage();
        log.error(exceptionMessage);
        return Collections.singletonMap("message", exception.getMessage());

    }




}
