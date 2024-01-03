package br.com.fiap.tech.challenge.adapter.driven.redis.converter;

import br.com.fiap.tech.challenge.enterprise.valueobject.EmailRegistration;
import org.springframework.core.convert.converter.Converter;

public class ReadingEmailRegistrationConverter implements Converter<byte[], EmailRegistration> {
    @Override
    public EmailRegistration convert(byte[] source) {
        return EmailRegistration.of(new String(source));
    }
}
