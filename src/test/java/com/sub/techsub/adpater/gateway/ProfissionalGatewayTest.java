package com.sub.techsub.adpater.gateway;


import com.sub.techsub.adapter.gateway.ProfissionalGateway;
import com.sub.techsub.core.domain.model.Profissional;
import com.sub.techsub.infrastructure.repository.IProfissionalRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringJUnitConfig
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProfissionalGatewayTest {


    @Mock
    private IProfissionalRepository profissionalRepository;

    @InjectMocks
    private ProfissionalGateway profissionalGateway;

    @Test
    void testSalvarProfissional() {
        Profissional profissional = new Profissional();
        profissional.setId(1L);
        profissional.setNome("João Silva");
        profissional.setHorariosDisponiveis("08:00 - 18:00");
        profissional.setTarifas(100.0);

        when(profissionalRepository.save(profissional)).thenReturn(profissional);

        Profissional savedProfissional = profissionalGateway.salvarProfissional(profissional);

        assertNotNull(savedProfissional);
        assertEquals(1L, savedProfissional.getId());
        assertEquals("João Silva", savedProfissional.getNome());
        assertEquals("08:00 - 18:00", savedProfissional.getHorariosDisponiveis());
        assertEquals(100.0, savedProfissional.getTarifas());

        verify(profissionalRepository, times(1)).save(profissional);
    }

    @Test
    void testBuscarProfissionalPorId() {
        Long id = 1L;
        Profissional profissional = new Profissional(id, "João Silva", "08:00 - 18:00", 100.0, null, null);

        when(profissionalRepository.findById(id)).thenReturn(Optional.of(profissional));
        Optional<Profissional> result = profissionalGateway.buscarProfissionalPorId(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
        assertEquals("João Silva", result.get().getNome());

        verify(profissionalRepository, times(1)).findById(id);
    }

    @Test
    void testBuscarProfissionalPorId_QuandoNaoEncontrado() {
        Long id = 1L;

        when(profissionalRepository.findById(id)).thenReturn(Optional.empty());
        Optional<Profissional> result = profissionalGateway.buscarProfissionalPorId(id);
        assertFalse(result.isPresent());

        verify(profissionalRepository, times(1)).findById(id);
    }

    @Test
    void testBuscarProfissionalPorReference() {
        Long id = 1L;
        Profissional profissional = new Profissional(id, "João Silva", "08:00 - 18:00", 100.0, null, null);

        when(profissionalRepository.getReferenceById(id)).thenReturn(profissional);

        Profissional result = profissionalGateway.buscarProfissionalPorReference(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("João Silva", result.getNome());

        verify(profissionalRepository, times(1)).getReferenceById(id);
    }
}
