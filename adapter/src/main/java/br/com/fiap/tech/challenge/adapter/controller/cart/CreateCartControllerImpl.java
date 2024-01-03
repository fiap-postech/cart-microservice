package br.com.fiap.tech.challenge.adapter.controller.cart;

import br.com.fiap.tech.challenge.adapter.dto.CartDTO;
import br.com.fiap.tech.challenge.adapter.presenter.CartPresenter;
import br.com.fiap.tech.challenge.application.dto.CreateCartDTO;
import br.com.fiap.tech.challenge.application.usecase.cart.CreateCartUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class CreateCartControllerImpl implements CreateCartController {

    private final CreateCartUseCase useCase;
    private final CartPresenter presenter;

    @Override
    public CartDTO create(CreateCartDTO dto) {
        return presenter.present(useCase.create(dto));
    }
}
