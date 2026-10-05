package dev.eder.clientes.exception;

import java.time.LocalDateTime;
import java.util.Map;

// Formato único de todos los errores
public record ErrorResponse(
    LocalDateTime fecha,
    int estado,
    String mensaje,
    Map<String, String> errores
) {
}
