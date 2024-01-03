package br.com.fiap.tech.challenge.adapter.controller.cart;

import br.com.fiap.tech.challenge.adapter.presenter.CartPresenter;
import br.com.fiap.tech.challenge.application.usecase.cart.AddCartItemUseCase;
import br.com.fiap.tech.challenge.application.usecase.cart.CreateCartUseCase;
import br.com.fiap.tech.challenge.application.usecase.cart.FindCartByUUIDUseCase;
import br.com.fiap.tech.challenge.application.usecase.cart.RemoveCartItemUseCase;
import br.com.fiap.tech.challenge.application.usecase.cart.UpdateCartItemUseCase;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartControllerFactory {

    public static CreateCartController createCartController(CreateCartUseCase useCase, CartPresenter presenter) {
        return new CreateCartControllerImpl(useCase, presenter);
    }

    public static FindCartByUUIDController findCartByUUIDController(FindCartByUUIDUseCase useCase, CartPresenter presenter) {
        return new FindCartByUUIDControllerImpl(useCase, presenter);
    }

    public static AddCartItemController addCartItemController(AddCartItemUseCase useCase, CartPresenter presenter) {
        return new AddCartItemControllerImpl(useCase, presenter);
    }

    public static UpdateCartItemController updateCartItemController(UpdateCartItemUseCase useCase, CartPresenter presenter) {
        return new UpdateCartItemControllerImpl(useCase, presenter);
    }

    public static RemoveCartItemController removeCartItemController(RemoveCartItemUseCase useCase, CartPresenter presenter) {
        return new RemoveCartItemControllerImpl(useCase, presenter);
    }

}
