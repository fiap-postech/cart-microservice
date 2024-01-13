package br.com.fiap.tech.challenge.customer.launcher.expectations;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Model;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.JsonBody;

import static br.com.fiap.tech.challenge.customer.launcher.fixture.Fixture.create;
import static br.com.fiap.tech.challenge.customer.launcher.util.JsonUtil.asJsonString;
import static org.mockserver.model.HttpRequest.request;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetProductExpectations {
    public static <O> HttpRequest getProductSuccessExpectation(MockServerClient client, String id, Model<O> output) {
        var request = request()
                .withPath(String.format("/product/%s", id))
                .withMethod("GET");

        var response = HttpResponse.response()
                .withStatusCode(200)
                .withBody(new JsonBody(asJsonString(create(output))));

        client.when(request).respond(response);

        return request;
    }

}
