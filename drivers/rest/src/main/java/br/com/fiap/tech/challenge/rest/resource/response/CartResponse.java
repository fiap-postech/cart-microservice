package br.com.fiap.tech.challenge.rest.resource.response;

import br.com.fiap.tech.challenge.rest.common.response.Response;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Representação de um Carrinho")
public class CartResponse extends Response {
    @Serial
    private static final long serialVersionUID = 1464909268054662495L;

    @Schema(description = "Identificador do produto", example = "269f2a43-adcd-49fe-bedc-d30c589d883a")
    private String id;

    @Schema(description = "Valor total do carrinho", example = "9.05")
    private BigDecimal total;

    @Schema(description = "Subtotal do valor do carrinho", example = "10.95")
    private BigDecimal subtotal;

    @Schema(description = "Desconto do carrinho", example = "1.90")
    private BigDecimal discount;

    @Schema(description = "Cliente do carrinho")
    private CustomerResponse customer;

    @Schema(description = "Lista de itens do carrinho")
    private List<CartItemResponse> items;
}
