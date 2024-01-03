package br.com.fiap.tech.challenge.adapter.controller.cart;

import br.com.fiap.tech.challenge.adapter.dto.CartDTO;
import br.com.fiap.tech.challenge.application.dto.RemoveCartItemDTO;

public interface RemoveCartItemController {

    CartDTO remove(String cartUuid, RemoveCartItemDTO dto);
}
