package dev.eder.clientes.mapper;

import org.springframework.stereotype.Component;
import dev.eder.clientes.dto.ClienteRequest;
import dev.eder.clientes.exception.ClienteResponse;
import dev.eder.clientes.model.Cliente;

@Component
public class ClienteMapper {

    // DTO de entrada -> entidad nueva (al crear)
    public Cliente toEntity(ClienteRequest request) {
        Cliente cliente = new Cliente();
        actualizarEntidad(request, cliente);
        return cliente;
    }

    // Copia los datos DTO a una entidad que ya existe (al editar)
    public void actualizarEntidad(ClienteRequest request, Cliente cliente) {
        cliente.setNombre(request.nombre().trim());
        cliente.setApellido(request.apellido().trim());
        cliente.setEmail(request.email().trim().toLowerCase()); // email siempre en minúsculas
        cliente.setTelefono(request.telefono());
    }

    // Entidad -> DTO de salida
    public ClienteResponse toResponse(Cliente cliente) {
        return new ClienteResponse(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getTelefono(),
                cliente.getFechaRegistro());
    }
}
