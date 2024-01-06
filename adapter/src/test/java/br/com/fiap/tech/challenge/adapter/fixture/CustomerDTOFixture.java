package br.com.fiap.tech.challenge.adapter.fixture;

import br.com.fiap.tech.challenge.adapter.dto.CustomerDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Instancio;
import org.instancio.Model;

import java.util.UUID;

import static br.com.fiap.tech.challenge.adapter.fixture.CustomerFixture.UUID_CUSTOMER;
import static org.instancio.Select.field;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerDTOFixture {

    public static Model<CustomerDTO> enabledCustomerDTOModel() {
        return Instancio.of(CustomerDTO.class)
                .set(field(CustomerDTO::getName), "José da Silva")
                .set(field(CustomerDTO::getId), UUID_CUSTOMER.toString())
                .set(field(CustomerDTO::getEmail), "jose.silva@gmail.com")
                .set(field(CustomerDTO::getDocument), "19748826325")
                .set(field(CustomerDTO::isEnabled), true)
                .toModel();
    }

    public static Model<CustomerDTO> disabledCustomerDTOModel() {
        return Instancio.of(enabledCustomerDTOModel())
                .set(field(CustomerDTO::isEnabled), false)
                .toModel();
    }


}
