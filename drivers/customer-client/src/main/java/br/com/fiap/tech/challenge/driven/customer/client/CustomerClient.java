package br.com.fiap.tech.challenge.driven.customer.client;

import br.com.fiap.tech.challenge.driven.customer.response.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CustomerClient", url = "${customer.host}")
public interface CustomerClient {

    @GetMapping("/customer/{id}")
    ResponseEntity<CustomerResponse> getById(@PathVariable("id") String id);

}
