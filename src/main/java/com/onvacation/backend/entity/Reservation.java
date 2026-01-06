package com.onvacation.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * ENTIDAD RESERVATION - REPRESENTA UNA RESERVA EN LA BASE DE DATOS
 * 
 * CONCEPTOS IMPORTANTES:
 * 
 * 1. ENTIDAD (Entity): Es una clase que se mapea a una tabla en la base de datos.
 *    Cada instancia de esta clase = una fila en la tabla.
 * 
 * 2. JPA (Java Persistence API): Tecnología que permite trabajar con bases de datos
 *    usando objetos Java en lugar de escribir SQL manualmente.
 * 
 * 3. ANOTACIONES:
 *    - @Entity: Marca esta clase como una entidad de base de datos
 *    - @Table: Especifica el nombre de la tabla en la BD
 *    - @Data: (Lombok) Genera getters, setters, toString(), equals(), hashCode()
 *    - @NoArgsConstructor: (Lombok) Genera constructor sin parámetros
 *    - @AllArgsConstructor: (Lombok) Genera constructor con todos los parámetros
 */
@Entity
@Table(name = "reservations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    /**
     * ID PRIMARIO
     * 
     * @Id: Marca este campo como la clave primaria
     * @GeneratedValue: El valor se genera automáticamente
     * @Column: Configura la columna en la BD
     * 
     * UUID: Identificador Único Universal (mejor que un número secuencial)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    /**
     * NÚMERO DE RESERVA
     * Ejemplo: "RES-2024-001"
     */
    @Column(name = "reservation_number", nullable = false, length = 100)
    private String reservationNumber;

    /**
     * NOMBRE DEL CLIENTE
     * Ejemplo: "Juan Pérez"
     */
    @Column(name = "client_name", nullable = false)
    private String clientName;

    /**
     * FECHA DEL VIAJE
     * LocalDate: Representa solo la fecha (sin hora)
     */
    @Column(name = "travel_date")
    private LocalDate travelDate;

    /**
     * OBSERVACIONES
     * @Column(columnDefinition = "TEXT"): Permite textos largos
     */
    @Column(name = "observation", columnDefinition = "TEXT")
    private String observation;

    /**
     * FECHA DE PAGO DEL MES
     */
    @Column(name = "payment_date")
    private LocalDate paymentDate;

    // ==========================================
    // CAMPOS DINÁMICOS DE CUOTA
    // ==========================================

    /**
     * MES DE LA CUOTA
     * Ejemplo: "DICIEMBRE", "ENERO"
     * 
     * Este campo guarda el mes que viene en el nombre de las columnas
     * "CUOTA DE DICIEMBRE" -> se guarda "DICIEMBRE"
     */
    @Column(name = "quota_month", length = 20)
    private String quotaMonth;

    /**
     * MONTO DE LA CUOTA
     * BigDecimal: Tipo de dato para manejar dinero (más preciso que double)
     * precision=10: Hasta 10 dígitos en total
     * scale=2: 2 decimales (ejemplo: 1234.56)
     */
    @Column(name = "quota_amount", precision = 10, scale = 2)
    private BigDecimal quotaAmount;

    /**
     * SALDO DE LA CUOTA
     */
    @Column(name = "quota_balance", precision = 10, scale = 2)
    private BigDecimal quotaBalance;

    // ==========================================
    // INFORMACIÓN DE LA AGENCIA
    // ==========================================

    /**
     * NOMBRE DE LA AGENCIA
     * Ejemplo: "Viajes Paraíso"
     */
    @Column(name = "agency_name")
    private String agencyName;

    /**
     * EMAIL DE LA AGENCIA
     * Ejemplo: "contacto@viajesparaiso.com"
     */
    @Column(name = "agency_email")
    private String agencyEmail;

    // ==========================================
    // CAMPOS DE AUDITORÍA
    // ==========================================

    /**
     * FECHA DE CREACIÓN
     * LocalDateTime: Fecha + Hora
     * updatable = false: No se puede modificar después de creada
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * FECHA DE ÚLTIMA ACTUALIZACIÓN
     */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * MÉTODO QUE SE EJECUTA ANTES DE GUARDAR EN LA BD
     * 
     * @PrePersist: Se ejecuta automáticamente antes de INSERT
     * 
     * Este método establece la fecha de creación automáticamente
     * cuando se guarda una nueva reserva.
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * MÉTODO QUE SE EJECUTA ANTES DE ACTUALIZAR EN LA BD
     * 
     * @PreUpdate: Se ejecuta automáticamente antes de UPDATE
     * 
     * Este método actualiza la fecha de modificación automáticamente
     * cuando se modifica una reserva existente.
     */
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
