package br.com.fiap.tech.challenge.adapter.driven.redis.converter;

import br.com.fiap.tech.challenge.enterprise.valueobject.Document;
import org.springframework.core.convert.converter.Converter;

public class ReadingDocumentConverter implements Converter<byte[], Document> {
    @Override
    public Document convert(byte[] source) {
        return Document.of(new String(source));
    }
}
