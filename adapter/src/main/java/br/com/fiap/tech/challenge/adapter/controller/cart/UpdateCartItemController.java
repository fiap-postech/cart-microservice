package br.com.fiap.tech.challenge.adapter.controller.cart;

import br.com.fiap.tech.challenge.adapter.dto.CartDTO;
import br.com.fiap.tech.challenge.application.dto.UpdateCartItemDTO;

public interface UpdateCartItemController {

    CartDTO update(String uuid, UpdateCartItemDTO dto);

}
