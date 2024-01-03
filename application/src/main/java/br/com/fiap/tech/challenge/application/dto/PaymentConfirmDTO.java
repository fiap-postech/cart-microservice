package br.com.fiap.tech.challenge.application.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;

@Data
public class PaymentConfirmDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -3894253820744509086L;

    private String action;
    private String apiVersion;
    private PaymentDataDTO data;
    private String dateCreated;
    private BigInteger id;
    private boolean liveMode;
    private String type;
    private String userId;
}
