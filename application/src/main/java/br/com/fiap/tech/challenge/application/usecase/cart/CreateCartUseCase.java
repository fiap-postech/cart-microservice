package br.com.fiap.tech.challenge.application.usecase.cart;

import br.com.fiap.tech.challenge.application.dto.CreateCartDTO;
import br.com.fiap.tech.challenge.enterprise.entity.Cart;

public interface CreateCartUseCase {

    Cart create(CreateCartDTO dto);

}