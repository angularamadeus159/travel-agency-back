# 📚 GUÍA COMPLETA: CREACIÓN DEL BACKEND SPRING BOOT

## 🎯 Objetivo del Proyecto

Crear un backend en Java con Spring Boot para gestionar reservas de viajes, procesar archivos Excel y enviar correos electrónicos a agencias.

---

## 📋 Tabla de Contenidos

1. [Estructura del Proyecto](#estructura-del-proyecto)
2. [Tecnologías Utilizadas](#tecnologías-utilizadas)
3. [Configuración Inicial](#configuración-inicial)
4. [Conceptos Importantes](#conceptos-importantes)
5. [Componentes del Proyecto](#componentes-del-proyecto)
6. [Cómo Ejecutar el Proyecto](#cómo-ejecutar-el-proyecto)
7. [Endpoints de la API](#endpoints-de-la-api)
8. [Próximos Pasos](#próximos-pasos)

---

## 📁 Estructura del Proyecto

```
on-vacation-backend/
├── pom.xml                              # Configuración de Maven y dependencias
├── src/
│   ├── main/
│   │   ├── java/com/onvacation/backend/
│   │   │   ├── OnVacationBackendApplication.java    # Clase principal
│   │   │   ├── config/                               # Configuraciones
│   │   │   ├── controller/                           # Controladores REST
│   │   │   ├── dto/                                  # Data Transfer Objects
│   │   │   │   ├── ReservationDTO.java
│   │   │   │   ├── EmailRequestDTO.java
│   │   │   │   └── ResponseDTO.java
│   │   │   ├── entity/                               # Entidades JPA
│   │   │   │   ├── Reservation.java
│   │   │   │   └── Agency.java
│   │   │   ├── repository/                           # Repositorios
│   │   │   │   ├── ReservationRepository.java
│   │   │   │   └── AgencyRepository.java
│   │   │   ├── service/                              # Servicios de negocio
│   │   │   ├── exception/                            # Manejo de excepciones
│   │   │   └── util/                                 # Utilidades
│   │   └── resources/
│   │       ├── application.properties                # Configuración
│   │       └── static/                               # Archivos estáticos
│   └── test/                                         # Pruebas unitarias
└── README.md
```

---

## 🛠️ Tecnologías Utilizadas

### Backend
- **Java 25** (compatible con Java 21)
- **Spring Boot 3.3.0** - Framework principal
- **Spring Data JPA** - Acceso a datos
- **PostgreSQL** - Base de datos (Supabase)
- **Apache POI 5.2.5** - Procesamiento de Excel
- **Lombok** - Reducción de código boilerplate
- **Spring Mail** - Envío de correos

### Base de Datos
- **Supabase (PostgreSQL)** - Base de datos en la nube

---

## ⚙️ Configuración Inicial

### 1. Configurar Base de Datos (Supabase)

Edita el archivo `src/main/resources/application.properties`:

```properties
# Reemplaza estos valores con tu información de Supabase
spring.datasource.url=jdbc:postgresql://db.nfyufytfpqhivuievnqc.supabase.co:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=TU_PASSWORD_AQUI
```

**¿Dónde encontrar estos datos en Supabase?**
1. Ve a tu proyecto en Supabase Dashboard
2. Settings → Database
3. Copia:
   - Host (será algo como `db.xxx.supabase.co`)
   - Database password (la que definiste al crear el proyecto)

### 2. Instalar Dependencias

El proyecto usa Maven para gestionar dependencias. Todas están en `pom.xml`.

**Comando para descargar dependencias:**
```bash
mvn clean install
```

---

## 📖 Conceptos Importantes

### 1. ¿Qué es Spring Boot?

Spring Boot es un framework que simplifica la creación de aplicaciones Java empresariales.

**Ventajas:**
- ✅ Configuración automática
- ✅ Servidor web embebido (Tomcat)
- ✅ Fácil manejo de dependencias
- ✅ Producción lista rápidamente

### 2. Arquitectura en Capas

El proyecto sigue el patrón de arquitectura en capas:

```
Frontend (Angular)
       ↓
Controller (API REST) ← Recibe peticiones HTTP
       ↓
Service (Lógica de negocio) ← Procesa datos, aplica reglas
       ↓
Repository (Acceso a datos) ← Comunica con la base de datos
       ↓
Database (PostgreSQL)
```

**Ventajas:**
- Separación de responsabilidades
- Código más mantenible
- Fácil de testear
- Reutilización de código

### 3. JPA y Hibernate

**JPA (Java Persistence API):**
- Estándar para mapear objetos Java a tablas de base de datos
- Evita escribir SQL manualmente

**Hibernate:**
- Implementación de JPA
- Genera SQL automáticamente

**Ejemplo:**
```java
// En lugar de escribir SQL:
// SELECT * FROM reservations WHERE agency_email = 'test@example.com'

// Solo escribes:
List<Reservation> reservations = repository.findByAgencyEmail("test@example.com");
```

### 4. Inyección de Dependencias

Spring gestiona la creación de objetos (beans) por ti.

**Sin Spring:**
```java
ReservationRepository repo = new ReservationRepository();
ReservationService service = new ReservationService(repo);
```

**Con Spring (más simple):**
```java
@Autowired
private ReservationService service; // Spring lo crea automáticamente
```

---

## 🧩 Componentes del Proyecto

### 1. Entidades (Entity)

**Ubicación:** `src/main/java/com/onvacation/backend/entity/`

**¿Qué son?**
Clases Java que representan tablas en la base de datos.

**Entidades creadas:**
- `Reservation.java` → Tabla `reservations`
- `Agency.java` → Tabla `agencies`

**Ejemplo:**
```java
@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    private UUID id;
    private String clientName;
    // ... más campos
}
```

### 2. Repositorios (Repository)

**Ubicación:** `src/main/java/com/onvacation/backend/repository/`

**¿Qué son?**
Interfaces que manejan el acceso a la base de datos.

**Repositorios creados:**
- `ReservationRepository.java`
- `AgencyRepository.java`

**Métodos automáticos:**
- `save()` - Guardar/actualizar
- `findById()` - Buscar por ID
- `findAll()` - Obtener todos
- `deleteById()` - Eliminar

**Métodos personalizados:**
```java
List<Reservation> findByAgencyEmail(String email);
List<Reservation> findByTravelDateBetween(LocalDate start, LocalDate end);
```

### 3. DTOs (Data Transfer Objects)

**Ubicación:** `src/main/java/com/onvacation/backend/dto/`

**¿Qué son?**
Objetos para transferir datos entre el frontend y backend.

**DTOs creados:**
- `ReservationDTO.java` - Datos de reserva
- `EmailRequestDTO.java` - Datos para enviar email
- `ResponseDTO.java` - Respuesta genérica

**Ventaja:**
No expones la estructura interna de tu base de datos.

### 4. Servicios (Service)

**Ubicación:** `src/main/java/com/onvacation/backend/service/`

**¿Qué hacen?**
Contienen la lógica de negocio de la aplicación.

**Servicios a crear:**
- `ReservationService` - Operaciones CRUD de reservas
- `ExcelService` - Procesar archivos Excel
- `EmailService` - Enviar correos electrónicos

### 5. Controladores (Controller)

**Ubicación:** `src/main/java/com/onvacation/backend/controller/`

**¿Qué hacen?**
Exponen endpoints REST para que el frontend pueda consumir.

**Controladores a crear:**
- `ReservationController` - API de reservas

---

## 🚀 Cómo Ejecutar el Proyecto

### Opción 1: Desde la línea de comandos

```bash
# Navega a la carpeta del proyecto
cd C:\Users\Johana\on-vacation-backend

# Ejecuta con Maven
mvn spring-boot:run
```

### Opción 2: Desde Visual Studio Code

1. Abre VS Code
2. File → Open Folder → Selecciona `on-vacation-backend`
3. Instala la extensión "Extension Pack for Java"
4. Presiona F5 o haz clic en "Run" en la clase principal

### Verificar que está corriendo

Abre el navegador y ve a: `http://localhost:8080`

Deberías ver un mensaje o página de Spring Boot.

---

## 📡 Endpoints de la API

### Reservas

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/reservations` | Obtener todas las reservas |
| GET | `/api/reservations/{id}` | Obtener una reserva por ID |
| GET | `/api/reservations/filter` | Filtrar reservas |
| POST | `/api/reservations/upload` | Subir archivo Excel |
| DELETE | `/api/reservations/{id}` | Eliminar una reserva |

### Emails

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/reservations/send-email` | Enviar email a agencia |

### Agencias

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/agencies` | Obtener todas las agencias |
| GET | `/api/agencies/{id}` | Obtener una agencia |
| POST | `/api/agencies` | Crear agencia |

---

## 📝 Próximos Pasos

1. ✅ Estructura del proyecto creada
2. ✅ Entidades y repositorios configurados
3. ✅ DTOs definidos
4. ⏳ Crear servicios de negocio
5. ⏳ Crear controladores REST
6. ⏳ Implementar servicio de Excel
7. ⏳ Implementar servicio de emails
8. ⏳ Configurar CORS para Angular
9. ⏳ Pruebas con Postman
10. ⏳ Integración con el frontend

---

## 🔧 Comandos Útiles

```bash
# Compilar el proyecto
mvn clean compile

# Ejecutar tests
mvn test

# Crear JAR ejecutable
mvn clean package

# Ejecutar el JAR
java -jar target/reservation-backend-1.0.0.jar
```

---

## 📚 Recursos para Aprender

- **Spring Boot Official Docs:** https://spring.io/projects/spring-boot
- **Spring Data JPA:** https://spring.io/projects/spring-data-jpa
- **Apache POI:** https://poi.apache.org/
- **Lombok:** https://projectlombok.org/

---

## ❓ Preguntas Frecuentes

### ¿Por qué usar Lombok?

Lombok reduce código repetitivo. En lugar de escribir:

```java
public class Reservation {
    private String name;
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    // ... 20 líneas más
}
```

Solo escribes:
```java
@Data
public class Reservation {
    private String name;
}
```

### ¿Qué es @Autowired?

Es inyección de dependencias. Spring crea los objetos por ti.

### ¿Dónde veo los logs?

En la consola donde ejecutaste `mvn spring-boot:run`.

---

## 🐛 Solución de Problemas Comunes

### Error: Cannot resolve symbol 'lombok'

**Solución:**
1. Instala el plugin de Lombok en VS Code
2. Ejecuta `mvn clean install`

### Error: Connection refused to database

**Solución:**
1. Verifica las credenciales en `application.properties`
2. Asegúrate de que Supabase esté accesible

### Error: Port 8080 already in use

**Solución:**
Cambia el puerto en `application.properties`:
```properties
server.port=8081
```

---

*Creado por Johana - Proyecto On Vacation Backend*
