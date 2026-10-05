# API de Clientes · Spring Boot + PostgreSQL

API REST para registrar, consultar, editar y eliminar clientes. La hice para practicar un CRUD completo con la estructura por capas que se usa en proyectos reales: DTOs, mapper, validaciones y manejo global de errores.

## Tecnologías

| Tecnología | Uso |
|------------|-----|
| Java 21 | Lenguaje |
| Spring Boot | Framework principal |
| Spring Data JPA | Acceso a la base de datos |
| Spring Validation | Validación de datos de entrada |
| PostgreSQL | Base de datos |
| Lombok | Menos código repetido |

## Funcionalidades

- Listar todos los clientes
- Consultar un cliente por id
- Registrar un cliente nuevo
- Editar un cliente
- Eliminar un cliente
- Validar los datos de entrada (nombre, apellido, email y teléfono)
- No permitir dos clientes con el mismo email, sin importar mayúsculas o minúsculas

## Estructura del proyecto

```
src/main/java/com/ejemplo/clientes/
├── entity/        # Entidad Cliente (tabla clientes)
├── repository/    # Acceso a la base de datos
├── dto/           # Datos de entrada (Request) y salida (Response)
├── mapper/        # Conversión entre entidad y DTO
├── exception/     # Excepciones propias y manejo global de errores
├── service/       # Lógica de negocio
└── controller/    # Endpoints REST
```

## Requisitos

- Java 21
- PostgreSQL instalado (puerto 5432)

## Cómo ejecutarlo

**1. Crear la base de datos** en pgAdmin o psql:

```sql
CREATE DATABASE clientes_db;
```

**2. Revisar las credenciales** en `src/main/resources/application.properties`. Por defecto usa el usuario `postgres` con contraseña `postgres`. Si tu PostgreSQL tiene otra contraseña, cámbiala ahí.

**3. Clonar y ejecutar**

```bash
git clone https://github.com/ederjesus1004/api-clientes.git
cd api-clientes
./mvnw spring-boot:run
```

En Windows usa `mvnw.cmd spring-boot:run`. La API queda en `http://localhost:8080`. La tabla `clientes` se crea sola al arrancar.

## Endpoints

| Método | Ruta | Descripción | Respuesta |
|--------|------|-------------|-----------|
| GET | `/api/clientes` | Lista todos los clientes | 200 |
| GET | `/api/clientes/{id}` | Obtiene un cliente | 200 / 404 |
| POST | `/api/clientes` | Registra un cliente | 201 / 400 / 409 |
| PUT | `/api/clientes/{id}` | Edita un cliente | 200 / 400 / 404 / 409 |
| DELETE | `/api/clientes/{id}` | Elimina un cliente | 204 / 404 |

## Ejemplos

**Registrar un cliente**

`POST /api/clientes`

```json
{
  "nombre": "Eder",
  "apellido": "Cuaresma",
  "email": "eder@correo.com",
  "telefono": "987654321"
}
```

Respuesta `201`:

```json
{
  "id": 1,
  "nombre": "Eder",
  "apellido": "Cuaresma",
  "email": "eder@correo.com",
  "telefono": "987654321",
  "fechaRegistro": "2026-10-05T10:15:30"
}
```

**Error de validación**

Respuesta `400` cuando faltan datos o tienen mal formato:

```json
{
  "fecha": "2026-10-05T10:16:02",
  "estado": 400,
  "mensaje": "Datos inválidos",
  "errores": {
    "nombre": "El nombre es obligatorio",
    "email": "El email no tiene un formato válido"
  }
}
```

**Email repetido**

Respuesta `409`:

```json
{
  "fecha": "2026-10-05T10:17:45",
  "estado": 409,
  "mensaje": "Ya existe un cliente con el email eder@correo.com",
  "errores": {}
}
```

## Validaciones

| Campo | Regla |
|-------|-------|
| nombre | Obligatorio, máximo 100 caracteres |
| apellido | Obligatorio, máximo 100 caracteres |
| email | Obligatorio, formato válido y único |
| telefono | Opcional. Si se envía, 9 dígitos y empieza con 9 |

## Códigos de respuesta

| Código | Cuándo sale |
|--------|-------------|
| 200 | Consulta o edición correcta |
| 201 | Cliente registrado |
| 204 | Cliente eliminado |
| 400 | Datos inválidos, JSON mal escrito o id que no es número |
| 404 | El cliente no existe |
| 409 | El email ya lo usa otro cliente |

## Lo que aprendí

- Separar el proyecto en capas y para qué sirve cada una
- Usar DTOs para no exponer la entidad directamente
- Centralizar la conversión entre entidad y DTO en un mapper
- Validar datos con anotaciones y devolver mensajes claros
- Manejar errores en un solo lugar con `@RestControllerAdvice`
- Diferenciar la validación del email al crear y al editar

## Autor

**Eder Cuaresma**
Estudiante de Ingeniería de Sistemas e Informática, UTP

- GitHub: [@ederjesus1004](https://github.com/ederjesus1004)
