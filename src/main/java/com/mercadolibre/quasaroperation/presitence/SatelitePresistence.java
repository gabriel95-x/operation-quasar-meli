package com.mercadolibre.quasaroperation.presitence;

import com.mercadolibre.quasaroperation.model.table.SateliteTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SatelitePresistence extends JpaRepository<SateliteTable, Integer> {
    SateliteTable findOneByName(String name);
}
