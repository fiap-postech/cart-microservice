package br.com.fiap.tech.challenge.application.fixture;

import br.com.fiap.tech.challenge.application.dto.RemoveCartItemDTO;
import org.instancio.Instancio;
import org.instancio.Model;

import static org.instancio.Select.field;

public class RemoveCartItemDTOFixture {

    public static Model<RemoveCartItemDTO> createRemoveCartItemDTOModel() {
        return Instancio.of(RemoveCartItemDTO.class)
                .set(field(RemoveCartItemDTO::getProductId), "82736436-9ea5-45d1-81a4-31cba447566e")
                .toModel();
    }
}
