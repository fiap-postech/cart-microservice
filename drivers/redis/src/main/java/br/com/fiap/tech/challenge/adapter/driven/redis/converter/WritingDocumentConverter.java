package br.com.fiap.tech.challenge.adapter.driven.redis.converter;

import br.com.fiap.tech.challenge.enterprise.valueobject.Document;
import org.springframework.core.convert.converter.Converter;

import java.util.Optional;

public class WritingDocumentConverter implements Converter<Document, byte[]> {
    @Override
    public byte[] convert(Document source) {
        return Optional.of(source)
                .map(Document::document)
                .map(String::getBytes)
                .orElse(new byte[0]);
    }
}
