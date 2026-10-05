package dev.eder.clientes.exception;

import java.time.LocalDateTime;

// Lo que devolvemos al consultar un cliente
public record ClienteResponse(
    Long id,
    String nombre,
    String apellido,
    String telefono,
    LocalDateTime fechaRegistro
) {

}
