package br.com.fiap.tech.challenge.application.usecase.customer;

import br.com.fiap.tech.challenge.application.gateway.CustomerReaderGateway;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerUseCaseFactory {

    public static FindCustomerByDocumentUseCase findCustomerByDocumentService(CustomerReaderGateway reader) {
        return new FindCustomerByDocumentUseCaseImpl(reader);
    }

}
