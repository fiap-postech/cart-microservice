package br.com.fiap.tech.challenge.application.usecase.customer;

import br.com.fiap.tech.challenge.enterprise.entity.Customer;
import br.com.fiap.tech.challenge.enterprise.valueobject.Document;
import br.com.fiap.tech.challenge.application.gateway.CustomerReaderGateway;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
class FindCustomerByDocumentUseCaseImpl implements FindCustomerByDocumentUseCase {

    private final CustomerReaderGateway readerService;

    @Override
    public Optional<Customer> get(String document) {
        return readerService.readByDocument(Document.of(document));
    }

}
