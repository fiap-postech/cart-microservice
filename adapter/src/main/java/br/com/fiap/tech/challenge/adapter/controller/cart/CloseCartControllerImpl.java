package br.com.fiap.tech.challenge.adapter.controller.cart;

import br.com.fiap.tech.challenge.application.usecase.cart.CloseCartUseCase;
import lombok.RequiredArgsConstructor;

import static java.util.UUID.fromString;

@RequiredArgsConstructor
public class CloseCartControllerImpl implements CloseCartController {

    private final CloseCartUseCase useCase;

    @Override
    public void close(String cartUuid) {
        useCase.close(fromString(cartUuid));
    }
}