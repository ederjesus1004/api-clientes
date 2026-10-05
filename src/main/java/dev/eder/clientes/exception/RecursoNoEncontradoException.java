package dev.eder.clientes.exception;

// Cuando el cliente no existe → 404
public class RecursoNoEncontradoException extends RuntimeException {
    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
