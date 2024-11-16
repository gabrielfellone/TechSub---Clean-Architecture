package com.sub.techsub.core.exception;


import com.sub.techsub.core.usecase.exception.AgendamentoException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.assertj.core.api.Assertions.*;

class AgendamentoExceptionTest {

    @Test
    void testAgendamentoExceptionWithMessage() {
        String errorMessage = "Estabelecimento não encontrado";
        AgendamentoException exception = new AgendamentoException(errorMessage);
        assertThat(exception.getMessage()).isEqualTo(errorMessage);
    }

    @Test
    void testAgendamentoExceptionWithMessageAndCause() {
        String errorMessage = "Erro ao processar agendamento";
        Exception cause = new Exception("Causa raiz");
        AgendamentoException exception = new AgendamentoException(errorMessage, cause);
        assertThat(exception.getMessage()).isEqualTo(errorMessage);
        assertThat(exception.getCause()).isEqualTo(cause);
    }

    @Test
    void testHttpStatusIsNotFound() {
        AgendamentoException exception = new AgendamentoException("Erro");
        assertThat(exception.getClass().getAnnotation(ResponseStatus.class).value())
                .isEqualTo(HttpStatus.NOT_FOUND);
    }
}
