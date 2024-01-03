package br.com.fiap.tech.challenge.application.usecase.cart;

import br.com.fiap.tech.challenge.application.dto.CreateCartDTO;
import br.com.fiap.tech.challenge.application.gateway.CartWriterGateway;
import br.com.fiap.tech.challenge.application.usecase.customer.FindCustomerByUUIDUseCase;
import br.com.fiap.tech.challenge.application.util.Validations;
import br.com.fiap.tech.challenge.enterprise.entity.Cart;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

import static br.com.fiap.tech.challenge.enterprise.error.ApplicationError.CART_CUSTOMER_NOT_AVAILABLE;
import static br.com.fiap.tech.challenge.enterprise.error.ApplicationError.CUSTOMER_NOT_FOUND_BY_UUID;

@RequiredArgsConstructor
class CreateCartUseCaseImpl implements CreateCartUseCase {

    private final FindCustomerByUUIDUseCase findCustomerByUUIDUseCase;
    private final CartWriterGateway writerService;

    @Override
    public Cart create(CreateCartDTO dto) {
        Validations.validate(dto);

        var customer = findCustomerByUUIDUseCase.get(UUID.fromString(dto.getCustomerId()))
                .orElseThrow(() -> new ApplicationException(CUSTOMER_NOT_FOUND_BY_UUID, dto.getCustomerId()));

        if (!customer.enabled()) {
            throw new ApplicationException(CART_CUSTOMER_NOT_AVAILABLE, customer.uuid());
        }

        var cart = Cart.builder()
                .customer(customer)
                .build();

        return writerService.write(cart);
    }
}