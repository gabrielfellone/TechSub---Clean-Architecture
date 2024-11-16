package com.sub.techsub.adpater.gateway;


import com.sub.techsub.adapter.gateway.ClienteGateway;
import com.sub.techsub.core.domain.model.Cliente;
import com.sub.techsub.infrastructure.repository.IClienteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringJUnitConfig
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClienteGatewayTest {

    @Mock
    private IClienteRepository clienteRepository;

    @InjectMocks
    private ClienteGateway clienteGateway;

    @Test
    void testSalvarCliente() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("João");

        when(clienteRepository.save(cliente)).thenReturn(cliente);
        Cliente savedCliente = clienteGateway.salvarCliente(cliente);

        assertNotNull(savedCliente);
        assertEquals(1L, savedCliente.getId());
        assertEquals("João", savedCliente.getNome());

        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void testBuscarClientePorId() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("João");

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        Optional<Cliente> foundCliente = clienteGateway.buscarClientePorId(1L);

        assertTrue(foundCliente.isPresent());
        assertEquals(1L, foundCliente.get().getId());
        assertEquals("João", foundCliente.get().getNome());

        verify(clienteRepository, times(1)).findById(1L);
    }

    @Test
    void testBuscarClientePorIdNaoEncontrado() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<Cliente> foundCliente = clienteGateway.buscarClientePorId(1L);
        assertFalse(foundCliente.isPresent());
        verify(clienteRepository, times(1)).findById(1L);
    }

    @Test
    void testListarClientes() {
        Cliente cliente1 = new Cliente(1L, "João");
        Cliente cliente2 = new Cliente(2L, "Maria");

        when(clienteRepository.findAll()).thenReturn(List.of(cliente1, cliente2));
        List<Cliente> clientes = clienteGateway.listarClientes();

        assertNotNull(clientes);
        assertEquals(2, clientes.size());
        assertEquals("João", clientes.get(0).getNome());
        assertEquals("Maria", clientes.get(1).getNome());

        verify(clienteRepository, times(1)).findAll();
    }

}
