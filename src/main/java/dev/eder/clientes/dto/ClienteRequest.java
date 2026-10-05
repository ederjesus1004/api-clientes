package dev.eder.clientes.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

// Lo que llega para crear o editar. No tiene id ni fechaRegistro
public record ClienteRequest(

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede tener mas de 100 caracteres")
    String nombre,

    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 100, message = "El apellido no puede tener mas de 100 caracteres")
    String apellido,

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no tiene el formato valido")
    String email,

    // Optional, pero si viene: 9 digitos y empieza con 9 (Celular en Peru)
    //@Pattern(regexp = ...)	Que el texto cumpla un patrón. ^9\d{8}$ = empieza con 9 y le siguen 8 dígitos. Si el campo viene vacío (null), no valida nada, por eso es opcional
    @Pattern(regexp = "^9\\d{8}$", message = "El telefono debe de tener 9 digitos y emepzar con 9")
    String telefono

) {

}
