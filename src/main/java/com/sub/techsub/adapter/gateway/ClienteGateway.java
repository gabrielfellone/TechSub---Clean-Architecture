package com.sub.techsub.adapter.gateway;

import com.sub.techsub.core.domain.model.Cliente;
import com.sub.techsub.infrastructure.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteGateway {

    private final IClienteRepository clienteRepository;

    @Autowired
    public ClienteGateway(IClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente salvarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> buscarClientePorId(Long id) {
        return clienteRepository.findById(id);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }
}