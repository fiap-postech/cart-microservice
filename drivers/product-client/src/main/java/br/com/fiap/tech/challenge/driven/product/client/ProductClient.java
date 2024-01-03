package br.com.fiap.tech.challenge.driven.product.client;

import br.com.fiap.tech.challenge.driven.product.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ProductClient", url = "${product.host}")
public interface ProductClient {


    @GetMapping("/product/{uuid}")
    ResponseEntity<ProductResponse> getByUUID(@PathVariable("uuid") String uuid);

}
