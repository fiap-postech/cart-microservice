package br.com.fiap.tech.challenge.customer.launcher.config;

import br.com.fiap.tech.challenge.launcher.configuration.ControllerConfiguration;
import br.com.fiap.tech.challenge.launcher.configuration.GatewayConfiguration;
import br.com.fiap.tech.challenge.launcher.configuration.MainConfiguration;
import br.com.fiap.tech.challenge.launcher.configuration.PresenterConfiguration;
import br.com.fiap.tech.challenge.launcher.configuration.UseCaseConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "br.com.fiap.tech.challenge")
@EntityScan(basePackages = "br.com.fiap.tech.challenge")
@Import(
        {
                ControllerConfiguration.class,
                GatewayConfiguration.class,
                MainConfiguration.class,
                PresenterConfiguration.class,
                UseCaseConfiguration.class
        }
)
public class TestConfiguration {
}
