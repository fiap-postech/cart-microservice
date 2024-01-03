package br.com.fiap.tech.challenge.launcher.configuration;

import br.com.fiap.tech.challenge.adapter.controller.cart.AddCartItemController;
import br.com.fiap.tech.challenge.adapter.controller.cart.CartControllerFactory;
import br.com.fiap.tech.challenge.adapter.controller.cart.CreateCartController;
import br.com.fiap.tech.challenge.adapter.controller.cart.FindCartByUUIDController;
import br.com.fiap.tech.challenge.adapter.controller.cart.RemoveCartItemController;
import br.com.fiap.tech.challenge.adapter.controller.cart.UpdateCartItemController;
import br.com.fiap.tech.challenge.adapter.presenter.CartPresenter;
import br.com.fiap.tech.challenge.application.usecase.cart.AddCartItemUseCase;
import br.com.fiap.tech.challenge.application.usecase.cart.CreateCartUseCase;
import br.com.fiap.tech.challenge.application.usecase.cart.FindCartByUUIDUseCase;
import br.com.fiap.tech.challenge.application.usecase.cart.RemoveCartItemUseCase;
import br.com.fiap.tech.challenge.application.usecase.cart.UpdateCartItemUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfiguration {

    @Bean
    public CreateCartController createCartController(CreateCartUseCase useCase, CartPresenter presenter) {
        return CartControllerFactory.createCartController(useCase, presenter);
    }

    @Bean
    public FindCartByUUIDController findCartByUUIDController(FindCartByUUIDUseCase useCase, CartPresenter presenter) {
        return CartControllerFactory.findCartByUUIDController(useCase, presenter);
    }

    @Bean
    public AddCartItemController addCartItemController(AddCartItemUseCase useCase, CartPresenter presenter) {
        return CartControllerFactory.addCartItemController(useCase, presenter);
    }

    @Bean
    public UpdateCartItemController updateCartItemController(UpdateCartItemUseCase useCase, CartPresenter presenter) {
        return CartControllerFactory.updateCartItemController(useCase, presenter);
    }

    @Bean
    public RemoveCartItemController removeCartItemController(RemoveCartItemUseCase useCase, CartPresenter presenter) {
        return CartControllerFactory.removeCartItemController(useCase, presenter);
    }
}
