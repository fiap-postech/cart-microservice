package br.com.fiap.tech.challenge.rest.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class SwaggerConfiguration implements WebMvcConfigurer {

    @Value("${server.port}")
    private String port;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl("http://localhost:"+port);
        devServer.setDescription("Server URL - Ambiente de Desenvolvimento");

        Server prodServer = new Server();
        prodServer.setUrl("N/A");
        prodServer.setDescription("Server URL - Ambiente de Produção");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Tech Challenge API")
                .version("1.0.0")
                .description("Esta API expõe endpoints referentes ao Tech Challenge de criação de um sistema de gerenciamento de pedidos de fast food.")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/doc");
    }
}
