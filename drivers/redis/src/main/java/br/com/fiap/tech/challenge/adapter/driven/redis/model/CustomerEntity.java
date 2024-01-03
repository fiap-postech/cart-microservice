package br.com.fiap.tech.challenge.adapter.driven.redis.model;

import br.com.fiap.tech.challenge.enterprise.valueobject.Document;
import br.com.fiap.tech.challenge.enterprise.valueobject.EmailRegistration;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class CustomerEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -3460260434106384272L;

    private String id;
    private String name;
    private Document document;
    private EmailRegistration email;
    private Boolean enabled;
}