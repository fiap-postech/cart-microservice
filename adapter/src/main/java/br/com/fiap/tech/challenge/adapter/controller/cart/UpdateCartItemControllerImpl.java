package br.com.fiap.tech.challenge.adapter.controller.cart;

import br.com.fiap.tech.challenge.adapter.dto.CartDTO;
import br.com.fiap.tech.challenge.adapter.presenter.CartPresenter;
import br.com.fiap.tech.challenge.application.dto.UpdateCartItemDTO;
import br.com.fiap.tech.challenge.application.usecase.cart.UpdateCartItemUseCase;
import lombok.RequiredArgsConstructor;

import static java.util.UUID.fromString;

@RequiredArgsConstructor
class UpdateCartItemControllerImpl implements UpdateCartItemController {

    private final UpdateCartItemUseCase useCase;
    private final CartPresenter presenter;

    @Override
    public CartDTO update(String uuid, UpdateCartItemDTO dto) {
        return presenter.present(useCase.update(fromString(uuid), dto));
    }
}
