package com.sub.techsub.adpater.gateway;


import com.sub.techsub.adapter.gateway.ServicoGateway;
import com.sub.techsub.core.domain.model.Servico;
import com.sub.techsub.infrastructure.repository.IServicosRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringJUnitConfig
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ServicoGatewayTest {

    @Mock
    private IServicosRepository servicoRepository;

    @InjectMocks
    private ServicoGateway servicoGateway;

    @Test
    void testSalvarServico() {
        Servico servico = new Servico();
        servico.setId(1L);
        servico.setNome("Serviço Teste");

        when(servicoRepository.save(servico)).thenReturn(servico);

        Servico savedServico = servicoGateway.salvarServico(servico);

        assertNotNull(savedServico);
        assertEquals(1L, savedServico.getId());
        assertEquals("Serviço Teste", savedServico.getNome());

        verify(servicoRepository, times(1)).save(servico);
    }

    @Test
    void testBuscarServicoPorId() {
        Long id = 1L;
        Servico servico = new Servico(id, "Serviço Teste");

        when(servicoRepository.findById(id)).thenReturn(Optional.of(servico));

        Optional<Servico> result = servicoGateway.buscarServicoPorId(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
        assertEquals("Serviço Teste", result.get().getNome());

        verify(servicoRepository, times(1)).findById(id);
    }

    @Test
    void testBuscarServicoPorId_QuandoNaoEncontrado() {
        Long id = 1L;
        when(servicoRepository.findById(id)).thenReturn(Optional.empty());
        Optional<Servico> result = servicoGateway.buscarServicoPorId(id);
        assertFalse(result.isPresent());
        verify(servicoRepository, times(1)).findById(id);
    }

    @Test
    void testListarServicos() {
        Servico servico1 = new Servico(1L, "Serviço 1");
        Servico servico2 = new Servico(2L, "Serviço 2");
        List<Servico> servicos = Arrays.asList(servico1, servico2);

        when(servicoRepository.findAll()).thenReturn(servicos);

        List<Servico> result = servicoGateway.listarServicos();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Serviço 1", result.get(0).getNome());
        assertEquals("Serviço 2", result.get(1).getNome());

        verify(servicoRepository, times(1)).findAll();
    }

    @Test
    void testBuscarServicoPorReference() {
        Long id = 1L;
        Servico servico = new Servico(id, "Serviço Teste");
        when(servicoRepository.getReferenceById(id)).thenReturn(servico);

        Servico result = servicoGateway.buscarServicoPorReference(id);
        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Serviço Teste", result.getNome());
        verify(servicoRepository, times(1)).getReferenceById(id);
    }

}
