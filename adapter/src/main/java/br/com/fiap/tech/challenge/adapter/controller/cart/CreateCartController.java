package br.com.fiap.tech.challenge.adapter.controller.cart;

import br.com.fiap.tech.challenge.adapter.dto.CartDTO;
import br.com.fiap.tech.challenge.application.dto.CreateCartDTO;

public interface CreateCartController {

    CartDTO create(CreateCartDTO dto);
}
