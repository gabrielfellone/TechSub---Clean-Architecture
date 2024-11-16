package com.sub.techsub.adpater.controller;


import com.sub.techsub.adapter.controller.EstabelecimentoController;
import com.sub.techsub.adapter.controller.resources.requests.EstabelecimentoRequest;
import com.sub.techsub.adapter.controller.resources.responses.EstabelecimentoResource;
import com.sub.techsub.adapter.gateway.*;
import com.sub.techsub.core.domain.model.*;
import com.sub.techsub.core.usecase.EstabelecimentoUseCases;
import com.sub.techsub.core.usecase.exception.ProfissionalException;
import com.sub.techsub.core.usecase.exception.ServicosException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringJUnitConfig
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EstabelecimentoControllerTest {

    @InjectMocks
    private EstabelecimentoController estabelecimentoController;

    @Mock
    private EstabelecimentoUseCases estabelecimentoUseCases;

    @Mock
    private EstabelecimentoGateway estabelecimentoGateway;

    @Mock
    private ProfissionalGateway profissionalGateway;

    @Mock
    private ServicoGateway servicoGateway;

    @Mock
    private EstabelecimentoServicoGateway estabelecimentoServicoGateway;

    @Mock
    private EstabelecimentoProfissionalGateway estabelecimentoProfissionalGateway;

    private EstabelecimentoRequest estabelecimentoRequest;
    private Estabelecimento estabelecimento;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        estabelecimentoRequest = new EstabelecimentoRequest();
        estabelecimentoRequest.setNome("Estabelecimento Teste");
        estabelecimentoRequest.setEndereco("Rua Teste");
        estabelecimentoRequest.setHorarioFuncionamento("9h - 18h");
        estabelecimentoRequest.setServicos(Arrays.asList(1L, 2L));
        estabelecimentoRequest.setProfissionais(Arrays.asList(3L, 4L));
        estabelecimentoRequest.setFotos(Arrays.asList("Foto1.png", "Foto2.png"));

        estabelecimento = new Estabelecimento(estabelecimentoRequest);
        estabelecimento.setId(1L);
    }

    @Test
    void testCadastrarEstabelecimento() {
        when(estabelecimentoGateway.salvarEstabelecimento(any(Estabelecimento.class)))
                .thenReturn(estabelecimento);

        when(servicoGateway.buscarServicoPorReference(anyLong())).thenReturn(new Servico());
        when(profissionalGateway.buscarProfissionalPorReference(anyLong())).thenReturn(new Profissional());

        Long id = estabelecimentoController.cadastrarEstabelecimento(estabelecimentoRequest);

        verify(estabelecimentoGateway).salvarEstabelecimento(any(Estabelecimento.class));
        verify(estabelecimentoServicoGateway, times(2)).salvarRelacionamentoEstabelecimentoServico(any(EstabelecimentoServico.class));
        verify(estabelecimentoProfissionalGateway, times(2)).salvarRelacionamentoEstabelecimentoProfissional(any(EstabelecimentoProfissional.class));

        assertEquals(1L, id);
    }

    @Test
    void testListarTodosEstabelecimentos() {
        Estabelecimento estabelecimentoMock = mock(Estabelecimento.class);
        when(estabelecimentoGateway.listarEstabelecimentos()).thenReturn(List.of(estabelecimentoMock));

        when(estabelecimentoMock.getEstabelecimentoServicos()).thenReturn(Arrays.asList(new EstabelecimentoServico()));
        when(estabelecimentoMock.getFotos()).thenReturn(new String[]{"Foto1.png"});

        List<EstabelecimentoResource> recursos = estabelecimentoController.listarTodos();

        assertNotNull(recursos);
        assertFalse(recursos.isEmpty());
        verify(estabelecimentoGateway).listarEstabelecimentos();
    }

    @Test
    void testSalvarServicos_Sucesso() {
        Estabelecimento estabelecimentoMock = mock(Estabelecimento.class);

        when(servicoGateway.buscarServicoPorReference(1L)).thenReturn(new Servico());

        estabelecimentoController.salvarServicos(Arrays.asList(1L), estabelecimentoMock);

        verify(estabelecimentoServicoGateway).salvarRelacionamentoEstabelecimentoServico(any(EstabelecimentoServico.class));
    }

    @Test
    void testSalvarServicos_ThrowServicosException() {
        Estabelecimento estabelecimentoMock = mock(Estabelecimento.class);
        when(servicoGateway.buscarServicoPorReference(1L)).thenThrow(new RuntimeException());

        assertThrows(ServicosException.class, () -> estabelecimentoController.salvarServicos(Arrays.asList(1L), estabelecimentoMock));
    }

    @Test
    void testSalvarProfissionais_Sucesso() {
        Estabelecimento estabelecimentoMock = mock(Estabelecimento.class);
        when(profissionalGateway.buscarProfissionalPorReference(3L)).thenReturn(new Profissional());

        estabelecimentoController.salvarProfissionais(Arrays.asList(3L), estabelecimentoMock);

        verify(estabelecimentoProfissionalGateway).salvarRelacionamentoEstabelecimentoProfissional(any(EstabelecimentoProfissional.class));
    }

    @Test
    void testSalvarProfissionais_ThrowProfissionalException() {
        Estabelecimento estabelecimentoMock = mock(Estabelecimento.class);
        when(profissionalGateway.buscarProfissionalPorReference(3L)).thenThrow(new RuntimeException());

        assertThrows(ProfissionalException.class, () -> estabelecimentoController.salvarProfissionais(Arrays.asList(3L), estabelecimentoMock));
    }

    @Test
    void testFiltroEstabelecimento() {
        EstabelecimentoResource estabelecimentoResource = mock(EstabelecimentoResource.class);
        when(estabelecimentoUseCases.filtrarEstabelecimento(anyList(), anyString(), anyString(), anyString(), anyDouble(), anyDouble(), anyDouble()))
                .thenReturn(List.of(estabelecimentoResource));

        List<EstabelecimentoResource> estabelecimentosFiltrados = estabelecimentoController.filtroEstabelecimento(
                "Teste", "Teste", "Servico", 4.5, 50.0, 150.0);

        assertNotNull(estabelecimentosFiltrados);
        assertFalse(estabelecimentosFiltrados.isEmpty());
        verify(estabelecimentoUseCases).filtrarEstabelecimento(anyList(), anyString(), anyString(), anyString(), anyDouble(), anyDouble(), anyDouble());
    }
}