package com.mercadolibre.quasaroperation.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class LocationException extends RuntimeException {

   final String message;
   final HttpStatus statusCode;
    public LocationException(String message,HttpStatus statusCode) {
        super("Get location EXCEPTION");
        this.message = message;
        this.statusCode = statusCode;
     }
}