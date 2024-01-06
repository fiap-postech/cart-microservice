package br.com.fiap.tech.challenge.adapter.fixture;

import br.com.fiap.tech.challenge.application.dto.CreateCartDTO;
import org.instancio.Instancio;
import org.instancio.Model;

import static br.com.fiap.tech.challenge.adapter.fixture.CustomerFixture.UUID_CUSTOMER;
import static org.instancio.Select.field;

public class CreateCartDTOFixture {

    public static Model<CreateCartDTO> buildCreateCartDTOModel() {

        return Instancio.of(CreateCartDTO.class)
                .set(field(CreateCartDTO::getCustomerId), UUID_CUSTOMER.toString())
                .toModel();
    }
}
