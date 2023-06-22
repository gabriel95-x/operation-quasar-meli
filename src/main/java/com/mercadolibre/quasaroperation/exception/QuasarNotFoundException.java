package com.mercadolibre.quasaroperation.exception;

import com.mercadolibre.quasaroperation.response.ResponseDefinition;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Data
@Slf4j
public class QuasarNotFoundException extends RuntimeException {

    ResponseDefinition responseDefinition;
    public QuasarNotFoundException(ResponseDefinition responseDefinition){
       super("excepcion personalizada");
       this.responseDefinition = responseDefinition;

       log.error(responseDefinition.toString());
   }

}
