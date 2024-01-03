package br.com.fiap.tech.challenge.driven.customer.response;

import br.com.fiap.tech.challenge.rest.common.response.Response;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class CustomerResponse extends Response {

    private String id;

    private String name;

    private String email;

    private String document;

    private boolean enabled;
}
