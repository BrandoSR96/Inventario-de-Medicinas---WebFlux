package com.com.reporte.inventario.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private int status;
    
    public ErrorResponse(String message) {
        this.message = message;
    }
}