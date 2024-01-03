package br.com.fiap.tech.challenge.adapter.presenter;

import br.com.fiap.tech.challenge.adapter.dto.CartDTO;
import br.com.fiap.tech.challenge.enterprise.entity.Cart;

public interface CartPresenter {

    CartDTO present(Cart cart);

}
