package com.sub.techsub.infrastructure;

;
import com.sub.techsub.core.domain.model.Estabelecimento;
import com.sub.techsub.infrastructure.repository.IEstabelecimentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringJUnitConfig
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EstabelecimentoRepositoryTest {

    @Autowired
    private IEstabelecimentoRepository estabelecimentoRepository;

    private Estabelecimento estabelecimento;

    @BeforeEach
    public void setUp() {
        estabelecimento = new Estabelecimento();
        estabelecimento.setNome("Estabelecimento repository teste");
        estabelecimento.setEndereco("Rua Teste");
    }

    @Test
    @Rollback(false)
    public void testSaveAndFindById() {
        Estabelecimento savedEstabelecimento = estabelecimentoRepository.save(estabelecimento);
        Optional<Estabelecimento> found = estabelecimentoRepository.findById(savedEstabelecimento.getId());
        assertTrue(found.isPresent());
        assertEquals(estabelecimento.getNome(), found.get().getNome());
    }

    @Test
    @Rollback(false)
    public void testDelete() {
        Estabelecimento savedEstabelecimento = estabelecimentoRepository.save(estabelecimento);
        estabelecimentoRepository.delete(savedEstabelecimento);
        Optional<Estabelecimento> found = estabelecimentoRepository.findById(savedEstabelecimento.getId());
        assertFalse(found.isPresent());
    }

    @Test
    public void testFindAll() {
        int atualTamanho = estabelecimentoRepository.findAll().size();
        estabelecimentoRepository.save(estabelecimento);
        assertEquals(atualTamanho+1, estabelecimentoRepository.findAll().size());
    }
}