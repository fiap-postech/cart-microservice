package br.com.fiap.tech.challenge.customer.launcher.fixture;

import br.com.fiap.tech.challenge.rest.resource.request.CreateCartRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Instancio;
import org.instancio.Model;

import java.util.UUID;

import static org.instancio.Select.field;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateCartRequestFixture {

    public static Model<CreateCartRequest> createCartRequestModel() {
        return Instancio.of(CreateCartRequest.class)
                .set(field(CreateCartRequest::getCustomerId), UUID.randomUUID().toString())
                .toModel();
    }

}
