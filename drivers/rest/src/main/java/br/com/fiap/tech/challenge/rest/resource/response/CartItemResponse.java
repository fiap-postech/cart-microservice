package br.com.fiap.tech.challenge.rest.resource.response;

import br.com.fiap.tech.challenge.rest.common.response.Response;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Representação de um Item do Carrinho")
public class CartItemResponse extends Response {

    @Serial
    private static final long serialVersionUID = 5960662426733468603L;

    @Schema(description = "Produto do carrinho")
    private ProductResponse product;

    @Schema(description = "Quantidade do produto do item do carrinho", example = "1")
    private int quantity;

    @Schema(description = "Valor total do item do carrinho", example = "9.05")
    private BigDecimal total;

    @Schema(description = "Subtotal do valor do item do carrinho", example = "10.95")
    private BigDecimal subtotal;

    @Schema(description = "Desconto do item do carrinho", example = "1.90")
    private BigDecimal discount;
}