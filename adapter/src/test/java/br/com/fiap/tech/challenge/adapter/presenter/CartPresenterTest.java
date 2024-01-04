package br.com.fiap.tech.challenge.adapter.presenter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static br.com.fiap.tech.challenge.adapter.fixture.CartDTOFixture.createCartDTOModel;
import static br.com.fiap.tech.challenge.adapter.fixture.CartFixture.createCartModel;
import static br.com.fiap.tech.challenge.adapter.fixture.Fixture.create;
import static org.assertj.core.api.Assertions.assertThat;

class CartPresenterTest {

    private CartPresenter presenter;

    @BeforeEach
    void setUp() {
        presenter = PresenterFactory.cartPresenter();
    }

    @Test
    void shouldPresentCart() {
        var cart = create(createCartModel());
        var expected = create(createCartDTOModel());

        var response = presenter.present(cart);

        assertThat(response).usingRecursiveComparison().isEqualTo(expected);
    }
}
