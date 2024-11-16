package com.sub.techsub.adpater.controller;

import com.sub.techsub.adapter.controller.ServicoController;
import com.sub.techsub.adapter.gateway.ServicoGateway;
import com.sub.techsub.core.domain.model.Servico;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringJUnitConfig
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ServicoControllerTest {

    @Mock
    private ServicoGateway servicoGateway;

    @InjectMocks
    private ServicoController servicoController;


    @Test
    void buscaPorIdServico_DeveRetornarServico_QuandoIdForValido() {
        Long id = 1L;
        Servico servicoEsperado = new Servico();
        when(servicoGateway.buscarServicoPorReference(id)).thenReturn(servicoEsperado);

        Servico resultado = servicoController.buscaPorIdServico(id);

        assertNotNull(resultado);
        assertEquals(servicoEsperado, resultado);
        verify(servicoGateway, times(1)).buscarServicoPorReference(id);
    }

}

