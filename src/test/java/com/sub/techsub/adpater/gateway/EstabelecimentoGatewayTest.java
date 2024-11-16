package com.sub.techsub.adpater.gateway;


import com.sub.techsub.adapter.gateway.EstabelecimentoGateway;
import com.sub.techsub.core.domain.model.Estabelecimento;
import com.sub.techsub.infrastructure.repository.IEstabelecimentoRepository;
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
public class EstabelecimentoGatewayTest {


    @Mock
    private IEstabelecimentoRepository estabelecimentoRepository;

    @InjectMocks
    private EstabelecimentoGateway estabelecimentoGateway;

    @Test
    void testSalvarEstabelecimento() {
        Estabelecimento estabelecimento = new Estabelecimento();
        estabelecimento.setId(1L);
        estabelecimento.setNome("Estabelecimento 1");
        estabelecimento.setEndereco("Rua 1");

        when(estabelecimentoRepository.save(estabelecimento)).thenReturn(estabelecimento);

        Estabelecimento savedEstabelecimento = estabelecimentoGateway.salvarEstabelecimento(estabelecimento);

        assertNotNull(savedEstabelecimento);
        assertEquals(1L, savedEstabelecimento.getId());
        assertEquals("Estabelecimento 1", savedEstabelecimento.getNome());
        assertEquals("Rua 1", savedEstabelecimento.getEndereco());

        verify(estabelecimentoRepository, times(1)).save(estabelecimento);
    }

    @Test
    void testBuscarEstabelecimentoPorId() {
        Estabelecimento estabelecimento = new Estabelecimento();
        estabelecimento.setId(1L);
        estabelecimento.setNome("Estabelecimento 1");
        estabelecimento.setEndereco("Rua 1");

        when(estabelecimentoRepository.findById(1L)).thenReturn(Optional.of(estabelecimento));

        Optional<Estabelecimento> foundEstabelecimento = estabelecimentoGateway.buscarEstabelecimentoPorId(1L);

        assertTrue(foundEstabelecimento.isPresent());
        assertEquals(1L, foundEstabelecimento.get().getId());
        assertEquals("Estabelecimento 1", foundEstabelecimento.get().getNome());
        assertEquals("Rua 1", foundEstabelecimento.get().getEndereco());
        verify(estabelecimentoRepository, times(1)).findById(1L);
    }

    @Test
    void testBuscarEstabelecimentoPorIdNaoEncontrado() {
        when(estabelecimentoRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<Estabelecimento> foundEstabelecimento = estabelecimentoGateway.buscarEstabelecimentoPorId(1L);
        assertFalse(foundEstabelecimento.isPresent());
        verify(estabelecimentoRepository, times(1)).findById(1L);
    }

    @Test
    void testListarEstabelecimentos() {
        Estabelecimento estabelecimento1 = new Estabelecimento();
        estabelecimento1.setId(1L);
        estabelecimento1.setNome("Estabelecimento 1");
        estabelecimento1.setEndereco("Rua 1");

        Estabelecimento estabelecimento2 = new Estabelecimento();
        estabelecimento2.setId(2L);
        estabelecimento2.setNome("Estabelecimento 2");
        estabelecimento2.setEndereco("Rua 2");

        when(estabelecimentoRepository.findAll()).thenReturn(List.of(estabelecimento1, estabelecimento2));

        List<Estabelecimento> estabelecimentos = estabelecimentoGateway.listarEstabelecimentos();

        assertNotNull(estabelecimentos);
        assertEquals(2, estabelecimentos.size());
        assertEquals("Estabelecimento 1", estabelecimentos.get(0).getNome());
        assertEquals("Estabelecimento 2", estabelecimentos.get(1).getNome());

        verify(estabelecimentoRepository, times(1)).findAll();
    }

}
