package br.com.fiap.tech.challenge.driven.product.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("br.com.fiap.tech.challenge.driven.product")
@ComponentScan("br.com.fiap.tech.challenge.driven.product")
public class ProductClientConfiguration {
}
