package br.com.fiap.tech.challenge.adapter.driven.redis.converter;

import br.com.fiap.tech.challenge.enterprise.valueobject.EmailRegistration;
import org.springframework.core.convert.converter.Converter;

import java.util.Optional;

public class WritingEmailRegistrationConverter implements Converter<EmailRegistration, byte[]> {
    @Override
    public byte[] convert(EmailRegistration source) {
        return Optional.ofNullable(source)
                .map(EmailRegistration::email)
                .map(String::getBytes)
                .orElse(new byte[0]);
    }
}
