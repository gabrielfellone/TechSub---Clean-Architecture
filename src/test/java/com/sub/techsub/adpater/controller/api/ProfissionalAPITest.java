package com.sub.techsub.adpater.controller.api;

import com.sub.techsub.adapter.controller.ProfissionalController;
import com.sub.techsub.adapter.controller.api.ProfissionalAPI;
import com.sub.techsub.adapter.controller.resources.requests.ProfissionalRequest;
import com.sub.techsub.core.domain.model.Profissional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.CREATED;

@ExtendWith(MockitoExtension.class)
class ProfissionalAPITest {

    @Mock
    private ProfissionalController profissionalController;

    @InjectMocks
    private ProfissionalAPI profissionalAPI;


    @Test
    void cadastrar_DeveRetornarIdCriado_QuandoRequisicaoForValida() {
        ProfissionalRequest profissionalRequest = new ProfissionalRequest();
        Long idEsperado = 1L;
        when(profissionalController.cadastrarProfissional(any(ProfissionalRequest.class))).thenReturn(idEsperado);

        ResponseEntity<Long> resposta = profissionalAPI.cadastrar(profissionalRequest);

        assertNotNull(resposta);
        assertEquals(CREATED, resposta.getStatusCode());
        assertEquals(idEsperado, resposta.getBody());
        verify(profissionalController, times(1)).cadastrarProfissional(any(ProfissionalRequest.class));
    }

    @Test
    void listarTodos_DeveRetornarListaDeProfissionais() {
        List<Profissional> listaEsperada = List.of(new Profissional(), new Profissional());
        when(profissionalController.listarTodos()).thenReturn(listaEsperada);

        List<Profissional> resultado = profissionalAPI.listarTodos();

        assertNotNull(resultado);
        assertEquals(listaEsperada.size(), resultado.size());
        verify(profissionalController, times(1)).listarTodos();
    }

    @Test
    void buscarPerfilProfissional_DeveRetornarPerfil_QuandoIdForValido() {
        Long id = 1L;
        Optional<Profissional> profissionalEsperado = Optional.of(new Profissional());
        when(profissionalController.buscarPerfilProfissional(id)).thenReturn(profissionalEsperado);

        Optional<Profissional> resultado = profissionalAPI.buscarPerfilProfissional(id);

        assertNotNull(resultado);
        assertEquals(profissionalEsperado, resultado);
        verify(profissionalController, times(1)).buscarPerfilProfissional(id);
    }
}
