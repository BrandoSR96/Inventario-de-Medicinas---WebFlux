package com.com.reporte.inventario.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.com.reporte.inventario.model.Medicina;
import com.com.reporte.inventario.model.repository.MedicinaRepository;
import com.com.reporte.inventario.services.MedicinaService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/medicina")
@AllArgsConstructor
public class MediciaController {
	
    private static final Logger logger = LoggerFactory.getLogger(MediciaController.class);
	
	public final MedicinaService medicinaService;
	public final MedicinaRepository medicinaRepository;
	
	@GetMapping
	public Mono<ResponseEntity<Flux<Medicina>>> listarMedicina() {
	    return medicinaService.listarMedicina()
	            .hasElements()
	            .flatMap(hasElements -> {
	                if (hasElements) {
	                    return Mono.just(ResponseEntity.ok(medicinaService.listarMedicina()));
	                } else {
	                    return Mono.just(ResponseEntity.noContent().build());
	                }
	            });
	}

	@PostMapping
	public Mono<ResponseEntity<Medicina>> guardarMedicina(@RequestBody Medicina medicina){
		return medicinaService.guardarMedicina(medicina)
				.map(saveMedicina -> ResponseEntity.status(HttpStatus.CREATED).body(saveMedicina))
				.onErrorResume(e -> Mono.just(ResponseEntity.badRequest().build()));
	}

	
	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Object>> eliminarMedicina(@PathVariable Long id) {
	    return medicinaService.eliminarMedicina(id)
	            .then(Mono.just(ResponseEntity.<Void>noContent().build()))
	            .onErrorResume(e -> Mono.just(ResponseEntity.<Void>notFound().build()));
	}
	
	@DeleteMapping("/eliminarMedicinas")
	public ResponseEntity<Object> eliminarTodasLasMedicinas(){
		return ResponseEntity.ok(medicinaService.eliminarTodasLasMedicinas());
	}
	
    /*@GetMapping("/reporte-csv")
    public Mono<ResponseEntity<?>> descargarReporteCSV() {
        return medicinaService.listarMedicina()
                .collectList()  // Recoge las medicinas en una lista
                .flatMap(medicinas -> {
                    if (medicinas.isEmpty()) {
                        return Mono.just(ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay medicinas disponibles para generar el reporte."));
                    }
                    return medicinaService.generarReporteCSV(Flux.fromIterable(medicinas))  // Genera el archivo CSV de forma reactiva
                            .map(archivoCSV -> {
                                if (archivoCSV != null && archivoCSV.exists()) {
                                    return ResponseEntity.ok()
                                            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte_medicinas.csv")
                                            .body(new FileSystemResource(archivoCSV));  // Devuelve el archivo como recurso
                                } else {
                                    logger.error("El archivo CSV no fue generado correctamente.");
                                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al generar el archivo CSV.");
                                }
                            })
                            .onErrorResume(e -> {
                                logger.error("Error al generar el archivo CSV: {}", e.getMessage(), e);
                                return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al generar el archivo CSV."));
                            });
                });
    }*/
	@GetMapping("/reporte-csv")
    public Mono<ResponseEntity<?>> descargarReporteCSV() {
        return medicinaService.listarMedicina()
                .collectList()
                .flatMap(medicinas -> {
                    if (medicinas.isEmpty()) {
                        return Mono.just(ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay medicinas disponibles para generar el reporte."));
                    }
                    return medicinaService.generarReporteCSV(Flux.fromIterable(medicinas))
                            .map(archivoCSV -> {
                                if (archivoCSV != null && archivoCSV.exists()) {
                                    return ResponseEntity.ok()
                                            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte_medicinas.csv")
                                            .body(new FileSystemResource(archivoCSV));
                                } else {
                                    logger.error("El archivo CSV no fue generado correctamente.");
                                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al generar el archivo CSV.");
                                }
                            })
                            .onErrorResume(e -> {
                                logger.error("Error al generar el archivo CSV: {}", e.getMessage(), e);
                                return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al generar el archivo CSV."));
                            });
                });
    }
	
}
