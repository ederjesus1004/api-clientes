package dev.eder.clientes.exception;

// Cuando el email ya lo usa otro cliente → 409
public class EmailDuplicadoException extends RuntimeException{

    public EmailDuplicadoException(String email){
        super("Ya existe un cliente con el email" + email);
    }

}
