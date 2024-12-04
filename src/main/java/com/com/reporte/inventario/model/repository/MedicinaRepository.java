package com.com.reporte.inventario.model.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.com.reporte.inventario.model.Medicina;

public interface MedicinaRepository extends ReactiveCrudRepository<Medicina, Long>{

}
