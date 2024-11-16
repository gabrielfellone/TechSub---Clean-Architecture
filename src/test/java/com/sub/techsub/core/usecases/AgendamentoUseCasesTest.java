package com.sub.techsub.core.usecases;

import com.sub.techsub.core.domain.model.Estabelecimento;
import com.sub.techsub.core.domain.model.Profissional;
import com.sub.techsub.core.usecase.AgendamentoUseCases;
import com.sub.techsub.core.usecase.exception.AgendamentoException;
import com.sub.techsub.core.usecase.exception.ProfissionalException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AgendamentoUseCasesTest {

    private AgendamentoUseCases agendamentoUseCases;

    @BeforeEach
    void setUp() {
        agendamentoUseCases = new AgendamentoUseCases();
    }

    @Test
    void testFormatarDataParaAgendamento() {
        LocalDateTime dataAgendamento = LocalDateTime.of(2024, 11, 10, 14, 30);
        LocalDate dataEsperada = LocalDate.of(2024, 11, 10);

        LocalDate dataFormatada = agendamentoUseCases.formatarDataParaAgendamento(dataAgendamento);

        assertEquals(dataEsperada, dataFormatada);
    }

    @Test
    void testFormatarHoraParaAgendamento() {
        LocalDateTime dataAgendamento = LocalDateTime.of(2024, 11, 10, 14, 30);
        LocalTime horaEsperada = LocalTime.of(14, 30);

        LocalTime horaFormatada = agendamentoUseCases.formatarHoraParaAgendamento(dataAgendamento);

        assertEquals(horaEsperada, horaFormatada);
    }

    @Test
    void testValidarDataEHoraEstabelecimento_Valido() {
        LocalDateTime dataAgendamento = LocalDateTime.of(2024, 11, 11, 10, 0);
        Estabelecimento estabelecimento = mock(Estabelecimento.class);
        when(estabelecimento.getHorarioFuncionamento()).thenReturn("Segunda Sexta 9:00-18:00");

        agendamentoUseCases.validarDataEHoraEstabelecimento(Optional.of(estabelecimento), dataAgendamento);


    }

    @Test
    void testValidarDataEHoraEstabelecimento_EstabelecimentoNaoEncontrado() {
        LocalDateTime dataAgendamento = LocalDateTime.of(2024, 11, 11, 10, 0);

        assertThrows(AgendamentoException.class, () ->
                agendamentoUseCases.validarDataEHoraEstabelecimento(Optional.empty(), dataAgendamento)
        );
    }

    @Test
    void testValidarDataEHoraProfissional_Valido() {
        LocalDateTime dataAgendamento = LocalDateTime.of(2024, 11, 11, 10, 0);
        Profissional profissional = mock(Profissional.class);
        when(profissional.getHorariosDisponiveis()).thenReturn("Segunda Sexta 9:00-18:00");

        agendamentoUseCases.validarDataEHoraProfissional(Optional.of(profissional), dataAgendamento);


    }

    @Test
    void testValidarDataEHoraProfissional_ProfissionalNaoEncontrado() {
        LocalDateTime dataAgendamento = LocalDateTime.of(2024, 11, 10, 10, 0);

        assertThrows(ProfissionalException.class, () ->
                agendamentoUseCases.validarDataEHoraProfissional(Optional.empty(), dataAgendamento)
        );
    }

    @Test
    void testValidarRangeDosDiasDiponiveis() {
        assertTrue(AgendamentoUseCases.validarRangeDosDiasDiponiveis(2, 4, 3)); // Terça a Quinta, Terça é válido
        assertFalse(AgendamentoUseCases.validarRangeDosDiasDiponiveis(2, 4, 5)); // Terça a Quinta, Sexta não é válido
    }

    @Test
    void testValidarRangeHoraDisponivel() {
        LocalDateTime dataAgendamentoDentro = LocalDateTime.of(2024, 11, 10, 10, 30);
        LocalDateTime dataAgendamentoFora = LocalDateTime.of(2024, 11, 10, 8, 0);
        LocalTime horaEntrada = LocalTime.of(9, 0);
        LocalTime horaSaida = LocalTime.of(18, 0);

        assertTrue(AgendamentoUseCases.validarRangeHoraDisponivel(dataAgendamentoDentro, horaEntrada, horaSaida));
        assertFalse(AgendamentoUseCases.validarRangeHoraDisponivel(dataAgendamentoFora, horaEntrada, horaSaida));
    }

    @Test
    void testFormatarEValidaDataHoraAgendamento_FormatoInvalido() {
        String horarioInvalido = "Segunda Sexta 18:00-09:00";
        LocalDateTime dataAgendamento = LocalDateTime.of(2024, 11, 10, 10, 0);

        assertThrows(RuntimeException.class, () ->
                AgendamentoUseCases.formatarEValidaDataHoraAgendamento(horarioInvalido, dataAgendamento)
        );
    }
}
