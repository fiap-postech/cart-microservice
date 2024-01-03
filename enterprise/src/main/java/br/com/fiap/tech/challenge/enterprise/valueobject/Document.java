package br.com.fiap.tech.challenge.enterprise.valueobject;

import br.com.fiap.tech.challenge.enterprise.validation.DocumentCustomer;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.io.Serial;

@Accessors(fluent = true)
@Getter
public class Document extends ValueObject {
    @Serial
    private static final long serialVersionUID = -7027977210412190271L;

    @SuppressWarnings({"java:S1700"})
    @DocumentCustomer
    private final String document;

    private Document(String document) {
        this.document = document;

        validate();
    }

    public static Document of(String document) {
        return new Document(document);
    }
}
