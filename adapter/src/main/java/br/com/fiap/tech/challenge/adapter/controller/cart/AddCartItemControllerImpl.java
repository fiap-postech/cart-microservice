package br.com.fiap.tech.challenge.adapter.controller.cart;

import br.com.fiap.tech.challenge.adapter.dto.CartDTO;
import br.com.fiap.tech.challenge.adapter.presenter.CartPresenter;
import br.com.fiap.tech.challenge.application.dto.AddCartItemDTO;
import br.com.fiap.tech.challenge.application.usecase.cart.AddCartItemUseCase;
import lombok.RequiredArgsConstructor;

import static java.util.UUID.fromString;

@RequiredArgsConstructor
class AddCartItemControllerImpl implements AddCartItemController {

    private final AddCartItemUseCase useCase;
    private final CartPresenter presenter;

    @Override
    public CartDTO add(String uuid, AddCartItemDTO item) {
        return presenter.present(useCase.add(fromString(uuid), item));
    }
}
