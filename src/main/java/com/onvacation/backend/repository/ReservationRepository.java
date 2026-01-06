package com.onvacation.backend.repository;

import com.onvacation.backend.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * REPOSITORIO DE RESERVASsss
 * 
 * CONCEPTO: REPOSITORY PATTERN
 * Un repositorio es una capa de abstracción entre la lógica de negocio y la base de datos.
 * Piensa en él como un "cajero automático" para tus datos.
 * 
 * ¿QUÉ HACE JpaRepository?
 * Al extender JpaRepository<Reservation, UUID>, automáticamente obtienes métodos como:
 * - save(reservation): Guardar o actualizar
 * - findById(id): Buscar por ID
 * - findAll(): Obtener todas las reservas
 * - deleteById(id): Eliminar por ID
 * - count(): Contar registros
 * ¡Y muchos más sin escribir código!
 * 
 * PARÁMETROS DE JpaRepository:
 * - Reservation: El tipo de entidad que maneja
 * - UUID: El tipo de dato del ID
 */
@Repository // Marca esta interfaz como un componente de Spring
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

    /**
     * MÉTODO QUERY PERSONALIZADO 1: Buscar por agencia
     * 
     * Spring Data JPA lee el nombre del método y genera la consulta SQL automáticamente.
     * 
     * Nomenclatura:
     * - find: SELECT
     * - By: WHERE
     * - AgencyName: Campo "agencyName"
     * 
     * SQL generado automáticamente:
     * SELECT * FROM reservations WHERE agency_name = ?
     * 
     * @param agencyName Nombre de la agencia
     * @return Lista de reservas de esa agencia
     */
    List<Reservation> findByAgencyName(String agencyName);

    /**
     * MÉTODO QUERY PERSONALIZADO 2: Buscar por email de agencia
     * 
     * SQL generado:
     * SELECT * FROM reservations WHERE agency_email = ?
     */
    List<Reservation> findByAgencyEmail(String agencyEmail);

    /**
     * MÉTODO QUERY PERSONALIZADO 3: Buscar por rango de fechas
     * 
     * Between: Busca entre dos valores
     * 
     * SQL generado:
     * SELECT * FROM reservations 
     * WHERE travel_date BETWEEN ? AND ?
     */
    List<Reservation> findByTravelDateBetween(LocalDate startDate, LocalDate endDate);

    /**
     * MÉTODO QUERY PERSONALIZADO 4: Buscar por mes de cuota
     * 
     * IgnoreCase: Ignora mayúsculas/minúsculas
     * 
     * SQL generado:
     * SELECT * FROM reservations 
     * WHERE LOWER(quota_month) = LOWER(?)
     */
    List<Reservation> findByQuotaMonthIgnoreCase(String quotaMonth);

    /**
     * CONSULTA JPQL PERSONALIZADA
     * 
     * @Query: Permite escribir consultas personalizadas en JPQL
     * JPQL: Java Persistence Query Language (similar a SQL pero orientado a objetos)
     * 
     * Esta consulta busca reservas por múltiples criterios opcionales.
     * 
     * EXPLICACIÓN:
     * - :agencyName es un parámetro nombrado
     * - OR :agencyName IS NULL: Si no se proporciona agencia, trae todas
     * - @Param: Vincula el parámetro del método con el de la consulta
     * 
     * @param agencyName Nombre de la agencia (opcional)
     * @param agencyEmail Email de la agencia (opcional)
     * @param startDate Fecha inicio (opcional)
     * @param endDate Fecha fin (opcional)
     * @return Lista de reservas filtradas
     */
    @Query("SELECT r FROM Reservation r WHERE " +
           "(:agencyName IS NULL OR r.agencyName = :agencyName) AND " +
           "(:agencyEmail IS NULL OR r.agencyEmail = :agencyEmail) AND " +
           "(:startDate IS NULL OR r.travelDate >= :startDate) AND " +
           "(:endDate IS NULL OR r.travelDate <= :endDate)")
    List<Reservation> findByFilters(
            @Param("agencyName") String agencyName,
            @Param("agencyEmail") String agencyEmail,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    /**
     * CONSULTA NATIVA SQL
     * 
     * @Query(nativeQuery = true): Permite escribir SQL puro en lugar de JPQL
     * 
     * Útil cuando necesitas funciones específicas de PostgreSQL.
     * 
     * Esta consulta cuenta cuántas reservas tiene cada agencia.
     */
    @Query(value = "SELECT agency_email, COUNT(*) as total " +
                   "FROM reservations " +
                   "GROUP BY agency_email " +
                   "ORDER BY total DESC",
           nativeQuery = true)
    List<Object[]> countReservationsByAgency();
}
