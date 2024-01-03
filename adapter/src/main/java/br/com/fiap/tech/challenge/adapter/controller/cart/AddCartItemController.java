package br.com.fiap.tech.challenge.adapter.controller.cart;

import br.com.fiap.tech.challenge.adapter.dto.CartDTO;
import br.com.fiap.tech.challenge.application.dto.AddCartItemDTO;

public interface AddCartItemController {
    CartDTO add(String uuid, AddCartItemDTO item);
}
