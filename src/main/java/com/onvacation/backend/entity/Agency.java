package com.onvacation.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * ENTIDAD AGENCY - REPRESENTA UNA AGENCIA DE VIAJES
 * 
 * Esta tabla almacena información de las agencias con las que trabajas.
 * Es opcional, pero útil para tener un catálogo de agencias.
 */
@Entity
@Table(name = "agencies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agency {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    /**
     * NOMBRE DE LA AGENCIA
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * EMAIL DE CONTACTO
     */
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    /**
     * PERSONA DE CONTACTO
     * Ejemplo: "María González"
     */
    @Column(name = "contact_person")
    private String contactPerson;

    /**
     * TELÉFONO (OPCIONAL)
     */
    @Column(name = "phone")
    private String phone;

    /**
     * ACTIVA O INACTIVA
     * true = activa, false = inactiva
     */
    @Column(name = "active")
    private Boolean active = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
