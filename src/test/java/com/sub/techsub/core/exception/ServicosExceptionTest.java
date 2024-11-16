package com.sub.techsub.core.exception;


import com.sub.techsub.core.usecase.exception.ServicosException;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

class ServicosExceptionTest {

    @Test
    void testServicosExceptionWithMessage() {
        String errorMessage = "Serviço não encontrado";
        ServicosException exception = new ServicosException(errorMessage);
        assertThat(exception.getMessage()).isEqualTo(errorMessage);
    }

    @Test
    void testServicosExceptionWithMessageAndCause() {
        String errorMessage = "Erro ao buscar serviço";
        Exception cause = new Exception("Causa raiz");
        ServicosException exception = new ServicosException(errorMessage, cause);
        assertThat(exception.getMessage()).isEqualTo(errorMessage);
        assertThat(exception.getCause()).isEqualTo(cause);
    }

    @Test
    void testHttpStatusIsNotFound() {
        ServicosException exception = new ServicosException("Erro");
        assertThat(exception.getClass().getAnnotation(ResponseStatus.class).value())
                .isEqualTo(HttpStatus.NOT_FOUND);
    }
}
