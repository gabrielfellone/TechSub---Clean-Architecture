package com.sub.techsub.adpater.controller;


import com.sub.techsub.adapter.controller.ProfissionalController;
import com.sub.techsub.adapter.controller.resources.requests.ProfissionalRequest;
import com.sub.techsub.adapter.gateway.ProfissionalGateway;
import com.sub.techsub.adapter.gateway.ServicoGateway;
import com.sub.techsub.core.domain.model.Profissional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringJUnitConfig
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProfissionalControllerTest {

    @Mock
    private ProfissionalGateway profissionalGateway;

    @Mock
    private ServicoGateway servicoGateway;

    @InjectMocks
    private ProfissionalController profissionalController;


    @Test
    void buscarPerfilProfissional_DeveRetornarProfissional_QuandoIdForValido() {
        Long id = 1L;
        Profissional profissionalEsperado = new Profissional();
        when(profissionalGateway.buscarProfissionalPorId(id)).thenReturn(Optional.of(profissionalEsperado));

        Optional<Profissional> resultado = profissionalController.buscarPerfilProfissional(id);

        assertNotNull(resultado);
        assertEquals(profissionalEsperado, resultado.orElse(null));
        verify(profissionalGateway, times(1)).buscarProfissionalPorId(id);
    }

    @Test
    void cadastrarProfissional_DeveRetornarId_QuandoProfissionalForValido() {
        ProfissionalRequest request = new ProfissionalRequest();
        Profissional profissionalSalvo = new Profissional();
        profissionalSalvo.setId(1L);
        when(profissionalGateway.salvarProfissional(any(Profissional.class))).thenReturn(profissionalSalvo);
        when(servicoGateway.buscarServicoPorReference(any())).thenReturn(null);  // Ajuste conforme o seu modelo

        Long idResultado = profissionalController.cadastrarProfissional(request);

        assertNotNull(idResultado);
        assertEquals(1L, idResultado);
        verify(profissionalGateway, times(1)).salvarProfissional(any(Profissional.class));
        verify(servicoGateway, times(1)).buscarServicoPorReference(any());
    }

    @Test
    void listarTodos_DeveRetornarListaDeProfissionais() {
        List<Profissional> listaEsperada = List.of(new Profissional(), new Profissional());
        when(profissionalGateway.listarProfissionais()).thenReturn(listaEsperada);

        List<Profissional> resultado = profissionalController.listarTodos();

        assertNotNull(resultado);
        assertEquals(listaEsperada.size(), resultado.size());
        verify(profissionalGateway, times(1)).listarProfissionais();
    }

    @Test
    void atualizaProfissional_DeveChamarSalvarProfissional() {
        Profissional profissional = new Profissional();

        profissionalController.atualizaProfissional(profissional);

        verify(profissionalGateway, times(1)).salvarProfissional(profissional);
    }
}

