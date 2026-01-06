package com.onvacation.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO (DATA TRANSFER OBJECT) DE RESERVA
 * 
 * ¿QUÉ ES UN DTO?
 * Un DTO es un objeto que se usa para transferir datos entre capas de la aplicación.
 * 
 * ¿POR QUÉ USAR DTOs EN LUGAR DE ENTIDADES?
 * 1. SEGURIDAD: No expones la estructura interna de tu base de datos
 * 2. FLEXIBILIDAD: Puedes enviar solo los datos que necesitas
 * 3. DESACOPLAMIENTO: Cambios en la BD no afectan a la API
 * 
 * EJEMPLO:
 * - La entidad tiene UUID (no amigable para el usuario)
 * - El DTO puede usar String para el ID
 * - La entidad tiene createdAt/updatedAt (no necesarios en respuestas simples)
 * - El DTO solo tiene los datos relevantes
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {

    private String id;
    private String reservationNumber;
    private String clientName;
    private LocalDate travelDate;
    private String observation;
    private LocalDate paymentDate;
    
    // Campos dinámicos ojo
    private String quotaMonth;
    private BigDecimal quotaAmount;
    private BigDecimal quotaBalance;
    
    // Información de agencia
    private String agencyName;
    private String agencyEmail;
}
