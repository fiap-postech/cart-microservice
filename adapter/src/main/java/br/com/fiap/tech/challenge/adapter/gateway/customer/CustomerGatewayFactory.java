package br.com.fiap.tech.challenge.adapter.gateway.customer;

import br.com.fiap.tech.challenge.adapter.repository.CustomerReaderRepository;
import br.com.fiap.tech.challenge.application.gateway.CustomerReaderGateway;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerGatewayFactory {

    public static CustomerReaderGateway customerReaderGateway(CustomerReaderRepository readerRepository){
        return new CustomerReaderGatewayImpl(readerRepository);
    }

}
