package com.sub.techsub.adpater.gateway;

import com.sub.techsub.adapter.gateway.EstabelecimentoProfissionalGateway;
import com.sub.techsub.core.domain.model.EstabelecimentoProfissional;
import com.sub.techsub.infrastructure.repository.IEstabelecimentoProfissionalRepository;
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

public class EstabelecimentoProfissionalGatewayTest {


    @Mock
    private IEstabelecimentoProfissionalRepository estabelecimentoProfissionalRepository;

    @InjectMocks
    private EstabelecimentoProfissionalGateway estabelecimentoProfissionalGateway;

    @Test
    void testSalvarRelacionamentoEstabelecimentoProfissional() {
        EstabelecimentoProfissional estabelecimentoProfissional = new EstabelecimentoProfissional();
        estabelecimentoProfissional.setEstabelecimentoId(1L);
        estabelecimentoProfissional.setProfissionalId(1L);

        when(estabelecimentoProfissionalRepository.save(estabelecimentoProfissional)).thenReturn(estabelecimentoProfissional);

        EstabelecimentoProfissional savedRelacionamento = estabelecimentoProfissionalGateway.salvarRelacionamentoEstabelecimentoProfissional(estabelecimentoProfissional);

        assertNotNull(savedRelacionamento);
        assertEquals(1L, savedRelacionamento.getEstabelecimentoId());
        assertEquals(1L, savedRelacionamento.getProfissionalId());

        verify(estabelecimentoProfissionalRepository, times(1)).save(estabelecimentoProfissional);
    }
}
