package br.com.fiap.tech.challenge.rest.resource.request;

import br.com.fiap.tech.challenge.enterprise.entity.CartItem;
import br.com.fiap.tech.challenge.rest.common.request.Request;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Representação de um Item do Carrinho")
public class UpdateCartItemRequest extends Request<CartItem> {

    @Serial
    private static final long serialVersionUID = 9020112551138763090L;

    @NotBlank
    @Schema(description = "Identificador do produto que será atualizado no carrinho", example = "12d1b555-6b86-41d8-afb6-17a01b293869")
    private String productId;

    @PositiveOrZero
    @Schema(description = "Quantidade do produto que será atualizado no carrinho", example = "2")
    private int quantity;
}