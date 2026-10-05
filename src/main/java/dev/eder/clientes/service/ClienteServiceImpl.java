package dev.eder.clientes.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.eder.clientes.dto.ClienteRequest;
import dev.eder.clientes.exception.ClienteResponse;
import dev.eder.clientes.exception.EmailDuplicadoException;
import dev.eder.clientes.exception.RecursoNoEncontradoException;
import dev.eder.clientes.mapper.ClienteMapper;
import dev.eder.clientes.model.Cliente;
import dev.eder.clientes.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;

// CÓMO se hace cada operación
@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ClienteResponse> listar() {
        return clienteRepository.findAll()
                .stream()
                .map(clienteMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteResponse obtenerPorId(Long id) {
        return clienteMapper.toResponse(buscarCliente(id));
    }

    @Override
    @Transactional
    public ClienteResponse crear(ClienteRequest request) {
        String email = request.email().trim().toLowerCase();

        // Regla: el email no puede existir
        if (clienteRepository.existsByEmail(email)) {
            throw new EmailDuplicadoException(email);
        }

        Cliente cliente = clienteMapper.toEntity(request);
        return clienteMapper.toResponse(clienteRepository.save(cliente));
    }

    @Override
    @Transactional
    public ClienteResponse actualizar(Long id, ClienteRequest request) {
        Cliente cliente = buscarCliente(id); // si no existe → 404
        String email = request.email().trim().toLowerCase();

        // Regla: el email no puede ser de OTRO cliente
        if (clienteRepository.existsByEmailAndIdNot(email, id)) {
            throw new EmailDuplicadoException(email);
        }

        clienteMapper.actualizarEntidad(request, cliente);
        return clienteMapper.toResponse(clienteRepository.save(cliente));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        Cliente cliente = buscarCliente(id); // si no existe → 404
        clienteRepository.delete(cliente);
    }

    // Busca el cliente o lanza 404. Lo usan obtener, actualizar y eliminar.
    private Cliente buscarCliente(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe el cliente con id " + id));
    }
}
