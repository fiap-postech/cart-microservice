package br.com.fiap.tech.challenge.adapter.controller.cart;

import br.com.fiap.tech.challenge.adapter.dto.CartDTO;
import br.com.fiap.tech.challenge.adapter.presenter.CartPresenter;
import br.com.fiap.tech.challenge.application.usecase.cart.FindCartByUUIDUseCase;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
class FindCartByUUIDControllerImpl implements FindCartByUUIDController {

    private final FindCartByUUIDUseCase useCase;
    private final CartPresenter presenter;

    @Override
    public CartDTO get(String uuid) {
        return presenter.present(useCase.get(UUID.fromString(uuid)));
    }
}
