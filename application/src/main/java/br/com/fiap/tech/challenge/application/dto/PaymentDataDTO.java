package br.com.fiap.tech.challenge.application.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class PaymentDataDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 8246412417021354380L;

    private String id;
}
