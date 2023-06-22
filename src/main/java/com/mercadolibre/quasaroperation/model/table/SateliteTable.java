package com.mercadolibre.quasaroperation.model.table;


import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "satelites")
public class SateliteTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "stl_name")
    private String name;
    @Column(name = "stl_distance")
    private BigDecimal distance;
    @Column(name = "stl_message")
    private String message;

}
