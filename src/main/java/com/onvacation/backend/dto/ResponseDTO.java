package com.onvacation.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO GENÉRICO DE RESPUESTA
 * 
 * Este DTO se usa para enviar respuestas estandarizadas al frontend.
 * 
 * EJEMPLO DE USO:
 * - Éxito: { "success": true, "message": "10 reservas cargadas", "data": null }
 * - Error: { "success": false, "message": "Error al procesar", "data": null }
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {

    /**
     * Indica si la operación fue exitosa
     */
    private Boolean success;

    /**
     * Mensaje descriptivo
     */
    private String message;

    /**
     * Datos adicionales (opcional)
     */
    private Object data;

    /**
     * Constructor de conveniencia para respuestas sin datos
     */
    public ResponseDTO(Boolean success, String message) {
        this.success = success;
        this.message = message;
        this.data = null;
    }
}
