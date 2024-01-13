package br.com.fiap.tech.challenge.customer.launcher.fixture;

import br.com.fiap.tech.challenge.rest.resource.request.AddCartItemRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Instancio;
import org.instancio.Model;

import static org.instancio.Select.field;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddCartItemRequestFixture {

    public static Model<AddCartItemRequest> addOneBeverageModel() {
        return Instancio.of(AddCartItemRequest.class)
                .generate(field(AddCartItemRequest::getProductId), gen -> gen.text().uuid())
                .set(field(AddCartItemRequest::getQuantity), 1)
                .toModel();
    }

}
