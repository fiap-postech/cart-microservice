package br.com.fiap.tech.challenge.application.usecase.cart;

import br.com.fiap.tech.challenge.application.dto.CreateCartDTO;
import br.com.fiap.tech.challenge.application.gateway.CartWriterGateway;
import br.com.fiap.tech.challenge.application.usecase.customer.FindCustomerByDocumentUseCase;
import br.com.fiap.tech.challenge.application.util.Validations;
import br.com.fiap.tech.challenge.enterprise.entity.Cart;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import lombok.RequiredArgsConstructor;

import static br.com.fiap.tech.challenge.enterprise.error.ApplicationError.CART_CUSTOMER_NOT_AVAILABLE;
import static br.com.fiap.tech.challenge.enterprise.error.ApplicationError.CUSTOMER_NOT_FOUND_BY_DOCUMENT;

@RequiredArgsConstructor
class CreateCartUseCaseImpl implements CreateCartUseCase {

    private final FindCustomerByDocumentUseCase findCustomerByUUIDUseCase;
    private final CartWriterGateway writerService;

    @Override
    public Cart create(CreateCartDTO dto) {
        Validations.validate(dto);

        var customer = findCustomerByUUIDUseCase.get(dto.getCustomerDocument())
                .orElseThrow(() -> new ApplicationException(CUSTOMER_NOT_FOUND_BY_DOCUMENT, dto.getCustomerDocument()));

        if (!customer.enabled()) {
            throw new ApplicationException(CART_CUSTOMER_NOT_AVAILABLE, customer.uuid());
        }

        var cart = Cart.builder()
                .customer(customer)
                .build();

        return writerService.write(cart);
    }
}