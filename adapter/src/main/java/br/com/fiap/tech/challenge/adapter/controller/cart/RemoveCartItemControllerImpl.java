package br.com.fiap.tech.challenge.adapter.controller.cart;

import br.com.fiap.tech.challenge.adapter.dto.CartDTO;
import br.com.fiap.tech.challenge.adapter.presenter.CartPresenter;
import br.com.fiap.tech.challenge.application.dto.RemoveCartItemDTO;
import br.com.fiap.tech.challenge.application.usecase.cart.RemoveCartItemUseCase;
import lombok.RequiredArgsConstructor;

import static java.util.UUID.fromString;

@RequiredArgsConstructor
class RemoveCartItemControllerImpl implements RemoveCartItemController {

    private final RemoveCartItemUseCase useCase;
    private final CartPresenter presenter;

    @Override
    public CartDTO remove(String cartUuid, RemoveCartItemDTO dto) {
        return presenter.present(useCase.remove(fromString(cartUuid), dto));
    }
}
