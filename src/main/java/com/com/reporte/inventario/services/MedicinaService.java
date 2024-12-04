package com.com.reporte.inventario.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.com.reporte.inventario.model.Medicina;
import com.com.reporte.inventario.model.repository.MedicinaRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MedicinaService {
	
	private final MedicinaRepository medicinaRepository;
	
	public Flux<Medicina> listarMedicina(){
		return  medicinaRepository.findAll()
				.switchIfEmpty(Flux.empty());
	}
	
	public Mono<Medicina> guardarMedicina(Medicina medicina){
		return medicinaRepository.save(medicina);
	}
	
	public Mono<Void> eliminarMedicina(Long id){
		return medicinaRepository.deleteById(id);
	}
	
	public Mono<Void> eliminarTodasLasMedicinas(){
		return medicinaRepository.deleteAll();
	}

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /*public Mono<File> generarReporteCSV(Flux<Medicina> medicinas) {
        return medicinas.collectList()  // Recoge todos los elementos del Flux en una lista
                .flatMap(medicinaList -> {
                    try {
                        File archivoCSV = new File("reporte_medicinas.csv");
                        try (FileWriter writer = new FileWriter(archivoCSV)) {
                            writer.append("ID,Nombre,Descripción,Cantidad,Fecha de Expiración,Fecha de Creación\n");
                            for (Medicina medicina : medicinaList) {
                                writer.append(medicina.getId().toString())
                                      .append(",")
                                      .append(medicina.getName())
                                      .append(",")
                                      .append(medicina.getDescription())
                                      .append(",")
                                      .append(medicina.getQuantity().toString())
                                      .append(",")
                                      .append(medicina.getExpirationDate().format(DATE_FORMATTER))
                                      .append(",")
                                      .append(medicina.getCreatedAt().format(DATE_TIME_FORMATTER))
                                      .append("\n");
                            }
                        }
                        return Mono.just(archivoCSV);  // Devuelve el archivo generado
                    } catch (IOException e) {
                        return Mono.error(new RuntimeException("Error al escribir el archivo CSV", e));
                    }
                });
    }*/
    public Mono<File> generarReporteCSV(Flux<Medicina> medicinas) {
        return medicinas.collectList()
                .flatMap(medicinaList -> {
                    try {
                        File archivoCSV = new File("reporte_medicinas.csv");
                        try (FileWriter writer = new FileWriter(archivoCSV)) {
                            writer.append("ID,Nombre,Descripción,Cantidad,Fecha de Expiración,Fecha de Creación\n");
                            for (Medicina medicina : medicinaList) {
                                writer.append(medicina.getId().toString())
                                      .append(",")
                                      .append(medicina.getName())
                                      .append(",")
                                      .append(medicina.getDescription())
                                      .append(",")
                                      .append(medicina.getQuantity().toString())
                                      .append(",")
                                      .append(medicina.getExpirationDate().format(DATE_FORMATTER))
                                      .append(",")
                                      .append(medicina.getCreatedAt().format(DATE_TIME_FORMATTER))
                                      .append("\n");
                            }
                        }
                        return Mono.just(archivoCSV);
                    } catch (IOException e) {
                        return Mono.error(new RuntimeException("Error al escribir el archivo CSV", e));
                    }
                });
    }
}
