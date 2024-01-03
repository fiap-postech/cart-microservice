package br.com.fiap.tech.challenge.adapter.fixture;

import br.com.fiap.tech.challenge.enterprise.entity.Customer;
import br.com.fiap.tech.challenge.enterprise.valueobject.Document;
import br.com.fiap.tech.challenge.enterprise.valueobject.EmailRegistration;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Instancio;
import org.instancio.Model;

import java.util.UUID;

import static org.instancio.Select.field;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerFixture {

    public static final UUID UUID_CUSTOMER = UUID.fromString("3478a25e-704d-4e6b-916e-5789a940f2e8");

    public static Model<Customer> createCustomerModel() {
        return Instancio.of(Customer.class)
                .set(field(Customer::name), "José da Silva")
                .set(field(Customer::email), EmailRegistration.of("jose.silva@gmail.com"))
                .set(field(Customer::document), Document.of("19748826325"))
                .set(field(Customer::enabled), true)
                .set(field(Customer::uuid), UUID_CUSTOMER)
                .toModel();
    }
}
