package com.sub.techsub.core.domain.presenter;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class FormatarValorFotosEstacionamentoTest {

    private final FormatarValorFotosEstacionamento converter = new FormatarValorFotosEstacionamento();

    @Test
    void testConvertToDatabaseColumn() throws Exception {
        String[] fotos = {"foto1.jpg", "foto2.jpg", "foto3.jpg"};
        String json = converter.convertToDatabaseColumn(fotos);
        assertThat(json).isEqualTo("[\"foto1.jpg\",\"foto2.jpg\",\"foto3.jpg\"]");
    }

    @Test
    void testConvertToDatabaseColumn_EmptyArray() throws Exception {
        String[] fotos = {};
        String json = converter.convertToDatabaseColumn(fotos);
        assertThat(json).isEqualTo("[]");
    }

    @Test
    void testConvertToEntityAttribute() throws Exception {
        String json = "[\"foto1.jpg\",\"foto2.jpg\",\"foto3.jpg\"]";
        String[] fotos = converter.convertToEntityAttribute(json);
        assertThat(fotos).containsExactly("foto1.jpg", "foto2.jpg", "foto3.jpg");
    }

    @Test
    void testConvertToEntityAttribute_MalformedJson() throws Exception {
        String malformedJson = "[1231";
        assertThatThrownBy(() -> converter.convertToEntityAttribute(malformedJson))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void testConvertToEntityAttribute_InvalidJsonFormat() throws Exception {
        String invalidJson = "[1231";
        assertThatThrownBy(() -> converter.convertToEntityAttribute(invalidJson))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void testConvertToEntityAttribute_ValidMalformedJson() throws Exception {
        String malformedJson = "{foto1.jpg,foto2.jpg,foto3.jpg}";
        String[] fotos = converter.convertToEntityAttribute(malformedJson);
        assertThat(fotos).containsExactly("foto1.jpg", "foto2.jpg", "foto3.jpg");
    }
}
