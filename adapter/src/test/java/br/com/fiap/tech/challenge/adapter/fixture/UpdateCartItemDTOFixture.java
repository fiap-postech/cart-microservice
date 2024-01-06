package br.com.fiap.tech.challenge.adapter.fixture;

import br.com.fiap.tech.challenge.application.dto.UpdateCartItemDTO;
import org.instancio.Instancio;
import org.instancio.Model;

import static org.instancio.Select.field;

public class UpdateCartItemDTOFixture {

    public static Model<UpdateCartItemDTO> createUpdateCartItemDTOModel() {
        return Instancio.of(UpdateCartItemDTO.class)
                .set(field(UpdateCartItemDTO::getProductId), "82736436-9ea5-45d1-81a4-31cba447566e")
                .set(field(UpdateCartItemDTO::getQuantity), 2)
                .toModel();
    }
}
