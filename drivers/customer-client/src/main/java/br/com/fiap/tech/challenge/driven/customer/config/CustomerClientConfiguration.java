package br.com.fiap.tech.challenge.driven.customer.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("br.com.fiap.tech.challenge.driven.customer")
@ComponentScan("br.com.fiap.tech.challenge.driven.customer")
public class CustomerClientConfiguration {
}
