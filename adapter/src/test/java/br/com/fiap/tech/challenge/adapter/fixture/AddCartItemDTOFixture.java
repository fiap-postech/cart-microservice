package br.com.fiap.tech.challenge.adapter.fixture;

import br.com.fiap.tech.challenge.application.dto.AddCartItemDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Instancio;
import org.instancio.Model;

import static org.instancio.Select.field;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddCartItemDTOFixture {

    public static Model<AddCartItemDTO> createAddCartItemDTOModel() {
        return Instancio.of(AddCartItemDTO.class)
                .set(field(AddCartItemDTO::getProductId), "ade7f71c-5642-4fff-9385-4b983a0f9a7d")
                .set(field(AddCartItemDTO::getQuantity), 1)
                .toModel();
    }



}
