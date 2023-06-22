package com.mercadolibre.quasaroperation.controller;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ApiInfoConfig {

   @Value("${apiinfo.nameContact}")
   private String nameContact;
   @Value("${apiinfo.mailContact}")
   private String mailContact;
   @Value("${apiinfo.description}")
   private String description;
   @Value("${apiinfo.title}")
   private String title;


}