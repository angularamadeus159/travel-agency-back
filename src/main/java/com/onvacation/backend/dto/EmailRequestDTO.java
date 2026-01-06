package com.onvacation.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO PARA ENVÍO DE EMAILS
 * 
 * Este objeto contiene toda la información necesaria para enviar un email.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequestDTO {

    /**
     * Email de la agencia destinataria
     */
    private String agencyEmail;

    /**
     * Asunto del correo
     */
    private String subject;

    /**
     * Cuerpo del mensaje (puede ser HTML)
     */
    private String body;

    /**
     * Indicador si se deben incluir las reservas en el email
     */
    private Boolean includeReservations = true;
}
