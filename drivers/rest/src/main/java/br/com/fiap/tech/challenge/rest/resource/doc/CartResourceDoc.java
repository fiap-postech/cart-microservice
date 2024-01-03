package br.com.fiap.tech.challenge.rest.resource.doc;

import br.com.fiap.tech.challenge.rest.common.handler.error.ApiErrorResponse;
import br.com.fiap.tech.challenge.rest.resource.request.AddCartItemRequest;
import br.com.fiap.tech.challenge.rest.resource.request.CreateCartRequest;
import br.com.fiap.tech.challenge.rest.resource.request.RemoveCartItemRequest;
import br.com.fiap.tech.challenge.rest.resource.request.UpdateCartItemRequest;
import br.com.fiap.tech.challenge.rest.resource.response.CartResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Carrinho", description = "API responsável pelo gerenciamento de Carrinho")
public interface CartResourceDoc {

    @Operation(
            summary = "Retorna um carrinho pelo seu UUID",
            description = "Busca um carrinho cadastrado no banco de dados em memória através do UUID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK - Retorno do carrinho em caso de sucesso conforme requisição solicitada", content = { @Content(schema = @Schema(implementation = CartResponse.class), mediaType = "application/json") }),
                    @ApiResponse(responseCode = "400", description = "Retorno em caso de carrinho não encontrado pelo UUID informado", content = { @Content(schema = @Schema()) })
            }
    )
    CartResponse get(@Parameter(description = "UUID para pesquisar um carrinho", required = true) String uuid);

    @Operation(
            summary = "Cadastra um novo carrinho",
            description = "Cadastra um novo carrinho no banco de dados em memória",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Retorno em caso de sucesso em que o carrinho foi cadastrado", content = { @Content(schema = @Schema(implementation = CartResponse.class), mediaType = "application/json") }),
                    @ApiResponse(responseCode = "400", description = "Retorno informando qual campo do carrinho está incorreto e por qual motivo", content = { @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = "application/json") })
            }
    )
    CartResponse create(CreateCartRequest request);

    @Operation(
            summary = "Adiciona um novo item no carrinho",
            description = "Adiciona o item ao carrinho e salva no banco de dados em memória",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK - Retorno em caso de sucesso em que o item foi adicionado do carrinho", content = { @Content(schema = @Schema(implementation = CartResponse.class), mediaType = "application/json") }),
                    @ApiResponse(responseCode = "400", description = "Retorno informando qual campo do carrinho está incorreto e por qual motivo", content = { @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = "application/json") })
            }
    )
    CartResponse addItem(@Parameter(description = "CartId do carrinho ao qual se deseja adicionar o item", required = true) String cartId, AddCartItemRequest request);

    @Operation(
            summary = "Atualiza um item do carrinho",
            description = "Atualiza o item no carrinho e salve no banco de dados em memória",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK - Retorno em caso de sucesso em que o item foi atualizado do carrinho", content = { @Content(schema = @Schema(implementation = CartResponse.class), mediaType = "application/json") }),
                    @ApiResponse(responseCode = "400", description = "Retorno informando qual campo do carrinho está incorreto e por qual motivo", content = { @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = "application/json") })
            }
    )
    CartResponse updateItem(@Parameter(description = "CartId do carrinho ao qual se deseja atualizar o item", required = true) String cartId, UpdateCartItemRequest request);

    @Operation(
            summary = "Remove um item do carrinho",
            description = "Remove o item do carrinho e salva no banco de dados em memória",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK - Retorno em caso de sucesso em que o item foi removido do carrinho", content = { @Content(schema = @Schema(implementation = CartResponse.class), mediaType = "application/json") }),
                    @ApiResponse(responseCode = "400", description = "Retorno informando qual campo do carrinho está incorreto e por qual motivo", content = { @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = "application/json") })
            }
    )
    CartResponse removeItem(@Parameter(description = "CartId do carrinho ao qual se deseja remover o item", required = true) String cartId, RemoveCartItemRequest request);

}
