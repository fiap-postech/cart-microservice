package br.com.fiap.tech.challenge.adapter.presenter;


import br.com.fiap.tech.challenge.adapter.dto.CartDTO;
import br.com.fiap.tech.challenge.adapter.mapping.CartMapper;
import br.com.fiap.tech.challenge.enterprise.entity.Cart;

class CartPresenterImpl implements CartPresenter {
    @Override
    public CartDTO present(Cart cart) {
        return CartMapper.INSTANCE.toDTO(cart);
    }
}
