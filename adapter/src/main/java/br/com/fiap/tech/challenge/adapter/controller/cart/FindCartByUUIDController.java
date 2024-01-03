package br.com.fiap.tech.challenge.adapter.controller.cart;

import br.com.fiap.tech.challenge.adapter.dto.CartDTO;

public interface FindCartByUUIDController {

    CartDTO get(String uuid);
}
