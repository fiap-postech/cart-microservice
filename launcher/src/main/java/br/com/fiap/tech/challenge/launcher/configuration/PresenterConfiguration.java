package br.com.fiap.tech.challenge.launcher.configuration;

import br.com.fiap.tech.challenge.adapter.presenter.CartPresenter;
import br.com.fiap.tech.challenge.adapter.presenter.PresenterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PresenterConfiguration {

    @Bean
    public CartPresenter cartPresenter() {
        return PresenterFactory.cartPresenter();
    }

}
