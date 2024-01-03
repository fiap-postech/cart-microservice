package br.com.fiap.tech.challenge.launcher.configuration;

import br.com.fiap.tech.challenge.adapter.driven.redis.config.RedisConfiguration;
import br.com.fiap.tech.challenge.driven.cart.CartProducerConfiguration;
import br.com.fiap.tech.challenge.driven.customer.config.CustomerClientConfiguration;
import br.com.fiap.tech.challenge.driven.product.config.ProductClientConfiguration;
import br.com.fiap.tech.challenge.rest.config.RestConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        RestConfiguration.class,
        RedisConfiguration.class,
        CustomerClientConfiguration.class,
        ProductClientConfiguration.class,
        CartProducerConfiguration.class
})
public class MainConfiguration {
}
