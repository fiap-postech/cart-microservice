package br.com.fiap.tech.challenge.application.usecase.cart;

import java.util.UUID;

public interface CloseCartUseCase {

    void close(UUID cartUuid);

}