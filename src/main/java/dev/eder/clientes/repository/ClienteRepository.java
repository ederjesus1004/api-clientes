package dev.eder.clientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.eder.clientes.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
    //¿Ya existe un cliente con este email?
    // SELECT COUNT(*) > 0 FROM clientes WHERE email = ?
    boolean existsByEmail(String email);

    //¿Existo OTRO cliente (con id distinto) con este email?
    //Se usa al editar, para que un cliente pueda conservar su propio email
    boolean existsByEmailAndIdNot(String email, Long id);

}
