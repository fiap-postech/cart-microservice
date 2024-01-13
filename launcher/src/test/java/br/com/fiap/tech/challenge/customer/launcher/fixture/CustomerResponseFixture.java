package br.com.fiap.tech.challenge.customer.launcher.fixture;

import br.com.fiap.tech.challenge.driven.customer.response.CustomerResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Instancio;
import org.instancio.Model;

import java.util.UUID;

import static org.instancio.Select.field;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerResponseFixture {

    public static Model<CustomerResponse> consumerCustomerResponseModel() {
        return Instancio.of(CustomerResponse.class)
                .set(field(CustomerResponse::getId), UUID.randomUUID().toString())
                .set(field(CustomerResponse::getName), "Consumidor")
                .set(field(CustomerResponse::getDocument), "00000000000")
                .set(field(CustomerResponse::getEmail), "consumidor@digio.com.br")
                .set(field(CustomerResponse::isEnabled), true)
                .toModel();
    }

}
