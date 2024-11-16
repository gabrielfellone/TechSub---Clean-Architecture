package com.sub.techsub.core.exception;


import com.sub.techsub.core.usecase.exception.ProfissionalException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.assertj.core.api.Assertions.*;

class ProfissionalExceptionTest {

    @Test
    void testProfissionalExceptionWithMessage() {
        String errorMessage = "Profissional não encontrado";
        ProfissionalException exception = new ProfissionalException(errorMessage);
        assertThat(exception.getMessage()).isEqualTo(errorMessage);
    }

    @Test
    void testProfissionalExceptionWithMessageAndCause() {
        String errorMessage = "Erro ao buscar profissional";
        Exception cause = new Exception("Causa raiz");
        ProfissionalException exception = new ProfissionalException(errorMessage, cause);
        assertThat(exception.getMessage()).isEqualTo(errorMessage);
        assertThat(exception.getCause()).isEqualTo(cause);
    }

    @Test
    void testHttpStatusIsNotFound() {
        ProfissionalException exception = new ProfissionalException("Erro");
        assertThat(exception.getClass().getAnnotation(ResponseStatus.class).value())
                .isEqualTo(HttpStatus.NOT_FOUND);
    }
}
