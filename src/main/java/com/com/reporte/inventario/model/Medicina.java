package com.com.reporte.inventario.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Table(name = "medicines")
public class Medicina {
	
	@Id
	private Long id;
	private String name;
	private String description;
	private Integer quantity;   
	private LocalDate expirationDate;
	private LocalDateTime createdAt;
	
	

}
