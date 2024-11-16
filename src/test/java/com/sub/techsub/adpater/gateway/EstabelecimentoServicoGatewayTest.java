package com.sub.techsub.adpater.gateway;


import com.sub.techsub.adapter.gateway.EstabelecimentoServicoGateway;
import com.sub.techsub.core.domain.model.EstabelecimentoServico;
import com.sub.techsub.infrastructure.repository.IEstabelecimentoServicoRepository;
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
public class EstabelecimentoServicoGatewayTest {

    @Mock
    private IEstabelecimentoServicoRepository estabelecimentoServicoRepository;

    @InjectMocks
    private EstabelecimentoServicoGateway estabelecimentoServicoGateway;

    @Test
    void testSalvarRelacionamentoEstabelecimentoServico() {
        EstabelecimentoServico estabelecimentoServico = new EstabelecimentoServico();
        estabelecimentoServico.setEstabelecimentoId(1L);
        estabelecimentoServico.setServicoId(1L);

        when(estabelecimentoServicoRepository.save(estabelecimentoServico)).thenReturn(estabelecimentoServico);
        EstabelecimentoServico savedRelacionamento = estabelecimentoServicoGateway.salvarRelacionamentoEstabelecimentoServico(estabelecimentoServico);

        assertNotNull(savedRelacionamento);
        assertEquals(1L, savedRelacionamento.getEstabelecimentoId());
        assertEquals(1L, savedRelacionamento.getServicoId());

        verify(estabelecimentoServicoRepository, times(1)).save(estabelecimentoServico);
    }
}
