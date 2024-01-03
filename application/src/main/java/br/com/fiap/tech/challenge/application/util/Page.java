package br.com.fiap.tech.challenge.application.util;

import java.io.Serial;
import java.io.Serializable;

public record Page(
        Integer number,
        Integer size
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 5203104269614399061L;
}
