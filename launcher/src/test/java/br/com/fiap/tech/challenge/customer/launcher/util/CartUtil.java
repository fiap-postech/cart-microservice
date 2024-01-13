package br.com.fiap.tech.challenge.customer.launcher.util;

import br.com.fiap.tech.challenge.rest.resource.request.AddCartItemRequest;
import br.com.fiap.tech.challenge.rest.resource.request.CreateCartRequest;
import br.com.fiap.tech.challenge.rest.resource.response.CartResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static br.com.fiap.tech.challenge.customer.launcher.util.JsonUtil.asJsonString;
import static br.com.fiap.tech.challenge.customer.launcher.util.JsonUtil.fromJsonString;
import static org.assertj.core.api.Assertions.assertThat;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartUtil {

    public static CartResponse createCart(CreateCartRequest createCartRequest) {
        try {
            var request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8690/cart"))
                    .headers("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                    .POST(HttpRequest.BodyPublishers.ofString(asJsonString(createCartRequest)))
                    .build();

            var response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            assertThat(response.statusCode()).isEqualTo(201);

            return fromJsonString(response.body(), CartResponse.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static CartResponse addItemToCart(String cartId, AddCartItemRequest addCartItemRequest) {
        try {
            var uri = String.format("http://localhost:8690/cart/%s/items", cartId);

            var request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .headers("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                    .POST(HttpRequest.BodyPublishers.ofString(asJsonString(addCartItemRequest)))
                    .build();

            var response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            assertThat(response.statusCode()).isEqualTo(200);

            return fromJsonString(response.body(), CartResponse.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
