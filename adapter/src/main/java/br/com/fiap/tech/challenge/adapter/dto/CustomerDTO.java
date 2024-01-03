package br.com.fiap.tech.challenge.adapter.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class CustomerDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -447066642189592390L;

    private String id;
    private String name;
    private String email;
    private String document;
    private boolean enabled;
}
