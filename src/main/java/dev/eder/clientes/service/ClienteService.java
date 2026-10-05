package dev.eder.clientes.service;

import java.util.List;

import dev.eder.clientes.dto.ClienteRequest;
import dev.eder.clientes.exception.ClienteResponse;

// QUÉ se puede hacer con los clientes
public interface ClienteService {

    List<ClienteResponse> listar();

    ClienteResponse obtenerPorId(Long id);

    ClienteResponse crear(ClienteRequest request);

    ClienteResponse actualizar(Long id, ClienteRequest request);

    void eliminar(Long id);
}
