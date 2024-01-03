package br.com.fiap.tech.challenge.rest.resource;

import br.com.fiap.tech.challenge.adapter.controller.cart.AddCartItemController;
import br.com.fiap.tech.challenge.adapter.controller.cart.CreateCartController;
import br.com.fiap.tech.challenge.adapter.controller.cart.FindCartByUUIDController;
import br.com.fiap.tech.challenge.adapter.controller.cart.RemoveCartItemController;
import br.com.fiap.tech.challenge.adapter.controller.cart.UpdateCartItemController;
import br.com.fiap.tech.challenge.rest.mapping.CartResponseMapper;
import br.com.fiap.tech.challenge.rest.mapping.CreateCartMapper;
import br.com.fiap.tech.challenge.rest.mapping.ManageCartItemMapper;
import br.com.fiap.tech.challenge.rest.resource.doc.CartResourceDoc;
import br.com.fiap.tech.challenge.rest.resource.request.AddCartItemRequest;
import br.com.fiap.tech.challenge.rest.resource.request.CreateCartRequest;
import br.com.fiap.tech.challenge.rest.resource.request.RemoveCartItemRequest;
import br.com.fiap.tech.challenge.rest.resource.request.UpdateCartItemRequest;
import br.com.fiap.tech.challenge.rest.resource.response.CartResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartResource implements CartResourceDoc {

    private final CreateCartMapper createCartMapper;
    private final ManageCartItemMapper manageCartItemMapper;
    private final CartResponseMapper cartResponseMapper;

    private final FindCartByUUIDController findCartByUUIDController;
    private final CreateCartController createCartController;
    private final AddCartItemController addCartItemController;
    private final UpdateCartItemController updateCartItemController;
    private final RemoveCartItemController removeCartItemController;


    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public CartResponse get(@PathVariable("uuid") String uuid) {
        return cartResponseMapper.toResponse(findCartByUUIDController.get(uuid));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CartResponse create(@RequestBody @Valid CreateCartRequest request) {
        return cartResponseMapper.toResponse(createCartController.create(createCartMapper.toDTO(request)));
    }

    @PostMapping("{cartId}/items")
    @ResponseStatus(HttpStatus.OK)
    public CartResponse addItem(@PathVariable("cartId") String cartId, @RequestBody @Valid AddCartItemRequest request) {
        return cartResponseMapper.toResponse(
                addCartItemController.add(cartId, manageCartItemMapper.toDTO(request))
        );
    }

    @PatchMapping("{cartId}/items")
    @ResponseStatus(HttpStatus.OK)
    public CartResponse updateItem(@PathVariable("cartId") String cartId, @RequestBody @Valid UpdateCartItemRequest request) {
        return cartResponseMapper.toResponse(
                updateCartItemController.update(cartId, manageCartItemMapper.toDTO(request))
        );
    }

    @DeleteMapping("{cartId}/items")
    @ResponseStatus(HttpStatus.OK)
    public CartResponse removeItem(@PathVariable("cartId") String cartId, @RequestBody @Valid RemoveCartItemRequest request) {
        return cartResponseMapper.toResponse(
                removeCartItemController.remove(cartId, manageCartItemMapper.toDTO(request))
        );
    }
}