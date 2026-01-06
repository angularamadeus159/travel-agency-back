package com.onvacation.backend.repository;

import com.onvacation.backend.entity.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * REPOSITORIO DE AGENCIAS
 * 
 * Similar al ReservationRepository, pero para manejar agencias.
 */
@Repository
public interface AgencyRepository extends JpaRepository<Agency, UUID> {

    /**
     * Buscar agencia por email
     * 
     * Optional<Agency>: Puede o no encontrar la agencia
     * Es una buena práctica para evitar NullPointerException
     */
    Optional<Agency> findByEmail(String email);

    /**
     * Buscar agencia por nombre (ignorando mayúsculas)
     */
    Optional<Agency> findByNameIgnoreCase(String name);

    /**
     * Obtener solo agencias activas
     */
    List<Agency> findByActiveTrue();

    /**
     * Verificar si existe una agencia con ese email
     */
    boolean existsByEmail(String email);
}
